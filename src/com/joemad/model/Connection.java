package com.joemad.model;

import javax.persistence.EntityManager;

public class Connection {
	private EntityManager entityManager;
	private boolean isBusy = false;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public boolean isBusy() {
		return isBusy;
	}

	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

}
