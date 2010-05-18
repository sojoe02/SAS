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
 */
public class Harbours {

    private Font font;
    Scanner s = null;
    private ArrayList<String> line = new ArrayList<String>();
    private String[] name;
    private double[] longditude;
    private double[] latitude;
    PixelHandler pxhandler = new PixelHandler();
    private BufferedImage img = null;

    public Harbours() {
	try {
	    s = new Scanner(new BufferedReader(new FileReader("src/harbours.txt")));

	    while (s.hasNext()) {
		s.useDelimiter(",\\s");
		line.add(s.next());
	    }

	} catch (FileNotFoundException ex) {
	    System.out.println("src/harbours.txt not found");
	}

	distribute(line);

	font = new Font("SansSerif", Font.ITALIC, 12);
    }

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

    public void drawHarbours(Graphics2D g, double longmin, double longmax,
	    double latmin, double latmax, int width, int height) {

	g.setColor(Color.black);
	g.setFont(font);

	double sx = pxhandler.scalingX(longmin, longmax, width);
	double sy = pxhandler.scalingY(latmin, latmax, height);

	int i = 0;

	for (String n : name) {
	    int px = pxhandler.zoomLongToPixels(longditude[i], sx, longmin, longmax);
	    int py = pxhandler.zoomLatToPixels(latitude[i], sy, latmin, latmax);
	    g.setColor(Color.black);
	    g.drawString(n, px, py);
	    g.setColor(Color.blue);

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
