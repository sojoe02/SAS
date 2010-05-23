/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Zagadka
 */
public class SimulatorPanel extends JPanel {

    private JButton startship;
    private JTextField speedField, timeField;
    private SimulatorEntity sim;
    private JComboBox toList, fromList, speedList, timeList;
    private JTabbedPane tabbed = new JTabbedPane();
    private String[] Harbours = {"Odense", "NewOrleans", "CapeTown"};
    private Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 16, 18, 20};
    private String to, from;
    private int speed, time;
    private static int shipID = 110;

    public SimulatorPanel() {

	//fill the array with interger values for speed, and timefactor.

	setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

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

 /*	speedField = new JTextField("5");
	timeField = new JTextField("15");
*/

	JPanel shipsimPanel = new JPanel();
	shipsimPanelHandler(shipsimPanel);

	JPanel choosePanel = new JPanel();
	choosePanelHandler(choosePanel);
    }

    private void choosePanelHandler(JPanel choosePanel) {


	//choosePanel.setLayout(new BoxLayout(shipsimPanel, BoxLayout.Y_AXIS));


	/*
	shipsimPanel.add(new JLabel("Til	    (feks. NewOrleans)"));
	shipsimPanel.add(toField);
	shipsimPanel.add(new JLabel("Fra	    (feks. Odense)"));*/
	choosePanel.add(new JLabel("Til:"));
	choosePanel.add(toList);

	choosePanel.add(new JLabel("Fra:"));
	choosePanel.add(fromList);
	
	choosePanel.add(startship);



	add(choosePanel);
    }

    private void shipsimPanelHandler(JPanel shipsimPanel) {

	//shipsimPanel.setLayout(new BoxLayout(shipsimPanel, BoxLayout.Y_AXIS));
	//shipsimPanel.setLayout(new BoxLayout(shipsimPanel, BoxLayout.PAGE_AXIS));

	//shipsimPanel.setPreferredSize(new Dimension(200, 300));
	//shipsimPanel.setLayout(new GridLayout(2, 2, 30, 30));
	shipsimPanel.add(new JLabel("Hastighed:"));
	shipsimPanel.add(speedList);

	shipsimPanel.add(new JLabel("Tidsfactor:"));
	shipsimPanel.add(timeList);
	
 /*
	shipsimPanel.add(new JLabel("Hastighed	    (feks. 10)"));
	shipsimPanel.add(speedField);
	shipsimPanel.add(new JLabel("Tidsfactor	    (feks. 10)"));
	shipsimPanel.add(timeField);
*/
	add(shipsimPanel);
    }

    private class startshipListener implements ActionListener {

	public void actionPerformed(ActionEvent event) {

	    //int speed = Integer.parseInt(speedField.getText());
	    //int time = Integer.parseInt(timeField.getText());

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
}
