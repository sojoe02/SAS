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

	System.out.println("\t" + this + " - " + schedulingID + " - " + harbour + " - " + eventType);
    }


    public int getSchedulingID() {
	return schedulingID;
    }

}
