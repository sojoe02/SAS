/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.logic.stat;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Mats Larsen, Stefan Skytthe, Dan Vi, Søren Jørgensen
 */
public class Lambda {

    /*   //Array to hold random values
     * example:
     * OD = Odense, VA = Valencia, NY = New York, NO = New Orleans
     * LA = Los Angeles, SD = ShangHai DeepWater, SY = Sydney , CT = CapeTown
     *
     * 	 |OD   |VA   |NY   |NO   |LA   |SD   |SY   |CT   |
     *	---------------------------------------------------
     * OD| 0   |Lva0 |     |     |     |     |     |     |
     * VA|Lod1 | 0   |     |     |     |     |     |     |     /\
     * NY|Lod2 |Lva2 | 0   |     |     |     |     |     | = _/  \_
     * NO|Lod3 | etc.| 0   |     |     |     |     |     |
     * LA|Lod4 |     |     |     | 0   |     |     |     |
     * SD| etc.|     |     |     |     | 0   |     |     |
     * SY|     |     |     |     |     |     | 0   |     |
     * CP|     |     |     |     |     |     |     | 0   |
     *	-----------------------------------------------------
     */
    Integer[][] lambdaValues;
    ArrayList<Integer> temparray = new ArrayList<Integer>();
    int maxvalue;

    public Lambda(int maxvalue){
	this.maxvalue = maxvalue;
    }

    public int getRandLambda() {
	return (int) ((int) maxvalue * Math.random());
    }

    public void generateLambda(int harbours) {

	for (int i = 0; i < harbours; i++) {

	    temparray.add(getRandLambda());

	}
    }

    public Integer[][] distroLambda(int harbours) {

	lambdaValues = new Integer[harbours][harbours];

	for (int i = 0; i < harbours; i++) {

	    for (int l = 0; l < harbours; l++) {

		if (l != i) {
		    temparray.add(getRandLambda());
		} else {
		    temparray.add(null);
		}

		Iterator it = temparray.iterator();

		while (it.hasNext()) {
		    //System.out.println(it.next());
		    lambdaValues[i][l] = (Integer) it.next();
		}
	    }
	}
	return lambdaValues;
    }

    public void testArray() {

	distroLambda(8);

	for (int i = 0; i < lambdaValues.length; i++) {
	    System.out.println("-------");
	    for (int l = 0; l < lambdaValues.length; l++) {
		System.out.println(lambdaValues[i][l]);
	    }
	}


    }

    public static void main(String[] args) {

	Lambda la = new Lambda(30);

	la.testArray();
	// set the look and feel

    }
}
