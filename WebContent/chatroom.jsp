<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE html>
<html>
<head>
	<title>JoeMad - ChatRoom</title>
	<link rel="stylesheet" type="text/css" href="css/mycss.css">
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
</head>

<body>

<div class="mainContainer">
	<div class="chatContainer">
		<div class="messagesContainer">
		</div>

		<div class="currentPeople"> 
		</div>

		<div class="messageArea">
			<html:form action="/sendMessage" styleId="messageFormId">
			<html:textarea styleId="messageId" styleClass="messageInput" property="message">Your message here...</html:textarea>
			</html:form>
		</div>
	</div>	


</div> 
<script type="text/javascript" src="js/chatroom.js"></script>
</body>
</html>