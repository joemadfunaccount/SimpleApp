package com.joemad.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.joemad.dao.MessageDao;
import com.joemad.dao.UserDao;
import com.joemad.model.entity.Message;
import com.joemad.model.entity.User;
import com.joemad.util.JsonUtil;

public class GetMessagesAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		MessageDao messageDao = new MessageDao();
		List<Message> unreadMessages = messageDao.getUnreadMessages(user.getUserId());
		setLastReadMessageId(user,unreadMessages);
		String formattedMessages = JsonUtil.getGson().toJson(unreadMessages);
		response.getWriter().write(formattedMessages);
		return null;
	}
	
	private void setLastReadMessageId(User user,List<Message> unreadMessages){
		if(unreadMessages != null && !unreadMessages.isEmpty()){
			Message lastMessage = unreadMessages.get(unreadMessages.size() -1);
			Long lastReadMessageId = lastMessage.getMessageId();
			UserDao userDao = new UserDao();
			userDao.updateLastReadMessage(user.getUserId(), lastReadMessageId);
		}
	}

}
