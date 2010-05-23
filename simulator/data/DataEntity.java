/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.data;

import simulator.events.EventsEntity;

/**
 *
 * @author Zagadka
 */
public class DataEntity {

    getRoute getroute;
    private static DataEntity data = new DataEntity();

    private DataEntity() {
    }

    public static DataEntity getInstance() {

	return data;
    }

    public void startShip(String to, String from, int v, int time, int shipID) {

	getroute = new getRoute(to, from);


	EventsEntity.getInstance().StartShip(getroute.getLongditude(),
		getroute.getLatitude(), v, time, shipID);

    }

       public Integer[][] makeLambdaArray(int maxvalue, int harbours){
	
	return EventsEntity.getInstance().makeLambdaArray(maxvalue, harbours);
    }
}
