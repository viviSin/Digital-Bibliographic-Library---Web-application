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

	(Right now this only searches through titles)
	<form method="POST" action="add_listing">
			<input type="text" name="searchString" />
			<input type="hidden" name="action" value="searchListing" />
			<input type="submit" value="Search" />
	</form>

</body>
</html>