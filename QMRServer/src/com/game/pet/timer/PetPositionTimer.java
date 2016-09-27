package com.game.pet.timer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.map.message.ResSynMonsterPositionMessage;
import com.game.map.structs.Area;
import com.game.map.structs.Map;
import com.game.pet.struts.Pet;
import com.game.timer.TimerEvent;
import com.game.utils.MessageUtil;

//测试专用，前后台位置同步
public class PetPositionTimer extends TimerEvent {

	protected Logger log = Logger.getLogger(PetPositionTimer.class);
	
	public static HashSet<Integer> monsters = new HashSet<Integer>();
	
	private int serverId;
	
	private int lineId;
	
	private int mapId;
	
	public PetPositionTimer(int serverId, int lineId, int mapId){
		super(-1, 200);
		this.serverId = serverId;
		this.lineId = lineId;
		this.mapId = mapId;
	}
	
	@Override
	public void action() {
		//获取地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		if(map.isEmpty()) return;
		HashMap<Integer, Area> areas = map.getAreas();
		Iterator<Area> iterator = areas.values().iterator();
		while (iterator.hasNext()) {
			Area area= iterator.next();
			HashSet<Pet> pets = area.getPets();
			for (Pet pet : pets) {
				ResSynMonsterPositionMessage msg = new ResSynMonsterPositionMessage();
				msg.setPosition(pet.getPosition());
				MessageUtil.tell_round_message(pet, msg);
			}
		}
	}
}
