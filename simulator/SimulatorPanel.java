/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private JTextArea statoutput, resultoutput;
    private JButton makeLambdaArray, makeTraffic;
    //lmax er lig antal skibe i timen!
    private int lmax = 10, harbournr = 6, t = 1;
    private JScrollPane scrollPane, scrollPane2;
    private Integer[][] lambdaarray = null;
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


	statoutput = new JTextArea(15, 70);
	statoutput.setEditable(false);
	scrollPane = new JScrollPane(statoutput);

	resultoutput = new JTextArea(15, 70);
	resultoutput.setEditable(false);
	scrollPane2 = new JScrollPane(resultoutput);



	makeLambdaArray = new JButton("Make lambda Array");
	makeLambdaArray.addActionListener(new makeLambdaArrayListener());

	makeTraffic = new JButton("Make traffic");
	makeTraffic.addActionListener(new makeTrafficListener());


	JPanel statPanel = new JPanel();
	statPanelHandler(statPanel);
	statPanel.add(scrollPane);
	statPanel.add(scrollPane2);



    }

    private void statPanelHandler(JPanel statPanel) {
	statPanel.setLayout(new BoxLayout(statPanel, BoxLayout.Y_AXIS));

	statPanel.add(makeLambdaArray);
	statPanel.add(makeTraffic);

	add(statPanel);
    }

    private void shipsimPanelHandler(JPanel shipsimPanel) {

	shipsimPanel.setLayout(new BoxLayout(shipsimPanel, BoxLayout.X_AXIS));

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

	    lambdaarray = sim.makeLambdaArray(harbournr, lmax);

	    statoutput.append("Generating LambdaArray, ships pr. hour: \n");
	    statoutput.append("--------------------------------------------\n");
	    for (int i = 0; i < lambdaarray.length; i++) {
		for (int j = 0; j < lambdaarray[i].length; j++) {
		    //System.out.print(" " + temp[i][j]);
		    if (lambdaarray[i][j] != null) {
			statoutput.append("\t" + lambdaarray[i][j].toString());
		    } else {
			statoutput.append("\tX");
		    }
		}
		//System.out.println("");
		statoutput.append("\n");
	    }

	    statoutput.append("--------------------------------------------\n");


	}
    }

    private class makeTrafficListener implements ActionListener {

	public void actionPerformed(ActionEvent event) {

	    if (lambdaarray != null) {

		ArrayList<Double> traffictemp = new ArrayList<Double>();
		ArrayList<Integer> number = new ArrayList<Integer>();

		resultoutput.append("--------------------------------------------\n");
		resultoutput.append("Generating Traffic: "
			+ "(through exponential distribution)\n");
		resultoutput.append("--------------------------------------------\n");
		resultoutput.append("Printing results by row: \n");
		resultoutput.append("--------------------------------------------\n");

		statoutput.append("--------------------------------------------\n");
		statoutput.append("Printing traffic matrix, ships pr. hour: \n");
		statoutput.append("--------------------------------------------\n");
		
		for (int i = 0; i < lambdaarray.length; i++) {		    
		    for (int j = 0; j < lambdaarray[i].length; j++) {
			//System.out.print(" " + temp[i][j]);
			if (lambdaarray[i][j] != null) {
			    for (Double traffic : sim.makeTraffic(lambdaarray[i][j], t)) {
				resultoutput.append(traffic.toString() + " , ");
				traffictemp.add(traffic);
			    }
			    statoutput.append("\t" + Integer.toString(traffictemp.size()));
			    traffictemp.clear();			    
			    resultoutput.append("\n");
			} else {
			    resultoutput.append(" zero traffic\n");
			    statoutput.append("\t X ");
			}
			
		    }
		    statoutput.append("\n");
		    resultoutput.append("--------------------------------------------\n");
		}

		/*
		statoutput.append("--------------------------------------------\n");
		statoutput.append("Printing traffic matrix, ships pr. hour: \n");
		statoutput.append("--------------------------------------------\n");

		for (int i = 0; i < lambdaarray.length; i++) {
		    for (int j = 0; j < lambdaarray[i].length; j++) {
			if (lambdaarray[i][j] != null) {
			    int nr = sim.makeTraffic(lambdaarray[i][j], t).size();
			    statoutput.append("\t" + Integer.toString(nr));
			} else {
			    statoutput.append("\tX ");
			}
		    }

		    statoutput.append("\n");
		}
		statoutput.append("--------------------------------------------\n");
	    */} else {
		resultoutput.append("make a lambda array first please \n");
	    }
	}
    }
}
