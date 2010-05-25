/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package simulator.events.stat;

import simulator.logic.LogicEntity;

/**
 *
 * @author Mats Larsen, Stefan Skytthe, Dan Vi, Søren Jørgensen
 */
public class CalcProbabilityEvent {

    public double probWithPoisson(int t, double L, int k){

	return LogicEntity.getInstance().poissonProb(t, L, k);
	
    }

}
