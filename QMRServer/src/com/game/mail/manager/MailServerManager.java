package com.game.mail.manager;

import com.game.arrow.manager.ArrowManager;
import com.game.arrow.structs.ArrowReasonsType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.game.backpack.bean.ItemInfo;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.db.bean.Gold;
import com.game.db.bean.Q_mailBean;
import com.game.db.dao.Q_mailDao;
import com.game.dblog.LogService;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.mail.bean.MailDetailInfo;
import com.game.mail.bean.MailSummaryInfo;
import com.game.mail.bean.MailSummaryItem;
import com.game.mail.log.MailLog;
import com.game.mail.message.ReqMailDeleteMailToServerMessage;
import com.game.mail.message.ReqMailGetItemToServerMessage;
import com.game.mail.message.ReqMailListGetItemToServerMessage;
import com.game.mail.message.ReqMailQueryDetailToServerMessage;
import com.game.mail.message.ReqMailQueryListToServerMessage;
import com.game.mail.message.ReqMailQueryUserToServerMessage;
import com.game.mail.message.ReqMailReturnToServerMessage;
import com.game.mail.message.ReqMailSendNewMailToServerMessage;
import com.game.mail.message.ReqMailSendSystemMailToWorldMessage;
import com.game.mail.message.ResMailGetItemToClientMessage;
import com.game.mail.message.ResMailGetNewMailToClientMessage;
import com.game.mail.message.ResMailQueryDetailToClientMessage;
import com.game.mail.message.ResMailQueryListToClientMessage;
import com.game.mail.message.ResMailQueryUserToClientMessage;
import com.game.mail.message.ResMailReturnToClientMessage;
import com.game.mail.message.ResMailSendNewMailToClientMessage;
import com.game.mail.message.ResMailSendSystemMailToServerMessage;
import com.game.mail.structs.MailDetailInfoData;
import com.game.manager.ManagerPool;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.AttributeChangeReason;
import com.game.prompt.structs.Notifys;
import com.game.rank.manager.RankManager;
import com.game.rank.structs.RankType;
import com.game.server.impl.WServer;
import com.game.server.thread.SaveMailThread;
import com.game.structs.Reasons;
import com.game.utils.CommonConfig;
import com.game.utils.Global;
import com.game.utils.MessageUtil;

/**
 *
 * @author 杨鸿岚 邮件管理器类（游戏服务器）
 */
public class MailServerManager {

	private Logger log = Logger.getLogger(MailServerManager.class);
	private static Object obj = new Object();
	//帮会管理类实例
	private static MailServerManager manager;

	private MailServerManager() {
	}

	public static MailServerManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new MailServerManager();
			}
		}
		return manager;
	}
	//错误代码
	private byte Error_Fail = -1;		//失败
	private byte Error_Success = 0;		//成功
	//日志类型
	private int LogType_Send = 1;		//发送
	private int LogType_Rcv = 2;		//接收
	private int LogType_Del = 3;		//删除
	//邮件删除时间
	private static int deletetime = 30 * 24 * 3600;//30天
	//数据接口
	private Q_mailDao q_mailDao = new Q_mailDao();	//邮件数据接口

	public Q_mailDao getQ_mailDao() {
		return q_mailDao;
	}

	public void setQ_mailDao(Q_mailDao q_mailDao) {
		this.q_mailDao = q_mailDao;
	}

	//--------------------------成员函数------------------------//
	private int getdeletesendtime() {
		return ((int) System.currentTimeMillis() / 1000) - deletetime;
	}

	public void startClearMail() {
		List<Q_mailBean> mailBeans = MailServerManager.getInstance().getQ_mailDao().selectbysendtime(getdeletesendtime());
		if (!mailBeans.isEmpty()) {
			for (int i = 0; i < mailBeans.size(); i++) {
				Q_mailBean q_mailBean = mailBeans.get(i);
				if (q_mailBean != null) {
					MailServerManager.getInstance().deleteMail(q_mailBean.getMail_id());
				}
			}
		}
	}

	public void loginLoadMail(Player player) {
		loadMailToMap(player);
	}

	public void loadMailToMap(Player player) {
		if (player != null) {
			List<MailDetailInfoData> listdatas = loadMailByUserid(player.getId());
			if (listdatas != null && !listdatas.isEmpty()) {
				for (int i = 0; i < listdatas.size(); i++) {
					MailDetailInfoData mailDetailInfoData = listdatas.get(i);
					if (mailDetailInfoData != null) {
						if (mailDetailInfoData.getNsendTime() <= getdeletesendtime()) {
							WServer.getInstance().getwSaveMailThread().dealMail(getMailBean(mailDetailInfoData), SaveMailThread.MAIL_DELETE);
						} else {
							player.getMaildataMap().put(mailDetailInfoData.getMailid(), mailDetailInfoData);
						}
					}
				}
			}
		}
	}

	public MailDetailInfoData loadmail(long mailid) {
		Q_mailBean q_mailBean = getQ_mailDao().selectbymailid(mailid);
		if (q_mailBean != null) {
			if (q_mailBean.getMail_id() == mailid) {
				MailDetailInfoData mailDetailInfoData = (MailDetailInfoData) JSONserializable.toObject(q_mailBean.getMail_data(), MailDetailInfoData.class);
				if (mailDetailInfoData != null) {
					mailDetailInfoData.setSaveType(SaveMailThread.MAIL_UPDATE);
					return mailDetailInfoData;
				}
			}
		}
		return null;
	}

	public List<MailDetailInfoData> loadMailByUserid(long userid) {
		List<MailDetailInfoData> listdatas = new ArrayList<MailDetailInfoData>();
		List<Q_mailBean> list = null;
		list = getQ_mailDao().selectbyuserid(userid);
		if (list != null) {
			Iterator<Q_mailBean> iter = list.iterator();
			while (iter.hasNext()) {
				Q_mailBean q_mailBean = (Q_mailBean) iter.next();
				if (q_mailBean != null) {
					MailDetailInfoData mailDetailInfoData = (MailDetailInfoData) JSONserializable.toObject(q_mailBean.getMail_data(), MailDetailInfoData.class);
					if (mailDetailInfoData != null) {
						mailDetailInfoData.setSaveType(SaveMailThread.MAIL_UPDATE);
						listdatas.add(mailDetailInfoData);
					}
				}
			}
		}
		return listdatas;
	}

	public boolean saveMailToMap(Player player, MailDetailInfoData mailDetailInfoData) {
		if (saveMail(mailDetailInfoData)) {
			if (player.getMaildataMap().containsKey(mailDetailInfoData.getMailid())) {
				player.getMaildataMap().remove(mailDetailInfoData.getMailid());
				player.getMaildataMap().put(mailDetailInfoData.getMailid(), mailDetailInfoData);
			} else {
				player.getMaildataMap().put(mailDetailInfoData.getMailid(), mailDetailInfoData);
			}
			return true;
		}
		return false;
	}

	public boolean saveMail(MailDetailInfoData mailDetailInfoData) {
		Q_mailBean q_mailBean = getMailBean(mailDetailInfoData);
		if (q_mailBean != null) {
			log.error("saveMail "+mailDetailInfoData.getMailid());
			WServer.getInstance().getwSaveMailThread().dealMail(q_mailBean, mailDetailInfoData.getSaveType());
//			if (getQ_mailDao().update(q_mailBean) == 0) {
//				if (getQ_mailDao().insert(q_mailBean) == 0) {
//					log.error(String.format("邮件数据保存错误，邮件id[%s]", Long.toString(mailDetailInfoData.getMailid())));
//					return false;
//				}
//			}
			return true;
		}
		return false;
	}

	public boolean deleteMail(MailDetailInfoData mailDetailInfoData) {
		Q_mailBean q_mailBean = getMailBean(mailDetailInfoData);
		if (q_mailBean != null) {
			WServer.getInstance().getwSaveMailThread().dealMail(q_mailBean, SaveMailThread.MAIL_DELETE);
			return true;
		}
		return false;
	}

	public boolean deleteMailByUserid(MailDetailInfoData mailDetailInfoData) {
		Q_mailBean q_mailBean = getMailBean(mailDetailInfoData);
		if (q_mailBean != null) {
			WServer.getInstance().getwSaveMailThread().dealMail(q_mailBean, SaveMailThread.MAIL_DELETEALLBYID);
			return true;
		}
		return false;
	}

	public boolean deleteMail(long mailid) {
		if (getQ_mailDao().delete(mailid) == 0) {
			log.error(String.format("邮件数据删除错误，邮件id[%s]", Long.toString(mailid)));
			return false;
		}
		return true;
	}
