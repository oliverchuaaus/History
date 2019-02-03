<html>
<body>
	<ul>
		<li>First Name: <%=request.getParameter("first_name")%>
		</li>
		<li>Last Name: <%=request.getParameter("last_name")%>
		</li>
	</ul>

	<div>RequestScope: ${requestScope}</div>
	<div>Param: ${param}</div>
	<div>ParamValues: ${paramValues}</div>
	<div>First Name: ${param.first_name}</div>
	<div>Last Name: ${param['last_name']}</div>
</body>
</html>