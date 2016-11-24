<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<title>Admin Home</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<form method="POST" action="adminHome">
			<input type="text" name="searchString" />
			<input type="hidden" name="action" value="search" />
			<input type="submit" value="Search" />
	</form>
	
	<c:choose>
		<c:when test="${fn:length(users)!='0'}">
			<table>
			<c:forEach var="user" items="${users}">
				<tr>
				<td>${user.id}-${user.username}-${user.password}-${user.email}-${user.yearOfBirth}-${user.address}
				-${user.creditcard}-${user.hash}-${user.isVerified}-${user.isBanned}</td>
				
				<td>
					<form method="post" action='admin_listings'>
					<input type="hidden" name="userid" value="${user.id}"/>
					<button name="viewListings" type="submit">View Listings</button>
					</form>
				</td>
				
				
				
				<td>
					<form method="post" action='admin_history'>
					<input type="hidden" name="userid" value="${user.id}"/>
					<button name="viewHistory">View History</button>
					</form>
				</td>
				<c:choose>
					<c:when test="${user.isBanned == 1}">
						<td>
							<form method="post" action='admin_home'>
							<input type="hidden" name="userid" value="${user.id}"/>
							<input type="hidden" name="action" value="ban"/>
							<button name="ban" type="submit">Ban</button>
							</form>
						</td>
					</c:when>
					<c:otherwise>
						<td>
							<form method="post" action="admin_home">
							<input type="hidden" name="userid" value="${user.id}"/>
							<input type="hidden" name="action" value="unban"/>
							<button name="unban">Unban</button>
							</form>
						</td>
					</c:otherwise>
				</c:choose>
				</tr>
			</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<h2>No users found</h2>
		</c:otherwise>
	</c:choose>


</body>
</html>