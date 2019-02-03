<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
	
</script>

</head>
<body>
	<form action="${pageContext.request.contextPath}/xssPost" method="POST">
		First Name: <input id="fn" type="text" name="first_name" value="${queryStr}"> <br />
		Last Name: <input id="ln" type="text" name="last_name" /> <input
			type="submit" value="Submit" />
	</form>

</body>

<script>
	$(document).ready(function() {
		console.log("${queryStr}");
		<%--
		$("#fn").val("${queryStr}");
		$("#fn").html('<input id="fn" type="text" name="first_name" value="${queryStr}"> ');
		--%>
	});
</script>
</html>