<jsp:useBean id="myBean1" class="com.tougher.MyBean"></jsp:useBean>
<jsp:setProperty name="myBean1" property="id" value="14344" />

<jsp:useBean id="myBean2" class="com.tougher.MyBean">
	<jsp:setProperty name="myBean2" property="id" value="5254" />
</jsp:useBean>


<html>
<body>
	<h2>useBean!</h2>

	<ul>
		<li>myBean1.id: <jsp:getProperty name="myBean1" property="id" />
		</li>
		<li>Cannot set and get nested bean without using EL 
		</li>
		<li>myBean2.id: <jsp:getProperty name="myBean2" property="id" />
		<p>Nested setProperty only sets if object created via useBean wrapper.
		</li>
	</ul>

</body>
</html>
