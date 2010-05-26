package control;

import Network.*;
import domain.entity.ESAS;
import domain.mediator.MBroker;

public class CActioner {

    private MBroker broker;
    private ESAS sas;

    public CActioner() throws Exception {
	sas = new ESAS();
	broker = new MBroker(sas);
    }
    /* Denne metode finder ud af hvilken metode som er blevet kaldt fra klienten.
    Hvor den returnerer et sObject som skal sendes til klienten som respons.
     * Der betnyttes synchronized for programmet er multitrådet.
     */

    public synchronized SendObject chooseMetode(SendObject sObject) throws Exception {
	try {
	    // Testes om login metoden er blevet kaldt.
	    if (sObject.getMetodeChoose().equals("login")) {
		//henter user og rettigheder
		sObject = new SendObject(broker.login(sObject.getUserID()));
		return sObject;
	    }
	    // Testes om findShipDates metoden er blevet kaldt.
	    if (sObject.getMetodeChoose().equals("findShipDates")) {
		/*
		 * findShipDates henter de skibsdatoer der overholder kundens ønsker.
		Resultet af de fundne datoer sendes op til kunden i
		presentationsalget i form af et arraylist.
		 */
		sObject = new SendObject("findShipDates",
			broker.findShipDates(sObject.getStartDest(),
			sObject.getEndDest(), sObject.getDate(), sObject.getContainers()));
		return sObject;
	    }
// Testes om placeOrder metoden er blevet kaldt.
	    if (sObject.getMetodeChoose().equals("placeOrder")) {
		//opretter order i DB
		broker.placeOrder(sObject.getUserID(), sObject.getShipID(),
			sObject.getStartSID(), sObject.getEndSID(), sObject.getContainers(), sObject.getContent());
	    }
	} catch (java.lang.NullPointerException e) {
	    e.getMessage();
	}
	return sObject;
    }
}
