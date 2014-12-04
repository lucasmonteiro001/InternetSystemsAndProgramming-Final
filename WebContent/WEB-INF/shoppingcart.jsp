<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../WEB-INF/classes/header.jsp" />


<div class="well well-sm span4">
	<h4 style="display: inline">Shopping Cart</h4>

	<br> <br>
	<p style="disply: block">Flights you have chosen.</p>

	<table class="table table-hover" style="background-color: white">
		<c:if test="${not empty shoppingCart}">
			<thead>
				<tr>
					<th>Flight</th>
					<th>Seats</th>
					<th>Costs</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="booking" items="${shoppingCart}">
					<tr>
						<td><c:out value="${booking.flightIds}"/></td>
						<td><c:out value="${booking.numberOfSeats}"/></td>
						<td><c:out value="${booking.totalCost}"/></td>
					</tr>
				</c:forEach>
				<tr>
					<td><b>Total</b></td>
					<td><b><c:out value="${totalCost}"/></b></td>
					<td><a href='<c:url value="confirmbooking.jsp"></c:url>'><input type="button"
							class="btn btn-primary btn-sm" name="send"
							value="Proceed to Checkout" align="right"></a></td>
				</tr>
		</tbody>
		</c:if>
		<c:if test="${empty shoppingCart}">
			Your shopping cart is empty.
		</c:if>
		</table>
</div>
<jsp:include page="../WEB-INF/classes/bottom.jsp" />