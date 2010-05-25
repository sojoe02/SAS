package domain.entity;

/**
 *
 * @author skytthe
 */
public class EShippingCompany extends EUser {

	public EShippingCompany(int UserID, String Name) {
		super(UserID, Name);

		//TODO slet
	    System.out.println("\t " + UserID + " - " + Name);
	}

	public void placeOrder() {
	    
	}
}
