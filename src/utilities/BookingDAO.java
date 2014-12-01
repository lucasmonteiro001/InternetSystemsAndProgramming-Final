package utilities;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.Book;
//TODO EVERYTHING OF BOOKING
public class BookingDAO {

	/*
	 * We are going to use a CRUD to access the Objects at the Database
	 */
	public BookingDAO() {

	}

	/**
	 * This method adds the Booking object passed as parameter of the database.
	 * @see JDBCHelper 
	 * @param book
	 */
	public void addBooking(Book book) {
		JDBCHelper jdbc = new JDBCHelper();
		ArrayList<Object> param = new ArrayList<Object>();
		//param.add(new Date());
		//param.add(book.getBookingId());

		
		param.add(book.getFlightIds());
		param.add(book.getNumberOfSeats());
		param.add(book.getAccountId());
		param.add(book.getUserId());
		param.add(book.getTotalCost());


		try {
			// Insert in Booking table
			ResultSet rs1 = jdbc.insertDB(
					"INSERT INTO mmoraesg.booking (date_of_booking, flight_ids, number_of_seats, account_id, user_id, total_cost) VALUES (NOW(), ?, ?, ?, ?, ?);",
					param);
			
			int bookingId = getBookingId(param);
			
			ArrayList<Object> param_bf = new ArrayList<Object>();
			param_bf.add(bookingId);
			param_bf.add(book.getFlightIds());
			
			ResultSet rs2 = jdbc.insertDB("INSERT INTO mmoraesg.booking_flight (booking_id, flight_id) VALUES (?, ?)", param_bf);

			jdbc.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This method gets the booking id of the order.
	 * @see JDBCHelper 
	 * @param param
	 */
	public int getBookingId(ArrayList<Object> param) throws SQLException {
		
		JDBCHelper jdbc = new JDBCHelper();
		
		ResultSet rs1 = jdbc
				.queryDB(
						"SELECT id FROM mmoraesg.booking WHERE  "
						+ "flight_ids = ? AND number_of_seats = ? AND account_id = ? AND  user_id = ? AND total_cost = ?;",
						param);
		
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		ResultSetMetaData metaData = rs1.getMetaData();
		
		while (rs1.next()) {
		    Map<String, Object> columns = new LinkedHashMap<String, Object>();

		    for (int i = 1; i <= metaData.getColumnCount(); i++) {
		        columns.put(metaData.getColumnLabel(i), rs1.getObject(i));
		    }

		    rows.add(columns);
		}

		try {
			if (rows.size() != 0) {
				int returnBook;
				returnBook = ((int) rows.get(0).get("id"));
				
				return returnBook;
			}
			jdbc.conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
