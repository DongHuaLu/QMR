package com.game.drop.timer;

import com.game.drop.script.IDropClearScript;
import com.game.drop.structs.MapDropInfo;
import com.game.manager.ManagerPool;
import com.game.map.bean.DropGoodsInfo;
import com.game.map.message.ResRoundGoodsDisappearMessage;
import com.game.map.structs.Area;
import com.game.map.structs.Map;
import com.game.script.structs.ScriptEnum;
import com.game.structs.Position;
import com.game.timer.TimerEvent;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

public class DropClearTimer extends TimerEvent {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DropClearTimer.class);
	private static final int CLEAR_TIME = 60 * 1000;//保留10秒
	private int serverId;
	private int lineId;
	private int mapId;

	public DropClearTimer(int serverId, int lineId, int mapId) {
		super(-1, 1000);
		this.serverId = serverId;
		this.lineId = lineId;
		this.mapId = mapId;
	}

	@Override
	public void action() {
		//获取地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		List<MapDropInfo> mapDropInfos = new ArrayList<MapDropInfo>();
		Iterator<Area> areaIter = map.getAreas().values().iterator();
		while (areaIter.hasNext()) {
			Area area = (Area) areaIter.next();
			if (area.getDropGoods().size() == 0) {
				continue;
			}
			Iterator<MapDropInfo> dropGoodsIterator = area.getDropGoods().values().iterator();
			while (dropGoodsIterator.hasNext()) {
				MapDropInfo next = dropGoodsIterator.next();
				if (next.getCleartimepoint() == 0) {
					DropGoodsInfo dropinfo = next.getDropinfo();
					long time = System.currentTimeMillis() - dropinfo.getDropTime();
					if (time >= CLEAR_TIME) {
						dropGoodsIterator.remove();
						Position position = MapUtils.buildPosition((short) dropinfo.getX(), (short) dropinfo.getY());
						ResRoundGoodsDisappearMessage msg = new ResRoundGoodsDisappearMessage();
						msg.getGoodsIds().add(dropinfo.getDropGoodsId());
						MessageUtil.tell_round_message(next, msg);
					}
				} else {
					if (System.currentTimeMillis() > next.getCleartimepoint()) {
						DropGoodsInfo dropinfo = next.getDropinfo();
						dropGoodsIterator.remove();
						Position position = MapUtils.buildPosition((short) dropinfo.getX(), (short) dropinfo.getY());
						ResRoundGoodsDisappearMessage msg = new ResRoundGoodsDisappearMessage();
						msg.getGoodsIds().add(dropinfo.getDropGoodsId());
						MessageUtil.tell_round_message(next, msg);
						mapDropInfos.add(next);
					}
				}
			}
		}
		for (int i = 0; i < mapDropInfos.size(); i++) {
			MapDropInfo mapDropInfo = mapDropInfos.get(i);
			if (mapDropInfo != null) {
				IDropClearScript script = (IDropClearScript) ManagerPool.scriptManager.getScript(ScriptEnum.DROP_CLEAR);
				if (script != null) {
					try {
						script.dropClear(mapDropInfo);
					} catch (Exception e) {
						logger.error(e, e);
					}
				} else {
					logger.error("物品清除脚本不存在！");
				}
			}
		}
	}
}
