/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Network;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mats l
 */
public class SendObject implements Serializable{
   private  ArrayList<Integer> userInfo = new ArrayList<Integer>();
    ArrayList<String> ShipDates = new ArrayList<String>();
    ArrayList<String[]> availableShips;
    private int userID;
    private int shipID;
    private int startSID;
    private int endSID;
    private int containers;
    private String metodeChoose;
    private String startDest;
    private String endDest;
    private String content;
    private Date date;


    public SendObject(int userID, int shipID, int startSID, int endSID,
	    int containers, String content, String metodeChoose)	{
	this.userID = userID;
	this.shipID = shipID;
	this.startSID = startSID;
	this.endSID = endSID;
	this.containers = containers;
	this.content = content;
	this.metodeChoose = metodeChoose;

    }

  public SendObject (String metodeChoose, ArrayList<String[]> availableShips ) {
      this.availableShips = availableShips;
  }

    public SendObject(String startDest, String endDest, Date date,
	    int containers, String metodeChoose) {
	this.startDest = startDest;
	this.endDest = endDest;
	this.date = date;
	this.containers = containers;
	this.metodeChoose = metodeChoose;
    }

    public SendObject(ArrayList<Integer> userInfo ) {
	this.userInfo = userInfo;
    }

 public SendObject(int userID, String metodeChoose)	{
     this.userID = userID;
     this.metodeChoose = metodeChoose;


 }

 public ArrayList<Integer> getUserInfo()	{
    return userInfo;
 }

public ArrayList<String[]> getAvailableShips()	{
    return availableShips;
}




public String getContent()   {
    return content;
}

public int getEndSID()   {
    return endSID;
}

public int getStartSID()   {
    return startSID;
}

public int getShipID()   {
    return shipID;
}

public int getUserID()   {
    return userID;
}

public String getMetodeChoose()	{
    return metodeChoose;
}
public String getStartDest()	{
    return startDest;
}
public String getEndDest()	{
    return endDest;
}
public Date getDate()	{
    return date;
}

public int getContainers()	{
    return containers;
}

public void putShipDates (ArrayList ShipDates)   {
    this.ShipDates = ShipDates;
}

public ArrayList getShipDates()  {
    return ShipDates;
}

}

