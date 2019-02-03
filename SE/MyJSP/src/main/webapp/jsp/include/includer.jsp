<html>
<body>
	<h2>Includer!</h2>
</body>
</html>

<%!String filename = "included.jsp";%>
<%
	request.setAttribute("requestAttribute", "requestAttributeValue");
	session.setAttribute("sessionAttribute", "sessionAttributeValue");
	request.setAttribute("requestFile", "included.jsp");
%>

<p>
<h4>include directive translate time:</h4>
<%@ include file="included.jsp"%>

<p>
<h4>include action translate time:</h4>
<jsp:include page="included.jsp">
	<jsp:param name="includeParam" value="includeParamValue"></jsp:param>
</jsp:include>

<p>
<h4>include directive request time:</h4>

Not possible!

<p>
<h4>include action request time:</h4>

<jsp:include page='<%=request.getAttribute("requestFile").toString()%>'>
	<jsp:param name="includeParam" value="includeParamValue"></jsp:param>
</jsp:include>





<%--

All works as well: 
<%@ include file="included.jsp"%>
<%@ include file="./included.jsp"%>
<%@ include file="/include/included.jsp"%>


<jsp:include page="included.jsp">
<jsp:include page="./included.jsp">
<jsp:include page="/include/included.jsp">

--%>

