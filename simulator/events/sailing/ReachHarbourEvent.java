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
public class ReachHarbourEvent extends Observable {

    private static ReachHarbourEvent harbour = new  ReachHarbourEvent();

    private ReachHarbourEvent() {
	//System.out.println("The ship has reached its destination");
    }
    
      public static synchronized ReachHarbourEvent getInstance(){
	 return harbour;
     }

    public void HarbourReachedEvent(int shipID) {
	setChanged();
	notifyObservers(Integer.toString(shipID));
    }
}
