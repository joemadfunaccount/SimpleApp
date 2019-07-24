package com.joemad.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.joemad.actionform.SendMessageForm;
import com.joemad.dao.MessageDao;
import com.joemad.dao.UserDao;
import com.joemad.model.entity.User;

public class SendMessageAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SendMessageForm sendForm = (SendMessageForm) form;
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		MessageDao messageDao = new MessageDao();
		UserDao userDao = new UserDao();
		messageDao.persistMessage(loggedInUser.getUserId(), sendForm.getMessage());
		String remoteAddress = request.getRemoteAddr();
		userDao.updateUserAddress(loggedInUser.getUserId(), remoteAddress);
		return null;
	}
}
