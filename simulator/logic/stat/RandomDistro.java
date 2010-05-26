/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.logic.stat;


import java.util.Random;

/**
 *
 * @author Mats Larsen, Stefan Skytthe, Dan Vi, Søren Jørgensen
 */
public class RandomDistro {

    private Random random = new Random();

    public double getExpRandom(int lambda) {

	double mean = 1/(double)lambda;

	return (-mean * Math.log(random.nextDouble()));

    }
    /*this will calculate the probability with exponential properties
     * with memoryless properties.  
     */

    public double getProbability(int x, int Lambda) {

	return (Math.exp(-Lambda * x));

    }
  
}
