package scripts.monster;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_monsterBean;
import com.game.manager.ManagerPool;
import com.game.monster.script.IMonsterStopScript;
import com.game.monster.structs.Monster;
import com.game.script.IScript;
import com.game.script.structs.ScriptEnum;

public class MonsterStopScript implements IMonsterStopScript {

	private Logger log = Logger.getLogger(MonsterStopScript.class);
	
	public static int simen_scriptId = 4010;				//四门阵
	
	@Override
	public int getId() {
		return ScriptEnum.MONSTER_STOP;
	}

	@Override
	public void stop(Monster monster) {
		//查找怪物模板
		Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(monster.getModelId());
		if(model==null){
			return;
		}
		
		if(model.getQ_script_id() > 0){
			IScript script = ManagerPool.scriptManager.getScript(model.getQ_script_id());
			if(script!=null && script instanceof IMonsterStopScript){
				try{
					((IMonsterStopScript)script).stop(monster);
				}catch (Exception e) {
					log.error(e, e);
				}
			}
		}
	
	}

}
