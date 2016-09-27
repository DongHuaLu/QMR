package com.game.card.manager;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.alibaba.fastjson.JSON;
import com.game.allserverdb.bean.All_Card_dataBean;
import com.game.allserverdb.dao.All_Card_dataDao;
import com.game.card.log.CardLog;
import com.game.card.message.ReqInnerCardPhoneToWorldMessage;
import com.game.card.message.ReqInnerCardToWorldMessage;
import com.game.card.message.ResCardPhoneToClientMessage;
import com.game.card.message.ResInnerCardToServerMessage;
import com.game.card.script.ICardParseScript;
import com.game.data.bean.Q_cardBean;
import com.game.data.dao.Q_cardDao;
import com.game.data.manager.DataManager;
import com.game.db.bean.Card_dataBean;
import com.game.db.bean.Phone_cardBean;
import com.game.db.dao.Card_dataDao;
import com.game.db.dao.Phone_cardDao;
import com.game.dblog.LogService;
import com.game.globaldb.bean.G_Card_dataBean;
import com.game.globaldb.dao.G_Card_dataDao;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerWorldInfo;
import com.game.script.manager.ScriptManager;
import com.game.script.structs.ScriptEnum;
import com.game.server.WorldServer;
import com.game.utils.MessageUtil;
import com.game.utils.TimeUtil;

/**
 * CDKEY管理类
 *
 * @author 杨鸿岚
 */
public class CardManager {

	private Logger log = Logger.getLogger(CardManager.class);
	private static Object obj = new Object();
	//管理类实例
	private static CardManager manager;

	private CardManager() {
	}

	public static CardManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new CardManager();
			}
		}
		return manager;
	}
	private Card_dataDao card_dataDao = new Card_dataDao();
	private G_Card_dataDao g_Card_dataDao = new G_Card_dataDao();
	private All_Card_dataDao all_Card_dataDao = new All_Card_dataDao();
	private Q_cardDao q_cardDao = new Q_cardDao();

	public Card_dataDao getCard_dataDao() {
		return card_dataDao;
	}

	public G_Card_dataDao getG_Card_dataDao() {
		return g_Card_dataDao;
	}

	public All_Card_dataDao getAll_Card_dataDao() {
		return all_Card_dataDao;
	}

	public Q_cardDao getQ_cardDao() {
		return q_cardDao;
	}
	private short agid_save;
	private short type_save;

	/**
	 * @return the agid_save
	 */
	public short getAgid_save() {
		return agid_save;
	}

	/**
	 * @param agid_save the agid_save to set
	 */
	public void setAgid_save(short agid_save) {
		this.agid_save = agid_save;
	}

	/**
	 * @return the type_save
	 */
	public short getType_save() {
		return type_save;
	}

	/**
	 * @param type_save the type_save to set
	 */
	public void setType_save(short type_save) {
		this.type_save = type_save;
	}
	//错误代码
	private byte Error_PhoneSuccess = 1;
	private byte Error_Success = 0;
	private byte Error_Fail = -1;		//验证失败
	private byte Error_UseAccount = -2;	//已经使用过的账号
	private byte Error_UsePlayer = -3;	//已使用过的角色
	private byte Error_UseCard = -4;	//已使用过的卡
	private byte Error_NoType = -5;		//没有这个类型的卡
	private byte Error_Sql = -6;		//数据库连接错误
	private byte Error_PhoneFail = -7;
//	private String PassKey = "34r98s)&j&*^*&asdOIUOAISUD7";
	//平台ID
