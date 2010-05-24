/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.logic.stat;

import java.math.BigDecimal;
import java.math.BigInteger;
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

    //next algorithm that will calculate the odds of a ship passing within a given
    //timeperiod using time t(whole hours), and lambda, and k number of ships.

    public double poisson(int k){

	//using bigintegers because looss of precision is very likely when
	//doing factorial, even 64bit long might not be enough if a guess might
	//be 300 ships pr timeinterval.
        BigInteger N = BigInteger.ONE;

	//doing the factorial!
        for (int i=1; i<=k; i++) {
            N = N.multiply(BigInteger.valueOf(i));
        }

	BigInteger Lambda;
	Lambda = BigInteger.valueOf(L);

	//calculating lambda^k/k!, making two bigintegers instead of a float.
	BigInteger[] temp = Lambda.pow(k).divideAndRemainder(N);

	//And no precision is lost!
	double P = Double.parseDouble(temp[0].toString() + "." + temp[1].toString())
		*Math.exp(-L);
	return P;

    }
}
