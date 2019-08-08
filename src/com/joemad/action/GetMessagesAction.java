package com.joemad.action;

import java.lang.reflect.Type;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.joemad.dao.MessageDao;
import com.joemad.dao.UserDao;
import com.joemad.model.entity.User;
import com.joemad.util.JsonUtil;

public class GetMessagesAction extends Action {
	private MessageDao messageDao = new MessageDao();
	private UserDao userDao = new UserDao();
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		String unreadMessages = messageDao.getUnreadMessages(user.getUserId());
		setLastReadMessageId(user,unreadMessages);
		response.getWriter().write(unreadMessages);
		return null;
	}
	
	private void setLastReadMessageId(User user,String unreadMessages){
		if(unreadMessages != null && !"".equals(unreadMessages.trim()) && !"[]".equals(unreadMessages)){
			try {
			Type listType = new TypeToken<List<List<Object>>>(){}.getType();
			List<List<Object>> unreadMessagesList = JsonUtil.getGson().fromJson(unreadMessages, listType );
			List<Object> lastMessageListObj = unreadMessagesList.get(unreadMessagesList.size() -1);
			LinkedTreeMap<String,Object> lastMessageObj = (LinkedTreeMap<String,Object>) lastMessageListObj.get(0);
			Long lastMessageId = 0L;
			String messageIdStr = lastMessageObj.get("messageId")+"";
			messageIdStr = messageIdStr.substring(0,messageIdStr.indexOf("."));
			lastMessageId = Long.parseLong(messageIdStr);  
			userDao.updateLastReadMessage(user.getUserId(), lastMessageId);
			}
			catch(Exception e) {
				e.printStackTrace();
			}		
		}
	}

}
