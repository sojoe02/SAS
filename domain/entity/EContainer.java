package domain.entity;

/**
 *
 * @author skytthe
 */
public class EContainer {

	private int ContainerID;
	private String Content;

	public EContainer(int ContainerID,String content) {
	    this.ContainerID = ContainerID;
	    this.Content = content;

	    //TODO slet
	    System.out.println("\t \t" + ContainerID + " - " + content + " - " + this);
	}
	
}
