// Lav en der læser .sql og deler op i strings for hver ";"
// brug foundation til at afvikle



package test;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

public class resetDB {

    public static void main(String[] args) throws SQLException {
	resetDatabase();
	System.out.println("-- Database Reset --");
    }



    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://" + "localhost" + ":3306/" + "sasDB";
    private static final String USER = "sas";
    private static final String PASSWORD = "sas";

    static
    {
        try
        {
            Class.forName(DRIVER_NAME).newInstance();
            System.out.println("*** Driver loaded");
        }
        catch(Exception e)
        {
            System.out.println("*** Error : "+e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void resetDatabase() throws SQLException
    {
        String s            = new String();
        StringBuffer sb = new StringBuffer();

        try
        {
            FileReader fr = new FileReader(new File("src/SQL/sasDB.sql"));
            // be sure to not have line starting with "--" or "/*" or any other non aplhabetical character

            BufferedReader br = new BufferedReader(fr);

            while((s = br.readLine()) != null)
            {
                sb.append(s);
            }
            br.close();

            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] inst = sb.toString().split(";");
	    System.out.println(inst);

            Connection c = resetDB.getConnection();
            Statement st = c.createStatement();

            for(int i = 0; i<inst.length; i++)
            {
                // we ensure that there is no spaces before or after the request string
                // in order to not execute empty statements
                if(!inst[i].trim().equals(""))
                {
                    st.executeUpdate(inst[i]);
                    System.out.println(">>"+inst[i]);
                }
            }

        }
        catch(Exception e)
        {
            System.out.println("*** Error : "+e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
            System.out.println("################################################");
            System.out.println(sb.toString());
        }

    }
 }