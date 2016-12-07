<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>

<!DOCTYPE html public "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Intranet</TITLE>
<LINK REL="stylesheet"
	HREF='<html:rewrite page="/pages/css/miniintranet.css" />'>
<SCRIPT>
function toggle(index){
	document.SearchUnitForm.searchType[index].click();
}
</SCRIPT>
</HEAD>
<BODY CLASS="body">
<SPAN CLASS="pageHeader">Search/View Unit List</SPAN>
<html:form action="/SearchUnit">
	<TABLE CLASS="formTable" >
		<TR>
			<TD CLASS="formField"><html:radio property="searchType"
				value="SEARCH_BY_UNIT_SUBSTRING" /></TD>
			<TD CLASS="formLabel">Unit Name</TD>
			<TD><html:text styleClass="formField" property="unitSubstring"
				onchange="toggle('0')" /> <SPAN CLASS="formComment">(substring
			search)</SPAN></TD>
		</TR>
		<TR>
			<TD CLASS="formField"><html:radio property="searchType"
				value="SEARCH_BY_SUPER_UNIT" /></TD>
			<TD CLASS="formLabel">Super Unit</TD>
			<TD><html:select property="superUnitCode" onchange="toggle('1')" styleClass="formField">
				<html:optionsCollection name="unitList" value="unitCode"
					label="unitName" />
			</html:select></TD>
		</TR>
		<TR>
			<TD COLSPAN="3" ALIGN="center"><INPUT TYPE="submit" NAME="search"
				VALUE="Search" CLASS="formButton"></TD>
		</TR>
		<TR>
			<TD COLSPAN="3" ALIGN="center"><html:link styleClass="resultsLink"
				action="/AddUnitInput?command=addInput">Add New Unit</html:link></TD>
		</TR>
	</TABLE>
</html:form>
<BR />
<c:if test="${not newPage && not empty unitListResults}">
	<TABLE CLASS="resultsTable" id="unitList">
		<TR>
			<TH COLSPAN="3" ALIGN="left" CLASS="resultsHeader">Units</TH>
		</TR>
		<c:forEach var="unit" items="${unitListResults}" varStatus="status">
			<c:choose>
				<c:when test="${status.count%2 eq 0}">
					<TR CLASS="resultsEven">
				</c:when>
				<c:otherwise>
					<TR CLASS="resultsOdd">
				</c:otherwise>
			</c:choose>
			<TD><c:out value="${unit.unitName}" /></TD>
			<TD><html:link styleClass="resultsLink" action="/UpdateUnitInput?command=updateInput"
				paramId="unitCode" paramName="unit" paramProperty="unitCode">
					Update
				</html:link></TD>
			<TD><html:link styleClass="resultsLink" action="/DeleteUnitInput?command=deleteInput"
				paramId="unitCode" paramName="unit" paramProperty="unitCode">
					Delete
				</html:link></TD>
			</TR>
		</c:forEach>
		<pg:pager
			maxPageItems="<%=Integer.parseInt((String)request.getAttribute("maxPageItems"))%>"
			maxIndexPages="<%=Integer.parseInt((String)request.getAttribute("maxIndexPages"))%>"
			items="<%=((Integer)request.getAttribute("resultCount")).intValue()%>"
			url="./SearchUnit.do" export="currentPageNumber=pageNumber">
			<TR>
				<TD COLSPAN="3"><pg:index>
					<pg:prev>
						<a class="pagerLink" href="<%= pageUrl %>">&lt;&lt;Previous</a>
					</pg:prev>
					<pg:pages>
						<c:choose>
							<c:when test="${pageNumber==currentPageNumber}">
								<span class="pagerLinkSelected"><%=pageNumber%></span>
							</c:when>
							<c:otherwise>
								<a class="pagerLink" href="<%= pageUrl %>"><%=pageNumber%></a>
							</c:otherwise>
						</c:choose>
					</pg:pages>
					<pg:next>
						<a class="pagerLink" href="<%= pageUrl %>">Next&gt;&gt;</a>
					</pg:next>
				</pg:index></TD>
			</TR>

		</pg:pager>
	</TABLE>
</c:if>
<c:if test="${not newPage && empty unitListResults}">
	<TABLE id="noUnitList">
		<TR>
			<TD colspan="3" class="messageTable"><span class="messageHeading"><bean:message
				key="search.noMatchingRecords" /></span></TD>
		</TR>
	</TABLE>
</c:if>
</BODY>
</HTML>
