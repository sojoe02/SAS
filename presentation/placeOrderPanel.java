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

public class placeOrderPanel extends JPanel {

    private CActioner cactioner;
    private specificationPanel specPanel;
    private JButton orderButton, searchButton, clearButton, useSpecButton;
    
    private JScrollPane orderPane, startDatePane, endDatePane, shipsPane;
    private JTextArea orderText, startDateText, endDateText, shipsText;
    private JRadioButton startRadio, endRadio;
    private JTextField dateChoiceField, shipChoiceField;
    private String[] info;
    private String startDate, endDate, startDest, endDest, conNum, content;
    private ArrayList<String> shipDates = new ArrayList<String>();
    private DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

    placeOrderPanel(specificationPanel specPanel) {

	//Tilføj obj referencen til Specifkations obj
	this.specPanel = specPanel;


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
        choosingDatePanel.setLayout(new GridLayout(2, 1));

        shipChoiceField = new JTextField("Eks: 1");
        dateChoiceField = new JTextField("Eks: 2010-12-04");


        //oprettelse af to tekstfelter, hvori man kan indtaste den ønskede dato
        //og det ønsekde skib
        choosingDatePanel.add(new JLabel("Indtast den ønskede dato: "));
        choosingDatePanel.add(dateChoiceField);

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
                orderText.setText("Afgangs dato: " + dateChoiceField.getText()
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
            System.out.println(shipChoiceField.getText());
            System.out.println(dateChoiceField.getText());
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
	    endDate = info[1];
	    startDest = info[2];
	    endDest = info[3];
	    conNum = info[4];
	    content = info[5];
	    System.out.println("søgning er udført");

	    //TODO tilpas den nye metode-parametre
            try {
		shipDates = cactioner.findShipDates(startDest,  endDest,df.parse(startDate),
                        df.parse(endDate), Integer.parseInt(conNum), content);
	    } catch (Exception e) {
		e.getMessage();
	    }

            shipDates.add("2010-12-04");
            shipDates.add("2011-11-01");
            shipDates.add("1");

            shipDates.add("2020-12-30");
            shipDates.add("2059-05-10");
            shipDates.add("2");



            //3 parametre som skal angive, start, slut og skibs ids position i
            //arraylisten shipDates
            int a = 0,b = 1,c = 2;

            //skriver alle de søgte datoer og skibs id ud
            while(c<shipDates.size()){
                shipsText.append("Start datoer: " + shipDates.get(a));
//                a=a+3;
                shipsText.append("\nSlut datoer: " + shipDates.get(b));
                b=b+3;
                shipsText.append("\nSkibs ID: " + shipDates.get(c)+ "\n\n");
                c=c+3;
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
