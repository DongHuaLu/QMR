package com.game.friend.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.game.db.bean.Friend;
import com.game.db.dao.FriendDao;
import com.game.dblog.LogService;
import com.game.friend.bean.RelationInfo;
import com.game.friend.log.FriendLog;
import com.game.friend.message.ReqRelationAddToWorldMessage;
import com.game.friend.message.ReqRelationConfigToWorldMessage;
import com.game.friend.message.ReqRelationDeleteToWorldMessage;
import com.game.friend.message.ReqRelationGetListToWorldMessage;
import com.game.friend.message.ReqRelationInnerAddToWorldMessage;
import com.game.friend.message.ReqRelationInnerNotifyToWorldMessage;
import com.game.friend.message.ReqRelationMoodToWorldMessage;
import com.game.friend.message.ReqRelationSortToWorldMessage;
import com.game.friend.message.ResInnerRelationAddNoticeMessage;
import com.game.friend.message.ResInnerRelationSendListToWorldMessage;
import com.game.friend.message.ResRelationAddOrChangeToClientMessage;
import com.game.friend.message.ResRelationDeleteToClientMessage;
import com.game.friend.message.ResRelationGetListToClientMessage;
import com.game.friend.message.ResRelationIconTipToClientMessage;
import com.game.friend.structs.PlayerRelation;
import com.game.languageres.manager.ResManager;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerWorldInfo;
import com.game.prompt.structs.Notifys;
import com.game.server.WorldServer;
import com.game.server.thread.SaveFriendThread;
import com.game.setupmenu.manager.SetupMenuManager;
import com.game.utils.MessageUtil;

/**
 * @author 杨洪岚 好友管理类
 */
public class FriendManager {

	private Logger log = Logger.getLogger(FriendManager.class);
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
	//好友数据库
	private FriendDao friendDao = new FriendDao();
	//所有玩家的好友列表Map
	private HashMap<Long, PlayerRelation> allfriendsHashMap = new HashMap<Long, PlayerRelation>();
	//所有在线玩家的关注列表Map
	private HashMap<Long, HashMap<Long, Integer>> allOnlineAttentionHashMap = new HashMap<Long, HashMap<Long, Integer>>();

	/**
	 * @return the allfriendsHashMap
	 */
	public HashMap<Long, PlayerRelation> getFriendsHashMap() {
		return allfriendsHashMap;
	}

	public HashMap<Long, HashMap<Long, Integer>> getAllOnlineAttentionHashMap() {
		return allOnlineAttentionHashMap;
	}

	/**
	 * 获得玩家关系数据，没有则新建一个
	 *
	 * @param getidLong 玩家id
	 * @return PlayerRelation
	 */
	public PlayerRelation getPlayerRelation(Long getidLong) {
		if (allfriendsHashMap.containsKey(getidLong)) {
			PlayerRelation playerRelation = (PlayerRelation) allfriendsHashMap.get(getidLong);
			if (playerRelation.isBoSave()) {
				playerRelation.setSaveType(SaveFriendThread.FRIEND_UPDATE);
			}
			return playerRelation;
		} else {
			PlayerRelation playerRelation = new PlayerRelation();
			playerRelation.setBoSave(false);
			playerRelation.setSaveType(SaveFriendThread.FRIEND_INSERT);
			playerRelation.setOwnPlayerId(getidLong);
			allfriendsHashMap.put(getidLong, playerRelation);
			return playerRelation;
		}
	}
	//关系人数限制
	private byte Max_FriendNum = 50;				//好友上限
	private byte Max_EnemyNum = 30;					//仇人上限
	private byte Max_RecentcontactpersonNum = 20;	//最近联系人上限
	private byte Max_BlackNum = 20;					//黑名单上限
	//错误代码
	private byte Error_Fail = -1;		//失败
	private byte Error_Success = 0;		//成功
	
	/**
	 * 对所有在线玩家关注列表登陆处理
	 */
	public void loginAllOnlineAttentionHashMap(Player player){
		if (player != null) {
			PlayerRelation playerRelation = getPlayerRelation(player.getId());
			if (playerRelation != null) {
				for (Map.Entry<Long, RelationInfo> entry : playerRelation.getFriendHashMap().entrySet()) {
					RelationInfo relationInfo = entry.getValue();
					if (relationInfo != null) {
						addAllOnlineAttentionHashMap(relationInfo.getLgUserId(), player.getId(), relationInfo.getBtListType());
					}
				}
				for (Map.Entry<Long, RelationInfo> entry : playerRelation.getEnemyHashMap().entrySet()) {
					RelationInfo relationInfo = entry.getValue();
					if (relationInfo != null) {
						addAllOnlineAttentionHashMap(relationInfo.getLgUserId(), player.getId(), relationInfo.getBtListType());
					}
				}
				playerRelation.getRecentcontactpersonHashMap().clear();
			}
		}
	}

	/**
	 * 对所有在线玩家关注列表添加
	 */
	public void addAllOnlineAttentionHashMap(long mainKeyId, long subKeyId, int addListType) {
		if (getAllOnlineAttentionHashMap().containsKey(mainKeyId)) {
			HashMap<Long, Integer> getSubMap = getAllOnlineAttentionHashMap().get(mainKeyId);
			if (getSubMap != null) {
				getSubMap.put(subKeyId, addListType);
			} else {
				getSubMap = new HashMap<Long, Integer>();
				getSubMap.put(subKeyId, addListType);
				getAllOnlineAttentionHashMap().put(mainKeyId, getSubMap);
			}
		} else {
			HashMap<Long, Integer> getSubMap = new HashMap<Long, Integer>();
			getSubMap.put(subKeyId, addListType);
			getAllOnlineAttentionHashMap().put(mainKeyId, getSubMap);
		}
	}
	
	/**
	 * 对所有在线玩家关注列表删除
	 */
	public void removeAllOnlineAttentionHashMap(long mainKeyId, long subKeyId) {
		if (getAllOnlineAttentionHashMap().containsKey(mainKeyId)) {
			HashMap<Long, Integer> getSubMap = getAllOnlineAttentionHashMap().get(mainKeyId);
			if (getSubMap != null) {
				PlayerRelation playerRelation = getPlayerRelation(subKeyId);
				int listtype = 0;
				if (playerRelation != null) {
					if (playerRelation.getFriendHashMap().containsKey(mainKeyId)) {
						listtype = 1;
					}
					if (playerRelation.getFriendHashMap().containsKey(mainKeyId)) {
						listtype = 2;
					}
				}
				if (listtype == 0) {
					getSubMap.remove(subKeyId);
				} else {
					getSubMap.put(subKeyId, listtype);
				}
			}
		}
	}

