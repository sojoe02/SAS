/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.logic.stat;

import java.util.ArrayList;

/**
 *
 * @author japper
 * This class will deal with poisson distrubtion using the lambda class and
 * the RandomDistro class.
 */
public class Poisson {
    private int t;		 //cumulative Ti
    private RandomDistro random;
    private int L ;


    public Poisson(int t, int L) {

	this.L = L;
	this.t = t;

	random = new RandomDistro();	

    }

    //This method will use the expontial numbergenerator and lambda
    //to generate a Ti value.
    public double generateTi() {
	//the mean should be the reciprocal value of lambda
	return  random.getExpRandom(L);

    }

    //this method will generate and array of Ti values until time t
    public ArrayList<Double> getTiArray(){

	ArrayList<Double> Ti = new ArrayList<Double>();

	double i = 0;

	while(i < t){
	    i = generateTi();
	    Ti.add(i);
	}

	return Ti;
    }        
}
