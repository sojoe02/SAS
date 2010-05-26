package domain.entity;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

public class EShip {

    private int ShipID;
    private String Name;
    private String Captain;
    private ECargo cargo;
    private LinkedList<EScheduling> schedulings = new LinkedList<EScheduling>();
    private Map<Integer, EScheduling> completedSchedulings = new HashMap<Integer, EScheduling>();

    public EShip(int ShipID, String Name, String Captain, int maxContainers, ArrayList<String[]> containers, ArrayList<String[]> scheduling, Map<String, EHarbour> harbours) {
        this.ShipID = ShipID;
        this.Name = Name;
        this.Captain = Captain;

	cargo = new ECargo(maxContainers, containers);

	for(int i = 0; i < scheduling.size(); i++) {
	    String[] schedulingArrayInstans = scheduling.get(i);
	    EScheduling schedulingInstans = new EScheduling(Integer.parseInt(schedulingArrayInstans[0]),
		    schedulingArrayInstans[1],schedulingArrayInstans[2] ,
		    Integer.parseInt(schedulingArrayInstans[4]),harbours.get(schedulingArrayInstans[3]) );
	    schedulings.add(schedulingInstans);
	}
    }

    public EScheduling getScheduling(int schedulingID) {
	ListIterator<EScheduling> itr = schedulings.listIterator();
	while(itr.hasNext()) {
	    EScheduling schedulingElement = itr.next();
	    if(schedulingElement.getSchedulingID() == schedulingID) {
		return schedulingElement;
	    }
	}
	return null;
    }

    public EContainer getContainer(int containerID) {
	return cargo.getContainer(containerID);
    }

}
