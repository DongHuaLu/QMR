package com.game.manager;

import com.game.dblog.LogService;
import com.game.player.manager.PlayerManager;
import com.game.publogin.manager.PublicPlayerManager;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-15
 * 
 * 类说明 
 */
public class ManagerPool {
	//玩家管理类
	public static PlayerManager playerManager = PlayerManager.getInstance();
	
	public static PublicPlayerManager publicPlayerManager = PublicPlayerManager.getInstance();
	
	public static LogService logservice=LogService.getInstance();
	
}
