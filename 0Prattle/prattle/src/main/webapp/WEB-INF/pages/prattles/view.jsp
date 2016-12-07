<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table>
	<s:url value="/prattles/${prattle.prattler.username}"
		var="prattler_url" />
	<tr>
		<td><img
			src="<s:url value="/resources/images/prattle_avatar.png"/>"
			width="48" height="48" /></td>
		<td><a href="${prattler_url}">${prattle.prattler.username}</a> <small><c:out
					value="${prattle.text}" /> <small><c:out
						value="${prattle.postedDate}" /></small></small> <s:url
				value="/prattles/${spittle.id}" var="prattler_url" /> <sf:form
				method="delete" action="${spittle_url}"
				name="deletePrattle_${spittle.id}" cssClass="deleteForm">
				<input type="submit" value="Delete" />
			</sf:form></td>
	</tr>
</table>
