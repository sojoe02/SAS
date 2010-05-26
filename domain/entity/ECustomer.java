package domain.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author skytthe
 */
public class ECustomer extends EUser {

	private String Company;
	private String Adress;

	private Map<String, EOrder> waitingOrders = new HashMap<String, EOrder>();
	private Map<String, EOrder> activeOrders = new HashMap<String, EOrder>();
	private Map<String, EOrder> completeOrders = new HashMap<String, EOrder>();

	public ECustomer(int UserID, String Name, String Company, String Adress, ArrayList orders, Map<Integer, EShip> ships) {
	    super(UserID, Name);
	    this.Company = Company;
	    this.Adress = Adress;

	    //TODO slet
	    System.out.println("\t" + UserID + " - " + Name + " - " + Company + " - " + Adress);

	    //oprettelse af ordre
	    for(int i = 0; i < orders.size(); i++) {
		Object[] orderInstans = (Object[]) orders.get(i);

		EOrder order = new EOrder(Integer.parseInt((String) orderInstans[0]),
			ships.get(Integer.parseInt((String) orderInstans[6])).getScheduling(Integer.parseInt((String) orderInstans[2])),
			ships.get(Integer.parseInt((String) orderInstans[6])).getScheduling(Integer.parseInt((String) orderInstans[3])),
			ships.get(Integer.parseInt((String) orderInstans[6])),
			(String) orderInstans[4],(ArrayList<Integer>)orderInstans[7]
			);	
		
	//	EOrder order = null;
//TODO slet System.out.println("");
		if(orderInstans[5].equals("waiting")) {
		    System.out.println("wait");
		    waitingOrders.put((String) orderInstans[0], order);
		}
		if(orderInstans[5].equals("Active")) {
		    System.out.println("active");
		    activeOrders.put((String) orderInstans[0], order);
		}
		if(orderInstans[5].equals("Complete")) {
		    System.out.println("complete");
		    completeOrders.put((String) orderInstans[0], order);
		}
	    }
	}


	public void placeOrder(int orderID, int startScheduling, int endScheduling, int shipID, String content, Map<Integer, EShip> ships) {
	    EOrder order = new EOrder(orderID,
		    ships.get(shipID).getScheduling(startScheduling),
		    ships.get(shipID).getScheduling(endScheduling),
		    ships.get(shipID),
		    content,
		    null);
	    waitingOrders.put(Integer.toString(orderID), order);
	    System.out.println("TEST:  " + order);
	}



}
