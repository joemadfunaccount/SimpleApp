package com.joemad.action;

import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.joemad.actionform.LoginForm;
import com.joemad.dao.UserDao;
import com.joemad.model.Constants;
import com.joemad.model.CurrentPeople;
import com.joemad.model.entity.User;

public class LoginAction extends Action {
	private UserDao userDao = new UserDao();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LoginForm loginForm = (LoginForm) form;
		User user = new User();
		Boolean isLoginSuccessful = isLoginSuccessful(loginForm, request);
		user = (User) request.getAttribute("user");
		if (isLoginSuccessful) {
			HttpSession session = request.getSession();
			session.setAttribute("loggedInUser", user);
			CurrentPeople.addUser(user);
			if (user.getVerified() == 0) {
				response.getWriter().write("Please confirm your email address!");
			} 
			else if(user.getStatus() == Constants.Status.Blocked.ordinal()) {
				response.getWriter().write("You have been blocked, please contact the system adminstrator!");
			}
			else if(user.getStatus() == Constants.Status.Timeout.ordinal()) {
				response.getWriter().write("You have been timed-out, please try logging in at a later time!");
			}
			else {
				userDao.resetFailedLoginAttempts(user.getUserId());
				return mapping.findForward("loginSuccess");
			}
		} else {
			if (user!=null && user.getUserId() != null) {
				if (user.getFailedLoginAttempts() > 10) {
					userDao.lockUser(user.getUserId());
					response.getWriter().write("Too many failed login attempts, you have been locked, please contact the system administrator!");
				} else {
					userDao.incrementFailedLoginAttempts(user.getUserId());
					response.getWriter().write("Incorrect Username or Password!");
				}
			} else {
				response.getWriter().write("Username doesn't exist, please, make sure you have entered the correct username!");
			}
		}
		return null;
	}

	private boolean isLoginSuccessful(LoginForm loginForm, HttpServletRequest request) {
		User user = userDao.getUserByUsername(loginForm.getUsername());
		request.setAttribute("user", user);
		if (user != null) {
			String decodedPassword = new String(Base64.getDecoder().decode(user.getPassword()));
			if (loginForm.getPassword().equals(decodedPassword)) {
				return true;
			}
		}
		return false;
	}

}
