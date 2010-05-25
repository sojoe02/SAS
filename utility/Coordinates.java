/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author Mats Larsen, Stefan Skytthe, Dan Vi, Søren Jørgensen
 */
public class Coordinates {

    private double lon;
    private double lat;

    public Coordinates(double lon, double lat) {
	this.lon = lon;
	this.lat = lat;
    }

    public void setLong(double lon) {
	this.lon = lon;
    }

    public double getLong() {
	return lon;
    }

    public void setLat(double lat) {
	this.lat = lat;
    }

    public double getLat() {
	return lat;
    }
}
