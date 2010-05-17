package domain.entity;

public class EShip {

	private String ShipID;
	private String Name;
	private String Captain;
	private ECargo cargo;
	private EScheduling scheduling;

	public EShip(String ShipID, String Name, String Captain, int maxContainers) {
		this.ShipID = ShipID;	//TODO tilføj auto
		this.Name = Name;
		this.Captain = Captain;

		cargo = new ECargo(maxContainers, ShipID);
		scheduling = new EScheduling();
	}

	

}
