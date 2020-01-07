<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.*" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>preCar</title>
<script>
function goList(){
	form.submit();
}
</script>
</head>
<body>
<form name = "form" method = "post" action ="<c:url value='/car/findAll' />">
<input type="button" value="¸ñ·Ï" onClick="goList()">
</form>
</body>
</html>