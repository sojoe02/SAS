package control;

import domain.entity.ESAS;
import domain.mediator.MBroker;

import java.util.ArrayList;
import java.util.Date;

public class CActioner {

    private MBroker broker;
    private ESAS sas;

    public CActioner() throws ClassNotFoundException {
	sas = new ESAS();
	broker = new MBroker(sas);
    }


    // findShipDates henter de skibsdatoer der overholder kundens ønsker.
    public ArrayList findShipDates(String startDest, String endDest, Date date, int containers) throws Exception {

	/* Resultet af de fundne datoer sendes op til kunden i
	 presentationsalget i form af et arraylist.
	 */
	return broker.findShipDates(startDest, endDest, date, containers);
    }

    //opret order
    public void placeOrder (int userID, int shipID, int startSID, int endSID, int containers) throws Exception {

	broker.placeOrder(userID, shipID, startSID, endSID, containers);
    }

}
