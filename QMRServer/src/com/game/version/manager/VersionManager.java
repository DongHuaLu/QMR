package com.game.version.manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_versionBean;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.utils.MessageUtil;
import com.game.version.bean.VersionResInfo;
import com.game.version.message.ReqVersionUpdateToWorldMessage;
import com.game.version.message.ResVersionResInfoMessage;

public class VersionManager {
	Logger log = Logger.getLogger(VersionManager.class);
	
	private static Object obj = new Object();
	//玩家管理类实例
	private static VersionManager manager;
	private VersionManager() {
	}

	public static VersionManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new VersionManager();
			}
		}
		return manager;
	}
	
	
	/**
	 * 登录时通知前端 RES版本号
	 *
	 */
	public void resVersionResInfo(Player player) {
		ResVersionResInfoMessage cmsg = new ResVersionResInfoMessage();
		HashMap<String, Q_versionBean> versionmap = ManagerPool.dataManager.q_versionContainer.getMap();
		Set<Entry<String, Q_versionBean>> entry = versionmap.entrySet();
		Iterator<Entry<String, Q_versionBean>> it = entry.iterator();
		while (it.hasNext()) {
			Entry<String, Q_versionBean> ver = it.next();
			VersionResInfo verinfo = new VersionResInfo();
			verinfo.setTabname(ver.getKey());
			verinfo.setVersion(ver.getValue().getQ_int_value());
			cmsg.getVersionlist().add(verinfo);
		}
		MessageUtil.tell_player_message(player, cmsg);
	}

	
	
	
	
	

	/**通知世界服务器进行重读data广播
	 * 
	 */
	public void versionUpdateToWorld(Player player){
		ReqVersionUpdateToWorldMessage wmsg = new ReqVersionUpdateToWorldMessage();
		if (player != null) {
			wmsg.setPlayerId(player.getId());
		}
		MessageUtil.send_to_world(wmsg);
	}
	
	
	
	
	/**世界服务器通知所有game重读data
	 * 
	 */
	public void stResVersionUpdateToGameMessage() {
		allreloaddata();
	}
	
	
	
	/**重读所有数据
	 * 
	 */
	public void allreloaddata() {
		ManagerPool.dataManager.setData();
		ManagerPool.reloadDataManager();
		log.info("游戏服务器数据库重读完成。");
	}
	
}
