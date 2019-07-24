package com.joemad.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.joemad.dao.ConnectionManager;
import com.joemad.util.EmailUtil;

public class AppStartupListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("Connecting to the Database and shit");
		ConnectionManager.init();
		System.out.println("Initializing Email Settings and shit");
		EmailUtil.init();
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		System.out.println("Shutting down!");
	}

}
