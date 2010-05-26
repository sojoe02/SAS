/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.entity;


import java.util.*;


public class ECargo {

    private int maxContainers;
    private Map<Integer, EContainer> Container = new HashMap<Integer, EContainer>();

    public ECargo(int maxContainers, ArrayList<String[]> containers) {
	this.maxContainers = maxContainers;

	for(int i = 0; i < containers.size(); i++) {
	    String[] containerInstans = containers.get(i);
	    EContainer con = new EContainer(Integer.parseInt(containerInstans[0]), containerInstans[1]);
	    Container.put(Integer.parseInt(containerInstans[0]), con);
	    System.out.println(con);
	}
    }

    public int getMaxContainers() {
	return maxContainers;
    }

    public EContainer getContainer(int containerID) {
	return Container.get(containerID);
    }
}
