<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>

	<script>
		$.ajax({
			url : "TestAjax",
			type : "POST",
			data : JSON.stringify( { "first-name": "Lucas", "last-name": "Monteiro" }),
			contentType: 'application/json',
			success: function(data) {
				if(data.success == "false")
			    	error(data.data);
				else {
					error("Funcionou!");
				}
			},
			error: function() {
				error("Funcionou!");
			}
			
		});
		
		function error(msg) {
			alert(msg);
		};
	</script>

</body>
</html>