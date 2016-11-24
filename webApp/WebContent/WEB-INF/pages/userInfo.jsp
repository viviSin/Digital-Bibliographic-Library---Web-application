<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user detail</title>
<style>
p { 
	color: #424242;
    font-family: "Adobe Caslon Pro", "Hoefler Text", Georgia, Garamond, Times, serif;
 letter-spacing:0.1em;

 margin: 40px auto;
 text-transform: lowercase;
 line-height: 145%;
 font-size: 14pt;
 font-variant: small-caps;
}
em {
	
	 
}

h5 {

}
table, th, td {
    border: 1px solid black;
}
</style>

</head>
<body>
<jsp:include page="header.jsp" />
<h2>User info</h2>
<!-- //<!ENTITY % field "author|editor|title|booktitle|pages|year|address|journal|
//	volume|number|month|url|ee|cdrom|cite|publisher|note|crossref|isbn|series|school|chapter"> -->

<form id="form" action="UserInfoServlet"  method="post" name="form">

<c:forEach var="elements" items="${elements}">
<table >
 	<tr>
        <th>  </th>
        <th>Recorded info</th>
        <th>New Info</th>
        
    </tr>
	 <tr>
        <th>User Name</th>
        <td><h5> <em>  ${elements.username}</em> </h5></td>
        <td><input type="text" name = "username" /></td>
    </tr>
    <tr>
        <th>password</th>
        <td><h5> <em>  ${elements.password}</em> </h5></td>
        <td><input type="text" name = "password" /></td>
    </tr>
    <tr>
        <th>email</th>
        <td><h5> <em>  ${elements.email}</em> </h5></td>
        <td>Email cannot be change<!-- <input type="text" name = "email" /> --></td>
    </tr>
    <tr>
        <th>Year of Birth</th>
        <td><h5> <em>  ${elements.yearOfBirth}</em> </h5></td>
        <td><input type="text" name = "yearOfBirth" /></td>
    </tr>
    <tr>
        <th>address</th>
        <td><h5> <em>  ${elements.address}</em> </h5></td>
        <td><input type="text" name = "address" /></td>
    </tr>
    <tr>
        <th>Credit card number</th>
        <td><h5> <em>  ${elements.creditcard}</em> </h5></td>
        <td><input type="text" name = "creditcard" /></td>
    </tr>
</table>

</c:forEach>
 <input type = "submit" name = "updateInfo" value = "updateInfo"  />
</form>
</body>
</html>