//
//	public boolean deleteMailByUserid(long userid) {
//		if (getQ_mailDao().deletebyuserid(userid) == 0) {
//			log.error(String.format("邮件数据删除错误，玩家id[%s]", Long.toString(userid)));
//			return false;
//		}
//		return true;
//	}

	public MailSummaryInfo getMailSummaryInfo(MailDetailInfoData mailDetailInfoData) {
		MailSummaryInfo mailSummaryInfo = new MailSummaryInfo();
		mailSummaryInfo.setMailid(mailDetailInfoData.getMailid());
		mailSummaryInfo.setSzSenderName(mailDetailInfoData.getSzSenderName());
		mailSummaryInfo.setSzTitle(mailDetailInfoData.getSzTitle());
		mailSummaryInfo.setBtAccessory(mailDetailInfoData.getBtAccessory());
		mailSummaryInfo.setBtRead(mailDetailInfoData.getBtRead());
		mailSummaryInfo.setBtSystem(mailDetailInfoData.getBtSystem());
		mailSummaryInfo.setNSendTime(mailDetailInfoData.getNsendTime());
		for (int i = 0; i < mailDetailInfoData.getItemdata().size(); i++) {
			Item item = mailDetailInfoData.getItemdata().get(i);
			if (item != null) {
				MailSummaryItem mailSummaryItem = new MailSummaryItem();
				mailSummaryItem.setItemid(item.getItemModelId());
				mailSummaryItem.setItemnum(item.getNum());
				mailSummaryInfo.getSummaryitemlist().add(mailSummaryItem);
			}
		}
		return mailSummaryInfo;
	}

	public MailDetailInfo getMailDetailInfo(MailDetailInfoData mailDetailInfoData) {
		MailDetailInfo mailDetailInfo = new MailDetailInfo();
		mailDetailInfo.setMailid(mailDetailInfoData.getMailid());
		mailDetailInfo.setSenderid(mailDetailInfoData.getSenderid());
		mailDetailInfo.setReceiverid(mailDetailInfoData.getReceiverid());
		mailDetailInfo.setSzSenderName(mailDetailInfoData.getSzSenderName());
		mailDetailInfo.setSzReceiverName(mailDetailInfoData.getSzReceiverName());
		mailDetailInfo.setSzTitle(mailDetailInfoData.getSzTitle());
		mailDetailInfo.setSzNotice(mailDetailInfoData.getSzNotice());
		mailDetailInfo.setBtRead(mailDetailInfoData.getBtRead());
		mailDetailInfo.setBtGoldType(mailDetailInfoData.getBtGoldType());
		mailDetailInfo.setNGold(mailDetailInfoData.getNgold());
		mailDetailInfo.setBtAccessory(mailDetailInfoData.getBtAccessory());
		mailDetailInfo.setNSendTime(mailDetailInfoData.getNsendTime());
		mailDetailInfo.setBtSystem(mailDetailInfoData.getBtSystem());
		mailDetailInfo.setBtReturn(mailDetailInfoData.getBtReturn());
		for (int i = 0; i < mailDetailInfoData.getItemdata().size(); i++) {
			Item item = mailDetailInfoData.getItemdata().get(i);
			if (item != null) {
				ItemInfo itemInfo = item.buildItemInfo();
				if (itemInfo != null) {
					mailDetailInfo.getItemlist().add(itemInfo);
				}
			}
		}
		return mailDetailInfo;
	}

	public Q_mailBean getMailBean(MailDetailInfoData mailDetailInfoData) {
		Q_mailBean q_mailBean = new Q_mailBean();
		q_mailBean.setMail_id(mailDetailInfoData.getMailid());
		q_mailBean.setSend_name(mailDetailInfoData.getSzSenderName());
		q_mailBean.setReceiver_id(mailDetailInfoData.getReceiverid());
		q_mailBean.setReceiver_name(mailDetailInfoData.getSzReceiverName());
		q_mailBean.setSend_time(mailDetailInfoData.getNsendTime());
		q_mailBean.setBtAccessory(mailDetailInfoData.getBtAccessory());
		q_mailBean.setBtRead(mailDetailInfoData.getBtRead());
		q_mailBean.setBtReturn(mailDetailInfoData.getBtReturn());
		q_mailBean.setBtSystem(mailDetailInfoData.getBtSystem());
		q_mailBean.setMail_data(JSONserializable.toString(mailDetailInfoData));
		return q_mailBean;
	}

	//--------------------------消息函数------------------------//
	public void reqMailQueryListToServer(Player player, ReqMailQueryListToServerMessage message) {
		ResMailQueryListToClientMessage sendMessage = new ResMailQueryListToClientMessage();
		sendMessage.setBtErrorCode(Error_Fail);
		if (!player.getMaildataMap().isEmpty()) {
			for (Map.Entry<Long, MailDetailInfoData> entry : player.getMaildataMap().entrySet()) {
				MailDetailInfoData mailDetailInfoData = entry.getValue();
				if (mailDetailInfoData != null) {
					sendMessage.getMailSummaryList().add(getMailSummaryInfo(mailDetailInfoData));
				}
			}
		}

//		List<MailDetailInfoData> listdatas = loadMailByUserid(player.getId());
//		if (!listdatas.isEmpty()) {
//			for (int i = 0; i < listdatas.size(); i++) {
//				MailDetailInfoData mailDetailInfoData = listdatas.get(i);
//				if (mailDetailInfoData != null) {
//					sendMessage.getMailSummaryList().add(getMailSummaryInfo(mailDetailInfoData));
//				}
//			}
//		}
		sendMessage.setBtErrorCode(Error_Success);
		MessageUtil.tell_player_message(player, sendMessage);
	}

	public void reqMailQueryDetailToServer(Player player, ReqMailQueryDetailToServerMessage message) {
		ResMailQueryDetailToClientMessage sendMessage = new ResMailQueryDetailToClientMessage();
		sendMessage.setBtErrorCode(Error_Fail);
//		MailDetailInfoData mailDetailInfoData = loadmail(message.getMailid());
		MailDetailInfoData mailDetailInfoData = player.getMaildataMap().get(message.getMailid());
		if (mailDetailInfoData != null) {
			mailDetailInfoData.setSaveType(SaveMailThread.MAIL_UPDATE);
			mailDetailInfoData.setBtRead((byte) 1);
			if (saveMail(mailDetailInfoData)) {
				sendMessage.setMailDetail(getMailDetailInfo(mailDetailInfoData));
				sendMessage.setBtErrorCode(Error_Success);
			} else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("邮件读取失败"));
			}
		} else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("邮件读取失败"));
		}
		MessageUtil.tell_player_message(player, sendMessage);
	}

	public void reqMailGetItemToServer(Player player, ReqMailGetItemToServerMessage message, boolean borefresh) {
		ResMailGetItemToClientMessage sendMessage = new ResMailGetItemToClientMessage();
		sendMessage.setBtErrorCode(Error_Fail);
		sendMessage.setItemid(message.getItemid());
		//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
		MailDetailInfoData mailDetailInfoData = player.getMaildataMap().get(message.getMailid());
//		MailDetailInfoData mailDetailInfoData = loadmail(message.getMailid());
		if (mailDetailInfoData != null) {
			mailDetailInfoData.setSaveType(SaveMailThread.MAIL_UPDATE);
			long action = Config.getId();
			int ngold = mailDetailInfoData.getNgold();
			int getgold = 0;
			int recharge = 0;
			Gold goldinfo = player.getGold();
			if (goldinfo != null) {
				getgold = goldinfo.getGold();
				recharge = goldinfo.getTotalGold();
			}
			List<Item> items = new ArrayList<Item>();
			if (message.getItemid() == -1) {
				int itemNum = 0;
				for (int i = 0; i < mailDetailInfoData.getItemdata().size(); i++) {
					Item item = mailDetailInfoData.getItemdata().get(i);
					if (item != null) {
						if (item.getItemModelId() > 0) {
							itemNum++;
						} else {
							if (item.getItemModelId() == -1) {
								int money = player.getMoney() + item.getNum();
								if (money < 0 || money > Global.BAG_MAX_COPPER) {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取铜币失败"));
									return;
								}
							} else if (item.getItemModelId() == -2) {
								if (getgold > 0) {
									int gold = getgold + item.getNum();
									if (gold < 0 || gold > Global.BAG_MAX_GOLD) {
										MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取元宝失败"));
										return;
									}
								} else {
									int gold = player.getCanreceiveyuanbao() + item.getNum();
									if (gold < 0 || gold > Global.BAG_MAX_GOLD) {
										MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取元宝失败"));
										return;
									}
								}
							} else if (item.getItemModelId() == -3) {
								int max = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.ZHENGQI_MAX.getValue()).getQ_int_value();
								int zhenqi = player.getZhenqi() + item.getNum();
								if (zhenqi < 0 || zhenqi > max) {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取真气失败"));
									return;
								}
							} else if (item.getItemModelId() == -4) {
								long exp = player.getExp() + item.getNum();
								if (exp < 0) {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取经验失败"));
									return;
								}
							} else if (item.getItemModelId() == -5) {
								int bindgold = player.getBindGold() + item.getNum();
								if (bindgold < 0 || bindgold > Global.BAG_MAX_GOLD) {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取礼金失败"));
									return;
								}
							} else if (item.getItemModelId() == -6) {
								int fightspirit = ArrowManager.getInstance().getFightSpiritNum(player, ArrowManager.FightType_RI) + item.getNum();
								if (fightspirit < 0 || fightspirit > 2100000000) {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取七曜战魂失败"));
									return;
								}
							} else if (item.getItemModelId() == -7) {
								int rank = player.getRankexp() + item.getNum();
								if (rank < 0 || rank > 2100000000) {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取军功失败"));
									return;
								}
							}
						}
					}
				}
				if (BackpackManager.getInstance().getEmptyGridNum(player) < itemNum) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取附件物品时，玩家包裹剩余格子数不足"));
					return;
				}
				if (mailDetailInfoData.getNgold() != 0) {
					if (mailDetailInfoData.getBtGoldType() == 1) {//金币
						int money = player.getMoney() + mailDetailInfoData.getNgold();
						if (money < 0 || money > Global.BAG_MAX_COPPER) {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取铜币失败"));
							return;
						}
					} else if (mailDetailInfoData.getBtGoldType() == 2) {//元宝
						if (getgold > 0) {
							int gold = getgold + mailDetailInfoData.getNgold();
							if (gold < 0 || gold > Global.BAG_MAX_GOLD) {
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取元宝失败"));
								return;
							}
						} else {
							int gold = player.getCanreceiveyuanbao() + mailDetailInfoData.getNgold();
							if (gold < 0 || gold > Global.BAG_MAX_GOLD) {
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取元宝失败"));
								return;
							}
						}
					}
				}
				String str = "";
				for (int i = 0; i < mailDetailInfoData.getItemdata().size(); i++) {
					Item item = mailDetailInfoData.getItemdata().get(i);
					if (item != null) {
						if (item.getItemModelId() > 0) {
							BackpackManager.getInstance().addItem(player, item, Reasons.MAILSHOUQUITEM, action);
						} else {
							if (item.getItemModelId() == -1) {
								BackpackManager.getInstance().changeMoney(player, item.getNum(), Reasons.MAILSHOUQUMONEY, action);
							} else if (item.getItemModelId() == -2) {
								if (getgold > 0 && recharge > 0) {
									BackpackManager.getInstance().addGold(player, item.getNum(), Reasons.MAILSHOUQUGOLD, action);
								} else {
									BackpackManager.getInstance().changeTmpGold(player, item.getNum());
								}
							} else if (item.getItemModelId() == -3) {
								PlayerManager.getInstance().addZhenqi(player, item.getNum(), AttributeChangeReason.MAIL);
							} else if (item.getItemModelId() == -4) {
								PlayerManager.getInstance().addExp(player, item.getNum(), AttributeChangeReason.MAIL);
							} else if (item.getItemModelId() == -5) {
								BackpackManager.getInstance().changeBindGold(player, item.getNum(), Reasons.MAILSHOUQUBINDGOLD, action);
							} else if (item.getItemModelId() == -6) {
								ArrowManager.getInstance().addFightSpiritNum(player, ArrowManager.FightType_RI, item.getNum(), true, ArrowReasonsType.MAIL);
							} else if (item.getItemModelId() == -7) {
								RankManager.getInstance().addranknum(player, item.getNum(), RankType.MAIL);
							}
						}
						String name=ManagerPool.backpackManager.getName(item.getItemModelId());
						if (i == 0) {
							str = name+"("+ item.getNum()+")";
						}else {
							str = str+"，"+name+"("+ item.getNum()+")";
						}
						items.add(item);
					}
				}
				MessageUtil.notify_player(player, Notifys.CHAT_ROLE, ResManager.getInstance().getString("从邮件获得：{1}"), str);
				for (int i = 0; i < items.size(); i++) {
					Item item = items.get(i);
					if (item != null) {
						mailDetailInfoData.getItemdata().remove(item);
					}
				}
				if (mailDetailInfoData.getNgold() != 0) {
					if (mailDetailInfoData.getBtGoldType() == 1) {//金币
						BackpackManager.getInstance().changeMoney(player, mailDetailInfoData.getNgold(), Reasons.MAILSHOUQUMONEY, action);
					} else if (mailDetailInfoData.getBtGoldType() == 2) {//元宝
						if (getgold > 0 && recharge > 0) {
							BackpackManager.getInstance().addGold(player, mailDetailInfoData.getNgold(), Reasons.MAILSHOUQUGOLD, action);
						} else {
							BackpackManager.getInstance().changeTmpGold(player, mailDetailInfoData.getNgold());
						}
					}
					mailDetailInfoData.setNgold(0);
				}
				mailDetailInfoData.calbtAccessory();
				if (saveMail(mailDetailInfoData)) {
					sendMessage.setBtErrorCode(Error_Success);
					sendMessage.setMailDetail(getMailDetailInfo(mailDetailInfoData));
					setMailLog(LogType_Rcv, player, mailDetailInfoData);
				} else {
					if (ngold != 0) {
						if (mailDetailInfoData.getBtGoldType() == 1) {//金币
							if (!BackpackManager.getInstance().changeMoney(player, -mailDetailInfoData.getNgold(), Reasons.MAILSHOUQUMONEYFAIL, action)) {
							}
						} else if (mailDetailInfoData.getBtGoldType() == 2) {//元宝
							if (getgold > 0 && recharge > 0) {
								if (!BackpackManager.getInstance().changeGold(player, -mailDetailInfoData.getNgold(), Reasons.MAILRETURNGOLD, action)) {
								}
							} else {
								if (!BackpackManager.getInstance().changeTmpGold(player, -mailDetailInfoData.getNgold())) {
								}
							}
						}
					}
					if (!items.isEmpty()) {
						for (int i = 0; i < items.size(); i++) {
							Item item = items.get(i);
							if (item != null) {
								BackpackManager.getInstance().removeItemByCellId(player, item.getGridId(), Reasons.MAILSHOUQUITEMFAIL, action);
							}
						}
					}
				}
			} else if (message.getItemid() == 0) {
				if (mailDetailInfoData.getNgold() != 0) {
					if (mailDetailInfoData.getBtGoldType() == 1) {//金币
						int money = player.getMoney() + mailDetailInfoData.getNgold();
						if (money < 0 || money > Global.BAG_MAX_COPPER) {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取铜币失败"));
							return;
						}
					} else if (mailDetailInfoData.getBtGoldType() == 2) {//元宝
						if (getgold > 0) {
							int gold = getgold + mailDetailInfoData.getNgold();
							if (gold < 0 || gold > Global.BAG_MAX_GOLD) {
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取元宝失败"));
								return;
							}
						} else {
							int gold = player.getCanreceiveyuanbao() + mailDetailInfoData.getNgold();
							if (gold < 0 || gold > Global.BAG_MAX_GOLD) {
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取元宝失败"));
								return;
							}
						}
					}
				}
				if (mailDetailInfoData.getNgold() != 0) {
					if (mailDetailInfoData.getBtGoldType() == 1) {//金币
						BackpackManager.getInstance().changeMoney(player, mailDetailInfoData.getNgold(), Reasons.MAILSHOUQUMONEY, action);
					} else if (mailDetailInfoData.getBtGoldType() == 2) {//元宝
						if (getgold > 0 && recharge > 0) {
							BackpackManager.getInstance().addGold(player, mailDetailInfoData.getNgold(), Reasons.MAILSHOUQUGOLD, action);
						} else {
							BackpackManager.getInstance().changeTmpGold(player, mailDetailInfoData.getNgold());
						}
					}
					mailDetailInfoData.setNgold(0);
				}
				mailDetailInfoData.calbtAccessory();
				if (saveMail(mailDetailInfoData)) {
					sendMessage.setBtErrorCode(Error_Success);
					sendMessage.setMailDetail(getMailDetailInfo(mailDetailInfoData));
					setMailLog(LogType_Rcv, player, mailDetailInfoData);
				} else {
					if (ngold != 0) {
						if (mailDetailInfoData.getBtGoldType() == 1) {//金币
							if (!BackpackManager.getInstance().changeMoney(player, -mailDetailInfoData.getNgold(), Reasons.MAILSHOUQUMONEYFAIL, action)) {
							}
						} else if (mailDetailInfoData.getBtGoldType() == 2) {//元宝
							if (getgold > 0 && recharge > 0) {
								if (!BackpackManager.getInstance().changeGold(player, -mailDetailInfoData.getNgold(), Reasons.MAILRETURNGOLD, action)) {
								}
							} else {
								if (!BackpackManager.getInstance().changeTmpGold(player, -mailDetailInfoData.getNgold())) {
								}
							}
						}
					}
				}
			} else {
				int itemNum = 0;
				for (int i = 0; i < mailDetailInfoData.getItemdata().size(); i++) {
					Item item = mailDetailInfoData.getItemdata().get(i);
					if (item != null && item.getId() == message.getItemid()) {
						if (item.getItemModelId() > 0) {
							itemNum++;
						} else {
							if (item.getItemModelId() == -1) {
								int money = player.getMoney() + item.getNum();
								if (money < 0 || money > Global.BAG_MAX_COPPER) {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取铜币失败"));
									return;
								}
							} else if (item.getItemModelId() == -2) {
								if (getgold > 0) {
									int gold = getgold + item.getNum();
									if (gold < 0 || gold > Global.BAG_MAX_GOLD) {
										MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取元宝失败"));
										return;
									}
								} else {
									int gold = player.getCanreceiveyuanbao() + item.getNum();
									if (gold < 0 || gold > Global.BAG_MAX_GOLD) {
										MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取元宝失败"));
										return;
									}
								}
							} else if (item.getItemModelId() == -3) {
								int max = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.ZHENGQI_MAX.getValue()).getQ_int_value();
								int zhenqi = player.getZhenqi() + item.getNum();
								if (zhenqi < 0 || zhenqi > max) {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取真气失败"));
									return;
								}
							} else if (item.getItemModelId() == -4) {
								long exp = player.getExp() + item.getNum();
								if (exp < 0) {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取经验失败"));
									return;
								}
							} else if (item.getItemModelId() == -5) {
								int bindgold = player.getBindGold() + item.getNum();
								if (bindgold < 0 || bindgold > Global.BAG_MAX_GOLD) {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取礼金失败"));
									return;
								}
							} else if (item.getItemModelId() == -6) {
								int fightspirit = ArrowManager.getInstance().getFightSpiritNum(player, ArrowManager.FightType_RI) + item.getNum();
								if (fightspirit < 0 || fightspirit > 2100000000) {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取七曜战魂失败"));
									return;
								}
							} else if (item.getItemModelId() == -7) {
								int rank = player.getRankexp() + item.getNum();
								if (rank < 0 || rank > 2100000000) {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取军功失败"));
									return;
								}
							}
						}
						break;
					}
				}
				if (BackpackManager.getInstance().getEmptyGridNum(player) < itemNum) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("收取附件物品时，玩家包裹剩余格子数不足"));
					return;
				}
				for (int i = 0; i < mailDetailInfoData.getItemdata().size(); i++) {
					Item item = mailDetailInfoData.getItemdata().get(i);
					if (item != null && item.getId() == message.getItemid()) {
						if (item.getItemModelId() > 0) {
							BackpackManager.getInstance().addItem(player, item, Reasons.MAILSHOUQUITEM, action);
						} else {
							if (item.getItemModelId() == -1) {
								BackpackManager.getInstance().changeMoney(player, item.getNum(), Reasons.MAILSHOUQUMONEY, action);
							} else if (item.getItemModelId() == -2) {
								if (getgold > 0 && recharge > 0) {
									BackpackManager.getInstance().addGold(player, item.getNum(), Reasons.MAILSHOUQUGOLD, action);
								} else {
									BackpackManager.getInstance().changeTmpGold(player, item.getNum());
								}
							} else if (item.getItemModelId() == -3) {
								PlayerManager.getInstance().addZhenqi(player, item.getNum(), AttributeChangeReason.MAIL);
							} else if (item.getItemModelId() == -4) {
								PlayerManager.getInstance().addExp(player, item.getNum(), AttributeChangeReason.MAIL);
							} else if (item.getItemModelId() == -5) {
								BackpackManager.getInstance().changeBindGold(player, item.getNum(), Reasons.MAILSHOUQUBINDGOLD, action);
							} else if (item.getItemModelId() == -6) {
								ArrowManager.getInstance().addFightSpiritNum(player, ArrowManager.FightType_RI, item.getNum(), true, ArrowReasonsType.MAIL);
							} else if (item.getItemModelId() == -7) {
								RankManager.getInstance().addranknum(player, item.getNum(), RankType.MAIL);
							}
						}
						items.add(item);
						break;
					}
				}
				for (int i = 0; i < items.size(); i++) {
					Item item = items.get(i);
					if (item != null) {
						mailDetailInfoData.getItemdata().remove(item);
					}
				}
				mailDetailInfoData.calbtAccessory();
				if (saveMail(mailDetailInfoData)) {
					sendMessage.setBtErrorCode(Error_Success);
					sendMessage.setMailDetail(getMailDetailInfo(mailDetailInfoData));
					setMailLog(LogType_Rcv, player, mailDetailInfoData);
				} else {
					if (!items.isEmpty()) {
						for (int i = 0; i < items.size(); i++) {
							Item item = items.get(i);
							if (item != null) {
								BackpackManager.getInstance().removeItemByCellId(player, item.getGridId(), Reasons.MAILSHOUQUITEMFAIL, action);
							}
						}
					}
				}
			}
		}
		MessageUtil.tell_player_message(player, sendMessage);
		if (borefresh && mailDetailInfoData != null && mailDetailInfoData.getBtAccessory() == 0) {
			ReqMailDeleteMailToServerMessage deleteMailToServerMessage = new ReqMailDeleteMailToServerMessage();
			deleteMailToServerMessage.setBtdeleteall((byte) 0);
			deleteMailToServerMessage.getDeleteMailIdList().add(mailDetailInfoData.getMailid());
			reqMailDeleteMailToServer(player, deleteMailToServerMessage);
		}
	}

	public void reqMailSendNewMailToServer(Player player, ReqMailSendNewMailToServerMessage message) {
		boolean boSend = true;
		ResMailSendNewMailToClientMessage sendMessage = new ResMailSendNewMailToClientMessage();
		sendMessage.setBtErrorCode(Error_Fail);
		MailDetailInfoData mailDetailInfoData = new MailDetailInfoData();
		mailDetailInfoData.setSaveType(SaveMailThread.MAIL_INSERT);
		mailDetailInfoData.setBtSystem((byte) 0);
		mailDetailInfoData.setSenderid(player.getId());
		mailDetailInfoData.setSzSenderName(player.getName());
		mailDetailInfoData.setReceiverid(0);//TODO 没有通过名字找到玩家
		mailDetailInfoData.setSzReceiverName(message.getSzReceiverName());
		mailDetailInfoData.setSzTitle(message.getSzTitle());
		mailDetailInfoData.setSzNotice(message.getSzNotice());
		mailDetailInfoData.setBtGoldType(message.getBtGoldType());
		mailDetailInfoData.setNgold(message.getNGold());
		mailDetailInfoData.setNsendTime((int) (System.currentTimeMillis() / 1000));
		mailDetailInfoData.setBtReturn((byte) 0);
		mailDetailInfoData.setBtRead((byte) 0);
		long action = Config.getId();
		int getgold = 0;
		Gold goldinfo = player.getGold();
		if (goldinfo != null) {
			getgold = goldinfo.getGold();
		}
		if (message.getBtGoldType() == 1) {//金币
			if (player.getMoney() < message.getNGold()) {
				boSend = false;
			}
		} else if (message.getBtGoldType() == 2) {//元宝
			if (getgold < message.getNGold()) {
				boSend = false;
			}
		}
		if (boSend) {
			for (int i = 0; i < message.getItemidlist().size(); i++) {
				long itemid = message.getItemidlist().get(i);
				if (itemid != 0) {
					Item item = BackpackManager.getInstance().getItemById(player, itemid);
					if (item != null && !item.isTrade() && !item.isBind() && !item.isLost()) {
						if (BackpackManager.getInstance().removeItemByCellId(player, item.getGridId(), Reasons.MAILSENDITEM, action)) {
							mailDetailInfoData.getItemdata().add(item);
						} else {
							boSend = false;
							break;
						}
					} else {
						boSend = false;
						break;
					}
				}
			}
		}
		if (boSend) {
			if (message.getBtGoldType() == 1) {//金币
				BackpackManager.getInstance().changeMoney(player, -mailDetailInfoData.getNgold(), Reasons.MAILSENDMONEY, action);
			} else if (message.getBtGoldType() == 2) {//元宝
				BackpackManager.getInstance().changeGold(player, -mailDetailInfoData.getNgold(), Reasons.MAILSENDGOLD, action);
			}
			mailDetailInfoData.calbtAccessory();
			mailDetailInfoData.setMailid(Config.getId());
			if (saveMail(mailDetailInfoData)) {
				sendMessage.setBtErrorCode(Error_Success);
				getNewMailTip(0);//TODO 没有通过名字找到玩家
				setMailLog(LogType_Send, player, mailDetailInfoData);
			} else {
				if (message.getBtGoldType() == 1) {//金币
					BackpackManager.getInstance().changeMoney(player, mailDetailInfoData.getNgold(), Reasons.MAILSENDMONEYFAIL, action);
				} else if (message.getBtGoldType() == 2) {//元宝
					BackpackManager.getInstance().addGold(player, mailDetailInfoData.getNgold(), Reasons.MAILRETURNSENDGOLD, action);
				}
				for (int i = 0; i < mailDetailInfoData.getItemdata().size(); i++) {
					Item item = mailDetailInfoData.getItemdata().get(i);
					if (item != null) {
						BackpackManager.getInstance().addItem(player, item, Reasons.MAILSENDITEMFAIL, action);
					}
				}
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("发送邮件失败"));
			}
		} else {
			for (int i = 0; i < mailDetailInfoData.getItemdata().size(); i++) {
				Item item = mailDetailInfoData.getItemdata().get(i);
				if (item != null) {
					BackpackManager.getInstance().addItem(player, item, Reasons.MAILSENDITEMFAIL, action);
				}
			}
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("发送邮件失败"));
		}
		MessageUtil.tell_player_message(player, sendMessage);
	}

	public void reqMailDeleteMailToServer(Player player, ReqMailDeleteMailToServerMessage message) {
		if (message.getBtdeleteall() == 1) {
			if (!player.getMaildataMap().isEmpty()) {
				MailDetailInfoData mailDetailInfoData = (MailDetailInfoData) player.getMaildataMap().values().toArray()[0];
				if (mailDetailInfoData != null) {
					if (deleteMailByUserid(mailDetailInfoData)) {
						MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("邮件全部删除成功"));
						player.getMaildataMap().clear();
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("邮件全部删除失败"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("邮件全部删除失败"));
				}
			}
		} else {
			for (int i = 0; i < message.getDeleteMailIdList().size(); i++) {
				Long deletemailid = message.getDeleteMailIdList().get(i);
				if (deletemailid != 0) {
					MailDetailInfoData mailDetailInfoData = player.getMaildataMap().get(deletemailid);
					if (mailDetailInfoData != null) {
						if (deleteMail(mailDetailInfoData)) {
							MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("邮件删除成功"));
							player.getMaildataMap().remove(deletemailid);
						} else {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("邮件删除失败"));
						}
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("邮件删除失败"));
					}
				}
			}
		}
		ResMailQueryListToClientMessage sendMessage = new ResMailQueryListToClientMessage();
		sendMessage.setBtErrorCode(Error_Fail);
		if (!player.getMaildataMap().isEmpty()) {
			for (Map.Entry<Long, MailDetailInfoData> entry : player.getMaildataMap().entrySet()) {
				MailDetailInfoData mailDetailInfoData = entry.getValue();
				if (mailDetailInfoData != null) {
					sendMessage.getMailSummaryList().add(getMailSummaryInfo(mailDetailInfoData));
				}
			}
		}
