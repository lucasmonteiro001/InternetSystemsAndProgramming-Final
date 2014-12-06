package utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Client;
import model.User;

public class ClientDAO {
	private UserDAO uDao;
	private OrganizationDAO oDao;
	/*
	 * We are going to use a CRUD to access the Objects at the Database
	 */
	public ClientDAO() {
		uDao = new UserDAO();
		oDao = new OrganizationDAO();
	}
	/**
	 * This method adds the client passed as parameter of the database.
	 * @see JDBCHelper 
	 * @param user
	 */
	public void addClient (Client client) {
		JDBCHelper jdbc = new JDBCHelper();
		ArrayList<Object> param = new ArrayList<Object>();

		try {
			client.setUser(uDao.readUser(client.getUser()));
			client.setOrganization(oDao.readOrganization(client.getOrganization()));
			param.add(client.getUser().getId());
			param.add(client.getOrganization().getId());
			jdbc.insertDB(
					"INSERT INTO client (userId, organizationId) VALUES (?, ?);",
					param);

			jdbc.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
