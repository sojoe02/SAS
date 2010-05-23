/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.logic.stat;


import java.util.Random;

/**
 *
 * @author Zagadka
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

    public Double[][] makeProbabilityArray(int x, int maxvalue, int harbours) {

	Lambda L = new Lambda(maxvalue);

	Integer[][] lambdaArray = L.distroLambda(harbours);

	Double[][] probArray = new Double[lambdaArray.length][lambdaArray.length];

	int k = 0;

	for (Integer[] i : lambdaArray) {

	    int l = 0;

	    for (Integer j : i) {

		System.out.println(j);

		if (j != null) {
		    probArray[k][l] = getProbability(x, j);
		} else {
		    probArray[k][l] = null;
		}
		l++;

	    }
	    k++;

	}

	return probArray;

    }

    public void testArray() {

	Double[][] probArray = makeProbabilityArray(1, 5, 8);

	for (int i = 0; i < probArray.length; i++) {
	    System.out.println("-------");
	    for (int l = 0; l < probArray.length; l++) {
		System.out.println(probArray[i][l]);
	    }
	}


    }
    
    public static void main(String[] args) {

	RandomDistro la = new RandomDistro();

	la.testArray();
	// set the look and feel

    }
}
