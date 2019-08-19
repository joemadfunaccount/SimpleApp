<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html>

<% 
if(session.getAttribute("loggedInUser") != null){
	String nextJSP = "/chatroom.jsp";
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
	dispatcher.forward(request,response);
} 
%>

<html>
<head>
	<title>JoeMad - Login</title>
	<link rel="stylesheet" type="text/css" href="css/mycss.css">
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
</head>
<body>
<div class="mainContainer">
	<div class="containerTitle">
		Welcome
	</div>
	<div class="loginContainer">
		<html:form action="/login" styleClass="formContainer">
			<html:text property="username" styleClass="inputText" value="Username" styleId="username" onfocus="resetUsername()"/>
			<html:password property="password" styleClass="inputText" value="Password" styleId="password" onfocus="resetPassword()"/>
			<html:submit value="Login" styleClass="loginButton"/>
		</html:form>
	</div>
	<div class="containerFooter">
		No Copyright and Sh*t
	</div>
</div> 
<script type="text/javascript" src="js/myscript.js"></script>
</body>
</html>