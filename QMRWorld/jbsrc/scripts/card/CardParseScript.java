package scripts.card;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.zip.CRC32;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.util.Base64;

import com.alibaba.fastjson.JSON;
import com.game.allserverdb.bean.All_Card_dataBean;
import com.game.card.manager.CardManager;
import com.game.card.message.ReqInnerCardToWorldMessage;
import com.game.card.message.ResCardPhoneToClientMessage;
import com.game.card.message.ResInnerCardToServerMessage;
import com.game.card.script.ICardParseScript;
import com.game.data.manager.DataManager;
import com.game.db.bean.Card_dataBean;
import com.game.db.bean.Phone_cardBean;
import com.game.globaldb.bean.G_Card_dataBean;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerWorldInfo;
import com.game.prompt.structs.Notifys;
import com.game.script.manager.ScriptManager;
import com.game.script.structs.ScriptEnum;
import com.game.server.WorldServer;
import com.game.utils.CodedUtil;
import com.game.utils.MessageUtil;

/**
 * CDKEY解析脚本
 *
 * @author 杨鸿岚
 */
public class CardParseScript implements ICardParseScript {

	private Logger log = Logger.getLogger(CardParseScript.class);
	private byte Error_PhoneSuccess = 1;
	private byte Error_Success = 0;
	private byte Error_Fail = -1;		//验证失败
	private byte Error_UseAccount = -2;	//已经使用过的账号
//	private byte Error_UsePlayer = -3;	//已使用过的角色
//	private byte Error_UseCard = -4;	//已使用过的卡
	private byte Error_NoType = -5;		//没有这个类型的卡
	private byte Error_Sql = -6;		//数据库连接错误
	private byte Error_PhoneFail = -7;
	private String PassKeyAll = "8589dkkkmf1ljoewdnf3v9df9";		//all(新手卡，我们对所有运营平台提供-我们接口)
	private String PassKey = "34r98s)&j&*^*&asdOIUOAISUD7";			//all
	private String PassKey37wan = "37wan_58tfhjksdhfyahjsdgt374395kshfks";	//37wan
	private String PassKey360 = "3604fsodfj5t7y*&*kjshefl5f897WYEFUIH";	//360
	private String PassKeyPPS = "PPScard_i%^2IPlKm";			//PPS
//	private String PassKeyDuoWan = "DUOWAN10039kdnnnf200jnf1";		//duowan
	private String ArgName37wan = "37wan";
	private String ArgName360 = "360";
	private String ArgNamePPS = "pps";
//	private String ArgNameDuoWan = "duowan";

	@Override
	public int getId() {
		return ScriptEnum.CARDPARSE;
	}

	/**
	 * CDKEY解析
	 *
	 * @param player	玩家
	 * @param card	CDKEY
	 * @param argUserName	平台账号名
	 * @param argZoneName	平台区服名
	 * @param argName	平台名
	 * @return int
	 */
	@Override
	public int cardParse(Player player, String card, String argUserName, String argZoneName, String argName) {
		if (card == null) {
			card = "";
		}
		if (argUserName == null) {
			argUserName = "";
		}
		if (argZoneName == null || "".equals(argZoneName)) {
			argZoneName = "" + player.getServer();
			//argZoneName = "";
		}
		if (argName == null || "".equals(argName)) {
			argName = WorldServer.getInstance().getServerWeb();
		}
		int giftid = Error_Success;
		if (card.length() < 16) {
			giftid = checkCDKEY8(player, card, argName);
		} else {
			giftid = checkCDKEY16(player, card, argName);
		}
		if (giftid != Error_Fail) {
			CardManager.getInstance().setAgid_save((short) 0);
			return giftid;
		} else {
			if (card.length() < 16) {
				giftid = checkCDKEY8(player, card, "");
			} else {
				giftid = checkCDKEY16(player, card, "");
			}
		}
		if (giftid != Error_Fail) {
			CardManager.getInstance().setAgid_save((short) 0);
			return giftid;
		}
		if (argName.equalsIgnoreCase(ArgName360)) {
			giftid = check360(player, card, argUserName, argZoneName, argName);
		} else if (argName.equalsIgnoreCase(ArgNamePPS)) {
			giftid = checkPPS(player, card, argUserName, argZoneName, argName);
		} else {
			giftid = checkall(player, card, argUserName, argZoneName, argName);
		}
		if (giftid != Error_Fail) {
			CardManager.getInstance().setAgid_save((short) 0);
			return giftid;
		}
		giftid = Error_NoType;
		CardManager.getInstance().setAgid_save((short) 0);
		return giftid;
	}

