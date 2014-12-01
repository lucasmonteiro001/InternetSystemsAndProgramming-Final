package model;

import java.util.ArrayList;
import java.util.Date;
/**
 * Model class for Book, containing all the data for the Book.
 * 
 * @author Mateus
 *
 */

public class Book {

	private int id;
	private int bookingId;
	private Date dateOfBooking;
	private int flightIds;
	private int numberOfSeats;
	private int userId;
	private double totalCost;
	private int accountId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Date getDateOfBooking() {
		return dateOfBooking;
	}

	public void setDateOfBooking(Date dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}

	public int getFlightIds() {
		return flightIds;
	}

	public void setFlightIds(int flightIds) {
		this.flightIds = flightIds;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Book() {
		super();
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getAccountId() {
		// TODO Auto-generated method stub
		return accountId;
	}

}
