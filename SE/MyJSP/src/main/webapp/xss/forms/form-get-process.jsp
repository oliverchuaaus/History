<html>
<body>
	<form action="${pageContext.request.contextPath}/xssGet" method="GET">
		First Name: <input type="text" name="first_name" value="${queryStr}"> <br /> Last
		Name: <input type="text" name="last_name" /> <input type="submit"
			value="Submit" />
	</form>


</body>
</html>