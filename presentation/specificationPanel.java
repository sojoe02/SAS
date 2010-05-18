/*
 * To change this template, choose Tools | Templates

 * and open the template in the editor.
 */

package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/* Denne klasse indeholder alle de specifikationer som man skal bruge, for at
 * her indtaster man, stat-/slutdestination, start eller slut dato, antal
 * container og containernes indhold.
 */

public class specificationPanel extends JPanel{
    private JLabel startDestLab,endDestLab, startDateLab, endDateLab, numLab, conLab;

    private JTextField startDestField, endDestField, startDateField,
            endDateField, numField, conField;
    

    specificationPanel(){

        setBackground(Color.white);

        setBorder(BorderFactory.createTitledBorder("Specifikation"));

        setLayout(new GridLayout(14,1));

        startDestLab = new JLabel("Start destination: ");
        endDestLab = new JLabel("Slut destination: ");
        startDateLab = new JLabel("Afgangs dato (yyyy-mm-dd) : ");
        endDateLab = new JLabel("Ankomst dato (yyyy-mm-dd) : ");
        numLab = new JLabel("Antal container: ");
        conLab = new JLabel("Container indhold: ");


        //tilføj text felter
        startDestField = new JTextField("Odense");
        endDestField = new JTextField("Amsterdam");
        startDateField = new JTextField("2010-06-02");
        endDateField = new JTextField("2010-07-05");
        numField = new JTextField("10");
        conField = new JTextField("Bananer");


        add(startDestLab);
        add(startDestField);

        add(endDestLab);
        add(endDestField);

        add(startDateLab);
        add(startDateField);

        add(endDateLab);
        add(endDateField);

        add(numLab);
        add(numField);

        add(conLab);
        add(conField);

    }

    /* getInfo sender et String array:
     * {startdato, slutdato, startdestination, slutdestination, antal container
     * , indhold af ordre}
     */
    public String[] getInfo(){
        String[] info = {startDateField.getText(), endDateField.getText(),
        startDestField.getText(),endDestField.getText(), numField.getText(), 
        conField.getText()};

        return info;
    }
   
}

