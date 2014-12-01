package model;
/**
 * Model class for Account, containing all the data for the Account.
 * 
 * @author Lucas
 *
 */

public class Account {
	private int id;
	private int holderId;
	private int routingNumber;
	private double balance;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getHolderId() {
		return holderId;
	}


	public void setHolderId(int holderId) {
		this.holderId = holderId;
	}


	public int getRoutingNumber() {
		return routingNumber;
	}


	public void setRoutingNumber(int routingNumber) {
		this.routingNumber = routingNumber;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public Account() {
	}

}
