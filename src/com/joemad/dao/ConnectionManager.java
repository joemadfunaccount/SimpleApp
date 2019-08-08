package com.joemad.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

public class ConnectionManager {
	private static final String PERSISTENCE_UNIT_NAME = "myPersistenceUnit";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	private static EntityManager entityManager;

	public static void init() {
		entityManager = factory.createEntityManager();
	}

	public static EntityManager getEntityManager() {
		if (entityManager == null) {
			factory.createEntityManager();
		}
		return entityManager;
	}
}
