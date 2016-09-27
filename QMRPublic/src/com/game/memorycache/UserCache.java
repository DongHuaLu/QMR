package com.game.memorycache;

import java.util.concurrent.ConcurrentHashMap;

import com.game.memorycache.structs.UserInfo;

//ID缓存数据
public class UserCache {

	//key为平台名字+"_"+ID
	private ConcurrentHashMap<String, UserInfo> users = new ConcurrentHashMap<String, UserInfo>();
	
	public void putUser(UserInfo user){
		synchronized (users) {
			this.users.put(user.getWeb() + "_" + user.getUserId(), user);
		}
	}
	
	public UserInfo getUser(String web, String userId){
		synchronized (users) {
			return this.users.get(web + "_" + userId);
		}
	}

	public ConcurrentHashMap<String, UserInfo> getUsers() {
		return users;
	}
	
}
