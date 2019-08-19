package com.joemad.util;

import javax.persistence.EntityManager;
import com.joemad.model.Connection;

public class JPAUtil {
	public static Object mergeObj(Object obj) {
		Object newMergedObj = null;
		try {
			Connection activeConnection = ConnectionManager.getActiveConnection();
			EntityManager entityManager = activeConnection.getEntityManager();
			entityManager.getTransaction().begin();
			newMergedObj = entityManager.merge(obj);
			entityManager.getTransaction().commit();
			ConnectionManager.returnConnectionToThePool(activeConnection);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return newMergedObj;
	}
}
