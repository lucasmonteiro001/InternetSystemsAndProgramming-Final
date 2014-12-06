<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty shoppingCart}">
	<% response.sendRedirect(response.encodeURL("flightsearchquery.jsp")); %>
</c:if>

<c:forEach var="cookieVal" items="${cookie}">
	<c:set var="cookiename" value="${cookieVal.key}"/>
	<c:if test="${cookiename == 'JSESSIONID'}">
		<c:set var="jsessionid" value="${cookieVal.value.value}"></c:set>
	</c:if>
</c:forEach>

<jsp:include page="WEB-INF/classes/header.jsp"/>

<h3>Confirmation</h3>
<div class="well well-sm span4">
	<table class="table table-hover" style="background-color:white">
		<c:if test="${not empty shoppingCart}">
			<thead>
				<tr>
					<th> Date of Booking</th> 
					<th> Flight Id </th> 
					<th> Total Cost </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="booking" items="${shoppingCart}">
					<tr>
						<td><c:out value="${booking.dateOfBooking}"/></td>
						<td><c:out value="${booking.flightIds}"/></td>
						<td><c:out value="${booking.totalCost}"/></td>
					</tr>
				</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td><strong><c:out value="${totalCost}"/></strong></td>
				</tr>
		</c:if>
		<c:if test="${empty shoppingCart}">
			Your shopping cart is empty.
		</c:if>
			</tbody>
		</table><br>
</div>

<div class="acct-info">
	<h3>Account information</h3>
	<form class="form-horizontal" role="form" name="input">
		<div class="well well-sm span4">
			<div class="col-sm-7 error" style="color:red;width:100%"><br></div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="accountHolderId">Account holder id</label>
				<div class="col-sm-2">
					<input type="textfield" class="form-group" id="accountHolderId" placeholder="Account holder id" name="accountHolderId">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="routNumber">Routing number</label>
				<div class="col-sm-10">
					<input type="textfield" class="form-group" id="accountRoutingNumber" placeholder="Routing number" name="accountRoutingNumber" data-mask="0000000000">
				</div>
			</div>
			<input class="col-sm-offset-2 btn btn-primary btn-sm" type="submit" value="Submit">
		</div>
	</form>
</div>	

<div class="trans-conf"  style="display:none">
	<div class="alert alert-success alert-dismissible" role="alert"> 
		<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span>
		<span class="sr-only">Close</span></button>
		Your transaction was successfully recorded. Thanks for flying with  Air Minas.
	</div>
</div>

<div class="well well-sm span4 form-pass-details" style="display:none">
    <h5>Please provide the passenger details: </h5>
	<form class="form-horizontal form-group-sm" role="form" name="input" action='<c:url value="FlightSearchQuery"></c:url>' method="post" id="input">
		<div class="form-group">
	  		<label class="col-sm-2 control-label" for="passengerName">Name</label>
			<div class="col-sm-10">
				<input type="text" class="form-group" id="passengerName" name="passengerName" required>
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
	<button type="button" class="btn btn-primary btn-sm"
		onClick=verifyData()>
		<b>Print page</b>
	</button> 
</div>

<script type="text/javascript" src="js/mask/jquery.mask.js"></script>
<script type="text/javascript" src="js/masked_input_1.3.js"></script>

<script>

	 $("form").submit(function (event) {
		confirm_function(event);
		event.preventDefault();
	});
	
	function confirm_function(event) {
		
		var accountHolderId 		= $("#accountHolderId").val();
		var accountRoutingNumber 	= $("#accountRoutingNumber").val();
		var totalCost				= "" + ${totalCost};
			
		var accData = new Object();
		accData.accountHolderId 		= accountHolderId;
		accData.accountRoutingNumber 	= accountRoutingNumber;
		accData.totalCost				= totalCost;
		
		//var jsonData = JSON.stringify(accData);
		
		var toUrl 	= "/Team15-HW3-Banking/Bank1;jsessionid=";
		var toBank 	= toUrl.concat('${pageContext.session.id}');
		
		$.ajax({ url : toBank,
			type : "POST",
			data : accData,
			cache: false,
			success: function(data) {
				if (data.success == "true") { // If success == true
					clearError();
					$(".acct-info").remove();
					$(".form-pass-details").show();
					$(".trans-conf").show();
					$(".trans-conf").fadeOut(4000);
					update_history_function(accData);
					//On Success: Show Success/Failure message and a form for the user to
					//enter passenger details (Name, Age, Sex, etc...), Print Ticket button. Also calls update_history_function.
				}
				else { // If request was not successfull
			    	error(data.data);
					//On Failure: The page displays a message: "Transaction was not successful"
				}
				
			},
			error: function() {
				
			}
			
		});
		event.preventDefault();
	};
	
	function update_history_function(accData) {
		
		var toUrl 		= "UpdateBookingHistory;jsessionid=";
		var toHistory	= toUrl.concat('${jsessionid}');

		$.ajax({ url : toHistory,
			type : "POST",
			data : accData,
			cache: false,
			success: function(data) {
				if (data.success == "true") { // If success == true
					console.log("Booking History updated");
				}
				else { // If request was not successfull
					console.log("Failed to update Booking History");
				}
				
			},
			error: function() {
				
			}
			
		});
		event.preventDefault();
	};
	
	function clearError() {
		$(".error").hide();
	};
	 
	function error(msg) {
		$(".error").html(msg).show();
	};
	
	function verifyData() {
		var passengerName = document.forms["input"]["passengerName"].value;
		var age = document.forms["input"]["age"].value;
		var sex = document.forms["input"]["sex"].value;
		
		if (passengerName != null 
				&& passengerName != ""
				&& age != null && age != "" 
				&& sex != null && sex != "") {
			window.print();
		}
		else {
			alert("Please, provide the information required!");
		}
	};
	
</script>
<jsp:include page="WEB-INF/classes/bottom.jsp"/>