package scripts.player;

import org.apache.log4j.Logger;

import com.game.cooldown.structs.CooldownTypes;
import com.game.dblog.LogService;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.log.RolePrisonLog;
import com.game.npc.manager.NpcManager;
import com.game.player.log.RoleHeartCheatLog;
import com.game.player.script.IPlayerCheckScript;
import com.game.player.script.PlayerCheckType;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.structs.Grid;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;

public class PlayerCheckScript implements IPlayerCheckScript {

	private static Logger log = Logger.getLogger("PLAYERCHECK");
	
	private int prison_map = 42122;
	
	@Override
	public int getId() {
		return ScriptEnum.PLAYER_CHECK;
	}

	@Override
	public void onCheck(Player player, int type, Object... obj) {
		boolean check = false;
		if(type==PlayerCheckType.HEART){
//			int times = (Integer)obj[0];
			if(System.currentTimeMillis() - player.getCheckTime() > 30 * 1000){
				player.setCheckTimes(0);
			}
			long between = (Long)obj[1];
			int allow = 1000;
//			ConcurrentHashMap<String, PlayerSpeedReport> serverSpeedReportMap = ManagerPool.playerManager.getServerSpeedReportMap();
//			if(serverSpeedReportMap.contains(player.getId())){
//				//PlayerSpeedReport playerSpeedReport = serverSpeedReportMap.get(player.getId());
//				
//				allow =500;
//			}
			//放宽1秒
			int time = (int)((between - allow) / 100);
			if(time<=0){
				if(between > 0) log.error("玩家(" + player.getId() + ")作弊, SS允许" + allow + ", 参数:" + between);
				return;
			}
			player.setCheckTimes(player.getCheckTimes() + time);
			player.setCheckTime(System.currentTimeMillis());
			try{
				RoleHeartCheatLog rlog = new RoleHeartCheatLog(player);
				rlog.setChecktimes(player.getCheckTimes());
				rlog.setCheckparam(between);
				LogService.getInstance().execute(rlog);
			}catch (Exception e) {
				log.error(e, e);
			}
			if(player.getCheckTimes() >= 40){
				log.error("玩家(" + player.getId() + ")作弊, SS作弊" + player.getCheckTimes() + "次被关监狱, 参数:" + between);
				player.setCheckTimes(0);
				check = true;
			}else{
				log.error("玩家(" + player.getId() + ")作弊, SS作弊" + player.getCheckTimes() + "次, 参数:" + between);
			}
		}else if(type==PlayerCheckType.JUMP_COOLDOWN){
			long value = (Long)obj[0]; 
			log.error("玩家(" + player.getId() + ")作弊, 作弊类型:" + type + ", 作弊参数:" + value);
		}else if(type==PlayerCheckType.ATTACK_SPEED){
			long value = (Long)obj[0]; 
			log.error("玩家(" + player.getId() + ")作弊, 作弊类型:" + type + ", 作弊参数:" + value);
			if(value >= 500 && !ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.PLAYER_ATTACK, null)){
				ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.PLAYER_ATTACK, null, 1200);
			}
		}else{
			double value = (Double)obj[0]; 
			if(type!=0) log.error("玩家(" + player.getId() + ")作弊, 作弊类型:" + type + ", 作弊参数:" + value);
		}
		
		if(player.getHeartCheckTimes() > 100){
			check = true;
		}
		
//		log.error("玩家(" + player.getId() + ")作弊,心跳作弊次数:" + player.getHeartCheckTimes() + ",走路作弊次数:" + player.getCheckTimes());
		//作弊大于10次
		if(check && player.getMapModelId()!=prison_map){
			player.setHeartCheckTimes(0);
			player.setCheckTimes(0);
			int times = player.getPrisonTimes() + 1;
			int time = 0;
			if(times==1){
				time = 1 * 60 * 60;
			}else if(times==2){
				time = 2 * 60 * 60;
			}else if(times==3){
				time = 4 * 60 * 60;
			}else if(times==4){
				time = 8 * 60 * 60;
			}else if(times>=5){
				time = 24 * 60 * 60;
			}else{
				time = 24 * 60 * 60;
			}
			log.error("玩家(" + player.getId() + ")作弊被关监狱次数:" + times + ",被关时间:" + time + ",当前剩余时间:" + player.getPrisonRemainTime());
			
			Grid grid = MapUtils.getGrid(30, 20, ManagerPool.mapManager.getMapBlocks(prison_map));
			//关监狱
			player.setPrisonTimes(times);
			player.setPrisonEnterTime(System.currentTimeMillis());
			player.setPrisonRemainTime(player.getPrisonRemainTime()+time); //关监狱时间累加用于后台增加蹲监狱时间
			MessageUtil.notify_player(player, Notifys.CHAT_BULL, ResManager.getInstance().getString("因检测到连续使用外挂而被抓入监狱（当前第{1}次），（第1次关1小时，第2次关12小时，第3次将永久关监狱。）"), times+"");
			ManagerPool.mapManager.changeMap(player, prison_map, prison_map, 0, grid.getCenter(), this.getClass().getName() + ".onCheck");
			ManagerPool.playerManager.sendPlayerPrisonState(player); //发送玩家关监狱状态
			//进监狱日志
			try{
				RolePrisonLog plog = new RolePrisonLog();
				plog.setSid(player.getCreateServerId());
				plog.setUserid(player.getUserId());
				plog.setRoleid(String.valueOf(player.getId()));
				plog.setRolename(player.getName());
				plog.setType(0);
				plog.setPrisontime(player.getPrisonTimes());
				plog.setPrisonremaintime(player.getPrisonRemainTime());
				plog.setHandletype(1);
				LogService.getInstance().execute(plog);
			}catch (Exception e) {
				log.error(e, e);
			}
		}
	}

//	public void addCheck(){
//		long time = System.currentTimeMillis() - this.checkTime;
//		if(time > Global.CHECK_BETWEEN){
//			this.checkTimes = 0;
//		}
//		this.checkTimes++;
//		this.checkTime = System.currentTimeMillis();
//		
//		ManagerPool.playerManager.playercheck(this);
//	}
//	
//	public void addHeartCheck(int times){
//		this.heartCheckTimes = times;
//		
//		ManagerPool.playerManager.playercheck(this);
//	}
}
