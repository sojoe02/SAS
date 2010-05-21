/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import control.CActioner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class placeOrderPanel extends JPanel {

    private CActioner control;
    private PUser user;

    private specificationPanel specPanel;
    private JButton orderButton, searchButton, clearButton, useSpecButton;
    
    private JScrollPane orderPane, startDatePane, endDatePane, shipsPane;
    private JTextArea orderText, startDateText, endDateText, shipsText;
    private JRadioButton startRadio, endRadio;
    private JTextField startDateChoiceField,endDateChoiceField, shipChoiceField;
    private String[] info;
    private String startDate, endDate, startDest, endDest, conNum, content;
    private ArrayList<String[]> shipDates = new ArrayList<String[]>();
    private DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

    placeOrderPanel(specificationPanel specPanel, CActioner control, PUser user) {

	//Tilføj obj referencen til Specifkations obj
	this.specPanel = specPanel;
	this.control = control;
	this.user = user;

	//Layout for PlaceOrder Panel
	setBackground(Color.white);
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Tekstfelt med alle de mulige skibe
        shipsText = new JTextArea();
        shipsText.setEditable(false);

        shipsPane = new JScrollPane(shipsText);


	searchButton = new JButton("Søg efter skib ledige skibe");
	searchButton.addActionListener(new searchListener());

        clearButton = new JButton("Ryd søgningen");
        clearButton.addActionListener(new clearListener());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        buttonPanel.add(searchButton);
        buttonPanel.add(clearButton);

        /* oprettelse af et panel som indeholder et vindue til alle skibs info
         * og en knap til at søge i databasen efter skibe
         *
         */

        JPanel shipChoosingPanel = new JPanel();
        shipChoosingPanel.setBackground(Color.white);
        shipChoosingPanel.setLayout(new BoxLayout(shipChoosingPanel, BoxLayout.Y_AXIS));

        shipChoosingPanel.add(new JLabel("Vælg et skib"));
        shipChoosingPanel.add(shipsPane);
        shipChoosingPanel.add(buttonPanel);


        





/*
        //rulbart vindue med startdatoer
        startDateText = new JTextArea();
        startDateText.setEditable(false);


//Temp:
	startDateText.setText("18-12-2010");

	startDatePane = new JScrollPane(startDateText);

	//Rulbart vindue med ankomstdatoer
	endDateText = new JTextArea();
	endDateText.setEditable(false);

//Temp
	endDateText.setText("20-12-2010");

	endDatePane = new JScrollPane(endDateText);



	JPanel datePanePanel = new JPanel();
	datePanePanel.setBackground(Color.white);
	datePanePanel.setLayout(new GridLayout(2, 2));
	datePanePanel.add(new JLabel("Afgangs dato: "));
	datePanePanel.add(new JLabel("Ankomst dato: "));
	datePanePanel.add(startDatePane);
	datePanePanel.add(endDatePane);



	dateChoiceField = new JTextField();





	//opret Listener til RadioButtons
	chooseOrder listener = new chooseOrder();
*/

        
        JPanel choosingDatePanel = new JPanel();
        choosingDatePanel.setBackground(Color.white);
        choosingDatePanel.setLayout(new GridLayout(4,1));

        shipChoiceField = new JTextField("");
        startDateChoiceField = new JTextField("");
        endDateChoiceField = new JTextField("");

        //oprettelse af to tekstfelter, hvori man kan indtaste den ønskede dato
        //og det ønsekde skib
        choosingDatePanel.add(new JLabel("Indtast den ønskede starID: "));
        choosingDatePanel.add(startDateChoiceField);

        choosingDatePanel.add(new JLabel("Indtast den ønskede slutID: "));
        choosingDatePanel.add(endDateChoiceField);


        choosingDatePanel.add(new JLabel("Indtast det ønskede skibsnummer: "));
        choosingDatePanel.add(shipChoiceField);

        useSpecButton = new JButton("Brug specfikiationer");
        useSpecButton.addActionListener(new chooseOrder());


/*

	//oprettelse af de to radiobuttons, og tilføjelse af deres actionlistener
	startRadio = new JRadioButton("Brug afgangs dato");
	startRadio.addActionListener(listener);
	startRadio.setBackground(Color.white);

	endRadio = new JRadioButton("Brug ankomst dato");
	endRadio.addActionListener(listener);
	endRadio.setBackground(Color.white);

	//indsæt radiobuttons i en gruppe
	ButtonGroup datoGroup = new ButtonGroup();
	datoGroup.add(startRadio);
	datoGroup.add(endRadio);

	JPanel radioPanel = new JPanel();
	radioPanel.setBackground(Color.white);
	radioPanel.add(startRadio);
	radioPanel.add(endRadio);
*/

	orderText = new JTextArea();    //Et felt hvor man kan skrive
	orderText.setEditable(false);   //Gør at man ikke kan ændre i teksten

	//oprettelse af rul-bar vindue
	orderPane = new JScrollPane(orderText);
	orderPane.setSize(new Dimension(100, 100));


	//De to knapper: søg og opret
	orderButton = new JButton("Opret Ordre");
	orderButton.addActionListener(new makeOrderListener());





//*********************************************************
//          Tilføjning af de forskellige komponenter
//***************************************************


        add(shipChoosingPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
//      add(datePanePanel);
        add(choosingDatePanel);
        add(useSpecButton);
//        add(radioPanel);
        add(orderPane);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(orderButton);

    }

    private class chooseOrder implements ActionListener {
        /*
         * Bruges når man har valgt ALLE ens specifikationer, inkl skib og dato
         * Man får så vist hvordan ens ordre kommer til at se ud
         */


        public void actionPerformed(ActionEvent event) {
                orderText.setText("Afgangs dato: " + startDateChoiceField.getText()
			+ "\nAnkomst dao: " +endDateChoiceField.getText()
                        + "\nSkibs ID: " + shipChoiceField.getText()
                        + "\n\nStart destination: " + startDest
                        + "\nSlut Destination: " + endDest
                        + "\nAntal container: " + conNum
                        + "\n\nOrdre indhold: " + content);
        }
    }

    private class makeOrderListener implements ActionListener{
        /*
         * Laver et popup vindue, som siger at ordren er bekræftet og lavet
         */
        public void actionPerformed(ActionEvent event) {
//            System.out.println(shipChoiceField.getText());
//            System.out.println(startDateChoiceField.getText());
//            System.out.println(endDateChoiceField.getText());
	    
	    try {
		control.placeOrder(user.getUserID(), Integer.parseInt(shipChoiceField.getText()), Integer.parseInt(startDateChoiceField.getText()),
			Integer.parseInt(endDateChoiceField.getText()), Integer.parseInt(conNum), content);
	    } catch (Exception e) {
		e.getMessage();
	    }

	    JOptionPane.showMessageDialog(null, "Ordren er oprettet og registeret");
   
	}
    }

    private class searchListener implements ActionListener {

	public void actionPerformed(ActionEvent event) {

	    /* getInfo sender et String array:
	     * {startdato, slutdato, startdestination, slutdestination, antal container
	     * , indhold af ordre}
             *
             * Der bliver derefter søgt i databasen for at finde de matchende
             * datoer og skibe, og man får det så vist
	     */
	    info = specPanel.getInfo(); //Strign array
	    startDate = info[0];
	    startDest = info[1];
	    endDest = info[2];
	    conNum = info[3];
	    content = info[4];
	    System.out.println("søgning er udført");


            try {
		int t = Integer.parseInt(conNum);
		Date date = df.parse(startDate);
		shipDates = control.findShipDates(startDest,  endDest, date, Integer.parseInt(conNum));
	    } catch (Exception e) {
		e.getMessage();
	    }


	    for (int i = 0; i < shipDates.size(); i++) {
		String[] shipDate = shipDates.get(i);
		    shipsText.append("Start datoer: " + shipDate[3] + "("+ shipDate[1] +")");
		    shipsText.append("\nSlut datoer: " + shipDate[4] + "("+ shipDate[2] +")");
		    shipsText.append("\nSkibs ID: " + shipDate[0] + "\n");
	    }
	}
    }

    private class clearListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            //sletter alle datoer og skibs ider fra søgnings vindue
            shipDates.clear(); //sletter alt fra shipDates
            shipsText.setText("");  //lave et tomt vindue
        }
    }
}
