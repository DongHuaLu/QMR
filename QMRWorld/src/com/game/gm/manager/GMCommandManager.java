package com.game.gm.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.db.bean.GameMaster;
import com.game.db.dao.GameMasterDao;
import com.game.player.structs.Player;
import com.game.utils.TimeUtil;

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
	/**
	 * 
	 * @param player
	 * @return 正整数表示 GM等级   0表示不是GM
	 */
	public int getGMLevelByUserid(){
		
		
		return 0;
	}
	
	public List<GameMaster> getGameMasterList(){
		List<GameMaster> gmlist = new ArrayList<GameMaster>();
		try {
			gmlist = gmDao.getGameMasterList();
		} catch (SQLException e) {
			log.error(e, e);
		} 
		return gmlist;
	}
	
	public int updateGameMaster(GameMaster gm){
		try {
			return gmDao.updateGameMaster(gm);
		} catch (SQLException e) {
			log.error(e, e);
		}
		return 0;
	}
	
	public int addGameMaster(long userid, String username, String rolename, int serverid, int gmlevel){
		long now = System.currentTimeMillis(); String date = TimeUtil.getNowStringDate();
		GameMaster gm = new GameMaster(username, userid, serverid, gmlevel, now, date, 0);
		try {
			gmDao.insertGameMaster(gm);
			return 1;
		} catch (SQLException e) {
			log.error(e, e);
		}
		return 0;
	}
	
	//测试
	public static void main(String[] args){
//		System.out.println( GMCommandManager.getInstance().getPlayerGmLevel(369767872094661L));
//		GameMaster gm = new GameMaster();
//		gm.setUserid(555L); gm.setGmlevel(3); gm.setIsDeleted(2); 
//		GMCommandManager.getInstance().updateGameMaster(gm);
//		System.out.println(GMCommandManager.getInstance().updateGameMaster(gm));
//		List<GameMaster> gmlist = new ArrayList<GameMaster>();
//		gmlist = GMCommandManager.getInstance().getGameMasterList();
//		System.out.println(gmlist.get(0).getRolename());
//		GMCommandManager.getInstance().addGameMaster(555L, "username", "角色名", 1, 2);
//		System.out.println(GMCommandManager.getInstance().getGameMasterList().size());
	}
}
