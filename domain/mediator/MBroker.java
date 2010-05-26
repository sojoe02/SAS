package domain.mediator;

import acquaintance.IAContants;
import domain.entity.ESAS;
import foundation.*;

import java.util.ArrayList;
import java.util.Date;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MBroker implements IAContants{

    private ESAS sas;
    private MtransactionScript transactionScript;
    private FConnection connection;
    private static int orderID;

    /*
     * MBroker har samme reference til ESas som CActioner.
     * Der oprettes forbindelse til foundation.
     */
    public MBroker(ESAS sas) throws Exception {
	this.sas = sas;
	transactionScript = new MtransactionScript();
	connection = new FConnection(dbUrl, dbDriver);

	//Opretter alle objekter i domain.entity, da der ikke anvendes lazy load eller lign.
	initialisation();

	//tjekker connection
	if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    System.out.println("fejl");
	}
	//henter OrderID
	ResultSet rsOrderID = connection.getReader().query("SELECT MAX(orderID) AS orderID FROM orders;");
	while (rsOrderID.next()) {
	    orderID = rsOrderID.getInt("orderID")+1;
	}
	connection.getReader().closeResult(rsOrderID);
    }

    public ArrayList<Integer> login(int userID) throws SQLException {
	ArrayList<Integer> userInfo = new ArrayList<Integer>();

	//tjekke DB-connection
	if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    return null;
	}

	String sqlStmt =
		    " SELECT userID,adminAccess,simulatorAccess"
		    + " FROM accessControl ac1"
		    + " WHERE userID = " + userID
		    + " ;"
	;
	
	ResultSet rsUserInfo = connection.getReader().query(sqlStmt);

	while (rsUserInfo.next()) {
	    userInfo.add(rsUserInfo.getInt("userID"));
	    userInfo.add(rsUserInfo.getInt("adminAccess"));
	    userInfo.add(rsUserInfo.getInt("simulatorAccess"));
	}

	connection.getReader().closeResult(rsUserInfo);
	return userInfo;
    }

    public ArrayList<String[]> findShipDates(String startDest, String endDest,Date date, int containers) throws SQLException {
	ArrayList<String[]> availableShips = new ArrayList();

	//tjekke DB-connection
	if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    return null;
	}

	   //sql kald der finder de skibe der opfylder krav
	String sqlStmt =
		"SELECT d1.ShipID AS ship, d1.schedulingID AS startSID, d1.eventDate AS startDate,"
		+" d2.schedulingID AS endSID, d2.eventDate AS endDate"
		+" FROM Scheduling d1, Scheduling d2"
		+" WHERE d1.harbour='"+ startDest +"' AND d1.eventType='Departure'"
		+" AND d2.harbour='"+ endDest +"' AND d2.eventType='Arrival'"
		+" AND d1.shipID=d2.shipID"
		+" AND d1.schedulingID < d2.schedulingID"
		+" ORDER BY startDate"
		+";"
	;

	ResultSet rsShips = connection.getReader().query(sqlStmt);
	//indlæser resultat i 2-dim ArrayList
	while (rsShips.next()) {
	    String[] availableShip = new String[5];

	    availableShip[0] = Integer.toString(rsShips.getInt("ship"));
	    availableShip[1] = Integer.toString(rsShips.getInt("startSID"));
	    availableShip[2] = Integer.toString(rsShips.getInt("endSID"));
	    availableShip[3] = rsShips.getString("startDate");
	    availableShip[4] = rsShips.getString("endDate");
	    availableShips.add(availableShip);
	}

	connection.getReader().closeResult(rsShips);

	return availableShips;





