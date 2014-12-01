<%
 	if (session.getAttribute("user") == null) {
 		response.sendRedirect("LoginError");
 	}
 %>

<%
//Airline iata codes
String[] airlines = {"AA", "DL", "UA", "WN", "B6", "AS", "NK", "F9"};

//airport iata codes
String[] airports = {"ATL", "ANC", "AUS", "BWI", "BOS", 
		"CLT", "MDW", "ORD", "CVG", "CLE", "CMH", "DFW", 
		"DEN", "DTW", "FLL", "RSW", "BDL", "HNL", "IAH", 
		"HOU", "IND", "MCI", "LAS", "LAX", "MEM", "MIA", 
		"MSP", "BNA", "MSY", "JFK", "LGA", "EWR", "OAK", 
		"ONT", "MCO", "PHL", "PHX", "PIT", "PDX", "RDU", 
		"SMF", "SLC", "SAT", "SAN", "SFO", "SJC", "SNA", 
		"SEA", "STL", "TPA", "IAD", "DCA"}; 
%>

<jsp:include page="WEB-INF/classes/header.jsp"/>

<script type="text/javascript" src="js/masked_input_1.3.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

<div class="well well-sm span4">
    <h4>Details of your travel: </h4>
	<form class="form-horizontal form-group-sm" role="form" name="input" action="FlightSearchQuery" method="post" id="input">
		<div class="form-group">
  			<label class="col-sm-2 control-label" for="source">From</label>
  			<select name="source" form="input" id="source" required>
  				<option value=""> </option>
  				<% for (int i = 0; i < airports.length ; i++) {%>
  					<option value=<%=airports[i]%> ><%=airports[i]%></option>
  				<% } %>
  			</select>
		</div>
		<div class="form-group">
  			<label class="col-sm-2 control-label" for="destination">To</label>
  			<select name="destination" form="input" id="destination" required>
  				<option value=""> </option>
  				<% for (int i = 0; i < airports.length ; i++) {%>
  					<option value=<%=airports[i]%> ><%=airports[i]%></option>
  				<% } %>
  			</select>
		</div>
		<div class="form-group">
  		<label class="col-sm-2 control-label" for="departure">Departure</label>
  			
  				<input type="text" style="margin-left:1px" class="form-group" id="departure" name="departure" disabled="disabled"  required >
  			
		</div>
    	<button type="submit" class="btn btn-primary btn-sm col-md-offset-2" value="Search">Search</button>
		<button type="button" class="btn btn-danger btn-sm" value="Exit" onClick="window.location='login.jsp'">Exit</button>
	</form>    
</div>


<script>
$.datepicker.setDefaults({
	  showOn: "both"
	});

$( "#departure" ).datepicker({ 
	appendText: "&nbsp; (mm-dd-yyyy)",
	autoSize: true,
	minDate: 0, 
	maxDate: "+365D",
	changeMonth: true,
    changeYear: true
});

</script>

<jsp:include page="WEB-INF/classes/bottom.jsp"/>

