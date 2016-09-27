package scripts.monster;

import java.util.List;

import com.game.buff.structs.Buff;
import com.game.buff.structs.BuffConst;
import com.game.data.bean.Q_mapBean;
import com.game.fight.structs.Fighter;
import com.game.guildflag.manager.GuildFlagManager;
import com.game.guildflag.structs.GuildTerritoryFlag;
import com.game.manager.ManagerPool;
import com.game.monster.script.IMonsterAiScript;
import com.game.monster.structs.Monster;
import com.game.pet.struts.Pet;
import com.game.player.structs.Player;
import com.game.skill.structs.Skill;
import com.game.utils.MessageUtil;

/**领地争夺战，旗帜被攻击触发,增加积分
 * 
 * @author zhangrong
 *
 */
public class GuildFlagLessblood  implements IMonsterAiScript{

	@Override
	public int getId() {
		return 2003000;
	}

	@Override
	public boolean wasHit(Monster monster, Fighter attacker) {
		if (ManagerPool.guildFlagManager.getFlagwarstatus() != 1) {
			return false;
		}
		
		if (ManagerPool.guildFlagManager.getflagmonidlist().contains(monster.getModelId())) {
			Player player = null ;
			if (attacker instanceof Player) {
				player = (Player)attacker;
			}else if (attacker instanceof Pet) {
				player = ManagerPool.petInfoManager.getPetHost((Pet)attacker);
			}
			if (player != null) {
				ManagerPool.guildFlagManager.addFlagWarJF(player,1);
				//给玩家加攻击者BUFF
				List<Buff> buff = ManagerPool.buffManager.getBuffByModelId( player, BuffConst.FLAG_BUFF);
				if (buff.size() == 0) {
					ManagerPool.buffManager.removeByBuffId(player, BuffConst.FLAG_DEF_BUFF);
					ManagerPool.buffManager.addBuff(player, player, BuffConst.FLAG_BUFF, 0, 0, 0);
				}
				double hpdou =  ((double)monster.getHp() / (double)monster.getMaxHp()) * 100;
				int hp = (int)hpdou;
				
				//由于系统得不到帮会名字，，只好在这里做个缓存，所有参与的帮会名字都会被记录下来
				if(player.getGuildId() > 0 && !GuildFlagManager.guildnamemap.containsKey(player.getGuildId())){
					GuildFlagManager.guildnamemap.put(player.getGuildId(), player.getGuildInfo().getGuildName());
				}
				if (hp%10 == 0) {
					Q_mapBean mapdb = ManagerPool.dataManager.q_mapContainer.getMap().get(monster.getMapModelId());
					String name = mapdb.getQ_map_name();
					GuildTerritoryFlag territory = ManagerPool.guildFlagManager.getTerritorymap().get(monster.getMapModelId());
					if (territory != null) {
						if(!monster.getParameters().containsKey("hp")){
							monster.getParameters().put("hp", hp);
						}
						int oldhp=(Integer)monster.getParameters().get("hp");
						if (oldhp != hp) {
							monster.getParameters().put("hp", hp);
							if (hp > 50) {
								MessageUtil.notify_guild_all(territory.getGuildid() , String.format("本帮安插在【%s】地图的帮旗遭受攻击，剩余%s％血量",name,hp+""));
							}else if (hp > 1 ) {
								MessageUtil.notify_guild_all(territory.getGuildid() , String.format("本帮安插在【%s】地图的帮旗仅剩余%s％血量，帮内弟兄赶紧前往讨伐敌人，守护帮旗！",name,hp+""));
							}
						}
					}
				}
			}
		}
		return false;
	}

	
	@Override
	public Fighter getAttackTarget(Monster monster) {
		return null;
	}

	@Override
	public Skill getSkill(Monster monster) {
		return null;
	}

	
	
}
