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

    public ArrayList findShipDates(String startDest, String endDest,Date date, int containers) throws SQLException {
	ArrayList availableShips = new ArrayList();

	//tjekke db-connection
	if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    return null;
	}

	    //LAV SQL querie DER FINDER SKIBE

	return availableShips;
    }


    public void placeOrder (int userID, int shipID, int startSID, int endSID, int containers) throws SQLException{
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
