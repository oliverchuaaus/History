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
<SPAN CLASS="pageHeader">Update Unit Done</SPAN>
<html:form action="/SearchUnit">
	<TABLE CLASS="messageTable">
		<TR>
			<TH COLSPAN="2" CLASS="messageHeading">The chosen unit has been
			updated in the database with the following details:</TH>
		</TR>
		<TR>
			<TD CLASS="messageHeading">Unit Name</TD>
			<TD><c:out value="${unit.unitName}"/></TD>
		</TR>
		<TR>
			<TD CLASS="messageHeading">Super Unit</TD>
			<TD><c:out value="${unit.superUnit.unitName}" /></TD>
		</TR>
		<TR>
			<TD COLSPAN="2" ALIGN="center"><INPUT CLASS="formButton"
				TYPE="submit" VALUE="Ok"></TD>
		</TR>
	</TABLE>
	<html:hidden property="command" value="search"/>
</html:form>

</BODY>
</HTML>
