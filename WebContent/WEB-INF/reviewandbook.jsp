<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="flightBean" class="model.Flight" scope="session" />

<jsp:include page="../WEB-INF/classes/header.jsp" />

<form class="form-horizontal" role="form" name="input" action="ReviewAndBook">
	
	<h3>You have chosen this flight</h3>
	
	<div class="alert alert-success alert-dismissible success-msg" role="alert" style="display:none"> 
		<div class="success"></div>
	</div>
	
	<div class="well well-sm span4">
	
	<div class="form-group">
		<table class="table table-hover" style="background-color: white">
			<thead>
				<tr>
					<th>Date</th>
					<th>From</th>
					<th>To</th>
					<th>Flight Number</th>
					<th>Departure Time</th>
					<th>Arrival Time</th>
					<th>Number of Stops</th>
					<th>Class</th>
					<th>Number of Seats</th>
				</tr>
			</thead>
			<tbody>
				<tr>
				<tr>
					<td><jsp:getProperty property="departure" name="flightBean"/></td>
					<td><jsp:getProperty property="source" name="flightBean" /></td>
					<td><jsp:getProperty property="destination" name="flightBean" /></td>
					<td><jsp:getProperty property="id" name="flightBean" /></td>
					<td><jsp:getProperty property="departure" name="flightBean" /></td>
					<td><jsp:getProperty property="arrival" name="flightBean" /></td>
					<td>1</td>
					<td><select class="form-group" id="class" name="class">
							<option value="economy">Economy</option>
							<option value="business">Business</option>
							<option value="firstclass">First Class</option>
					</select></td>
					<td>
						<input type="number" min="1" max="10" id="number_of_seats" name="number_of_seats" required>
					</td>
					</div>

				</tr>
				</tr>
			</tbody>
		</table>
		<br>
	</div>

	<input type="button" class="btn btn-sm" value="Back to see the flights"
		onclick="window.history.back();"> <input type="submit"
		class="btn btn-primary btn-sm" name="send" value="Add to shopping cart"
		align="right">
</form>
<script>
	$("form").submit(function(event) {

		
		var numberOfSeats = $("#number_of_seats").val();
		var classe = $("#class").val();

		var jsonDataObject = new Object();
		jsonDataObject.numberOfSeats = numberOfSeats;
		jsonDataObject.classe = classe;
		
		var jsonData = JSON.stringify(jsonDataObject);

		
		$.ajax({
			url : "ReviewAndBook",
			type : "GET",
			data : {action:"export",json:jsonData},
			contentType: 'application/json',
			cache: false,
			success: function(data) {
				if(data.success == "true")
					
					showDiv(data.data);
				else {
					clearError();
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			}
			
		});
		
		event.preventDefault();
	});
	
	function clearError() {
		$(".error").hide();
	};
	 
	function showDiv(msg) {
		$(".success").html(msg);
		$(".success-msg").show();
		$(".success-msg").fadeOut(5000);
	};
	
</script>

<jsp:include page="../WEB-INF/classes/bottom.jsp" />