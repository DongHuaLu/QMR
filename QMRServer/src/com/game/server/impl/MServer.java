package com.game.server.impl;

import java.util.List;

import com.game.backpack.timer.BackPackTimer;
import com.game.biwudao.timer.BiWuDaoTimer;
import com.game.buff.timer.MonsterBuffTimer;
import com.game.buff.timer.PetBuffTimer;
import com.game.buff.timer.PlayerBuffTimer;
import com.game.country.timer.CountryTimer;
import com.game.dazuo.timer.DaZuoApplyClearTimer;
import com.game.drop.timer.DropClearTimer;
import com.game.epalace.timer.EpalaceTimer;
import com.game.equip.timmer.EquipLostTimeCheck;
import com.game.equipstreng.timer.StrengTimer;
import com.game.guildflag.timer.GuildFlagTimer;
import com.game.manager.ManagerPool;
import com.game.map.timer.GroundMagicTimer;
import com.game.map.timer.MapCountTimer;
import com.game.map.timer.MapHeartTimer;
import com.game.map.timer.MonsterStepTimer;
import com.game.map.timer.PlayerBlockTimer;
import com.game.map.timer.PlayerRunTimer;
import com.game.map.timer.PlayerStepTimer;
import com.game.marriage.timer.MarriagePlayerTimer;
import com.game.marriage.timer.MarriageTimer;
import com.game.monster.timer.MonsterAiTimer;
import com.game.monster.timer.MonsterAttackTimer;
import com.game.monster.timer.MonsterHeartTimer;
import com.game.monster.timer.MonsterReviveTimer;
import com.game.pet.timer.PetAiTimer;
import com.game.pet.timer.PetAttackTimer;
import com.game.pet.timer.PetHeartTimer;
import com.game.pet.timer.PetStepTimer;
import com.game.player.timer.PlayerHeartTimer;
import com.game.player.timer.PlayerPositionTimer;
import com.game.sceneobj.timer.SceneobjTimer;
import com.game.server.MapServer;
import com.game.server.Server;
import com.game.server.config.MapConfig;
import com.game.server.filter.HandlerFilter;
import com.game.server.thread.ServerThread;
import com.game.skill.timer.SkillTimer;
import com.game.systemgrant.timer.SystemGrantTimer;
import com.game.task.timer.SupperFinshTaskTimer;
import com.game.zones.timer.ZoneDeleteTimer;
import com.game.zones.timer.ZonePlayerTimer;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-10-6
 * 
 * 线服务线程
 */
public class MServer extends MapServer {
	
	private long createTime;
	
	private boolean delete = false;
	
	
	public MServer(String name, long zoneId, int zoneModelId, List<MapConfig> mapConfigs) {
		super(name, zoneId, zoneModelId, mapConfigs);
		this.createTime = System.currentTimeMillis();
	}

	@Override
	protected void init() {
		for (int i = 0; i < this.getMapConfigs().size(); i++) {
			MapConfig config = this.getMapConfigs().get(i);
			//初始化地图
			config.setMapId(ManagerPool.mapManager.initMaps(config.getServerId(), config.getLineId(), config.getMapModelId(), this.getZoneId(), this.getZoneModelId()));
			//初始化怪物
			ManagerPool.monsterManager.initMonster(config.getServerId(), config.getLineId(), config.getMapId(), config.getMapModelId());
			//初始化NPC
			ManagerPool.npcManager.initSceneNpc(config.getServerId(), config.getLineId(), config.getMapId(), config.getMapModelId());
		}
	}
	
