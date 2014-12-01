package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Book;
import model.User;

import org.json.JSONObject;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		
		HttpSession session 	= request.getSession();
		User user 				= (User) session.getAttribute("user");
		
		BookingDAO bookingDao	= new BookingDAO();
		JsonHelper js 			= new JsonHelper();
		Account account			= new Account();
		AccountDAO accountDao 	= new AccountDAO();
		
		try {
			String json 	= request.getParameter("json");
			JSONObject jObj = new JSONObject(json);
			
			int hId 	= Integer.parseInt(jObj.get("accountHolderId").toString());
			int rNum 	= Integer.parseInt(jObj.get("accountRoutingNumber").toString());

			account.setHolderId(hId);
			account.setRoutingNumber(rNum);

			account = accountDao.readAccount(account);
			
			@SuppressWarnings("unchecked")
			ArrayList<Book> shoppingCart = (ArrayList<Book>) session.getAttribute("shoppingCart");
			
			for (Book booking : shoppingCart) {
				booking.setAccountId(account.getId());
				booking.setUserId(user.getId());
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
