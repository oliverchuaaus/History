<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<TITLE>Intranet</TITLE>
<LINK REL="stylesheet"
	HREF='<html:rewrite page="/pages/css/miniintranet.css" />'>
</HEAD>


<BODY CLASS="body">
<SPAN CLASS="pageHeader">Delete Unit Confirmation</SPAN>
<html:form action="/DeleteUnit">
	<TABLE CLASS="messageTable">
		<TR>
			<TH COLSPAN="2" CLASS="messageHeading">The following unit will be
			deleted in the database:</TH>
		</TR>
		<TR>
			<TD CLASS="messageHeading">Unit Name</TD>
			<TD><c:out value="${unit.unitName}" /></TD>
		</TR>
		<TR>
			<TD CLASS="messageHeading">Super Unit</TD>
			<TD><c:out value="${unit.superUnit.unitName}" /></TD>
		</TR>
		<TR>
			<TD ALIGN="center"><html:cancel styleClass="formButton">
				Cancel Delete
			</html:cancel></TD>
			<TD ALIGN="center"><INPUT TYPE="submit" CLASS="formButton"
				VALUE="Confirm Delete"></TD>
		</TR>
	</TABLE>
	<html:hidden property="command" value="delete"/>
</html:form>

</BODY>
</HTML>
