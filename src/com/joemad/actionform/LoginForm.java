package com.joemad.actionform;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LoginForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	public LoginForm() {
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		username = null;
		password = null;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if (username == null || "".equals(username.trim())) {
			errors.add("username", new ActionMessage("error.validation.username.empty"));
		}
		if (password == null || "".equals(password.trim())) {
			errors.add("password", new ActionMessage("error.validation.password.empty"));
		}
		return errors;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
