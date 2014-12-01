package utilities;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
/**
 * UserDAO class to manipulates the User on Database.
 */
public class AccountDAO {

	/*
	 * We are going to use a CRUD to access the Objects at the Database
	 */
	public AccountDAO() {

	}
	/**
	 * This method remove the account passed as parameter of the database.
	 * @see JDBCHelper 
	 * @param account
	 */
	public Account readAccount(Account account) {
		// (String host, String db, String user, String password){
		JDBCHelper jdbc = new JDBCHelper();
		ArrayList<Object> param = new ArrayList<Object>();

		param.add(account.getHolderId());
		param.add(account.getRoutingNumber());

		ResultSet rs1 = jdbc
				.queryDB(
						"SELECT * FROM account WHERE account.holder_id =? AND account.routing_number =?;",
						param);

		try {
			if (rs1 != null && rs1.next()) {
				Account returnAccount = new Account();
				returnAccount.setId(rs1.getInt("id"));
				returnAccount.setHolderId(rs1.getInt("holder_id"));
				returnAccount.setRoutingNumber(rs1.getInt("routing_number"));
				returnAccount.setBalance(rs1.getDouble("balance"));
				return returnAccount;
			}
			jdbc.conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method adds the account passed as parameter of the database.
	 * @see JDBCHelper 
	 * @param account
	 */
	public void addAccount(Account account) {
		JDBCHelper jdbc = new JDBCHelper();
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(account.getHolderId());
		param.add(account.getRoutingNumber());
		param.add(account.getBalance());
		try {
			ResultSet rs1 = jdbc
					.insertDB(
							"INSERT INTO account (holder_id, routing_number, balance) VALUES (?, ?, ?);",
							param);

			jdbc.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method remove the account passed as parameter of the database.
	 * @see JDBCHelper 
	 * @param account
	 */
	public void removeAccount(Account account) {
		JDBCHelper jdbc = new JDBCHelper();
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(account.getId());
		try {
			ResultSet rs1 = jdbc.queryDB("DELETE account WHERE (id = ?);",
					param);

			jdbc.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateAccount(Account account) {
		JDBCHelper jdbc = new JDBCHelper();
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(account.getBalance());
		param.add(account.getHolderId());
		param.add(account.getRoutingNumber());
		try {
			jdbc.insertDB(
					"UPDATE account SET account.balance = ? WHERE account.holder_id = ? AND account.routing_number =?;",
					param);
			jdbc.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
