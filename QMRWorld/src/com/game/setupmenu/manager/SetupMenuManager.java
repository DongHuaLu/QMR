package com.game.setupmenu.manager;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerWorldInfo;
import com.game.setupmenu.message.ResSetMenuStatusToWorldMessage;

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
	//世界服务器目前只用到这3个
	/**
	 * 禁止他人加我为好友
	 */
	private final int JOIN_FRIEND = 1;
	/**
	 * 禁止他人邀请我加帮会
	 */
	public final int JOIN_GUILD = 4;
	/**
	 * 禁止他人邀请我加队伍
	 */
	private final int JOIN_TEAM = 8;

	/**
	 * 是否禁止加队伍
	 *
	 * @param player
	 * @return	true为禁止
	 */
	public boolean isBanTeam(Player player) {
		return isSetMenu(player, JOIN_TEAM);
	}

	/**
	 * 是否禁止加帮会
	 *
	 * @param player
	 * @return true为禁止
	 */
	public boolean isBanGuild(Player player) {
		return isSetMenu(player, JOIN_GUILD);
	}

	/**
	 * 是否禁止他人添加我为好友
	 *
	 * @return true为禁止
	 */
	public boolean isBanFriend(Player player) {
		return isSetMenu(player, JOIN_FRIEND);
	}

	/**
	 * 是否在游戏设置里设定了选项 true 为 已选
	 */
	public boolean isSetMenu(Player player, int type) {
		int sta = player.getMenustatus();
		if ((sta & type) != 0) {
			return true;
		}
		return false;
	}

	/**
	 * 是否禁止他人添加我为好友(World)
	 *
	 * @return true为禁止
	 */
	public boolean isBanFriendByWorld(PlayerWorldInfo playerWorldInfo) {
		return isSetMenuByWorld(playerWorldInfo, JOIN_FRIEND);
	}

	/**
	 * 是否在游戏设置里设定了选项 true 为 已选(World)
	 */
	public boolean isSetMenuByWorld(PlayerWorldInfo playerWorldInfo, int type) {
		int sta = playerWorldInfo.getMenustatus();
		if ((sta & type) != 0) {
			return true;
		}
		return false;
	}

	/**
	 * 更新玩家系统设置
	 *
	 * @param msg
	 */
	public void stResSetMenuStatusToWorldMessage(ResSetMenuStatusToWorldMessage msg) {
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		if (player != null) {
			player.setMenustatus(msg.getMenustatus());
			player.setWorldMenustatus(player.getMenustatus());
		}
	}
}
