/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.data;
/*
 *This class purpose is to handle routes which are defined in a txt document
 * The class can draw a route taking accounting for zoomlevels via the
 * pixelhandler class.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Soren V Jorgensen
 */
public class getRoute {

    private Scanner s = null;
    private ArrayList<String> coordinates = new ArrayList<String>();
    private double[] longditude;
    private double[] latitude;
    private boolean inverse;
    

    public getRoute(String dest1, String dest2) {
	getRoute(dest1, dest2);
	distribute();
    }

    public double[] getLongditude() {
	if (inverse == false) {
	return reverse(longditude);
	}
	return longditude;
    }

    public double[] getLatitude() {
	if (inverse == false) {
	return reverse(latitude);
	}
	return latitude;
    }

    private double[] reverse(double[] b) {
	int left = 0;          // index of leftmost element
	int right = b.length - 1; // index of rightmost element

	while (left < right) {
	    // exchange the left and right elements
	    double temp = b[left];
	    b[left] = b[right];
	    b[right] = temp;

	    // move the bounds toward the center
	    left++;
	    right--;
	}
	return b;
    }//endmethod reverse

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
		//System.out.println(longditude[k]);
		j++;
	    } else if (j == 1) {
		latitude[k] = Double.parseDouble(i);
		//System.out.println(latitude[k]);
		k++;
		j = 0;
	    }
	}

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
		inverse = false;
		coordinates.add(s.next());
		
	    }

	} catch (FileNotFoundException ex) {
	    try {
		s = new Scanner(new BufferedReader(new FileReader("src/"
			+ dest2 + "-" + dest1 + ".txt")));

		s.useDelimiter(",\\s");
		while (s.hasNext()) {
		     inverse = true;
		    coordinates.add(s.next());
		   

		}

	    } catch (FileNotFoundException ex2) {
		System.out.println("src/" + dest2 + "-" + dest1 + ".txt not found");
	    }
	}


    }
}
