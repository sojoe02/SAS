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
 * This class draw the ship in accordance it's given coordinates.
 * Also it will be able to register mouse button press near it, setting the
 * boolean show flag that will determine wether the coordinates should be drawn
 * or not.
 */
public class Ship {

    //Pixel values for shipposition H=height, W=width.
    private int H;
    private int W;
    //Ship drawing pixelsize.
    private int SIZE;
    //coodinate precision.
    private int cP = 6;
    private Font font;
    private Color c;
    private boolean show;
    private int shipID;

    //route strings
    

    public Ship(int shipID, Color c) {
	//setting initial pixelvalues for the ship position

	this.shipID = shipID;
	
	H = 50;
	W = 50;
	//Define the font.
	font = new Font("SansSerif", Font.BOLD, 12);
	
	SIZE = 8;

	Random r = new Random();
	//set random shipcolour.
	this.c = c;
    }

    
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
	    g.setColor(Color.white);
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
	    String sLon = Double.toString(MapPanelEntity.LATITUDE.get(shipID));
	    String sLat = Double.toString(MapPanelEntity.LONGDITUDE.get(shipID));

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

    
    public void drawRoute(){
	if(show == true){
	    
	}
	
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

    public int getShipID(){
	return shipID;
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
