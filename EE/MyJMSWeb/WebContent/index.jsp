<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>rr-jms</title>
  </head>
  <body>
    <h1>Start the longProcess....</h1>
    <form name="appForm" action="StartLongProcess">
    	Your name: <input type="text" name="name" size="40"><br/>
    	<br/><br/>
    	<input type="submit" value="Kick-off The Long Process"/>
    </form>
    <br/>
    <c:if test="${started}">
    	The message was sent to the queue. Look at the console where you started JBoss. <br/>
    	You should see "Doing Long Process.." and then a "Completed Long Process" when it's finished.<br/>
    	You could even submit this form several times before the first long process has even<br/> 
    	completed (use a different name).<br/>
    </c:if>
  </body>
</html>