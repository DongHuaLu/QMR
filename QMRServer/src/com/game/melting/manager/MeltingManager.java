package com.game.melting.manager;

import com.alibaba.fastjson.JSON;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Attribute;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_equip_appendBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_meting_randomBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.equip.manager.EquipManager;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.mail.manager.MailServerManager;
import com.game.melting.log.MeltingLog;
import com.game.melting.message.ReqMeltingItemToServerMessage;
import com.game.melting.message.ResMeltingItemToClientMessage;
import com.game.player.manager.PlayerAttributeManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.structs.Attributes;
import com.game.structs.Reasons;
import com.game.utils.CommonConfig;
import com.game.utils.Global;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * 熔炼系统
 *
 * @author 杨鸿岚
 */
public class MeltingManager {

	private Logger log = Logger.getLogger(MeltingManager.class);
	private static Object obj = new Object();
	//管理类实例
	private static MeltingManager manager;

	private MeltingManager() {
	}

	public static MeltingManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new MeltingManager();
			}
		}
		return manager;
	}
	//----------------------------成员变量------------------------------//
	private static int DEF_MELTING_LV = 60;		//默认可兑换熔炼石装备等级
	private static int DEF_MELTING_QUALITY = 4;	//默认可兑换熔炼石装备品质
	private static int DEF_MELTINGID = 17000;	//默认最小熔炼石id
	private static int DEF_MELTINGID_MAX = 17100;	//默认最大熔炼石id
	private static final int ATTACK_TYPE = 0;
	private static final int ATTACKSPEED_TYPE = 1;
	private static final int DEFENSE_TYPE = 2;
	private static final int CRIT_TYPE = 3;
	private static final int DODGE_TYPE = 4;
	private static final int SPEED_TYPE = 5;
	private static final int MAXHP_TYPE = 6;
	private static final int MAXMP_TYPE = 7;
	private static final int MAXSP_TYPE = 8;
	private static final int LUCK_TYPE = 9;

	//----------------------------成员函数------------------------------//
	/**
	 * 根据装备等级和属性类型得到熔炼石物品id
	 *
	 * @return int
	 */
	public int getMeltingItemId(int equiplv, int attrtype) {
		int result = DEF_MELTINGID;
		result = result + attrtype * 10;
		switch (equiplv) {
			case 60:
				result = result + 0;
				break;
			case 80:
				result = result + 1;
				break;
			case 100:
				result = result + 2;
				break;
			default:
				result = 0;
				break;
		}
		if (result >= DEF_MELTINGID_MAX || result < DEF_MELTINGID) {
			result = 0;
		}
		return result;
	}

	/**
	 * 根据熔炼石物品id得到装备等级
	 *
	 * @return int
	 */
	public int getEquipLv(int meltingitemid) {
		if (meltingitemid >= DEF_MELTINGID_MAX || meltingitemid < DEF_MELTINGID) {
			return 0;
		}
		int result = 0;
		int idx = meltingitemid % DEF_MELTINGID;
		idx = idx % 10;
		switch (idx) {
			case 0:
				result = 60;
				break;
			case 1:
				result = 80;
				break;
			case 2:
				result = 100;
				break;
			default:
				result = 0;
				break;
		}
		return result;
	}

	/**
	 * 根据熔炼石物品id得到属性类型
	 *
	 * @return int
	 */
	public int getAttrType(int meltingitemid) {
		if (meltingitemid >= DEF_MELTINGID_MAX || meltingitemid < DEF_MELTINGID) {
			return -1;
		}
		int result = 0;
		int idx = meltingitemid % DEF_MELTINGID;
		result = idx / 10;
		return result;
	}

	/**
	 * 根据属性类型得到真实属性类型
	 *
	 * @return int
	 */
	public int getTrueAttrType(int attrtype) {
		int result = 0;
		switch (attrtype) {
			case ATTACK_TYPE:
				result = Attributes.ATTACK.getValue();
				break;
			case ATTACKSPEED_TYPE:
				result = Attributes.ATTACKSPEED.getValue();
				break;
			case DEFENSE_TYPE:
				result = Attributes.DEFENSE.getValue();
				break;
			case CRIT_TYPE:
				result = Attributes.CRIT.getValue();
				break;
			case DODGE_TYPE:
				result = Attributes.DODGE.getValue();
				break;
			case SPEED_TYPE:
				result = Attributes.SPEED.getValue();
				break;
			case MAXHP_TYPE:
				result = Attributes.MAXHP.getValue();
				break;
			case MAXMP_TYPE:
				result = Attributes.MAXMP.getValue();
				break;
			case MAXSP_TYPE:
				result = Attributes.MAXSP.getValue();
				break;
			case LUCK_TYPE:
				result = Attributes.LUCK.getValue();
				break;
			default:
				result = 0;
				break;
		}
		return result;
	}

	/**
	 * 根据真实属性类型得到属性类型
	 *
	 * @return int
	 */
	public int getFalseAttrType(int trueattrtype) {
		int result = 0;
		if (trueattrtype == Attributes.ATTACK.getValue()) {
			result = ATTACK_TYPE;
		} else if (trueattrtype == Attributes.ATTACKSPEED.getValue()) {
			result = ATTACKSPEED_TYPE;
		} else if (trueattrtype == Attributes.DEFENSE.getValue()) {
			result = DEFENSE_TYPE;
		} else if (trueattrtype == Attributes.CRIT.getValue()) {
			result = CRIT_TYPE;
		} else if (trueattrtype == Attributes.DODGE.getValue()) {
			result = DODGE_TYPE;
		} else if (trueattrtype == Attributes.SPEED.getValue()) {
			result = SPEED_TYPE;
		} else if (trueattrtype == Attributes.MAXHP.getValue()) {
			result = MAXHP_TYPE;
		} else if (trueattrtype == Attributes.MAXMP.getValue()) {
			result = MAXMP_TYPE;
		} else if (trueattrtype == Attributes.MAXSP.getValue()) {
			result = MAXSP_TYPE;
		} else if (trueattrtype == Attributes.LUCK.getValue()) {
			result = LUCK_TYPE;
		}
		return result;
	}

	/**
	 * 得到装备相同类型的属性列表
	 *
	 * @return
	 */
	public List<Attribute> getSameAttrTypeList(Equip equip, int attrtype) {
		if (equip == null) {
			return null;
		}
		List<Attribute> list = new ArrayList<Attribute>();
		for (int i = 0; i < equip.getAttributes().size(); i++) {
			Attribute attribute = equip.getAttributes().get(i);
			if (attribute != null && attribute.getType() == getTrueAttrType(attrtype)) {
				list.add(attribute);
			}
		}
		return list;
	}

	/**
	 * 得到装备不同类型的属性列表
	 *
	 * @return
	 */
	public List<Attribute> getNoSameAttrTypeList(Equip equip, int attrtype) {
		if (equip == null) {
			return null;
		}
		List<Attribute> list = new ArrayList<Attribute>();
		for (int i = 0; i < equip.getAttributes().size(); i++) {
			Attribute attribute = equip.getAttributes().get(i);
			if (attribute != null && attribute.getType() != getTrueAttrType(attrtype)) {
				list.add(attribute);
			}
		}
		return list;
	}

	/**
	 * 获得一个新的属性
	 *
	 * @return
	 */
	public Attribute getNewAttribute(Equip equip, int attrtype) {
		if (equip == null) {
			return null;
		}
		Q_equip_appendBean appendModel = DataManager.getInstance().q_equip_appendContainer.getMap().get(equip.getItemModelId());
		if (appendModel == null) {
			return null;
		}
		Attribute attribute = new Attribute();
		switch (attrtype) {
			case ATTACK_TYPE:
				attribute.setType(Attributes.ATTACK.getValue());
				attribute.setValue(RandomUtils.random(appendModel.getQ_attack_min(), appendModel.getQ_attack_max()));
				break;
			case ATTACKSPEED_TYPE:
				attribute.setType(Attributes.ATTACKSPEED.getValue());
				attribute.setValue(RandomUtils.random(appendModel.getQ_attackspeed_min(), appendModel.getQ_attackspeed_max()));
				break;
			case DEFENSE_TYPE:
				attribute.setType(Attributes.DEFENSE.getValue());
				attribute.setValue(RandomUtils.random(appendModel.getQ_defence_min(), appendModel.getQ_defence_max()));
				break;
			case CRIT_TYPE:
				attribute.setType(Attributes.CRIT.getValue());
				attribute.setValue(RandomUtils.random(appendModel.getQ_crit_min(), appendModel.getQ_crit_max()));
				break;
			case DODGE_TYPE:
				attribute.setType(Attributes.DODGE.getValue());
				attribute.setValue(RandomUtils.random(appendModel.getQ_dodge_min(), appendModel.getQ_dodge_max()));
				break;
			case SPEED_TYPE:
				attribute.setType(Attributes.SPEED.getValue());
				attribute.setValue(RandomUtils.random(appendModel.getQ_speed_min(), appendModel.getQ_speed_max()));
				break;
			case MAXHP_TYPE:
				attribute.setType(Attributes.MAXHP.getValue());
				attribute.setValue(RandomUtils.random(appendModel.getQ_hp_min(), appendModel.getQ_hp_max()));
				break;
			case MAXMP_TYPE:
				attribute.setType(Attributes.MAXMP.getValue());
				attribute.setValue(RandomUtils.random(appendModel.getQ_mp_min(), appendModel.getQ_mp_max()));
				break;
			case MAXSP_TYPE:
				attribute.setType(Attributes.MAXSP.getValue());
				attribute.setValue(RandomUtils.random(appendModel.getQ_sp_min(), appendModel.getQ_sp_max()));
				break;
			case LUCK_TYPE:
				attribute.setType(Attributes.LUCK.getValue());
				attribute.setValue(RandomUtils.random(appendModel.getQ_luck_min(), appendModel.getQ_luck_max()));
				break;
			default:
				return null;
		}
		if (attribute.getValue() <= 0) {
			return null;
		}
		return attribute;
	}
	
	public boolean checkEquipGetMelting(Equip equip) {
		if (equip == null) {
			return false;
		}
		Q_itemBean equipBean = DataManager.getInstance().q_itemContainer.getMap().get(equip.getItemModelId());
		if (equipBean == null) {
			return false;
		}
		if (equipBean.getQ_level() < DEF_MELTING_LV) {
			return false;
		}
		if (equip.getQuality() < DEF_MELTING_QUALITY) {
			return false;
		}
		return true;
	}

	/**
	 * 出售紫装获得熔炼石
	 *
	 * @return
	 */
	public void sellEquipGetMelting(Player player, Equip equip) {
		if (!checkEquipGetMelting(equip)) {
			return;
		}
		Q_itemBean equipBean = DataManager.getInstance().q_itemContainer.getMap().get(equip.getItemModelId());
		if (equipBean == null) {
			return;
		}
		if (equip.getAttributes().isEmpty()) {
			return;
		}
		Attribute oldAttribute = equip.getAttributes().get(RandomUtils.random(equip.getAttributes().size()));
		if (oldAttribute == null) {
			return;
		}
		int meltingItemId = getMeltingItemId(equipBean.getQ_level(), getFalseAttrType(oldAttribute.getType()));
		List<Item> createItems = Item.createItems(meltingItemId, 1, equip.isBind(), 0);
		if (!createItems.isEmpty()) {
			if (BackpackManager.getInstance().getBackpackAbleAddGridId(player, meltingItemId, 1, equip.isBind(), 0) != -1) {
				if (BackpackManager.getInstance().addItems(player, createItems, Reasons.MELTING_GETITEM, Config.getId())) {
					MessageUtil.notify_player(player, Notifys.SUCCESS, String.format(ResManager.getInstance().getString("出售紫装%s,您获得了%s*%d"), BackpackManager.getInstance().getName(equip.getItemModelId()), BackpackManager.getInstance().getName(meltingItemId), 1));
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("出售紫装%s,您获得了%s*%d,由于包裹空位不足，已转发邮件。"), BackpackManager.getInstance().getName(equip.getItemModelId()), BackpackManager.getInstance().getName(meltingItemId), 1));
					if (!MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("系统邮件"), String.format(ResManager.getInstance().getString("出售紫装%s,您获得了%s*%d,由于包裹空位不足，已转发邮件。"), BackpackManager.getInstance().getName(equip.getItemModelId()), BackpackManager.getInstance().getName(meltingItemId), 1), (byte) 0, 0, createItems)) {
						log.info("[Player" + player.getId() + "]" + "出售紫装获得熔炼石发送邮件失败==" + JSONserializable.toString(createItems));
					}
				}
			} else {
				MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("出售紫装%s,您获得了%s*%d,由于包裹空位不足，已转发邮件。"), BackpackManager.getInstance().getName(equip.getItemModelId()), BackpackManager.getInstance().getName(meltingItemId), 1));
				if (!MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("系统邮件"), String.format(ResManager.getInstance().getString("出售紫装%s,您获得了%s*%d,由于包裹空位不足，已转发邮件。"), BackpackManager.getInstance().getName(equip.getItemModelId()), BackpackManager.getInstance().getName(meltingItemId), 1), (byte) 0, 0, createItems)) {
					log.info("[Player" + player.getId() + "]" + "出售紫装获得熔炼石发送邮件失败==" + JSONserializable.toString(createItems));
				}
			}
		} else {
			log.error(String.format("没有该熔炼石数据==%d", meltingItemId));
		}
	}

	//----------------------------消息处理------------------------------//
	public void reqMeltingItemToServer(Player player, ReqMeltingItemToServerMessage message) {
		if (player == null) {
			log.error("玩家为NULL");
			return;
		}
		Equip equip = EquipManager.getInstance().getEquipById(player, message.getItemid());
		if (equip == null) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您没有要熔炼的装备"));
			return;
		}
		Q_itemBean equipBean = DataManager.getInstance().q_itemContainer.getMap().get(equip.getItemModelId());
		if (equipBean == null) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("没有该装备对应的模板数据"));
			return;
		}
		Item meltingItem = BackpackManager.getInstance().getItemById(player, message.getMetingid());
		if (meltingItem == null) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您身上没有{1}这个物品"), BackpackManager.getInstance().getName(meltingItem.getItemModelId()));
			return;
		}
		if (meltingItem.getNum() == 0) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您身上没有{1}这个物品"), BackpackManager.getInstance().getName(meltingItem.getItemModelId()));
			return;
		}
		int equiplv = getEquipLv(meltingItem.getItemModelId());
		if (equiplv == 0) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("没有该等级的熔炼石"));
			return;
		}
		if (equipBean.getQ_level() != equiplv) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, String.format(ResManager.getInstance().getString("%d级熔炼石只能熔炼%d级装备"), equiplv, equiplv));
			return;
		}
		int attrtype = getAttrType(meltingItem.getItemModelId());
		if (attrtype < ATTACK_TYPE || attrtype > LUCK_TYPE) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("没有该类型的熔炼石"));
			return;
		}
		Q_itemBean itemBean = DataManager.getInstance().q_itemContainer.getMap().get(meltingItem.getItemModelId());
		if (itemBean == null) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("没有这种熔炼石"));
			return;
		}
		if (equip.getQuality() != 4) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("只有紫色装备才可以进行熔炼操作"));
			return;
		}
		List<Attribute> noSameAttrTypeList = getNoSameAttrTypeList(equip, attrtype);
		if (noSameAttrTypeList == null) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您没有要熔炼的装备"));
			return;
		}
		if (noSameAttrTypeList.isEmpty()) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("目标装备所有附加属性均与熔炼石属性一致"));
			return;
		}
		List<Attribute> sameAttrTypeList = getSameAttrTypeList(equip, attrtype);
		if (sameAttrTypeList == null) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您没有要熔炼的装备"));
			return;
		}
		int needgold = DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.METING_GOLD.getValue()).getQ_int_value();
		if (needgold == 0) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("熔炼所需元宝数据错误"));
			return;
		}
		if (!BackpackManager.getInstance().checkGold(player, needgold)) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("熔炼所需元宝不足"));
			return;
		}
		int needmoney = DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.METING_MONEY.getValue()).getQ_int_value();
		if (needmoney == 0) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("熔炼所需铜币数据错误"));
			return;
		}
		if (player.getMoney() < needmoney) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("您的铜币不足，建议您开通VIP每日可免费领取大量铜币"));
			return;
		}
		Attribute newAttribute = getNewAttribute(equip, attrtype);
		if (newAttribute == null) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("熔炼获得新属性失败"));
			return;
		}
		long actionid = Config.getId();
		if (!BackpackManager.getInstance().removeItem(player, meltingItem, 1, Reasons.MELTING_ITEM, actionid)) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, String.format(ResManager.getInstance().getString("熔炼移除熔炼石%s失败"), BackpackManager.getInstance().getName(meltingItem.getItemModelId())));
			return;
		}
		if (!BackpackManager.getInstance().changeGold(player, -needgold, Reasons.MELTING_GOLD, actionid)) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("熔炼所需元宝不足"));
			return;
		}
		if (!BackpackManager.getInstance().changeMoney(player, -needmoney, Reasons.MELTING_MONEY, actionid)) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("您的铜币不足，建议您开通VIP每日可免费领取大量铜币"));
			return;
		}
		if (meltingItem.isBind()) {
			equip.setBind(meltingItem.isBind());
		}
		Q_meting_randomBean meltingBean = DataManager.getInstance().q_meting_randomContainer.getMap().get(equiplv + "_" + attrtype + "_" + sameAttrTypeList.size());
		ResMeltingItemToClientMessage sendMessage = new ResMeltingItemToClientMessage();
		MeltingLog log = new MeltingLog();
		log.setRoleid(player.getId());
		log.setUserid(Long.valueOf(player.getUserId()));
		log.setGold(needgold);
		log.setMoney(needmoney);
		log.setMeltingid(meltingItem.getItemModelId());
		if (meltingBean == null) {
			int idx = RandomUtils.random(equip.getAttributes().size());
			Attribute oldAttribute = equip.getAttributes().set(idx, newAttribute);
			sendMessage.setEquipInfo(equip.buildItemInfo());
			sendMessage.setIdx((byte) idx);
			sendMessage.setRepattr(newAttribute.buildGoodsAttribute());
			log.setIteminfo(JSON.toJSONString(equip));
			log.setIdx(idx);
			log.setOldattrinfo(JSON.toJSONString(oldAttribute));
			log.setNewattrinfo(JSON.toJSONString(newAttribute));
		} else {
			if (RandomUtils.random(Global.MAX_PROBABILITY) < meltingBean.getQ_random() || sameAttrTypeList.isEmpty()) {//随机到附加几率
				Attribute noSameAttribute = noSameAttrTypeList.get(RandomUtils.random(noSameAttrTypeList.size()));
				if (noSameAttribute != null) {
					int indexOf = equip.getAttributes().indexOf(noSameAttribute);
					Attribute oldAttribute = equip.getAttributes().set(indexOf, newAttribute);
					sendMessage.setEquipInfo(equip.buildItemInfo());
					sendMessage.setIdx((byte) indexOf);
					sendMessage.setRepattr(newAttribute.buildGoodsAttribute());
					log.setIteminfo(JSON.toJSONString(equip));
					log.setIdx(indexOf);
					log.setOldattrinfo(JSON.toJSONString(oldAttribute));
					log.setNewattrinfo(JSON.toJSONString(newAttribute));
				}
			} else { //随机到不能附加几率
				Attribute sameAttribute = sameAttrTypeList.get(RandomUtils.random(sameAttrTypeList.size()));
				if (sameAttribute != null) {
					int indexOf = equip.getAttributes().indexOf(sameAttribute);
					Attribute oldAttribute = equip.getAttributes().set(indexOf, newAttribute);
					sendMessage.setEquipInfo(equip.buildItemInfo());
					sendMessage.setIdx((byte) indexOf);
					sendMessage.setRepattr(newAttribute.buildGoodsAttribute());
					log.setIteminfo(JSON.toJSONString(equip));
					log.setIdx(indexOf);
					log.setOldattrinfo(JSON.toJSONString(oldAttribute));
					log.setNewattrinfo(JSON.toJSONString(newAttribute));
				}
			}
		}
		PlayerAttributeManager.getInstance().countPlayerAttribute(player, PlayerAttributeType.EQUIP);
		MessageUtil.tell_player_message(player, sendMessage);
		LogService.getInstance().execute(log);
	}
}
