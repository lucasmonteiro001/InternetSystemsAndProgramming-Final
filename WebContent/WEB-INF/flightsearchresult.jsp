<jsp:include page="../WEB-INF/classes/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="well well-sm span4">
	<h4 style="display: inline">Choose your flight</h4>

	<br><br>
	
	<p style="disply: block">Choose your destiny and when you want to go.</p>
	
	<c:if test="${not empty flights}">
		<form name="input" action="<c:url value='FlightSearchResult'></c:url>" method="post"
			role="form">
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
							<td><input type="radio" id="<c:out value="${flightBean.id}"/>"
								name="choosenFlight" value="<c:out value="${flightBean.id}"/>" required></td>
							<c:set var="flightInfo" value="${flightBean.departure},${flightBean.source},${flightBean.destination},${flightBean.id},${flightBean.departure},${flightBean.arrival}" />
							<c:forTokens items="${flightInfo}" delims="," var="flightSingleInfo">
								<td><c:out value="${flightSingleInfo}"/></td>
							</c:forTokens>
							<td>1</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	
			<label for="choosenFlight" class="error" style="display: none">Please
				select one option</label><br> <input type="button" class="btn"
				value="Back to Search page" onclick="window.history.back();">
				<input type="submit" class="btn btn-primary" name="send"
				value="View and Book" align="right">
		</form>
	</c:if>
	<c:if test="${empty flights}">
		<p style="disply: block">Sorry. We were unable to find any results
			for your search.</p>
		<input type="button" class="btn" value="Back to Search page"
			onclick="window.history.back();">
	</c:if>
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