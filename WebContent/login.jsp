<jsp:include page="WEB-INF/classes/header.jsp" />


<h3>Log in here</h3>
<div class="well well-sm span4">
	<form class="form-horizontal form-group-sm" role="form" name="input"
		action="Login" method="post">
		<div class="form-group">
			<%
				if (session.getAttribute("status") != null) {
					%><label class="error" for="status"><%=session.getAttribute("status") %></label>
					<br/>
					<%
					session.setAttribute("status", null);
				}
			%>
			<label class="col-sm-2 control-label" for="email">Email</label>
			<div class="col-sm-7">
				<input type="textfield" class="form-group" id="email"
					placeholder="email" name="email">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="password">Password</label>
			<div class="col-sm-7">
				<input type="password" class="form-group" id="password"
					placeholder="Password" name="password">
			</div>
		</div>
		<input class="col-sm-offset-2 btn btn-primary btn-sm" type="submit"
			value="Sign in"> &nbsp or <a href="registration.jsp">Sign
			up now</a>
	</form>
</div>


<script>
	$("form").validate({
		rules : {
			email : {
				required : true,
				email : true
			},
			password : {
				required : true
			}
		}
	});
</script>

<jsp:include page="WEB-INF/classes/bottom.jsp" />