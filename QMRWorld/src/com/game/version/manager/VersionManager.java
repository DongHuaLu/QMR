package com.game.version.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_versionBean;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.utils.MessageUtil;
import com.game.version.bean.VersionResInfo;
import com.game.version.message.ReqVersionUpdateToWorldMessage;
import com.game.version.message.ResVersionUpdateToClientMessage;
import com.game.version.message.ResVersionUpdateToGameMessage;


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
	
	private static List<VersionResInfo> verlist = new  ArrayList<VersionResInfo>();
	
	
	/**
	 * 生成版本号对比表
	 *
	 */
	public void resVersionResInfo( ) {
		List<Q_versionBean> versionlist = ManagerPool.dataManager.q_versionContainer.getList();
		for (Q_versionBean q_versionBean : versionlist) {
			VersionResInfo verinfo = new VersionResInfo();
			verinfo.setTabname(q_versionBean.getQ_tablename());
			verinfo.setVersion(q_versionBean.getQ_int_value());
			verlist.add(verinfo);
		}
	}

	
	
	
	/**世界服务器广播给所有GAME重读data消息
	 * 
	 */
	public void stReqVersionUpdateToWorldMessage(ReqVersionUpdateToWorldMessage msg) {
		verlist.clear();
		resVersionResInfo();	//生成原表
		allreloaddata();		//重读数据
		HashMap<String, Q_versionBean> versionmap = ManagerPool.dataManager.q_versionContainer.getMap();
		
		ResVersionUpdateToClientMessage cmsg = new ResVersionUpdateToClientMessage();
		for (VersionResInfo verinfo : verlist) {
			Q_versionBean verbean = versionmap.get(verinfo.getTabname());
			if (verbean != null ) {
				if(verbean.getQ_int_value() != verinfo.getVersion()){
					VersionResInfo newverinfo = new VersionResInfo();
					newverinfo.setTabname(verbean.getQ_tablename());
					newverinfo.setVersion(verbean.getQ_int_value());
					cmsg.getVersionlist().add(newverinfo);
				}
			}
		}
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerId());
		ResVersionUpdateToGameMessage gmag = new ResVersionUpdateToGameMessage();
		MessageUtil.send_to_game(gmag);
		if(cmsg.getVersionlist().size() > 0){
			MessageUtil.tell_world_message(cmsg);
			log.info(cmsg);
			if (player != null) {
				MessageUtil.notify_player(player,Notifys.SUCCESS,"数据库重读完成");
			}
		}else {
			log.info("没有需要更新的数据。");
			if (player != null) {
				MessageUtil.notify_player(player,Notifys.ERROR,"没有需要更新的数据");
			}
		}
	}
	
	
	
	
	
	/**重读所有数据
	 * 
	 */
	public void allreloaddata() {
		ManagerPool.dataManager.setData();
		ManagerPool.reloadDataManager();
		log.info("世界服务器数据库重读完成。");
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
}
