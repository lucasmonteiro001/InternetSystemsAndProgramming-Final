package model;

import java.util.Date;
/**
 * Model class for BookingHistoryModel, containing all the data for the Booking.
 * 
 * @author Lucas
 *
 */


public class BookingHistoryModel {
	
	private Date date_of_booking;
	private String source;
	private String destination;
	private String flight_id;
	private Date departure;
	private Date arrival;
	private String total_cost;
	public Date getDate_of_booking() {
		return date_of_booking;
	}
	public void setDate_of_booking(Date date_of_booking) {
		this.date_of_booking = date_of_booking;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(String flight_id) {
		this.flight_id = flight_id;
	}
	public Date getDeparture() {
		return departure;
	}
	public void setDeparture(Date departure) {
		this.departure = departure;
	}
	public Date getArrival() {
		return arrival;
	}
	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}
	public String getTotal_cost() {
		return total_cost;
	}
	public void setTotal_cost(String total_cost) {
		this.total_cost = total_cost;
	}
	
	
	
	
}
