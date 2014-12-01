<%
	if (session.getAttribute("shoppingCart") == null) {
		response.sendRedirect("flightsearchquery.jsp");
	} else {
		ArrayList<Book> b = (ArrayList<Book>)session.getAttribute("shoppingCart");
		if (b.size() == 0)
			response.sendRedirect("flightsearchquery.jsp");
	}
%>

<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Flight"%>
<%@page import="model.Book"%>

<jsp:include page="WEB-INF/classes/header.jsp"/>

<form class="form-horizontal" role="form" name="input" action="Transaction">
<jsp:useBean id="flightBean" class="model.Flight" scope="session" />
<h3>Confirmation</h3>
<div class="well well-sm span4">

	<%
		double aggregateCost = 0;
		String aggregateCostFormatted = "Empty Shopping Cart. (0.00)";
		ArrayList<Book> fs = (ArrayList<Book>) session
				.getAttribute("shoppingCart");
		if (fs != null) {
	%>

	<table class="table table-hover" style="background-color:white">
			<thead>
				<tr>
					<th> Date of Booking</th> 
					<th> Flight Id </th> 
					<th> Total Cost </th>
				</tr>
			</thead>
			<tbody>
				<tr>
			<%
				Iterator<Book> it = fs.iterator();
				while (it.hasNext()) {
					Book book = (Book) it.next();
			%>
			<tr>
				<td><%=book.getDateOfBooking() %></td>
				<td><%=book.getFlightIds()%></td>
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
			<% } else { %>
			Your shopping cart is empty.
			<% } %>
			</tbody>
		</table><br>
</div>

<div class="acct-info">
	<h3>Account information</h3>
	<div class="well well-sm span4">
				<div class="form-group"><div class="col-sm-7 error" style="color:red;display: block;"></div></div>
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
			</form>	
	</div>
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
	<form class="form-horizontal form-group-sm" role="form" name="input" action="FlightSearchQuery" method="post" id="input">
		
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
		confirm_function();
		event.preventDefault();
	});
	
	function confirm_function() {
		
		var accountHolderId 		= $("#accountHolderId").val();
		var accountRoutingNumber 	= $("#accountRoutingNumber").val();
		var totalCost				= "" + <%=session.getAttribute("totalCost")%>;
			
		var jsonDataObject = new Object();
		jsonDataObject.accountHolderId 		= accountHolderId;
		jsonDataObject.accountRoutingNumber = accountRoutingNumber;
		jsonDataObject.totalCost			= totalCost;
		
		var jsonData = JSON.stringify(jsonDataObject);
		
		$.ajax({ url : "/Team15-HW3-Banking/Bank1",
			type : "GET",
			data : {action:"export",json:jsonData},
			contentType: 'application/json',
			cache: false,
			success: function(data) {
				if (data.success == "true") { // If success == true
					clearError();
					$(".acct-info").remove();
					$(".form-pass-details").show();
					$(".trans-conf").show();
					$(".trans-conf").fadeOut(4000);
					update_history_function(jsonData);
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
	
	function update_history_function(jsonData) {

		$.ajax({ url : "UpdateBookingHistory",
			type : "GET",
			data : {action:"export",json:jsonData},
			contentType: 'application/json',
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