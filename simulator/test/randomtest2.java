/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.test;

/**
 *
 * @author Zagadka
 */
public class randomtest2 {

    public static double getVar(double meanvar) {
	double u = Math.random();
	return -(meanvar * Math.log(u));
    }

    public static void main(String[] args) {
	int i;

	for (i = 0; i < 1000; i++) {
	    double r = getVar(1);
	    System.out.println(r);
	}
    }
}
