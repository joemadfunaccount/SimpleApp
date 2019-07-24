package com.joemad.actionform;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import com.joemad.util.ValidationUtil;

public class RegistrationForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private String username;
	private String password;

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		name = null;
		email = null;
		username = null;
		password = null;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if (name == null || "".equals(name)) {
			errors.add("name", new ActionMessage("error.validation.name.empty"));
		}
		if (email == null || "".equals(email.trim())) {
			errors.add("message", new ActionMessage("error.validation.email.empty"));
		} else {
			if(!ValidationUtil.isEmailValid(email)) {
				errors.add("message", new ActionMessage("error.validation.email.malformedformat"));
			}
		}
		if (username == null || "".equals(username)) {
			errors.add("username", new ActionMessage("error.validation.username.empty"));
		}
		if (password == null || "".equals(password)) {
			errors.add("password", new ActionMessage("error.validation.password.empty"));
		}
		return errors;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
