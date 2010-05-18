/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.mappanel;

/**
 *
 * @author Soren V Jorgensen
 */
public class PixelHandler {

    public int zoomLatToPixels(double latitude, double scaley, double latmin, double latmax) {

	return (int) -((latitude-latmax) * scaley);
    }

    public double scalingY(double latmin, double latmax, int pheight) {

	return (double) pheight / (latmax - latmin);

    }

    public int zoomLongToPixels(double longditude, double scalex,double longmin,double longmax) {

	return (int) ((longditude-longmin) * scalex);
    }

    public double scalingX(double longmin, double longmax, int pwidth) {
	
	return (double) pwidth / (longmax - longmin);
    }

    //For zoom there is need of methods to turn pixelvalues into long-/latitude.

    public int pixelToLat(int pHeight, int y){

	return (int) ((1 - ((double) y / (double) pHeight)) * 180 - 90);

    }

    public int pixelToLong(int pWidth,int x){

	return (int)(((double)x/(double)pWidth)*360-180);
    }
}
