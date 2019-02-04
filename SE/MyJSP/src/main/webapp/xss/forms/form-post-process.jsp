<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
	
</script>

</head>
<body>
	<form action="${pageContext.request.contextPath}/xssPost" method="POST">
		First Name: <input id="fn" type="text" name="first_name"
			value="${queryStr}"> <br /> Last Name: <input id="ln"
			type="text" name="last_name" /> <input type="submit" value="Submit" />
	</form>

	<div id="summary"></div>

</body>

<script>
	$(document)
			.ready(
					function() {
						console.log("${queryStr}");

						function htmlEncode(value) {
							// Create a in-memory div, set its inner text (which jQuery automatically encodes)
							// Then grab the encoded contents back out. The div never exists on the page.
							return $('<textarea>').text(value).html();
						}

						function htmlDecode(value) {
							return $('<textarea>').html(value).text();
						}
						
						var output = htmlEncode($("#fn").val());
						$("#summary").html(output);
<%--
		//doesn't work
		document.getElementById("fn").value = "${queryStr}";
		//doesn't work
		$("#fn").val("${queryStr}");
		//doesn't work
		$("#fn").html('<input id="fn" type="text" name="first_name" value="${queryStr}"> ');
		//doesn't work
		$('#fn').attr('value', '${queryStr}');
--%>
	});
</script>
</html>