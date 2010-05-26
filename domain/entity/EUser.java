package domain.entity;

/**
 *
 * @author skytthe
 */
public abstract class EUser {

	private int UserID;
	private String Name;

	public EUser(int UserID, String Name) {
		this.UserID = UserID;
		this.Name = Name;
	}
}
