<jsp:include page="WEB-INF/classes/header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${empty user}">
	<% response.sendRedirect("LoginError"); %>
</c:if>

<%	String[] airlines = {"AA", "DL", "UA", "WN", "B6", "AS", "NK", "F9"};

	String[] airports = {"ATL", "ANC", "AUS", "BWI", "BOS", 
		"CLT", "MDW", "ORD", "CVG", "CLE", "CMH", "DFW", 
		"DEN", "DTW", "FLL", "RSW", "BDL", "HNL", "IAH", 
		"HOU", "IND", "MCI", "LAS", "LAX", "MEM", "MIA", 
		"MSP", "BNA", "MSY", "JFK", "LGA", "EWR", "OAK", 
		"ONT", "MCO", "PHL", "PHX", "PIT", "PDX", "RDU", 
		"SMF", "SLC", "SAT", "SAN", "SFO", "SJC", "SNA", 
		"SEA", "STL", "TPA", "IAD", "DCA"}; 
%>

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
  				<c:forEach items="<%=airports%>" var="airport" >
  					<option value= <c:out value="${airport}"/>>
  						<c:out value="${airport}"/>
  					</option>
  				</c:forEach>
  			</select>
		</div>
		<div class="form-group">
  			<label class="col-sm-2 control-label" for="destination">To</label>
  			<select name="destination" form="input" id="destination" required>
  				<option value=""> </option>
  				<c:forEach items="<%=airports%>" var="airport" >
  					<option value= <c:out value="${airport}"/>>
  						<c:out value="${airport}"/>
  					</option>
  				</c:forEach>
  			</select>
		</div>
		<div class="form-group">
  			<label class="col-sm-2 control-label" for="departure">Departure</label>
  			<input type="text" style="margin-left:1px" class="form-group" id="departure" name="departure" readonly="readonly" required >
		</div>
    	<button type="submit" class="btn btn-primary btn-sm col-md-offset-2" value="Search" data-toggle="modal" data-target=".bs-example-modal-sm">Search</button>
		<button type="button" class="btn btn-danger btn-sm" value="Exit" onClick="window.location='login.jsp'">Exit</button>
	</form>    
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="position: fixed;top: 50%;left: 50%;transform: translate(-50%, -50%)">
    <div class="modal-content">
      <div class="modal-body">
        Please, wait while we load the results! <img alt="Wait symbol" src="style/wait.gif" style="width: 50px;height: 50px;margin-left: 5%">
      </div>
    </div>
  </div>
</div>


<script>
$( "form" ).submit(function( event ) {
	$('#myModal').modal({
		show:true,
		backdrop: 'static'
	});
});

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

