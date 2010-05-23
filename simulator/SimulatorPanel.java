/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 *
 * @author Zagadka
 */
public class SimulatorPanel extends JPanel {

    private JButton startship;
    private SimulatorEntity sim;
    private JComboBox toList, fromList, speedList, timeList;

    /*
     * Constants and variables for use witht the shipsimulatorpanel:
     */
    private String[] Harbours = {"Odense", "NewOrleans", "CapeTown"};
    private Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 16, 18, 20};
    private String to, from;
    private int speed, time;
    private static int shipID = 110;
    //-------------------------------------------------------------------
    /*
     * Constants and variables for use with the statpanel
     */
    private JTextArea statoutput;
    private JButton makeLambdaArray;
    private int lmax = 10, harbournr = 6;
    private JScrollPane scrollPane;
    //---------------------------------------------------------------------

    public SimulatorPanel() {

	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



	//super(new GridBagLayout());
	// first the shipsim part, they are all chooseboxes
	//-------------------------------------------------------------
	toList = new JComboBox(Harbours);
	toList.addActionListener(new choosetoListener());
	toList.setSelectedIndex(1);

	fromList = new JComboBox(Harbours);
	fromList.addActionListener(new choosefromListener());
	fromList.setSelectedIndex(0);

	speedList = new JComboBox(integers);
	speedList.addActionListener(new choosespeedListener());
	speedList.setSelectedIndex(5);


	timeList = new JComboBox(integers);
	timeList.addActionListener(new choosetimeListener());
	timeList.setSelectedIndex(5);

	sim = SimulatorEntity.getInstance();

	startship = new JButton("launch the ship");
	startship.addActionListener(new startshipListener());

	JPanel shipsimPanel = new JPanel();
	shipsimPanelHandler(shipsimPanel);

	//------------------------------------------------------------
	//next the stat panel.


	statoutput = new JTextArea(40, 70);
	statoutput.setEditable(false);
	scrollPane = new JScrollPane(statoutput);



	makeLambdaArray = new JButton("Make lambda Array");
	makeLambdaArray.addActionListener(new makeLambdaArrayListener());


	JPanel statPanel = new JPanel();
	statPanelHandler(statPanel);
	statPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void statPanelHandler(JPanel statPanel) {

	statPanel.add(makeLambdaArray);

	add(statPanel);
    }

    private void shipsimPanelHandler(JPanel shipsimPanel) {

	//shipsimPanel.setLayout(new BoxLayout(shipsimPanel, BoxLayout.Y_AXIS));

	shipsimPanel.add(new JLabel("Hastighed:"));
	shipsimPanel.add(speedList);
	shipsimPanel.add(new JLabel("Tidsfactor:"));
	shipsimPanel.add(timeList);
	shipsimPanel.add(new JLabel("Til:"));
	shipsimPanel.add(toList);
	shipsimPanel.add(new JLabel("Fra:"));
	shipsimPanel.add(fromList);
	shipsimPanel.add(startship);

	add(shipsimPanel);
    }

    private class startshipListener implements ActionListener {

	public void actionPerformed(ActionEvent event) {

	    if (to == null ? from != null : !to.equals(from)) {
		sim.startShip(to, from,
			speed, time, shipID);
		shipID++;
	    }
	}
    }

    private class choosefromListener implements ActionListener {

	public void actionPerformed(ActionEvent event) {
	    JComboBox cb = (JComboBox) event.getSource();
	    from = (String) cb.getSelectedItem();

	}
    }

    private class choosetoListener implements ActionListener {

	public void actionPerformed(ActionEvent event) {
	    JComboBox cb = (JComboBox) event.getSource();
	    to = (String) cb.getSelectedItem();

	}
    }

    private class choosespeedListener implements ActionListener {

	public void actionPerformed(ActionEvent event) {
	    JComboBox cb = (JComboBox) event.getSource();
	    speed = (Integer) cb.getSelectedItem();

	}
    }

    private class choosetimeListener implements ActionListener {

	public void actionPerformed(ActionEvent event) {
	    JComboBox cb = (JComboBox) event.getSource();
	    time = (Integer) cb.getSelectedItem();

	}
    }

    private class makeLambdaArrayListener implements ActionListener {

	public void actionPerformed(ActionEvent event) {

	    Integer[][] temp = sim.makeLambdaArray(harbournr, lmax);

	    statoutput.append("Generating LambdaArray: \n");
	    statoutput.append("--------------------------------------------\n");
	    for (int i = 0; i < temp.length; i++) {
		for (int j = 0; j < temp[i].length; j++) {
		    //System.out.print(" " + temp[i][j]);
		    if (temp[i][j] != null) {
			statoutput.append("    " + temp[i][j].toString());
		    } else {
			statoutput.append("    X");
		    }
		}
		//System.out.println("");
		statoutput.append("\n");
	    }

	    statoutput.append("--------------------------------------------\n");


	}
    }
}
