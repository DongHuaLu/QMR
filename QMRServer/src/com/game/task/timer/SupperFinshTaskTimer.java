package com.game.task.timer;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import com.game.map.manager.MapManager;
import com.game.map.structs.Map;
import com.game.player.structs.LaterTask;
import com.game.player.structs.Player;
import com.game.timer.TimerEvent;

/**
 * 日常任务延时完成
 * @author 赵聪慧
 * @2012-8-24 下午12:40:43
 */
public class SupperFinshTaskTimer extends TimerEvent {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SupperFinshTaskTimer.class);

	private int serverid;
	private int lineid;
	private int mapid;
	
	public SupperFinshTaskTimer(int serverid,int lineid,int mapid) {
		super(-1,500);
		this.serverid=serverid;
		this.lineid=lineid;
		this.mapid=mapid;
	}

	@Override
	public void action() {
		Map map = MapManager.getInstance().getMap(serverid, lineid, mapid);
		Iterator<Player> iterator = map.getPlayers().values().iterator();
		while (iterator.hasNext()) {
			Player player = (Player) iterator.next();
			if(player.getLaterList()!=null&&player.getLaterList().size()>0){
				ArrayList<LaterTask> list=new ArrayList<LaterTask>(player.getLaterList());
				for (LaterTask laterTask : list) {
					if(laterTask.getNowcount()<laterTask.getCount()&&System.currentTimeMillis()>=laterTask.getStartTime()+(laterTask.getNowcount()+1)*laterTask.getInterval()){
						//到点了
						try{
							laterTask.getRun().run();	
						}catch (Exception e) {
							//防止 因逻辑异常倒到的内存泄露 
							logger.error(e,e);
						}
						laterTask.setNowcount(laterTask.getNowcount()+1);
					}
					if(laterTask.getNowcount()>=laterTask.getCount()){
						player.getLaterList().remove(laterTask);
					}
				}
			}
		}
	}
}
