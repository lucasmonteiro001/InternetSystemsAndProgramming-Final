package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

public class JDBCHelper {

	public Connection conn;
	String host;
	String db;
	String user;
	String pwd;

	public JDBCHelper() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Properties prop = new Properties();

			prop.load(this.getClass().getResourceAsStream(
					"database-config.properties"));

			host = prop.getProperty("HOST_ADDRESS");
			db = prop.getProperty("USER_DATABASE");
			user = prop.getProperty("USERNAME");
			pwd = prop.getProperty("PASSWORD");

		} catch (FileNotFoundException e) {
			System.out
					.println("property file <database-config.properties> not found in classpath");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.conn = this.initiateConnection(host, db, user, pwd);

	}

	/***
	 * Make sure to close your connection!!!
	 */

	/**
	 * This class loads the MySQL Driver and Connects to the entered database.
	 * 
	 * @param host
	 *            Host computer ("cse.unl.edu")
	 * @param db
	 * @param user
	 * @param password
	 * @return A live connection or null
	 */
	public Connection initiateConnection(String host, String db, String user,
			String password) {

		Connection dbConnection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// System.out.println("Driver Loaded");
		} catch (Exception x) {
			System.out.println("Unable to load the driver class");
		}

		try {
			String connString = String.format(
					"jdbc:mysql://%s:3306/%s?user=%s&password=%s", host, db,
					user, password);
			dbConnection = DriverManager.getConnection(connString);
			// System.out.println("Connected to Database");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Couldn't get Connection");
			return null;
		}
		return dbConnection;
	}

	/**
	 * 
	 * 
	 * @param query
	 *            A Select Statement. Use ? for parameters and the sqlParam
	 *            parameters to pass in values.
	 * @param sqlParam
	 *            An ArrayList of objects of parameters for the Select
	 *            Statement.
	 * @return A resultset if the query is successful, else null
	 */
	public <T> ResultSet queryDB(String query, ArrayList<T> sqlParam) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);

			int i = 1;
			for (T a : sqlParam) {
				// System.out.println(a.getClass());
				if (a.getClass() == String.class) {
					ps.setString(i, (String) a);
					// System.out.println(String.format("I'm a String!  %d - %s",
					// i, (String) a));
				} else if (a.getClass() == Integer.class) {
					ps.setInt(i, (Integer) a);
					// System.out.println(String.format("I'm an Integer!  %d - %d",
					// i, (Integer) a));
				} else if (a.getClass() == Double.class) {
					ps.setDouble(i, (Double) a);
					// System.out.println(String.format("I'm a Double!  %d - %f",
					// i, (Double) a));
				} else if (a.getClass() == Timestamp.class) {
					ps.setTimestamp(i, (Timestamp) a);
					// System.out.println(String.format("I'm a DateTime!  %d - %s",
					// i, a.toString()));
				}
				i++;
			}

			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return rs;
	}

	public <T> ResultSet insertDB(String query, ArrayList<T> sqlParam) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);

			int i = 1;
			for (T a : sqlParam) {
				// System.out.println(a.getClass());
				if (a.getClass() == String.class) {
					ps.setString(i, (String) a);
					// System.out.println(String.format("I'm a String!  %d - %s",
					// i, (String) a));
				} else if (a.getClass() == Integer.class) {
					ps.setInt(i, (Integer) a);
					// System.out.println(String.format("I'm an Integer!  %d - %d",
					// i, (Integer) a));
				} else if (a.getClass() == Double.class) {
					ps.setDouble(i, (Double) a);
					// System.out.println(String.format("I'm a Double!  %d - %f",
					// i, (Double) a));
				} else if (a.getClass() == Timestamp.class) {
					ps.setTimestamp(i, (Timestamp) a);
					// System.out.println(String.format("I'm a DateTime!  %d - %s",
					// i, a.toString()));
				}
				i++;
			}

			ps.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
