<%@page import="java.util.ArrayList"%>
<%@page import="model.BookingHistoryModel"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="WEB-INF/classes/header.jsp" />

<script src="js/jquery-ui/jquery-ui.js" type="text/javascript"></script>
<link href="js/jquery-ui/jquery-ui.css" rel="stylesheet" type="text/css" />

<!-- Include one of jTable styles. -->
<link href="js/jtable.2.4.0/themes/metro/blue/jtable.min.css" rel="stylesheet" type="text/css" />
<script src="js/jtable.2.4.0/jquery.jtable.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function () {
    $('#bookingHistory').jtable({
        title: 'Booking History',
        paging: true, //Enable paging
        pageSize: 10, //Set page size (default: 10)  
        actions: {
            listAction:   'BookingHistory?action=bookingHistoryList'
        },
        fields: {
            flight_id: {
                title: 'Flight Number',
                width: '15%'
            },
            source: {
                title: 'From',
                width: '10%'
            },
            destination: {
                title: 'To',
                width: '10%'
            },
            date_of_booking: {
                title: 'Date of Booking',
                width: '30%',
            },
            departure: {
                title: 'Departure',
                width: '25%'
            },
            total_cost: {
                title: 'Total Cost',
                width: '10%'
            }
        }
    });
    
    $('#bookingHistory').jtable('load');
});
</script>

<div id="bookingHistory"></div>

</body>
</html>