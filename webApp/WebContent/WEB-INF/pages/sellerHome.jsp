<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<title>Seller Home</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<form method="POST" action="seller">
			<input type="text" name="searchString" />
			<input type="hidden" name="action" value="searchListing" />
			<input type="submit" value="Search" />
	</form>
	
	<h1>Listings for ${curUser.username}</h1>
	
	<c:choose>
		<c:when test="${fn:length(onSaleItemList)!='0'}">
			<table>
			<c:forEach var="listing" items="${onSaleItemList}">
				<tr>
				<td>${listing.id}-${listing.item.publtype}-${listing.item.title}-${listing.price}
				-${listing.mdate}-${listing.quantity}-${listing.isPaused}</td>
				<td>
					Quantity:
					<form method="post" action="add_listing">
					<input type="text" value="${listing.quantity}" name="newQuantity" />
					<input type="hidden" name="action" value="updateQuantity" />
					<input type="hidden" name="listingid" value="${listing.id }" />
					<input type="submit" value="Update" />
					</form>
				</td>
				<td>
					Price:
					<form method="post" action="add_listing">
					<input type="text" value="${listing.price}" name="newPrice" />
					<input type="hidden" name="action" value="updatePrice" />
					<input type="hidden" name="listingid" value="${listing.id}" />
					<input type="submit" value="Update" />
					</form>
				</td>
				
				<c:choose>
					<c:when test="${listing.isPaused == 1}">
						<td>
							<form method="post" action='seller'>
							<input type="hidden" name="userid" value="${curUser.id}"/>
							<input type="hidden" name="listingid" value="${listing.id}"/>
							<input type="hidden" name="action" value="pause"/>
							<button name="pause" type="submit">Pause</button>
							</form>
						</td>
					</c:when>
					<c:otherwise>
						<td>
							<form method="post" action="seller">
							<input type="hidden" name="userid" value="${curUser.id}"/>
							<input type="hidden" name="listingid" value="${listing.id}"/>
							<input type="hidden" name="action" value="unpause"/>
							<button name="unpause">Unpause</button>
							</form>
						</td>
					</c:otherwise>
				</c:choose>
				
				</tr>
			</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<h2>No listings found</h2>
		</c:otherwise>
	</c:choose>

	<br>
	<form method="post" action="add_listing">
		<input type="hidden" name="userid" value="${curUser.id}"/>
		<input type="hidden" name="action" value="addListingHome"/>
		<button name="addListing">Add New Listing</button>
	</form>

</body>
</html>