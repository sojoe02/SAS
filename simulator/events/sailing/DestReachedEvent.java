/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.events.sailing;

import java.util.Observable;
/**
 *
 * @author Zagadka
 */



public class DestReachedEvent extends Observable{

    //private static DestReachedEvent dest = new DestReachedEvent();

    /*check to see if there are any more coordinates and the heading needs to be
     * changed
     * if not do a reach harbourevent and the trip is done.
     */

    public void DestReachedEvent() {   
    setChanged();
	notifyObservers();
    }

    
}
