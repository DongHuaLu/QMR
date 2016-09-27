package com.game.player.scheduler;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.dblog.LogService;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.log.RoleLoginLog;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.server.config.MapConfig;
import com.game.server.impl.MServer;
import com.game.server.impl.WServer;
import com.game.server.script.IMidNightScript;
import com.game.task.manager.TaskManager;
import com.game.timer.SchedulerEvent;
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
	private static final Logger logger = Logger
			.getLogger(Midnight0ClockEvent.class);

	@Override
	public void action() {
		logger.error("开始执行零点任务");
		
		IMidNightScript script = (IMidNightScript) ManagerPool.scriptManager.getScript(ScriptEnum.MID_NIGHT);
		if (script != null) {
			try {
				script.onMidNight();
			} catch (Exception e) {
				logger.error(e, e);
			}
		} else {
			logger.error("0点事件脚本不存在！");
		}
		
		MServer[] servers = WServer.getInstance().getMServers();
		for (MServer server : servers) {
			try {
				for (int j = 0; j < server.getMapConfigs().size(); j++) {
					MapConfig config = server.getMapConfigs().get(j);
					final Map map = ManagerPool.mapManager.getMap(
							config.getServerId(), config.getLineId(),
							config.getMapId());
					server.addCommand(new Handler() {
						@Override
						public void action() {
							Iterator<Player> iter = map.getPlayers().values()
									.iterator();
							while (iter.hasNext()) {
								Player player = (Player) iter.next();
								checkZeroClock(player);
							}
							logger.error("零点任务信息地图" + map.getMapModelid()+" line "+map.getLineId()
									+ "玩家" + map.getPlayers().size() + "个");
						}
					});

				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		logger.error("开始执行零点任务执行结束");
	}
	
	public static void checkZeroClock(Player player){
		long clockTimer = player.getLastCheckZeroClockTimer();
		if(TimeUtil.isSameDay(clockTimer, System.currentTimeMillis())){
			return;
		}
//		if(System.currentTimeMillis()-clockTimer>=24*60*60*1000){
			player.setLastCheckZeroClockTimer(System.currentTimeMillis());
			// 清除日常任务
			try {
				TaskManager.getInstance().zeroClockAction(player);
			} catch (Exception e) {
				logger.error(e, e);
			}
			try{
				ManagerPool.zonesManager.stResZoneSurplusSum(player);
			}catch (Exception e) {
				logger.error(e,e);
			}
			try {
				ManagerPool.activitiesManager.nextLogin(player);
			} catch (Exception e) {
				logger.error(e, e);
			}
			try {
				ManagerPool.dianjiangchunManager.ClearDianjiangchunInfo(player);
			} catch (Exception e) {
				logger.error(e, e);
			}
			try{
				//零点未下线的记录登陆日志
				RoleLoginLog loginlog = new RoleLoginLog(player);
				LogService.getInstance().execute(loginlog);
			}catch(Exception e){
				logger.error(e, e);
			}
			
			try {//清理并发送境界祝福值
				ManagerPool.realmManager.sendRealmInfo(player);
			} catch (Exception e) {
				logger.error(e, e);
			}
		}
//	}
}