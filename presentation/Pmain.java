package presentation;


import control.CActioner;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class Pmain {

    public static void main(String[] args) throws Exception {
	// create control objekt
	final CActioner control = new CActioner();
	final PUser user = new PUser();
	boolean userIDAccept;
	//login dialog til indtast af userID, som bruges når der oprettes et PUser objekt og
	// bruges til at hente accessControl fra databasen
	do {
	    String dialogInput = JOptionPane.showInputDialog("Indtast userID");

	    try {
		int userID = Integer.parseInt(dialogInput);
		ArrayList<Integer> userAccess = control.login(userID);
		if (userAccess.size() == 3) {
		    user.setAll(userAccess);
		    userIDAccept = false;
		} else {
		    JOptionPane.showMessageDialog(null, "userID eksisterer ikke");
		    userIDAccept = true;
		}
	    } catch (Exception e) {
		JOptionPane.showMessageDialog(null, "userID består kun af tal");
		userIDAccept = true;
	    }   
	} while (userIDAccept);


	    //starter GUI
	    SwingUtilities.invokeLater(new Runnable() {

		public void run() {
			new GuiLauncher(control, user);

		}
	    });
    }
}
