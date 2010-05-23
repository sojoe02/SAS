/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.events;

import simulator.data.DataEntity;
import simulator.events.sailing.DestReachedEvent;
import simulator.events.sailing.ShipStartEvent;


/**
 *
 * @author Zagadka
 */
public class EventsEntity {

     private static EventsEntity events = new EventsEntity();

    private EventsEntity(){

    }

     public static EventsEntity getInstance(){
	 return events;
     }

    //This method starts the ship whenever a new route has been planned
    public void StartShip(double[] lat, double[] lon, int v, int time, int shipID) {
	// Create the Subject and Observers.

	DestReachedEvent dest = new DestReachedEvent();

	ShipStartEvent sObs = new ShipStartEvent(lat, lon, v, time, shipID, dest);
	// Add those Observers!
	dest.addObserver(sObs);

	
	

    }
}
