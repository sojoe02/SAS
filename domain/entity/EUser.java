package domain.entity;

/**
 *
 * @author skytthe
 */
public abstract class EUser {

	private String UserID;
	private String Name;

	public EUser(String UserID, String Name) {
		this.UserID = UserID;
		this.Name = Name;
	}

	abstract void makeOrder();
	abstract void getOrder();
}
