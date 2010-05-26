package domain.entity;

import java.util.*;

public class ESAS {


	private Map<Integer, EUser> users = new HashMap<Integer, EUser>();
	private Map<Integer, EShip> ships = new HashMap<Integer, EShip>();
	private Map<String, EHarbour> harbours = new HashMap<String, EHarbour>();

	public ESAS() {
	}

	// Konstruktorer for skib, havn og bruger
	public void createShip(int ShipID, String Name, String Captain, int maxContainers, ArrayList<String[]> containers, ArrayList<String[]> scheduling) {
	    EShip ship = new EShip(ShipID, Name, Captain, maxContainers, containers, scheduling, harbours);
	    ships.put(ShipID, ship);
	}

	public EShip getShip(int shipID) {
	    return ships.get(shipID);
	}
	public void createUser(int userID, String name, String company, String adress, String userType, ArrayList orders) {
	    if(userType.equals("admin")) {
		EUser user = new EShippingCompany(userID, name);
		users.put(userID, user);
	    }
	    if(userType.equals("customer")) {
		EUser user = new ECustomer(userID, name, company, adress, orders, ships);
		users.put(userID, user);
	    }
	}

	public void createHarbour(String Name, String coordinate, String nationality) {
	    EHarbour harbour = new EHarbour(Name, coordinate, nationality);
	    harbours.put(Name, harbour);
	}

	public EHarbour getHarbour(String name) {
	    return harbours.get(name);
	}

    public void placeOrder(int userID, int orderID, int startScheduling, int endScheduling, int shipID, String content) {
	ECustomer user = (ECustomer) users.get(userID);
	user.placeOrder(orderID,startScheduling,endScheduling,shipID,content,ships);
    }
}
