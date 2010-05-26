/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package simulator.test;

/**
 *
 * @author Zagadka
 */import java.lang.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class randgenviacalendar  {
  public static int seed;
  public static final int n = 100;
  public static void main(String argv[]) {

 // Initiate the seed from the current time
    GregorianCalendar t = new GregorianCalendar();
    int t1 = t.get(Calendar.SECOND);
    int t2 = t.get(Calendar.MINUTE);
    int t3 = t.get(Calendar.HOUR_OF_DAY);
    int t4 = t.get(Calendar.DAY_OF_MONTH);
    int t5 = t.get(Calendar.MONTH);
    int t6 = t.get(Calendar.YEAR);
    seed = t6 + 65*(t5+12*(t4+31*(t3+24*(t2+60*t1))));
    if ((seed%2) == 0) seed = seed-1;

 // Put the exponential random numbers in the array
    for (int i=0; i<n; ++i) {
      System.out.println("The " + i
        + "th exponential number is: " + rane());
    }
  }

// Method to generate an exponential random number from a
// uniform random number in [0,1].

  public static double rane() {
    return -Math.log(1-ranf());
  }

// Method to generate a uniform random number in [0,1]
// following x(i+1)=a*x(i) mod c with a=pow(7,5) and
// c=pow(2,31)-1. Here the seed is a global variable.

  public static double ranf() {
    final int a = 16807, c = 2147483647, q = 127773,
      r = 2836;
    final double cd = c;
    int h = seed/q;
    int l = seed%q;
    int t = a*l-r*h;
    if (t > 0) seed = t;
    else seed = c+t;
    return seed/cd;
  }
}
