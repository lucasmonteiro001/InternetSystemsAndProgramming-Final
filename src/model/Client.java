package model;

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
	public void setClient(Organization organization) {
		this.organization = organization;
	}
	
	
}
