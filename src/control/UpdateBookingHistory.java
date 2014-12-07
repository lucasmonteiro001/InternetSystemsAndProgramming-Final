package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Book;
import model.Client;
import utilities.AccountDAO;
import utilities.BookingDAO;
import utilities.JsonHelper;

/**
 * Servlet implementation class UpdateBookingHistory
 */
public class UpdateBookingHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBookingHistory() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setContentType("application/json");
		
		
		HttpSession session 	= request.getSession();
		Client client 				= (Client) session.getAttribute("client");
		
		BookingDAO bookingDao	= new BookingDAO();
		JsonHelper js 			= new JsonHelper();
		Account account			= new Account();
		AccountDAO accountDao 	= new AccountDAO();
		
		try {
			StringBuilder sb = new StringBuilder();
		    BufferedReader reader = request.getReader();
		    try {
		        String line;
		        while ((line = reader.readLine()) != null) {
		            sb.append(line).append('\n');
		        }
		    } finally {
		        reader.close();
		    }
		    
		    String[] requestQuery = sb.toString().split("&");
		    
		    int accountHolderId 		= Integer.parseInt((requestQuery[0].split("="))[1]);
		    int accountRoutingNumber 	= Integer.parseInt((requestQuery[1].split("="))[1]);
		    
			account.setHolderId(accountHolderId);
			account.setRoutingNumber(accountRoutingNumber);

			account = accountDao.readAccount(account);
			
			@SuppressWarnings("unchecked")
			ArrayList<Book> shoppingCart = (ArrayList<Book>) session.getAttribute("shoppingCart");
			
			for (Book booking : shoppingCart) {
				booking.setAccountId(account.getId());
				booking.setUserId(client.getUser().getId());
				bookingDao.addBooking(booking);
			}
			
			session.setAttribute("shoppingCart", new ArrayList<BookingHistory>());
			
			String return_msg = js.getJsonFormatted(true,
					"Thanks! Your transaction was successfully recorded!");
			response.getWriter().print(return_msg);
			
		} catch (Exception e) {
			String return_msg = js.getJsonFormatted(true,
					"Failed to update booking history!");
			response.getWriter().print(return_msg);
			
			e.printStackTrace();
		}
	}

}
