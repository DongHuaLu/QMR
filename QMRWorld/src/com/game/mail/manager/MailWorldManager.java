package com.game.mail.manager;

import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.db.bean.Q_mailBean;
import com.game.db.dao.Q_mailDao;
import com.game.dblog.LogService;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.mail.log.MailLog;
import com.game.mail.message.ReqMailSendSystemMailToWorldMessage;
import com.game.mail.message.ResMailSendSystemMailToServerMessage;
import com.game.mail.structs.MailDetailInfoData;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerWorldInfo;
import com.game.utils.MessageUtil;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author 杨鸿岚 邮件管理器类（游戏服务器）
 */
public class MailWorldManager {

	private Logger log = Logger.getLogger(MailWorldManager.class);
	private static Object obj = new Object();
	//帮会管理类实例
	private static MailWorldManager manager;

	private MailWorldManager() {
	}

	public static MailWorldManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new MailWorldManager();
			}
		}
		return manager;
	}
	//错误代码
	private byte Error_Fail = -1;		//失败
	private byte Error_Success = 0;		//成功
	//数据接口
	private Q_mailDao q_mailDao = new Q_mailDao();	//邮件数据接口

	public Q_mailDao getQ_mailDao() {
		return q_mailDao;
	}

	public void setQ_mailDao(Q_mailDao q_mailDao) {
		this.q_mailDao = q_mailDao;
	}

	public Q_mailBean getMailBean(MailDetailInfoData mailDetailInfoData) {
		Q_mailBean q_mailBean = new Q_mailBean();
		q_mailBean.setMail_id(mailDetailInfoData.getMailid());
		q_mailBean.setSend_name(mailDetailInfoData.getSzSenderName());
		q_mailBean.setReceiver_id(mailDetailInfoData.getReceiverid());
		q_mailBean.setReceiver_name(mailDetailInfoData.getSzReceiverName());
		q_mailBean.setSend_time(mailDetailInfoData.getNSendTime());
		q_mailBean.setBtAccessory(mailDetailInfoData.getBtAccessory());
		q_mailBean.setBtRead(mailDetailInfoData.getBtRead());
		q_mailBean.setBtReturn(mailDetailInfoData.getBtReturn());
		q_mailBean.setBtSystem(mailDetailInfoData.getBtSystem());
		q_mailBean.setMail_data(JSONserializable.toString(mailDetailInfoData));
		return q_mailBean;
	}

	public boolean saveMail(MailDetailInfoData mailDetailInfoData) {
		Q_mailBean q_mailBean = getMailBean(mailDetailInfoData);
		if (q_mailBean != null) {
			if (getQ_mailDao().insert(q_mailBean) == 0) {
				log.error(String.format("邮件数据保存错误，邮件id[%s]", Long.toString(mailDetailInfoData.getMailid())));
				return false;
			}
			return true;
		}
		return false;
	}

	public void reqMailSendSystemMailToWorld(ReqMailSendSystemMailToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerid());
		if (player != null) {
			if (message.getBtErrorCode() == Error_Fail) {
			}
		}
	}

	/**
	 * 发送系统邮件
	 *
	 * @param receivername 接收者玩家名字
	 * @param title 邮件标题
	 * @param notice 邮件内容
	 * @param goldtype 金币类型 1 金币 2 元宝
	 * @param gold 金币数量
	 * @param items 要发送的物品列表
	 * @return long 玩家id 1成功 -1 失败 -2 发送到游戏服务器
	 */
	public long sendSystemMail(String receivername, String title, String notice, byte goldtype, int gold, List<Item> items) {
		long receiverid = -1;
		PlayerWorldInfo playerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(receivername);
		if (playerWorldInfo != null) {
			boolean boSend = true;
			MailDetailInfoData mailDetailInfoData = new MailDetailInfoData();
			mailDetailInfoData.setBtSystem((byte) 1);
			mailDetailInfoData.setSenderid(0);
			mailDetailInfoData.setSzSenderName(ResManager.getInstance().getString("系统邮件"));
			mailDetailInfoData.setReceiverid(playerWorldInfo.getId());
			mailDetailInfoData.setSzReceiverName(receivername);
			mailDetailInfoData.setSzTitle(title);
			mailDetailInfoData.setSzNotice(notice);
			mailDetailInfoData.setBtGoldType(goldtype);
			mailDetailInfoData.setNGold(gold);
			mailDetailInfoData.setNSendTime((int) (System.currentTimeMillis() / 1000));
			mailDetailInfoData.setBtReturn((byte) 0);
			mailDetailInfoData.setBtRead((byte) 0);
			if (boSend) {
				if (items != null) {
					for (int i = 0; i < items.size(); i++) {
						Item item = items.get(i);
						if (item != null && !item.isTrade() /*
							 * && !item.isBind()
							 */ && !item.isLost()) {
							mailDetailInfoData.getItemdata().add(item);
						} else {
							boSend = false;
							break;
						}
					}
				}
			}
			if (boSend) {
				mailDetailInfoData.calbtAccessory();
				mailDetailInfoData.setMailid(Config.getId());
				Player player = PlayerManager.getInstance().getOnlinePlayerByName(receivername);
				if (player != null) {
					ResMailSendSystemMailToServerMessage sendMessage = new ResMailSendSystemMailToServerMessage();
					sendMessage.setMailJsonstr(JSONserializable.toString(mailDetailInfoData));
					MessageUtil.send_to_game(player, sendMessage);
					receiverid = -2;
				} else {
					if (saveMail(mailDetailInfoData)) {
						receiverid = playerWorldInfo.getId();
						//玩家不在线也记录邮件发送日志
						setMailLog(1, playerWorldInfo, mailDetailInfoData);
					}
				}
			}
		}
		return receiverid;
	}
	
	//记录邮件日志
	public void setMailLog(int logtype, PlayerWorldInfo playerworldinfo, MailDetailInfoData mailDetailInfoData) {
		MailLog log = new MailLog();
		log.setLogtype(logtype);
		log.setAccount(playerworldinfo.getAccount());
		log.setContent(mailDetailInfoData.getSzNotice());
		log.setEventid(mailDetailInfoData.getMailid());
		log.setEventtime(System.currentTimeMillis());
		log.setGoldtype(mailDetailInfoData.getBtGoldType());
		log.setItemdata(JSONserializable.toString(mailDetailInfoData));
		log.setMoney(mailDetailInfoData.getNGold());
		log.setRoleid(playerworldinfo.getId());
		log.setRolelevel(playerworldinfo.getLevel());
		log.setRolename(playerworldinfo.getName());
		log.setSendername(mailDetailInfoData.getSzSenderName());
		log.setSendtime(mailDetailInfoData.getNSendTime());
		log.setTitle(mailDetailInfoData.getSzTitle());
		log.setSid(-1); //TODO 唐超修改 player为空时服务器为-1
		LogService.getInstance().execute(log);
	}
	
	//记录邮件日志
	public void setMailLog(int logtype, Player player, MailDetailInfoData mailDetailInfoData) {
		MailLog log = new MailLog();
		log.setLogtype(logtype);
		log.setAccount((player != null) ? player.getUserId() : "system");
		log.setContent(mailDetailInfoData.getSzNotice());
		log.setEventid(mailDetailInfoData.getMailid());
		log.setEventtime(System.currentTimeMillis());
		log.setGoldtype(mailDetailInfoData.getBtGoldType());
		log.setItemdata(JSONserializable.toString(mailDetailInfoData));
		log.setMoney(mailDetailInfoData.getNGold());
		log.setRoleid((player != null) ? player.getId() : 0);
		log.setRolelevel((player != null) ? player.getLevel() : 0);
		log.setRolename((player != null) ? player.getName() : "system");
		log.setSendername(mailDetailInfoData.getSzSenderName());
		log.setSendtime(mailDetailInfoData.getNSendTime());
		log.setTitle(mailDetailInfoData.getSzTitle());
		log.setSid((player != null) ? player.getCreateServer():-1); //TODO 唐超修改 player为空时服务器为-1
		LogService.getInstance().execute(log);
	}
}
