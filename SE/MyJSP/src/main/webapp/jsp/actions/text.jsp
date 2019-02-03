<%@page isELIgnored="false"%>

<%!int content = 4;%>
<html>
<body>
	<h2>text!</h2>

	Use &lt;jsp:text> as template data. Can only contain text and EL
	expressions.

	<ul>
		<li>normal:
			<div>
				<div>div inside book tags</div>
			</div>
		</li>

		<li>with jsp:text and cdata:
			<div><jsp:text>
					<![CDATA[<div>div inside book tags</div>]]>
				</jsp:text></div>
		</li>

		<li>with el:
			<div>
				<jsp:text>
				 ${content > 3}  ${content < 3} ${content != 3}
			</jsp:text>
			</div>
		</li>
	</ul>
</body>
</html>
