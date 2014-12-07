package utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Client;
import model.Organization;
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
	public Client readClient(User user) {
		JDBCHelper jdbc = new JDBCHelper();
		ArrayList<Object> param = new ArrayList<Object>();
		if (user != null) {
			try {
				param.add(user.getId());
				ResultSet rs1 = jdbc.queryDB("SELECT * FROM client WHERE userId = ?;",
						param);
				
				if (rs1 != null && rs1.next()) {
					int orgId = rs1.getInt("organizationId");
					Client returnClient = new Client ();
					param = new ArrayList<Object>();
					param.add(rs1.getInt("userId"));
					rs1 = jdbc.queryDB("SELECT * FROM user WHERE id =?", param);
					returnClient.setUser(new User());
					rs1.next();
					returnClient.getUser().setId(rs1.getInt("id"));
					returnClient.getUser().setName(rs1.getString("name"));
					returnClient.getUser().setEmail(rs1.getString("email"));
					returnClient.getUser().setPassword(rs1.getString("password"));
					returnClient.getUser().setDateOfBirth(rs1.getDate("date_of_birth"));
					
					param = new ArrayList<Object>();
					
					param.add(orgId);
					rs1 = jdbc.queryDB("SELECT * FROM organization WHERE id =?", param);
					rs1.next();
					returnClient.setOrganization(new Organization());
					returnClient.getOrganization().setId(rs1.getInt("id"));
					returnClient.getOrganization().setName(rs1.getString("name"));
					returnClient.getOrganization().setAddress(rs1.getString("address"));
					
					return returnClient;
				}
				jdbc.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
}
