package control;

import Network.*;
import domain.entity.ESAS;
import domain.mediator.MBroker;
import java.sql.SQLException;

public class CActioner {

    private MBroker broker;
    private ESAS sas;

    public CActioner() throws Exception {
	sas = new ESAS();
	broker = new MBroker(sas);
    }
		//henter user og rettigheder
    public SendObject login(SendObject rObject) throws SQLException	{
		SendObject sObject = new SendObject(broker.login(rObject.getUserID()));
		return sObject;
	    }
		/*
		 * findShipDates henter de skibsdatoer der overholder kundens ønsker.
		Resultet af de fundne datoer sendes op til kunden i
		presentationsalget i form af et arraylist.
		 */
    public SendObject findShipDates(SendObject rObject) throws SQLException	{
		SendObject sObject = new SendObject("findShipDates",
			broker.findShipDates(rObject.getStartDest(),
			rObject.getEndDest(), rObject.getDate(), rObject.getContainers()));
		return sObject;
	    }
		//opretter order i DB
    public void placeOrder(SendObject rObject) throws SQLException	{
		broker.placeOrder(rObject.getUserID(), rObject.getShipID(),
			rObject.getStartSID(), rObject.getEndSID(), rObject.getContainers(), rObject.getContent());
	    }
    }
