/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.events.stat;

import java.util.ArrayList;
import simulator.logic.LogicEntity;

/**
 *
 * @author japper
 * This class will make traffic using a lambda array and
 */
public class MakeTrafficEvent {

    public ArrayList<Double> makeTrafficArray(int lambda, int t) {

	ArrayList<Double> Ti = new ArrayList<Double>();

	double i = 0;
	double j = 0;

	while (j < t) {
	    i = LogicEntity.getInstance().getExpRandom(lambda);
	    j += i;
	    if(j < t){
	    Ti.add(i);
	    }
	    
	}
	return Ti;
    }
}
