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


    /*
     * MBroker har samme reference til ESas som CActioner.
     * Der oprettes forbindelse til foundation.
     */
    public MBroker(ESAS sas) throws ClassNotFoundException {
	this.sas = sas;
	transactionScript = new MtransactionScript();
	connection = new FConnection(dbUrl, dbDriver);
    }

    public ArrayList login(int userID) throws SQLException {
	ArrayList<String[]> userInfo = new ArrayList();

	//tjekke DB-connection
	if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    return null;
	}

	//TODO
	String sqlStmt =
		" SELECT userType,userID "
		+" From Users "
		+" WHERE userID = " + userID
		+" ;";
	
	ResultSet rsUserInfo = connection.getReader().query(sqlStmt);
	

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

	return availableShips;



//SELECT		d1.ShipID AS ship, d1.schedulingID AS startSID, d1.eventDate AS startDate,
//			d2.schedulingID AS endSID, d2.eventDate AS endDate,
//			d1.currentContainers AS startC, d2.currentContainers as endC
//FROM		Scheduling d1, Scheduling d2
//WHERE		d1.harbour='Odense' And d1.eventType='Departure'
//AND 		d2.harbour='Amsterdam' And d2.eventType='Arrival'
//AND 		d1.shipID=d2.shipID
//ORDER BY 	startDate
    }


    public void placeOrder (int userID, int shipID, int startSID, int endSID, int containers) throws SQLException{
	//opretter order i DB
	String sqlStmt = "INSERT INTO orders (userID, containers, startScheduling,endScheduling)"
		+ "VALUES ("
		+ userID + ","
		+ containers + ","
		+ startSID + ","
		+ endSID+ ");"
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

//INSERT INTO orders (userID, containers, startScheduling,endScheduling)
//VALUES (1,10,1,6);

//UPDATE Scheduling
//SET currentContainers = currentContainers+10
//WHERE shipID = 1 AND schedulingID >= 3 AND schedulingID <= 20;

    }






}
