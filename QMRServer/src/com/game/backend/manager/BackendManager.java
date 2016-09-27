package com.game.backend.manager;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.config.Config;
import com.game.player.structs.Player;
import com.game.structs.Reasons;

public class BackendManager {
	protected Logger log = Logger.getLogger(BackendManager.class);
	private static Object obj = new Object();
	//后台管理类实例
	private static BackendManager manager;

	private BackendManager() { }

	public static BackendManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new BackendManager();
			}
		}
		return manager;
	}
	
	public void changemoney(Player player, int num){
		long actionid = Config.getId();
		BackpackManager.getInstance().changeMoney(player, num, Reasons.BACKEND_MONEY, actionid);
	}
	
	public void changebindgold(Player player, int num){
		long actionid = Config.getId();
		BackpackManager.getInstance().changeBindGold(player, num, Reasons.BACKEND_BINDGOLD, actionid);
	}
	
}
