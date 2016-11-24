<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Sign UP</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="container">
		<div class="row">
			<form class="col-md-4 col-md-offset-4 col-xs-12" method="post" action='signup' >

				<p class="text-danger">${param.errorMsg}</p>

				<div class="form-group">
					<label for="username">Username</label> <input type="text"
						class="form-control" id="username" name="username"
						placeholder="Username">
				</div>
				<div class="form-group">
					<label for="password">Password</label> <input type="password"
						class="form-control" id="password" name="password"
						placeholder="Password">
				</div>

				<div class="form-group">
					<label for="password2">Confirm Password</label> <input
						type="password" class="form-control" id="password2"
						name="password2" placeholder="Enter Password Again">
				</div>

				<div class="form-group">
					<label for="email">Email</label> <input type="email"
						class="form-control" id="email" name="email" placeholder="Email">
				</div>

				<button type="submit" class="btn btn-primary">Sign up now</button>
			</form>
		</div>
	</div>


</body>
</html>