package scripts.fight;

import com.game.fight.script.IAttackCheckScript;
import com.game.fight.structs.Fighter;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.monster.structs.Monster;
import com.game.pet.struts.Pet;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.utils.MessageUtil;

public class AttackCheckScript implements IAttackCheckScript {

	//阿斗
	private int ADOU = 13501;
		
	@Override
	public int getId() {
		return ScriptEnum.CHECKATTACK;
	}

	@Override
	public boolean check(Fighter fighter, Fighter defenser) {
		
		if (defenser instanceof Monster ) {
			Monster monster = (Monster)defenser;
			
			if(monster.getModelId()==ADOU){
				if(fighter instanceof Player || fighter instanceof Pet){
					return false;
				}else if(fighter instanceof Monster){
					return true;
				}
			}
			
			if(fighter instanceof Monster){
				return false;
			}
			
			
			//---------------------------帮会领地旗帜------和（MonsterCanBeAttackScript脚本一致）------------
			if (ManagerPool.guildFlagManager.getflagmonidlist().contains(monster.getModelId())) {//检测是否对应怪物
				Player player = null;
				if (fighter instanceof Player) {
					player = (Player)fighter;

				}else if(fighter instanceof Pet){
					Pet attackPet = (Pet) fighter;
					long attackplayerid = attackPet.getOwnerId();
					player = ManagerPool.playerManager.getOnLinePlayer(attackplayerid);
				}
				if (player != null) {
					if (player.getLevel() < 30) {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的等级低于30级，不能攻击帮旗。"));
						return false;
					}
					
					
					if (monster.getParameters().containsKey("guildid")) {
						long guildid = (Long)(monster.getParameters().get("guildid"));
						if (player.getGuildId() == guildid) {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("不能攻击本帮帮旗。"));
							return false;
						}
					}
					
					if(ManagerPool.guildFlagManager.getFlagwarstatus() != 1 ){
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("现在不是领地争夺战时间，不能攻击帮旗。"));
						return false;
					}
					
					if (player.getLine() != 1) {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("请到1线去攻击帮旗真身。"));
						return false;
					}
				}
			}
			
			if (fighter instanceof Player) {	//相同阵营不能攻击
				Player player = (Player)fighter;
				if (player.getGroupmark() > 0  && player.getGroupmark() == monster.getGroupmark() ) {
					return false;
				}
			}
		}else if ((fighter instanceof Player || fighter instanceof Pet) && (defenser instanceof Player || defenser instanceof Pet)) {
			//相同阵营不能攻击(条件是在特殊地图里才是不能攻击)
			Player attplayer = null;
			if (fighter instanceof Player) {
				attplayer = (Player)fighter;
			}else if (fighter instanceof Pet) {
				attplayer = ManagerPool.petInfoManager.getPetHost((Pet)fighter);
			}
			Player defplayer = null;
			if (defenser instanceof Player) {
				defplayer = (Player)defenser;
			}else if (defenser instanceof Pet) {
				defplayer = ManagerPool.petInfoManager.getPetHost((Pet)defenser);
			}

			if (attplayer!=null && attplayer.getMapModelId() == 42124) {//水淹大梁地图
				if (defplayer != null && attplayer.getGroupmark() > 0  && attplayer.getGroupmark() == defplayer.getGroupmark() ) {
					//MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("相同阵营不能攻击。"));
					return false;
				}
			}
		}
		
		

		

		return true;
	}

}
