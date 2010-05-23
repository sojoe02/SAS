/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import simulator.data.DataEntity;

/**
 *
 * @author Zagadka
 */


// private static LogicEntity logic = new LogicEntity();

public class SimulatorEntity {

    private static SimulatorEntity simulator = new SimulatorEntity();


    private SimulatorEntity(){

    }

    public static SimulatorEntity getInstance(){
	return simulator;
    }

    public void startShip(String to, String from, int v, int time, int shipID) {

	DataEntity.getInstance().startShip(to, from, v, time, shipID);
    }
}
