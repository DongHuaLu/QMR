package scripts.monster;

import org.apache.log4j.Logger;

import com.game.monster.script.IMonsterCanSeeScript;
import com.game.monster.structs.Monster;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;

public class MonsterCanSeeScript implements IMonsterCanSeeScript {
	
	protected Logger log = Logger.getLogger(MonsterCanSeeScript.class);

	@Override
	public int getId() {
		return ScriptEnum.MONSTER_SEE;
	}

	@Override
	public boolean cansee(Player player, Monster monster){
		if(monster.getShowSet().contains(player.getId())){
//			if(player.getId()==369853903318560l && monster.getModelId()==150) log.error(monster.getModelId() + "(" + monster.getName() + ")对[" + player.getName() +"]:可见");
			return true;
		}else if(monster.getHideSet().contains(player.getId())){
//			if(player.getId()==369853903318560l && monster.getModelId()==150) log.error(monster.getModelId() + "(" + monster.getName() + ")对[" + player.getName() +"]:隐藏");
			return false;
		}
		if(player.getShowSet().contains(String.valueOf(monster.getModelId()))){
//			if(player.getId()==369853903318560l && monster.getModelId()==150) log.error(monster.getModelId() + "(" + monster.getName() + ")对[" + player.getName() +"]:玩家可见");
			return true;
		}else if(player.getHideSet().contains(String.valueOf(monster.getModelId()))){
//			if(player.getId()==369853903318560l && monster.getModelId()==150) log.error(monster.getModelId() + "(" + monster.getName() + ")对[" + player.getName() +"]:玩家隐藏");
			return false;
		}
//		if(player.getId()==369853903318560l && monster.getModelId()==150) log.error(monster.getModelId() + "(" + monster.getName() + ")对[" + player.getName() +"]:" + (monster.isShow()?"可见":"隐藏"));
		return monster.isShow();
	}

}
