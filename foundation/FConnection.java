/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package foundation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mats l
 */
public class FConnection {

    private Connection conn;
    private FReader reader;
    private FWriter writer;
    private String dbUrl;
    private String dbDriver;

    public FConnection(String dbUrl, String dbDriver) throws ClassNotFoundException {
	this.dbUrl = dbUrl;
	this.dbDriver = dbDriver;
	Class.forName(dbDriver);
    }

    public boolean connect(String userName, String passWord, String dataBaseUser) throws SQLException {

	if (conn != null) {
	    if ((userName != null && userName.length() != 0) && (passWord != null && passWord.length() != 0)) {
		Connection con = null;
		try {
		    con = DriverManager.getConnection(dbUrl, dataBaseUser, passWord);

		    System.out.println("Connection established to " + dbUrl + "...");

		} catch (java.sql.SQLException e) {
		    System.out.println("Connection couldn't be established to " + dbUrl + e.toString());
		}
		if (con != null) {
		    try {
			conn.close();
		    } catch (Exception e1) {
		    }
		    conn = con;		   
		}
	    }
	} else {
	    try {
		conn = DriverManager.getConnection(dbUrl, dataBaseUser, passWord);
		System.out.println("Connection established to " + dbUrl + "...");
	    }catch (Exception e) {
		System.out.println("Connection couldn't be established to " + dbUrl + e.toString());
	}
    }

	reader = new FReader(conn);
	writer = new FWriter(conn);

	
	    if(conn != null && !conn.isClosed())    {
		return true;
	    }
	    else    {
	    return false;
	}

}
    public FReader getReader()	{
	return reader;
    }
    public FWriter getWriter()	{
	return writer;
    }
}

