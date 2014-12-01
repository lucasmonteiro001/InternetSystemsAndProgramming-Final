package control;

import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Book;
import model.Flight;
import model.User;

import org.json.JSONException;
import org.json.JSONObject;

import utilities.JsonHelper;

/**
 * Servlet implementation class ReviewAndBook
 */
public class ReviewAndBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private final double TX_ECONOMY_SEAT = 123.43;
	private final double TX_FIRST_CLASS_SEAT = 550;
	private final double TX_BUSINESS_CLASS_SEAT = 325.56;
	private ArrayList <Book> shoppingCart = null;
	private Flight flight;
	private int economyClass = 0;
	private int firstClass = 0;
	private int businessClass = 0;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewAndBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet (HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("application/json");
		JsonHelper js 		= new JsonHelper();
		session 			= request.getSession();
		Book book 			= new Book ();
		User user 			= (User) session.getAttribute("user");
		double totalCost 	= 0;
		double cost			= 0;
		shoppingCart = new ArrayList <Book> ();
		String json 		= request.getParameter("json");
		JSONObject jObj;
		String return_msg = "";
		try {
		jObj = new JSONObject(json);
		String classe = jObj.get("classe").toString();
		String numberOfSeats = jObj.get("numberOfSeats").toString();
		session.setAttribute("classe", classe);
		session.setAttribute("numberOfSeats", numberOfSeats);
		

		if (session.getAttribute("shoppingCart") != null) {
	    	shoppingCart = (ArrayList<Book>)  session.getAttribute("shoppingCart");
	    }

		if (classe.equals("economy"))
			cost = Integer.parseInt(numberOfSeats)*TX_ECONOMY_SEAT;
		else if (classe.equals("business"))
			cost = Integer.parseInt(numberOfSeats)*TX_BUSINESS_CLASS_SEAT;
		else if (classe.equals("firstclass"))
			cost = Integer.parseInt(numberOfSeats)*TX_FIRST_CLASS_SEAT;
		
		flight = (Flight) session.getAttribute("flightBean");
		book.setUserId(user.getId());
		book.setNumberOfSeats(Integer.parseInt(numberOfSeats));
		book.setFlightIds(flight.getId());
		book.setTotalCost(cost);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		String date_s = df.format(calobj.getTime());
		Date date = df.parse(date_s);
		
		book.setDateOfBooking(date);
		
		
		shoppingCart.add(book);
		Iterator<Book> it = shoppingCart.iterator();
		
		
		
		while (it.hasNext()) {
			totalCost += ((Book) it.next()).getTotalCost();
			System.out.println (totalCost);
		}
			session.setAttribute("totalCost", totalCost);
		    session.setAttribute("shoppingCart", shoppingCart);
		    return_msg = js.getJsonFormatted(true,
					"Ticket added to your shopping cart!");
		    response.getWriter().print(return_msg);
		    
		} catch (JSONException e) {
			return_msg = js.getJsonFormatted(false,
					"Catch de JsonException");
			response.getWriter().print(return_msg);
			e.printStackTrace();
		} catch (NumberFormatException e) {
			return_msg = js.getJsonFormatted(false,
					"Catch de NumberFormatException");
			response.getWriter().print(return_msg);
		} catch (Exception e) {
			return_msg = js.getJsonFormatted(false,
					"Catch de Exception e");
			response.getWriter().print(return_msg);
			e.printStackTrace();
		}

    }
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();

		flight = (Flight) session.getAttribute("flightBean");
		economyClass = (int) session.getAttribute("economyClass");
		businessClass = (int) session.getAttribute("businessClass");
		firstClass = (int) session.getAttribute("firstClass");

		double totalCost = 0;
		if ((flight.getBusinessClassReserved() - businessClass >= 0)
				&& (flight.getEconomyClassReserved() - economyClass >= 0)
				&& (flight.getFirstClassReserved() - firstClass >= 0)) {

			totalCost = economyClass * TX_ECONOMY_SEAT + businessClass
					* TX_BUSINESS_CLASS_SEAT + firstClass * TX_FIRST_CLASS_SEAT;
			NumberFormat formatter = NumberFormat.getCurrencyInstance();

			session.setAttribute("totalCostFormatted",
					formatter.format(totalCost));
			session.setAttribute("totalCost", totalCost);
			session.setAttribute("totalSeats", firstClass + businessClass
					+ economyClass);

			RequestDispatcher rd = request
					.getRequestDispatcher("WEB-INF/transaction.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request
					.getRequestDispatcher("WEB-INF/reviewandbook.jsp");
			rd.forward(request, response);
		}

	}
}
