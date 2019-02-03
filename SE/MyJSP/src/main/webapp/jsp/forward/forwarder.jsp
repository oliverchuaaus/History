<html>
<body>
	<h2>Forwarder!</h2>
</body>
</html>

<%!String filename = "forwarded.jsp";%>
<%
	request.setAttribute("requestAttribute", "requestAttributeValue");
	session.setAttribute("sessionAttribute", "sessionAttributeValue");
	request.setAttribute("requestFile", "forwarded.jsp");
%>

<p>
<h4>forward directive request time:</h4>
<jsp:forward page='<%=request.getAttribute("requestFile").toString()%>'>
	<jsp:param name="forwardParam" value="forwardParamValue"></jsp:param>
</jsp:forward>
