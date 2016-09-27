package com.game.gm.manager;

import org.apache.log4j.Logger;

import com.game.db.bean.GameMaster;
import com.game.db.dao.GameMasterDao;
import com.game.player.structs.Player;

public class GMCommandManager {

	private Logger log = Logger.getLogger(GMCommandManager.class);
	
	private GameMasterDao gmDao;
	
	private static Object obj = new Object();
	// 管理类实例
	private static GMCommandManager manager;
	
	private GMCommandManager() {
		gmDao = new GameMasterDao();
		
	}
	public static GMCommandManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new GMCommandManager();
			}
		}
		return manager;
	}
//	/**
//	 * 
//	 * @param player
//	 * @return 正整数表示 GM等级   0表示不是GM
//	 */
//	public int getPlayerGmLevel(long userid){
//		if(GMCommandManager.getInstance().getGmmap().containsKey(userid)){  //gm列表有此玩家
//			return GMCommandManager.getInstance().getGmmap().get(userid).getGmlevel();
//		}else{
//			return 0;
//		}
//	}
	
	public void reloadGMLevel(Player player){
		try {
			long userid = Long.valueOf(player.getUserId());
			GameMaster gm = new GameMaster();
			gm = gmDao.selectByUserid(userid);
			if(gm!=null){
				player.setGmlevel(gm.getGmlevel());
			}else{
				player.setGmlevel(0);
			}
		} catch (Exception e) {
			log.error(e, e);
		}
	}
	
	//测试
	public static void main(String[] args){
		GMCommandManager.getInstance();
//		System.out.println(GMCommandManager.getInstance().getGmmap().size() );
	}
}
