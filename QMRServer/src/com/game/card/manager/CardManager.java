package com.game.card.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.card.message.ReqCardPhoneToServerMessage;
import com.game.card.message.ReqCardToServerMessage;
import com.game.card.message.ReqInnerCardPhoneToWorldMessage;
import com.game.card.message.ReqInnerCardToWorldMessage;
import com.game.card.message.ResCardToClientMessage;
import com.game.card.message.ResInnerCardToServerMessage;
import com.game.config.Config;
import com.game.languageres.manager.ResManager;
import com.game.mail.manager.MailServerManager;
import com.game.pet.manager.PetOptManager;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;

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
	//错误代码
	private final byte Error_PhoneSuccess = 1;
	private final byte Error_Success = 0;
	private final byte Error_Fail = -1;		//验证失败
	private final byte Error_UseAccount = -2;	//已经使用过的账号
	private final byte Error_UsePlayer = -3;	//已使用过的角色
	private final byte Error_UseCard = -4;		//已使用过的卡
	private final byte Error_NoType = -5;		//没有这个类型的卡
	private final byte Error_Sql = -6;		//数据库连接错误
	private final byte Error_PhoneFail = -7;
	
	public void reqCardToServer(Player player, ReqCardToServerMessage message) {
		if (message.getCard() == null || message.getCard().equals("")) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("CDKEY为空，请重新确认填写的CDKEY。"));
			return;
		}
		//只有91渠道能使用的新手卡
		if(message.getCard().indexOf("_")!=-1){
			if(player.getAgentColdatas()==null || !message.getCard().startsWith(player.getAgentColdatas() + "_")){
				return;
			}
			message.setCard(message.getCard().replace(player.getAgentColdatas() + "_", ""));
		}
		if (player != null) {
			ReqInnerCardToWorldMessage sendMessage = new ReqInnerCardToWorldMessage();
			sendMessage.setPlayerId(player.getId());
			sendMessage.setCard(message.getCard());
			sendMessage.setArguserName(player.getUserName());
			sendMessage.setArgzoneName(String.valueOf(player.getCreateServerId()));
			sendMessage.setArgName(player.getWebName());
			MessageUtil.send_to_world(sendMessage);
		}
	}

	public void resInnerCardToServer(Player player, ResInnerCardToServerMessage message) {
		if (player != null) {
			switch (message.getErrorcode()) {
				case Error_PhoneSuccess:
				case Error_Success: {
					if (message.getGiftid() >= 100000) {
						int petid = message.getGiftid() % 100000;
						if (petid != 0) {
							PetOptManager.getInstance().addPet(player, petid, "手机验证领取侍宠", Config.getId());
							MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您已经通过手机验证获得了新的美人。"));
						}else{
							log.error(String.format("Card数据库配置错误,手机验证奖励侍宠giftid==%d", message.getGiftid()));
						}
					} else {
						if (BackpackManager.getInstance().getEmptyGridNum(player) >= 1) {
							List<Item> items = Item.createItems(message.getGiftid(), 1, true, 0);
							if (!items.isEmpty()) {
								long action = Config.getId();
								BackpackManager.getInstance().addItems(player, items, Reasons.CARD_GIFT, action);
							}
						} else {
							sendMailByCard(player.getId(), player.getName(), message.getGiftid());
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于您的包裹已满，所以您的礼包未能正常发放，请去邮件中领取。"));
							MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("由于您的包裹已满，所以您的礼包未能正常发放，请去邮件中领取。"));
						}
					}
					if (message.getErrorcode() == Error_PhoneSuccess) {
						return;
					}
				}
				break;
				case Error_Fail: {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该CDKEY是错误的CDKEY，验证失败。"));
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("该CDKEY是错误的CDKEY，验证失败。"));
				}
				break;
				case Error_UseAccount: {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该账号已使用过这种类型的CDKEY，不能重复使用。"));
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("该账号已使用过这种类型的CDKEY，不能重复使用。"));
				}
				break;
				case Error_UsePlayer: {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该角色已使用过这种类型的CDKEY，不能重复使用。"));
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("该角色已使用过这种类型的CDKEY，不能重复使用。"));
				}
				break;
				case Error_UseCard: {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该CDKEY是已使用过的，不能重复使用。"));
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("该CDKEY是已使用过的，不能重复使用。"));
				}
				break;
				case Error_NoType: {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("没有这个类型的CDKEY，不能使用。"));
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("没有这个类型的CDKEY，不能使用。"));
				}
				break;
				case Error_Sql: {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("CDKEY数据库错误，不能使用。"));
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("CDKEY数据库错误，不能使用。"));
				}
				break;
				case Error_PhoneFail: {
					return;
				}
			}
			ResCardToClientMessage sendMessage = new ResCardToClientMessage();
			sendMessage.setErrorcode(message.getErrorcode());
			sendMessage.setAgid(message.getAgid());
			sendMessage.setType(message.getType());
			sendMessage.setGiftid(message.getGiftid());
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			if (message.getErrorcode() == Error_Success || message.getErrorcode() == Error_PhoneSuccess) {
				sendMailByCard(message.getPlayerId(), "", message.getGiftid());
			}
		}
	}

	public void sendMailByCard(long receiverid, String receivername, int itemid) {
		if (itemid != 0) {
			List<Item> items = Item.createItems(itemid, 1, true, 0);
			if (!items.isEmpty()) {
				MailServerManager.getInstance().sendSystemMail(receiverid, receivername, ResManager.getInstance().getString("系统邮件"), ResManager.getInstance().getString("由于包裹满，或者其他原因，您的礼包未能正常发放，转发为系统邮件，请点击附件领取。"), (byte) 1, 0, items);
			}
		}
	}

	//------------------------手机验证----------------------------//
	public void reqCardPhoneToServer(Player player, ReqCardPhoneToServerMessage message) {
		if (message.getAccount() == null || message.getAccount().equals("")) {
			message.setAccount("");
		}
		if (message.getPhone() == null || message.getPhone().equals("")) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("手机号码为空，请重新确认填写的手机号码。"));
			return;
		}
		if (player != null) {
			ReqInnerCardPhoneToWorldMessage sendMessage = new ReqInnerCardPhoneToWorldMessage();
			sendMessage.setPlayerId(player.getId());
			sendMessage.setAccount(message.getAccount());
			sendMessage.setPhone(message.getPhone());
			sendMessage.setPlayerid(player.getId());
			MessageUtil.send_to_world(sendMessage);
			log.error(String.format("getId=%s,getAccount=%s,getPhone=%s", String.valueOf(player.getId()), message.getAccount(), message.getPhone()));
		}
	}
}
