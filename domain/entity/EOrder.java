package domain.entity;

/**
 *
 * @author skytthe
 */
public class EOrder {

	private int orderID;
	private EScheduling startScheduling;
	private EScheduling endScheduling;
	private EShip ship;
	private EContainer[] containers;
	
}
