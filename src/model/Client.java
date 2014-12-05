package model;

public class Client {
	private User user;
	private Client client;
	public Client() {
		super();
	}
	public Client(User user, Client client) {
		super();
		this.user = user;
		this.client = client;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	
}
