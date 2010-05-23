/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.logic.ship;

import simulator.Coordinates;

/**
 *
 * @author 
 */
public class ShipMoveLogic {

    public Coordinates getNextPos(Coordinates to, Coordinates from, int v, int time) {

	double longNow = from.getLong();
	double latNow = from.getLat();
	double longEnd = to.getLong();
	double latEnd = to.getLat();
	double angle = Math.atan2(latEnd - latNow, longEnd - longNow);

	double vLong = v * Math.cos(angle) * 0.0001;
	double vLat = v * Math.sin(angle) *0.0001;

	double nextLong = longNow + time * vLong;
	double nextLat = latNow + time * vLat;

	return new Coordinates(nextLong, nextLat);
    }
}
