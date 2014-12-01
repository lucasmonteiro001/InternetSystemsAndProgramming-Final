package utilities;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class PageUtilities {
	private String currPage;
	private Map<String, String> systemPages = new HashMap<String, String>();

	public PageUtilities(HttpServletRequest request) {
		this.currPage = checkCurrPage(request);
		this.systemPages = setSystemPages();
	}

	
	public String getCurrPage() {
		return this.currPage;
	}
	
	public Map<String, String> getSystemPages() {
		return this.systemPages;
	}
	
	String checkCurrPage(HttpServletRequest request) {

		String curUrl = request.getRequestURL() + "";
		String[] str = curUrl.split("/");
		String currPage = str[str.length - 1];

		return currPage;
	}

	Map<String, String> setSystemPages() {
		Map<String, String> systemPages = new HashMap<String, String>();
		
		systemPages.put("login.jsp", "Login");
		systemPages.put("flightsearchquery.jsp", "Flight Search");
		systemPages.put("transaction.jsp", "Transaction");
		systemPages.put("bookinghistory.jsp", "Booking History");
		systemPages.put("registration.jsp", "Registration");
		systemPages.put("reviewandbook.jsp", "Review and Book");
		systemPages.put("flightsearchresult.jsp",
				"Flights that match your search");
		systemPages.put("transactionconfirmationpage.jsp",
				"Transaction confirmation");
		systemPages.put("shoppingcart.jsp", "Shopping Cart");
		systemPages.put("confirmbooking.jsp", "Confirm");
		
		return systemPages;
	}

}
