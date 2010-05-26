/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentation.mappanel;

import java.awt.Color;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import javax.swing.JComponent;


/**
 *
 * @author Mats Larsen, Stefan Skytthe, Dan Vi, Søren Jørgensen
 * This one will take handle the showMap panel, and add new ships as
 * gpsCoordinates are comming ind. This like all other entities in the
 * presentation layers is a singleton.
 */
public class MapPanelEntity {

    ShowMap showmap;

    /*
     * These two static and inherently syncronized hashtablels will be dynamicly
     * written to from either the control layer (not yet implementet) or the
     * simulator.
     */
    public static Map<Integer, Double> LONGDITUDE =
	    new Hashtable<Integer, Double>(50);
    public static Map<Integer, Double> LATITUDE =
	    new Hashtable<Integer, Double>(50);

    private static MapPanelEntity mappanel = new MapPanelEntity();

   private MapPanelEntity(){

	showmap = new ShowMap();
    }

   public static MapPanelEntity getInstance(){

       return mappanel;
   }

    public JComponent showMap(){

	return showmap;
    }

    public void addShip(int shipID){
	Random r = new Random();
	Color c = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
	showmap.addShip(shipID, c);
	
    }

}
