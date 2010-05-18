/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.mappanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Soren V Jorgensen
 */
public class Ship {

    //Pixel values for shipposition.
    private int H;
    private int W;
    //Ship drawing pixelsize.
    private int SIZE;
    //coodinate precision.
    private int cP = 6;
    private Font font;
    //Gps index
    private int gpsNr;
    private Color c;
    private boolean show;

    public Ship(int colorcode, int gpsNr) {
	//setting initial pixelvalues for the ship position	
	this.gpsNr = gpsNr;
	H = 50;
	W = 50;
	//Define the font.
	font = new Font("SansSerif", Font.BOLD, 10);
	
	SIZE = 8;

	Random r = new Random();
	//set random shipcolour.
	c = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }

    /*private void updatePosition(double lon, double lat) {
    longditude = lon;
    latitude = lat;
    }*/
    public void draw(Graphics g) {
	//shift color for rendering of the ship!
	g.setColor(c);
	//Draw the ship:
	g.fillOval(W, H, SIZE, SIZE);
    }
    /*--------------------------------------------------------------
     * Show ships coodinates with 5 dec precision takes a bit of tinkering
     * to avoid exceptions with varied decimals, also shift text so it's
     * allways readable (doesnt disapear off edges.)
     *--------------------------------------------------------------
     */

    public void drawCoor(Graphics g ,int pWidth, int pHeight) {
	//Set the subsequent colour:
	if (show == true) {
	    g.setColor(Color.black);
	    g.setFont(font);
	    //first the shift testing and pixel compensation.
	    int Ws = W, Hs = H;
	    if (W > pWidth / 2) {
		Ws = W - 45;
	    }
	    if (H < pHeight / 2) {
		Hs = H + 15;
	    }

	    //second decimal control as to not spam the screen with text.
	    String sLon = Double.toString(MAPCONTROL.longditude.get(gpsNr));
	    String sLat = Double.toString(MAPCONTROL.latitude.get(gpsNr));

	    if (sLon.length() > cP && sLat.length() > cP) {

		g.drawString(sLon.substring(0, cP) + ","
			+ sLat.substring(0, cP), Ws, Hs);

	    } else if (sLon.length() > cP) {

		g.drawString(sLon.substring(0, cP) + "," + sLat, Ws, Hs);

	    } else if (sLat.length() > cP) {

		g.drawString(sLon + "," + sLat.substring(0, cP), Ws, Hs);

	    } else {
		g.drawString(sLon + "," + sLat, Ws, Hs);
	    }
	}
	//----------------------------------------------------------------
    }

    public void move(int H, int W) {
	//update position of the ship
	this.H = H;
	this.W = W;
    }

    public void changeShow(boolean show){
	this.show = show;
    }

    public boolean getShow(){
	return show;
    }


    /*-------------------------------------------------------------------------
     * Testing if the mouse is near the ship and then draw the coordinates
     * on the map for the world to see.
    ------------------------------------------------------------------------*/
    public boolean nearShip(int x, int y) {
	// is (x,y) near the Ship?
	if ((Math.abs(W + SIZE - x) <= SIZE)
		&& (Math.abs(H + SIZE - y) <= SIZE)) {
	    return true;
	}
	return false;
    }
}
