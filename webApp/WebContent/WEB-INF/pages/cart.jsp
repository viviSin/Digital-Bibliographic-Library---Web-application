<%--
/**
 * @author weidongli
 *
 */
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.*" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"   uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- <%@ page import = "*.*" %> --%>
<jsp:include page="header.jsp" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shopping Cart</title>

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
<script src="jquery-1.12.4.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script>
$(document).ready(function() {
	
    $('.field input').keyup(function() {

        var empty = false;
        $('.field input').each(function() {
            if ($(this).val().length == 0) {
                empty = true;
            }
        });

        if (empty) {
            $('.actions input').attr('disabled', 'disabled');
        } else {
            $('.actions input').removeAttr('disabled');
        }
    });
});
</script>
</head> 
<body>

<!--  <input type="checkbox" id="checkme"/> -->
<!-- <!--<input type="submit" name="sendNewSms" class="inputButton" id="sendNewSms" value=" Send " /> --> 
<c:choose>
    <c:when test="${not empty requestScope.elements}">
     
		<div class="list" align="justify" id="outside" style="margin-left: auto" style="margin-right: auto;">
			<form id="form" action="cart"  method="post" name="form">	
		<!-- 	<button type="submit" id="setDel" name="setDel" value="setDel">Delete selected item</button> -->
			<!-- <div id="test" class='actions' onClick = "valthisform();"> -->
			<div class='bar'>
		 	<!-- <button type="button" id="clickme">option</button> -->
		  </div>
			
			<div id="Another" style="display:none">
				<div id="test" >
			 		<button type="submit"  name="delYes"  value="delYes" id="delYes"   >Delete selected item</button> 
			 		<button type="submit"  name="orderYes"  value="orderYes" id="orderYes">Order selected item</button> 
	 			</div>
			</div>
			
			
			
			<table style="width:100%" >
				<tr class='header'>
					<th>    </th>
					<th>title</th>
					<th>type</th>
					<th>quality</th>
					<th>available</th>
					<th>updated date</th>
					
				</tr>
			    <c:forEach var="element" items="${requestScope.elements}" begin="0"  end="9">
				
						<tr class='content'>
						
						<td width="10%">
							<!-- <input type="hidden" name="from" value="cartJsp">  -->
							<%-- <input type="hidden" name="id" value="${element.id}"> --%>
							<div class='field'><input type="checkbox"  id="delId" name="delId"  value="${element.key}"></div>
	       					<!-- <button type="submit" name="addId"  onClick="window.alert('item has been add to the cart');">Cart</button> -->
						</td>
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
								<c:out value="${element.ava}"></c:out>
							    <%-- <c:catch var="exception">${element.isAva}</c:catch>
								<c:if test="${not empty exception}">${element.isAva} | </c:if> --%>
							</td> 
							
							<td width="10%">
								<c:catch var="exception">${element.mdate}</c:catch>
								<c:if test="${not empty exception}">${element.mdate}</c:if> 
							</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</div>
     	<%-- <c:forEach var="elist" items="${requestScope.elements}" >
			<c:out value="${elist.type}"></c:out><br>
		</c:forEach> --%>
    </c:when>    
   <c:otherwise>
    Shopping Cart is Empty
        <br>
        
    </c:otherwise>
</c:choose>
<br>
<br>
<br>
<br>
<!-- <form>
<button type="submit" id="asd" name="asd" value="asd">submit</button>
</form> -->
</body>
<script language="JavaScript">
function valthisform()
{
    var checkboxs=document.getElementsByName("delId");
    var okay=false;
    var sendbtn = document.getElementById("delItem");
    
    for(var i=0,l=checkboxs.length;i<l;i++)
    {
        if(checkboxs[i].checked)
        {
            okay=true;
            break;
        }
    }
 
    if(okay){
    	sendbtn.disabled = false;
    }
    else sendbtn.disabled = true;
}
</script>
<script>
document.getElementById("outside").onclick=function(){

        var checkboxs=document.getElementsByName("delId");
        var okay=false;
        var sendbtn = document.getElementById("delItem");
        
        for(var i=0,l=checkboxs.length;i<l;i++)
        {
            if(checkboxs[i].checked)
            {
                okay=true;
                break;
            }
        }
        if(okay){
       		document.getElementById('Another').style.display = 'block';
       		document.getElementById('Another').style.visibility = 'visible';
        }
        else {
        	if (document.getElementById('Another').style.display == "block") 
        	{
        		document.getElementById('Another').style.display = 'none';
        		document.getElementById('Another').style.visibility = 'none';
        	}
        
        };
        
    };
</script>
</html>


