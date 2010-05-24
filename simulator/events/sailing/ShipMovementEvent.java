/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.events.sailing;

import java.util.logging.Level;
import java.util.logging.Logger;
import simulator.Coordinates;
import presentation.mappanel.MapPanelEntity;
import simulator.logic.LogicEntity;

/**
 *
 * @author Zagadka
 */
public class ShipMovementEvent implements Runnable {

    private Coordinates to = null;
    // private Coordinates next = null;
    private Coordinates from = null;
    private Thread shipnMove;
    private int time;
    private int v;
    private int shipID;
    private DestReachedEvent dest;

    //tolerance paremeter when I need to check if the ship is in harbour.
    private final double tolerance = 0.5;

    //boolean to help kill the thread.
    private boolean threadDone = false;


    public ShipMovementEvent(Coordinates to, Coordinates from,
	    int shipID, int time, int v, DestReachedEvent dest) {

	this.dest = dest;
	this.time = time;
	this.v = v;
	this.shipID = shipID;
	this.from = from;
	this.to = to;
	

    }

    public void move(int v, int time, int shipID) {

	from = LogicEntity.getInstance().movShip(to, from, v, time);

	MapPanelEntity.LATITUDE.put(shipID, from.getLat());
	MapPanelEntity.LONGDITUDE.put(shipID, from.getLong());	
	
	//comparing doubles with tolerance.
	if (Math.abs(from.getLat() - to.getLat())< tolerance &&
		Math.abs(from.getLong() - to.getLong()) < tolerance){
	    killme();
	    dest.DestReachedEvent();
	}
    }

    public void killme(){
	threadDone = true;
    }

    public void run() {


	

	while (!threadDone) {
	    try {
		Thread.sleep(10); //setting the speed with which the ship updates
		//coordinates
	    } catch (InterruptedException ex) {
		Logger.getLogger(ShipMovementEvent.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    move(time, v, shipID);

	}


    }
}
