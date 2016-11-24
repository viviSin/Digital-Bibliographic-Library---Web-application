<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<c:set  var="item" value="${item}" />

<title>${item.getTitle()}</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<h1>Publication Information</h1>
		<table class="table table-bordered">
			<tbody>
						<tr>
						<td>Title</td>
						<td>${item.getTitle()}</td>
					</tr><tr>
						<td>Publication type:</td>
						<td>${item.getPubltype()}</td>
					</tr><tr>
						<td>Publication year:</td>
						<td>${item.getPublyear()}</td>
					</tr>
					<tr>
						<td>Venue:</td>
						<td>${item.getVenues()}</td>
					</tr>
					<tr>
						<td>Authors:</td>
						<td>
							<c:forEach items="${item.getAuthors()}" var="author">
							  ${author.getName()}<br>
							</c:forEach>
						</td>
					</tr>
			</tbody>

		</table>
		
	</div>
</body>
</html>