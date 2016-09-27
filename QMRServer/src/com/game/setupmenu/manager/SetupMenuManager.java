package com.game.setupmenu.manager;

import com.game.player.structs.Player;
import com.game.setupmenu.message.ReqGetMenuStatusMessage;
import com.game.setupmenu.message.ReqSetMenuStatusMessage;
import com.game.setupmenu.message.ResMenuStatusMessage;
import com.game.setupmenu.message.ResSetMenuStatusToWorldMessage;
import com.game.setupmenu.struts.SetupMenuType;
import com.game.utils.MessageUtil;


public class SetupMenuManager {


	private SetupMenuManager() {
	}
	
	//玩家管理类实例
	private static SetupMenuManager manager;
	private static Object obj = new Object();
	public static SetupMenuManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new SetupMenuManager();
			}
		}
		return manager;
	}
	
	

	
	/**
	 * 设置菜单状态消息
	 * @param msg
	 */
	public void stReqSetMenuStatusMessage(Player player,ReqSetMenuStatusMessage msg){
		player.setMenustatus(msg.getMenustatus());
		player.setClientset(msg.getClientset());
		//同步到世界服务器
		ResSetMenuStatusToWorldMessage wmsg = new ResSetMenuStatusToWorldMessage();
		wmsg.setMenustatus(msg.getMenustatus());
		wmsg.setPlayerid(player.getId());
		MessageUtil.send_to_world(wmsg);
	}
	
	/**前端请求获取菜单设置
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqGetMenuStatusMessage(Player player,ReqGetMenuStatusMessage msg ){
		stResMenuStatus(player);
	}
	
	
	/**发送系统菜单设置
	 * 
	 * @param player
	 */
	public void stResMenuStatus(Player player ){
		ResMenuStatusMessage msg = new ResMenuStatusMessage();
		msg.setMenustatus(player.getMenustatus());
		msg.setClientset(player.getClientset());
		MessageUtil.tell_player_message(player, msg);	
	}
	
	
	/**是否在游戏设置里设定了选项
	 * 	true 为  已选 
	 */
	public boolean isSetMenu(Player player ,SetupMenuType type) {
		int sta = player.getMenustatus();
		if ((sta &  type.getValue()) != 0) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	
}
