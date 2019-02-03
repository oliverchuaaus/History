<%
	System.out.println("Inside scriptlet");
%>
<jsp:scriptlet>System.out.println("Inside jsp scriptlet");</jsp:scriptlet>


<html>
<body>
	<h2>Scriptlets!</h2>
	
	<p>
	Hello
	<%
	out.println("interspersed");
	%>
	World
	
</body>
</html>
