/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.logic;

import simulator.logic.ship.ShipMoveLogic;
import java.util.ArrayList;
import simulator.logic.stat.*;
import simulator.Coordinates;

/**
 *
 * @author Grp2
 *
 * This class is the handler of the logic layer, events will use this to
 * access relevant methods from logic. This class is singleton.
 */
public class LogicEntity {

    private static LogicEntity logic = new LogicEntity();

    ShipMoveLogic shiplogic;
    RandomDistro randomgenExp;
    Lambda lambda;
    Poisson poisson;

    private LogicEntity() {
    }

    public static synchronized LogicEntity getInstance() {
	return logic;
    }

    public Coordinates movShip(Coordinates to, Coordinates from, int v, int time) {

	shiplogic = new ShipMoveLogic();

	return shiplogic.getNextPos(to, from, v, time);
    }

    public double getExpRandom(int lambda) {

	randomgenExp = new RandomDistro();

	return randomgenExp.getExpRandom(lambda);
    }    

    public int getRandLambda(int maxvalue){

	lambda = new Lambda(maxvalue);

	return lambda.getRandLambda();
    }

    public double getProbability(int x, int Lambda){

	RandomDistro random = new RandomDistro();

	return random.getProbability(x, Lambda);
    }



    public double poissonProb(int t,  double L, int k){

	poisson = new Poisson(t, L);
	
	return poisson.poisson(k);

    }
}
