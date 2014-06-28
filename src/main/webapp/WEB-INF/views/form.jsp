<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<c:if test="${!ajaxRequest}">
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
</c:if>
<ul class="nav nav-pills">
<li><a href="<c:url value="/"/>">Interactive Hospital ++</a></li>
  <li><a href="<c:url value="/"/>">Home</a></li>
  <li class="active"><a href="<c:url value="patientform"/>">Check your symptoms</a></li>
  <li><a href ="<c:url value="diagnose"/>">Let us Diagnose you</a></li>
</ul>

<div class="jumbotron">
<h2>Check some symptoms you have</h2>
	<form:form id="form" method="post" modelAttribute="patientBean" cssClass="cleanform" 
		action="/hospital-application/patientform/success">
  		<fieldset class="checkbox">
 			 <c:forEach items="${symptomList}" var="symptom" varStatus="status">
				<p><label><form:checkbox path="symptomNames" value="${symptom}"/> <c:out value="${symptom}"></c:out></label>
			</c:forEach>
	  	</fieldset>
	  	<div id = "error">
	  		<p id ="error"><form:errors path="symptomNames" cssClass="error"></form:errors>
	  	</div>
		<p><button type="submit" class = "btn btn-success">Diagnose</button></p>
	</form:form>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#form").submit(function() {  
			$.post($(this).attr("action"), $(this).serialize(), function(html) {
				$(".jumbotron").replaceWith(html);
				$('html, body').animate({ scrollTop: $("#message").offset().top }, 500);
			});
			return false;  
		});			
	});
</script>

<c:if test="${!ajaxRequest}">
</body>
</html>
</c:if>