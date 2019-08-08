package com.joemad.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.joemad.dao.UserDao;

public class VerifyUserEmailAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			String requestCode = request.getParameter("generatedcode");
			UserDao userDao = new UserDao();
			if(userDao.verifiedUser(username, requestCode)){
				response.getWriter().write("success");
			} else {
				response.getWriter().write("error");
			}
			return null;
	}
}
