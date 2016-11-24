<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="search.css" />

<!DOCTYPE html>
<html>
<head>
<c:set  var="item" value="${onSaleItem.getItem()}" />
<title>${item.getTitle()}</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	
		<h1>Product Information:</h1>
		<h4>Price: $ ${onSaleItem.getPrice()}
		</h4> 
		<h4>
		 Seller: ${onSaleItem.getSeller().getUsername()}
		</h4> 
		<h4>
		 Last edited: 
		<fmt:formatDate value="${onSaleItem.getMdate()}" pattern="dd/MM/yyyy HH:mm"/>
		</h4> 
		<h4>
					Stock:
					${onSaleItem.getQuantity()}
		</h4>
		
				<br>
				<br>	
		<form method ='get'>
			<h4>
			Quantity:
					<select name="quantity" >
			 				<c:forEach begin="1" end="${onSaleItem.getQuantity()}" var="loop">
   									 <option> ${loop}</option>
							</c:forEach>
					</select>
			<input type="hidden" name="id" value="${onSaleItem.getId()}"  />
			<input type="hidden" name="iid" value="${onSaleItem.getItem().getId()}"  />
			<input type="submit" name="buttonName" value="add to cart"  />
			</h4>
		</form>
		
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
		
</body>
</html>