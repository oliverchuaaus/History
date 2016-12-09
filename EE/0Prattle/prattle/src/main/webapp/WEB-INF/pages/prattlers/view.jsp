<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<h2>Prattler</h2>
<h3>${prattler.username}</h3>
<p>${prattler.fullName}</p>

<a class="join" href="<s:url value="/prattlers/${prattler.username}/prattles"/>">See User prattlers!</a>
