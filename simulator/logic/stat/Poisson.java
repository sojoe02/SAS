/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.logic.stat;


import java.math.BigDecimal;
import java.math.BigInteger;
/**
 *
 * @author japper
 * This class will deal with the poisson process using the lambda class and
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

	//Using bigdecimals because loss of precision is very likely when
	//doing factorial, even 64bit long might not be enough if a guess might
	//be 100 ships pr timeinterval which is 100!.
        BigDecimal N = BigDecimal.ONE;

	//doing the factorial!
        for (int i=1; i<=k; i++) {
            N = N.multiply(BigDecimal.valueOf(i));
        }	
	BigDecimal Lambda;
	Lambda = BigDecimal.valueOf(L);

	BigDecimal T;
	T = BigDecimal.valueOf(t);

	//calculating lambda^k/k!.
	BigDecimal temp1 = T.multiply(Lambda);

	
	BigDecimal temp = temp1.pow(k).divide(N, 1,BigDecimal.ROUND_DOWN);

	//And no precision is lost!
	double P = temp.doubleValue()
		* Math.exp(-L*t) ;
	

	return P;

    }
}
