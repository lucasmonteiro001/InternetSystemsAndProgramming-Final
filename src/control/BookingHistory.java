package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.json.JsonObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BookingHistoryModel;
import model.Client;
import model.User;
import utilities.BookingHistoryDAO;
import utilities.CharacterEscapingHelper;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class Booking
 */
public class BookingHistory extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookingHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/**
	 * This doGet calls the servlet BookingHistoryModel retrieve the information
	 * of the Booking History.
	 * 
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		CharacterEscapingHelper csh = new CharacterEscapingHelper();

		if (request.getParameter("action") != null) {

			String action = csh.forHTML((String) request.getParameter("action"));

			HttpSession session = request.getSession();

			Client client = (Client) session.getAttribute("client");

			BookingHistoryDAO dao = new BookingHistoryDAO();

			switch (action) {
			case "bookingHistoryList":
				try {
					int startPageIndex		= Integer.parseInt(csh.forHTML(request.getParameter("jtStartIndex")));
					int numRecordsPerPage	= Integer.parseInt(csh.forHTML(request.getParameter("jtPageSize")));
					       
					ArrayList<BookingHistoryModel> all = dao .getBookingHistory(client.getUser(), startPageIndex, numRecordsPerPage);
					
					//Get Total Record Count for Pagination
					int bookingListCount = dao.getBookingHistoryCount(client.getUser());

					Gson gson = new Gson();

					JsonElement element = gson.toJsonTree(all,
							new TypeToken<ArrayList<BookingHistoryModel>>() {}.getType());

					JsonArray jsonArray = element.getAsJsonArray();

					String listData = jsonArray.toString();
					
					// Return Json in the format required by jTable plugin
					listData = "{\"Result\":\"OK\",\"Records\":" + listData + ",\"TotalRecordCount\":" + bookingListCount +"}";
					
					response.getWriter().print(listData);

				} catch (Exception ex) {
					String error="{\"Result\":\"ERROR\",\"Message\":" + ex.getMessage() + "}";
					response.getWriter().print(error);
					ex.printStackTrace();

				}
				break;

			default:
				System.out.println("Passou direto");
				break;
			}
		} else {

			RequestDispatcher rd = request
					.getRequestDispatcher("bookinghistory.jsp");

			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	public JsonObject getBookingHistoryList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		BookingHistoryDAO bHDAO = new BookingHistoryDAO();

		JsonObject booking = null;

		Client client = (Client) session.getAttribute("client");

		booking = bHDAO.getBookingHistoryJson(client.getUser());

		return booking;
	}
}
