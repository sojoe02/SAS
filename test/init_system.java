
package test;

import domain.entity.ESAS;
import domain.mediator.MBroker;


public class init_system {

    public static void main(String[] args) throws Exception {
	System.out.println("-----------------------");
	System.out.println("TEST:  system opstart");
	System.out.println("-----------------------");

	ESAS sas = new ESAS();
	MBroker broker = new MBroker(sas);

	System.out.println(sas.getShip(1).getScheduling(1));
	System.out.println(sas.getShip(1).getScheduling(2).getSchedulingID());
	System.out.println(sas.getShip(3).getScheduling(47).getSchedulingID());
    }

}
 