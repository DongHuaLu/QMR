package com.game.dazuo.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.game.dazuo.manager.PlayerDaZuoManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.timer.TimerEvent;

/**
 * 清除过时的双修申请
 * @author 赵聪慧
 * @2012-8-11 下午5:17:25
 */
public class DaZuoApplyClearTimer extends TimerEvent {
	private int lineId;
	private int serverId;
	private int mapId;
	public DaZuoApplyClearTimer(int serverId, int lineId, int mapId) {
		super(-1,1000);
		this.serverId = serverId;
		this.lineId=lineId;
		this.mapId=mapId;
	}

	@Override
	public void action() {
		// 获取地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		// 遍历玩家列表
		Iterator<Player> iter = map.getPlayers().values().iterator();
		
		while (iter.hasNext()) {
			Player player = (Player) iter.next();
			if(!PlayerState.SIT.compare(player.getState())){
				continue;
			}
			List<Long> remove=new ArrayList<Long>();
			HashMap<Long,Long> dazuoAcceptList = player.getDazuoAcceptList();
			if(dazuoAcceptList==null||dazuoAcceptList.size()<=0){
				continue;
			}
			Set<Long> keySet = dazuoAcceptList.keySet();
			for (Long roleId : keySet) {
				Long time = dazuoAcceptList.get(roleId);
				if(time!=null&&System.currentTimeMillis()-time>PlayerDaZuoManager.SHUANGXIUTIMEOUT){
					remove.add(roleId);
					
				}
			}
			for (Long key : remove) {
				player.getDazuoAcceptList().remove(key);
			}
		}
	}

}
