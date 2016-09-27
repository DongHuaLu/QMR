package com.game.biwudao.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.game.biwudao.manager.BiWuDaoManager;
import com.game.data.bean.Q_characterBean;
import com.game.data.bean.Q_mapBean;
import com.game.data.manager.DataManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.timer.TimerEvent;

public class BiWuDaoTimer extends TimerEvent{

	private int serverId;
	
	private int lineId;
	
	private int mapId;

	
	
	public BiWuDaoTimer(int serverId, int lineId, int mapId) {
		super(-1,1000);
		this.serverId=serverId;
		this.lineId=lineId;
		this.mapId = mapId;
	}
	

	@Override
	public void action() {
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		long systime = System.currentTimeMillis()/1000;

		if (map.getMapModelid() != BiWuDaoManager.BIWUDAO_MAPID) {
			return;
		}
		if (ManagerPool.biWuDaoManager.getBiwudaostate() != 1) {	//不是活动时间
			//结束后检测回城标记，所有人回城
			if (ManagerPool.biWuDaoManager.getBiwudaostate() == 2 && map.getParameters().containsKey("move")) {
				map.getParameters().remove("move");
				if(map.getPlayerNumber() > 0){
					Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(map.getMapModelid());
					HashMap<Long, Player> players = map.getPlayers();
					List<Player> playerslist=new ArrayList<Player>();
					playerslist.addAll(players.values());
					for (Player player :playerslist) {
						Position position = ManagerPool.mapManager.RandomDieBackCity(mapBean);
						ManagerPool.playerManager.autoRevive(player);
						ManagerPool.playerManager.changePkState(player, 0, 0);
						ManagerPool.mapManager.changeMap(player, mapBean.getQ_map_quit(), mapBean.getQ_map_quit(), 0, position, this.getClass().getName() + ".action");
					}
					playerslist = null;
				}
			}
			return;
		}

		if ( systime % 5 == 0) {		//5秒得到1次打坐奖励
			//遍历玩家列表
			Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
			Player[] players = map.getPlayers().values().toArray(new Player[0]);
			for (Player player : players) {
				//不是本线玩家
				if(player.isDie() || player.getServerId()!=this.serverId || player.getLine()!=this.lineId || player.getMap()!=this.mapId) continue;
				int gridtype = ManagerPool.mapManager.getGridType(grids, player.getPosition());
				int multiple = 1;
				gridtype = gridtype &0xFC;
				if (gridtype == 32) {	//游泳区
					multiple = 5;
				}else if (gridtype == 16) {  
					multiple = 15;
				}else if (gridtype == 8) {
					multiple = 30;
				}else if (gridtype == 4) {
					multiple = 60;
				}
				if (player.getGuildId() > 0 && player.getGuildId() == ManagerPool.biWuDaoManager.getBiwudaoguildid()) {
					multiple = multiple *2;	//占领旗帜的帮会再加倍奖励
				}
				
				Q_characterBean model = DataManager.getInstance().q_characterContainer.getMap().get(player.getLevel());
				
				int zq= multiple*model.getQ_dazuozq();
				int exp = multiple*model.getQ_dazuoexp();
				player.setBiwudaototalexp( exp + player.getBiwudaototalexp());
				player.setBiwudaototalzhenqi( zq + player.getBiwudaototalzhenqi());
				ManagerPool.playerManager.addExp(player, exp,AttributeChangeReason.BIWUDAO_TIME);
				ManagerPool.playerManager.addZhenqi(player, zq,AttributeChangeReason.BIWUDAO_TIME);	
				
				ManagerPool.biWuDaoManager.totalGainToClien(player);
			}
		}
	}
}










