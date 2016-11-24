<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<title>Admin Listings</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<form method="POST" action="adminHome">
			<input type="text" name="searchString" />
			<input type="hidden" name="action" value="search" />
			<input type="submit" value="Search" />
	</form>
	
	<h1>Listings for ${curUser.username}</h1>
	<c:choose>
		<c:when test="${fn:length(listings)!='0'}">
			<table>
			<c:forEach var="listing" items="${listings}">
				<tr>
				<td>${listing.id}-${listing.item.title}-${listing.price}-${listing.mdate}
					-${listing.quantity}-${listing.isPaused}
				</td>
				<td>
					<form method="post" action='admin_listings'>
					<input type="hidden" name="userid" value="${curUser.id}"/>
					<input type="hidden" name="listingid" value="${listing.id}"/>
					<input type="hidden" name="action" value="remove"/>
					<button name="remove" type="submit">Remove</button>
					</form>
				</td>
				</tr>
			</c:forEach>
			</table>
			</c:when>
		<c:otherwise>
			<h2>No listings found</h2>
		</c:otherwise>
	</c:choose>


</body>
</html>