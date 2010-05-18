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
public class GpsSim implements Runnable {

    private boolean running = false;
    private Random generator = new Random();
    private double lat=0,lon=0;

    public void run() {

	running = true; //the thread is running!

	while (running) {
	    lat = (double) generator.nextInt(180) - 90;
	    lon = (double) generator.nextInt(360) - 180;
	    MAPCONTROL.coordinateHandling(lat, lon, 0);
	    lat = (double) generator.nextInt(180) - 90;
	    lon = (double) generator.nextInt(360) - 180;
	    MAPCONTROL.coordinateHandling(lat, lon, 1);
	    try {
		Thread.sleep(100);
	    } catch (InterruptedException ex) {
	    }
	}
	System.exit(0); //exit

    }
}
