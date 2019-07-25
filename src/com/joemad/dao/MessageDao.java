package com.joemad.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import com.joemad.model.entity.Message;
import com.joemad.model.entity.User;
import com.joemad.util.JPAUtil;

public class MessageDao implements Serializable {
	private static final long serialVersionUID = 1L;
	private EntityManager entityManager = ConnectionManager.getEntityManager();
	private static UserDao userDao = null;
	
	static {
		userDao = new UserDao();
	}
	
	public List<Message> getUnreadMessages(Long userId) {
		User user = userDao.getUser(userId);
		Long lastReadMessageId = user.getLastReadMessageId();
		TypedQuery<Message> query = entityManager
				.createQuery("SELECT m FROM Message m where m.messageId > :lastReadMessageId", Message.class);
		query.setParameter("lastReadMessageId", lastReadMessageId + "");
		List<Message> unreadMessages = null;
		try {
			unreadMessages = query.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return unreadMessages;
	}
	
	public boolean persistMessage(Long userId, String message){
		try {
			Message newMessage = new Message();
			newMessage.setCreationDate(new Date());
			newMessage.setUserId(userId);
			newMessage.setMessage(message);
			JPAUtil.mergeObj(entityManager,newMessage);
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Long getLastMessageId(){
		Query query = entityManager.createQuery("SELECT max(m.messageId) from Message m");
		Object maxIdObj = query.getSingleResult();
		String maxIdObjStr = maxIdObj+"";
		Long maxId = null;
		try {
			maxId = Long.parseLong(maxIdObjStr);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return maxId;
	}
}