	//------------------------------下面是解析函数-------------------------//
	public int checkCDKEY8(Player player, String Card, String argName) {
		int erroridandret = Error_Success;
		String cardString = Card.toLowerCase();
		long cardlong = Long.parseLong(cardString, 36);
		if ((cardlong & 0xFFFFFF0000000000L) != 0) {
			return Error_Fail;
		}
		short type = (short) (cardlong >> 32 & 0xff);
		int randnum = (int) (cardlong >> 16 & 0xffff);
		int crc32 = (int) (cardlong & 0xffff);
		long crc32long = ((long) crc32) & 0x000000000000FFFFL;
		//解密
		String usePassKey = getCardPassKey(argName);
		type = (short) ((short) ((type & 0xFF) ^ crc32long) & 0xFF);
		short crctype = type;
		if (type >= 128) {
			usePassKey = usePassKey + "_" + player.getServer();
			type = (short) (type & 0x7F);
		}
		//手机的8位CDKEY，type是平台id,50表示手机的类型
		CardManager.getInstance().setAgid_save(type);
		CardManager.getInstance().setType_save((short) 50);
		randnum = (int) ((long) ((randnum & 0x0000FFFF) ^ crc32long) & 0x0000FFFF);
		//解密
		String crc32str = String.format("%d%d%s", crctype, randnum, usePassKey);
		CRC32 crc32s = new CRC32();
		crc32s.update(crc32str.getBytes());
		long getnowCrc32 = crc32s.getValue();
		getnowCrc32 = (((getnowCrc32 >> 16 & 0x0000ffff) ^ (getnowCrc32 & 0x0000ffff)) & 0x0000ffff);
		if (crc32long == getnowCrc32) {//TODO 全区通用的卡的话，可能还需要一个全区共用的数据库服务器
			Card_dataBean card_dataBean = CardManager.getInstance().getOneCard_dataBean(player, Card, type, 50);
			erroridandret = CardManager.getInstance().checkCardData(card_dataBean);
			if (erroridandret == Error_Success) {
				int giftid = CardManager.getInstance().getGiftId((short) type, (short) 50);
				if (giftid != 0) {
					if (CardManager.getInstance().getCard_dataDao().insert(card_dataBean) == 0) {
						erroridandret = Error_Sql;
					} else {
						erroridandret = giftid;
					}
				} else {
					erroridandret = Error_NoType;
				}
			}
		} else {
			erroridandret = Error_Fail;
		}
		System.out.println(String.format("%s==%d==%d==%s==%d", Card, crctype, randnum, argName,erroridandret));
		return erroridandret;
	}

