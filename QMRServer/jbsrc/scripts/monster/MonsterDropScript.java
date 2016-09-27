package scripts.monster;

import java.util.List;

import org.apache.log4j.Logger;

import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_monsterBean;
import com.game.fight.structs.Fighter;
import com.game.horse.manager.HorseManager;
import com.game.horse.struts.Horse;
import com.game.manager.ManagerPool;
import com.game.monster.script.IMonsterDieDropScript;
import com.game.monster.structs.Monster;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;
import com.game.structs.Reasons;
import com.game.utils.RandomUtils;
import com.game.utils.ScriptsUtils;
import com.game.utils.TimeUtil;

public class MonsterDropScript implements IMonsterDieDropScript {
	protected static Logger log = Logger.getLogger(MonsterDropScript.class);
	@Override
	public int getId() {
		return ScriptEnum.MONSTER_DROP;
	}
	
	
//	private String key1 = "drop_1026_use";
//	private String key2 = "drop_1027_use";
	
	@Override
	public void onMonsterDie(Monster monster, Fighter killer) {
		// 这里特殊处理下年兽，年兽掉的装备，人人都可以拾取
		if (monster.getModelId() == 2002) {
			monster.setKiller(null);
		}
		
		if (killer instanceof Player) {
			//活动掉落调用脚本
			ScriptsUtils.call(ScriptEnum.BASEACTIVITIES, "activityRandomDrop", monster, killer);
		}
		
		//boss累计死亡计数掉落
		IMonsterDieDropScript script = (IMonsterDieDropScript) ManagerPool.scriptManager.getScript(7002);
		if (script != null) {
			try {
				script.onMonsterDie(monster, killer);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
	}

	
	//----------------------------多玩1区特殊奖励----------------------------
	//在玩家击杀所有普通怪物时调用掉率脚本：
	//如果玩家坐骑为（熊、老虎、豹子），有1/5000 概率直接给玩家包裹中直接添加一个极品礼盒道具，当玩家通过该途径获得极品礼盒后，则不在对该玩家执行特殊掉落
	//如果玩家等级大于50级有1/10000几率直接给玩家包裹中放入 九星装备保护卡*1（绑、时效），当玩家通过该途径获得九星保护卡后，则不在对该玩家执行特殊掉落
	
	public void setduowanitem(Monster monster, Fighter killer){
		int sid = WServer.getInstance().getServerId();
		if (sid != 1) {
			return;
		}
		
		if (TimeUtil.GetSeriesDay() <= 20121026) {
			Player player = (Player)killer;
			if ( WServer.getInstance().getServerWeb().equals("duowan")) {	//检测多玩平台
				Q_monsterBean mondata = ManagerPool.dataManager.q_monsterContainer.getMap().get(monster.getModelId());
				if(mondata.getQ_monster_type() == 1){	//普通怪物
					String duowankey1="DUOWAN1_20121026";
					String duowankey2="DUOWAN2_20121026";
					long action = Config.getId();
					if (!player.getActivitiesReward().containsKey(duowankey1)) {
						Horse horse=HorseManager.getInstance().getHorse(player);
						if(horse.getLayer() >= 5 && horse.getLayer() <= 7){
							if(RandomUtils.random(1, 5000) == 1){
								if(ManagerPool.backpackManager.getEmptyGridNum(player) >= 1) {//包裹检测
									player.getActivitiesReward().put(duowankey1, "20121026");
									List<Item> createItems = Item.createItems(9101,1, false,0 , 0, null);
									ManagerPool.backpackManager.addItem(player, createItems.get(0), Reasons.ACTIVITY_RNDITEM,action);
								}
							}
						}
					}
					
					if (player.getLevel() >=50 ) {
						if (!player.getActivitiesReward().containsKey(duowankey2)) {
							if(RandomUtils.random(1, 10000) == 1){
								if(ManagerPool.backpackManager.getEmptyGridNum(player) >= 1) {//包裹检测
									player.getActivitiesReward().put(duowankey2, "20121026");
									List<Item> createItems = Item.createItems(1026,1, true,System.currentTimeMillis()+24*60*60*1000, 0, null);
									ManagerPool.backpackManager.addItem(player, createItems.get(0), Reasons.ACTIVITY_MONEY,action);
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	
	
	
	
	
	
}
