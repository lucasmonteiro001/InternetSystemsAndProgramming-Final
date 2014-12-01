package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Flight;

/**
 * Servlet implementation class FlightSearchResult
 */
public class FlightSearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FlightSearchResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @see FlightSearchResult#getChoosenFlight(HttpServletRequest)
	 *      
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		Flight flight = getChoosenFlight(request);
		session.setAttribute("flightChoosen", flight.getId());
		session.setAttribute("flightBean", flight);

		RequestDispatcher rd = request
				.getRequestDispatcher("WEB-INF/reviewandbook.jsp");
		rd.forward(request, response);

	}

	/**
	 * This method returns a flight if it exists.
	 * @param request
	 * @return tempFlight
	 */
	public Flight getChoosenFlight(HttpServletRequest request) {

		@SuppressWarnings("unchecked")
		ArrayList<Flight> flights = (ArrayList<Flight>) session
				.getAttribute("flights");
		@SuppressWarnings("rawtypes")
		Iterator it = flights.iterator();

		while (it.hasNext()) {
			Flight tempFlight = (Flight) it.next();
			if (Integer.parseInt(request.getParameter("choosenFlight")) == tempFlight.getId()) {
				return tempFlight;
			}
		}
		return null;
	}

}
