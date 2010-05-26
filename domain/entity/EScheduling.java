package domain.entity;






public class EScheduling {

    private int schedulingID;
    private String eventType;
    private String eventDate;
    private int currentContainers;
    private EHarbour harbour;

    public EScheduling(int schedulingID, String eventType, String eventDate, int currentContainers, EHarbour harbour) {
	this.schedulingID = schedulingID;
	this.eventType = eventType;
	this.eventDate = eventDate;
	this.currentContainers = currentContainers;
	this.harbour = harbour;
<<<<<<< HEAD

	System.out.println("\t" + this + " - " + schedulingID + " - " + harbour + " - " + eventType);
=======
>>>>>>> 7325e4f548693ade0f91620d7d34870dd6105704
    }


    public int getSchedulingID() {
	return schedulingID;
    }

}
