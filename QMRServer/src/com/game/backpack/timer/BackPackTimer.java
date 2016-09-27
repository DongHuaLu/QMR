package com.game.backpack.timer;

import java.util.Iterator;

import com.game.backpack.manager.BackpackManager;
import com.game.data.bean.Q_backpack_gridBean;
import com.game.data.manager.DataManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.store.manager.StoreManager;
import com.game.timer.TimerEvent;
import com.game.utils.Global;

public class BackPackTimer extends TimerEvent {
	/**
	 * Logger for this class
	 */
//	private static final Logger logger = Logger.getLogger(BackPackTimer.class);

	//private Logger log=Logger.getLogger(BackPackTimer.class);
	
	private int serverId;
	
	private int lineId;
	
	private int mapId;
	
	public BackPackTimer(int serverId, int lineId, int mapId) {
		super(-1,1000);
		this.serverId=serverId;
		this.lineId=lineId;
		this.mapId=mapId;
	}

	@Override
	public void action() {
		//获取地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		//遍历玩家列表
		Iterator<Player> iter = map.getPlayers().values().iterator();
		while (iter.hasNext()) {
			Player player = (Player) iter.next();
//			不是本线玩家
			if(player.getServerId()!=this.serverId || player.getLine()!=this.lineId || player.getMap()!=this.mapId) continue;
			
			int bagCellsNum = player.getBagCellsNum();
			int storeCellsNum = player.getStoreCellsNum();
			if(bagCellsNum<Global.MAX_BAG_CELLS&&bagCellsNum>=Global.BAG_AUTOOPEN_CELL_ID){
				int bagCellTimeCount = player.getBagCellTimeCount()+1;
				Q_backpack_gridBean bagConfig = DataManager.getInstance().q_backpack_gridContainer.getMap().get(Global.BAG_TYPE+"_"+(bagCellsNum+1));
				if(bagCellTimeCount>bagConfig.getQ_time()){
					if(!player.isSendBagOpenCellTime()){
						BackpackManager.getInstance().dealCellTimeQueryMsg(player, bagCellsNum+1);
						player.setSendBagOpenCellTime(true);
					}
//					BackpackManager.getInstance().openCell(player, bagCellsNum+1);
//					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,"成功扩容背包到{1}个!奖励经验{2}",(bagCellsNum+1)+"",bagConfig.getQ_exp()+"");
//					try{
//						CellOpenLog log=new CellOpenLog();
//						log.setAction((byte)0);
//						log.setAfterCells(player.getBagCellsNum());
//						log.setBeforeCells(bagCellsNum);
//						log.setCellId(bagCellsNum+1);
//						log.setResumegold(0);
//						log.setRoleId(player.getId());
//						log.setType(Global.BAG_TYPE);
//						log.setActionId(Config.getId());
//						LogService.getInstance().execute(log);
//					}catch (Exception e) {
//						logger.error(e,e);
//					}
				}else{						
					//未达条件  时间加一秒
					player.setBagCellTimeCount(bagCellTimeCount);
				}
			}
			// 仓库开锁
			if(player.getLevel()>=Global.STORE_NEED_GRADE&&storeCellsNum<Global.MAX_STORE_CELLS&&storeCellsNum>=Global.STORE_AUTOOPEN_CELL_ID){
				Q_backpack_gridBean StoreConfig = DataManager.getInstance().q_backpack_gridContainer.getMap().get(Global.STORE_TYPE+"_"+(storeCellsNum+1));
				int storeCellTimeCount = player.getStoreCellTimeCount()+1;
				if(storeCellTimeCount>StoreConfig.getQ_time()){
					if(!player.isSendStoreOpenCellTime()){
						StoreManager.getInstance().cellTimeQuery(player, storeCellsNum+1, 0);
						player.setSendStoreOpenCellTime(true);
					}
//					StoreManager.getInstance().openCell(player, storeCellsNum+1);
//					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,"成功扩容仓库到{1}个!奖励经验{2}",(storeCellsNum+1)+"",StoreConfig.getQ_exp()+"");
//					try{
//						CellOpenLog log=new CellOpenLog();
//						log.setAction((byte)0);
//						log.setAfterCells(player.getStoreCellsNum());
//						log.setBeforeCells(storeCellsNum);
//						log.setCellId(storeCellsNum+1);
//						log.setResumegold(0);
//						log.setRoleId(player.getId());
//						log.setType(Global.STORE_TYPE);
//						LogService.getInstance().execute(log);
//					}catch (Exception e) {
//						logger.error(e,e);
//					}
				}else{
					//未达条件  时间加一秒
					player.setStoreCellTimeCount(storeCellTimeCount);
				}
			}
			PlayerManager.getInstance().savePlayer(player);
		}
	}
}
