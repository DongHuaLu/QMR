package com.game.server.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.game.player.structs.Player;

public class GameConfig {
	private Logger log = Logger.getLogger(GameConfig.class);
	//国家索引 key为国家id（0-为公共区） value为服务器Id
	private HashMap<Integer, Integer> countrys = new HashMap<Integer, Integer>();
	//服务器索引 key为服务器Id value为国家id（0-为公共区）
	private HashMap<Integer, Integer> servers = new HashMap<Integer, Integer>();
	//服务器索引 key为服务器Id value为服务器开服时间
	private HashMap<Integer, String> serverTimes = new HashMap<Integer, String>();
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
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

	public HashMap<Integer, String> getServerTimes() {
		return serverTimes;
	}

	public void setServerTimes(HashMap<Integer, String> serverTimes) {
		this.serverTimes = serverTimes;
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
	
	public Date getServerTimeByServer(int server){
		if(serverTimes.containsKey(server)){
			String str = serverTimes.get(server);
			if(str!=null && !("").equals(str)){
				try{
					return format.parse(str);
				}catch (Exception e) {
					log.error(e, e);
				}
			}
		}
		return null;
	}
	
	/**
	 * 根据ServerId取得国家名称
	 * @param server
	 * @return
	 */
	public String getCountryNameByServer(int server){
		return getCountryNameByConutry(getCountryByServer(server));
	}
	/**
	 * 根据国家ID取得国家名称
	 * @param server
	 * @return
	 */
	public String getCountryNameByConutry(int server){
		return ServerName.getName(server);
	}
	
	
	/**
	 *根据玩家得到开区时间 
	 */
	public Date getServerTimeByPlayer(Player player){
		int country = getServerByCountry(player.getCountry());
		return getServerTimeByServer(country);
	}
	
}
