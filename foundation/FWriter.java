/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package foundation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Mats l
 */
public class FWriter {

    private Connection conn;

    public FWriter(Connection conn)	{
    this.conn = conn;
}

    public Statement updatequery(String sqlStmt) throws SQLException    {
	try {
	    Statement stmt = null;
	    stmt = conn.createStatement();
	    stmt.executeUpdate(sqlStmt);
	    return stmt;
	} catch (SQLException e) {
	    System.err.println("Got an exception ved update!  ");
	    System.err.println(e.getMessage());
	    
}
	return null;
    }

public void closeStatement(Statement stmt) throws SQLException	{
try {
			stmt.getResultSet().close();
		} catch (Exception exc) {
		}

		try {
			stmt.close();
		} catch (Exception exc) {
		}
}
}