//	private final short AG_37wan = 1;
//	private final short AG_17173 = 2;
//	private final short AG_07073 = 3;
//	private final short AG_265G = 4;
//	private final short AG_sina = 5;
//	private final short AG_duowan = 6;
//	private final short AG_pufa = 7;
//	private final short AG_ycshouji = 8;

	public void reqInnerCardToWorld(ReqInnerCardToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			int giftid = Error_Fail;
			boolean boPhoneVer = false;
			if (message.getCard().equalsIgnoreCase("-1")) {
				giftid = Error_PhoneFail;
				boPhoneVer = true;
				PlayerWorldInfo playerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(player.getId());
				Phone_cardBean phone_cardBean = getPhoneCardBean(playerWorldInfo);
				if (phone_cardBean != null) {
					if (!phone_cardBean.getVercode().equalsIgnoreCase("-1") && !phone_cardBean.getVercode().equalsIgnoreCase("") && !phone_cardBean.getPhone().equalsIgnoreCase("")) {
						setAgid_save((short) phone_cardBean.getAgid());
						setType_save((short) phone_cardBean.getType());
						giftid = getGiftId((short) phone_cardBean.getAgid(), (short) phone_cardBean.getType());
						if (giftid != 0) {
							phone_cardBean.setVercode("-1");
							if (!savePhoneData(phone_cardBean, false)) {
								giftid = Error_Sql;
							}
						} else {
							giftid = Error_NoType;
						}
					} else {
						giftid = Error_UseAccount;
					}
				} else {
					giftid = Error_Sql;
				}
				ResCardPhoneToClientMessage sendMessage = getPhoneToClientMessage(playerWorldInfo);
				if (sendMessage != null) {
					if (giftid == Error_Sql) {
						sendMessage.setErrorcode(PhoneError_Sql);
					}
					MessageUtil.tell_player_message(player, sendMessage);
				}
				if (giftid < 0) {
					giftid = Error_PhoneFail;
				}
				log.error(String.format("领取手机验证奖励giftid=[%d],phone_cardBean=[%s],sendMessage=[%s]", giftid, phone_cardBean == null ? "phone_cardBean为NULL" : JSON.toJSONString(phone_cardBean), JSON.toJSONString(sendMessage)));
			} else {
				ICardParseScript script = (ICardParseScript) ScriptManager.getInstance().getScript(ScriptEnum.CARDPARSE);
				if (script != null) {
					try {
						giftid = script.cardParse(player, message.getCard(), message.getArguserName(), message.getArgzoneName(), message.getArgName());
					} catch (Exception e) {
						log.error(e, e);
					}
				} else {
					log.error("没有CDKEY解析脚本");
				}
//				if (message.getCard().length() < 16) {
//					giftid = checkCDKEY8(player, message.getCard());
//				} else {
//					giftid = checkCDKEY16(player, message.getCard());
//				}
			}
			if (giftid > 0) {
				ResInnerCardToServerMessage sendMessage = new ResInnerCardToServerMessage();
				sendMessage.setErrorcode(boPhoneVer ? Error_PhoneSuccess : Error_Success);
				sendMessage.setPlayerId(player.getId());
				sendMessage.setAgid(getAgid_save());
				sendMessage.setType(getType_save());
				sendMessage.setGiftid(giftid);
				MessageUtil.send_to_game(player, sendMessage);
			} else {
				ResInnerCardToServerMessage sendMessage = new ResInnerCardToServerMessage();
				sendMessage.setErrorcode((byte) giftid);
				sendMessage.setPlayerId(player.getId());
				sendMessage.setAgid(getAgid_save());
				sendMessage.setType(getType_save());
				sendMessage.setGiftid(0);
				MessageUtil.send_to_game(player, sendMessage);
			}
			setCardLog(player.getId(), getAgid_save(), getType_save(), player.getServer(), giftid, message.getCard(), player.getCreateServer());
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

//	public int checkCDKEY8(Player player, String Card) {
//		int erroridandret = Error_Success;
//		String cardString = Card.toLowerCase();
//		long cardlong = Long.parseLong(cardString, 36);
//		short type = (short) (cardlong >> 32 & 0xff);
//		int randnum = (int) (cardlong >> 16 & 0xffff);
//		int crc32 = (int) (cardlong & 0xffff);
//		long crc32long = ((long) crc32) & 0x000000000000FFFFL;
//		//解密
//		String usePassKey = PassKey;
//		type = (short) ((short) ((type & 0xFF) ^ crc32long) & 0xFF);
//		short crctype = type;
//		if (type >= 128) {
//			usePassKey = usePassKey + "_" + player.getServer();
//			type = (short) (type & 0x7F);
//		}
//		//手机的8位CDKEY，type是平台id,50表示手机的类型
//		setAgid_save(type);
//		setType_save((short) 50);
//		randnum = (int) ((long) ((randnum & 0x0000FFFF) ^ crc32long) & 0x0000FFFF);
//		//解密
//		String crc32str = String.format("%d%d%s", crctype, randnum, usePassKey);
//		CRC32 crc32s = new CRC32();
//		crc32s.update(crc32str.getBytes());
//		long getnowCrc32 = crc32s.getValue();
//		getnowCrc32 = (((getnowCrc32 >> 16 & 0x0000ffff) ^ (getnowCrc32 & 0x0000ffff)) & 0x0000ffff);
//		if (crc32long == getnowCrc32) {//TODO 全区通用的卡的话，可能还需要一个全区共用的数据库服务器
//			Card_dataBean card_dataBean = getOneCard_dataBean(player, Card, type, 50);
//			erroridandret = checkCardData(card_dataBean);
//			if (erroridandret == Error_Success) {
//				int giftid = getGiftId((short) type, (short) 50);
//				if (giftid != 0) {
//					if (getCard_dataDao().insert(card_dataBean) == 0) {
//						erroridandret = Error_Sql;
//					} else {
//						erroridandret = giftid;
//					}
//				} else {
//					erroridandret = Error_NoType;
//				}
//			}
//		} else {
//			erroridandret = Error_Fail;
//		}
//		return erroridandret;
//	}
//
//	public int checkCDKEY16(Player player, String Card) {
//		int erroridandret = Error_Success;
//		Base64 base64 = new Base64();
//		IoBuffer buf = IoBuffer.allocate(255);
//		buf.put(base64.decode(Card.getBytes()));
//		buf.flip();
//		short agid = buf.getUnsigned();
//		setAgid_save(agid);
//		short type = buf.getUnsigned();
//		long nowtime = buf.getUnsignedInt();
//		int randnum = buf.getUnsignedShort();
//		int crc32 = buf.getInt();
//		long crc32long = ((long) crc32) & 0x00000000FFFFFFFFL;
//		//解密
//		String usePassKey = PassKey;
//		type = (short) ((short) ((type & 0xFF) ^ crc32long) & 0xFF);
//		short crctype = type;
//		if (type >= 128) {
//			usePassKey = usePassKey + "_" + player.getServer();
//			type = (short) (type & 0x7F);
//		}
//		setType_save(type);
//		nowtime = (((nowtime & 0x00000000FFFFFFFFL) ^ crc32long) & 0x00000000FFFFFFFFL);
//		//解密
//		String crc32str = String.format("%d%d%d%d%s", agid, crctype, nowtime, randnum, usePassKey);
//		CRC32 crc32s = new CRC32();
//		crc32s.update(crc32str.getBytes());
//		long getnowCrc32 = crc32s.getValue();
//		if (crc32long == getnowCrc32) {//TODO 全区通用的卡的话，可能还需要一个全区共用的数据库服务器
//			Card_dataBean card_dataBean = getOneCard_dataBean(player, Card, agid, type);
//			erroridandret = checkCardData(card_dataBean);
//			if (erroridandret == Error_Success) {
//				int giftid = getGiftId(agid, type);
//				if (giftid != 0) {
//					if (getCard_dataDao().insert(card_dataBean) == 0) {
//						erroridandret = Error_Sql;
//					} else {
//						erroridandret = giftid;
//					}
//				} else {
//					erroridandret = Error_NoType;
//				}
//			}
//		} else {
//			erroridandret = Error_Fail;
//		}
//		return erroridandret;
//	}
	/**
	 * 检查单平台单区卡数据库逻辑(以后检查就改这里)
	 *
	 * @param card card_dataBean
	 * @return boolean
	 */
	public int checkCardData(Card_dataBean card_dataBean) {
		int errorid = Error_Success;
		List<Card_dataBean> card_dataBeans = getCard_dataDao().selectbyid(card_dataBean.getCardid());//用过这个卡的不能用了。所有类型都要检查这个，类型为0，5（5类型为现在临时要用加上的）只检查这个。
		if (card_dataBeans != null && !card_dataBeans.isEmpty()) {
			errorid = Error_UseCard;
		}
		if (card_dataBean.getType() == 1) {//用过这种argid礼包类型的卡，其他平台都不能用(账号关联)
			List<Card_dataBean> card_dataBeanTypes = getCard_dataDao().selectbyaccountandtypeonly(card_dataBean);
			if (card_dataBeanTypes != null && !card_dataBeanTypes.isEmpty()) {
				errorid = Error_UseAccount;
			}
		} else if (card_dataBean.getType() == 2) {//用过这个argid礼包类型的卡，用的平台不能用，其他平台能用(账号关联)
			List<Card_dataBean> card_dataBeanTypes = getCard_dataDao().selectbyaccountandagidonly(card_dataBean);
			if (card_dataBeanTypes != null && !card_dataBeanTypes.isEmpty()) {
				errorid = Error_UseAccount;
			}
		} else if (card_dataBean.getType() == 3) {//用过这种argid礼包类型的卡，其他平台都不能用(角色关联)
			List<Card_dataBean> card_dataBeanTypes = getCard_dataDao().selectbyroleidandtypeonly(card_dataBean);
			if (card_dataBeanTypes != null && !card_dataBeanTypes.isEmpty()) {
				errorid = Error_UsePlayer;
			}
		} else if (card_dataBean.getType() == 4) {//用过这个argid礼包类型的卡，用的平台不能用，其他平台能用(角色关联)
			List<Card_dataBean> card_dataBeanTypes = getCard_dataDao().selectbyroleidandagidonly(card_dataBean);
			if (card_dataBeanTypes != null && !card_dataBeanTypes.isEmpty()) {
				errorid = Error_UsePlayer;
			}
		} else if (card_dataBean.getType() == 50) {//用过这个argid礼包类型的卡，用的平台不能用，其他平台能用(账号关联)(手机)
			List<Card_dataBean> card_dataBeanTypes = getCard_dataDao().selectbyaccountandagidonly(card_dataBean);
			if (card_dataBeanTypes != null && !card_dataBeanTypes.isEmpty()) {
				errorid = Error_UseAccount;
			}
		}
		return errorid;
	}
	
	/**
	 * 检查单平台全区卡数据库逻辑(以后检查就改这里)
	 *
	 * @param card g_card_dataBean
	 * @return boolean
	 */
	public int checkCardData(G_Card_dataBean g_Card_dataBean) {
		int errorid = Error_Success;
		if (g_Card_dataBean.getType() == 6 || g_Card_dataBean.getType() == 8) {//现在6是单平台全区数据库检查
			List<G_Card_dataBean> card_dataBeans = getG_Card_dataDao().selectbyid(g_Card_dataBean.getCardid());//用过这个卡的不能用了。所有类型都要检查这个。
			if (card_dataBeans != null && !card_dataBeans.isEmpty()) {
				errorid = Error_UseCard;
			}
		}else{
			errorid = Error_NoType;
		}
		if (g_Card_dataBean.getType() == 6) {//用过这个argid礼包类型的卡，用的平台不能用，其他平台能用(账号关联)(单平台全区数据库关联)
			List<G_Card_dataBean> card_dataBeanTypes = getG_Card_dataDao().selectbyaccountandagidonly(g_Card_dataBean);
			if (card_dataBeanTypes != null && !card_dataBeanTypes.isEmpty()) {
				errorid = Error_UseAccount;
			}
		}
		return errorid;
	}
	
	/**
	 * 检查全平台全区卡数据库逻辑(以后检查就改这里)
	 *
	 * @param card all_card_dataBean
	 * @return boolean
	 */
	public int checkCardData(All_Card_dataBean all_Card_dataBean) {
		int errorid = Error_Success;
		if (all_Card_dataBean.getType() == 7 || all_Card_dataBean.getType() == 9) {//现在7是全平台全区数据库检查
			List<All_Card_dataBean> card_dataBeans = getAll_Card_dataDao().selectbyid(all_Card_dataBean.getCardid());//用过这个卡的不能用了。所有类型都要检查这个。
			if (card_dataBeans != null && !card_dataBeans.isEmpty()) {
				errorid = Error_UseCard;
			}
		} else {
			errorid = Error_NoType;
		}
		if (all_Card_dataBean.getType() == 7) {//用过这个argid礼包类型的卡，用的平台不能用，其他平台能用(账号关联)(全平台全区数据库关联)
			List<All_Card_dataBean> card_dataBeanTypes = getAll_Card_dataDao().selectbyaccountandagidonly(all_Card_dataBean);
			if (card_dataBeanTypes != null && !card_dataBeanTypes.isEmpty()) {
				errorid = Error_UseAccount;
			}
		}
		return errorid;
	}

	public int getGiftId(short agid, short type) {
		Q_cardBean q_cardBean = DataManager.getInstance().q_cardContainer.getMap().get("" + agid + "_" + type);
		if (q_cardBean != null) {
			return q_cardBean.getQ_item_id();
		}
		return 0;
	}

	/**
	 * 得到单平台单区的CDKEY数据
	 */
	public Card_dataBean getOneCard_dataBean(Player player, String cardid, int agid, int type) {
		Card_dataBean card_dataBean = new Card_dataBean();
		card_dataBean.setCardid(cardid);
		card_dataBean.setAgid(agid);
		card_dataBean.setType(type);
		card_dataBean.setAccount(player.getUserId());
		card_dataBean.setRoleid(player.getId());
		return card_dataBean;
	}

	/**
	 * 得到单平台全区的CDKEY数据
	 */
	public G_Card_dataBean getOneG_Card_dataBean(Player player, String cardid, int agid, int type) {
		G_Card_dataBean g_Card_dataBean = new G_Card_dataBean();
		g_Card_dataBean.setCardid(cardid);
		g_Card_dataBean.setAgid(agid);
		g_Card_dataBean.setType(type);
		g_Card_dataBean.setAccount(player.getUserId());
		g_Card_dataBean.setRoleid(player.getId());
		g_Card_dataBean.setWeb(WorldServer.getInstance().getServerWeb());
		return g_Card_dataBean;
	}

	/**
	 * 得到全平台全区的CDKEY数据
	 */
	public All_Card_dataBean getOneAll_Card_dataBean(Player player, String cardid, int agid, int type) {
		All_Card_dataBean all_Card_dataBean = new All_Card_dataBean();
		all_Card_dataBean.setCardid(cardid);
		all_Card_dataBean.setAgid(agid);
		all_Card_dataBean.setType(type);
		all_Card_dataBean.setAccount(player.getUserId());
		all_Card_dataBean.setRoleid(player.getId());
		all_Card_dataBean.setWeb(WorldServer.getInstance().getServerWeb());
		return all_Card_dataBean;
	}

	/**
	 * 使用CDKEY日志
	 *
	 * @param playerid	//记录日志的玩家ID
	 * @param argid	//平台id
	 * @param type	//卡使用类型
	 * @param server	//区服务器id
	 * @param useresult	//使用结果
	 * @param card	//卡号
	 * @return
	 */
	public void setCardLog(long playerid, int argid, int type, int server, int useresult, String card, int sid) {
		CardLog cardLog = new CardLog();
		cardLog.setPlayerid(playerid);
		cardLog.setArgid(argid);
		cardLog.setType(type);
		cardLog.setServer(server);
		cardLog.setUseresult(useresult);
		cardLog.setCard(card);
		cardLog.setSid(sid);
		LogService.getInstance().execute(cardLog);
	}
	//----------------------------手机验证---------------------------//
	private Phone_cardDao phone_cardDao = new Phone_cardDao();

	public Phone_cardDao getPhone_cardDao() {
		return phone_cardDao;
	}
	//错误代码
	private byte PhoneError_NoRepeatReward = -6;	//不能重复领取奖励
	private byte PhoneError_Fail = -5;		//验证失败
	private byte PhoneError_VerError5 = -4;		//验证码错误超过5次
	private byte PhoneError_PhoneVerError3 = -3;	//手机验证错误超过3次
	private byte PhoneError_PhoneNoSame = -2;	//手机错误
	private byte PhoneError_Sql = -1;		//数据库错误
	private byte PhoneError_Success = 0;		//验证成功
	//状态
	private byte PhoneStatus_NoBind = 0;		//没绑定成功
	private byte PhoneStatus_Bind = 1;		//绑定，没验证
	private byte PhoneStatus_VerCode = 2;		//验证
	private byte PhoneStatus_GetAward = 3;		//领奖

	public Phone_cardBean getPhoneCardBean(PlayerWorldInfo playerWorldInfo) {
		if (playerWorldInfo != null) {
			return getPhone_cardDao().selectbyaccount(playerWorldInfo.getAccount());
		}
		return null;
	}

	public ResCardPhoneToClientMessage getPhoneToClientMessage(PlayerWorldInfo playerWorldInfo) {
		Phone_cardBean phone_cardBean = getPhoneCardBean(playerWorldInfo);
		if (phone_cardBean != null) {
			ResCardPhoneToClientMessage sendMessage = new ResCardPhoneToClientMessage();
			sendMessage.setAccount(phone_cardBean.getAccount());
			sendMessage.setAgid(phone_cardBean.getAgid());
			sendMessage.setPhone(phone_cardBean.getPhone());
			sendMessage.setPlayerid(phone_cardBean.getRoleid());
			sendMessage.setType(phone_cardBean.getType());
			sendMessage.setVercode(phone_cardBean.getVercode());
			sendMessage.setPhonevernum(phone_cardBean.getPhonevernum());
			sendMessage.setVererrornum(phone_cardBean.getVererrornum());//TODO 加了2个变量，没处理
			if (phone_cardBean.getPhone().equalsIgnoreCase("")) {
				sendMessage.setStatus(PhoneStatus_NoBind);
			} else {
				if (phone_cardBean.getVercode().equalsIgnoreCase("")) {
					sendMessage.setStatus(PhoneStatus_Bind);
				} else if (phone_cardBean.getVercode().equalsIgnoreCase("-1")) {
					sendMessage.setStatus(PhoneStatus_GetAward);
				} else {
					sendMessage.setStatus(PhoneStatus_VerCode);
				}
			}
			if (sendMessage.getStatus() != PhoneStatus_VerCode) {
				if (phone_cardBean.getVererrornum() >= 5) {
					sendMessage.setErrorcode(PhoneError_VerError5);
				} else {
					if (TimeUtil.isSameDay(phone_cardBean.getPhonevertime(), System.currentTimeMillis())) {
						if (phone_cardBean.getPhonevernum() >= 3) {
							sendMessage.setErrorcode(PhoneError_PhoneVerError3);
						}
					} else {
						phone_cardBean.setPhonevernum(0);
						phone_cardBean.setPhonevertime(System.currentTimeMillis());
						if (!savePhoneData(phone_cardBean, false)) {
							sendMessage.setErrorcode(PhoneError_Sql);
						}
					}
				}
			}
			return sendMessage;
		}
		return null;
	}

	public boolean savePhoneData(Phone_cardBean phone_cardBean, boolean boInsert) {
		if (boInsert) {
			if (getPhone_cardDao().insert(phone_cardBean) == 0) {
				return false;
			}
		} else {
			if (getPhone_cardDao().update(phone_cardBean) == 0) {
				return false;
			}
		}
		return true;
	}

	public boolean savePhoneData(ResCardPhoneToClientMessage message, boolean boInsert) {
		Phone_cardBean phone_cardBean = new Phone_cardBean();
		phone_cardBean.setAccount(message.getAccount());
		phone_cardBean.setAgid(message.getAgid());
		phone_cardBean.setPhone(message.getPhone());
		phone_cardBean.setRoleid(message.getPlayerid());
		phone_cardBean.setType(message.getType());
		phone_cardBean.setVercode(message.getVercode());
		phone_cardBean.setPhonevernum(message.getPhonevernum());
		phone_cardBean.setVererrornum(message.getVererrornum());
		phone_cardBean.setPhonevertime(System.currentTimeMillis());
		return savePhoneData(phone_cardBean, boInsert);
	}

	public void reqInnerCardPhoneToWorld(ReqInnerCardPhoneToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			PlayerWorldInfo playerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(player.getId());
			if (playerWorldInfo != null) {
				if (message.getPhone().equalsIgnoreCase("-1")) {//查询手机
					ResCardPhoneToClientMessage sendMessage = getPhoneToClientMessage(playerWorldInfo);
					if (sendMessage == null) {//没有手机数据，就新建一个
						sendMessage = new ResCardPhoneToClientMessage();
						sendMessage.setAccount(playerWorldInfo.getAccount());
						sendMessage.setPlayerid(playerWorldInfo.getId());
						sendMessage.setPhone("");
						sendMessage.setVercode("");
						sendMessage.setStatus(PhoneStatus_NoBind);
					}
					MessageUtil.tell_player_message(player, sendMessage);
					log.error(String.format("查询手机[getId=%s,getAccount=%s,Status=%d,message=%s]", String.valueOf(playerWorldInfo.getId()), playerWorldInfo.getAccount(), PhoneStatus_NoBind, JSON.toJSONString(sendMessage)));
				} else {//要绑定或者修改手机
					ResCardPhoneToClientMessage sendMessage = getPhoneToClientMessage(playerWorldInfo);
					if (sendMessage == null) {//没有手机数据，就新建一个数据进行绑定
						sendMessage = new ResCardPhoneToClientMessage();
						sendMessage.setAccount(playerWorldInfo.getAccount());
						sendMessage.setPlayerid(playerWorldInfo.getId());
						sendMessage.setPhone(message.getPhone());
						sendMessage.setVercode("");
						sendMessage.setStatus(PhoneStatus_Bind);
						if (!savePhoneData(sendMessage, true)) {
							sendMessage.setErrorcode(PhoneError_Sql);
							sendMessage.setStatus(PhoneStatus_NoBind);
						}
					} else {
						if (!sendMessage.getPhone().equalsIgnoreCase(message.getPhone())) {
							sendMessage.setVererrornum(0);
						}
						sendMessage.setPhone(message.getPhone());
						sendMessage.setPhonevernum(sendMessage.getPhonevernum() + 1);
						sendMessage.setStatus(PhoneStatus_Bind);
						if (!savePhoneData(sendMessage, false)) {
							sendMessage.setErrorcode(PhoneError_Sql);
							sendMessage.setStatus(PhoneStatus_NoBind);
						}
					}
					MessageUtil.tell_player_message(player, sendMessage);
					log.error(String.format("绑定或者修改手机[getId=%s,getAccount=%s,Status=%d,getPhone=%s,message=%s]", String.valueOf(playerWorldInfo.getId()), playerWorldInfo.getAccount(), PhoneStatus_NoBind, message.getPhone(), JSON.toJSONString(sendMessage)));
				}
			} else {
				log.error(String.format("没有找到玩家WorldInfo 玩家ID[%s]", String.valueOf(message.getPlayerId())));
			}
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	public void resultHttp(IoSession ioSession, String result) throws Exception {
		String resultString = result;
		byte[] b = resultString.getBytes("UTF-8");
		IoBuffer out = IoBuffer.allocate(b.length);
		out.put(b);
		out.flip();
		if (ioSession != null) {
			ioSession.write(out);
		}
	}

	public static void main(String[] args) {
		//src37wan/laodao_37wan_check_phone.php?agent=haowan123&server=2&phone=13350065285&vercode=323444&account=shouyou608&dxgate=haowan123&idh=151947&idl=1615786010
		long idl = -1379726406;
		long idh = 151956;
//		System.out.println(idh << 32);
		long playerid = (((long)idh << 32) | (idl & 0x00000000ffffffffL));
		System.out.println(playerid);
		
		playerid = 652648965671866l;
		System.out.println(Long.toHexString(playerid));
		idl = (int)playerid;
		idh = (int)(playerid >> 32);
		System.out.println(idl);
		System.out.println(idh);
		System.out.println(Long.toHexString(idl));
		System.out.println(Long.toHexString(idh));
	}
	public void processHttpRequest(IoSession ioSession, HashMap<String, String> paramMap) throws Exception {
		int msgtype = Integer.valueOf(paramMap.get("msgtype"));
		switch (msgtype) {
			case 1: {
				//http send:{"msgid":"5","msgtype":"1","account":"shouyou636","idh":"151956","idl":"-1379726406","phone":"13350065285","agent":"haowan123","oneid":"2","vercode":"cDKVYR","vererrorcode":"0","argid":"24","type":"2"}
//				String account = paramMap.get("account");
				long idl = Long.valueOf(paramMap.get("idl"));
				long idh = Long.valueOf(paramMap.get("idh"));
				long playerid = (((long)idh << 32) | (idl & 0x00000000ffffffffL));
				String phone = paramMap.get("phone");
				int argid = Integer.valueOf(paramMap.get("argid"));
				int type = Integer.valueOf(paramMap.get("type"));
				String vercode = paramMap.get("vercode");
				int vererrorcode = Integer.valueOf(paramMap.get("vererrorcode"));
				PlayerWorldInfo playerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(playerid);
				Phone_cardBean phone_cardBean = getPhoneCardBean(playerWorldInfo);
				ResCardPhoneToClientMessage sendMessage = getPhoneToClientMessage(playerWorldInfo);
				int ret = 0;
				if (phone_cardBean != null) {
					if (phone_cardBean.getPhone().equalsIgnoreCase(phone)) {
						if (vererrorcode == 0) {
							if (phone_cardBean.getVercode().equalsIgnoreCase("-1")) {
								ret = PhoneError_NoRepeatReward;
							} else {
								phone_cardBean.setVercode(vercode);
								phone_cardBean.setAgid(argid);
								phone_cardBean.setType(type);
								phone_cardBean.setPhonevernum(0);
								phone_cardBean.setVererrornum(0);
								if (savePhoneData(phone_cardBean, false)) {
									ret = PhoneError_Success;
								} else {
									ret = PhoneError_Sql;
								}
							}
						} else {
							phone_cardBean.setVererrornum(phone_cardBean.getVererrornum() + 1);
							if (savePhoneData(phone_cardBean, false)) {
								ret = PhoneError_Fail;
							} else {
								ret = PhoneError_Sql;
							}
						}
					} else {
						ret = PhoneError_PhoneNoSame;
					}
				} else {
					ret = PhoneError_Sql;
				}
				resultHttp(ioSession, "" + ret);
				if (sendMessage != null) {
					sendMessage.setErrorcode((byte) ret);
					if (ret == PhoneError_Success) {
						sendMessage.setStatus(PhoneStatus_VerCode);
					} else {
						sendMessage.setStatus(PhoneStatus_Bind);
					}
					Player player = PlayerManager.getInstance().getPlayer(playerid);
					if (player != null) {
						MessageUtil.tell_player_message(player, sendMessage);
					}
				}
				log.error(String.format("手机验证msgtype=[%d],paramMap=[%s],phone_cardBean=[%s],ret=[%d],status=[%d]", msgtype, JSON.toJSONString(paramMap), phone_cardBean == null ? "phone_cardBean为NULL" : JSON.toJSONString(phone_cardBean), ret, sendMessage.getStatus()));
			}
			break;
			default: {
				log.error(String.format("未知的错误msgtype=[%d],paramMap=[%s]", msgtype, JSON.toJSONString(paramMap)));
			}
			break;
		}
	}
}
//		switch (agid) {
//			case AG_37wan: {
//				switch (type) {
//					default: {
//					}
//					break;
//				}
//			}
//			break;
//			case AG_17173: {
//				switch (type) {
//					default: {
//					}
//					break;
//				}
//			}
//			break;
//			case AG_07073: {
//				switch (type) {
//					default: {
//					}
//					break;
//				}
//			}
//			break;
//			case AG_265G: {
//				switch (type) {
//					default: {
//					}
//					break;
//				}
//			}
//			break;
//			case AG_sina: {
//				switch (type) {
//					default: {
//					}
//					break;
//				}
//			}
//			break;
//			case AG_duowan: {
//				switch (type) {
//					default: {
//					}
//					break;
//				}
//			}
//			break;
//			case AG_pufa: {
//				switch (type) {
//					default: {
//					}
//					break;
//				}
//			}
//			break;
//			case AG_ycshouji: {
//				switch (type) {
//					default: {
//					}
//					break;
//				}
//			}
//			break;
//		}
