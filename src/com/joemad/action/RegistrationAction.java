package com.joemad.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.joemad.actionform.RegistrationForm;
import com.joemad.dao.UserDao;
import com.joemad.model.entity.User;
import com.joemad.util.EmailUtil;

public class RegistrationAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		RegistrationForm regForm = (RegistrationForm) form;
		UserDao userDao = new UserDao();
		String remoteAddress = request.getRemoteAddr();
		User user = userDao.persistUser(regForm.getName(), regForm.getUsername(), regForm.getPassword(), regForm.getEmail(),remoteAddress);
		if (user.getUserId()!=null) {
			response.getWriter().write("success");
			session.setAttribute("username", user.getUsername());
			sendVerificationEmail(user);
		} else {
			response.getWriter().write("error");
		}
		return null;
	}
	
	private void sendVerificationEmail(User user){
		try {
			String verificationUrl = "http://localhost/verifyemail.do?generatedcode="+user.getGeneratedCode();
			String subject = "Verification needed";
			StringBuilder body = new StringBuilder("Dear ").append(user.getName()).append(", please verify your account by cliking on this link:").append(verificationUrl);
			EmailUtil.sendEmail(user.getEmail(), subject, body.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
