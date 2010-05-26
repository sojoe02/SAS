package presentation;

import java.util.ArrayList;


public class PUser {

    private int userID;
    private boolean adminAccess;
    private boolean simulatorAccess;

    public PUser() {
    }

    public PUser(ArrayList<Integer> userAccess) {
	userID = userAccess.get(0);
    }
    
    public PUser(int UserID, boolean[] accesTable) {
	this.userID = UserID;
    }

    public int getUserID() {
	return userID;
    }
}
