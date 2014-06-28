<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Interactive Hospital ++</title>	
	<link href="<c:url value="/resources/bootstrap.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
</head>
<body>
<div id="background">
	<img src="resources/hospital.jpeg" class="stretch" alt="" />
</div>
<ul class="nav nav-pills">
<li><a href="<c:url value="/"/>">Interactive Hospital ++</a></li>
  <li class="active"><a href="#home">Home</a></li>
  <li><a href="<c:url value="patientform"/>">Check your symptoms</a></li>
  <li><a href ="<c:url value="diagnose"/>">Let us Diagnose you</a></li>
</ul>
<div class="jumbotron">
		<h2>Top Three Diseases</h2>
		<div class = "center">
			<c:forEach items="${topDiseases}" var="disease" varStatus="status">
	  			<h4> ${status.index + 1}.  - <c:out value="${disease}"/> (<c:out value="${disease.countSymptoms()}"/>)</h4>
			</c:forEach>
			<p>
		</div>
		<h2>Top Three Symptoms</h2>
		<div class = "center">
			<c:forEach items="${topSymptoms}" var="symptom" varStatus="status">
	  			<h4>${status.index + 1}.  - <c:out value="${symptom}"/> (<c:out value="${symptom.diseasesCount()}"/>)</h4>
			</c:forEach>
			<p>
		</div>
		<h3>We are the best hospital online! <br/>We have <c:out value="${uniqueSymptoms}"/> unique symptoms in our database.</h3>
</div>
</body>
</html>