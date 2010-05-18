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
 * @author Soren V. Jorgensen.
 * this class will load the appropriate map whenever a zoom command
 * is issued from whereever.
 */
public class ZoomHandler {

    int[] longvalues = {-140, -100, -60, -20, 20, 60, 100, 140, 180};
    int[] longminvalues = {-180, -140, -100, -60, -20, 20, 60, 100, 140};
    int[] latvalues = {-60, -40, -20, 0, 20, 40, 60, 80};
    int[] latminvalues = {-80, -60, -40, -20, 0, 20, 40, 60};
    //array for setting xy max/min values for the mappanel.
    private int[] longLat = new int[4];

    private String lookingThroughX(int x) {

	int j = 0;

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


