package com.joemad.util;

import javax.persistence.EntityManager;

public class JPAUtil {
	public static Object mergeObj(EntityManager entityManager, Object obj) {
		Object mergedObj = null;
		try {
			entityManager.getTransaction().begin();
			mergedObj = entityManager.merge(obj);
			entityManager.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mergedObj;
	}
}
