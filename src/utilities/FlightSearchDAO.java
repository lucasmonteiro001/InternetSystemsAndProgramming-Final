package utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.Flight;
import model.User;
/**
 * FlightSearchDAO class to manipulate the Flights in a search.
 * @author Lucas
 *
 */
//TODO update user
public class FlightSearchDAO {

	/*
	 * We are going to use a CRUD to access the Objects at the Database
	 */
	public FlightSearchDAO() {

	}
/**
 * This method reads the flights which best matches the search for flights.
 * @param flight
 * @return ArrayList<Flight>
 */
	public ArrayList<Flight> readFlight(Flight flight) {
		// (String host, String db, String user, String password){
		JDBCHelper jdbc = new JDBCHelper();
		ArrayList<Object> param = new ArrayList<Object>();
		/**
		 * param.add("Hello"); param.add(35767); param.add(4.0);
		 * param.add(Timestamp.valueOf("2014-09-30 11:41:00"));
		 */

		param.add(flight.getSource());
		param.add(flight.getDestination());

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");

		String dateDeparture = sdf.format(flight.getDeparture());
		String query = "SELECT * FROM cse464.flights WHERE cse464.flights.source = ? "
				+ "and cse464.flights.destination = ? "
				+ "AND DATE(cse464.flights.departure) like '"
				+ dateDeparture
				+ "%';";

		ResultSet rs1 = jdbc.queryDB(query, param);

		try {
			if (rs1 != null) {
				ArrayList<Flight> flightArray = new ArrayList<Flight>();
				while (rs1.next()) {
					Flight returnFlight = new Flight();
					returnFlight.setId(rs1.getInt("id"));
					returnFlight.setPlane(rs1.getInt("plane"));
					returnFlight.setOperator(rs1.getString("operator"));
					returnFlight.setSource(rs1.getString("source"));
					returnFlight.setDestination(rs1.getString("destination"));
					returnFlight.setBusinessClassReserved(rs1
							.getInt("business_reserved"));
					returnFlight.setEconomyClassReserved(rs1
							.getInt("economy_reserved"));
					returnFlight.setFirstClassReserved(rs1
							.getInt("first_class_reserved"));
					returnFlight.setDeparture(rs1.getTimestamp("departure"));
					returnFlight.setArrival(rs1.getTimestamp("arrival"));

					flightArray.add(returnFlight);

				}
				return flightArray;
			}
			jdbc.conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * This method reads multiple flights which best matches the search for flights.
	 * @param flight
	 * @return ArrayList<Flight>
	 */
	public ArrayList<Flight> readMultipleFlights(Flight flight) {
		// (String host, String db, String user, String password){
		JDBCHelper jdbc = new JDBCHelper();
		ArrayList<Object> param = new ArrayList<Object>();
		/**
		 * param.add("Hello"); param.add(35767); param.add(4.0);
		 * param.add(Timestamp.valueOf("2014-09-30 11:41:00"));
		 */
		param.add(flight.getSource());
		param.add(flight.getDestination());
		param.add(flight.getDeparture());
		param.add(flight.getArrival());

		ResultSet rs1 = jdbc
				.queryDB(
						"SELECT * FROM cse464.flights WHERE source = ? AND destination = ? AND departure = ? AND arrival = ?;",
						param);

		try {
			if (rs1 != null) {
				ArrayList<Flight> flightArray = new ArrayList<Flight>();
				while (rs1.next()) {
					Flight returnFlight = new Flight();
					returnFlight.setArrival(rs1.getDate("arrival"));
					returnFlight.setBusinessClassReserved(rs1
							.getInt("business_reserved"));
					returnFlight.setDeparture(rs1.getDate("departure"));
					returnFlight.setDestination(rs1.getString("destination"));
					returnFlight.setEconomyClassReserved(rs1
							.getInt("economy_reserved"));
					returnFlight.setFirstClassReserved(rs1
							.getInt("first_class_reserved"));
					returnFlight.setOperator(rs1.getString("operator"));
					returnFlight.setPlane(rs1.getInt("plane"));
					returnFlight.setSource(rs1.getString("source"));
					flightArray.add(returnFlight);
				}

				return flightArray;
			}
			jdbc.conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
