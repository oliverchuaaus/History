<%@page import="java.util.Date"%>
<html>
<body>
	<h2>Expressions!</h2>

	<ul>
		<li>new Date(): <%=new Date()%></li>
		<li>Literal String "Test": <%="Test"%></li>
	</ul>

	Escaping literals:
	<ul>
		<li><\% - jsp literal needs to written as &lt;\%</li>
		<li>%> - jsp literal no need to escape</li>
	</ul>


	Escaping string literals:
	<ul>
		<li><%= "<%" %> - string literal, no need to escape</li>
		<!-- jsp error on %\> string literal, but it is valid and works-->
		<li><%= "%&gt;" %> - string literal, need to escape with %\></li>
	</ul>

	Escape characters in string literal:
	<ul>
		<li>\b: <%="*\b*"%></li>
		<li>\t: <%="*\t*"%></li>
		<li>\f: <%="*\f*"%></li>
		<li>\n: <%="*\n*"%></li>
		<li>\r: <%="*\r*"%></li>
		<li>\": <%="*\"*"%></li>
		<li>\': <%="*\'*"%></li>
		<li>\ : <%="*\\*"%></li>

		<li>&lt; : <%="*<*"%></li>
		<li>% : <%="*%*"%></li>
		<li>> : <%="*>*"%></li>
		<li>&lt;%> : <%="*&lt;%&gt;*"%></li>
	</ul>

</body>
</html>
