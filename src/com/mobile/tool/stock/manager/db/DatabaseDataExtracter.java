package com.mobile.tool.stock.manager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseDataExtracter {

	public static void main(String[] args) {
		DatabaseDataExtracter dbDataExtracter = new DatabaseDataExtracter();
		try {
			Connection conn = dbDataExtracter.initialiseDBConnection();
			if(args.length==1){
				
			}else{
				dbDataExtracter.readAndPrintAllData(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Connection initialiseDBConnection() throws ClassNotFoundException,
			SQLException {
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		String connectionURL = "jdbc:derby:stockmanager";
		Class.forName(driver);
		return DriverManager.getConnection(connectionURL);
	}
	
	private void readAndPrintAllData(Connection conn) {
		// TODO Auto-generated method stub
		/*Statement stmt2 = conn.createStatement();
		ResultSet rs = stmt2.executeQuery("select * from Employee");
		int num = 0;
		while (rs.next()) {
			System.out.println(++num + ": Name: " + rs.getString(1)
					+ "\n Address: " + rs.getString(2));
		}
		rs.close();*/
	}
	
	
}
