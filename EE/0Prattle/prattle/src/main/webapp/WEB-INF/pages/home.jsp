<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>

<div>
	<h2>A global community of friends and strangers spitting out their
		inner-most and personal thoughts on the web for everyone else to see.</h2>
	<h3>Look at what these people are spitting right now...</h3>

	<ol class="spittle-list">
		<c:forEach var="prattle" items="${prattles}">

			<s:url value="/prattlers/{prattlerName}" var="prattler_url">
				<s:param name="prattlerName" value="${prattle.prattler.username}" />
			</s:url>

			<li><span class="spittleListImage"> <img
					src="/images/avatar_${prattle.prattler.id}.jpg"
					width="48" border="0" align="middle"
					onError="this.src='<s:url value="/resources/images"/>/spitter_avatar.png';" />
			</span> 
			
			<span class="spittleListText"> <a href="${prattler_url}">
						<c:out value="${prattle.prattler.username}" />
				</a> - <c:out value="${prattle.text}" /><br /> <small><fmt:formatDate
							value="${prattle.postedDate}" pattern="hh:mma MMM d, yyyy" /></small>
			</span></li>
		</c:forEach>
	</ol>
</div>
