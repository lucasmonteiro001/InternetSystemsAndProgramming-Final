<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Flight"%>
<%@page import="model.Book"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>

<jsp:include page="../WEB-INF/classes/header.jsp" />


<div class="well well-sm span4">
	<h4 style="display: inline">Shopping Cart</h4>

	<br> <br>
	<p style="disply: block">Flights you have chosen.</p>

	<%
		double aggregateCost = 0;
		String aggregateCostFormatted = "Empty Shopping Cart. (0.00)";
		ArrayList<Book> fs = (ArrayList<Book>) session
				.getAttribute("shoppingCart");
		if (fs != null) {
	%>
	<table class="table table-hover" style="background-color: white">
		<thead>
			<tr>
				<th>Flight</th>
				<th>Seats</th>
				<th>Costs</th>
			</tr>

			<%
				Iterator<Book> it = fs.iterator();
					while (it.hasNext()) {
						Book book = (Book) it.next();
			%>
			<tr>
				<td><%=book.getFlightIds()%></td>
				<td><%=book.getNumberOfSeats()%></td>
				<td><%=book.getTotalCost()%></td>
			</tr>
			<%
				aggregateCost += book.getTotalCost();
						NumberFormat numberFormatter = NumberFormat
								.getNumberInstance(new Locale("en_US"));
						aggregateCostFormatted = numberFormatter
								.format(aggregateCost);
					}
			%>
			<tr>
				<td><b>Total</b></td>
				<td><b><%=aggregateCostFormatted%></b></td>
				<td><a href="confirmbooking.jsp"><input type="button"
						class="btn btn-primary btn-sm" name="send"
						value="Proceed to Checkout" align="right"></a></td>
			</tr>
			<%
				} else {
			%>
			Your shopping cart is empty.
			<%
				}
			%>

			<jsp:include page="../WEB-INF/classes/bottom.jsp" />