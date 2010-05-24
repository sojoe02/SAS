/*
 *This class purpose is to handle routes which are defined in a txt document
 * The class can draw a route taking accounting for zoomlevels via the
 * pixelhandler class.
 */
package presentation.mappanel;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Soren V Jorgensen
 * This class wil take the routes from a DB in this case a simple .txt as there
 * was no time for anything else, and draw a route if the map is zoomed in
 * (to prevent cluttering the mainmap).
 */
public class RouteHandler {

    private Scanner s = null;


    //temporary array to load in the coordinates from a DB.
    private ArrayList<String> coordinates = new ArrayList<String>();

    //Arrays to hole longditude and latitude values as doubles.
    private double[] longditude;
    private double[] latitude;

    //integer array use to draw the route
    private int[] intLong;
    private int[] intLat;
    //this one also for drawing the route ad long and latitudes needs to be 
    //converted into pixels.
    private PixelHandler pxhandler = new PixelHandler();

    public RouteHandler(String dest1, String dest2) {
	getRoute(dest1, dest2);
	distribute();
    }

    /*
     * This method distribute the long and lat values of the coordinates array
     * into two double arrays one for longditudes and one for latitudes.
     */
    private void distribute() {

	int j = 0;
	int k = 0;

	longditude = new double[coordinates.size() / 2];
	latitude = new double[coordinates.size() / 2];

	for (String i : coordinates) {
	    if (j == 0) {
		longditude[k] = Double.parseDouble(i);
		
		j++;
	    } else if (j == 1) {
		latitude[k] = Double.parseDouble(i);
		
		k++;
		j = 0;
	    }
	}

    }

    /*
     * This will draw the route if it's zoomed in, not optimized in the least
     * but it works okay.
     */
    public void drawRoute(Graphics g,
	    double longmin, double longmax, double latmin, double latmax,
	    int width, int height, boolean zoomed) {
	//----------------------------------------------------------------------
	//Converting the latitude and longditude values to pixelvalues and
	//integer, taking in acount the zoomlevel.
	//----------------------------------------------------------------------
	double sx = pxhandler.scalingX(longmin, longmax, width);
	double sy = pxhandler.scalingY(latmin, latmax, height);

	int i = 0;

	intLong = new int[longditude.length];
	intLat = new int[latitude.length];

	for (double n : longditude) {
	    intLong[i] = pxhandler.zoomLongToPixels(n, sx, longmin, longmax);
	    i++;
	    //System.out.println(n);
	}

	i = 0;

	for (double n : latitude) {
	    intLat[i] = pxhandler.zoomLatToPixels(n, sy, latmin, latmax);
	    i++;
	    //System.out.println(n);
	}
	//----------------------------------------------------------------------

	int a = 1;

	/*
	 * Draw a line between gps coordinates.
	 */
	if(zoomed == true){
	while (latitude.length > a) {
	    g.setColor(Color.white);
	    g.drawLine(intLong[a - 1], intLat[a - 1],
		    intLong[a], intLat[a]);
	    a++;
	}}



    }

    /*
     * This one get the route from a txt file with the correct syntax.
     */

    private void getRoute(String dest1, String dest2) {
	try {
	    s = new Scanner(new BufferedReader(new FileReader("src/" + dest1
		    + "-" + dest2 + ".txt")));

	    s.useDelimiter(",\\s");
	    while (s.hasNext()) {
		coordinates.add(s.next());
	    }

	} catch (FileNotFoundException ex) {
	    try {
		s = new Scanner(new BufferedReader(new FileReader("src/"
			+ dest2 + "-" + dest1 + ".txt")));

		s.useDelimiter(",\\s");
		while (s.hasNext()) {
		    coordinates.add(s.next());
		}

	    } catch (FileNotFoundException ex2) {
		System.out.println("src/" + dest2 + dest1 + ".txt not found");
	    }
	}


    }
}
