<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<title>Admin History</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<form method="POST" action="adminHome">
			<input type="text" name="searchString" />
			<input type="hidden" name="action" value="search" />
			<input type="submit" value="Search" />
	</form>
	
	<h1>History for ${curUser.username}</h1>
	<c:choose>
		<c:when test="${fn:length(buyHistory)!='0'}">
			<h2>Buy History</h2>
			<table>
			<c:forEach var="buyHistory" items="${buyHistory}">
				<tr>
				<c:forEach var="orderItem" items="${buyHistory.orderItems}">
				<tr>
					<td>${orderItem.item.publtype}-${orderItem.item.title}
					-${buyHistory.mdate}-${orderItem.price}-${orderItem.seller.username}
					</td>
				</tr>
				</c:forEach>
				</tr>
			</c:forEach>
			</table>
			</c:when>
		<c:otherwise>
			<h2>No buy history found</h2>
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${fn:length(removeHistory)!='0'}">
			<h2>Removed History</h2>
			<table>
			<c:forEach var="removeItem" items="${removeHistory}">
				<tr>
					<td>${removeItem.item.publtype}-${removeItem.item.title}
					-${removeItem.added}-${removeItem.removed}
				</tr>
			</c:forEach>
			</table>
			</c:when>
		<c:otherwise>
			<h2>No removal history found</h2>
		</c:otherwise>
	</c:choose>


</body>
</html>