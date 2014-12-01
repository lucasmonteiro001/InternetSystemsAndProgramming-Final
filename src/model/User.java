package model;

import java.util.Date;

/**
 * Model class for user, containing name and password.
 * 
 * @author Mateus
 *
 */

public class User {
	private String name;
	private String password;
	private String email;
	private Date dateOfBirth;
	private int id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String email, String password, Date dateOfBirth) {
		super();
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
	}

	public User (String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User() {
		super();
	}

}
