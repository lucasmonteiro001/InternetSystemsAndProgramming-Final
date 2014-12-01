package model;

import java.util.Date;

/**
 * Model class for Fight, containing all the data for the Flight.
 * 
 * @author Mateus
 *
 */

public class Flight {
	private int id;
	private int plane;
	private String operator;
	private String source;
	private String destination;
	private int firstClassReserved;
	private int businessClassReserved;
	private int economyClassReserved;
	private Date departure;
	private Date arrival;
	public Flight() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlane() {
		return plane;
	}
	public void setPlane(int plane) {
		this.plane = plane;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
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
	public int getFirstClassReserved() {
		return firstClassReserved;
	}
	public void setFirstClassReserved(int firstClassReserved) {
		this.firstClassReserved = firstClassReserved;
	}
	public int getBusinessClassReserved() {
		return businessClassReserved;
	}
	public void setBusinessClassReserved(int businessClassReserved) {
		this.businessClassReserved = businessClassReserved;
	}
	public int getEconomyClassReserved() {
		return economyClassReserved;
	}
	public void setEconomyClassReserved(int economyClassReserved) {
		this.economyClassReserved = economyClassReserved;
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
	
	

}
