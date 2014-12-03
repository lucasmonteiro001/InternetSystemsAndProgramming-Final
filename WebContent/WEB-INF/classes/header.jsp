<%@page import="utilities.PageUtilities"%>
<%@page import="java.util.*"%>
<%@ include file="head.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% PageUtilities pg = new PageUtilities(request); %>
<body>
	<div class="container">
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">

				<div class="navbar-header">
					<c:if test="${not empty sessionScope.user}">
						<a class="navbar-brand" href='<c:url value="flightsearchquery.jsp"></c:url>'><b>Air Minas</b></a>
					</c:if>
					<c:if test="${empty sessionScope.user}">
						<a class="navbar-brand" href="login.jsp"><b>Air Minas</b></a>
					</c:if>
				</div>

				<c:if test="${not empty sessionScope.user}">
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						
							<% if (!pg.getCurrPage().contains("flightsearchquery.jsp")) {%>
								<li class="divider-vertical"></li>
								<li><a href='<c:url value="flightsearchquery.jsp"></c:url>'> Flight Search </a></li>
							<%}%>
						<li class="divider-vertical"></li>
						<li class="active"><a href="#"> 
						<% if (pg.getSystemPages().get(pg.getCurrPage()) != null
								&& !pg.getCurrPage().contains("bookinghistory.jsp")
								&& !pg.getCurrPage().contains("shoppingcart.jsp")) { %> 
							<%=pg.getSystemPages().get(pg.getCurrPage())%> 
						<% } else if (pg.getSystemPages().get(pg.getCurrPage()) != null
								&& pg.getCurrPage().contains("bookinghistory.jsp")){%>
							<img style="width: 1.4em;height: 1.4em;"src="style/booking-icon.png">&nbsp; <%=pg.getSystemPages().get(pg.getCurrPage())%>
						<% } else if (pg.getSystemPages().get(pg.getCurrPage()) != null
								&& pg.getCurrPage().contains("shoppingcart.jsp")){%>
							<span style="width:1.4em;height:1.4em;" class="glyphicon glyphicon-shopping-cart"></span>&nbsp; <%=pg.getSystemPages().get(pg.getCurrPage())%>
						<% } %>
						</a></li>
							<% if (!pg.getCurrPage().equals("bookinghistory.jsp")
										&& !pg.getCurrPage().contains("login.jsp")
										&& !pg.getCurrPage().contains("registration.jsp")) {
							%>
								<li class="divider-vertical"></li>
								<li><a href='<c:url value="BookingHistory"></c:url>'><img style="width: 1.4em;height: 1.4em;"src="style/booking-icon.png">&nbsp; Booking history </a></li>
							<%}%>
							<% if(!pg.getCurrPage().contains("shoppingcart.jsp")) {%>
								<li class="divider-vertical"></li>
								<li><a href='<c:url value="ShoppingCart"></c:url>'><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp; Shopping Cart</a></li>
							<%}%>

					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><img class="logo" src="style/background-mg.png"></li>
							<% if (!pg.getCurrPage().contains("login.jsp")
										&& !pg.getCurrPage().contains("registration.jsp")) {
							%>
						
						<li class="divider-vertical"></li>
								
						<li><a href='<c:url value="Logout"></c:url>'><span
								class="glyphicon glyphicon-off black">&nbsp;</span> ${user.email} </a></li>
						<% } %>
						
					</ul>
				</div>
				</c:if>
			</div>
		</nav>