package presentation;


import control.CActioner;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.SwingUtilities;


/**
 *
 * @author skytthe
 */
public class Pmain {

	public static void main(String[] args) throws Exception {
	    // create user
	    final PUser user = new PUser(1);
	    final CActioner control = new CActioner();



	SwingUtilities.invokeLater(new Runnable() {

	    public void run() {
		new GuiLauncher(control, user);

	    }
	});

/*

	    System.out.println("UserID: " + testUser.getUserID());
	    System.out.println("");
	    

		ArrayList<String[]> test = new ArrayList<String[]>();
		Date d1 = new Date(2010, 5, 8);
		test = control.findShipDates("Amsterdam", "Porto", d1 ,2);
		
		System.out.print("size of al after additions " + test.size());
		   System.out.println("\n");
		System.out.print("contents of al: " +  test );
		   System.out.println("");
		   
	    for (int i = 0; i < test.size(); i++) {
		System.out.println("Order: " + i);
		String[] ee = test.get(i);
		for (int j = 0; j < 5; j++) {
		    System.out.println(ee[j]);
		    
		}
	    }


		control.placeOrder(testUser.getUserID(), 1, 1, 6,10,"mats");
		control.placeOrder(testUser.getUserID(), 1, 5, 12,10,"danner");
//


	    /*		System.out.println("Opstart:");
	    ECargo test = new ECargo(100, "1");
	    //	OPRETTELSE AF SAS
	    ESAS sas = new ESAS();
	    //	OPRETTELSE AF OBJEKTER
	    EHarbour hamburg = new EHarbour("Hamburg","1,2,1,2");
	    EHarbour kina = new EHarbour("Kina havn","1,21,10,2");
	    EHarbour newYork = new EHarbour("new york","11,2,1,20");
	     */



	}
}
