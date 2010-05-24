/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.events.sailing;

import java.util.Observable;
import java.util.Observer;
import presentation.mappanel.MapPanelEntity;
import simulator.Coordinates;

/**
 *
 * @author Zagadka
 */
public class ShipStartEvent implements Observer {
    /*this class will start the ship moving it from one harbour to another,
     *  using the other classes in events package.
     * It will observe on DestReachEvent.
     */

    private Coordinates from = null;
    private Coordinates to = null;

    private int counter = 0;
    private int harbourstart;
    private int v ;
    private int shipID;
    private int time;
    private double[] lat;
    private double[] lon;
    private DestReachedEvent dest;

    public ShipStartEvent(double[] lat, double[] lon, int v, int time, int shipID, DestReachedEvent dest) {

	this.dest = dest;
	this.lat = lat;
	this.lon = lon;
	this.v = v;
	this.shipID = shipID;
	this.time = time;
	
	from = new Coordinates(lat[counter], lon[counter]);
	to = new Coordinates(lat[counter + 1], lon[counter + 1]);
	MapPanelEntity.getInstance().addShip(shipID);
	counter +=2;

	harbourstart = lat.length;
	new Thread(new ShipMovementEvent(to, from, shipID, time, v, dest)).start();
    }

    public void update(Observable o, Object o1) {
	
	if (counter < harbourstart) {

	    from = new Coordinates(lat[counter-1], lon[counter-1]);
	    to = new Coordinates(lat[counter], lon[counter]);

	    new Thread(new ShipMovementEvent(to, from, shipID, time, v, dest)).start();

	    counter ++;

	} else {
	    ReachHarbourEvent.getInstance().HarbourReachedEvent(shipID);
	}
    }
}