	@Override
	public void run() {
		super.run();
		//添加服务器消息执行检查
		((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addCommandFitler(new HandlerFilter(this.getMapConfigs()));
		for (int i = 0; i < this.getMapConfigs().size(); i++) {
			MapConfig config = this.getMapConfigs().get(i);
//			//同步怪物信息
			ManagerPool.monsterManager.syncMonster(config.getServerId(), config.getLineId(), config.getMapId());
			//初始化人物移动定时
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new PlayerStepTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//初始化人物走路
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new PlayerRunTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//初始化怪物移动定时
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new MonsterStepTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//初始化怪物AI定时
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new MonsterAiTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//初始化怪物攻击定时
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new MonsterAttackTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//初始化怪物复活定时
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new MonsterReviveTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//包裹 仓库自动开格定时
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new BackPackTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//定时清除地面物品
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new DropClearTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//技能升级倒计时
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new SkillTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//定时计算人物Buff
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new PlayerBuffTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//定时计算怪物Buff
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new MonsterBuffTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//定时计算宠物Buff
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new PetBuffTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//定时格挡
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new PlayerBlockTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//玩家心跳
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new PlayerHeartTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//怪物心跳
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new MonsterHeartTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//地图中元素的统计
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new MapCountTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//测试坐标同步
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new PlayerPositionTimer(config.getServerId(), config.getLineId(), config.getMapId()));
//	//		//测试坐标同步
//	//		((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new MonsterPositionTimer(this.getServerId(), this.getLineId(), this.getMapId()));
//			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new PetPositionTimer(config.getServerId(), config.getLineId(), config.getMapId()));
//			//定时公告
//			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new BulletinTimer(config.getServerId(), config.getLineId(), config.getMapId()));
//			//宠物跟随AI
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new PetHeartTimer(config.getServerId(), config.getLineId(), config.getMapId()));
//			
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new PetAttackTimer(config.getServerId(), config.getLineId(), config.getMapId()));
//			//宠物移动定时
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new PetStepTimer(config.getServerId(), config.getLineId(), config.getMapId()));
//			
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new PetAiTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//装备强化完成倒计时
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new StrengTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new EquipLostTimeCheck(config.getServerId(), config.getLineId(), config.getMapId()));
			
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new DaZuoApplyClearTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new SupperFinshTaskTimer(config.getServerId(), config.getLineId(), config.getMapId()));

			//自动扫荡倒计时
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new ZonePlayerTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//地宫探险 行走
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new EpalaceTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//攻城战
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new CountryTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//定时刷场景NPC和怪物
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new SceneobjTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			
			//地图刷新帮旗
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new GuildFlagTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//比武岛
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new BiWuDaoTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//比武岛
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new MapHeartTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//全服邮件
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new SystemGrantTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//结婚系统
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new MarriageTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new MarriagePlayerTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			//地面魔法
			((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new GroundMagicTimer(config.getServerId(), config.getLineId(), config.getMapId()));
			
		}
		//副本
		((ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD)).addTimerEvent(new ZoneDeleteTimer(this.getZoneId(), this.getZoneModelId()));
	}
	
	public void stop(boolean flag){
		super.stop(flag);
		if(flag){
			for (int i = 0; i < this.getMapConfigs().size(); i++) {
				MapConfig config = this.getMapConfigs().get(i);
				//移除怪物
	//			ManagerPool.monsterManager.removeMonster(config.getServerId(), config.getLineId(), config.getMapId());
				//移除地图		
				ManagerPool.mapManager.removeMap(config.getServerId(), config.getLineId(), config.getMapId());
			}
		}
	}
	
	public ServerThread getMainThread(){
		return (ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD);
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}


	
//	/**
//	 * 所有在地图中角色 执行任务
//	 * @param run
//	 */
//	public void allPlayerRun(final PlayerRunable run){
//		
//		ServerThread thread = (ServerThread) thread_pool.get(Server.DEFAULT_MAIN_THREAD);
//		thread.addCommand(new ICommand() {
//			
//			@Override
//			public void action() {
//				Map map = MapManager.getInstance().getMap(getServerId(), getLineId(), getMapId());
//				HashMap<Long,Player> players = map.getPlayers();
//				if(players!=null&&players.size()>0){
//					List<Long> keys=new ArrayList<Long>();
//					keys.addAll(players.keySet());
//					for (Long key : keys) {
//						Player player = players.get(key);
//						if(player!=null){
//							run.run(player);
//						}
//					}
//				}
//			}
//
//			public Object clone() throws CloneNotSupportedException {
//				return super.clone();
//			}
//		});
//	}
}
