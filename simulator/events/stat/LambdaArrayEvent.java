/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package simulator.events.stat;

import java.util.ArrayList;
import java.util.Iterator;
import simulator.logic.LogicEntity;

/**
 *
 * @author Zagadka
 */
public class LambdaArrayEvent {

    Integer[][] lambdaValues;
    ArrayList<Integer> temparray = new ArrayList<Integer>();
    
    int harbours;
    int maxvalueForLambda;

    public LambdaArrayEvent(int harbours, int maxvalueForLambda){

	this.harbours = harbours;
	this.maxvalueForLambda = maxvalueForLambda;
	
    }

      public Integer[][] lambdaArray() {

	lambdaValues = new Integer[harbours][harbours];

	for (int i = 0; i < harbours; i++) {

	    for (int l = 0; l < harbours; l++) {

		if (l != i) {
		    temparray.add(LogicEntity.getInstance()
			    .getRandLambda(maxvalueForLambda));
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

}
