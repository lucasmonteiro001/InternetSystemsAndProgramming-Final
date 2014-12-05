package model;

public class Organization {
	private int id;
	private String name;
	private String address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Organization() {
		super();
	}
	public Organization(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
}
