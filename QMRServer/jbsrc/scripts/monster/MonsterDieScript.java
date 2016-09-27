package scripts.monster;

import org.apache.log4j.Logger;

import com.game.fight.structs.Fighter;
import com.game.manager.ManagerPool;
import com.game.monster.script.IMonsterDieScript;
import com.game.monster.structs.Monster;
import com.game.pet.struts.Pet;
import com.game.player.structs.Player;
import com.game.script.manager.ScriptManager;
import com.game.script.structs.ScriptEnum;
import com.game.utils.ScriptsUtils;

public class MonsterDieScript implements IMonsterDieScript {

	protected static Logger log = Logger.getLogger(MonsterDieScript.class);
	public static int simen_scriptId = 4010;				//四门阵
	public static int SheZhanQunRuZoneId = 3001;		//SheZhanQunRuzoneid
	public static int SheZhanQunRusSriptId = 4009;		//舌战群儒副本scriptid
	public static int MeiHuaXuanWuSriptId = 4011;		//梅花玄武阵副本scriptid
	public static int[] bow_scriptId = new int[]{4101, 4102, 4103, 4104, 4105, 4106, 4107};				//弓箭boss副本
	private static int shuiyandaliang  = 4012;
	@Override
	public int getId() {
		return ScriptEnum.MONSTER_DIE;
	}

	@Override
	public void onMonsterDie(Monster monster, Fighter killer) {
		
		try {
		//扫荡-怪物死亡
			ManagerPool.zonesManager.zonesMonsterDie(monster);
		} catch (Exception e) {
			log.error(e, e);
		}
		
		//八卦阵木桶怪物掉落
		if (monster.getParameters().containsKey("bgzmonsterdrop")) {
			try {
				if (killer instanceof Pet) {
					Player player = ManagerPool.petInfoManager.getPetHost((Pet) killer);
					ScriptsUtils.call(4008, "bgzmonsterdrop", player, monster.getModelId());
				} else {
					ScriptsUtils.call(4008, "bgzmonsterdrop", (Player) killer, monster.getModelId());
				}
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		//八卦阵BOSS死亡
		if (monster.getParameters().containsKey("bgzbossdie")) {
			try {
				if (killer != null) {
					log.error("onMonsterDie=八卦阵BOSS死亡,killer="+killer.getName());
				}else {
					log.error("onMonsterDie=八卦阵BOSS死亡,killer=null");
				}
				
				if (killer instanceof Pet) {
					Player player = ManagerPool.petInfoManager.getPetHost((Pet) killer);
					ScriptsUtils.call(4008, "bgzbossdie", player, monster);
				} else {
					ScriptsUtils.call(4008, "bgzbossdie", (Player) killer, monster);
				}
			} catch (Exception e) {
				log.error(e, e);
			}
		}


		IMonsterDieScript script = (IMonsterDieScript) ManagerPool.scriptManager.getScript(simen_scriptId);
		if (script != null) {
			try {
				script.onMonsterDie(monster, killer);
			} catch (Exception e) {
				log.error(e, e);
			}
		}

		for (int i = 0; i < bow_scriptId.length; i++) {
			script = (IMonsterDieScript) ManagerPool.scriptManager.getScript(bow_scriptId[i]);
			if (script != null) {
				try {
					script.onMonsterDie(monster, killer);
				} catch (Exception e) {
					log.error(e, e);
				}
			}
		}

		script = (IMonsterDieScript) ScriptManager.getInstance().getScript(SheZhanQunRusSriptId);
		if (script != null) {
			try {
				script.onMonsterDie(monster, killer);
			} catch (Exception e) {
				log.error(e, e);
			}
			
		}
		
		script = (IMonsterDieScript) ScriptManager.getInstance().getScript(MeiHuaXuanWuSriptId);
		if (script != null) {
			try {
				script.onMonsterDie(monster, killer);
			} catch (Exception e) {
				log.error(e, e);
			}
			
		}

		//帮旗战，帮旗死亡
		script = (IMonsterDieScript) ScriptManager.getInstance().getScript(ScriptEnum.GUILDFLAG);
		if (script != null) {
			try {
				script.onMonsterDie(monster, killer);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
		//水淹大梁
		script = (IMonsterDieScript) ScriptManager.getInstance().getScript(shuiyandaliang);
		if (script != null) {
			try {
				script.onMonsterDie(monster, killer);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
	}
}
