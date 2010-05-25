/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Mats Larsen, Stefan Skytthe, Dan Vi, Søren Jørgensen
 */
public class SimulatorPanel extends JPanel implements Observer {

    private JButton startship;
    private SimulatorEntity sim;
    private JComboBox toList, fromList, speedList, timeList;
    /*
     * Constants and variables for use witht the shipsimulatorpanel:
     */
    private String[] Harbours = {"Odense", "NewOrleans", "CapeTown"};
    private Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 16, 18, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    private String to, from;
    private int speed, time;
    private static int shipID = 110;
    //-------------------------------------------------------------------
    /*
     * Constants and variables for use with the statpanel
     */
    private JTextArea statoutput, resultoutput;
    private JComboBox kList, LList, tList, hList,t_estList;
    private JButton makeLambdaArray, makeTraffic, poisson;
    //lmax er lig antal skibe i timen!
    private int lmax = 10, harbournr = 6, t = 1, k = 1, t_est = 1;
    private JScrollPane scrollPane, scrollPane2;
    private Integer[][] lambdaarray = null;
    private Double[][] trafficarray = null;

    private Integer[] estimate;
    //---------------------------------------------------------------------

    public SimulatorPanel() {

	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	fillEstimateArray();

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

	startship = new JButton("Affyr skib");
	startship.addActionListener(new startshipListener());

	JPanel shipsimPanel = new JPanel();
	shipsimPanelHandler(shipsimPanel);

	//------------------------------------------------------------
	//next the stat panel.

	statoutput = new JTextArea(15, 80);
	statoutput.setEditable(false);
	scrollPane = new JScrollPane(statoutput);

	resultoutput = new JTextArea(10, 80);
	resultoutput.setEditable(false);
	scrollPane2 = new JScrollPane(resultoutput);

	makeLambdaArray = new JButton("Sæt traffik");
	makeLambdaArray.addActionListener(new makeLambdaArrayListener());

	makeTraffic = new JButton("Lav Traffik");
	makeTraffic.addActionListener(new makeTrafficListener());

	poisson = new JButton("Poisson");
	poisson.addActionListener(new makePoissonListener());

	kList = new JComboBox(estimate);
	kList.addActionListener(new chooseKListener());
	kList.setSelectedIndex(2);

	hList = new JComboBox(integers);
	hList.addActionListener(new chooseHListener());
	hList.setSelectedIndex(2);

	tList = new JComboBox(integers);
	tList.addActionListener(new chooseTListener());
	tList.setSelectedIndex(2);

	LList = new JComboBox(integers);
	LList.addActionListener(new chooseLListener());
	LList.setSelectedIndex(2);

	JPanel buttonstatPanel = new JPanel();
	buttonStatPanelHandler(buttonstatPanel);

	JPanel statPanel = new JPanel();
	statPanelHandler(statPanel);
	statPanel.add(scrollPane);
	statPanel.add(scrollPane2);
    }

    private void statPanelHandler(JPanel statPanel) {
	statPanel.setLayout(new BoxLayout(statPanel, BoxLayout.Y_AXIS));
	add(statPanel);
    }

