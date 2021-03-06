package model;
/**
 * Model class for Client, containing User and Password.
 * 
 * @author Mateus
 *
 */

public class Client {
	private User user;
	private Organization organization;
	public Client() {
		super();
	}
	public Client(User user, Organization organization) {
		super();
		this.user = user;
		this.organization = organization;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	
}
