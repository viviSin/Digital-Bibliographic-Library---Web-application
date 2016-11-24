<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>My Selling Items</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="container">
		<h1>My Selling Items</h1>

		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Title</th>
					<th>Publition Type</th>
					<th>Venues</th>
					<th>Publication Year</th>
					<th>M Date</th>
					<th>Price</th>
					<th>Stock</th>
					<th>is Paused</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${items}" var="item">
					<tr>
						<td>${item.title}</td>
						<td>${item.publtype}</td>
						<td>${item.publyear}</td>
						<td>${item.venues}</td>
						<td><fmt:formatDate type="date" value="${item.mdate}" /></td>
						<td>${item.price}</td>
						<td>${item.stock}</td>
						<td>${item.isPaused}</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>


	</div>

</body>
</html>