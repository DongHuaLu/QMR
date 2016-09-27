package com.game.player.timer;

import com.game.languageres.manager.ResManager;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.game.login.message.ResPlayerNonageToGameMessage;
import com.game.manager.ManagerPool;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.User;
import com.game.timer.TimerEvent;
import com.game.utils.MessageUtil;

public class UserOnlineTimer extends TimerEvent {
	/**
	 * Logger for this class
	 */
	protected static final Logger logger = Logger.getLogger(UserOnlineTimer.class);
	
	private static final int MINUTE = 60 * 1000;
	
	private static final int HOUR = 60 * 60 * 1000;

	public UserOnlineTimer() {
		super(-1, 5000);
	}

	@Override
	public void action() {
		ConcurrentHashMap<String, User> users = PlayerManager.getUserTimes();
		synchronized (users) {
			//迭代器
			Iterator<User> iter = users.values().iterator();
			while (iter.hasNext()) {
				User user = (User) iter.next();
				List<Player> players = ManagerPool.playerManager.getPlayersByUser(user.getUserId());
				
				if(user.getState()==2) continue;
				if(players==null || players.size()==0) continue;
				
				//logger.error(user.getUserId() + "在线中！");
				long old = user.getOnline();
				user.setOnline(user.getOnline() + 5000);
				long now = user.getOnline();
				
				user.setLasttime(System.currentTimeMillis());
				
				if(old <= 1 * HOUR && now > 1 * HOUR){
					//第1次1小时提醒
					MessageUtil.nonage_user(user, ResManager.getInstance().getString("您累计在线时间已满1小时"));
				}else if(old <= 2 * HOUR && now > 2 * HOUR){
					//第2次2小时提醒
					MessageUtil.nonage_user(user, ResManager.getInstance().getString("您累计在线时间已满2小时"));
				}else if(old <= 3 * HOUR && now > 3 * HOUR){
					//变更防沉迷状态
					for (int i = 0; i < players.size(); i++) {
						Player player = players.get(i);
						ResPlayerNonageToGameMessage nonagemsg = new ResPlayerNonageToGameMessage();
						nonagemsg.setPlayerId(player.getId());
						nonagemsg.setNonage(ManagerPool.playerManager.getUserNonage(user));
						MessageUtil.send_to_game(player, nonagemsg);
					}
					//第3次3小时提醒
					MessageUtil.nonage_user(user, ResManager.getInstance().getString("您累计在线时间已满3小时，请您下线休息，做适当身体活动"));
				}else if(old <= 3 * HOUR + 30 * MINUTE && now > 3 * HOUR + 30 * MINUTE){
					//第4次3小时30分提醒
					MessageUtil.nonage_user(user, ResManager.getInstance().getString("您已经进入疲劳游戏时间，您的游戏收益将降为正常值的50％，为了您的健康，请尽快下线休息，做适当身体活动，合理安排学习生活"));
				}else if(old <= 4 * HOUR && now > 4 * HOUR){
					//第5次4小时提醒
					MessageUtil.nonage_user(user, ResManager.getInstance().getString("您已经进入疲劳游戏时间，您的游戏收益将降为正常值的50％，为了您的健康，请尽快下线休息，做适当身体活动，合理安排学习生活"));
				}else if(old <= 4 * HOUR + 30 * MINUTE && now > 4 * HOUR + 30 * MINUTE){
					//第6次4小时30分提醒
					MessageUtil.nonage_user(user, ResManager.getInstance().getString("您已经进入疲劳游戏时间，您的游戏收益将降为正常值的50％，为了您的健康，请尽快下线休息，做适当身体活动，合理安排学习生活"));
				}else if(now > 5 * HOUR){
					old = old - 5 * HOUR;
					now = now - 5 * HOUR;
					int k = (int)(now / (15 * MINUTE));
					if(old <= k * 15 * MINUTE && now > k * 15 * MINUTE){
						if(k==0){
							//变更防沉迷状态
							for (int i = 0; i < players.size(); i++) {
								Player player = players.get(i);
								ResPlayerNonageToGameMessage nonagemsg = new ResPlayerNonageToGameMessage();
								nonagemsg.setPlayerId(player.getId());
								nonagemsg.setNonage(ManagerPool.playerManager.getUserNonage(user));
								MessageUtil.send_to_game(player, nonagemsg);
							}
						}
						//超过5小时后每15分钟提醒一次
						MessageUtil.nonage_user(user, "您已进入不健康游戏时间，为了您的健康，请您立即下线休息。如不下线，您的身体将受到损害，您的收益已降为零，直到您的累计下线时间满5小时后，才能恢复正常");
					}
				}
			}
		}
	}

}
