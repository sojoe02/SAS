/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.mappanel;

/**
 *
 * @author Mats Larsen, Stefan Skytthe, Dan Vi, Søren Jørgensen
 *
 * This class will handle everything in regards to pixel and longditude/latitude
 * conversion.
 */
public class PixelHandler {

    /*
     * The following method will change a latitude value to a pixelvalue.
     * This value will in showmap have to be updated for every ship at
     * every frame update
     */
    public int zoomLatToPixels(double latitude, double scaley, double latmin, double latmax) {

	return (int) -((latitude - latmax) * scaley);
    }

    /*
     * This will return a scale constant which can be used if zoom is implemented
     * on the map, this only has to be calculated once for every frame update
     */
    public double scalingY(double latmin, double latmax, int pheight) {

	return (double) pheight / (latmax - latmin);

    }

    /*
     * next two methods will be the same as the two above only
     * for X and longditude
     */
    public int zoomLongToPixels(double longditude, double scalex, double longmin, double longmax) {

	return (int) ((longditude - longmin) * scalex);
    }

    public double scalingX(double longmin, double longmax, int pwidth) {

	return (double) pwidth / (longmax - longmin);
    }

    /*
     * For the zoom to work there is need of methods to turn pixelvalues into 
     *long-/latitude.
     */
    public int pixelToLat(int pHeight, int y) {

	return (int) ((1 - ((double) y / (double) pHeight)) * 180 - 90);

    }

    public int pixelToLong(int pWidth, int x) {

	return (int) (((double) x / (double) pWidth) * 360 - 180);
    }
}
