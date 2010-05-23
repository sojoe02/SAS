/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.mappanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author Soren V Jorgensen
 * This class can read harbours from a .txt DB, again there havent been time
 * to implement a 'real' DB. And draw their names on the harbours position on
 * the map.
 */
public class Harbours {

    //font for the harbournames.
    private Font font;

    Scanner s = null;

    //temp array for storing coordinates and harbour names
    private ArrayList<String> line = new ArrayList<String>();

    private String[] name;

    //arrays for the longditude and latitude values
    private double[] longditude;
    private double[] latitude;

    //needs to be use for drawing the map.
    PixelHandler pxhandler = new PixelHandler();    

    public Harbours() {
	//read the harbours from the .txt file from the constructor.
	try {
	    s = new Scanner(new BufferedReader(new FileReader("src/harbours.txt")));

	    while (s.hasNext()) {
		//set delimiter to ,<space>
		s.useDelimiter(",\\s");
		line.add(s.next());
	    }

	} catch (FileNotFoundException ex) {
	    System.out.println("src/harbours.txt not found");
	}

	distribute(line);

	font = new Font("SansSerif", Font.BOLD, 12);
    }

    /*
     * distribute will take the temp array line and distribute it's values into
     * three array, one for the harbour name and two double arrays
     * longditude/latitude.
     */
    private void distribute(ArrayList<String> line) {

	int j = 0;
	int k = 0;

	name = new String[line.size() / 3];
	longditude = new double[line.size() / 3];
	latitude = new double[line.size() / 3];


	for (String i : line) {
	    if (j == 0) {
		name[k] = i;
		//System.out.println(name[k]);
		j++;
	    } else if (j == 1) {
		longditude[k] = Double.parseDouble(i);
		//System.out.println(longditude[k]);
		j++;
	    } else if (j == 2) {
		latitude[k] = Double.parseDouble(i);
		//System.out.println(latitude[k]);
		k++;
		j = 0;
	    }

	}

    }

    /*
     * Drawing the harbours, there will be some imprecision due to warping
     * of the map since the earth is round(i've been told) and the map is flat.
     */
    public void drawHarbours(Graphics2D g, double longmin, double longmax,
	    double latmin, double latmax, int width, int height) {

	g.setColor(Color.white);
	g.setFont(font);

	double sx = pxhandler.scalingX(longmin, longmax, width);
	double sy = pxhandler.scalingY(latmin, latmax, height);

	int i = 0;

	for (String n : name) {
	    int px = pxhandler.zoomLongToPixels(longditude[i], sx, longmin, longmax);
	    int py = pxhandler.zoomLatToPixels(latitude[i], sy, latmin, latmax);
	    //g.setColor(Color.black);
	    g.drawString(n, px, py);
	   // g.setColor(Color.blue);

	   /* try {
		img = ImageIO.read(new File("Images/havn.jpg"));
	    } catch (IOException ex) {
		System.out.println("Images/havn.png not found");
	    }

	    g.drawImage(img, px, py, 20, 20, null);*/
	    //g.fillRect(px, py, 5, 5);
	    i++;
	}
    }
}
