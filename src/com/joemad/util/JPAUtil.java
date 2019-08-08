package com.joemad.util;

import javax.persistence.EntityManager;

public class JPAUtil {
	public static Object mergeObj(EntityManager entityManager, Object obj) {
		Object newMergedObj = null;
		try {
			entityManager.getTransaction().begin();
			newMergedObj = entityManager.merge(obj);
			entityManager.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return newMergedObj;
	}

}
