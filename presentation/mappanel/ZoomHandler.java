/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.mappanel;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Mats Larsen, Stefan Skytthe, Dan Vi, Søren Jørgensen
 * 
 * this class will load the appropriate map whenever a zoom command
 * is issued from whereever.
 */
public class ZoomHandler {

    /*
     * Hardcoding the arrays for zooming, looking at specific areas of the map
     * this could be expanded to be more dynamic later.
     */
    private int[] longvalues = {-140, -100, -60, -20, 20, 60, 100, 140, 180};
    private int[] longminvalues = {-180, -140, -100, -60, -20, 20, 60, 100, 140};
    private int[] latvalues = {-60, -40, -20, 0, 20, 40, 60, 80};
    private int[] latminvalues = {-80, -60, -40, -20, 0, 20, 40, 60};

    //array for setting xy max/min values for the mappanel. this wil handle
    //both longditude and latitude values.
    private int[] longLat = new int[4];

    /*
     * The next two methods will look through values of x and y respectively,
     * that could be input through  mouse button press via the mouselistener
     * interface in class showmap.
     *
     */
    private String lookingThroughX(int x) {

	int j = 0;

	//looking through the longvalues
	for (int i : longvalues) {

	    if (x <= i && x >= longminvalues[j]) {
		longLat[0] = longminvalues[j];
		longLat[1] = i;

		return Integer.toString(i);

	    }
	    j++;

	}

	return null;
    }

    private String lookingThroughY(int y) {

	int j = 0;

	for (int i : latvalues) {

	    if (y <= i && y >= latminvalues[j]) {
		longLat[2] = latminvalues[j];
		longLat[3] = i;

		return Integer.toString(i);
	    }
	    j++;

	}
	return null;
    }

    public BufferedImage loadMap(int x, int y) {

	String picturename;

	picturename = lookingThroughX(x);
	picturename += "_";
	picturename += lookingThroughY(y);

	BufferedImage img = null;

	try {
	    img = ImageIO.read(new File("Images/zoom/" + picturename + ".jpg"));
	} catch (IOException e) {
	    System.out.println("Images/zoom/" + picturename + " not found");
	    return null;
	}
	return img;
    }

    public int[] getLongLat() {
	return longLat;
    }
}


