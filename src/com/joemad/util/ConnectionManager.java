package com.joemad.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import com.joemad.model.Connection;

public class ConnectionManager {
	private static final String PERSISTENCE_UNIT_NAME = "myPersistenceUnit";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	private static List<Connection> activeConnections = Collections.synchronizedList(new ArrayList<Connection>());

	public static void init() {
		generateMoreConnections(70);
	}

	public static synchronized Connection getActiveConnection() {
		if(activeConnections.isEmpty()) {
			generateMoreConnections(3);
		}
		Connection activeConnection = activeConnections.get(0);
		activeConnections.remove(0);
		return activeConnection;
	}
	
	public static synchronized Connection returnConnectionToThePool(Connection busyConnection) {
		busyConnection.setBusy(false);
		activeConnections.add(busyConnection);
		return busyConnection;
	}
	
	private static void generateMoreConnections(int numberOfConnections) {
		for(int counter=0; counter<numberOfConnections;counter++) {
			Connection connection = new Connection();
			connection.setEntityManager(factory.createEntityManager());
			activeConnections.add(connection);
		}
	}
}
