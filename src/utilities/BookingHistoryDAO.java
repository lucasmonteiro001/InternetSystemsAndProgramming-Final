package utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

import model.BookingHistoryModel;
import model.User;

/**
 * BookingHistoryDAO class to manage the Historic of bookings.
 * 
 * @author Mateus
 *
 */
public class BookingHistoryDAO {

	/*
	 * We are going to use a CRUD to access the Objects at the Database
	 */
	public BookingHistoryDAO() {

	}

	/**
	 * Get the booking history requested by the current user.
	 * 
	 * @param user
	 * @param numRecordsPerPage 
	 * @param startPageIndex 
	 * @return ArrayList <BookingHistoryModel>
	 */
	public ArrayList<BookingHistoryModel> getBookingHistory(User user, int startPageIndex, int numRecordsPerPage) {

		JDBCHelper jdbc = new JDBCHelper();

		getBookingHistoryJson(user);

		ArrayList<Object> param = new ArrayList<Object>();
		ArrayList<BookingHistoryModel> bookingHistory = new ArrayList<BookingHistoryModel>();

		param.add(user.getId());
		param.add(startPageIndex);
		param.add(numRecordsPerPage);

		ResultSet rs1 = jdbc
				.queryDB(
						"SELECT b.date_of_booking, f.source, f.destination, f.id, f.departure, f.arrival, b.total_cost "
								+ "FROM mmoraesg.user u, mmoraesg.booking b, mmoraesg.booking_flight bf, cse464.flights f "
								+ "WHERE  u.id = ? AND u.id = b.user_id AND b.id = bf.booking_id AND bf.flight_id = f.id "
								+ " ORDER BY b.date_of_booking DESC LIMIT ?, ?;",
						param);

		try {
			while (rs1 != null && rs1.next()) {

				BookingHistoryModel book = new BookingHistoryModel();

				book.setArrival(rs1.getTimestamp("arrival"));
				book.setDate_of_booking(rs1.getTimestamp("date_of_booking"));
				book.setDeparture(rs1.getTimestamp("departure"));
				book.setDestination(rs1.getString("destination"));
				book.setFlight_id(rs1.getString("id"));
				book.setSource(rs1.getString("source"));
				book.setTotal_cost(rs1.getString("total_cost"));

				bookingHistory.add(book);
			}
			jdbc.conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bookingHistory;
	}

	public int getBookingHistoryCount(User user) {
		
		int count = 0;
		
		try {
			
			JDBCHelper jdbc = new JDBCHelper();
			
			ArrayList<Object> param = new ArrayList<Object>();
			
			param.add(user.getId());
			
			ResultSet rs = jdbc
					.queryDB(
							"SELECT count(*) AS count "
							+ "FROM mmoraesg.user u, mmoraesg.booking b, mmoraesg.booking_flight bf, cse464.flights f "
							+ "WHERE u.id=? "
							+ "AND u.id = b.user_id "
							+ "AND b.id=bf.booking_id "
							+ "AND bf.flight_id = f.id;",
							param);
			
			rs.absolute(1);
			count=rs.getInt("count");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	/*
	 * I'm not using this function anymore
	 */
	public JsonObject getBookingHistoryJson(User user) {

		JDBCHelper jdbc = new JDBCHelper();

		ArrayList<Object> param = new ArrayList<Object>();

		param.add(user.getId());

		ResultSet rs1 = jdbc
				.queryDB(
						"SELECT b.date_of_booking, f.source, f.destination, f.id, f.departure, f.arrival, b.total_cost "
								+ "FROM mmoraesg.user u, mmoraesg.booking b, mmoraesg.booking_flight bf, cse464.flights f "
								+ "WHERE  u.id = ? AND u.id = b.user_id AND b.id = bf.booking_id AND bf.flight_id = f.id;",
						param);

		try {

			JsonBuilderFactory factory = Json.createBuilderFactory(null);

			List<JsonObject> booking_list = new ArrayList<JsonObject>();

			while (rs1 != null && rs1.next()) {

				JsonObject booking = factory
						.createObjectBuilder()
						.add("arrival", rs1.getTimestamp("arrival").toString())
						.add("date_of_booking",
								rs1.getTimestamp("date_of_booking").toString())
						.add("departure",
								rs1.getTimestamp("departure").toString())
						.add("destination", rs1.getString("destination"))
						.add("id", rs1.getString("id"))
						.add("source", rs1.getString("source"))
						.add("total_cost", rs1.getString("total_cost")).build();

				booking_list.add(booking);

			}

			JsonArrayBuilder builder = Json.createArrayBuilder();

			for (int i = 0; i < booking_list.size(); i++) {
				builder.add(booking_list.get(i));
			}

			JsonArray arr = builder.build();
			
			JsonObject booking_list_final = factory.createObjectBuilder()
					.add("Result", "OK")
					.add("Records", arr)
					.build();

			jdbc.conn.close();
			
			return booking_list_final;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;

	}

}
