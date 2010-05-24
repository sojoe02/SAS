/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.events;

import java.util.ArrayList;
import simulator.data.DataEntity;
import simulator.events.sailing.DestReachedEvent;
import simulator.events.sailing.ShipStartEvent;
import simulator.events.stat.CalcProbabilityEvent;
import simulator.events.stat.LambdaArrayEvent;
import simulator.events.stat.MakeTrafficEvent;


/**
 *
 * @author Zagadka
 */
public class EventsEntity {

     private static EventsEntity events = new EventsEntity();

     LambdaArrayEvent lambdaarray;
     MakeTrafficEvent maketraffic;
     CalcProbabilityEvent getProbability;

    private EventsEntity(){

    }

     public static synchronized EventsEntity getInstance(){
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

    public Integer[][] makeLambdaArray(int maxvalue, int harbours){
	lambdaarray = new LambdaArrayEvent(maxvalue, harbours);

	return lambdaarray.lambdaArray();
    }

    public ArrayList<Double> makeTraffic(int lambda, int t){

	maketraffic = new MakeTrafficEvent();

	return maketraffic.makeTrafficArray(lambda, t);
    }

    public double getPoissonProb(int t, double L, int k){

	getProbability = new CalcProbabilityEvent();

	return getProbability.probWithPoisson(t, L, k);
    }
}
