package presentation;


import control.CActioner;

import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author skytthe
 */
public class Pmain {

	public static void main(String[] args) throws Exception {
	    // create user
	    PUser testUser = new PUser(1);
	    System.out.println("UserID: " + testUser.getUserID());
	    System.out.println("");
	    
	    CActioner control = new CActioner();
		ArrayList<String> test = new ArrayList<String>();
		Date d1 = new Date(2010, 5, 8);
		Date d2 = new Date(2010, 5, 22);
		test = control.findShipDates("Amsterdam", "Porto", d1 ,2);
		
		System.out.print("size of al after additions " + test.size());
		   System.out.println("\n");
		System.out.print("contents of al: " +  test );



		control.placeOrder(testUser.getUserID(), 1, 1, 6,10);
		control.placeOrder(testUser.getUserID(), 1, 5, 12,10);
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