	public int checkCDKEY16(Player player, String Card, String argName) {
		int erroridandret = Error_Success;
		if (Card.length() > 16) {
			return Error_Fail;
		}
		Base64 base64 = new Base64();
		IoBuffer buf = IoBuffer.allocate(255);
		buf.put(base64.decode(Card.getBytes()));
		buf.flip();
		short agid = buf.getUnsigned();
		CardManager.getInstance().setAgid_save(agid);
		short type = buf.getUnsigned();
		long nowtime = buf.getUnsignedInt();
		int randnum = buf.getUnsignedShort();
		int crc32 = buf.getInt();
		if (buf.hasRemaining()) {
			return Error_Fail;
		}
		long crc32long = ((long) crc32) & 0x00000000FFFFFFFFL;
		//解密
		String usePassKey = getCardPassKey(argName);
		type = (short) ((short) ((type & 0xFF) ^ crc32long) & 0xFF);
		short crctype = type;
		if (type >= 128) {
			usePassKey = usePassKey + "_" + player.getServer();
			type = (short) (type & 0x7F);
		}
		CardManager.getInstance().setType_save(type);
		nowtime = (((nowtime & 0x00000000FFFFFFFFL) ^ crc32long) & 0x00000000FFFFFFFFL);
		//解密
		String crc32str = String.format("%d%d%d%d%s", agid, crctype, nowtime, randnum, usePassKey);
		CRC32 crc32s = new CRC32();
		crc32s.update(crc32str.getBytes());
		long getnowCrc32 = crc32s.getValue();
		if (crc32long == getnowCrc32) {//TODO 全区通用的卡的话，可能还需要一个全区共用的数据库服务器
			switch (type) {
				case 6://现在6是单平台全区数据库检查
				case 8:
				{
					G_Card_dataBean g_Card_dataBean = CardManager.getInstance().getOneG_Card_dataBean(player, Card, agid, type);
					erroridandret = CardManager.getInstance().checkCardData(g_Card_dataBean);
					if (erroridandret == Error_Success) {
						int giftid = CardManager.getInstance().getGiftId(agid, type);
						if (giftid != 0) {
							if (CardManager.getInstance().getG_Card_dataDao().insert(g_Card_dataBean) == 0) {
								erroridandret = Error_Sql;
							} else {
								erroridandret = giftid;
							}
						} else {
							erroridandret = Error_NoType;
						}
					}
				}
				break;
				case 7://现在7是全平台全区数据库检查
				case 9:
				{
					All_Card_dataBean all_Card_dataBean = CardManager.getInstance().getOneAll_Card_dataBean(player, Card, agid, type);
					erroridandret = CardManager.getInstance().checkCardData(all_Card_dataBean);
					if (erroridandret == Error_Success) {
						int giftid = CardManager.getInstance().getGiftId(agid, type);
						if (giftid != 0) {
							if (CardManager.getInstance().getAll_Card_dataDao().insert(all_Card_dataBean) == 0) {
								erroridandret = Error_Sql;
							} else {
								erroridandret = giftid;
							}
						} else {
							erroridandret = Error_NoType;
						}
					}
				}
				break;
				default:
				{
					Card_dataBean card_dataBean = CardManager.getInstance().getOneCard_dataBean(player, Card, agid, type);
					erroridandret = CardManager.getInstance().checkCardData(card_dataBean);
					if (erroridandret == Error_Success) {
						int giftid = CardManager.getInstance().getGiftId(agid, type);
						if (giftid != 0) {
							if (CardManager.getInstance().getCard_dataDao().insert(card_dataBean) == 0) {
								erroridandret = Error_Sql;
							} else {
								erroridandret = giftid;
							}
						} else {
							erroridandret = Error_NoType;
						}
					}
				}
				break;
			}
		} else {
			erroridandret = Error_Fail;
		}
		System.out.println(String.format("%s==%d==%d==%d==%d==%s==%d", Card, agid, crctype, nowtime, randnum, argName,erroridandret));
		return erroridandret;
	}

