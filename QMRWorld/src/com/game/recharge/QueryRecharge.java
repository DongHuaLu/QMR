package com.game.recharge;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerWorldInfo;
import com.game.registrar.manager.RegistrarManager;
import com.game.utils.ScriptsUtils;

/**
 * 查询充值
 *
 * @author 杨鸿岚
 */
public class QueryRecharge {
	
	private static Logger log = Logger.getLogger(QueryRecharge.class);

	private static String Error = "-2";	//类型错误
	private static String Fail = "-1";	//没有充值历史
	private static String Success = "0";	//有充值历史

	/**
	 * 查询充值
	 *
	 * @param param 参数列表
	 * @see
	 * 参数-0-游戏服务器脚本id,参数-1-回调函数名,参数-2-回调玩家id,参数-3-查询充值类型,参数-4-查询开始时间(可选),参数-5-查询结束时间(可选)
	 * @return
	 */
	public static void queryRecharge(List<Object> param) {
		int gamescriptid = Integer.parseInt(param.get(0).toString()); 
		String methodname = param.get(1).toString();
		long playerid = Long.valueOf(param.get(2).toString());
		int type = Integer.valueOf(param.get(3).toString());
		Player player = PlayerManager.getInstance().getPlayer(playerid);
		if (player != null) {
			RechargeManager.getInstance().queryRechargeHistory(player);
			if (player.getRechargeHistorys() == null) {
				ScriptsUtils.callGame(player, gamescriptid, methodname, Long.toString(player.getId()), Error);
			}
			if (player.getRechargeHistorys().isEmpty()) {//没有充值历史
				ScriptsUtils.callGame(player, gamescriptid, methodname, Long.toString(player.getId()), Fail);
			} else {
				switch (type) {
					case 0: {//有没有充值
						ScriptsUtils.callGame(player, gamescriptid, methodname, Long.toString(player.getId()), Success);
					}
					break;
					case 1: {//每日的充值
						Calendar calendar = Calendar.getInstance();
						calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0); //当天凌晨
						int result = 0;
						Iterator<RechargeHistory> iterator = player.getRechargeHistorys().iterator();
						while (iterator.hasNext()) {
							RechargeHistory rechargeHistory = iterator.next();
							if (rechargeHistory != null) {
								if (rechargeHistory.getTime() >= calendar.getTimeInMillis()) {
									result += rechargeHistory.getGold();
								}
							}
						}
						ScriptsUtils.callGame(player, gamescriptid, methodname, Long.toString(player.getId()), Success, Integer.toString(result));
					}
					break;
					case 2: {//一段时间的充值
						long startTime = Long.valueOf(param.get(4).toString());
						long endTime = Long.valueOf(param.get(5).toString());
						int result = 0;
						Iterator<RechargeHistory> iterator = player.getRechargeHistorys().iterator();
						while (iterator.hasNext()) {
							RechargeHistory rechargeHistory = iterator.next();
							if (rechargeHistory != null) {
								if (rechargeHistory.getTime() >= startTime && rechargeHistory.getTime() < endTime) {
									result += rechargeHistory.getGold();
								}
							}
						}
						ScriptsUtils.callGame(player, gamescriptid, methodname, Long.toString(player.getId()), Success, Integer.toString(result));
					}
					break;
					case 3: {//总的充值
						int result = 0;
						Iterator<RechargeHistory> iterator = player.getRechargeHistorys().iterator();
						while (iterator.hasNext()) {
							RechargeHistory rechargeHistory = iterator.next();
							if (rechargeHistory != null) {
								result += rechargeHistory.getGold();
							}
						}
						ScriptsUtils.callGame(player, gamescriptid, methodname, Long.toString(player.getId()), Success, Integer.toString(result));
					}
					break;
					default: {//错误的类型
						ScriptsUtils.callGame(player, gamescriptid, methodname, Long.toString(player.getId()), Error);
					}
					break;
				}
			}
		}
	}

	/**
	 * 查询充值
	 *
	 * @param player 玩家
	 * @param type 查询充值类型
	 * @param startTime 查询开始时间(可选)
	 * @param endTime 查询结束时间(可选)
	 * @return
	 */
	public static int queryRecharge(Player player, int type, long startTime, long endTime) {
		int result = 0;
		if (player != null) {
			RechargeManager.getInstance().queryRechargeHistory(player);
			if (player.getRechargeHistorys() == null) {
				return result;
			}
			if (player.getRechargeHistorys().isEmpty()) {//没有充值历史
				result = 0;
			} else {
				switch (type) {
					case 0: {//有没有充值
						result = 1;
					}
					break;
					case 1: {//每日的充值
						Calendar calendar = Calendar.getInstance();
						calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0); //当天凌晨
						Iterator<RechargeHistory> iterator = player.getRechargeHistorys().iterator();
						while (iterator.hasNext()) {
							RechargeHistory rechargeHistory = iterator.next();
							if (rechargeHistory != null) {
								if (rechargeHistory.getTime() >= calendar.getTimeInMillis()) {
									result += rechargeHistory.getGold();
								}
							}
						}
					}
					break;
					case 2: {//一段时间的充值
						Iterator<RechargeHistory> iterator = player.getRechargeHistorys().iterator();
						while (iterator.hasNext()) {
							RechargeHistory rechargeHistory = iterator.next();
							if (rechargeHistory != null) {
								if (rechargeHistory.getTime() >= startTime && rechargeHistory.getTime() < endTime) {
									result += rechargeHistory.getGold();
								}
							}
						}
					}
					break;
					case 3: {//总的充值
						Iterator<RechargeHistory> iterator = player.getRechargeHistorys().iterator();
						while (iterator.hasNext()) {
							RechargeHistory rechargeHistory = iterator.next();
							if (rechargeHistory != null) {
								result += rechargeHistory.getGold();
							}
						}
					}
					break;
					default: {//错误的类型
						result = -1;
					}
					break;
				}
			}
		}
		return result;
	}

	/**
	 * 查询充值
	 *
	 * @param playerid 玩家id
	 * @param type 查询充值类型
	 * @param startTime 查询开始时间(可选)
	 * @param endTime 查询结束时间(可选)
	 * @return
	 */
	public static int queryRecharge(long playerid, int type, long startTime, long endTime) {
		int result = 0;
		Player player = PlayerManager.getInstance().getPlayer(playerid);
		if (player != null) {
			result = queryRecharge(player, type, startTime, endTime);
		}
		return result;
	}

	public static Map<String, String> queryPlayerAwardParam(PlayerWorldInfo playerWorldInfo) {
		if (playerWorldInfo != null) {
			return RegistrarManager.getInstance().getPlayerRegistrarParams(Long.valueOf(playerWorldInfo.getAccount()));
		}
		return new HashMap<String, String>();
	}

	public static Map<String, String> queryPlayerAwardParam(Player player) {
		if (player != null) {
			if (player.getAwardParamMap() == null) {
				player.setAwardParamMap((RegistrarManager.getInstance().getPlayerRegistrarParams(player)));
				return player.getAwardParamMap();
			} else {
				return player.getAwardParamMap();
			}
		}
		return new HashMap<String, String>();
	}

	public static Map<String, String> queryPlayerAwardParam(long playerid) {
		Player player = PlayerManager.getInstance().getPlayer(playerid);
		if (player != null) {
			return queryPlayerAwardParam(player);
		}else{
			PlayerWorldInfo playerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(playerid);
			if (playerWorldInfo != null) {
				return queryPlayerAwardParam(playerWorldInfo);
			}else{
				log.error(String.format("读取玩家活动数据错误,玩家没找到，玩家id[%s]", Long.toString(playerid)));
				return new HashMap<String, String>();
			}
		}
	}

	public static void updatePlayerParams(PlayerWorldInfo playerWorldInfo, Map<String, String> param) {
		if (playerWorldInfo != null) {
			RegistrarManager.getInstance().savePlayerParams(Long.valueOf(playerWorldInfo.getAccount()), param);
		}
	}

	public static void updatePlayerParams(Player player, Map<String, String> param) {
		if (player != null) {
			player.setAwardParamMap(param);
			RegistrarManager.getInstance().savePlayerParams(player, player.getAwardParamMap());
		}
	}

	public static void updatePlayerParams(long playerid, Map<String, String> param) {
		Player player = PlayerManager.getInstance().getPlayer(playerid);
		if (player != null) {
			updatePlayerParams(player, param);
		}else{
			PlayerWorldInfo playerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(playerid);
			if (playerWorldInfo != null) {
				updatePlayerParams(playerWorldInfo, param);
			}else{
				log.error(String.format("保存玩家活动数据错误,玩家没找到，玩家id[%s]", Long.toString(playerid)));
			}
		}
	}
}
