package com.game.player.scheduler;


import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerWorldInfo;
import com.game.registrar.manager.RegistrarManager;
import com.game.server.WorldServer;
import com.game.timer.SchedulerEvent;
import com.game.toplist.manager.TopListManager;
import com.game.utils.TimeUtil;

/**
 * 零点需要执行的任务
 *
 * @author 赵聪慧
 *
 */
public class Midnight0ClockEvent extends SchedulerEvent {

	/**
	 * Logger for this class
	 */
	
	private static String TOPLISTLEVEL = "TOPLISTLEVEL";
	private static String TOPLISTHORSE = "TOPLISTHORSE";
	private static String TOPLISTSKILL = "TOPLISTSKILL";
	
	private static final Logger logger = Logger.getLogger(Midnight0ClockEvent.class);
	private static boolean betatoplistactivity = true;
	@Override
	public void action() {
		logger.error("开始执行零点任务");
		try {
			WorldServer.getInstance().getServerThread().addCommand(new Handler() {

				@Override
				public void action() {
					if(betatoplistactivity){
						long now = System.currentTimeMillis();
						Date opendate = WorldServer.getGameConfig().getServerTimeByServer(1);
						long opentime = opendate.getTime(); //开服时间
						Calendar c = Calendar.getInstance();
						opentime = opentime+3*24*3600*1000; //开服三天后
						c.setTimeInMillis(opentime);
						c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0); //开服三天后的凌晨
						long limit = c.getTimeInMillis(); //开服三天后的凌晨
						if(now>=limit-3600000 && now<=limit+3600000){
							betatoplistactivity = false;
							Object[] levelarray = TopListManager.getInstance().getLevelTopMap().values().toArray();
							for(int index=0; index<3 && index<levelarray.length; index++){
								long playerid = (Long)levelarray[index];
								PlayerWorldInfo playerinfo = PlayerManager.getInstance().getPlayerWorldInfo(playerid);
								long userid = Long.valueOf(playerinfo.getAccount());
								Map<String, String> playerParams = RegistrarManager.getInstance().getPlayerRegistrarParams(userid);
								String key = TOPLISTLEVEL+playerid;
								playerParams.put(key, ""+(index+4));
								RegistrarManager.getInstance().savePlayerParams(userid, playerParams); 
							}
							Object[] horsearray = TopListManager.getInstance().getHorseTopMap().values().toArray();
							for(int index=0; index<3 && index<horsearray.length; index++){
								long playerid = (Long)horsearray[index];
								PlayerWorldInfo playerinfo = PlayerManager.getInstance().getPlayerWorldInfo(playerid);
								long userid = Long.valueOf(playerinfo.getAccount());
								Map<String, String> playerParams = RegistrarManager.getInstance().getPlayerRegistrarParams(userid);
								String key = TOPLISTHORSE+playerid;
								playerParams.put(key, ""+(index+4));
								RegistrarManager.getInstance().savePlayerParams(userid, playerParams);
							}
							Object[] skillarray = TopListManager.getInstance().getGestTopMap().values().toArray();
							for(int index=0; index<3 && index<skillarray.length; index++){
								long playerid = (Long)skillarray[index];
								PlayerWorldInfo playerinfo = PlayerManager.getInstance().getPlayerWorldInfo(playerid);
								long userid = Long.valueOf(playerinfo.getAccount());
								Map<String, String> playerParams = RegistrarManager.getInstance().getPlayerRegistrarParams(userid);
								String key = TOPLISTSKILL+playerid;
								playerParams.put(key, ""+(index+4));
								RegistrarManager.getInstance().savePlayerParams(userid, playerParams);
							}							
						}
					}
					
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.error("零点任务执行结束");
	}
	
	public static void main(String[] args){
		System.out.println(new Date(1348718400000L));
	}
	
}