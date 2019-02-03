<%!public void jspInit() {
		System.out.println("Initialise jsp");
	}%>
<%!public void jspDestroy() {
		System.out.println("Destroy jsp");
	}%>

<%!int number1 = 94;%>
<jsp:declaration>int number2 = 100;</jsp:declaration>
<%!int number3 = 88;
	int number4 = 42;%>

<html>
<body>
	<h2>Declarations!</h2>

	<ul>
		<li>number1: <%=number1%></li>
		<li>number2: <%=number2%></li>
		<li>number3: <%=number3%></li>
		<li>number4: <%=number4%></li>
	</ul>
</body>
</html>
