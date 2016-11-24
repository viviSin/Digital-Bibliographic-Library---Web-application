<%-- 
/**
 * @author weidongli
 *nothing related to cart in this page, i made it simple. 
 *user shud be able to click on the title link and go to 
 *the onsaleitem page, then select the quantity then add to cart. 
 *by the way, we need to add photos to the item, so in the listing table,
 *u can just define one more column and add the pic.
 *
 *
 * -Leon
 *
 */
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="org.w3c.dom.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="search.css" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Welcome!</title>
</head>
<body>
	
	<center>
		<h1>Welcome to DBLP!</h1>
		Let's dig out the computer science bibliography you need!
	</center>
	<!--leads to result page -->
	<form action='index' method="get">
		<center>
			<span title="Please enther any substrings of the title"><input
				name=searchTitle type='text' value="${searchTitle}"></span> <input type='submit'
				name=buttonName value='Search'><br>
		</center>
		<br><br>
	
	<%--I put a table inside another in this page to organize the position. if anyone can use bootstrap or css to make to better please do so. -Leon --%>
		<table>
			<tr>
				<td>additional details: <br> Author: <input name=searchAuthor type='text' value="${searchAuthor}"><br>
				 <span
					title="Platform or origin where the item has been published">Venue:
						<input name=searchVenue type='text' value="${searchVenue}">
				</span><br>
				</td>
				<td>
					<center>
						Publication-year:From<input name=searchYear1 type='text' value = "${searchYear1}">
						To <input name=searchYear2 type='text'value = "${searchYear2}"><br>
						Publication type:
						<button type="button" onclick="ConferenceButton()">Conference</button>
						<button type="button" onclick="BCButton()">Books or
							Collections</button>
						<button type="button" onclick="JournalButton()">Journal</button>
						<button type="button" onclick="ThesisButton()">Thesis</button>
						<button type="button" onclick="WebPageButton()">WebPage</button>
						<br> (if no specific publication type selected, search will
						consider all the types.)
						<table>
							<tr>
								<td><input id=artC type='checkbox' name='article'
									value='article' <c:if test="${allowTypes.contains('article')}">checked="checked"</c:if>><span
									title="An article from a journal or magazine">article</span></td>
								<td><input id=inpC type='checkbox' name='inproceedings'
									value='inproceedings' <c:if test="${allowTypes.contains('inproceedings')}">checked="checked"</c:if>><span
									title="A paper in a conference or workshop proceedings.">inproceedings</span>
								</td>
								<td><input id=proC type='checkbox' name='proceedings'
									value='proceedings'<c:if test="${allowTypes.contains('proceedings')}">checked="checked"</c:if>><span
									title="The proceedings volume of a conference or workshop.">proceedings</span>
								</td>
								<td><input id=booC type='checkbox' name='book'
									value='book'<c:if test="${allowTypes.contains('book')}">checked="checked"</c:if>><span
									title="An authored monograph or an edited collection of articles.">book
								</span></td>
							</tr>
							<tr>
								<td><input id=incC type='checkbox' name='incollection'
									value='incollection'<c:if test="${allowTypes.contains('incollection')}">checked="checked"</c:if>><span
									title="A part or chapter in a monograph.">incollection</span></td>
								<td><input id=phdC type='checkbox' name='phdthesis'
									value='phdthesis'<c:if test="${allowTypes.contains('phdthesis')}">checked="checked"</c:if>><span title="A PhD thesis.">phdthesis</span>
								</td>
								<td><input id=masC type='checkbox' name='mastersthesis'
									value='mastersthesis'<c:if test="${allowTypes.contains('mastersthesis')}">checked="checked"</c:if>><span title="A Master's thesis.">
										mastersthesis</span></td>
								<td><input id=wwwC type='checkbox' name='www'
									value='www'<c:if test="${allowTypes.contains('www')}">checked="checked"</c:if>><span title="A web page.">www</span></td>
							</tr>
						</table>
					</center>
				</td>
			</tr>
		</table>
	</form>

	<br> Documents:

	<br>
	<div class=resultTable>
	<c:choose>
	<c:when test="${onSaleList.size() > 0}">
		
		<c:set var="itemPerPage" value ="10" />
		<c:set var="totalItem" value = "${onSaleList.size()}"  />
		<c:set var="lastOnPage" value = "${currentPage*itemPerPage-1}"  />
		<c:set var="firstOnPage" value = "${itemPerPage*(currentPage-1)}"  />
		<table>
			<tr>
				<th>Title</th>
				<th>Type</th>
				<th>Authors</th>
				<th>Price</th>
				<th>Stock</th>
				<th>Seller</th>
			</tr>
			<c:forEach begin="${firstOnPage}" end= "${lastOnPage}" items="${onSaleList}" var="onSaleItem">
    				<tr>
    					<td><a href= onsaleitem?id=${onSaleItem.getId()} >${onSaleItem.getItem().getTitle()}</a></td>
    					<td>${onSaleItem.getItem().getPubltype()}</td>
    					<td>
    						<c:forEach items="${onSaleItem.getItem().getAuthors()}" var="author">
							  <p>${author.getName()}  </p>
							</c:forEach>
						</td>
    					<td><fmt:formatNumber value="${onSaleItem.getPrice()}"
    							type="currency" /></td>
    					<td>${onSaleItem.getQuantity()}</td>
    					<td>${onSaleItem.getSeller().getUsername()}</td>
    				</tr>
    			</c:forEach>
		</table>
		
					<br>
					<center> 
					<form action ='index' method="get">
					
					<%--current page  only stored when user is doing paging-related operation  -Leon --%>
					<%-- if u have time, you can do paging with number buttons instead of pre and nxt -Leon --%>
					
						<%-- this did not work so I ve done it in serverside with session.
						 <c:set var="onSaleList" value="${onSaleList}" scope="request"/>
						 
						 this did not work either, so I will use input tag
						 <c:set var="currentPage" value="${currentPage}" scope="request"/>
						--%>
						<input type='hidden' name='currentPage' value ="${currentPage}">
						
							<c:if test= "${currentPage > 1 }">
									<input type='submit' name='buttonName' value='    Previous   ' >
							</c:if>
							
							<c:set var="tpNum" value= "${totalItem/itemPerPage}"/>
							<c:set var="totalPage" value= "${tpNum+(1-tpNum%1)%1}"/>
							${currentPage} / <fmt:formatNumber value="${totalPage}" maxFractionDigits="0" />
							<c:if test= "${lastOnPage < totalItem - 1}">
									<input type='submit' name='buttonName' value='      Next      ' >
							</c:if>
					</form>
					</center>	
		</c:when>
		<c:otherwise>
			Sorry, there is no result for search.
		</c:otherwise>
		</c:choose>
	</div>

	

</body>


<script>

	function ConferenceButton() {
		reverseButton("inpC");
		reverseButton("proC");
	}
	function BCButton() {
		reverseButton("booC");
		reverseButton("incC");
	}
	function JournalButton() {
		reverseButton("artC"); 
	}
	function ThesisButton() {
		reverseButton("phdC");
		reverseButton("masC");
	}
	function WebPageButton() {
		reverseButton("wwwC");
	}
	function reverseButton(id) {
		if (document.getElementById(id).checked) {
			document.getElementById(id).checked = false;
		} else {
			document.getElementById(id).checked = true;
		}
	}
</script>
</html>