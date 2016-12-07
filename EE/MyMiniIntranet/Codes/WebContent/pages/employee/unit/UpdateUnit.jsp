<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<TITLE>Intranet</TITLE>
<LINK REL="stylesheet"
	HREF='<html:rewrite page="/pages/css/miniintranet.css" />'>
	<script src="<html:rewrite page="/pages/js/miniintranet.js" />"></script>	
<html:javascript formName="UpdateUnitForm" staticJavascript="false" />
</HEAD>

<BODY CLASS="body">
<SPAN CLASS="pageHeader">Update Unit</SPAN>
<html:form action="/UpdateUnit"
	onsubmit="return validateUpdateUnitForm(this);">
	<TABLE CLASS="formTable">
		<TR>
			<TD CLASS="formLabel">Unit Name</TD>
			<TD CLASS="formField"><html:text property="unitName" /></TD>
		</TR>
		<TR>
			<TD CLASS="formLabel">Super Unit</TD>
			<TD><html:select property="superUnitCode" styleClass="formField">
				<html:optionsCollection name="unitList" value="unitCode"
					label="unitName" />
			</html:select></TD>
		</TR>
		<TR>
			<TD COLSPAN="2" ALIGN="right"><INPUT TYPE="submit" CLASS="formButton"
				VALUE="Update Unit"></TD>
		</TR>
	</TABLE>
	<html:hidden property="command" value="update"/>
	</html:form>
</BODY>
</HTML>
