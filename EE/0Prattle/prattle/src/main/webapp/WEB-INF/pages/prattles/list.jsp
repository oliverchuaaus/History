<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
	<h2>Prattles for ${prattler.username}</h2>

	<script>
		function deleteSpittle(id) {
			if (confirm("Are you sure you want to delete this prattle?")) {
				document["deleteSpittle_" + id].submit();
			}
		}
	</script>

	<table class="horizontalRuled">
		<c:forEach items="${prattleList}" var="prattle">
			<s:url value="/prattlers/${prattle.prattler.username}"
				var="prattler_url" />
			<tr>
				<td><a href="${prattler_url}">${prattle.prattler.username}</a> <small><c:out
							value="${prattle.text}" /><br />
					<small> <fmt:formatDate value="${prattle.postedDate}"
								pattern="hh:mma MMM d, yyyy" />
					</small></small> <s:url value="/prattles/delete/${prattle.id}" var="prattle_url" /> <sf:form
						method="DELETE" action="${prattle_url}"
						name="deletePrattle_${prattle.id}" cssClass="deleteForm">
						<input type="submit" value="Delete" />
					</sf:form></td>
			</tr>
		</c:forEach>
	</table>
</div>
