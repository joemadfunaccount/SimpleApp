package com.joemad.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.joemad.model.entity.User;
import com.joemad.util.JsonUtil;

public class CurrentPeople {
	private static Set<User> currentUsers= new HashSet<User>();
	
	public static String getCurrentPeople() {
		List<String> currentPeopleStr = new ArrayList<String>();
		if(!currentUsers.isEmpty()) {
			for(User user:currentUsers){
				currentPeopleStr.add(user.getName());
			}
		}
		return JsonUtil.getGson().toJson(currentPeopleStr);
	}
	
	public static boolean addUser(User user){
		currentUsers.add(user);
		return true;
	}
	
	public static boolean removeUser(User user) {
		currentUsers.remove(user);
		return true;
	}
}
