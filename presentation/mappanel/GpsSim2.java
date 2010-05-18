/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.mappanel;

import presentation.mappanel.MAPCONTROL;
import java.util.Random;

/**
 *
 * @author Soren V. Jorgensen
 */
public class GpsSim2 implements Runnable {

    private int dirlat = -1;
    private int dirlong = -1;
    private double lon = 50;
    private double lat = 50;
     int gpsindex;

    public GpsSim2(int i) {
	gpsindex = i;
	
    }

    public void run() {

	Thread thisThread = Thread.currentThread();
	while (thisThread.isAlive()) {
	    randomGen();
	    //System.out.println(SystemHandler.lat + SystemHandler.lon);
	    try {
		Thread.sleep(1000);
	    } catch (InterruptedException ex) {
	    }
	}
	//System.exit(0); //exit

    }

    public void randomGen() {
	Random generator = new Random();

	if (generator.nextInt(100) == 1 && dirlat == -1) {
	    dirlat = 1;
	}
	if (generator.nextInt(100) == 1 && dirlat == 1) {
	    dirlat = -1;
	}

	lat += dirlat;

	if (lat > 89) {
	    lat = -89;
	}
	if (lat < -89) {
	    lat = 89;
	}



	if (generator.nextInt(60) == 1 && dirlong == 1) {
	    dirlong = -1;
	}
	if (generator.nextInt(60) == 1 && dirlong == -1) {
	    dirlong = 1;
	}


	lon = lon + dirlong;

	if (lon > 179) {
	    lon = -179;
	}
	if (lon < -179) {
	    lon = 179;
	}

	MAPCONTROL.coordinateHandling(lat, lon,gpsindex);
	//System.out.println(lat + "and" + lon);
    }
}
