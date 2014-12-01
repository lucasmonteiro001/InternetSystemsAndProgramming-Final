<jsp:useBean id="flightBean" class="model.Flight" scope="session"/>
<jsp:useBean id="booking" class="model.Book" scope="session"/>

<jsp:include page="../WEB-INF/classes/header.jsp" />

<h3>Order received! Your flight has been reserved. See you soon!</h3>

<p>
	Thank your for flying <b>Air Minas</b>. Click <a
		href="flightsearchquery.jsp"> here </a> to continue flying!
</p>
<br>

<p>
<ul>
	<h4>Your order: </h4>
	<li>Departure: <jsp:getProperty property="departure" name="flightBean" /></li>
	<li>Source: <jsp:getProperty property="source" name="flightBean" /></li>
	<li>Destination: <jsp:getProperty property="destination" name="flightBean" /></li>
	<li>Flight: <jsp:getProperty property="id" name="flightBean" /></li>
	<li>Arrival: <jsp:getProperty property="arrival" name="flightBean" /></li>
	<li>Number of Stops: 1</li>
	<li>Total cost: $<%=session.getAttribute("totalCostFormatted")%></li>
	<li>Number of seats: <jsp:getProperty property="numberOfSeats" name="booking" /></li>
</ul>

<div class="well well-sm span4">
    <h5>Please provide the passenger details: </h5>
	<form class="form-horizontal form-group-sm" role="form" name="input" action="FlightSearchQuery" method="post" id="input">
		
		<div class="form-group">
  		<label class="col-sm-2 control-label" for="passengerName">Name</label>
  			<div class="col-sm-10">
  				<input type="textfield" class="form-group" id="passengerName" name="passengerName" required>
  			</div>
		</div>
		
		<div class="form-group">
  		<label class="col-sm-2 control-label" for="age">Age</label>
  			<div class="col-sm-10">
  				<input type="number" class="form-group" id="age" name="age" min=0 max=115 required>
  			</div>
		</div>
		
		<div class="form-group">
  			<label class="col-sm-2 control-label" for="sex">Sex</label>
  			<div class="col-sm-10">
				<select class="form-group" id="sex" name="sex">
		    		<option value="male">Male</option>
		    		<option value="female">Female</option>
		    		<option value="not-specified">Not specified</option>
		    	</select>
		    </div>
    	</div>
	</form>    
</div>

</p>

<button type="button" class="btn btn-primary btn-sm"
	onClick=verifyData()>
	<b>Print page</b>
</button>
<button type="button" class="btn btn-primary btn-sm"
	onClick="window.location='flightsearchquery.jsp'">
	<b>Home</b>
</button>

<script>

	function verifyData() {
		var passengerName = document.forms["input"]["passengerName"].value;
		var age = document.forms["input"]["age"].value;
		var sex = document.forms["input"]["sex"].value;
		
		if (passengerName != null && passengerName != ""
				&& age != null && age != "" 
				&& sex != null && sex != "") {
			window.print();
		}
		else {
			alert("Please, provide the information required!");
		}
	}

</script>

<jsp:include page="../WEB-INF/classes/bottom.jsp" />