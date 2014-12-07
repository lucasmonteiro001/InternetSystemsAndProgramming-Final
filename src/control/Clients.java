package control;

import model.Client;
import model.Organization;
import model.User;
import utilities.ClientDAO;
import utilities.OrganizationDAO;
import utilities.UserDAO;
/**
 * 
 * @author Mateus
 * @author Lucas
 * 
 */
public class Clients {
	private Client client;
	private Organization address;
	private User user;
	private UserDAO userDataAccessObject = null;
	private OrganizationDAO organizationDataAccessObject = null;
	private ClientDAO clientDAO = null;
	public Clients() {
		user = new User();
		userDataAccessObject = new UserDAO();
		organizationDataAccessObject = new OrganizationDAO();
		clientDAO = new ClientDAO ();
	}

	/**
	 * This method receives an user object containing its username and its password. Then, the method transforms into a hashedPassword and save it, in case that is not a repeated user.
	 * @param user information about the new user
	 * @return bool_val If the user is created returns true. Otherwise, returns false.
	 */
	public boolean createUser(User user) {
		userDataAccessObject = new UserDAO();
		if (userDataAccessObject.readUser(user) == null) {

			String hashedPassword = "";

			hashedPassword = "" + user.getPassword().hashCode();

			user.setPassword(hashedPassword);

			userDataAccessObject.addUser(user);

			return true;

		} else {

			return false;
		}
	}
	public boolean createClient (Client client) {
		if (createUser (client.getUser()) ) {
			createOrganization(client.getOrganization());
			clientDAO.addClient(client);
			return true;
		}	
		return false;	
	}
	public void createOrganization(Organization organization) {
		organizationDataAccessObject = new OrganizationDAO();
		Organization org = organizationDataAccessObject.readOrganization(organization);
		if (org == null) {
			organizationDataAccessObject.addOrganization(organization);
		} 
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public boolean userExists (User user) {
		User u = userDataAccessObject.readUser(user);
		if (u != null) {
			setUser(u);
			return true;
		}
		return false;
	}

	

}
