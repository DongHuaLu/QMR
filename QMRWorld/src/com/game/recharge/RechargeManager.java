package com.game.recharge;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.game.db.bean.GameUser;
import com.game.db.bean.Gold;
import com.game.db.bean.GoldRechargeLog;
import com.game.db.dao.GoldDao;
import com.game.db.dao.GoldRechargeLogDAO;
import com.game.db.dao.UserDao;
import com.game.dblog.LogService;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.recharge.message.RechargeMessage;
import com.game.utils.MessageUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;

public class RechargeManager {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RechargeManager.class);
	private static final Logger recharge = Logger.getLogger("RECHARGELOG");
	private static RechargeManager instance = new RechargeManager();
	private GoldDao dao = new GoldDao();
	private UserDao userDao = new UserDao();
	private GoldRechargeLogDAO rechargelog = new GoldRechargeLogDAO();

	public static RechargeManager getInstance() {
		return instance;
	}

	private RechargeManager() {
	}

	/**
	 * 元宝充值
	 *
	 * @param entry
	 * @return	1 找不到账户
	 */
	public int reacharge(RechargeEntry entry) {
		recharge.info(entry.toString());
		try {
			if (StringUtils.isBlank(entry.getOid())) {
				//参数错误 订单号不能为空
				return 1;
			}

			if (StringUtils.isBlank(entry.getGold()) || Integer.parseInt(entry.getGold()) == 0) {
				//参数错误 充值数不能为空或者为零
				return 2;
			}

			if (StringUtils.isBlank(entry.getSid())) {
				//参数错误 sid不能为空
				return 3;
			}

			if (StringUtils.isBlank(entry.getUid())) {
				//参数错误 uid不能为空
				return 4;
			}
			GoldRechargeLog selectById = rechargelog.selectById(entry.getOid());
			if (selectById != null) {
				//参数错误 订单号重复
				return 5;
			}
			int serverId = Integer.parseInt(entry.getSid());
			GameUser user = userDao.selectGameUser(entry.getUid(), serverId);
			if (user == null) {
				//参数错误 找不到账户
				return 6;
			}
			//List<Player> result = PlayerManager.getInstance().getPlayerByUserServerId(String.valueOf(user.getUserid()), serverId);
			List<Player> result = PlayerManager.getInstance().getPlayersByUser(String.valueOf(user.getUserid()));
			if (result!=null && result.size() > 1) {
				logger.error("userId=" + user.getUserid() + ",serverId" + serverId + "出现了同一个服同一个账号登录两个角色的情况,忽略充值请求" + entry.toString(), new Exception());
				return -1;
			}

			//订单号验证
			Gold gold = dao.select(String.valueOf(user.getUserid()), serverId);
			int rechargeNum = Integer.parseInt(entry.getGold());
			if (gold == null) {
				gold = new Gold();
				gold.setUserId(String.valueOf(user.getUserid()));
				gold.setServerId(serverId);
				gold.setGold(rechargeNum);
				gold.setTotalGold(rechargeNum > 0 ? rechargeNum : 0);
				gold.setCostGold(0);
				gold.setBuyitemresume(0);
				gold.setFaildrollBackadd(0);
				gold.setGettempybadd(0);
				gold.setHuokuanAdd(0);
				gold.setJiaoyiresume(0);
				gold.setJiaoyiybadd(0);
				gold.setShangjiaresume(0);
				gold.setTwgmadd(0);
				gold.setYbxiajiaadd(0);
//				gold.setIsinner(0);
				dao.insert(gold);
			} else {
				Gold savegold = new Gold();
				savegold.setGold(rechargeNum);
				savegold.setTotalGold(rechargeNum > 0 ? rechargeNum : 0);
				savegold.setUserId(String.valueOf(user.getUserid()));
				savegold.setServerId(serverId);
//				gold.setGold(rechargeNum);
//				gold.setTotalGold(rechargeNum>0?rechargeNum:0);
				dao.update(savegold);
			}

			GoldRechargeLog rechargeLog = new GoldRechargeLog();
			rechargeLog.setGold(rechargeNum);
			rechargeLog.setOid(entry.getOid());
			rechargeLog.setServerid(entry.getSid());
			rechargeLog.setTime(System.currentTimeMillis());
			rechargeLog.setType(Integer.parseInt(entry.getType()));
			rechargeLog.setUid(entry.getUid());
			rechargeLog.setUserid(user.getUserid());
			rechargeLog.setRmb(entry.getRMB());
			rechargeLog.setRechargeContent(entry.toString());
			rechargelog.insert(rechargeLog);

			//添加gamelog库日志
			RechargelogLog logLog = new RechargelogLog(rechargeLog);
			LogService.getInstance().execute(logLog);

			if (result!=null && result.size() > 0) {   // 通知到Server
				for (Player player : result) {
					RechargeMessage msg = new RechargeMessage();
					msg.setPlayerId(player.getId());
					msg.setRechargeParam(rechargeNum);
					msg.setOid(entry.getOid());
					MessageUtil.send_to_game(player, msg);
					if (player.getRechargeHistorys() != null) {
						RechargeHistory rechargeHistory = new RechargeHistory();
						rechargeHistory.setGold(rechargeLog.getGold());
						rechargeHistory.setTime(rechargeLog.getTime());
						player.getRechargeHistorys().add(rechargeHistory);
					}
				}
			}
		} catch (Exception e) {
			logger.error("充值出现异常\t" + entry.toString(), e);
			return -2;
		}
		return 0;
	}

	public void queryRechargeHistory(Player player) {
		if (player != null && player.getRechargeHistorys() == null) {
			player.setRechargeHistorys(new ArrayList<RechargeHistory>());
			try {
				List<GoldRechargeLog> loglist = rechargelog.selectByUserid(Long.valueOf(player.getUserId()));
				Iterator<GoldRechargeLog> iterator = loglist.iterator();
				while (iterator.hasNext()) {
					GoldRechargeLog goldRechargeLog = iterator.next();
					if (goldRechargeLog != null) {
						RechargeHistory rechargeHistory = new RechargeHistory();
						rechargeHistory.setGold(goldRechargeLog.getGold());
						rechargeHistory.setTime(goldRechargeLog.getTime());
						player.getRechargeHistorys().add(rechargeHistory);
					}
				}
			} catch (SQLException ex) {
				java.util.logging.Logger.getLogger(RechargeManager.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public void queryRechargeHistory(long playerid) {
		Player player = PlayerManager.getInstance().getPlayer(playerid);
		if (player != null && player.getRechargeHistorys() == null) {
			queryRechargeHistory(player);
		}
	}
}
