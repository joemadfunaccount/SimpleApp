<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE html>
<html>
<head>
	<title>JoeMad - Register</title>
	<link rel="stylesheet" type="text/css" href="css/mycss.css">
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
</head>
<body>

<div class="mainContainer">
	<div class="containerTitle">
		Registeration
	</div>

	<div class="regContainer">
		<html:form class="regForm" action="/register">
			<html:text styleClass="reginputText" property= "name" value="Name" />
			<html:text styleClass="reginputText" property="username" value="Username" />
			<html:text styleClass="reginputText" property="email" value="Email" />
			<html:text styleClass="reginputText" property="password" value="Password" />
			<html:submit value="Register" class="registerButton"/>
		</html:form>
	</div>

	<div class="regFooter">
		No Copyright and Sh*t
	</div>
	
</div> 
<script type="text/javascript" src="js/myscript.js"></script>
</body>
</html>