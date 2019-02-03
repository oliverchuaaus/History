<%@page import="java.util.Date"%>
<%@page isErrorPage="true"%>
<%@page info="jsp"%>
<%@page session="false"%>

<%
	new Date();
%>
<html>
<body>
	<h2>Directives!</h2>

	Page Directives
	<ul>
		<li>import="package.name"</li>
		<li>isErrorPage="true", and so</li>
		<li>exception is available: <%=exception %></li>
		<li>session="false", and so session is not available and</li>
		<li>session: <%=pageContext.getSession()%></li>
	</ul>

	Include Directives
	<ul>
		<li>file="file.jsp"</li>
	</ul>

</body>
</html>
