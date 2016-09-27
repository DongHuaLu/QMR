package com.game.offline.manager;

import com.game.backpack.manager.BackpackManager;
import com.game.config.Config;
import com.game.data.bean.Q_characterBean;
import com.game.data.manager.DataManager;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.offline.message.ReqGetRetreatAwardMessage;
import com.game.offline.message.ReqRetreatInfoMessage;
import com.game.offline.message.ResRetreatInfoMessage;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.AttributeChangeReason;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.CommonConfig;
import com.game.utils.MessageUtil;
import org.apache.log4j.Logger;

/**
 * 离线系统(现只包括闭关)
 *
 * @author 杨鸿岚
 */
public class OffLineManager {

	protected Logger log = Logger.getLogger(OffLineManager.class);
	//玩家管理类实例
	private static OffLineManager manager;
	private static Object obj = new Object();

	private OffLineManager() {
	}

	public static OffLineManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new OffLineManager();
			}
		}
		return manager;
	}
	private int retreatItemId = 1009;	//闭关符ID

	/**
	 * 获得闭关时间
	 *
	 * @param player
	 * @return
	 */
	public int getRetreatTime(Player player) {
		if (player == null) {
			return 0;
		}
		int curTime = 0;
		if (player.getRetreatGetAwardTime() == 0) {
			player.setRetreatGetAwardTime(System.currentTimeMillis());
		} else {
			curTime = (int)((System.currentTimeMillis() - player.getRetreatGetAwardTime()) / 1000) ;
			if (curTime > 12 * 3600) {
				curTime = 12 * 3600;
			} else {
				if (curTime < 0) {
					curTime = 0;
				}
			}
		}
		return curTime;
	}

	/**
	 * 获得闭关经验（单倍）
	 *
	 * @param player
	 * @param q_characterBean
	 * @param retreatTime
	 * @return
	 */
	public int getRetreatExp(Player player, Q_characterBean q_characterBean, int retreatTime) {
		if (retreatTime == 0) {
			return 0;
		}
		if (player == null) {
			return 0;
		}
		if (q_characterBean == null) {
			q_characterBean = DataManager.getInstance().q_characterContainer.getMap().get(player.getLevel());
			if (q_characterBean == null) {
				return 0;
			}
		}
		return q_characterBean.getQ_basis_exp() * (retreatTime / 60) / 3;
	}

	/**
	 * 获得闭关真气（单倍）
	 *
	 * @param player
	 * @param q_characterBean
	 * @param retreatTime
	 * @return
	 */
	public int getRetreatZhenqi(Player player, Q_characterBean q_characterBean, int retreatTime) {
		if (retreatTime == 0) {
			return 0;
		}
		if (player == null) {
			return 0;
		}
		if (q_characterBean == null) {
			q_characterBean = DataManager.getInstance().q_characterContainer.getMap().get(player.getLevel());
			if (q_characterBean == null) {
				return 0;
			}
		}
		return q_characterBean.getQ_basis_zhenqi() * (retreatTime / 60) / 6;
	}

	/**
	 * 发送闭关信息消息
	 *
	 * @param player 玩家
	 */
	public void sendRetreatInfoMessage(Player player) {
		if (player == null) {
			return;
		}
		Q_characterBean q_characterBean = DataManager.getInstance().q_characterContainer.getMap().get(player.getLevel());
		if (q_characterBean == null) {
			return;
		}
		int curTime = getRetreatTime(player);
		ResRetreatInfoMessage sendMessage = new ResRetreatInfoMessage();
		sendMessage.setNotifyType(0);
		sendMessage.setCurTime(curTime);
		sendMessage.setCurExp(getRetreatExp(player, q_characterBean, curTime));
		sendMessage.setCurZhenqi(getRetreatZhenqi(player, q_characterBean, curTime));
		MessageUtil.tell_player_message(player, sendMessage);
	}

	/**
	 * 登陆发送闭关信息
	 *
	 * @param player 玩家
	 */
	public void loginCheckRetreat(Player player) {
		sendRetreatInfoMessage(player);
	}

	/**
	 * 返回闭关信息
	 *
	 * @param player 玩家
	 * @param message 消息
	 */
	public void reqRetreatInfoMessageToServer(Player player, ReqRetreatInfoMessage message) {
		if (player == null) {
			log.error("reqRetreatInfoMessageToServer没有玩家");
			return;
		}
		sendRetreatInfoMessage(player);
	}

	/**
	 * 领取闭关奖励
	 *
	 * @param player 玩家
	 * @param message 消息
	 */
	public void reqGetRetreatAwardMessageToServer(Player player, ReqGetRetreatAwardMessage message) {
		if (player == null) {
			log.error("reqGetRetreatAwardMessageToServer没有玩家");
			return;
		}
		int retreatTime = getRetreatTime(player);
		int addexp = getRetreatExp(player, null, retreatTime);
		int addzhenqi = getRetreatZhenqi(player, null, retreatTime);
		if (addexp != 0 || addzhenqi != 0) {
			switch (message.getGetType()) {
				case 0: {
					int max = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.ZHENGQI_MAX.getValue()).getQ_int_value();
					if (player.getZhenqi() + addzhenqi > max) {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("提取真气后将超过上限，请消耗一些真气后再提取"));
						return;
					}
					PlayerManager.getInstance().addExp(player, addexp, AttributeChangeReason.BIGUAN);
					PlayerManager.getInstance().addZhenqi(player, addzhenqi,AttributeChangeReason.BIGUAN);
					player.setRetreatGetAwardTime(System.currentTimeMillis());
					sendRetreatInfoMessage(player);
					MessageUtil.notify_player(player, Notifys.SUCCESS, String.format(ResManager.getInstance().getString("领取闭关收益成功，获得经验%d，真气%d"), addexp, addzhenqi));
				}
				break;
				case 1: {
					int max = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.ZHENGQI_MAX.getValue()).getQ_int_value();
					if (player.getZhenqi() + addzhenqi * 2 > max) {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("提取真气后将超过上限，请消耗一些真气后再提取"));
						return;
					}
					if (BackpackManager.getInstance().getItemNum(player, retreatItemId) != 0) {
						if (BackpackManager.getInstance().removeItem(player, retreatItemId, 1, Reasons.OFFLINE_RETREATDELITEM, Config.getId())) {
							PlayerManager.getInstance().addExp(player, addexp * 2, AttributeChangeReason.BIGUAN);
							PlayerManager.getInstance().addZhenqi(player, addzhenqi * 2,AttributeChangeReason.BIGUAN);
							player.setRetreatGetAwardTime(System.currentTimeMillis());
							sendRetreatInfoMessage(player);
							MessageUtil.notify_player(player, Notifys.SUCCESS, String.format(ResManager.getInstance().getString("领取双倍闭关收益成功，获得经验%d，真气%d"), addexp * 2, addzhenqi * 2));
						} else {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的背包中闭关符没有删除成功，无法获得闭关奖励"));
						}
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的背包中没有闭关符"));
					}
				}
				break;
				case 2: {
					int max = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.ZHENGQI_MAX.getValue()).getQ_int_value();
					if (player.getZhenqi() + addzhenqi * 2 > max) {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("提取真气后将超过上限，请消耗一些真气后再提取"));
						return;
					}
					if (ManagerPool.shopManager.autoRemove(player, retreatItemId, 1, Reasons.OFFLINE_RETREATDELITEM, Config.getId())) {
						PlayerManager.getInstance().addExp(player, addexp * 2, AttributeChangeReason.BIGUAN);
						PlayerManager.getInstance().addZhenqi(player, addzhenqi * 2,AttributeChangeReason.BIGUAN);
						player.setRetreatGetAwardTime(System.currentTimeMillis());
						sendRetreatInfoMessage(player);
						MessageUtil.notify_player(player, Notifys.SUCCESS, String.format(ResManager.getInstance().getString("领取双倍闭关收益成功，获得经验%d，真气%d"), addexp * 2, addzhenqi * 2));
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，自动使用和购买闭关符不成功"));
					}
				}
				break;
			}
		} else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("暂时没有累积的经验与真气奖励，请稍后再来"));
		}
	}
}
