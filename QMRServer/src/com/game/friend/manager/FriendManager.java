package com.game.friend.manager;

import com.game.country.manager.CountryManager;
import com.game.data.bean.Q_mapBean;
import com.game.data.manager.DataManager;
import com.game.friend.message.*;
import com.game.languageres.manager.ResManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.prompt.structs.Notifys;
import com.game.utils.MessageUtil;

/**
 * @author ghost 好友管理类
 */
public class FriendManager {
	//private Logger log = Logger.getLogger(FriendManager.class);

	private static Object obj = new Object();
	//好友管理类实例
	private static FriendManager manager;

	private FriendManager() {
	}

	public static FriendManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new FriendManager();
			}
		}
		return manager;
	}

	public void RelationAdd(Player player, ReqRelationAddToServerMessage message) {
		if (!player.checkTempPlayer()) {
			if (PlayerState.DIE.compare(player.getState())) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您已经死亡，无法添加好友等关系"));
				return;
			}
			ReqRelationAddToWorldMessage sendMessage = new ReqRelationAddToWorldMessage();
			sendMessage.setBtListType(message.getBtListType());
			sendMessage.setOperateplayerId(message.getOperateplayerId());
			sendMessage.setPlayerId(player.getId());
			MessageUtil.send_to_world(sendMessage);
		} else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("游客玩家不能使用好友功能"));
		}
	}

	public void RelationDelete(Player player, ReqRelationDeleteToServerMessage message) {
		ReqRelationDeleteToWorldMessage sendMessage = new ReqRelationDeleteToWorldMessage();
		sendMessage.setBtListType(message.getBtListType());
		sendMessage.setOperateplayerId(message.getOperateplayerId());
		sendMessage.setPlayerId(player.getId());
		MessageUtil.send_to_world(sendMessage);
	}

	public void RelationGetList(Player player, ReqRelationGetListToServerMessage message) {
		ReqRelationGetListToWorldMessage sendMessage = new ReqRelationGetListToWorldMessage();
		sendMessage.setBtListType(message.getBtListType());
		sendMessage.setPlayerId(player.getId());
		MessageUtil.send_to_world(sendMessage);
	}

	public void RelationMood(Player player, ReqRelationMoodToServerMessage message) {
		if (!player.checkTempPlayer()) {
			if (message.getSzMood().contains("|") || message.getSzMood().contains(",")) {
				MessageUtil.notify_player(player, Notifys.CHAT_ROLE, "写心情不能使用“竖线|”或者“英文逗号,”");
				return;
			}
			player.setMood(message.getSzMood());
			ReqRelationMoodToWorldMessage sendMessage = new ReqRelationMoodToWorldMessage();
			sendMessage.setBtListType(message.getBtListType());
			sendMessage.setOperateplayerId(message.getOperateplayerId());
			sendMessage.setSzMood(message.getSzMood());
			sendMessage.setPlayerId(player.getId());
			MessageUtil.send_to_world(sendMessage);
		} else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("游客玩家不能使用好友功能"));
		}
	}

	public void RelationSort(Player player, ReqRelationSortToServerMessage message) {
		ReqRelationSortToWorldMessage sendMessage = new ReqRelationSortToWorldMessage();
		sendMessage.setBtListType(message.getBtListType());
		sendMessage.setOperateplayerId(message.getOperateplayerId());
		sendMessage.setSortNum(message.getSortNum());
		sendMessage.setPlayerId(player.getId());
		MessageUtil.send_to_world(sendMessage);
	}

	public void RelationConfig(Player player, ReqRelationConfigToServerMessage message) {
		player.setOpenMapLocation(message.getOpenMapLocation());
		player.setShowrelation(message.getShowrelation());
		player.setShowicon(message.getShowicon());
		ReqRelationConfigToWorldMessage sendMessage = new ReqRelationConfigToWorldMessage();
		sendMessage.setOpenMapLocation(message.getOpenMapLocation());
		sendMessage.setShowrelation(message.getShowrelation());
		sendMessage.setShowicon(message.getShowicon());
		sendMessage.setPlayerId(player.getId());
		MessageUtil.send_to_world(sendMessage);
	}

	public void RelationInnerAdd(Player player, byte btListType, long lgoperateplayerId, String szAddName) {
		if (!player.checkTempPlayer()) {
			if (!CountryManager.getInstance().isSiegeWarandMap(player) && btListType == 2) {
				return;
			}
			ReqRelationInnerAddToWorldMessage sendMessage = new ReqRelationInnerAddToWorldMessage();
			sendMessage.setBtListType(btListType);
			sendMessage.setOperateplayerId(lgoperateplayerId);
			sendMessage.setPlayerId(player.getId());
			MessageUtil.send_to_world(sendMessage);
		} else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("游客玩家不能使用好友功能"));
		}
	}

	public void RelationInnerNotify(Player player, byte btListType, long lgoperateplayerId, String szfriendNotify, String szenemyNotify) {
		if (!player.checkTempPlayer()) {
			ReqRelationInnerNotifyToWorldMessage sendMessage = new ReqRelationInnerNotifyToWorldMessage();
			sendMessage.setBtListType(btListType);
			sendMessage.setOperateplayerId(lgoperateplayerId);
			sendMessage.setFriendNotify(szfriendNotify);
			sendMessage.setEnemyNotify(szenemyNotify);
			sendMessage.setPlayerId(player.getId());
			MessageUtil.send_to_world(sendMessage);
		} else {
			//MessageUtil.notify_player(player, Notifys.ERROR, "游客玩家不能使用好友功能");
		}
	}

	public void relationLoginTip(Player player) {
		RelationInnerNotify(player, (byte) 5, 0, String.format(ResManager.getInstance().getString("您的好友【%s】上线了"), player.getName()), String.format(ResManager.getInstance().getString("您的仇人【%s】上线了"), player.getName()));
	}

	public void relationQuitTip(Player player) {
		RelationInnerNotify(player, (byte) 2, 0, String.format(ResManager.getInstance().getString("您的好友【%s】下线了"), player.getName()), String.format(ResManager.getInstance().getString("您的仇人【%s】下线了"), player.getName()));
	}

	public void relationKillTip(Player player, Player attFighter) {
		if (!CountryManager.getInstance().isSiegeWarandMap(player) || !CountryManager.getInstance().isSiegeWarandMap(attFighter)) {
			return;
		}
		Q_mapBean mapBean = DataManager.getInstance().q_mapContainer.getMap().get(attFighter.getMap());
		if (mapBean != null) {
			RelationInnerNotify(attFighter, (byte) 2, player.getId(), String.format(ResManager.getInstance().getString("您的好友【%s】在【%s】地图击败了玩家【%s】"), attFighter.getName(), mapBean.getQ_map_name(), player.getName()), String.format(ResManager.getInstance().getString("您的仇人【%s】在【%s】地图击败了玩家【%s】"), attFighter.getName(), mapBean.getQ_map_name(), player.getName()));
		}
	}

	public void relationBackKillTip(Player player, Player attFighter) {
		if (!CountryManager.getInstance().isSiegeWarandMap(player) || !CountryManager.getInstance().isSiegeWarandMap(attFighter)) {
			return;
		}
		Q_mapBean mapBean = DataManager.getInstance().q_mapContainer.getMap().get(attFighter.getMap());
		if (mapBean != null) {
			RelationInnerNotify(player, (byte) 2, attFighter.getId(), String.format(ResManager.getInstance().getString("您的好友【%s】被【%s】在【%s】地图击败了"), player.getName(), attFighter.getName(), mapBean.getQ_map_name()), String.format(ResManager.getInstance().getString("您的仇人【%s】被【%s】在【%s】地图击败了"), player.getName(), attFighter.getName(), mapBean.getQ_map_name()));
		}
	}

	/**
	 * 处理关系列表
	 *
	 * @param player 处理的玩家
	 * @param message 处理关系列表消息
	 * @return
	 */
	public void relationInnerSendList(Player player, ResInnerRelationSendListToWorldMessage message) {
		if (player == null) {
			return;
		}
		if (message.getBtListType() != 0) {
			if (message.getBtListType() == 4) {
				player.setBlackRoleList(message.getRelationList());
			}
		}
	}

	public void loginSendFriendAndGuildConfig(Player player) {
		ResRelationConfigToClientMessage sendMessage = new ResRelationConfigToClientMessage();
		sendMessage.setAutoArgeeAddGuild(player.getAutoArgeeAddGuild());
		sendMessage.setOpenMapLocation(player.getOpenMapLocation());
		sendMessage.setShowrelation(player.getShowrelation());
		sendMessage.setShowicon(player.getShowicon());
		MessageUtil.tell_player_message(player, sendMessage);
	}
}
