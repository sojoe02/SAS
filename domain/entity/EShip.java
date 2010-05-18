package domain.entity;

import java.util.LinkedList;

public class EShip {

	private String ShipID;
	private String Name;
	private String Captain;
	private ECargo cargo;
	private LinkedList schedulings = new LinkedList();

	public EShip(String ShipID, String Name, String Captain, int maxContainers) {
		this.ShipID = ShipID;	//TODO tilføj auto
		this.Name = Name;
		this.Captain = Captain;

		cargo = new ECargo(maxContainers, ShipID);
	}

	

}
