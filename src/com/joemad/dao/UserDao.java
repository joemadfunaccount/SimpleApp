package com.joemad.dao;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.joemad.model.Constants;
import com.joemad.model.entity.User;
import com.joemad.util.CodeGeneratorUtil;

public class UserDao implements Serializable {
	private static final long serialVersionUID = 1L;
	private EntityManager entityManager = ConnectionManager.getEntityManager();
	private static MessageDao messageDao;
	
	static {
		messageDao = new MessageDao();
	}
	
	public User getUserByUsername(String username) {
		if (username == null || "".equals(username.trim())) {
			return null;
		}
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u where u.username = :username",
				User.class);
		query.setParameter("username", username.trim());
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public User getUser(Long userId) {
		if (userId == null) {
			return null;
		}
		User user = null;
		try {
			user = entityManager.find(User.class, userId);
		} catch (Exception e) {
			e.printStackTrace();
			;
		}
		return user;
	}

	public boolean updateLastReadMessage(Long userId, Long lastReadMessageId) {
		try {
			User user = entityManager.find(User.class, userId);
			user.setLastReadMessageId(lastReadMessageId);
			entityManager.merge(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public User persistUser(String name, String username, String password, String email, String remoteAddress) {
		User user = new User();
		try {
			user.setName(name);
			user.setUsername(username);
			user.setPassword(Base64.getEncoder().encodeToString(password.getBytes()));
			user.setEmail(email);
			user.setCreationDate(new Date());
			user.setFailedLoginAttempts(0);
			user.setIpAddress(remoteAddress);
			user.setLastReadMessageId(messageDao.getLastMessageId());
			user.setStatus(Constants.Status.Active.ordinal());
			user.setVerified(0);
			user.setLocked(0);
			user.setGeneratedCode(CodeGeneratorUtil.generateRandomString());
			user = entityManager.merge(user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean incrementFailedLoginAttempts(Long userId) {
		try {
			User user = entityManager.find(User.class, userId);
			user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
			entityManager.merge(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean resetFailedLoginAttempts(Long userId) {
		try {
			User user = entityManager.find(User.class, userId);
			user.setFailedLoginAttempts(0);
			entityManager.merge(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean lockUser(Long userId) {
		try {
			User user = entityManager.find(User.class, userId);
			user.setLocked(1);
			entityManager.merge(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateUserAddress(Long userId, String remoteAdress) {
		try {
			User user = entityManager.find(User.class, userId);
			user.setIpAddress(remoteAdress);
			entityManager.merge(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean verifiedUser(String username, String requestCode) {
		User user = getUserByUsername(username);
		try {
			if(user!=null && user.getGeneratedCode()!=null){
				if(user.getGeneratedCode().equals(requestCode)){
					user.setVerified(1);
					entityManager.merge(user);
					return true;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();	
		}
		return false;
	}

}
