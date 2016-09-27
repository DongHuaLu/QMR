package com.game.server.config;

import java.util.HashMap;

public class GameConfig {
	//国家索引 key为国家id（0-为公共区） value为服务器Id
	private HashMap<Integer, Integer> countrys = new HashMap<Integer, Integer>();
	//服务器索引 key为服务器Id value为国家id（0-为公共区）
	private HashMap<Integer, Integer> servers = new HashMap<Integer, Integer>();
	
	public HashMap<Integer, Integer> getCountrys() {
		return countrys;
	}
	
	public void setCountrys(HashMap<Integer, Integer> countrys) {
		this.countrys = countrys;
	}
	
	public HashMap<Integer, Integer> getServers() {
		return servers;
	}
	
	public void setServers(HashMap<Integer, Integer> servers) {
		this.servers = servers;
	}

	public int getServerByCountry(int country){
		if(countrys.containsKey(country)){
			return countrys.get(country);
		}
		
		return -1;
	}
	
	public int getCountryByServer(int server){
		if(servers.containsKey(server)){
			return servers.get(server);
		}
		
		return -1;
	}
}
