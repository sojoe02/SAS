package presentation;


public class PUser {

    private int userID;
    private boolean[] accesTable;

    public PUser(int userID) {
	this.userID = userID;
    }

    public PUser(int UserID, boolean[] accesTable) {
	this.userID = UserID;
	this.accesTable = accesTable;
    }

    public int getUserID() {
	return userID;
    }

    public boolean[] getAccesTable() {
	return accesTable;
    }

   public boolean getAccesPoint(int pos) {
	return accesTable[pos];
    }

}
