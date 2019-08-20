var messagesContainerHeight = $(".messagesContainer").height();
var toInfinityAndBeyond = 99999999999999999;
var messagesContainer = $(".messagesContainer");
var currentPeople = $(".currentPeople");
$(".messageInput")
		.on(
				"keypress",
				function(e) {
					$(".messageInput").html("");
					if (e.which == 13) {
						e.preventDefault();
						var dataToBeSent = $("#messageFormId").serialize();
						$.ajax({
							url: "http://localhost/sendMessage.do",
							method: "POST",
							context: document.body,
							data: dataToBeSent,
							dataType: "text" 
						}).done(function(result) {
						   	console.log("Message Sent");
						  })
						  .fail(function(error) {
						    console.log("Some Shit Happened");
						  })
						  .always(function() {
						    console.log("Request Complete");
						  });	
						$(".messageInput").val("");
						messagesContainer.scrollTop(toInfinityAndBeyond);
					}
				});

setInterval(function(){
						$.ajax({
						url: "http://localhost/getMessages.do",
						method: "POST",
						context: document.body,
						data: "",
						dataType: "text" 
						}).done(function(result) {
							var jsonResult = JSON.parse(result);
							jsonResult.forEach(function(resultValue,index){
							messagesContainer.append("<div class='message' style='display:none; word-break:break-all; font-family:sans-serif; margin:2px;'><span style='font-weight:bold; font-family:sans-serif; color:#0000ee; font-size:17px;'>"+resultValue[1]+"</span><span>: </span><span>"+resultValue[0].message+"</span></div>");	
							});
							$(".messagesContainer .message").fadeIn(300);
						   	
						})
						  .fail(function(error) {
						    console.log("Some Shit Happened");
						})
						  .always(function() {
						    console.log("Request Complete");
						});	
						messagesContainer.scrollTop(toInfinityAndBeyond);
},1000);

setInterval(function(){
						$.ajax({
						url: "http://localhost/getCurrentPeopleAction.do",
						method: "POST",
						context: document.body,
						data: "",
						dataType: "text" 
						}).done(function(result) {
							currentPeople.html("");
							if(result == ""){
								return;
							}
							var jsonResult = JSON.parse(result);
							jsonResult.forEach(function(resultValue,index){
							currentPeople.append("<div style='font-weight:bold; font-family:sans-serif; color:#0000ee; font-size:17px; text-align:center;'>"+resultValue+"</div>");
							});
						})
						  .fail(function(error) {
						    console.log("Some Shit Happened");
						})
						  .always(function() {
						    console.log("Request Complete");
						});	
},2000);


setInterval(function(){
						$.ajax({
						url: "http://localhost/userActive.do",
						method: "POST",
						context: document.body,
						data: "",
						dataType: "text" 
						}).done(function(result) {
						   console.log("User Active Request Done");
						})
						  .fail(function(error) {
						    console.log("Some Shit Happened");
						})
						  .always(function() {
						    console.log("Request Complete");
						});	
},2000);

