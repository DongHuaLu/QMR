package com.game.player.timer;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.game.dblog.LogService;
import com.game.json.JSONserializable;
import com.game.player.log.OnlineCountLog;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.timer.TimerEvent;
import com.game.utils.TimeUtil;

public class OnlineCountTimmer extends TimerEvent {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(OnlineCountTimmer.class);

	public OnlineCountTimmer() {
		super(-1,2*60*1000);
	}

	@Override
	public void action() {
		try{
			Set<Long> players = PlayerManager.getPlayers().keySet();
			int allsize = players.size();
			int teamsize=0;//组队玩家
			int male=0;//男玩家
			int female=0;//女玩家
			String countrycount="";
			HashMap<String, Integer> guojia=new HashMap<String, Integer>();
			
			for (Long key : players) {
				Player player = PlayerManager.getPlayers().get(key);
				if(player==null){
					continue;
				}
				byte sex = player.getSex();
				if(sex==1){
					male++;
				}
				if(sex==2){
					female++;
				}
				long teamid = player.getTeamid();
				if(teamid!=0){
					teamsize++;
				}
				int country = player.getCountry();
				Integer gjcount= guojia.get(String.valueOf(country));
				gjcount=gjcount==null?0:gjcount;
				gjcount++;
				guojia.put(String.valueOf(country),gjcount);
			}
			countrycount+=JSONserializable.toString(guojia);
			OnlineCountLog log=new OnlineCountLog();
			log.setCountrycount(countrycount);
			log.setFemale(female);
			log.setMale(male);
			log.setOnlinecount(allsize);
			log.setTeamcount(teamsize);
			log.setDatetime(TimeUtil.getNowStringDate());
			LogService.getInstance().execute(log);	
		}catch(Exception e){
			logger.error(e,e);
		}
		
	}
	
	public static void main(String args[]){
//		HashMap<String,Integer> aaa=new HashMap<String, Integer>();
//		aaa.put("1", 5);
//		aaa.put("2", 5);
//		aaa.put("3", 5);
//		System.out.println(JSONserializable.toString(aaa));
	}
		

}

