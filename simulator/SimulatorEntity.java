/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.util.ArrayList;
import simulator.data.DataEntity;

/**
 *
 * @author Zagadka
 */
// private static LogicEntity logic = new LogicEntity();
public class SimulatorEntity {

    private static SimulatorEntity simulator = new SimulatorEntity();
    DataEntity data;

    private SimulatorEntity() {
	data = DataEntity.getInstance();
    }

    public static SimulatorEntity getInstance() {
	return simulator;
    }

    public void startShip(String to, String from, int v, int time, int shipID) {

	data.startShip(to, from, v, time, shipID);
    }

    public Integer[][] makeLambdaArray(int maxvalue, int harbours) {

	return data.makeLambdaArray(maxvalue, harbours);
    }

    public ArrayList<Double> makeTraffic(int lambda, int t) {

	return data.makeTraffic(lambda, t);
    }

    public double getPoissonProb(int t, int L, int k) {

	return data.getPoissonProb(t, L, k);
    }
}