	/**
	 * 全平台通用-CDKEY解析
	 *
	 * @param player	玩家
	 * @param card	CDKEY
	 * @param argUserName	平台账号名
	 * @param argZoneName	平台区服名
	 * @param argName	平台名
	 * @return int
	 */
	public int checkall(Player player, String card, String argUserName, String argZoneName, String argName) {
		int erroridandret = Error_Success;

		String md5Str = String.format("%s%s%s%s", argUserName, argName, argZoneName, md5(argName+PassKeyAll).toLowerCase());
		md5Str = md5(md5Str);
		md5Str = md5Str.toUpperCase();

		CardManager.getInstance().setAgid_save(getCardArgId(argName));
		CardManager.getInstance().setType_save(getCardType(argName));
		if (md5Str.equalsIgnoreCase(card)) {//TODO 全区通用的卡的话，可能还需要一个全区共用的数据库服务器
			Card_dataBean card_dataBean = CardManager.getInstance().getOneCard_dataBean(player, card, getCardArgId(argName), getCardType(argName));
			erroridandret = CardManager.getInstance().checkCardData(card_dataBean);
			if (erroridandret == Error_Success) {
				int giftid = CardManager.getInstance().getGiftId(getCardArgId(argName), getCardType(argName));
				if (giftid != 0) {
					if (CardManager.getInstance().getCard_dataDao().insert(card_dataBean) == 0) {
						erroridandret = Error_Sql;
					} else {
						erroridandret = giftid;
					}
				} else {
					erroridandret = Error_NoType;
				}
			}
		} else {
			erroridandret = Error_Fail;
		}
		System.out.println(String.format("全平台通用-CDKEY解析==[card=%s,%s==%s==%s,md5=%s]==ret=%d", card, argUserName, argName, argZoneName, md5Str, erroridandret));
		return erroridandret;
	}
	
	/**
	 * 360-CDKEY解析
	 *
	 * @param player	玩家
	 * @param card	CDKEY
	 * @param argUserName	平台账号名
	 * @param argZoneName	平台区服名
	 * @param argName	平台名
	 * @return int
	 */
	public int check360(Player player, String card, String argUserName, String argZoneName, String argName) {
		int erroridandret = Error_Success;
		
		if(card.length() > 32){
            try {
                //System.out.println(String.format("%s", card));
                
                String b64cardtype = card.substring(32);
                String cardtype=CodedUtil.decodeBase64(b64cardtype);
                String newcard=card.substring(0,32);
                String[] cardtypeinfo=cardtype.split(":");
                
                System.out.println(String.format("%s  ===  %s  ===  %s  ===  %s", card, b64cardtype, cardtype, newcard));
                
                String md5Str = String.format("%s%s%s%s%s", argUserName, argName, argZoneName, PassKey360, cardtype);
                //System.out.println(md5Str);
                md5Str = md5(md5Str);
                md5Str = md5Str.toUpperCase();
                //System.out.println(md5Str);
                
                String[] info = cardtypeinfo[0].split("_");
                short argId = Short.parseShort(info[0]);
                short typeId = Short.parseShort(info[1]);
                CardManager.getInstance().setAgid_save(argId);
                CardManager.getInstance().setType_save(typeId);
                if (md5Str.equalsIgnoreCase(newcard)) {//TODO 全区通用的卡的话，可能还需要一个全区共用的数据库服务器
                        Card_dataBean card_dataBean = CardManager.getInstance().getOneCard_dataBean(player, card, argId, typeId);
                        erroridandret = CardManager.getInstance().checkCardData(card_dataBean);
                        if (erroridandret == Error_Success) {
                                int giftid = CardManager.getInstance().getGiftId(argId, typeId);
                                if (giftid != 0) {
                                        if (CardManager.getInstance().getCard_dataDao().insert(card_dataBean) == 0) {
                                                erroridandret = Error_Sql;
                                        } else {
                                                erroridandret = giftid;
                                        }
                                } else {
                                        erroridandret = Error_NoType;
                                }
                        }
                } else {
                        erroridandret = Error_Fail;
                }
                System.out.println(String.format("%s==%s==%s==%d(new)", card, argUserName, "S" + argZoneName, erroridandret));
            } catch (Exception ex) {
                System.out.println(ex.toString());
                erroridandret = Error_Fail;
            }
		}else{
			String md5Str = String.format("%s%s%s", argUserName, "S" + argZoneName, PassKey360);
			md5Str = md5(md5Str);
			md5Str = md5Str.toUpperCase();
			short argId = getCardArgId(argName);
			short typeId = getCardType(argName);
			CardManager.getInstance().setAgid_save(argId);
			CardManager.getInstance().setType_save(typeId);
			if (md5Str.equalsIgnoreCase(card)) {//TODO 全区通用的卡的话，可能还需要一个全区共用的数据库服务器
				Card_dataBean card_dataBean = CardManager.getInstance().getOneCard_dataBean(player, card, argId, typeId);
				erroridandret = CardManager.getInstance().checkCardData(card_dataBean);
				if (erroridandret == Error_Success) {
					int giftid = CardManager.getInstance().getGiftId(argId, typeId);
					if (giftid != 0) {
						if (CardManager.getInstance().getCard_dataDao().insert(card_dataBean) == 0) {
							erroridandret = Error_Sql;
						} else {
							erroridandret = giftid;
						}
					} else {
						erroridandret = Error_NoType;
					}
				}
			} else {
				erroridandret = Error_Fail;
			}
			System.out.println(String.format("%s==%s==%s==%d(old)", card, argUserName, "S" + argZoneName, erroridandret));
		}
		return erroridandret;
	}

