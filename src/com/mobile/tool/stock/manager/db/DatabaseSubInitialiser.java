package com.mobile.tool.stock.manager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseSubInitialiser {

	private DatabaseSubInitialiser() {
	}

	public static void main(String[] args) {
		DatabaseSubInitialiser dbSubInitialiser = new DatabaseSubInitialiser();
		try {
			Connection conn = dbSubInitialiser.initialiseDBConnection();
			dbSubInitialiser.initialiseSubStaticDataInDB(conn);
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

	private void initialiseSubStaticDataInDB(Connection conn) throws SQLException {
		PreparedStatement psInsert = conn.prepareStatement("INSERT INTO EMPLOYEES_RECORDS VALUES ('SHAAGR123', 'Shashank', 'Agrawal', 1234567890, 'Male', NULL, NULL, 27, 'Married', NULL, NULL, 'shashank180287@gmail.com', CURRENT_DATE, NULL, NULL, 'employee1', NULL, NULL, NULL)");
		psInsert.executeUpdate();
		psInsert = conn.prepareStatement("INSERT INTO EMPLOYEES_RECORDS VALUES ('SHAAGR456', 'Shashank', 'Agrawal', 1234567890, 'Male', NULL, NULL, 27, 'Married', NULL, NULL, 'shanky_rmn@yahoomail.co.in', CURRENT_DATE, NULL, NULL, 'admin', NULL, NULL, NULL)");
		psInsert.executeUpdate();
		psInsert = conn.prepareStatement("INSERT INTO EMPLOYEES_RECORDS VALUES ('SHAAGR789', 'Shashank', 'Agrawal', 1234567890, 'Male', NULL, NULL, 27, 'Married', NULL, NULL, 'shashank180287@gmail.com', CURRENT_DATE, NULL, NULL, 'customer1', NULL, NULL, NULL)");
		psInsert.executeUpdate();
		psInsert = conn.prepareStatement("INSERT INTO TRANSECTION_MODE values (1, 'CASH', 'Paid by cash')");
		psInsert.executeUpdate();
		psInsert = conn.prepareStatement("INSERT INTO VENDOR_CATEGORY VALUES (1, 'Electronics Provider', 'Electronic Items Provider')");
		psInsert.executeUpdate();
		psInsert = conn.prepareStatement("INSERT INTO PRODUCT_CATEGORY VALUES (1, 'Electronics', 'Electronics')");
		psInsert.executeUpdate();
	}
}
