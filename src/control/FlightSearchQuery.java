package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Flight;
import utilities.FlightSearchDAO;

/**
 * Servlet implementation class FlightSearch
 */
public class FlightSearchQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlightSearchDAO searchFlightDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FlightSearchQuery() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/**
	 * This doPost calls the model Flight and sets the attribute if there is a result from the search. 
	 * Then it redirects for the appropriate page.
	 * 

	 */
	
	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Just can work if a session is there
		HttpSession session = request.getSession();
		
		String source 		= request.getParameter("source");
		String destination 	= request.getParameter("destination");
		String departure 	= request.getParameter("departure");

		ArrayList<Flight> flights = null;
		
		if (source != null && destination != null) {
			Flight flight = new Flight();
			
			session.setAttribute("source", source);
			session.setAttribute("destination", destination);
			
			flight.setDestination(destination);
			flight.setSource(source);
			
			if ((departure != null)) {
				
				session.setAttribute("departure", departure);
				searchFlightDao = new FlightSearchDAO();
				
				flight.setDeparture(new Date(departure));
	
				flights = searchFlightDao.readFlight(flight);
				request.setAttribute("flights", flights);
				session.setAttribute("flights", flights);
				
			}
			String originalURL = "WEB-INF/flightsearchresult.jsp";
			String encodedURL = response.encodeURL(originalURL);
			RequestDispatcher rd = request
					.getRequestDispatcher(encodedURL);
			rd.forward(request, response);
		}

	}
}
