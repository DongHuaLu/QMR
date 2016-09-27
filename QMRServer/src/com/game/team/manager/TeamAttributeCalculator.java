package com.game.team.manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.game.data.bean.Q_characterBean;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;

public class TeamAttributeCalculator implements PlayerAttributeCalculator{
	
	@Override
	public int getType() {
		return PlayerAttributeType.TEAM;
	}

	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		PlayerAttribute att = new PlayerAttribute();
		if (player.getTeamid() > 0) {
			//基本加成
//			Q_characterBean model = ManagerPool.dataManager.q_characterContainer.getMap().get(player.getLevel());
//			TeamInfo team = ManagerPool.teamManager.getTeam(player.getTeamid());
//			if(team != null){
//				List<TeamMemberInfo> memberInfolList = team.getMemberinfo();
//				
//				if(team!= null && memberInfolList.size() > 0){
//					int sum = 0;
//					for (TeamMemberInfo memberInfo : memberInfolList) {
//						if (memberInfo.getMemberid() != player.getId() && memberInfo.getMembermapid() == player.getMap() && memberInfo.getMemberline() == player.getLine()) {
//							sum = sum +1;
//						}
//					}
//					if (sum > 0) {
//						int hp = model.getQ_hp()*sum/100;		//组队加成
//						att.setMaxHp(hp);
//					}
//				}
//			}

			Q_characterBean model = ManagerPool.dataManager.q_characterContainer.getMap().get(player.getLevel());
			Map map = ManagerPool.mapManager.getMap(player);
			if (map != null) {
				int sum = 0;
				long pid = player.getId();
				long teamid = player.getTeamid();
				HashMap<Long, Player> mapplayers = map.getPlayers();
				if (mapplayers != null && mapplayers.size() > 0) {
					Iterator<Entry<Long, Player>> it = mapplayers.entrySet().iterator();
					while (it.hasNext()) {
						Entry<Long, Player> players =  it.next();
						Player xplayer = players.getValue();
						if (xplayer.getTeamid() > 0 && xplayer.getId() != pid &&xplayer.getTeamid() == teamid) {
							sum=sum+1;
						}
					}
				}
				if(sum > 0){
					int hp = model.getQ_hp();
					HashMap<String, Integer> lyProperty = ManagerPool.longyuanManager.getTianyuanProperty(player);
					if (lyProperty.containsKey(ResManager.getInstance().getString("血量"))) {
						hp = hp + lyProperty.get(ResManager.getInstance().getString("血量"));
					}
					
					
					hp = hp*sum/50;		//组队加成2%
					att.setMaxHp(hp);
				}
			}
		}
		return att;
	}
	
	
}