	/**
	 * PPS-CDKEY解析
	 *
	 * @param player	玩家
	 * @param card	CDKEY
	 * @param argUserName	平台账号名
	 * @param argZoneName	平台区服名
	 * @param argName	平台名
	 * @return int
	 * @see PPS 固定游戏标识 PassKeyPPS
	 */
	public int checkPPS(Player player, String card, String argUserName, String argZoneName, String argName) {
		int erroridandret = Error_Success;

		String md5Str = String.format("%s%s%s", argUserName, argZoneName, PassKeyPPS);
		md5Str = md5(md5Str);
		md5Str = md5Str.toUpperCase();

		CardManager.getInstance().setAgid_save(getCardArgId(argName));
		CardManager.getInstance().setType_save(getCardType(argName));
		if (md5Str.equalsIgnoreCase(card)) {//TODO 全区通用的卡的话，可能还需要一个全区共用的数据库服务器
			Card_dataBean card_dataBean = CardManager.getInstance().getOneCard_dataBean(player, card, getCardArgId(argName), getCardType(argName));
			erroridandret = CardManager.getInstance().checkCardData(card_dataBean);
			if (erroridandret == Error_Success) {
				int giftid = CardManager.getInstance().getGiftId(getCardArgId(argName), getCardType(argName));
				if (giftid != 0) {
					if (CardManager.getInstance().getCard_dataDao().insert(card_dataBean) == 0) {
						erroridandret = Error_Sql;
					} else {
						erroridandret = giftid;
					}
				} else {
					erroridandret = Error_NoType;
				}
			}
		} else {
			erroridandret = Error_Fail;
		}
		System.out.println(String.format("%s==%s==%s==%d", card, argUserName, argZoneName, erroridandret));
		return erroridandret;
	}

