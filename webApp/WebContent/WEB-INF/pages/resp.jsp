<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Response</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="container">
		<div class="row">
			<p class="text-success">${param.successMsg}</p>
			<p class="text-danger">${param.errorMsg}</p>
		</div>
	</div>


</body>
</html>