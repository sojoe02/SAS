/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.entity;


import java.util.*;


public class ECargo {

	private int maxContainers;

	private Map<String, EContainer> Container = new HashMap<String, EContainer>();

	public ECargo(int maxContainers, String ShipID) {
		this.maxContainers = maxContainers;


		for (int i = 1; i < maxContainers+1; i++) {
			System.out.println(ShipID + i);
			EContainer container = new EContainer(ShipID + i);
//			container.
		}

		//TODO lav løkke der opretter container
	}


}
