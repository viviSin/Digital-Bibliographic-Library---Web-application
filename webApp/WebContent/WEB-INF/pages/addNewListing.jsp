<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<title>Add Listing</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<h1>Search for Item</h1>

	<form method="POST" action="add_listing">
			<input type="text" name="searchString" />
			<input type="hidden" name="action" value="searchListing" />
			<input type="submit" value="Search" />
	</form>
	
	<h2>Items</h2>
	<c:choose>
		<c:when test="${fn:length(itemList)!='0'}">
			<table>
			<c:forEach var="item" items="${itemList}">
				<tr>
				<td>
				<c:forEach var="author" items="${item.authors}">
				${author.name}, 
				</c:forEach>
				-${item.publtype}-${item.title}-${item.publyear}-${item.venues}
				</td>
				<td>
					<form method="post" action="add_listing">
					<input type="hidden" name="action" value="newListing" />
					<input type="hidden" name="itemid" value="${item.id }" />
					Quantity: 
					<input type="text" value="0" name="newQuantity" />
					Price:
					<input type="text" value="0" name="newPrice" />
					<input type="submit" value="Add" />
					</form>
				</td>
				</tr>
			</c:forEach>
			</table>
			<h2>Can't find your item?</h2>
			<h3>Add A New Listing:</h3>
			<br>
			<form method="post" action="add_listing">
			<input type="hidden" name="action" value="addNewListing" />
			Authors:<br>
			(separate by commas for multiple authors e.g. john smith, john doe)<br>
			<input type="text" name="newAuthors" /><br>
			Publication Type:<br>
			<select name="newPubType">
			<option value="article">Article</option>
			<option value="inproceedings">Inproceedings</option>
			<option value="proceedings">Proceedings</option>
			<option value="book">Book</option>
			<option value="incollection">Incollection</option>
			<option value="phdthesis">PHD Thesis</option>
			<option value="mastersthesis">Masters Thesis</option>
			<option value="www">WWW</option>
			</select>
			<br>
			Publication Year:<br>
			<input type="text" name="newPubYear" /><br>
			Title:<br>
			<input type="text" name="newTitle" /><br>
			Venues:<br>
			<input type="text" name="newVenues" /><br>
			Quantity:<br>
			<input type="text" name="newQuantity" /><br>
			Price:<br>
			<input type="text" name="newPrice" /><br>
			<input type="submit" value="Add" />
			</form>
		</c:when>
		<c:otherwise>
			<h2>No items found</h2>
			<h3>Add A New Listing:</h3>
			<br>
			<form method="post" action="add_listing">
			<input type="hidden" name="action" value="addNewListing" />
			Authors:<br>
			(separate by commas for multiple authors e.g. john smith, john doe)<br>
			<input type="text" name="newAuthors" /><br>
			Publication Type:<br>
			<select name="newPubType">
			<option value="article">Article</option>
			<option value="inproceedings">Inproceedings</option>
			<option value="proceedings">Proceedings</option>
			<option value="book">Book</option>
			<option value="incollection">Incollection</option>
			<option value="phdthesis">PHD Thesis</option>
			<option value="mastersthesis">Masters Thesis</option>
			<option value="www">WWW</option>
			</select>
			<br>
			Publication Year:<br>
			<input type="text" name="newPubYear" /><br>
			Title:<br>
			<input type="text" name="newTitle" /><br>
			Venues:<br>
			<input type="text" name="newVenues" /><br>
			Quantity:<br>
			<input type="text" name="newQuantity" /><br>
			Price:<br>
			<input type="text" name="newPrice" /><br>
			<input type="submit" value="Add" />
			</form>
		</c:otherwise>
	</c:choose>
	
	<br>
	
	<h2>Your Listings</h2>
	<c:choose>
		<c:when test="${fn:length(listingList)!='0'}">
			<table>
			<c:forEach var="listing" items="${listingList}">
				<tr>
				<td>
				<c:forEach var="author" items="${listing.item.authors}">
				${author.name}, 
				</c:forEach>
				<td>-${listing.item.publtype}-${listing.item.title}-${listing.item.publyear}-${listing.item.venues}
				    -${listing.price}-${listing.mdate}
					-${listing.quantity}-${listing.isPaused}
				</td>
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
							<form method="post" action="seller">
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
			<h2>You have no listings matching the search result</h2>
		</c:otherwise>
	</c:choose>


</body>
</html>