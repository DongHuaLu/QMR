package com.game.server.config;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.server.impl.WServer;
import com.game.utils.HttpUtil;
import com.game.utils.MessageUtil;

public class GameConfig {
	private Logger log = Logger.getLogger(GameConfig.class);
	//国家索引 key为国家id（0-为公共区） value为服务器Id
	private ConcurrentHashMap<Integer, Integer> countrys = new ConcurrentHashMap<Integer, Integer>();
	//服务器索引 key为服务器Id value为国家id（0-为公共区）
	private ConcurrentHashMap<Integer, Integer> servers = new ConcurrentHashMap<Integer, Integer>();
	//服务器索引 key为服务器Id value为服务器开服时间
	private ConcurrentHashMap<Integer, Date> serverTimes = new ConcurrentHashMap<Integer, Date>();
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public ConcurrentHashMap<Integer, Integer> getCountrys() {
		return countrys;
	}
	
	public void setCountrys(ConcurrentHashMap<Integer, Integer> countrys) {
		this.countrys = countrys;
	}
	
	public ConcurrentHashMap<Integer, Integer> getServers() {
		return servers;
	}
	
	public void setServers(ConcurrentHashMap<Integer, Integer> servers) {
		this.servers = servers;
	}

	public ConcurrentHashMap<Integer, Date> getServerTimes() {
		return serverTimes;
	}

	public void setServerTimes(ConcurrentHashMap<Integer, Date> serverTimes) {
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
			Date date = serverTimes.get(server);
			if(date!=null){
				return date;
			}else {
				log.error("server " + server + " not exists!");
			}
		}else{
			log.error("server " + server + " not exists!");
		}
		return null;
	}
	
	
	public Date getConfigTimeToDate(String str){
		if(str!=null && !("").equals(str)){
			try{
				return format.parse(str);
			}catch (Exception e) {
				log.error("开区时间设置错误", e);
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
	
	
	
	/**改变当前服务器开服时间（GM指令）
	 * 
	 * @param player
	 */
	public void stGmServerTime(Player player,String str1,String str2){
		String timestr = str1+" "+str2;
		Pattern pattern = Pattern.compile("(\\d{1,4})\\-(\\d{1,2})\\-(\\d{1,2})\\s(\\d{1,2})\\:(\\d{1,2}):(\\d{1,2})");
		Matcher time = pattern.matcher(timestr);
		if (time.find()== false) {
			MessageUtil.notify_player(player, Notifys.ERROR, "格式错误，必须为yyyy-MM-dd HH:mm:ss");
			return;
		}
		Date date = getConfigTimeToDate(timestr);
		if (date != null) {
			serverTimes.put( WServer.getInstance().getServerId(), date);
			MessageUtil.notify_player(player, Notifys.SUCCESS, "{1}国开服时间已改为{2}",getCountryNameByConutry(player.getCountry()),timestr);
		}else {
			MessageUtil.notify_player(player, Notifys.ERROR, "时间解析出错");
		}
	}
	
	public void stGmServerTime(String serverid,String str1,String str2, String httpresult){
		try {
			String timestr = str1 + " " + str2;
			Pattern pattern = Pattern.compile("(\\d{1,4})\\-(\\d{1,2})\\-(\\d{1,2})\\s(\\d{1,2})\\:(\\d{1,2}):(\\d{1,2})");
			Matcher time = pattern.matcher(timestr);
			if (time.find() == false) {
				HttpUtil.wget(httpresult+"&result=0&tip="+URLEncoder.encode("格式错误，必须为yyyy-MM-dd HH:mm:ss", "UTF-8")+"&location=server");
				return;
			}
			Date date = getConfigTimeToDate(timestr);
			if (date != null) {
				serverTimes.put(Integer.parseInt(serverid), date);
				HttpUtil.wget(httpresult+"&result=1"+"&tip="+URLEncoder.encode(getCountryNameByConutry(Integer.parseInt(serverid))+"国开服时间已改为"+timestr,"UTF-8"));
			} else {
				HttpUtil.wget(httpresult+"&result=0"+"&tip="+URLEncoder.encode("时间解析出错","UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
