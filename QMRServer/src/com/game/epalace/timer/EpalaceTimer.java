package com.game.epalace.timer;

import java.util.Iterator;
import java.util.List;

import com.game.epalace.bean.EpalaceInfo;
import com.game.epalace.message.ResEpalaceDialToClientMessage;
import com.game.epalace.message.ResEpalaceWalkToClientMessage;
import com.game.epalace.structs.Epalace;
import com.game.epalace.structs.Epos;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.timer.TimerEvent;
import com.game.utils.MessageUtil;

public class EpalaceTimer extends TimerEvent{

	private int serverId;
	
	private int lineId;
	
	private int mapId;
	
	public EpalaceTimer(int serverId, int lineId, int mapId) {
		super(-1,500);
		this.serverId=serverId;
		this.lineId=lineId;
		this.mapId = mapId;
	}
	

	@Override
	public void action() {
		//获取地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		//遍历玩家列表
		Iterator<Player> iter = map.getPlayers().values().iterator();
		
		while (iter.hasNext()) {
			Player player = (Player) iter.next();
			//不是本线玩家
			if(player.getServerId()!=this.serverId || player.getLine()!=this.lineId || player.getMap()!=this.mapId) continue;
			
			Epalace epalace = player.getEpalace();
			List<Epos> pathlist = epalace.getEposlist();
			int num = pathlist.size();
			if (num > 0) {
				ResEpalaceWalkToClientMessage cmsg = new ResEpalaceWalkToClientMessage();
				Epos epos = pathlist.get(0);
				if (epos.getContinuedtime() > 0) {
					epos.setContinuedtime(epos.getContinuedtime()-1);
					if (epos.getContinuedtime()== 1) {
						pathlist.remove(0);
						
					}else if (epos.getContinuedtime() == 11 && num != 1) {	//出现罗盘
						ResEpalaceDialToClientMessage dialmsg =new ResEpalaceDialToClientMessage();
						dialmsg.setCurrentpos(epos.getCurrentpos());

						dialmsg.setForkdirection((byte) ManagerPool.epalaceManeger.getDirection(epos.getCurrentpos(),epos.getNextpos()));
						MessageUtil.tell_player_message(player, dialmsg);
						
					}else if (epos.getContinuedtime() == 13 || num ==1) {	//到达执行
						EpalaceInfo info = epos.makeeposinfo();

				
						epalace.setPos(epos.getCurrentpos());
						int fx = ManagerPool.epalaceManeger.getDirection(epos.getPreviouspos(),epos.getCurrentpos());
						epalace.setDirection((byte) fx);	
						
						if (num ==1) {	//最后一个走到岔路，发送消息后删除
							ManagerPool.epalaceManeger.epalaceReward(player, epos.getEventid());
							pathlist.remove(0);
							info.setForkdirection((byte) -2);
						}
						cmsg.setEpalaceInfo(info);
						MessageUtil.tell_player_message(player, cmsg);
					}
					
				}else{
					
					epos = pathlist.remove(0);
					EpalaceInfo info = epos.makeeposinfo();
					if (num == 1) {
						info.setForkdirection((byte) -2);
					}
					epalace.setPos(epos.getCurrentpos());
					int fx = ManagerPool.epalaceManeger.getDirection(epos.getPreviouspos(),epos.getCurrentpos());
					epalace.setDirection((byte) fx);	
					
					if (num == 1 ||(epos.getPreviouspos()> 0 && epos.getEventid() == 1005) ) {//走到最后一步，或者路过,触发地宫至宝
						ManagerPool.epalaceManeger.epalaceReward(player, epos.getEventid());
					}
					cmsg.setEpalaceInfo(info);
					MessageUtil.tell_player_message(player, cmsg);
				}	
			}
		}
	}	
	
	
}
