<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Flight"%>

<jsp:include page="../WEB-INF/classes/header.jsp" />

<div class="well well-sm span4">
	<h4 style="display: inline">Choose your flight</h4>

	<br> <br>
	<p style="disply: block">Choose your destiny and when you want to
		go.</p>

	<form name="input" action="FlightSearchResult" method="post"
		role="form">

		<%
			ArrayList <Flight> fs = (ArrayList <Flight>) session.getAttribute("flights");
			if (fs.size() != 0) {
		%>
		<table class="table table-hover" style="background-color: white">
			<thead>
				<tr>
					<th>Select</th>
					<th>Date</th>
					<th>From</th>
					<th>To</th>
					<th>Flight Number</th>
					<th>Departure Time</th>
					<th>Arrival Time</th>
					<th>Number of Stops</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="flightBean" items="${requestScope['flights']}">
					<tr>
						<td><input type="radio" id="${flightBean.id}"
							name="choosenFlight" value="${flightBean.id}" required></td>
						<td><c:out value="${flightBean.departure}" /></td>
						<td><c:out value="${flightBean.source}" /></td>
						<td><c:out value="${flightBean.destination}" /></td>
						<td><c:out value="${flightBean.id}" /></td>
						<td><c:out value="${flightBean.departure}" /></td>
						<td><c:out value="${flightBean.arrival}" /></td>
						<td>1</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>

		<label for="choosenFlight" class="error" style="display: none">Please
			select one option</label><br> <input type="button" class="btn"
			value="Back to Search page" onclick="window.history.back();">
		<!-- <input type="button" class="btn" value="See booking history page"
			onclick="window.location='BookingHistory';">  -->
			<input type="submit" class="btn btn-primary" name="send"
			value="View and Book" align="right">
	</form>
	<%
		} else {
	%>
	<p style="disply: block">Sorry. We were unable to find any results
		for your search.</p>

	<input type="button" class="btn" value="Back to Search page"
		onclick="window.history.back();"> <!-- <input type="button"
		class="btn" value="See booking history page"
		onclick="window.location='BookingHistory';"> -->
	<%
		}
	%>

</div>

<script>
	$("form").validate({
		rules : {
			choosenFlight : {
				required : true
			}
		}
	});
</script>

<jsp:include page="../WEB-INF/classes/bottom.jsp" />