<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin Page</title>
</head>
<body>
	<jsp:include page="layout/adminHeader.jsp"/>

	<div id="AllList">
	</div>
	
	<div id="pagination">         
	</div>
         
	<jsp:include page="layout/adminFooter.jsp"/>
	
	<script type="text/javascript" src="/resources/adminJs/admin.js"></script>    
</body>
</html>