package com.mobile.tool.stock.manager.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {
	private static JdbcTemplate jdbcTemplate;
	private Connection connection;

	private JdbcTemplate() {
	}

	public synchronized static JdbcTemplate getPostgreSQLJdbcTemplate() {
		if (jdbcTemplate == null)
			jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.initializePostgreSQLConnection();
		return jdbcTemplate;
	}

	public synchronized static JdbcTemplate getDerbyJdbcTemplate() {
		if (jdbcTemplate == null)
			jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.initializeDerbyConnection();
		return jdbcTemplate;
	}
	
	private void initializeDerbyConnection() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			connection = DriverManager.getConnection(
					"jdbc:derby:stockmanager;");//5432
		} catch (SQLException e) {
			System.out.println("ERROR: failed to connect to the databse");
			e.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("ERROR: failed to load MySQL JDBC driver.");
			cnfe.printStackTrace();
		}
	}

	private void initializePostgreSQLConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost/stockmanager", "postgres", "postgres");//5432
		} catch (SQLException e) {
			System.out.println("ERROR: failed to connect to the databse");
			e.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("ERROR: failed to load MySQL JDBC driver.");
			cnfe.printStackTrace();
		}
	}

	public int executeUpdate(String sqlQuery) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sqlQuery);
			return ps.executeUpdate(); // executes the insert query
		} catch (Exception e) {
			System.out.println("ERROR executing query: ");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public ResultSet executeQuery(String sqlQuery) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sqlQuery);
			return ps.executeQuery(); // executes the insert query
		} catch (Exception e) {
			System.out.println("ERROR executing query: ");
			e.printStackTrace();
		}
		return null;
	}
}
