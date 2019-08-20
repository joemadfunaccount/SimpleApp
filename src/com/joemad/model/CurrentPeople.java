package com.joemad.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.joemad.model.entity.User;
import com.joemad.util.JsonUtil;

public class CurrentPeople {
	private static Map<User,Date> currentUsers= new HashMap<User,Date>();
	
	public static synchronized String getCurrentPeople() {
		List<String> currentPeopleStr = new ArrayList<String>();
		Date now = new Date();
		if(!currentUsers.isEmpty()) {
				for(User user:currentUsers.keySet()) {
					Date userActiveDate = currentUsers.get(user);
					if((now.getTime() - userActiveDate.getTime()) < 3000){
						currentPeopleStr.add(user.getName());
					} else {
						removeUser(user);
					}
				}
		}
		return JsonUtil.getGson().toJson(currentPeopleStr);
	}
	
	public static boolean addUser(User user){
		currentUsers.put(user, new Date());
		return true;
	}
	
	public static boolean removeUser(User user) {
		currentUsers.remove(user);
		return true;
	}
}
