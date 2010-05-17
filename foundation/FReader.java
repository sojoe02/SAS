/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package foundation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Mats l
 */
public class FReader {
private Connection conn;



public FReader(Connection conn)	{
    this.conn = conn;
}

public ResultSet query(String sqlStmt) throws SQLException    {
    Statement  stmt = null;
    ResultSet rs = null;

    stmt = conn.createStatement();
    rs = stmt.executeQuery(sqlStmt);
    return rs;
}

public void closeResult(ResultSet rs) throws SQLException	{
	Statement stmt = rs.getStatement();
		rs.close();

	stmt.close();
	
}
}

