var messagesContainerHeight = $(".messagesContainer").height();
var toInfinityAndBeyond = 99999999999999999;
$(".messageInput")
		.on(
				"keypress",
				function(e) {
					$(".messageInput").html("");
					if (e.which == 13) {
						e.preventDefault();
						var messagesContainer = $(".messagesContainer");
						messagesContainer
								.append("<div class='message' style='display:none; word-break:break-all; font-family:sans-serif; margin:2px;'><span style='font-weight:bold; font-family:sans-serif; color:#0000ee; font-size:17px;'>Mohamed Youssef</span><span>: </span><span>Hi Joe</span></div>");
						$(".messagesContainer .message").fadeIn("slow");
						$(".messageInput").val("");
						messagesContainer.scrollTop(toInfinityAndBeyond);
					}
				});

$(".currentPeople")
		.append(
				"<div style='font-weight:bold; font-family:sans-serif; color:#0000ee; font-size:17px; text-align:center;'>Mohamed Youssef</div>");

function resetUsername() {
	$("#username").val("");
}

function resetPassword() {
	$("#password").val("");
}