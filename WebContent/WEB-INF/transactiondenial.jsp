<jsp:include page="../WEB-INF/classes/header.jsp"/>

	<h3>Sorry, your order could not be processed.</h3>

	<p>
		
		Thank your for choose <b>Air Minas</b>. <br/>
		
		<p><%= session.getAttribute("reason") %></p>
		<br/>
		Click <span onclick="window.history.back()" style="cursor:pointer"><a> here </a></span> to try again!
	</p><br>

	<button type="button" class="btn btn-primary btn-sm"  onClick="window.location='flightsearchquery.jsp'"><b>Home</b></button>
	
<jsp:include page="../WEB-INF/classes/bottom.jsp"/>