<jsp:useBean id="myBean1" class="com.tougher.MyBean"></jsp:useBean>
<jsp:setProperty name="myBean1" property="id" value="14344" />

<jsp:useBean id="myBean2" class="com.tougher.MyBean">
	<jsp:setProperty name="myBean2" property="id" value="5254" />
</jsp:useBean>


<html>
<body>
	<h2>tags: element, attribute, body!</h2>

	<jsp:element name="div">
         <jsp:attribute name="id">desc</jsp:attribute>
         <jsp:attribute name="style">color:red</jsp:attribute>
         <jsp:body>Body for XML element</jsp:body>
    </jsp:element>
</body>
</html>