	//------------------------------上面是解析函数-------------------------//
	// MD5验证
	protected String md5(String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(str.getBytes("UTF-8"));
			StringBuilder ret = new StringBuilder(bytes.length << 1);
			for (int i = 0; i < bytes.length; i++) {
				ret.append(Character.forDigit((bytes[i] >> 4) & 0xf, 16));
				ret.append(Character.forDigit(bytes[i] & 0xf, 16));
			}
			return ret.toString();
		} catch (Exception e) {
			//log.error(e, e);
		}
		return null;
	}

	public String getCardPassKey(String argName) {
		if (argName.equalsIgnoreCase(ArgName37wan)) {
			return PassKey37wan;
		} else if (argName.equalsIgnoreCase(ArgName360)) {
			return PassKey360;
		} else if (argName.equalsIgnoreCase(ArgNamePPS)) {
			return PassKeyPPS;
		} else {
			if(argName.equalsIgnoreCase("")){
				return PassKey;
			}else{
				return PassKey+argName;
			}
		}
	}

	public short getCardArgId(String argName) {
		if (argName.equalsIgnoreCase(ArgName37wan)) {
			return 10;
		} else if (argName.equalsIgnoreCase(ArgName360)) {
			return 21;
		} else if (argName.equalsIgnoreCase(ArgNamePPS)) {
			return 20;
		} else {
			return 10;
		}
	}

	public short getCardType(String argName) {
		if (argName.equalsIgnoreCase(ArgName37wan)) {
			return 2;
		} else if (argName.equalsIgnoreCase(ArgName360)) {
			return 2;
		} else if (argName.equalsIgnoreCase(ArgNamePPS)) {
			return 2;
		} else {
			return 2;
		}
	}

	//---------------------------下面HTTP方面的处理------------------------//
	/**
	 * HTTP发来的CDKEY进行处理(可以通用，现在多玩使用这个)
	 *
	 * @param ioSession http链接
	 * @param paramMap 参数Map
	 * @return
	 */
	@Override
	public void httpCardParse(IoSession ioSession, HashMap<String, String> paramMap) {
//	  'msgid' => "7", //后端http消息接口
//        "msgtype" => "1",
//        "src_card" => "" . $cardnum, //多玩卡号
//        "src_card_type" => "" . $cardtype, //多玩卡类型
//        "card" => $cardnum, //游戏服务器用于记录的卡号
//        "carditemstype" => 1, //卡奖励物品ID
//        "type" => 1, //卡使用类型
//        "account" => "" . $account, //账号
//        "playername" => "", //角色名
//        "idh" => "" . $idh,
//        "idl" => "" . $idl,
//        "zoneid" => "" . $zoneid, //区号
//        "agent" => $agent, //平台名
//        "errorid" => "0", //==0 成功            //错误ID
		if (paramMap.containsKey("msgtype")) {
			int msgtype = Integer.valueOf(paramMap.get("msgtype"));
			switch (msgtype) {
				case 1: {
					try {
//						String src_card = paramMap.get("src_card");
//						String src_card_type = paramMap.get("src_card_type");
						String card = paramMap.get("card");
						int carditemstype = Integer.valueOf(paramMap.get("carditemstype"));//argid
//						int argid = Integer.valueOf(paramMap.get("argid"));
						int type = Integer.valueOf(paramMap.get("type"));
//						String account = paramMap.get("account");
						String playername = paramMap.get("playername");
//						long idl = Long.valueOf(paramMap.get("idl"));
//						long idh = Long.valueOf(paramMap.get("idh"));
//						long playerid = ((idh << 32) | idl);
//						int zoneid = Integer.valueOf(paramMap.get("zoneid"));
//						String agent = paramMap.get("agent");
//						int errorid = Integer.valueOf(paramMap.get("errorid"));
						Player player = PlayerManager.getInstance().getOnlinePlayerByName(playername);
//						Player player = PlayerManager.getInstance().getPlayer(playerid);
						if (player == null) {
							CardManager.getInstance().resultHttp(ioSession, "" + Error_Fail);
							return;
						}
						int erroridandret = Error_Success;
						Card_dataBean card_dataBean = CardManager.getInstance().getOneCard_dataBean(player, card, carditemstype, type);
						erroridandret = CardManager.getInstance().checkCardData(card_dataBean);
						if (erroridandret == Error_Success) {
							int giftid = CardManager.getInstance().getGiftId((short) carditemstype, (short) type);
							if (giftid != 0) {
								if (CardManager.getInstance().getCard_dataDao().insert(card_dataBean) == 0) {
									erroridandret = Error_Sql;
								} else {
									erroridandret = giftid;
								}
							} else {
								erroridandret = Error_NoType;
							}
						}
						CardManager.getInstance().resultHttp(ioSession, "" + (erroridandret >= Error_Success ? Error_Success : Error_Fail));
						if (erroridandret > 0) {
							ResInnerCardToServerMessage sendMessage = new ResInnerCardToServerMessage();
							sendMessage.setErrorcode(Error_Success);
							sendMessage.setPlayerId(player.getId());
							sendMessage.setAgid(carditemstype);
							sendMessage.setType(type);
							sendMessage.setGiftid(erroridandret);
							MessageUtil.send_to_game(player, sendMessage);
						} else {
							ResInnerCardToServerMessage sendMessage = new ResInnerCardToServerMessage();
							sendMessage.setErrorcode((byte) erroridandret);
							sendMessage.setPlayerId(player.getId());
							sendMessage.setAgid(carditemstype);
							sendMessage.setType(type);
							sendMessage.setGiftid(0);
							MessageUtil.send_to_game(player, sendMessage);
						}
						CardManager.getInstance().setCardLog(player.getId(), carditemstype, type, player.getServer(), erroridandret, card,player.getServer());
					} catch (Exception e) {
						log.error(e.getMessage());
					}
				}
				break;
				default: {
					log.error(String.format("未知的错误msgtype=[%d],paramMap=[%s]", msgtype, JSON.toJSONString(paramMap)));
				}
				break;
			}
		}else{
			log.error(String.format("错误:msgtype不能解析,paramMap=[%s]", JSON.toJSONString(paramMap)));
		}
	}

	public void worldreload(List<Object> param) {
		final long roleID = Long.parseLong(param.get(0).toString());
		final String tableName = param.get(1).toString();
		log.info("接到重新load" + tableName + "指令");

		try {
			Player player = PlayerManager.getInstance().getPlayer(roleID);
			if (tableName != null && !tableName.equals("")) {
				Field declaredField = DataManager.getInstance().getClass().getDeclaredField(tableName + "Container");
				int hashCode = declaredField.get(DataManager.getInstance()).hashCode();
				Class<?> cls = declaredField.getType();
				Object newInstance = cls.newInstance();
				Method method = cls.getMethod("load");
				method.invoke(newInstance);
				declaredField.set(DataManager.getInstance(), newInstance);
				int hashCode2 = declaredField.get(DataManager.getInstance()).hashCode();
				if (hashCode != hashCode2) {
					log.info("reload " + tableName + " end");
				} else {
					log.info("reload " + tableName + " is faild" + hashCode + " " + hashCode2);
				}
				if (player != null) {
					MessageUtil.notify_player(player, Notifys.SUCCESS, "重加载表{1}成功", tableName);
				}

			} else {
				Field[] declaredFields = DataManager.getInstance().getClass().getDeclaredFields();
				for (Field field : declaredFields) {
					int hashCode = field.get(DataManager.getInstance()).hashCode();
					Class<?> cls = field.getType();
					Object newInstance = cls.newInstance();
					Method method = cls.getMethod("load");
					method.invoke(newInstance);
					field.set(DataManager.getInstance(), newInstance);
					int hashCode2 = field.get(DataManager.getInstance()).hashCode();
					if (hashCode != hashCode2) {
						log.info("reload " + tableName + " end");
					} else {
						log.info("reload " + tableName + " is faild" + hashCode + " " + hashCode2);
					}
					if (player != null) {
						MessageUtil.notify_player(player, Notifys.SUCCESS, "重加载所有表成功", tableName);
					}
				}
			}

		} catch (SecurityException e) {
			log.error("run()", e);
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			log.error("run()", e);
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			log.error("run()", e);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			log.error("run()", e);
			e.printStackTrace();
		} catch (InstantiationException e) {
			log.error("run()", e);
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			log.error("run()", e);
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			log.error("run()", e);
			e.printStackTrace();
		}



		if (log.isDebugEnabled()) {
			log.debug("reLoad(String) - end");
		}
	}

	public void testsourcecode(List<Object> param) {
		long roleID = Long.parseLong(param.get(0).toString());
		long destid = Long.parseLong(param.get(1).toString());
		long type = Long.parseLong(param.get(2).toString());
		Player player = PlayerManager.getInstance().getPlayer(roleID);
		Player destplayer = PlayerManager.getInstance().getPlayer(destid);
		if (destplayer != null) {
			if (type == 1) {
				destplayer.getAwardParamMap().put("测试代码", "1");
			} else {
				destplayer.getAwardParamMap().remove("测试代码");
			}
		}
		if (player != null) {
			MessageUtil.notify_player(player, Notifys.SUCCESS, "加载测试代码成功");
		}
	}
	
	public void runhttp(List<Object> param) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("msgtype", "1");
		map.put("playername", "[1区]令狐阳云");
		map.put("carditemstype", "21");
		map.put("type", "2");
		map.put("card", "jdkfjdkfjsldkf");
		httpCardParse(null, map);
	}
	
	public void phonecard(List<Object> param){
		if (param.isEmpty()) {
			log.error("phonecard参数为空");
			return;
		}
		String toString = param.get(0).toString();
		if (toString == null || toString.isEmpty()) {
			log.error("phonecard参数无法读取");
			return;
		}
		ReqInnerCardToWorldMessage message = JSON.parseObject(toString, ReqInnerCardToWorldMessage.class);
		if (message == null) {
			log.error("phonecard参数解析出错");
			return;
		}
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			int giftid = Error_Fail;
			boolean boPhoneVer = false;
			if (message.getCard().equalsIgnoreCase("-1")) {
				giftid = Error_PhoneFail;
				boPhoneVer = true;
				PlayerWorldInfo playerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(player.getId());
				Phone_cardBean phone_cardBean = CardManager.getInstance().getPhoneCardBean(playerWorldInfo);
				if (phone_cardBean != null) {
					if (!phone_cardBean.getVercode().equalsIgnoreCase("-1") && !phone_cardBean.getVercode().equalsIgnoreCase("") && !phone_cardBean.getPhone().equalsIgnoreCase("")) {
						CardManager.getInstance().setAgid_save((short) phone_cardBean.getAgid());
						CardManager.getInstance().setType_save((short) phone_cardBean.getType());
						giftid = CardManager.getInstance().getGiftId((short) phone_cardBean.getAgid(), (short) phone_cardBean.getType());
						if (giftid != 0) {
							phone_cardBean.setVercode("-1");
							if (!CardManager.getInstance().savePhoneData(phone_cardBean, false)) {
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
				ResCardPhoneToClientMessage sendMessage = CardManager.getInstance().getPhoneToClientMessage(playerWorldInfo);
				if (sendMessage != null) {
					if (giftid == Error_Sql) {
						sendMessage.setErrorcode((byte)-1);//PhoneError_Sql=-1
					}
					MessageUtil.tell_player_message(player, sendMessage);
				}
				if (giftid < 0) {
					giftid = Error_PhoneFail;
				}
				log.error(String.format("领取手机验证奖励giftid=[%d],phone_cardBean=[%s],sendMessage=[%s]", giftid, phone_cardBean==null?"phone_cardBean为NULL":JSON.toJSONString(phone_cardBean), JSON.toJSONString(sendMessage)));
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
				sendMessage.setAgid(CardManager.getInstance().getAgid_save());
				sendMessage.setType(CardManager.getInstance().getType_save());
				sendMessage.setGiftid(giftid);
				MessageUtil.send_to_game(player, sendMessage);
			} else {
				ResInnerCardToServerMessage sendMessage = new ResInnerCardToServerMessage();
				sendMessage.setErrorcode((byte) giftid);
				sendMessage.setPlayerId(player.getId());
				sendMessage.setAgid(CardManager.getInstance().getAgid_save());
				sendMessage.setType(CardManager.getInstance().getType_save());
				sendMessage.setGiftid(0);
				MessageUtil.send_to_game(player, sendMessage);
			}
			CardManager.getInstance().setCardLog(player.getId(), CardManager.getInstance().getAgid_save(), CardManager.getInstance().getType_save(), player.getServer(), giftid, message.getCard(),player.getCreateServer());
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}
	
//	public static void main(String[] args) {
//		CardParseScript cardParseScript = new CardParseScript();
//		cardParseScript.checkCDKEY16(null, "MRkxUH9xj1dhmDMc1151m4456xqg", "360");
//		System.out.println("success");
//	}
}
