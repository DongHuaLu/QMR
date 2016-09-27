package com.game.marriage.timer;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.buff.structs.Buff;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.marriage.message.ResRingUPToClientMessage;
import com.game.marriage.structs.Marriage;
import com.game.marriage.structs.Spouse;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.timer.TimerEvent;
import com.game.utils.MessageUtil;
/**婚礼事件处理
 * 
 * @author zhangrong
 *
 */
public class MarriagePlayerTimer extends TimerEvent{
	private Logger log = Logger.getLogger(MarriagePlayerTimer.class);
	private int serverId;
	
	private int lineId;
	
	private int mapId;

	
	public MarriagePlayerTimer(int serverId, int lineId, int mapId) {
		super(-1,1000);
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
		try {
			while (iter.hasNext()) {
				Player player = (Player) iter.next();
				//不是本线玩家
				if(player.getServerId()!=this.serverId || player.getLine()!=this.lineId || player.getMap()!=this.mapId) continue;
				
				int ms = (int) (System.currentTimeMillis() /1000);
				if (ms%5 == 0) {	//5秒检测一次
					if (player.getMarriageid() > 0 && player.getLevel() >= 30 ) {	//这里只检测结婚状态下，，离婚会在另外地方清理
						//处理 风雨同舟 BUFF   //处理  并蒂连枝 BUFF
						long spouseid = ManagerPool.marriageManager.getSpouseid(player);
						boolean is = map.getPlayers().containsKey(spouseid);//是否在同地图
						List<Buff> bdlz = ManagerPool.buffManager.getBuffByModelId(player, 9172);
						List<Buff> list = ManagerPool.buffManager.getBuffByModelId(player, 9166);
						if (is) {
							if (list.isEmpty()) {
								ManagerPool.buffManager.addBuff(player, player, 9166, 0, 0, 0);//结婚技能 风雨同舟
							}
							if (bdlz.isEmpty()) {
								ManagerPool.buffManager.addBuff(player, player, 9172,0,0,0);;//结婚技能 并蒂连枝
							}
						}else {
							if (list.size() > 0) {
								ManagerPool.buffManager.removeByBuffId(player, 9166);//移除 风雨同舟
							}
							if (bdlz.size()> 0) {
								ManagerPool.buffManager.removeByBuffId(player, 9172);//移除 并蒂连枝
							}
						}
					}
				}
	
				if (player.getMarriageid() > 0) {
					//如果戒指改变
					Marriage marriage = ManagerPool.marriageManager.getMarriage(player.getMarriageid());
					if (marriage!= null && marriage.getStatus() == 0) {
						if (marriage.getCurrringid() > 0  && player.getWeddingringid() > 0 && marriage.getCurrringid() != player.getWeddingringid()) {
							 ManagerPool.marriageManager.refreshMarriageinfo(player ,marriage);	//刷新结婚消息，更换戒指
						}
						Spouse spouse = marriage.getSelfSpouse(player.getId());
						if (spouse != null && spouse.getNoticering() > 0) {	//得到原来戒指
							MessageUtil.notify_player(player, Notifys.CHAT_ROLE, "您的爱人为您升级了婚戒");
							ResRingUPToClientMessage upmsg=new ResRingUPToClientMessage();
							MessageUtil.tell_player_message(player, upmsg);
							ManagerPool.marriageManager.giveoldRing(player.getId(),marriage.getId(),marriage.getOldringid());//给原来的戒指
							spouse.setNoticering(0);
							ManagerPool.marriageManager.saveMarriageinfo(marriage.getId(), marriage, false);
						}
					}
				}

				//离婚标记，在线离婚时，会调用这里
				if (player.getDivorceid() > 0) {
					ManagerPool.marriageManager.divorceClearPlayerData(player);
				}
			}			
		} catch (Exception e) {
			log.error(e,e);
		}
		

	}
			
	
	
	
	
}
