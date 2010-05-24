/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package simulator.events.stat;

import simulator.logic.LogicEntity;

/**
 *
 * @author grp2.
 */
public class CalcProbabilityEvent {

    public double probWithPoisson(int t, int L, int k){

	return LogicEntity.getInstance().poissonProb(t, L, k);
	
    }

}
