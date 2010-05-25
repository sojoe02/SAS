/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.data;

import java.util.ArrayList;
import simulator.events.EventsEntity;

/**
 *
 * @author Mats Larsen, Stefan Skytthe, Dan Vi, Søren Jørgensen
 */
public class DataEntity {

    getRoute getroute;
    EventsEntity events;
    private static DataEntity data = new DataEntity();

    private DataEntity() {
	events = EventsEntity.getInstance();
    }

    public static synchronized DataEntity getInstance() {

	return data;
    }

    public void startShip(String to, String from, int v, int time, int shipID) {

	getroute = new getRoute(to, from);

	EventsEntity.getInstance().StartShip(getroute.getLongditude(),
		getroute.getLatitude(), v, time, shipID);

    }

    public Integer[][] makeLambdaArray(int maxvalue, int harbours) {

	return events.makeLambdaArray(maxvalue, harbours);
    }

    public ArrayList<Double> makeTraffic(int lambda, int t) {

	return events.makeTraffic(lambda, t);
    }

    public double getPoissonProb(int t, double L, int k) {

	return events.getPoissonProb(t, L, k);
    }
}
