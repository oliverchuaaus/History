<%@page import="java.util.function.Supplier"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.tougher.MyNestedBean"%>
<%@page import="com.tougher.MyBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	MyBean myBean = new MyBean();
	myBean.setNestedBean(new MyNestedBean());
	request.setAttribute("myBean", myBean);

	Supplier<List<String>> supplier = () -> {
		List<String> items = new ArrayList<>();
		items.add("One");
		items.add("Two");
		items.add("Three");
		return items;
	};
	request.setAttribute("items", supplier.get());
%>
<jsp:useBean id="myBean2" class="com.tougher.MyBean" />
<html>
<body>
	<h2>JSTL!</h2>

	Set:
	<ul>

		<li>set new value: <c:set var="code">code value</c:set> <c:out
				value="${code}" /></li>

		<li>set new bean: <c:set var="myBean3">
				<%=new MyBean()%>
			</c:set> <c:out value="${myBean3}" /></li>

		<li>set useBean field: <c:set target="${myBean2}" property="id"
				value="14344" /> <c:out value="${myBean2.id}" /></li>

		<li>set request bean field: <c:set target="${myBean}"
				property="id" value="14344" /> <c:out value="${myBean.id}" /></li>

		<li>set request nested bean field: <c:set
				target="${myBean.nestedBean}" property="nestedId" value="5254" /> <c:out
				value="${myBean.nestedBean.nestedId}" /></li>
	</ul>
	Out:
	<ul>
		<li>out literal: <c:out value="Hello World" /></li>
		<li>out request bean: <c:out value="${myBean}" /></li>
		<li>out useBean: <c:out value="${myBean2}" /></li>
		<li>out new bean: <c:out value="${myBean3}" /></li>
	</ul>

	Others:
	<ul>
		<li>remove value: <c:remove var="code" /> <c:out value="${code}" /></li>

		<li>if true: <c:if test="${true}"> will appear </c:if></li>

		<li>if false: <c:if test="${false}"> will not appear </c:if></li>

		<li>choose: <c:choose>
				<c:when test="${false}">will not appear</c:when>
				<c:when test="${false}">will not appear</c:when>
				<c:otherwise>otherwise will appear</c:otherwise>
			</c:choose></li>

		<li>forEach: <c:forEach var="item" items="${items}">
			<br>Item: <c:out value="${item}" />
				
			</c:forEach>
		</li>
	</ul>

</body>
</html>