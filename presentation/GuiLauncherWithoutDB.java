/*
 * Class made to launch the SAS client, holds the main method and SAS frame
 * 
 */
package presentation;

/**
 *
 * @author Dan Vi, Stefan Skytthe, Soren V Jorgensen og Mats Larsen.
 */
import java.awt.*;
import javax.swing.*;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import presentation.mappanel.MapPanelEntity;
import simulator.SimulatorPanel;
import simulator.events.sailing.ReachHarbourEvent;

// This example demonstrates the use of JButton, JTextField and JLabel.
public class GuiLauncherWithoutDB extends JFrame {

    JFrame SASframe;
    JTabbedPane tabbed = new JTabbedPane();

    // Constructor
    public GuiLauncherWithoutDB() {

	ImageIcon mapicon = iconHandler("Images/mapicon.png", 45, 45);
	ImageIcon shipicon = iconHandler("Images/shipicon.png", 45, 45);
	ImageIcon sasicon = iconHandler("Images/sasicon.png", 45, 45);

	JPanel testpanel = new JPanel();
	JPanel testpanel1 = new JPanel();

	SASframe = new JFrame("SAS, smugling af slaver (og yumyum)");
	SASframe.setIconImage(Toolkit.getDefaultToolkit().getImage("Images/frameicon.png"));

	SASframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	SASframe.add(tabbed, BorderLayout.CENTER);

	/*-------------------------------------------------------------
	Adding the placeOrder Part to the frame.
	 */
/*	JPanel mainPanel = new JPanel();
	mainPanel.setBackground(Color.white);
	mainPanel.setPreferredSize(new Dimension(900, 500));
	mainPanel.setLayout(new GridLayout(1, 3, 30, 100));

	specificationPanel specPanel = new specificationPanel();

	mainPanel.add(specPanel);
	mainPanel.add(new placeOrderPanel(specPanel));
*/
	//tabbed.addTab("Lav Ordre", sasicon, mainPanel, "SAS");
	//--------------------------------------------------------------

	
	
	
	
	//add the observer for harbourevents.
	SimulatorPanel simpanel = new SimulatorPanel();
	ReachHarbourEvent.getInstance().addObserver(simpanel);


	tabbed.addTab("Skibsinfo", shipicon, simpanel, "Vis Skipsinfo");
	//add the map to a tab.
	tabbed.addTab("Kort", mapicon, MapPanelEntity.getInstance().showMap(), "Kort til visning af skibspositioner");

	SASframe.update(null);
	SASframe.pack();
	SASframe.setVisible(true);
    }

    //Method for turning images into 64px icons
    private ImageIcon iconHandler(String path, int width, int height) {
	Image img;
	try {
	    img = ImageIO.read(new File(path));
	} catch (IOException e) {
	    e.printStackTrace();
	    System.out.println("Image at " + path + " not found");
	    return null;
	}
	//Resizing the image and returning it as an icon
	return new ImageIcon(img.getScaledInstance(width, height, 4));
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
	// set the look and feel



	/*try {
	    UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
	} catch (ClassNotFoundException classNotFoundException) {
	} catch (InstantiationException instantiationException) {
	} catch (IllegalAccessException illegalAccessException) {
	} catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {*/
	    try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (Exception e) {
	    }
	
	//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
	/*for (int i = 0; i < 80; i++) {
	new Thread(new GpsSim2(i)).start();
	}*/
	SwingUtilities.invokeLater(new Runnable() {

	    public void run() {
		new GuiLauncherWithoutDB();

	    }
	});
    }
}
