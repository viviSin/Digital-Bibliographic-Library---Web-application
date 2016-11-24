<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order</title>
<style>
div.list {
    position: relative;
    top: 30px;
    left: 30px;
}
table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    padding: 8px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}

.content:hover{background-color:#f5f5f5}

#a_x200{
    visibility: hidden;
    width: 200px;
    height: 200px;
    background-color: black;
}
</style>
<style>
div.ex1 {
    margin-left: 2cm;
}
</style>
<script src="jquery-1.12.4.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

</head>
<body>
<jsp:include page="header.jsp" />
	
<h2>Order List</h2>

     
	<div class="list" align="justify" id="outside" style="margin-left: auto" style="margin-right: auto;">
	
	<!-- 	<button type="submit" id="setDel" name="setDel" value="setDel">Delete selected item</button> -->
		<!-- <div id="test" class='actions' onClick = "valthisform();"> -->
		<div class='bar'>
	 	<!-- <button type="button" id="clickme">option</button> -->
	  </div>
		
		<div id="Another" style="display:none">
			<div id="test" >
		 		<button type="submit"  name="delYes"  value="delYes" id="delYes"   >Delete selected item</button> 
		 		<button type="submit"  name="orderYes"  value="orderYes" id="orderYes"  onclick="myFunction()">Order selected item</button> 
 			</div>
		</div>
		
		
		
		<table style="width:100%" >
			<tr class='header'>
				
				<th>title</th>
				<th>type</th>
				<th>quality</th>
				<th>price</th>
				<th></th>
				
			</tr>
		    <c:forEach var="element" items="${requestScope.elements}" begin="0"  end="9">
			
					<tr class='content'>
					
					
						<td  width="40%">
						<c:catch var="exception">
							<a href="http://localhost:8080/search.do?param=${element.item.title}">${element.item.title}</a>
						</c:catch>
						<c:if test="${not empty exception}">${element.item.title}</c:if> 
						</td>
						<td width="20%">
							<c:catch var="exception">${element.item.publtype}</c:catch>
							<c:if test="${not empty exception}">${element.item.publtype}</c:if>
						</td>
						<td width="10%">
							<c:out value="${element.quantity}"></c:out>
						</td>
						<td>
							<c:out value="${element.price}"></c:out>
						    <%-- <c:catch var="exception">${element.isAva}</c:catch>
							<c:if test="${not empty exception}">${element.isAva} | </c:if> --%>
						</td> 
						
						<td width="10%">
							<%-- <c:catch var="exception">${element.mdate}</c:catch>
							<c:if test="${not empty exception}">${element.mdate}</c:if>  --%>
						</td>
					</tr>
				</c:forEach>
				<tr>
				<th>Total Price</th>
				<td></td>
				<td></td>
				<td>${price}</td>
				<td></td>
				</tr>
			</table>
	
	</div>
	<br>
	<br>
	<br>
	<br>
<div class="ex1">

	<form action="OrderItemServlet">	
	<div id="foo">
			<div class='bar'><div class='field'> </div></div>
			<div class='bar'>
			 	<div class='actions'>
					 <input type = "submit" name = "credit" value = "Comfirm to buy"  />
			 	</div>
			 </div>
			 </div>
	</form>	  
</div>
</body>
</html>