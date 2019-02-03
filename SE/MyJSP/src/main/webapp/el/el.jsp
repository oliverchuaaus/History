<%@page import="java.util.function.Supplier"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.tougher.MyNestedBean"%>
<%@page import="com.tougher.MyBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	Supplier<List<String>> supplier = () -> {
		List<String> items = new ArrayList<>();
		items.add("One");
		items.add("Two");
		items.add("Three");
		return items;
	};
	request.setAttribute("items", supplier.get());
%>

<jsp:useBean id="myBean" class="com.tougher.MyBean" />
<jsp:useBean id="myBean2" class="com.tougher.MyBean" />
<jsp:useBean id="myNestedBean" class="com.tougher.MyNestedBean" />

<c:set target="${myBean}" property="id" value="14344" />
<c:set target="${myBean}" property="nestedBean" value="${myNestedBean}" />
<c:set target="${myBean.nestedBean}" property="nestedId" value="5254" />
<c:set var="num" value="12" />

<html>
<body>

	<h2>EL!</h2>

	Access:
	<ul>
		<li>bean: ${myBean}</li>
		<li>bean field: ${myBean.id}</li>
		<li>nested bean: ${myBean.nestedBean}</li>
		<li>nested bean field: ${myBean.nestedBean.nestedId}</li>
		<li>access list items: ${items[1]}</li>
	</ul>

	Compare:
	<ul>
		<li>==: ${items[2] == 'Three'}</li>
		<li>eq: ${items[2] eq 'Three'}</li>
		<li>!=: ${items[2] != 'three'}</li>
		<li>ne: ${items[2] ne 'three'}</li>

		<li>>: ${num > 10}</li>
		<li>gt: ${num gt 10}</li>
		<li>&lt;: ${num < 13}</li>
		<li>lt: ${num lt 13}</li>
		<li>>=: ${num >= 12}</li>
		<li>ge: ${num ge 12}</li>
		<li>&lt;=: ${num <= 12}</li>
		<li>le: ${num le 12}</li>

		<li>or: ${false or num le 12}</li>
		<li>||: ${false || num le 12}</li>
		<li>and: ${true and num le 12}</li>
		<li>&&: ${true && num le 12}</li>

		<li>!: ${!(num lt 12)}</li>
		<li>not: ${not (num lt 12)}</li>

		<li>empty object field: ${empty myBean2.nestedBean}</li>
		<li>empty int field: ${empty myBean2.anInt}</li>
		<li>non existent bean: ${empty myBean3}</li>
	</ul>

	Functions:
	<ul>
		<li>fn:toUpperCase: ${fn:toUpperCase('get this')}</li>
		<li>fn:containsIgnoreCase: ${fn:containsIgnoreCase("Can\'t touch this!","ouch")}</li>
	</ul>
</body>
</html>
