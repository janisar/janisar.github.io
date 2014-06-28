<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Interactive Hospital ++</title>	
	<link href="<c:url value="/resources/bootstrap.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/resources/jquery/1.6/jquery.js" />"></script>
</head>
<body>
	<div id="background">
		<img src="/hospital-application/resources/hospital.jpeg" class="stretch" alt="" />
	</div>
	<ul class="nav nav-pills">
		<li><a href="<c:url value="/"/>">Interactive Hospital ++</a></li>
 		<li><a href="<c:url value="/"/>">Home</a></li>
  		<li><a href="<c:url value="patientform"/>">Check your symptoms</a></li>
  		<li class="active"><a href ="<c:url value="diagnose"/>">Let us Diagnose you</a></li>
	</ul>
	<div class = "jumbotron">
		<h2 id ="query-header">Welcome to our diagnose side</h2>
		<p id = "separate">
		Answer some questions and you will get to know which disease you have.
		<form:form method = "GET" action = "diagnosing">
			<input type="submit" class ="btn btn-success" value="Begin diagnosing"/>
		</form:form>
		<p>
	</div>
</body>
</html>