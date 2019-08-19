package com.joemad.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.joemad.model.Connection;
import com.joemad.model.entity.Message;
import com.joemad.model.entity.User;
import com.joemad.util.ConnectionManager;
import com.joemad.util.JPAUtil;
import com.joemad.util.JsonUtil;


public class MessageDao implements Serializable {
	private static final long serialVersionUID = 1L;
	private static UserDao userDao = null;
	
	static {
		userDao = new UserDao();
	}
	
	public String getUnreadMessages(Long userId) {
		String messages = "";
		User user = userDao.getUser(userId);
		Long lastReadMessageId = user.getLastReadMessageId();
		Connection activeConnection = getActiveConnection();
		EntityManager entityManager = activeConnection.getEntityManager();
		Query query = entityManager.createQuery("SELECT m, u.name FROM Message m inner join User u on m.userId = u.userId where m.messageId > :lastReadMessageId order by m.messageId ASC");
		query.setParameter("lastReadMessageId", lastReadMessageId);
		try {
			List<Object> unreadMessages = query.getResultList();
			messages = JsonUtil.getGson().toJson(unreadMessages);
		}catch(Exception e) {
			e.printStackTrace();
		}
		ConnectionManager.returnConnectionToThePool(activeConnection);
		return messages;
	}
	
	
	public boolean persistMessage(Long userId, String message){
		try {
			Message newMessage = new Message();
			newMessage.setCreationDate(new Date());
			newMessage.setUserId(userId);
			newMessage.setMessage(message);
			JPAUtil.mergeObj(newMessage);
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Long getLastMessageId(){
		Connection activeConnection = getActiveConnection();
		EntityManager entityManager = activeConnection.getEntityManager();
		Query query = entityManager.createQuery("SELECT max(m.messageId) from Message m");
		Object maxIdObj = query.getSingleResult();
		String maxIdObjStr = maxIdObj+"";
		Long maxId = null;
		try {
			maxId = Long.parseLong(maxIdObjStr);
		} catch(Exception e) {
			e.printStackTrace();
		}
		ConnectionManager.returnConnectionToThePool(activeConnection);
		return maxId;
	}
	
	private Connection getActiveConnection(){
		return ConnectionManager.getActiveConnection();
	}
}
