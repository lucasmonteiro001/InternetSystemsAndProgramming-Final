<%@page import="utilities.PageUtilities"%>
<%@page import="java.util.*"%>
<%@ include file="head.jsp"%>

<% if (session.getAttribute("user") != null) { %>
	<jsp:useBean id="user" class="model.User" scope="session" />
<% } %>

<% PageUtilities pg = new PageUtilities(request); %>

<body>
	<div class="container">
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">

				<div class="navbar-header">
					<% if(session.getAttribute("user") != null) {%>
						<a class="navbar-brand" href="flightsearchquery.jsp"><b>Air Minas</b></a>
					<%} else {%>
						<a class="navbar-brand" href="login.jsp"><b>Air Minas</b></a>
					<%}%>
				</div>


				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<%
							if (session.getAttribute("user") != null
									&& !pg.getCurrPage().equals("flightsearchquery.jsp")) {
						%>
						<li class="divider-vertical"></li>
						<li><a href="flightsearchquery.jsp"> Flight Search </a></li>
						<%}%>
						<li class="divider-vertical"></li>
						<li class="active"><a href="#"> 
						<% if (pg.getSystemPages().get(pg.getCurrPage()) != null
								&& !pg.getCurrPage().equals("bookinghistory.jsp")
								&& !pg.getCurrPage().equals("shoppingcart.jsp")) { %> 
							<%=pg.getSystemPages().get(pg.getCurrPage())%> 
						<% } else if (pg.getSystemPages().get(pg.getCurrPage()) != null
								&& pg.getCurrPage().equals("bookinghistory.jsp")){%>
							<img style="width: 1.4em;height: 1.4em;"src="style/booking-icon.png">&nbsp; <%=pg.getSystemPages().get(pg.getCurrPage())%>
						<% } else if (pg.getSystemPages().get(pg.getCurrPage()) != null
								&& pg.getCurrPage().equals("shoppingcart.jsp")){%>
							<span style="width:1.4em;height:1.4em;" class="glyphicon glyphicon-shopping-cart"></span>&nbsp; <%=pg.getSystemPages().get(pg.getCurrPage())%>
						<% } %>
						</a></li>
						<!-- Check if there's a session -->
						<%
							if (session.getAttribute("user") != null
									&& !pg.getCurrPage().equals("bookinghistory.jsp")
									&& !pg.getCurrPage().equals("login.jsp")
									&& !pg.getCurrPage().equals("registration.jsp")) {
						%>
							<li class="divider-vertical"></li>
							<li><a href="BookingHistory"><img style="width: 1.4em;height: 1.4em;"src="style/booking-icon.png">&nbsp Booking history </a></li>
						<%}%>
						<% if(session.getAttribute("user") != null
								&& !pg.getCurrPage().equals("shoppingcart.jsp")) {%>
							<li class="divider-vertical"></li>
							<li><a href="ShoppingCart"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp; Shopping Cart</a></li>
						<%}%>

					</ul>
					<ul class="nav navbar-nav navbar-right">
						<!-- <li><img class="logo" src="style/airplane.png"></li> -->
						<li><img class="logo" src="style/background-mg.png"></li>
						<!-- Check if there's a session -->
						<%
							if (session.getAttribute("user") != null
									&& !pg.getCurrPage().equals("login.jsp")
									&& !pg.getCurrPage().equals("registration.jsp")) {
						%>
						<li class="divider-vertical"></li>
								
						<li><a href="Logout"><span
								class="glyphicon glyphicon-off black">&nbsp</span> <jsp:getProperty
									property="email" name="user" /> </a></li>
						<%
							}
						%>
					</ul>
				</div>
			</div>
		</nav>