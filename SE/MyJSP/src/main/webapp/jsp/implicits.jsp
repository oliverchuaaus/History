
<html>
<body>
	<h2>Implicits!</h2>

	<ul>
		<li>session: <%=session%></li>
		<li>request: <%=request%></li>
		<li>page: <%=page%></li>

		<li>application: <%=application%></li>
		<li>config: <%=config%></li>
		<li>pageContext: <%=pageContext%></li>

		<li>response: <%=response%></li>
		<li>out: <%=out%></li>
		
		<li>out.bufferSize: <%=out.getBufferSize()%></li>
		<li>pageContext.release: <%=pageContext.getSession()%></li>
	</ul>
	
</body>
</html>
