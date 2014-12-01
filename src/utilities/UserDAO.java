package utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.User;
/**
 * UserDAO class to manipulates the User on Database.
 */
public class UserDAO {

	/*
	 * We are going to use a CRUD to access the Objects at the Database
	 */
	public UserDAO() {

	}
	/**
	 * This method reads the user passed as parameter of the database.
	 * @see JDBCHelper 
	 * @param user
	 */
	
	public User readUser(User user) {
		// (String host, String db, String user, String password){
		JDBCHelper jdbc = new JDBCHelper();
		ArrayList<Object> param = new ArrayList<Object>();
		/**
		 * param.add("Hello"); param.add(35767); param.add(4.0);
		 * param.add(Timestamp.valueOf("2014-09-30 11:41:00"));
		 */
		param.add(user.getEmail());
		ResultSet rs1 = jdbc
				.queryDB(
						"SELECT * FROM user WHERE user.email =?;",
						param);

		try {
			if (rs1 != null && rs1.next()) {
				User returnUser = new User();
				returnUser.setEmail(rs1.getString("email"));
				returnUser.setPassword(rs1.getString("password"));
				returnUser.setId(rs1.getInt("id"));
				returnUser.setDateOfBirth(rs1.getDate("date_of_birth"));
				
				return returnUser;
			}
			jdbc.conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
 * This method adds the user passed as parameter of the database.
 * @see JDBCHelper 
 * @param user
	 */
	public void addUser(User user) {
		JDBCHelper jdbc = new JDBCHelper();
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(user.getEmail());
		param.add(user.getPassword());
		java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String dateBirth = sdf.format(user.getDateOfBirth());
		param.add(dateBirth);
		try {
			ResultSet rs1 = jdbc.insertDB(
					"INSERT INTO user (email, password, date_of_birth) VALUES (?, ?, ?);",
					param);

			jdbc.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
/**
 * This method remove the user passed as parameter of the database.
 * @see JDBCHelper 
 * @param user
 */
	public void removeUser(User user) {
		JDBCHelper jdbc = new JDBCHelper();
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(user.getName());
		try {
			ResultSet rs1 = jdbc.queryDB("DELETE user WHERE (email = ?);",
					param);

			jdbc.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
