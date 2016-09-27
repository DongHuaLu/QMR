package com.game.equip.manager;

import java.util.ArrayList;

import com.game.backpack.structs.Attribute;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.HorseEquip;
import com.game.backpack.structs.Item;
import com.game.chat.bean.GoodsInfoRes;
import com.game.equip.bean.EquipAttribute;
import com.game.equip.bean.EquipInfo;
import com.game.equip.message.UnwearEquipItemMessage;
import com.game.equip.message.WearEquipItemMessage;
import com.game.horse.struts.Horse;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.vip.manager.VipManager;
import com.game.vip.struts.GuideType;

public class EquipManager {

	//private Logger log = Logger.getLogger(EquipManager.class);
	
	private static Object obj = new Object();

	//玩家管理类实例
	private static EquipManager manager;
	
	private EquipManager(){}
	
	public static EquipManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new EquipManager();
			}
		}
		return manager;
	}
	
	
	/**
	 * 装备物品
	 * @param roleId 玩家Id
	 * @param equipId 物品Id
	 */ 
	public void wear(Player player, long equipId){
		ManagerPool.backpackManager.useItem(player, equipId,1);
	}
	
	/**
	 * 卸下物品 (如果身上找不到，再去找坐骑身上)
	 * @param roleId 玩家Id
	 * @param equipId 物品Id
	 */ 
	public void unwear(Player player, long equipId){
		//获得卸下物品
		Item item = getEquipById(player, equipId);
		if (item == null) {
			item = getHorseEquipById(player, equipId);
		}
		if(item==null) return;

		item.unuse(player);
	}
	
	/**
	 * 获得装备物品消息
	 * @param equip 装备
	 */ 
	public WearEquipItemMessage getWearEquipInfo(Equip equip){
		WearEquipItemMessage msg = new WearEquipItemMessage();
		msg.setEquip(getEquipInfo(equip));
		return msg;
	}
	/**
	 * 获得装备物品+宝石消息消息
	 * @param equip 装备
	 */ 
	public WearEquipItemMessage getWearEquipInfo(Player player,Equip equip){
		WearEquipItemMessage msg = new WearEquipItemMessage();
		EquipInfo equipinfo = getEquipInfo(equip);
		msg.setEquip(equipinfo);
		return msg;
	}
	/**
	 * 获得卸下物品消息
	 * @param equip 装备
	 */ 
	public UnwearEquipItemMessage getUnwearEquipInfo(Equip equip){
		UnwearEquipItemMessage msg = new UnwearEquipItemMessage();
		msg.setItemId(equip.getId());
		msg.setGridId(equip.getGridId());
		return msg;
	}
	
	/**
	 * 获得装备栏中装备 
	 * @param roleId 玩家id
	 * @param equipId 物品id
	 * @return
	 */
	public Equip getEquipById(Player player, long equipId){
		for (int i = 0; i < player.getEquips().length; i++) {
			Equip equip = player.getEquips()[i];
			if(equip!=null && equip.getId()==equipId) return equip;
		}
		
		return null;
	}
	
	/**
	 * 获得坐骑装备栏中装备 
	 * @param roleId 玩家id
	 * @param equipId 物品id
	 * @return
	 */
	public HorseEquip getHorseEquipById(Player player, long equipId){
		Horse horse = ManagerPool.horseManager.getHorse(player);
		HorseEquip[] horseequips = horse.getHorseequips();
		for (int i = 0; i < horseequips.length; i++) {
			HorseEquip equip = horseequips[i];
			if(equip!=null && equip.getId()==equipId) return equip;
		}
		
		return null;
	}
	
	
	
	
	/**
	 * 获取装备信息
	 * @param equip 装备
	 * @return
	 */
	public EquipInfo getEquipInfo(Equip equip){
		EquipInfo info = new EquipInfo();
		info.setItemId(equip.getId());
		info.setItemLevel((byte) equip.getGradeNum());
		info.setItemModelId(equip.getItemModelId());
		info.setItemBind((byte)(equip.isBind()?1:0));
		info.setItemLosttime(equip.getLosttime());
		for (Attribute attribute : equip.getAttributes()) {
			EquipAttribute equipAttribute = new EquipAttribute();
			equipAttribute.setAttributeType((byte)attribute.getType());
			equipAttribute.setAttributeValue((byte)attribute.getValue());
			info.getItemAttributes().add(equipAttribute);
		}
		return info;
	}
	
	
	
	/**检查并改变套装属性BUFF
	 * 
	 * @param player
	 * type 0 上线 触发 ， 1其他
	 */
	public void stTaoZhuang(Player player,int type){
		Equip[] equips = player.getEquips();
		int lv = 0;	//强化等级
		int quality = 4;	//品质(默认4)，如果有小于4，则变0
		for (int i = 0; i < 10 ; i++) {	//10个装备项目
			if(equips[i] == null){
				lv = 0;
				break;
			}
			if (equips[i].getQuality() < 4) {
				quality = 0;
			}
			if(equips[i].getGradeNum() >= 10){
				lv = lv +10;
			}else if (equips[i].getGradeNum() >= 7) {
				lv = lv +7;
			}else {
				lv = 0;
				break;
			}
		}
		lv = lv /10;
		int buff7 = ManagerPool.dataManager.q_globalContainer.getMap().get(81).getQ_int_value();//7阶白装
		int buff7x = ManagerPool.dataManager.q_globalContainer.getMap().get(82).getQ_int_value();//7阶紫装
		int buff10 = ManagerPool.dataManager.q_globalContainer.getMap().get(83).getQ_int_value();//10阶白装
		int buff10x = ManagerPool.dataManager.q_globalContainer.getMap().get(84).getQ_int_value();//10阶紫装
		ManagerPool.buffManager.removeByBuffId( player, buff7);
		ManagerPool.buffManager.removeByBuffId( player, buff7x);
		ManagerPool.buffManager.removeByBuffId( player, buff10);
		ManagerPool.buffManager.removeByBuffId( player, buff10x);
		
		ManagerPool.buffManager.removeByBuffId( player, 9150);
		ManagerPool.buffManager.removeByBuffId( player, 9151);
		
		ManagerPool.buffManager.removeByBuffId( player, 9152);
		ManagerPool.buffManager.removeByBuffId( player, 9153);

		String key1 = "equip_9159";//7阶紫装翻倍
		String key2 = "equip_9160";//10阶紫装翻倍
		
		StringBuffer str = new StringBuffer("");
		if(lv >=7 ){
			ManagerPool.buffManager.addBuff(player, player, buff7, 0, 0, 0);	//7阶白装
			str.append(ResManager.getInstance().getString("7阶套装 "));
			if (quality >= 4) {		//7阶紫装
				str.append(ResManager.getInstance().getString("7阶紫装 "));
				ManagerPool.buffManager.addBuff(player, player, buff7x, 0, 0, 0);
				if (player.getActivitiesReward().containsKey(key1)) {
					ManagerPool.buffManager.addBuff(player, player, 9150, 0, 0, 0);//7阶紫装翻倍
					ManagerPool.buffManager.addBuff(player, player, 9151, 0, 0, 0);
				}
			}
		}
		if (lv >= 10 ) {
			ManagerPool.buffManager.addBuff(player, player, buff10, 0, 0, 0);	//10阶白装
			str.append(ResManager.getInstance().getString("10阶套装 "));
			if (quality >= 4) {		//10阶紫装
				str.append(ResManager.getInstance().getString("10阶紫装 "));
				ManagerPool.buffManager.addBuff(player, player, buff10x, 0, 0, 0);
				if (player.getActivitiesReward().containsKey(key2)) {
					ManagerPool.buffManager.addBuff(player, player, 9152, 0, 0, 0);//10阶紫装翻倍
					ManagerPool.buffManager.addBuff(player, player, 9153, 0, 0, 0);
				}
			}
		}
		if (lv >= 7) {
			MessageUtil.notify_player(player,Notifys.SUCCESS,ResManager.getInstance().getString("{1}属性激活"),str.toString());
			if (type == 2) {
				ParseUtil parseUtil = new ParseUtil();
				if (quality >= 4) {
					if(player.getEquipquality() < quality){
						player.setEquipquality(quality);
						parseUtil.setValue(String.format(ResManager.getInstance().getString("玩家【%s】经过不懈努力，终于获得全身紫色套装属性加成，战斗力获得提升！{@}"), player.getName()), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.TAOZHUANG_ZISE.getValue()));
						MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.TAOZHUANG_ZISE.getValue());
					}
				}else{
					if (lv < 10) {
						lv = 7;
					}
					if (player.getEquipbulletin() < lv ) {
						player.setEquipbulletin(lv);
						parseUtil.setValue(String.format(ResManager.getInstance().getString("玩家【%s】经过不懈努力，终于获得+%d套装属性加成，战斗力获得提升！{@}"), player.getName(),lv), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.TAOZHUANG.getValue()));
						MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.TAOZHUANG.getValue());
					}
				}
			}
		}
	}
}






