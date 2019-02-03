<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/xssPost" method="POST">
		First Name: <input type="text" name="first_name"> <br /> Last
		Name: <input type="text" name="last_name" /> <input type="submit"
			value="Submit" />
	</form>

</body>

<script>
	$(document).ready(function() {
		
	});
</script>

</html>

