package domain.entity;


import java.util.ArrayList;


public class EOrder {

    private int orderID;
    private EScheduling startScheduling;
    private EScheduling endScheduling;
    private EShip ship;
    private String content;
    private ArrayList<EContainer> containers = new ArrayList<EContainer>();

    public EOrder(int orderID, EScheduling startScheduling, EScheduling endScheduling, EShip ship, String content, ArrayList<Integer> container) {
	this.orderID = orderID;
	this.startScheduling = startScheduling;
	this.endScheduling = endScheduling;
	this.ship = ship;
	this.content = content;

	if (container != null) {
	    for(int i = 0; i < container.size(); i++) {
		System.out.println(container.get(i));
		EContainer con = ship.getContainer(container.get(i));

		containers.add(con);
	    }
	}
    }
}
