package scripts.map;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.dblog.LogService;
import com.game.manager.ManagerPool;
import com.game.map.log.RolePrisonLog;
import com.game.map.script.IMapHeartScript;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.structs.Grid;
import com.game.utils.MapUtils;

public class MapHeartScript implements IMapHeartScript {

	protected Logger log = Logger.getLogger(MapHeartScript.class);
	
	protected Logger checklog = Logger.getLogger("PLAYERCHECK");
	
	private Calendar start = Calendar.getInstance();
	
	private Calendar end = Calendar.getInstance();
	
	public MapHeartScript(){
		start.set(2012, 11, 25, 0, 0, 0);
		end.set(2012, 11, 28, 23, 59, 59);
	}
	@Override
	public int getId() {
		return ScriptEnum.MAP_HEART;
	}

	@Override
	public void onHeart(Map map) {
		//监狱放人
		try{
			if(map.getMapModelid()==42122){
				List<Player> players = new ArrayList<Player>();
				Iterator<Player> iter = map.getPlayers().values().iterator();
				while (iter.hasNext()) {
					Player player = (Player) iter.next();
					if(player.getPrisonRemainTime() > 0){
						player.setPrisonRemainTime(player.getPrisonRemainTime() - 1);
					}
					if(player.getPrisonRemainTime()==0) players.add(player);
				}
				
				Grid grid = MapUtils.getGrid(142, 55, ManagerPool.mapManager.getMapBlocks(20002));

				for (Player player : players) {
					player.setCheckTimes(0);
					player.setHeartCheckTimes(0);
					checklog.error("玩家(" + player.getId() + ")被释放");
					ManagerPool.mapManager.changeMap(player, 20002, 20002, 0, grid.getCenter(), this.getClass().getName() + ".onHeart");
					//出监狱日志
					try{
						RolePrisonLog plog = new RolePrisonLog();
						plog.setSid(player.getCreateServerId());
						plog.setUserid(player.getUserId());
						plog.setRoleid(String.valueOf(player.getId()));
						plog.setRolename(player.getName());
						plog.setType(1);
						plog.setPrisontime(player.getPrisonTimes());
						plog.setPrisonremaintime(0);
						plog.setHandletype(1);
						LogService.getInstance().execute(plog);
					}catch (Exception e) {
						log.error(e, e);
					}
				}
			}
		}catch (Exception e) {
			log.error(e, e);
		}
		//咸阳王城刷新圣诞老人
//		try{
//			if(map.getMapModelid()==20002 && map.getLineId()==1){
//				Calendar now = Calendar.getInstance();
//				int hour = now.get(Calendar.HOUR_OF_DAY);
//				//是否在活动期间
//				if(now.after(start) && now.before(end) && (hour==12 || hour==19)){
//					String key = "Show_Santa_Claus";
//					//刷新间隔
//					long between = 5 * 60 * 1000;
//					//上次刷新时间
//					long refreshTime = 0;
//					//圣诞老人
//					int modelId = 50000;
////					//消失时间
////					int delay = 3 * 60 * 1000;
//					if(map.getParameters().containsKey(key)){
//						refreshTime = (Long)map.getParameters().get(key);
//					}
//					long nowTime = System.currentTimeMillis();
//					if(nowTime - refreshTime >= between){
//						List<Grid> grids = MapUtils.getAllNoBlockAndSwimGrid(map.getMapModelid());
//						for (int i = 0; i < 10; i++) {
//							//刷新圣诞老人
//							NPC npc = ManagerPool.npcManager.createNpc(modelId, map, true);
//							Grid grid = grids.remove(RandomUtils.random(grids.size()));
//							npc.setPosition(grid.getCenter());
//							
//							ManagerPool.mapManager.enterMap(npc);
//							
//							log.error("刷新圣诞老人在[" + grid.getX() + "," + grid.getY() + "]");
//						}
//						
//						
//						map.getParameters().put(key, nowTime);
//						
//						
//						
////						//30秒后消失
////						ScriptsUtils.delayCall(this.getId(), "disapper", delay, npc);
//					}
//				}
//			}
//		}catch (Exception e) {
//			log.error(e, e);
//		}
	}
	
//	/**
//	 * npc消失
//	 * @param objs
//	 */
//	public void disapper(List<Object> objs){
//		NPC npc = (NPC)objs.get(0);
//		ManagerPool.mapManager.quitMap(npc);
//	}
}