    private void buttonStatPanelHandler(JPanel buttonstatPanel) {

	buttonstatPanel.setLayout(new BoxLayout(buttonstatPanel, BoxLayout.X_AXIS));
	buttonstatPanel.add(new JLabel(" Antal Havne:"));
	buttonstatPanel.add(hList);


	buttonstatPanel.add(new JLabel(" Skibe pr.time:"));
	buttonstatPanel.add(LList);

	buttonstatPanel.add(makeLambdaArray);
	buttonstatPanel.add(new JLabel(" Traffik periode(t):"));
	buttonstatPanel.add(tList);

	buttonstatPanel.add(makeTraffic);

	buttonstatPanel.add(new JLabel(" Estimer antal skibe(k):"));
	buttonstatPanel.add(kList);	

	buttonstatPanel.add(poisson);

	add(buttonstatPanel);
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

    public void fillEstimateArray() {
	int max = 300;
	estimate = new Integer[max];
	//ArrayList<Integer> temp = new ArrayList<Integer>();
	for (int i = 0; i < 300; i++) {
	    estimate[i] = i;
	}
    }
    public void update(Observable o, Object o1) {
	resultoutput.append("--------------------------------------------\n");
	resultoutput.append("Skib nr." + (String)o1 + " er gået i havn \n");
	resultoutput.append("--------------------------------------------\n");
	
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
	    statoutput.append("--------------------------------------------\n");
	    statoutput.append("Generer Lambda matrice, skibe pr. time(lambda): \n");
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
	    /*
	     * this convoluted thing will print out a traffic array of ships
	     * pr t int the stat output, and their intervals in the result output.
	     */
	    if (lambdaarray != null) {

		ArrayList<Double> traffictemp = new ArrayList<Double>();
		ArrayList<Integer> number = new ArrayList<Integer>();
		trafficarray = new Double[lambdaarray.length][lambdaarray.length];

		resultoutput.append("--------------------------------------------\n");
		resultoutput.append("Generer Traffik: "
			+ "(via exponential distribution)\n");
		resultoutput.append("--------------------------------------------\n");
		resultoutput.append("Printer resultater pr række: \n");
		resultoutput.append("--------------------------------------------\n");

		statoutput.append("--------------------------------------------\n");
		statoutput.append("Printer traffik matrice, skibe pr. timeslot: \n");
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
			    trafficarray[i][j] = Double.valueOf((double)traffictemp.size()/(double)t);
			    //System.out.println( Double.valueOf((double)traffictemp.size()/(double)t));
			    traffictemp.clear();
			    resultoutput.append("\n");
			} else {
			    trafficarray[i][j] = null;
			    resultoutput.append(" Ingen Traffik\n");
			    statoutput.append("\tX ");
			}

		    }
		    statoutput.append("\n");
		    resultoutput.append("--------------------------------------------\n");
		}
	    } else {
		resultoutput.append("Lav et vægtingsarray først (sæt traffik) \n");
	    }
	}
    }

    private class makePoissonListener implements ActionListener {

	public void actionPerformed(ActionEvent event) {

	    if (trafficarray != null) {
		statoutput.append("--------------------------------------------\n");
		statoutput.append("Poisson sansyndligheds matrice (%): \n");
		statoutput.append("--------------------------------------------\n");
		for (int i = 0; i < trafficarray.length; i++) {
		    for (int j = 0; j < trafficarray[i].length; j++) {
			if (trafficarray[i][j] != null) {
			    DecimalFormat df = new DecimalFormat("###.###");
			    String temp = df.format(100 * sim.getPoissonProb(t, trafficarray[i][j], k));
			    statoutput.append("\t" + temp);
			} else {
			    statoutput.append("\tX");
			}
		    }
		    //System.out.println("");
		    statoutput.append("\n");
		}

		statoutput.append("--------------------------------------------\n");
	    } else {
		resultoutput.append("Lav noget traffik først (sæt traffik) \n");
	    }

	}
    }

    private class chooseKListener implements ActionListener {

	public void actionPerformed(ActionEvent event) {
	    JComboBox cb = (JComboBox) event.getSource();
	    k = (Integer) cb.getSelectedItem();
	}
    }

    private class chooseTListener implements ActionListener {

	public void actionPerformed(ActionEvent event) {
	    JComboBox cb = (JComboBox) event.getSource();
	    t = (Integer) cb.getSelectedItem();
	}
    }

    private class chooseLListener implements ActionListener {

	public void actionPerformed(ActionEvent event) {
	    JComboBox cb = (JComboBox) event.getSource();
	    lmax = (Integer) cb.getSelectedItem();
	}
    }

    private class chooseHListener implements ActionListener {

	public void actionPerformed(ActionEvent event) {
	    JComboBox cb = (JComboBox) event.getSource();
	    harbournr = (Integer) cb.getSelectedItem();
	}
    }

       private class chooseT_estListener implements ActionListener {

	public void actionPerformed(ActionEvent event) {
	    JComboBox cb = (JComboBox) event.getSource();
	    t_est = (Integer) cb.getSelectedItem();
	}
    }

    
}