//SELECT		d1.ShipID AS ship, d1.schedulingID AS startSID, d1.eventDate AS startDate,
//			d2.schedulingID AS endSID, d2.eventDate AS endDate,
//			d1.currentContainers AS startC, d2.currentContainers as endC
//FROM		Scheduling d1, Scheduling d2
//WHERE		d1.harbour='Odense' And d1.eventType='Departure'
//AND 		d2.harbour='Amsterdam' And d2.eventType='Arrival'
//AND 		d1.shipID=d2.shipID
//AND			d1.schedulingID < d2.schedulingID
//ORDER BY 	startDate
    }


    public void placeOrder (int userID, int shipID, int startSID, int endSID, int containers, String content) throws SQLException{
	//opretter order i DB
	String sqlStmt = "INSERT INTO orders (orderID, userID, shipID, containers, startScheduling,endScheduling,content,orderStatus)"
		+ "VALUES ("
		+ orderID + ","
		+ userID + ","
		+ shipID + ","
		+ containers + ","
		+ startSID + ","
		+ endSID + ","
		+ "'" +content + "'" + ","
		+ " ' waiting ' " +
		");"
	;


	connection.getWriter().updatequery(sqlStmt);
	sqlStmt = "UPDATE Scheduling"
		+ " SET currentContainers=currentContainers+" + containers
		+ " WHERE shipID = " + shipID
		+ " AND schedulingID >= " + startSID
		+ " AND schedulingID <= " + endSID
		+ ";"
	;
	connection.getWriter().updatequery(sqlStmt);

	//opretter order-objekt
    	sas.placeOrder(userID, orderID, startSID, endSID, shipID, content);

	orderID = orderID+1;

//INSERT INTO orders (userID, containers, startScheduling,endScheduling,content)
//VALUES (1,10,1,6,"ting");

//UPDATE Scheduling
//SET currentContainers = currentContainers+10
//WHERE shipID = 1 AND schedulingID >= 3 AND schedulingID <= 20;

    }


    private void initialisation() throws Exception{
	   
	//tjekker db connection
	if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    System.out.println("fejl");
	}



	// oprettelse af havne
	String sqlStmt = "SELECT * FROM harbour;";

	ResultSet rsHarbours = connection.getReader().query(sqlStmt);
	//opretter objekter
	while (rsHarbours.next()) {
	    sas.createHarbour(rsHarbours.getString("name"),
		    rsHarbours.getString("coordinate"),
		    rsHarbours.getString("nationality"));
	}

	
	//oprettelse af skibe
	sqlStmt = "SELECT * FROM ship;";

	ResultSet rsShips = connection.getReader().query(sqlStmt);
	//opretter objekter
	while (rsShips.next()) {
	    //henter container
	    ArrayList<String[]> containers = new ArrayList<String[]>();
	    sqlStmt = "SELECT * FROM Container WHERE shipID = " + rsShips.getInt("shipID") + ";";

	    ResultSet rsContainers = connection.getReader().query(sqlStmt);
	    while(rsContainers.next()) {
		String[] container = new String[2];

		container[0] = Integer.toString(rsContainers.getInt("containerID"));
		container[1] = rsContainers.getString("content");

		containers.add(container);
	    }

	    //henter scheduling
	    ArrayList<String[]> schedulings = new ArrayList<String[]>();
	    sqlStmt = "SELECT * FROM scheduling WHERE shipID = " + rsShips.getInt("shipID") + ";";
	    
	    ResultSet rsSchedulings = connection.getReader().query(sqlStmt);
	    while (rsSchedulings.next()) {		
		String[] scheduling = new String[5];

		scheduling[0] = Integer.toString(rsSchedulings.getInt("schedulingID"));
		scheduling[1] = rsSchedulings.getString("eventType");
		scheduling[2] = rsSchedulings.getString("eventDate");
		scheduling[3] = rsSchedulings.getString("harbour");
		scheduling[4] = Integer.toString(rsSchedulings.getInt("currentContainers"));

		schedulings.add(scheduling);
	    }
	    //Opretter ship-objekt i entity
	    sas.createShip(rsShips.getInt("shipID"),
		    rsShips.getString("name"),
		    rsShips.getString("captain"),
		    rsShips.getInt("maxContainers"),
		    containers, schedulings);
	    //nulstiller arraylists
	    containers.clear();
	    schedulings.clear();
	}

	//oprettelse af users
	sqlStmt = "SELECT * FROM users;";

	ResultSet rsUsers = connection.getReader().query(sqlStmt);
	//opretter objekter
	while (rsUsers.next()) {

	    //henter orders
	    ArrayList orders = new ArrayList();
	    sqlStmt = "SELECT * FROM orders WHERE userID = " + rsUsers.getInt("userID") + ";";

	    ResultSet rsOrders = connection.getReader().query(sqlStmt);
	    while (rsOrders.next()) {
		Object[] order = new Object[8];
		
		order[0] = Integer.toString(rsOrders.getInt("orderID"));
		order[1] = Integer.toString(rsOrders.getInt("containers"));
		order[2] = Integer.toString(rsOrders.getInt("startScheduling"));
		order[3] = Integer.toString(rsOrders.getInt("endScheduling"));
		order[4] = rsOrders.getString("content");
		order[5] = rsOrders.getString("orderStatus");
		order[6] = Integer.toString(rsOrders.getInt("shipID"));

		//opretter container Array
		ArrayList<Integer> containerArray = new ArrayList<Integer>();
		sqlStmt = "SELECT * FROM container WHERE shipID =" + rsOrders.getInt("shipID") +"" +
			" AND orderID =" + rsOrders.getInt("orderID") +
			" ;";

		ResultSet rsContainer = connection.getReader().query(sqlStmt);
		while(rsContainer.next()) {
		    containerArray.add(rsContainer.getInt("containerID"));
		}

		order[7] = containerArray;

		orders.add(order);
	    }

	    sas.createUser(rsUsers.getInt("userID"),
		    rsUsers.getString("name"),
		    rsUsers.getString("company"),
		    rsUsers.getString("adress"),
		    rsUsers.getString("userType"),
		    orders);
	}


    }
}