	/**
	 * 检查列表是否达到数量上限
	 *
	 * @param playerRelation 玩家关系数据
	 * @param btListType 列表类型
	 * @return boolean
	 */
	public boolean checkListMax(PlayerRelation playerRelation, byte btListType) {
		// 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单
		switch (btListType) {
			case 1: {
				if (playerRelation.getFriendHashMap().size() < Max_FriendNum) {
					return true;
				}
			}
			break;
			case 2: {
				if (playerRelation.getEnemyHashMap().size() < Max_EnemyNum) {
					return true;
				}
			}
			break;
			case 3: {
				if (playerRelation.getRecentcontactpersonHashMap().size() <= Max_RecentcontactpersonNum) {
					return true;
				}
			}
			break;
			case 4: {
				if (playerRelation.getBlackHashMap().size() <= Max_BlackNum) {
					return true;
				}
			}
			break;
		}
		return false;
	}

	/**
	 * 根据列表类型添加关系
	 *
	 * @param player 操作玩家
	 * @param playerRelation 操作玩家关系数据
	 * @param btListType 列表类型
	 * @param destPlayerWorldInfo 被操作玩家世界服务器信息
	 * @param destPlayerRelation 被操作玩家关系数据
	 * @param boAddType 添加类型
	 * @return RelationInfo
	 */
	public RelationInfo addRelationByListType(Player player, PlayerRelation playerRelation, byte btListType, PlayerWorldInfo destPlayerWorldInfo, PlayerRelation destPlayerRelation, boolean boAddType) {
		// 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单
		// boAddType true 自动 false 手动
		switch (btListType) {
			case 1: {
				if (boAddType) {
					return null;
				}
				if (player.getId() != destPlayerWorldInfo.getId()) {
					if (checkListMax(playerRelation, btListType)) {
						if (!SetupMenuManager.getInstance().isBanFriendByWorld(destPlayerWorldInfo)) {
							if (player.getCountry() == destPlayerWorldInfo.getCountry()) {
								if (!playerRelation.getBlackHashMap().containsKey(destPlayerWorldInfo.getId())) {
									if (!playerRelation.getFriendHashMap().containsKey(destPlayerWorldInfo.getId())) {
										RelationInfo relationInfo = new RelationInfo();
										if (destPlayerWorldInfo != null) {
											destPlayerWorldInfo.setPopularity(destPlayerWorldInfo.getPopularity() + 1);
											PlayerManager.getInstance().savePlayerWorldInfo(destPlayerWorldInfo);
										}
										//destPlayer.setPopularity(destPlayer.getPopularity() + 1);
//										relationInfo.getPlayerInfo(destPlayer);
										getPlayerInfo(relationInfo, null, destPlayerWorldInfo);
										relationInfo.setBtListType(btListType);
										relationInfo.setBtSortIdx((byte) playerRelation.getFriendHashMap().size());
										relationInfo.setNAddTime((int) (System.currentTimeMillis() / 1000));
										relationInfo.setNRelationDegree(0);
										playerRelation.getFriendHashMap().put(destPlayerWorldInfo.getId(), relationInfo);
										savePlayerRelationData(playerRelation);

										addAllOnlineAttentionHashMap(destPlayerRelation.getOwnPlayerId(), player.getId(), btListType);
//										RelationInfo attentionRelationInfo = new RelationInfo();
////										attentionRelationInfo.getPlayerInfo(player);
//										getPlayerInfo(attentionRelationInfo, player, null);
//										attentionRelationInfo.setNAddTime((int) (System.currentTimeMillis() / 1000));
//										attentionRelationInfo.setBtListType(btListType);
//										destPlayerRelation.getAttentionHashMap().put(player.getId(), attentionRelationInfo);
//										savePlayerRelationData(destPlayerRelation);

										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜您添加好友{1}成功"), destPlayerWorldInfo.getName());

										Player destPlayer = PlayerManager.getInstance().getPlayer(destPlayerWorldInfo.getId());
										if (destPlayer != null) {
											ResRelationIconTipToClientMessage destClientMessage = new ResRelationIconTipToClientMessage();
											destClientMessage.setBtErrorCode(Error_Success);
											destClientMessage.setBtListType(btListType);
											destClientMessage.setFriendModeInfo(player.getFriendModeInfo());
											MessageUtil.tell_player_message(destPlayer, destClientMessage);

											ResRelationAddOrChangeToClientMessage destChangeToClientMessage = new ResRelationAddOrChangeToClientMessage();
											destChangeToClientMessage.setBtErrorCode(Error_Success);
											destChangeToClientMessage.setRelationAddInfo(relationInfo);
											MessageUtil.tell_player_message(destPlayer, destChangeToClientMessage);

											MessageUtil.notify_player(destPlayer, Notifys.SUCCESS, ResManager.getInstance().getString("您被{1}添加好友成功"), player.getName());
										}
										return relationInfo;
									} else {
										MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("该玩家已经是您的好友"));
									}
								} else {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该玩家已在您的黑名单中，请先删除，否则无法添加好友"));
								}
							} else {
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("对不起，该玩家非本国国民，不能添加为好友"));
							}
						} else {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该玩家已经设置禁止添加好友。"));
						}
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您最多只能添加{1}位好友，目前已达上限"), String.valueOf(Max_FriendNum));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您无法加自己为好友"));
				}
			}
			break;
			case 2: {
				if (player.getId() != destPlayerWorldInfo.getId()) {
					if (checkListMax(playerRelation, btListType)) {
						if (!playerRelation.getBlackHashMap().containsKey(destPlayerWorldInfo.getId())) {
							if (!playerRelation.getEnemyHashMap().containsKey(destPlayerWorldInfo.getId())) {
								RelationInfo relationInfo = new RelationInfo();
//								relationInfo.getPlayerInfo(destPlayer);
								getPlayerInfo(relationInfo, null, destPlayerWorldInfo);
								relationInfo.setBtListType(btListType);
								relationInfo.setBtSortIdx((byte) playerRelation.getEnemyHashMap().size());
								relationInfo.setNAddTime((int) (System.currentTimeMillis() / 1000));
								relationInfo.setNPopularity(0);
								relationInfo.setNRelationDegree(0);
								playerRelation.getEnemyHashMap().put(destPlayerWorldInfo.getId(), relationInfo);
								savePlayerRelationData(playerRelation);

								addAllOnlineAttentionHashMap(destPlayerRelation.getOwnPlayerId(), player.getId(), btListType);
//								RelationInfo attentionRelationInfo = new RelationInfo();
////								attentionRelationInfo.getPlayerInfo(player);
//								getPlayerInfo(attentionRelationInfo, player, null);
//								attentionRelationInfo.setNAddTime((int) (System.currentTimeMillis() / 1000));
//								attentionRelationInfo.setBtListType(btListType);
//								destPlayerRelation.getAttentionHashMap().put(player.getId(), attentionRelationInfo);
//								savePlayerRelationData(destPlayerRelation);

								if (boAddType) {
									MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("【{1}】将您击败，已记入仇人列表"), destPlayerWorldInfo.getName());
								} else {
									MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("添加仇人【{1}】成功"), destPlayerWorldInfo.getName());
								}

								Player destPlayer = PlayerManager.getInstance().getPlayer(destPlayerWorldInfo.getId());
								if (destPlayer != null) {
									ResRelationIconTipToClientMessage destClientMessage = new ResRelationIconTipToClientMessage();
									destClientMessage.setBtErrorCode(Error_Success);
									destClientMessage.setBtListType(btListType);
									destClientMessage.setFriendModeInfo(player.getFriendModeInfo());
									MessageUtil.tell_player_message(destPlayer, destClientMessage);
								}
								return relationInfo;
							} else {
								if (boAddType) {
									RelationInfo enemyRelationInfo = playerRelation.getEnemyHashMap().get(destPlayerWorldInfo.getId());
									enemyRelationInfo.setNRelationDegree(enemyRelationInfo.getNRelationDegree() - 1);
									MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("对【{1}】玩家的历史战绩-1"), destPlayerWorldInfo.getName());
									ResRelationAddOrChangeToClientMessage sendEnemyMessage = new ResRelationAddOrChangeToClientMessage();
									sendEnemyMessage.setBtErrorCode(Error_Success);
									sendEnemyMessage.setRelationAddInfo(enemyRelationInfo);
									MessageUtil.tell_player_message(player, sendEnemyMessage);
									savePlayerRelationData(playerRelation);

									if (destPlayerRelation.getEnemyHashMap().containsKey(player.getId())) {
										RelationInfo destenemyRelationInfo = destPlayerRelation.getEnemyHashMap().get(player.getId());
										destenemyRelationInfo.setNRelationDegree(destenemyRelationInfo.getNRelationDegree() + 1);
										Player destPlayer = PlayerManager.getInstance().getPlayer(destPlayerWorldInfo.getId());
										if (destPlayer != null) {
											MessageUtil.notify_player(destPlayer, Notifys.NORMAL, ResManager.getInstance().getString("对【{1}】玩家的历史战绩+1"), player.getName());
											ResRelationAddOrChangeToClientMessage sendDestEnemyMessage = new ResRelationAddOrChangeToClientMessage();
											sendDestEnemyMessage.setBtErrorCode(Error_Success);
											sendDestEnemyMessage.setRelationAddInfo(destenemyRelationInfo);
											MessageUtil.tell_player_message(destPlayer, sendDestEnemyMessage);
										}
										savePlayerRelationData(destPlayerRelation);
									}
								} else {
									MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("该玩家已经是您的仇人"));
								}
							}
						} else {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该玩家已在您的黑名单中，请先删除，否则无法添加仇人"));
							if (boAddType) {
								if (destPlayerRelation.getEnemyHashMap().containsKey(player.getId())) {
									RelationInfo destenemyRelationInfo = destPlayerRelation.getEnemyHashMap().get(player.getId());
									destenemyRelationInfo.setNRelationDegree(destenemyRelationInfo.getNRelationDegree() + 1);
									Player destPlayer = PlayerManager.getInstance().getPlayer(destPlayerWorldInfo.getId());
									if (destPlayer != null) {
										MessageUtil.notify_player(destPlayer, Notifys.NORMAL, ResManager.getInstance().getString("对【{1}】玩家的历史战绩+1"), player.getName());
										ResRelationAddOrChangeToClientMessage sendDestEnemyMessage = new ResRelationAddOrChangeToClientMessage();
										sendDestEnemyMessage.setBtErrorCode(Error_Success);
										sendDestEnemyMessage.setRelationAddInfo(destenemyRelationInfo);
										MessageUtil.tell_player_message(destPlayer, sendDestEnemyMessage);
									}
									savePlayerRelationData(destPlayerRelation);
								}
							}
						}
					} else {
						if (boAddType) {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("【{1}】将您击败，您的仇人数量已达{2}，记入仇人列表失败"), destPlayerWorldInfo.getName(), String.valueOf(Max_EnemyNum));
						} else {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您最多只能添加{1}位仇人，目前已达上限"), String.valueOf(Max_EnemyNum));
						}
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您无法加自己为仇人"));
				}
			}
			break;
			case 3: {//最近联系人不用返回消息，系统添加的
				if (!boAddType) {
					return null;
				}
				if (player.getId() != destPlayerWorldInfo.getId()) {
					if (player.getCountry() == destPlayerWorldInfo.getCountry()) {
						if (!playerRelation.getRecentcontactpersonHashMap().containsKey(destPlayerWorldInfo.getId())) {
							RelationInfo relationInfo = new RelationInfo();
//							relationInfo.getPlayerInfo(destPlayer);
							getPlayerInfo(relationInfo, null, destPlayerWorldInfo);
							relationInfo.setBtListType(btListType);
							relationInfo.setBtSortIdx((byte) playerRelation.getRecentcontactpersonHashMap().size());
							relationInfo.setNAddTime((int) (System.currentTimeMillis() / 1000));
							relationInfo.setNPopularity(0);
							relationInfo.setNRelationDegree(0);
							playerRelation.getRecentcontactpersonHashMap().put(destPlayerWorldInfo.getId(), relationInfo);
							if (!checkListMax(playerRelation, btListType)) {
								RelationInfo deleteRelationInfo = playerRelation.sortMapByListToDelete(playerRelation.getRecentcontactpersonHashMap(), true);
								ResRelationDeleteToClientMessage sendMessage = new ResRelationDeleteToClientMessage();
								sendMessage.setBtListType(deleteRelationInfo.getBtListType());
								sendMessage.setOperateplayerId(deleteRelationInfo.getLgUserId());
								sendMessage.setBtErrorCode(Error_Success);
								MessageUtil.tell_player_message(player, sendMessage);
								sortRelationList(playerRelation.getRecentcontactpersonHashMap());
							}
							//savePlayerRelationData(playerRelation);
							return relationInfo;
						} else {
							RelationInfo relationInfo = playerRelation.getRecentcontactpersonHashMap().get(destPlayerWorldInfo.getId());
							if (relationInfo != null) {
								relationInfo.setNAddTime((int) (System.currentTimeMillis() / 1000));
								//savePlayerRelationData(playerRelation);
								return relationInfo;
							}
						}
					}
				}
			}
			break;
			case 4: {
				if (boAddType) {
					return null;
				}
				if (player.getId() != destPlayerWorldInfo.getId()) {
					if (player.getCountry() == destPlayerWorldInfo.getCountry()) {
						if (!playerRelation.getFriendHashMap().containsKey(destPlayerWorldInfo.getId())) {
							if (!playerRelation.getEnemyHashMap().containsKey(destPlayerWorldInfo.getId())) {
								if (!playerRelation.getBlackHashMap().containsKey(destPlayerWorldInfo.getId())) {
									RelationInfo relationInfo = new RelationInfo();
//									relationInfo.getPlayerInfo(destPlayer);
									getPlayerInfo(relationInfo, null, destPlayerWorldInfo);
									relationInfo.setBtListType(btListType);
									relationInfo.setBtSortIdx((byte) playerRelation.getBlackHashMap().size());
									relationInfo.setNAddTime((int) (System.currentTimeMillis() / 1000));
									relationInfo.setNPopularity(0);
									relationInfo.setNRelationDegree(0);
									playerRelation.getBlackHashMap().put(destPlayerWorldInfo.getId(), relationInfo);
									if (!checkListMax(playerRelation, btListType)) {
										RelationInfo deleteRelationInfo = playerRelation.sortMapByListToDelete(playerRelation.getBlackHashMap(), true);
										ResRelationDeleteToClientMessage sendMessage = new ResRelationDeleteToClientMessage();
										sendMessage.setBtListType(deleteRelationInfo.getBtListType());
										sendMessage.setOperateplayerId(deleteRelationInfo.getLgUserId());
										sendMessage.setBtErrorCode(Error_Success);
										MessageUtil.tell_player_message(player, sendMessage);
										sortRelationList(playerRelation.getBlackHashMap());
									}
									MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("添加黑名单【{1}】成功"), destPlayerWorldInfo.getName());
									savePlayerRelationData(playerRelation);

									ResInnerRelationSendListToWorldMessage sendListMessage = new ResInnerRelationSendListToWorldMessage();
									sendListMessage.setBtListType(btListType);
									sendListMessage.setRelationList(playerRelation.getListWithMap(playerRelation.getBlackHashMap()));
									MessageUtil.send_to_game(player, sendListMessage);

									return relationInfo;
								} else {
									MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("该玩家已经在黑名单中"));
								}
							} else {
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("{1}玩家是您的仇人，请先从仇人列表中删除再添加黑名单"), destPlayerWorldInfo.getName());
							}
						} else {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("{1}玩家是您的好友，请先从好友列表中删除再添加黑名单"), destPlayerWorldInfo.getName());
						}
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("对不起，该玩家非本国国民，不能添加进入黑名单"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您无法加自己为黑名单"));
				}
			}
			break;
		}
		return null;
	}

	/**
	 * 列表排序
	 *
	 * @param getMap 关系Map
	 * @return
	 */
	public void sortRelationList(HashMap<Long, RelationInfo> getMap) {
		List<RelationInfo> tolist = new ArrayList<RelationInfo>();
		getRelationMapInfoToList(tolist, getMap);
		Collections.sort(tolist, new Comparator<RelationInfo>() {

			@Override
			public int compare(RelationInfo o1, RelationInfo o2) {
				return o1.getBtSortIdx() - o2.getBtSortIdx();
			}
		});
		for (int i = 0; i < tolist.size(); i++) {
			RelationInfo relationInfo = tolist.get(i);
			if (relationInfo != null) {
				relationInfo.setBtSortIdx((byte) i);
			}
		}
	}

	/**
	 * 根据列表类型删除关系
	 *
	 * @param player 操作玩家
	 * @param playerRelation 操作玩家关系数据
	 * @param btListType 列表类型
	 * @param destPlayerId 被操作玩家Id
	 * @param destPlayerRelation 被操作玩家数据
	 * @return boolean
	 */
	public boolean deleteRelationByListType(Player player, PlayerRelation playerRelation, byte btListType, long destPlayerId, PlayerRelation destPlayerRelation) {
		// 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单
		switch (btListType) {
			case 1: {
				if (playerRelation.getFriendHashMap().containsKey(destPlayerId)) {
					RelationInfo relationInfo = playerRelation.getFriendHashMap().remove(destPlayerId);
					removeAllOnlineAttentionHashMap(destPlayerId, player.getId());
//					if (destPlayerRelation.getAttentionHashMap().containsKey(player.getId())) {
//						destPlayerRelation.getAttentionHashMap().remove(player.getId());
//						savePlayerRelationData(destPlayerRelation);
//					}
					MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("删除好友【{1}】成功"), relationInfo.getSzName());
					sortRelationList(playerRelation.getFriendHashMap());
					savePlayerRelationData(playerRelation);
					return true;
				}
			}
			break;
			case 2: {
				if (playerRelation.getEnemyHashMap().containsKey(destPlayerId)) {
					RelationInfo relationInfo = playerRelation.getEnemyHashMap().remove(destPlayerId);
					removeAllOnlineAttentionHashMap(destPlayerId, player.getId());
//					if (destPlayerRelation.getAttentionHashMap().containsKey(player.getId())) {
//						destPlayerRelation.getAttentionHashMap().remove(player.getId());
//						savePlayerRelationData(destPlayerRelation);
//					}
					MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("删除仇人【{1}】成功"), relationInfo.getSzName());
					sortRelationList(playerRelation.getEnemyHashMap());
					savePlayerRelationData(playerRelation);
					return true;
				}
			}
			break;
			case 3: {
				if (playerRelation.getRecentcontactpersonHashMap().containsKey(destPlayerId)) {
					RelationInfo relationInfo = playerRelation.getRecentcontactpersonHashMap().remove(destPlayerId);
					MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("删除最近联系人【{1}】成功"), relationInfo.getSzName());
					sortRelationList(playerRelation.getRecentcontactpersonHashMap());
					//savePlayerRelationData(playerRelation);
					return true;
				}
			}
			break;
			case 4: {
				if (playerRelation.getBlackHashMap().containsKey(destPlayerId)) {
					RelationInfo relationInfo = playerRelation.getBlackHashMap().remove(destPlayerId);
					MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("删除黑名单【{1}】成功"), relationInfo.getSzName());
					sortRelationList(playerRelation.getBlackHashMap());
					savePlayerRelationData(playerRelation);

					ResInnerRelationSendListToWorldMessage sendListMessage = new ResInnerRelationSendListToWorldMessage();
					sendListMessage.setBtListType(btListType);
					sendListMessage.setRelationList(playerRelation.getListWithMap(playerRelation.getBlackHashMap()));
					MessageUtil.send_to_game(player, sendListMessage);

					return true;
				}
			}
			break;
		}
		return false;
	}

	/**
	 * 添加关系
	 *
	 * @param playerId 操作id
	 * @param btListType 列表类型
	 * @param operateplayerId 被操作id
	 * @param boAddType 添加类型
	 * @return
	 */
	public void addRelation(long playerId, byte btListType, long operateplayerId, boolean boAddType) {
		//boAddType true 自动 false 手动
		Player player = PlayerManager.getInstance().getPlayer(playerId);
		PlayerWorldInfo destPlayerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(operateplayerId);
		if (player != null && destPlayerWorldInfo != null) {
			if (!player.checkTempPlayer() && !destPlayerWorldInfo.checkTempPlayer()) {
				ResRelationAddOrChangeToClientMessage sendMessage = new ResRelationAddOrChangeToClientMessage();
				sendMessage.setRelationAddInfo(new RelationInfo());
				PlayerRelation playerRelation = getPlayerRelation(player.getId());
				PlayerRelation destPlayerRelation = getPlayerRelation(destPlayerWorldInfo.getId());
				if (playerRelation != null && destPlayerRelation != null) {
					RelationInfo relationInfo = addRelationByListType(player, playerRelation, btListType, destPlayerWorldInfo, destPlayerRelation, boAddType);
					if (relationInfo != null) {
						sendMessage.setRelationAddInfo(relationInfo);
						sendMessage.setBtErrorCode(Error_Success);
						if (PlayerManager.getInstance().isOnline(operateplayerId)) {
							ResInnerRelationAddNoticeMessage noticeMsg = new ResInnerRelationAddNoticeMessage();
							noticeMsg.setPlayerId(operateplayerId);
							MessageUtil.send_to_game(PlayerManager.getInstance().getPlayer(operateplayerId), noticeMsg);
						}
					} else {
						sendMessage.setBtErrorCode(Error_Fail);
					}
				} else {
					sendMessage.setBtErrorCode(Error_Fail);
				}
				if (boAddType && sendMessage.getBtErrorCode() == Error_Fail) {
					return;
				}
				MessageUtil.tell_player_message(player, sendMessage);
			} else {
				//MessageUtil.notify_player(player, Notifys.ERROR, "游客玩家不能使用好友功能");
			}
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(playerId)));
		}
	}

	/**
	 * 执行添加关系消息
	 *
	 * @param message 添加关系消息
	 * @return
	 */
	public void relationAddInWorld(ReqRelationAddToWorldMessage message) {
		addRelation(message.getPlayerId(), message.getBtListType(), message.getOperateplayerId(), false);
	}

	/**
	 * 执行删除关系消息
	 *
	 * @param message 删除关系消息
	 * @return
	 */
	public void relationDeleteInWorld(ReqRelationDeleteToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		//Player destPlayer = PlayerManager.getInstance().getPlayer(message.getOperateplayerId());
		if (player != null && message.getOperateplayerId() != 0) {
			ResRelationDeleteToClientMessage sendMessage = new ResRelationDeleteToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			if (player.getId() != message.getOperateplayerId()) {
				PlayerRelation playerRelation = getPlayerRelation(player.getId());
				PlayerRelation destPlayerRelation = getPlayerRelation(message.getOperateplayerId());
				if (playerRelation != null && destPlayerRelation != null) {
					if (deleteRelationByListType(player, playerRelation, message.getBtListType(), message.getOperateplayerId(), destPlayerRelation)) {
						sendMessage.setBtListType(message.getBtListType());
						sendMessage.setOperateplayerId(message.getOperateplayerId());
						sendMessage.setBtErrorCode(Error_Success);
					}
				}
			}
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 从关系Map里获得所有关系到List
	 *
	 * @param toList 关系List
	 * @param getMap 关系Map
	 * @return
	 */
	public void getRelationMapInfoToList(List<RelationInfo> toList, HashMap<Long, RelationInfo> getMap) {
		for (Map.Entry<Long, RelationInfo> entry : getMap.entrySet()) {
			RelationInfo relationInfo = entry.getValue();
			if (relationInfo.getBtListType() == 0) {
				relationInfo.setBtListType((byte) 1);
			}
			Player destPlayer = null;
			PlayerWorldInfo destPlayerWorldInfo = null;
			destPlayer = PlayerManager.getInstance().getPlayer(relationInfo.getLgUserId());
			if (destPlayer == null) {
				destPlayerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(relationInfo.getLgUserId());
			}
			if (destPlayer != null || destPlayerWorldInfo != null) {
//				relationInfo.getPlayerInfo(destPlayer);
				getPlayerInfo(relationInfo, destPlayer, destPlayerWorldInfo);
			} else {
				relationInfo.setBtState((byte) 0);
			}
			toList.add(relationInfo);
		}
	}

	/**
	 * 执行获取关系列表数据消息
	 *
	 * @param message 获取关系列表数据消息
	 * @return
	 */
	public void relationGetListInWorld(ReqRelationGetListToWorldMessage message) {
		// 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResRelationGetListToClientMessage sendMessage = new ResRelationGetListToClientMessage();
			sendMessage.setRelationMyInfo(new RelationInfo());
//			sendMessage.getRelationMyInfo().getPlayerInfo(player);
			getPlayerInfo(sendMessage.getRelationMyInfo(), player, null);
			PlayerRelation playerRelation = getPlayerRelation(player.getId());
			if (playerRelation != null) {
				switch (message.getBtListType()) {
					case 0: {
						getRelationMapInfoToList(sendMessage.getRelationList(), playerRelation.getFriendHashMap());
						getRelationMapInfoToList(sendMessage.getRelationList(), playerRelation.getEnemyHashMap());
						getRelationMapInfoToList(sendMessage.getRelationList(), playerRelation.getRecentcontactpersonHashMap());
						getRelationMapInfoToList(sendMessage.getRelationList(), playerRelation.getBlackHashMap());
					}
					break;
					case 1: {
						getRelationMapInfoToList(sendMessage.getRelationList(), playerRelation.getFriendHashMap());
					}
					break;
					case 2: {
						getRelationMapInfoToList(sendMessage.getRelationList(), playerRelation.getEnemyHashMap());
					}
					break;
					case 3: {
						getRelationMapInfoToList(sendMessage.getRelationList(), playerRelation.getRecentcontactpersonHashMap());
					}
					break;
					case 4: {
						getRelationMapInfoToList(sendMessage.getRelationList(), playerRelation.getBlackHashMap());
					}
					break;
				}
			}
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 执行改变心情消息
	 *
	 * @param message 改变心情消息
	 * @return
	 */
	public void relationMoodInWorld(ReqRelationMoodToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResRelationAddOrChangeToClientMessage sendMessage = new ResRelationAddOrChangeToClientMessage();
			sendMessage.setRelationAddInfo(new RelationInfo());
			player.setMood(message.getSzMood());
//			sendMessage.getRelationAddInfo().getPlayerInfo(player);
			getPlayerInfo(sendMessage.getRelationAddInfo(), player, null);
			sendMessage.setBtErrorCode(Error_Success);
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 执行改变顺序位置消息
	 *
	 * @param message 改变顺序位置消息
	 * @return
	 */
	public void relationSortInWorld(ReqRelationSortToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			PlayerRelation playerRelation = getPlayerRelation(player.getId());
			if (playerRelation != null) {
				switch (message.getBtListType()) {
					case 1: {
						RelationInfo relationInfo = playerRelation.getFriendHashMap().get(message.getOperateplayerId());
						if (relationInfo != null) {
							if (message.getSortNum() <= 0) {
								relationInfo.setBtSortIdx((byte) 0);
							} else if (message.getSortNum() >= Max_FriendNum) {
								relationInfo.setBtSortIdx((byte) Max_FriendNum);
							} else {
								relationInfo.setBtSortIdx((byte) message.getSortNum());
							}
							savePlayerRelationData(playerRelation);
						} else {
							PlayerRelation destPlayerRelation = getPlayerRelation(message.getOperateplayerId());
							if (destPlayerRelation != null) {
								if (deleteRelationByListType(player, playerRelation, message.getBtListType(), message.getOperateplayerId(), destPlayerRelation)) {
									ResRelationDeleteToClientMessage sendMessage = new ResRelationDeleteToClientMessage();
									sendMessage.setBtListType(message.getBtListType());
									sendMessage.setOperateplayerId(message.getOperateplayerId());
									sendMessage.setBtErrorCode(Error_Success);
									MessageUtil.tell_player_message(player, sendMessage);
								}
							}
						}
					}
					break;
					case 2: {
						RelationInfo relationInfo = playerRelation.getEnemyHashMap().get(message.getOperateplayerId());
						if (relationInfo != null) {
							if (message.getSortNum() <= 0) {
								relationInfo.setBtSortIdx((byte) 0);
							} else if (message.getSortNum() >= Max_EnemyNum) {
								relationInfo.setBtSortIdx((byte) Max_EnemyNum);
							} else {
								relationInfo.setBtSortIdx((byte) message.getSortNum());
							}
							savePlayerRelationData(playerRelation);
						} else {
							PlayerRelation destPlayerRelation = getPlayerRelation(message.getOperateplayerId());
							if (destPlayerRelation != null) {
								if (deleteRelationByListType(player, playerRelation, message.getBtListType(), message.getOperateplayerId(), destPlayerRelation)) {
									ResRelationDeleteToClientMessage sendMessage = new ResRelationDeleteToClientMessage();
									sendMessage.setBtListType(message.getBtListType());
									sendMessage.setOperateplayerId(message.getOperateplayerId());
									sendMessage.setBtErrorCode(Error_Success);
									MessageUtil.tell_player_message(player, sendMessage);
								}
							}
						}
					}
					break;
					case 3: {
						RelationInfo relationInfo = playerRelation.getRecentcontactpersonHashMap().get(message.getOperateplayerId());
						if (relationInfo != null) {
							if (message.getSortNum() <= 0) {
								relationInfo.setBtSortIdx((byte) 0);
							} else if (message.getSortNum() >= Max_RecentcontactpersonNum) {
								relationInfo.setBtSortIdx((byte) Max_RecentcontactpersonNum);
							} else {
								relationInfo.setBtSortIdx((byte) message.getSortNum());
							}
							//savePlayerRelationData(playerRelation);
						} else {
							PlayerRelation destPlayerRelation = getPlayerRelation(message.getOperateplayerId());
							if (destPlayerRelation != null) {
								if (deleteRelationByListType(player, playerRelation, message.getBtListType(), message.getOperateplayerId(), destPlayerRelation)) {
									ResRelationDeleteToClientMessage sendMessage = new ResRelationDeleteToClientMessage();
									sendMessage.setBtListType(message.getBtListType());
									sendMessage.setOperateplayerId(message.getOperateplayerId());
									sendMessage.setBtErrorCode(Error_Success);
									MessageUtil.tell_player_message(player, sendMessage);
								}
							}
						}
					}
					break;
					case 4: {
						RelationInfo relationInfo = playerRelation.getBlackHashMap().get(message.getOperateplayerId());
						if (relationInfo != null) {
							if (message.getSortNum() <= 0) {
								relationInfo.setBtSortIdx((byte) 0);
							} else if (message.getSortNum() >= Max_BlackNum) {
								relationInfo.setBtSortIdx((byte) Max_BlackNum);
							} else {
								relationInfo.setBtSortIdx((byte) message.getSortNum());
							}
							savePlayerRelationData(playerRelation);
						} else {
							PlayerRelation destPlayerRelation = getPlayerRelation(message.getOperateplayerId());
							if (destPlayerRelation != null) {
								if (deleteRelationByListType(player, playerRelation, message.getBtListType(), message.getOperateplayerId(), destPlayerRelation)) {
									ResRelationDeleteToClientMessage sendMessage = new ResRelationDeleteToClientMessage();
									sendMessage.setBtListType(message.getBtListType());
									sendMessage.setOperateplayerId(message.getOperateplayerId());
									sendMessage.setBtErrorCode(Error_Success);
									MessageUtil.tell_player_message(player, sendMessage);
								}
							}
						}
					}
					break;
				}
			}
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	public void relationConfigInWorld(ReqRelationConfigToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			player.setOpenmaplocation(message.getOpenMapLocation());
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 执行内部添加关系消息
	 *
	 * @param message 内部添加关系消息
	 * @return
	 */
	public void relationInnerAddInWorld(ReqRelationInnerAddToWorldMessage message) {
		addRelation(message.getPlayerId(), message.getBtListType(), message.getOperateplayerId(), true);
	}

	/**
	 * 执行内部关系通知消息
	 *
	 * @param message 内部关系通知消息
	 * @return
	 */
	public void relationInnerNotifyInWorld(ReqRelationInnerNotifyToWorldMessage message) {
		PlayerWorldInfo playerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(message.getPlayerId());
		if (playerWorldInfo != null) {
			//PlayerRelation playerRelation = getPlayerRelation(playerWorldInfo.getId());
			HashMap<Long, Integer> getSubMap = getAllOnlineAttentionHashMap().get(playerWorldInfo.getId());
			if (getSubMap != null && !getSubMap.isEmpty()) {
				for (Map.Entry<Long, Integer> entry : getSubMap.entrySet()) {
					Long subKeyId = entry.getKey();
					Integer listType = entry.getValue();
					if (subKeyId != 0 && (message.getOperateplayerId() == 0 || message.getOperateplayerId() != subKeyId)) {
						Player destPlayer = PlayerManager.getInstance().getPlayer(subKeyId);
						if (destPlayer != null) {
							if (listType == 1) {
								if (message.getBtListType() == 5) {
									MessageUtil.notify_player(destPlayer, Notifys.CUTOUT, message.getFriendNotify());
								} else {
									MessageUtil.notify_player(destPlayer, Notifys.NORMAL, message.getFriendNotify());
								}
							} else if (listType == 2) {
								MessageUtil.notify_player(destPlayer, Notifys.NORMAL, message.getEnemyNotify());
							}
							ResRelationAddOrChangeToClientMessage sendMessage = new ResRelationAddOrChangeToClientMessage();
							sendMessage.setBtErrorCode(Error_Success);
							sendMessage.setRelationAddInfo(new RelationInfo());
							Player player = PlayerManager.getInstance().getPlayer(playerWorldInfo.getId());
							getPlayerInfo(sendMessage.getRelationAddInfo(), player, playerWorldInfo);
							MessageUtil.tell_player_message(destPlayer, sendMessage);
						}
					}
				}
//				for (Map.Entry<Long, RelationInfo> entry : playerRelation.getAttentionHashMap().entrySet()) {
//					RelationInfo relationInfo = entry.getValue();
//					if (relationInfo != null && (message.getOperateplayerId() == 0 || message.getOperateplayerId() != relationInfo.getLgUserId())) {
//						Player destPlayer = PlayerManager.getInstance().getPlayer(relationInfo.getLgUserId());
//						if (destPlayer != null) {
//							if (relationInfo.getBtListType() == 1) {
//								if (message.getBtListType() == 5) {
//									MessageUtil.notify_player(destPlayer, Notifys.CUTOUT, message.getFriendNotify());
//								} else {
//									MessageUtil.notify_player(destPlayer, Notifys.NORMAL, message.getFriendNotify());
//								}
//							} else if (relationInfo.getBtListType() == 2) {
//								MessageUtil.notify_player(destPlayer, Notifys.NORMAL, message.getEnemyNotify());
//							}
//							ResRelationAddOrChangeToClientMessage sendMessage = new ResRelationAddOrChangeToClientMessage();
//							sendMessage.setBtErrorCode(Error_Success);
//							sendMessage.setRelationAddInfo(new RelationInfo());
//							Player player = PlayerManager.getInstance().getPlayer(playerWorldInfo.getId());
//							getPlayerInfo(sendMessage.getRelationAddInfo(), player, playerWorldInfo);
//							MessageUtil.tell_player_message(destPlayer, sendMessage);
//						}
//					}
//				}
			}
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 保存单个玩家数据
	 *
	 * @param playerRelation 玩家关系数据
	 * @return
	 */
	public void savePlayerRelationData(PlayerRelation playerRelation) {
		Friend friend = new Friend();
		playerRelation.saveAllInfo(friend);
		WorldServer.getInstance().getSaveFriendThread().dealMail(friend, playerRelation.getSaveType());
		playerRelation.setBoSave(true);
//			if (friendDao.update(friend) == 0) {
//				if (friendDao.insert(friend) == 0) {
//					log.error(String.format("数据保存错误，玩家ID[%s]", String.valueOf(friend.getRoleid())));
//				}
//			}
	}

	/**
	 * 保存好友数据
	 *
	 * @param
	 * @return
	 */
	public void saveData() {
		for (long allfriendsid : allfriendsHashMap.keySet()) {
			PlayerRelation playerRelation = allfriendsHashMap.get(allfriendsid);
			if (playerRelation != null) {
				savePlayerRelationData(playerRelation);
			}
		}
	}

	/**
	 * 读取好友数据
	 *
	 * @param
	 * @return
	 */
	public void loadData() {
		try {
			List<Friend> list = friendDao.select();
			Iterator<Friend> iterator = list.iterator();
			while (iterator.hasNext()) {
				Friend friend = (Friend) iterator.next();
				if (friend != null) {
					PlayerRelation playerRelation = new PlayerRelation();
					playerRelation.setBoSave(true);
					playerRelation.setSaveType(SaveFriendThread.FRIEND_UPDATE);
					playerRelation.loadAllInfo(friend);
					allfriendsHashMap.put(friend.getRoleid(), playerRelation);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getPlayerInfo(RelationInfo relationInfo, Player player, PlayerWorldInfo playerWorldInfo) {
		if (player == null && playerWorldInfo != null) {
			player = PlayerManager.getInstance().getPlayer(playerWorldInfo.getId());
		} else if (player != null && playerWorldInfo == null) {
			playerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(player.getId());
		}
		if (playerWorldInfo != null) {
			relationInfo.setLgUserId(playerWorldInfo.getId());
			relationInfo.setSzName(playerWorldInfo.getName());
			relationInfo.setNPopularity(playerWorldInfo.getPopularity());
			relationInfo.setNLevel(playerWorldInfo.getLevel());
			relationInfo.setBtState(PlayerManager.getInstance().isOnline(playerWorldInfo.getId()) ? (byte) 1 : (byte) 0);
			relationInfo.setWebvip(playerWorldInfo.getWebvip());
		}
		if (player != null && player.isSyncdata()) {
			relationInfo.setLgUserId(player.getId());
			relationInfo.setSzName(player.getName());
			relationInfo.setNIcon(player.getAvatarid());
			relationInfo.setBtSex(player.getSex());
			relationInfo.setNLevel(player.getLevel());
			if (player.getOpenmaplocation() == 1) {//等于1公开地图信息
				relationInfo.setNMapId(player.getMap());
			} else {
				relationInfo.setNMapId(0);
			}
			//relationInfo.setNPopularity(player.getPopularity());
			relationInfo.setSzMood(player.getMood());
			relationInfo.setBtState(player.getCurState());//状态 2 摆摊 1 在线 0 离线
		}
	}

	/**
	 * 在该玩家的黑名单里是否存在另一玩家
	 *
	 * @param src 源玩家
	 * @param dest 目标玩家
	 * @return boolean
	 */
	public boolean isContainsBlack(long src, long dest) {
		PlayerRelation playerRelation = getPlayerRelation(src);
		if (playerRelation != null) {
			if (playerRelation.getBlackHashMap().containsKey(dest)) {
				return true;
			}
		}
		return false;
	}

	public void setFriendLog(long friendid, String friendName, String logString) {
		FriendLog friendLog = new FriendLog();
		friendLog.setFriendId(friendid);
		friendLog.setFriendName(friendName);
		friendLog.setLogString(logString);
		LogService.getInstance().execute(friendLog);
	}

	public static String getCaller() {
		int i;
		String str = "";
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		for (i = 0; i < stack.length; i++) {
			StackTraceElement ste = stack[i];
			String outstr = String.format("%d -- %s -- %s -- %s --%s", i, ste.getClassName(), ste.getMethodName(), ste.getFileName(), ste.getLineNumber());
			System.out.println(outstr);
			str += outstr;
			str += "\r\n";
		}
		return str;
	}
}

/*
 * public void toStructs(String infoString) { infoString =
 * infoString.substring(1,infoString.length()-1); String[] infoStrings =
 * infoString.split(","); for (int i = 0; i < infoStrings.length; i++) { String
 * paramString = infoStrings[i]; int idx = paramString.indexOf(":"); if (idx !=
 * -1) { if (paramString.substring(0,idx-1)=="lgUserId") {
 * setLgUserId(Long.valueOf(paramString.substring(idx+1))); }else if
 * (paramString.substring(0,idx-1)=="szName") {
 * setSzName(paramString.substring(idx+1)); }else if
 * (paramString.substring(0,idx-1)=="nPopularity") {
 * setNPopularity(Integer.valueOf(paramString.substring(idx+1))); }else if
 * (paramString.substring(0,idx-1)=="nLevel") {
 * setNLevel(Integer.valueOf(paramString.substring(idx+1))); }else if
 * (paramString.substring(0,idx-1)=="nIcon") {
 * setNIcon(Integer.valueOf(paramString.substring(idx+1))); }else if
 * (paramString.substring(0,idx-1)=="btJob") {
 * setBtJob(Byte.valueOf(paramString.substring(idx+1))); }else if
 * (paramString.substring(0,idx-1)=="szMapName") {
 * setSzMapName(paramString.substring(idx+1)); }else if
 * (paramString.substring(0,idx-1)=="szMood") {
 * setSzMood(paramString.substring(idx+1)); }else if
 * (paramString.substring(0,idx-1)=="nRelationDegree") {
 * setNRelationDegree(Integer.valueOf(paramString.substring(idx+1))); }else if
 * (paramString.substring(0,idx-1)=="nAddTime") {
 * setNAddTime(Integer.valueOf(paramString.substring(idx+1))); }else if
 * (paramString.substring(0,idx-1)=="btState") {
 * setBtState(Byte.valueOf(paramString.substring(idx+1))); }else if
 * (paramString.substring(0,idx-1)=="btListType") {
 * setBtListType(Byte.valueOf(paramString.substring(idx+1))); }else if
 * (paramString.substring(0,idx-1)=="btSortIdx") {
 * setBtSortIdx(Byte.valueOf(paramString.substring(idx+1))); } } } }
 *
 * public void getPlayerInfo(Player player) { setLgUserId(player.getId());
 * setSzName(player.getName()); setNIcon(player.getIcon());
 * setNLevel(player.getLevel()); if (player.getOpenmaplocation() == 0)
 * {//等于0公开地图信息 setSzMapName("某某地"); } setNPopularity(player.getPopularity());
 * setSzMood(player.getMood()); setBtState((byte) 1); }
 *
 * @Override public int compareTo(RelationInfo o) { return (getNAddTime() >=
 * o.getNAddTime()) ? 1 : -1; }
 */
