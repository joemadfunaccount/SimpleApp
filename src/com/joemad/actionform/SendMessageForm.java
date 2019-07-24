package com.joemad.actionform;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class SendMessageForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String message;
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		userId = null;
		message = null;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if (userId == null) {
			errors.add("userId", new ActionMessage("error.validation.userid.empty"));
		}
		if (message == null || "".equals(message.trim())) {
			errors.add("message", new ActionMessage("error.validation.message.empty"));
		}
		return errors;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
