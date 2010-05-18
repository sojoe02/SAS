/*
 * This Static class will recieve coordinates from ship threads
 * and let the mappanel class read from it. It is sort of an entity class!
 * however the nature make it more prudent to choose this form instead of a
 * regular class.
 */
package presentation.mappanel;


import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author Soren V. Jorgensen
 */
public class MAPCONTROL {

    private static int gpsNr = 1;

    /*Hashtables for londitude and latidute, Hashtable is syncronized so reading
     * and writing is done by semaphore, which allow for multiple thread access.
     */
    public static Map<Integer, Double> longditude =
	    new Hashtable<Integer, Double>(50);
    public static Map<Integer, Double> latitude =
	    new Hashtable<Integer, Double>(50);

    public static double getLongditude(int i) {
	return longditude.get(i);
    }

    public static double getLatitude(int i) {
	return latitude.get(i);
    }

    //Method for writing GPS coordinates from external thread
    public static void coordinateHandling(double lat, double lon, int i) {
	latitude.put(i, lat);
	longditude.put(i, lon);
    }

    public static void addGps() {
	latitude.put(gpsNr, 0.0);
	gpsNr++;
    }

    public static void removeGps(int i) {
	latitude.remove(i);
	if (gpsNr > 0) {
	    gpsNr--;
	}
    }

    public static int getGpsMap() {
	return gpsNr;
    }
}