//		List<MailDetailInfoData> listdatas = loadMailByUserid(player.getId());
//		if (!listdatas.isEmpty()) {
//			for (int i = 0; i < listdatas.size(); i++) {
//				MailDetailInfoData mailDetailInfoData = listdatas.get(i);
//				if (mailDetailInfoData != null) {
//					sendMessage.getMailSummaryList().add(getMailSummaryInfo(mailDetailInfoData));
//				}
//			}
//		}
		sendMessage.setBtErrorCode(Error_Success);
		MessageUtil.tell_player_message(player, sendMessage);
	}

	public void reqMailQueryUserToServer(Player player, ReqMailQueryUserToServerMessage message) {
		ResMailQueryUserToClientMessage sendMessage = new ResMailQueryUserToClientMessage();
		sendMessage.setBtErrorCode(Error_Fail);
		sendMessage.setSzName(message.getSzName());//TODO 没有通过玩家名找玩家
		MessageUtil.tell_player_message(player, sendMessage);
	}

	public void reqMailReturnToServer(Player player, ReqMailReturnToServerMessage message) {
		ResMailReturnToClientMessage sendMessage = new ResMailReturnToClientMessage();
		sendMessage.setMailid(message.getMailid());
		sendMessage.setBtErrorCode(Error_Fail);
		MessageUtil.tell_player_message(player, sendMessage);
	}

	public void reqMailListGetItemToServer(Player player, ReqMailListGetItemToServerMessage message) {
		ReqMailDeleteMailToServerMessage deleteMailToServerMessage = new ReqMailDeleteMailToServerMessage();
		deleteMailToServerMessage.setBtdeleteall((byte) 0);
		if (message.getItemid() == -1 && message.getMailidlist().isEmpty()) {
			Iterator<Long> iterator = player.getMaildataMap().keySet().iterator();
			while(iterator.hasNext()) {
				Long mailid =  iterator.next();
				if (mailid != 0) {
					ReqMailGetItemToServerMessage getItemToServerMessage = new ReqMailGetItemToServerMessage();
					getItemToServerMessage.setMailid(mailid);
					getItemToServerMessage.setItemid(message.getItemid());
					reqMailGetItemToServer(player, getItemToServerMessage, false);
					MailDetailInfoData mailDetailInfoData = player.getMaildataMap().get(mailid);
					if (mailDetailInfoData != null && mailDetailInfoData.getBtAccessory() == 0) {
						deleteMailToServerMessage.getDeleteMailIdList().add(mailid);
					}
				}
			}
		} else {
			for (int i = 0; i < message.getMailidlist().size(); i++) {
				Long mailid = message.getMailidlist().get(i);
				if (mailid != 0) {
					ReqMailGetItemToServerMessage getItemToServerMessage = new ReqMailGetItemToServerMessage();
					getItemToServerMessage.setMailid(mailid);
					getItemToServerMessage.setItemid(message.getItemid());
					reqMailGetItemToServer(player, getItemToServerMessage, false);
					MailDetailInfoData mailDetailInfoData = player.getMaildataMap().get(mailid);
					if (mailDetailInfoData != null && mailDetailInfoData.getBtAccessory() == 0) {
						deleteMailToServerMessage.getDeleteMailIdList().add(mailid);
					}
				}
			}
		}
		reqMailDeleteMailToServer(player, deleteMailToServerMessage);
	}

	/**
	 * 发送系统邮件
	 *
	 * @param receiverid 接收者玩家id
	 * @param receivername 接收者玩家名字
	 * @param title 邮件标题
	 * @param notice 邮件内容
	 * @param goldtype 金币类型 1 金币 2 元宝
	 * @param gold 金币数量
	 * @param items 要发送的物品列表
	 * @return boolean true 成功 false 失败
	 */
	public boolean sendSystemMail(long receiverid, String receivername, String title, String notice, byte goldtype, int gold, List<Item> items) {
		boolean boSend = true;
		MailDetailInfoData mailDetailInfoData = new MailDetailInfoData();
		mailDetailInfoData.setSaveType(SaveMailThread.MAIL_INSERT);
		mailDetailInfoData.setBtSystem((byte) 1);
		mailDetailInfoData.setSenderid(0);
		mailDetailInfoData.setSzSenderName(ResManager.getInstance().getString("系统邮件"));
		mailDetailInfoData.setReceiverid(receiverid);
		mailDetailInfoData.setSzReceiverName(receivername);
		mailDetailInfoData.setSzTitle(title);
		mailDetailInfoData.setSzNotice(notice);
		mailDetailInfoData.setBtGoldType(goldtype);
		mailDetailInfoData.setNgold(gold);
		mailDetailInfoData.setNsendTime((int) (System.currentTimeMillis() / 1000));
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
			Player player = PlayerManager.getInstance().getOnLinePlayer(receiverid);
			if (player != null) {
				if (saveMailToMap(player, mailDetailInfoData)) {
					getNewMailTip(receiverid);
					setMailLog(LogType_Send, player, mailDetailInfoData);
					return true;
				}
			} else {
				if (saveMail(mailDetailInfoData)) {
					//getNewMailTip(receiverid);
					setMailLog(LogType_Send, null, mailDetailInfoData);
					return true;
				}
			}
		}
		return false;
	}

	public void resMailSendSystemMailToServer(Player player, ResMailSendSystemMailToServerMessage message) {
		log.error("accept mail from world: "+message.getMailJsonstr());
		ReqMailSendSystemMailToWorldMessage sendMessage = new ReqMailSendSystemMailToWorldMessage();
		sendMessage.setBtErrorCode(Error_Fail);
		MailDetailInfoData mailDetailInfoData = (MailDetailInfoData) JSONserializable.toObject(message.getMailJsonstr(), MailDetailInfoData.class);
		if (mailDetailInfoData != null) {
			mailDetailInfoData.setSaveType(SaveMailThread.MAIL_INSERT);
			mailDetailInfoData.calbtAccessory();
			if (player != null) {
				if (saveMailToMap(player, mailDetailInfoData)) {
					getNewMailTip(player.getId());
					setMailLog(LogType_Send, player, mailDetailInfoData);
					sendMessage.setBtErrorCode(Error_Success);
					sendMessage.setPlayerid(player.getId());
				}else{
					log.error("saveMailToMap Fail! "+player.getName()+"("+player.getId()+") "+message.getMailJsonstr());
				}
			} else {
				if (saveMail(mailDetailInfoData)) {
					//getNewMailTip(receiverid);
					setMailLog(LogType_Send, null, mailDetailInfoData);
					sendMessage.setBtErrorCode(Error_Success);
					sendMessage.setPlayerid(mailDetailInfoData.getReceiverid());
				}else{
					log.error("saveMailToMap Fail! "+message.getMailJsonstr());
				}
			}
		}
		MessageUtil.send_to_world(sendMessage);
	}

	public void getNewMailTip(long checkid) {
		Player player = PlayerManager.getInstance().getPlayer(checkid);
		if (player != null) {
			if (!player.getMaildataMap().isEmpty()) {
				ResMailGetNewMailToClientMessage sendMessage = new ResMailGetNewMailToClientMessage();
				int count = 0;
				for (Map.Entry<Long, MailDetailInfoData> entry : player.getMaildataMap().entrySet()) {
					MailDetailInfoData mailDetailInfoData = entry.getValue();
					if (mailDetailInfoData != null && mailDetailInfoData.getBtRead() == 0) {
						count++;
					}
				}
				sendMessage.setNCount(count);
				MessageUtil.tell_player_message(player, sendMessage);
			}
//			List<Q_mailBean> mailBeans = getQ_mailDao().selectnewbyuserid(checkid);
//			if (!mailBeans.isEmpty()) {
//				ResMailGetNewMailToClientMessage sendMessage = new ResMailGetNewMailToClientMessage();
//				sendMessage.setNCount(mailBeans.size());
//				MessageUtil.tell_player_message(player, sendMessage);
//			}
		}
	}

	public void setMailLog(int logtype, Player player, MailDetailInfoData mailDetailInfoData) {
		MailLog log = new MailLog();
		log.setLogtype(logtype);
		log.setAccount((player != null) ? player.getUserId() : "system");
		log.setContent(mailDetailInfoData.getSzNotice());
		log.setEventid(mailDetailInfoData.getMailid());
		log.setEventtime(System.currentTimeMillis());
		log.setGoldtype(mailDetailInfoData.getBtGoldType());
		log.setItemdata(JSONserializable.toString(mailDetailInfoData));
		log.setMoney(mailDetailInfoData.getNgold());
		log.setRoleid((player != null) ? player.getId() : 0);
		log.setRolelevel((player != null) ? player.getLevel() : 0);
		log.setRolename((player != null) ? player.getName() : "system");
		log.setSendername(mailDetailInfoData.getSzSenderName());
		log.setSendtime(mailDetailInfoData.getNsendTime());
		log.setTitle(mailDetailInfoData.getSzTitle());
		log.setSid((player != null) ? player.getCreateServerId():-1); //TODO 唐超修改 player为空时服务器为-1
		LogService.getInstance().execute(log);
	}
}
