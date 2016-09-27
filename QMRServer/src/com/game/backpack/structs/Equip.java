package com.game.backpack.structs;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.config.Config;
import com.game.data.bean.Q_equip_appendBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_item_strengthBean;
import com.game.data.manager.DataManager;
import com.game.equipstreng.structs.EquipStreng;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.message.ResArmorChangeMessage;
import com.game.map.message.ResWeaponChangeMessage;
import com.game.player.message.ReqSyncPlayerAppearanceInfoMessage;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.structs.Attributes;
import com.game.structs.Reasons;
import com.game.utils.CommonConfig;
import com.game.utils.Global;
import com.game.utils.MessageUtil;
import com.game.vip.manager.VipManager;

/**
 * 装备类
 * @author heyang szy_heyang@163.com
 *
 */
public class Equip extends Item {

	private Logger log = Logger.getLogger(Equip.class);
	
	private static final long serialVersionUID = -6709344489920034517L;
	public static final String BODYEQUIPLOST="BODYEQUIPLOST";
	//强化等级
	private int gradeNum;
	//附加属性
	private List<Attribute> attributes = new ArrayList<Attribute>();
	//已镶嵌宝石
	private List<Jewel> jewels= new  ArrayList<Jewel>();
	
	//攻击
	protected transient int attack;
	//防御
	protected transient int defense;
	//暴击
	protected transient int crit;
	//闪避
	protected transient int dodge;
	//最大血量
	protected transient int maxHp;
	//最大魔法
	protected transient int maxMp;
	//最大体力
	protected transient int maxSp;
	//攻击速度
	protected transient int attackSpeed;
	//速度
	protected transient int speed;
	//幸运
	protected transient int luck;
	
	//是否满附加
	protected transient boolean fullAppend;
	//是否满镶嵌
	protected transient boolean fullEnchase;
	//是否满强化
	protected transient boolean fullStrength;
	//品质
	protected transient int quality;
	//品质加成
	protected transient int qualityAdd;
	
	//最高星数强化失败次数
	private short gradefailnum ;
	//曾进行强化的最高星数
	private byte highestgrade;
	//进阶失败次数
	private short advfailnum ;
	
	
	/**
	 * 穿着装备
	 * @param roleId 玩家id
	 */
	public void use(Player player, String... parameters){
		//获得物品模型
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
		if(model==null) return;
		
		if(model.getQ_bind() == 2){
			//装备时绑定
			this.setBind(true);
		}
		
		if(!this.isBind()){
			//顶级强化装备佩戴时将装备设为绑定（0不生效，1生效）
			if(this.isFullStrength() && ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.EQUIP_TOPLEVEL_INTENSIFY_USEBIND.getValue()).getQ_int_value() == 1){
				this.setBind(true);
			}
		}
		
		if(!this.isBind()){
			//顶级镶嵌装备佩戴将装备设为绑定（0不生效，1生效）
			if(this.isFullEnchase() && ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.EQUIP_TOPLEVEL_INLAY_USEBIND.getValue()).getQ_int_value() == 1){
				this.setBind(true);
			}
		}

		if(!this.isBind()){
			//紫色装备佩戴时将装备设为绑定（0不生效，1生效）
			if(this.getQuality()==4 && ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.EQUIP_PURPLE_USEBIND.getValue()).getQ_int_value() == 1){
				this.setBind(true);
			}
		}
		long action = Config.getId();
		//获得对应装备栏装备
		Equip weared = player.getEquips()[model.getQ_kind() - 1];
		
		//未装备装备
		if(weared==null){
			BackpackManager.getInstance().removeItemByCellId(player, getGridId(), Reasons.WEAREDORUNWEARED,action);
			this.setGridId(-1);
			player.getEquips()[model.getQ_kind() - 1] = this;
			//发送装备信息
			MessageUtil.tell_player_message(player, ManagerPool.equipManager.getWearEquipInfo(this));
			
			if(model.getQ_kind()==1){
				ResWeaponChangeMessage msg = new ResWeaponChangeMessage();
				msg.setPersonId(player.getId());
				msg.setWeaponId(model.getQ_id());
				msg.setWeaponStreng((byte)this.getGradeNum());
				MessageUtil.tell_round_message(player,msg);	
			}else if(model.getQ_kind()==2){
				ResArmorChangeMessage msg=new ResArmorChangeMessage();
				msg.setPersonId(player.getId());
				msg.setArmorId(model.getQ_id());
				MessageUtil.tell_round_message(player,msg);
			}
			if(model.getQ_kind() == 1 || model.getQ_kind() == 2){	//只有武器和衣服变化才需要发这个消息
//				ReqSyncPlayerAppearanceInfoMessage syncmsg = new ReqSyncPlayerAppearanceInfoMessage();
//				syncmsg.setPlayerId(player.getId());
//				syncmsg.setVipid(VipManager.getInstance().getPlayerVipId(player));
//				syncmsg.setAppearanceInfo(ManagerPool.transactionsManager.setPlayerAppearanceInfo(player));
//				MessageUtil.send_to_world(syncmsg);
				ManagerPool.playerManager.stSyncExterior(player);
			}
			//重新计算属性
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.EQUIP, this.getItemModelId());
			if (this.getGradeNum() >= 7) {
				ManagerPool.equipManager.stTaoZhuang(player,1);//改变套装属性
			}
		}else{
			EquipStreng esdata = player.getEquipStreng();
			if (esdata.getItemid() == weared.getId()) {
				//获得物品模型
				Q_itemBean oldmodel = ManagerPool.dataManager.q_itemContainer.getMap().get(weared.getItemModelId());
				
				if(oldmodel==null) return;
				String name = BackpackManager.getInstance().getName(weared.getItemModelId());
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("『{1}』正在强化中，无法卸下，请等待强化完成。"),name);
				return;
			}
			
			weared.setGridId(this.getGridId());

//			player.getBackpackItems().put(String.valueOf(weared.getGridId()), weared);
			//log.info("UNWEAR " + weared.getId() + " TO " + weared.getGridId());
			//发送卸下装备信息
			MessageUtil.tell_player_message(player, ManagerPool.equipManager.getUnwearEquipInfo(weared));
//			Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(weared.getId());
			
			if(model.getQ_kind()==1){
				ResWeaponChangeMessage msg = new ResWeaponChangeMessage();
				msg.setPersonId(player.getId());
				msg.setWeaponId(model.getQ_id());
				msg.setWeaponStreng((byte)this.getGradeNum());
				MessageUtil.tell_round_message(player,msg);	
			}else if(model.getQ_kind()==2){
				ResArmorChangeMessage msg=new ResArmorChangeMessage();
				msg.setPersonId(player.getId());
				msg.setArmorId(model.getQ_id());
				MessageUtil.tell_round_message(player,msg);
			}

			ManagerPool.backpackManager.removeItem(player, getId(),Reasons.WEAREDORUNWEARED,action);
			ManagerPool.backpackManager.addItem(player, weared,Reasons.WEAREDORUNWEARED,action);
			this.setGridId(-1);
			player.getEquips()[model.getQ_kind() - 1] = this;
			//发送装备信息
			MessageUtil.tell_player_message(player, ManagerPool.equipManager.getWearEquipInfo(this));
			//重新计算属性
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.EQUIP, this.getItemModelId());
			
			if(model.getQ_kind() == 1 || model.getQ_kind() == 2){
//				ReqSyncPlayerAppearanceInfoMessage syncmsg = new ReqSyncPlayerAppearanceInfoMessage();
//				syncmsg.setPlayerId(player.getId());
//				syncmsg.setAppearanceInfo(ManagerPool.transactionsManager.setPlayerAppearanceInfo(player));
//				MessageUtil.send_to_world(syncmsg);
				ManagerPool.playerManager.stSyncExterior(player);
			}
			if (weared.getGradeNum() >= 7 || this.getGradeNum() >= 7) {
				ManagerPool.equipManager.stTaoZhuang(player,1);//改变套装属性
			}
		}
		
		ManagerPool.playerManager.savePlayer(player);
	}
	
	/**
	 * 卸下装备
	 * @param roleId 玩家id
	 */
	public void unuse(Player player, String... parameters){
		//获得物品模型
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
		if(model==null) return;
				
		//获得对应装备栏装备
		Equip weared = player.getEquips()[model.getQ_kind() - 1];
		if(weared==null||weared!=this){
			return;
		}
		EquipStreng esdata = player.getEquipStreng();
		String name=BackpackManager.getInstance().getName(getItemModelId());
		if (esdata.getItemid() == weared.getId()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("『{1}』正在强化中，无法卸下，请等待强化完成。"),name);
			return;
		}
		
		//装备该装备
		if(weared==this){
			if(parameters!=null&&parameters.length>0&&parameters[0].equals(BODYEQUIPLOST)){
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("您身上的{1}已经过期，自动卸下"),name);
				MessageUtil.notify_player(player, Notifys.NORMAL,ResManager.getInstance().getString("您身上的{1}已经过期，自动卸下"),name);
				if(!(BackpackManager.getInstance().hasAddSpace(player, weared)&&ManagerPool.backpackManager.addItem(player, weared,Reasons.WEAREDORUNWEARED,Config.getId()))){
					ArrayList<Item> arrayList = new ArrayList<Item>();
					arrayList.add(this);
					ManagerPool.mailServerManager.sendSystemMail(player.getId(), null, ResManager.getInstance().getString("系统邮件"), ResManager.getInstance().getString("装备过期自动卸下,加入包裹失败"), (byte) 1, 0, arrayList);
				}
			}else{
				if(!BackpackManager.getInstance().hasAddSpace(player, weared)){
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("对不起，您的背包空格不足，请清理后再进行本操作"));
					return;
				}
				if (!ManagerPool.backpackManager.addItem(player, weared,Reasons.WEAREDORUNWEARED,Config.getId())){
					//加入包裹失败
					return;
				}
			}
			player.getEquips()[model.getQ_kind() - 1] = null;
			//发送卸下装备信息
			MessageUtil.tell_player_message(player, ManagerPool.equipManager.getUnwearEquipInfo(this));
			if(model.getQ_kind()==1){
				ResWeaponChangeMessage msg = new ResWeaponChangeMessage();
				msg.setPersonId(player.getId());
				msg.setWeaponId(0);
				MessageUtil.tell_round_message(player,msg);	
			}else if(model.getQ_kind()==2){
				ResArmorChangeMessage msg=new ResArmorChangeMessage();
				msg.setPersonId(player.getId());
				msg.setArmorId(0);
				MessageUtil.tell_round_message(player,msg);
			}
			if(log.isDebugEnabled()){
				log.debug("UNWEAR " + this.getId() + " TO " + this.getGridId());	
			}
			//重新计算属性
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.EQUIP, this.getItemModelId());
			
			ManagerPool.playerManager.savePlayer(player);
			if(model.getQ_kind() == 1 || model.getQ_kind() == 2){	//只有武器和衣服变化才需要发这个消息
				ReqSyncPlayerAppearanceInfoMessage syncmsg = new ReqSyncPlayerAppearanceInfoMessage();
				syncmsg.setPlayerId(player.getId());
				syncmsg.setAppearanceInfo(ManagerPool.transactionsManager.setPlayerAppearanceInfo(player));
				syncmsg.setVipid(VipManager.getInstance().getPlayerVipId(player));
				MessageUtil.send_to_world(syncmsg);
			}
			ManagerPool.equipManager.stTaoZhuang(player,1);//改变套装属性
		}
	}
	/**强化等级
	 * 
	 * @return
	 */
	public int getGradeNum() {
		return gradeNum;
	}
	/**强化等级
	 * 
	 * @return
	 */
	public void setGradeNum(int gradeNum) {
		this.gradeNum = gradeNum;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public List<Jewel> getJewels() {
		return jewels;
	}

	public void setJewels(List<Jewel> jewels) {
		this.jewels = jewels;
	}
	
	/**
	 * 获取装备增加攻击力
	 * @return
	 */
	public int getAttack() {
		//获取基本攻击
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
		if(model==null){
			log.error("Item model " + this.getItemModelId() + " not found!");
			return 0;
		}
		int value = model.getQ_attack();
		Q_equip_appendBean append = ManagerPool.dataManager.q_equip_appendContainer.getMap().get(this.getItemModelId());
		if(append!=null){
			//计算附加属性
			for (int i = 0; i < attributes.size(); i++) {
				Attribute attribute = attributes.get(i);
				if(attribute.getType() == Attributes.ATTACK.getValue()){
					value += (int)((append.getQ_attack_min() + (double)(append.getQ_attack_max() - append.getQ_attack_min()) * attribute.getValue() / 100) * getQualityAdd() / Global.MAX_PROBABILITY);
				}
			}
		}
		if(gradeNum>0){
			//天生属性
			Q_item_strengthBean born = DataManager.getInstance().q_item_strengthContainer.getMap().get(getItemModelId()+"_"+gradeNum);
			if(born!=null){
				value+=born.getQ_attack();
			}
		}
		return value;
	}

	/**
	 * 获取装备增加防御力
	 * @return
	 */
	public int getDefense() {
		//获取基本防御
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
		if(model==null){
			log.error("Item model " + this.getItemModelId() + " not found!");
			return 0;
		}
		int value = model.getQ_defence();
		Q_equip_appendBean append = ManagerPool.dataManager.q_equip_appendContainer.getMap().get(this.getItemModelId());
		if(append!=null){
			//计算附加属性
			for (int i = 0; i < attributes.size(); i++) {
				Attribute attribute = attributes.get(i);
				if(attribute.getType() == Attributes.DEFENSE.getValue()){
					value += (int)((append.getQ_defence_min() + (double)(append.getQ_defence_max() - append.getQ_defence_min()) * attribute.getValue() / 100) * getQualityAdd() / Global.MAX_PROBABILITY);
				}
			}
		}
		if(gradeNum>0){
			//天生属性
			Q_item_strengthBean born = DataManager.getInstance().q_item_strengthContainer.getMap().get(getItemModelId()+"_"+gradeNum);
			if(born!=null){
				value+=born.getQ_defence();
			}
		}
		return value;
	}
	
	/**
	 * 获取装备增加暴击
	 * @return
	 */
	public int getCrit() {
		//获取基本暴击
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
		if(model==null){
			log.error("Item model " + this.getItemModelId() + " not found!");
			return 0;
		}
		int value = model.getQ_crit();
		Q_equip_appendBean append = ManagerPool.dataManager.q_equip_appendContainer.getMap().get(this.getItemModelId());
		if(append!=null){
			//计算附加属性
			for (int i = 0; i < attributes.size(); i++) {
				Attribute attribute = attributes.get(i);
				if(attribute.getType() == Attributes.CRIT.getValue()){
					value += (int)((append.getQ_crit_min() + (double)(append.getQ_crit_max() - append.getQ_crit_min()) * attribute.getValue() / 100) * getQualityAdd() / Global.MAX_PROBABILITY);
				}
			}
		}
		if(gradeNum>0){
			//天生属性
			Q_item_strengthBean born = DataManager.getInstance().q_item_strengthContainer.getMap().get(getItemModelId()+"_"+gradeNum);
			if(born!=null){
				value+=born.getQ_crit();
			}
		}
		return value;
	}
	
	/**
	 * 获取装备增加闪避
	 * @return
	 */
	public int getDodge() {
		//获取基本闪避
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
		if(model==null){
			log.error("Item model " + this.getItemModelId() + " not found!");
			return 0;
		}
		int value = model.getQ_dodge();
		Q_equip_appendBean append = ManagerPool.dataManager.q_equip_appendContainer.getMap().get(this.getItemModelId());
		if(append!=null){
			//计算附加属性
			for (int i = 0; i < attributes.size(); i++) {
				Attribute attribute = attributes.get(i);
				if(attribute.getType() == Attributes.DODGE.getValue()){
					value += (int)((append.getQ_dodge_min() + (double)(append.getQ_dodge_max() - append.getQ_dodge_min()) * attribute.getValue() / 100) * getQualityAdd() / Global.MAX_PROBABILITY);
				}
			}
		}
		if(gradeNum>0){
			//天生属性
			Q_item_strengthBean born = DataManager.getInstance().q_item_strengthContainer.getMap().get(getItemModelId()+"_"+gradeNum);
			if(born!=null){
				value+=born.getQ_dodge();
			}
		}
		return value;
	}

	/**
	 * 获取装备增加最大生命
	 * @return
	 */
	public int getMaxHp() {
		//获取基本最大生命
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
		if(model==null){
			log.error("Item model " + this.getItemModelId() + " not found!");
			return 0;
		}
		int value = model.getQ_max_hp();
		Q_equip_appendBean append = ManagerPool.dataManager.q_equip_appendContainer.getMap().get(this.getItemModelId());
		if(append!=null){
			//计算附加属性
			for (int i = 0; i < attributes.size(); i++) {
				Attribute attribute = attributes.get(i);
				if(attribute.getType() == Attributes.MAXHP.getValue()){
					value += (int)((append.getQ_hp_min() + (double)(append.getQ_hp_max() - append.getQ_hp_min()) * attribute.getValue() / 100) * getQualityAdd() / Global.MAX_PROBABILITY);
				}
			}
		}
		if(gradeNum>0){
			//天生属性
			Q_item_strengthBean born = DataManager.getInstance().q_item_strengthContainer.getMap().get(getItemModelId()+"_"+gradeNum);
			if(born!=null){
				value+=born.getQ_maxhp();
			}
		}
		return value;
	}
	
	/**
	 * 获取装备增加最大内力
	 * @return
	 */
	public int getMaxMp() {
		//获取基本最大内力
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
		if(model==null){
			log.error("Item model " + this.getItemModelId() + " not found!");
			return 0;
		}
		int value = model.getQ_max_mp();
		Q_equip_appendBean append = ManagerPool.dataManager.q_equip_appendContainer.getMap().get(this.getItemModelId());
		if(append!=null){
			//计算附加属性
			for (int i = 0; i < attributes.size(); i++) {
				Attribute attribute = attributes.get(i);
				if(attribute.getType() == Attributes.MAXMP.getValue()){
					value += (int)((append.getQ_mp_min() + (double)(append.getQ_mp_max() - append.getQ_mp_min()) * attribute.getValue() / 100) * getQualityAdd() / Global.MAX_PROBABILITY);
				}
			}
		}
		if(gradeNum>0){
			//天生属性
			Q_item_strengthBean born = DataManager.getInstance().q_item_strengthContainer.getMap().get(getItemModelId()+"_"+gradeNum);
			if(born!=null){
				value+=born.getQ_maxmp();
			}
		}
		return value;
	}
	
	/**
	 * 获取装备增加最大体力
	 * @return
	 */
	public int getMaxSp() {
		//获取基本最大体力
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
		if(model==null){
			log.error("Item model " + this.getItemModelId() + " not found!");
			return 0;
		}
		int value = model.getQ_max_sp();
		Q_equip_appendBean append = ManagerPool.dataManager.q_equip_appendContainer.getMap().get(this.getItemModelId());
		if(append!=null){
			//计算附加属性
			for (int i = 0; i < attributes.size(); i++) {
				Attribute attribute = attributes.get(i);
				if(attribute.getType() == Attributes.MAXSP.getValue()){
					value += (int)((append.getQ_sp_min() + (double)(append.getQ_sp_max() - append.getQ_sp_min()) * attribute.getValue() / 100) * getQualityAdd() / Global.MAX_PROBABILITY);
				}
			}
		}
		if(gradeNum>0){
			//天生属性
			Q_item_strengthBean born = DataManager.getInstance().q_item_strengthContainer.getMap().get(getItemModelId()+"_"+gradeNum);
			if(born!=null){
				value+=born.getQ_maxsp();
			}
		}
		return value;
	}
	/**
	 * 获取装备增加攻击速度
	 * @return
	 */
	public int getAttackSpeed() {
		//获取基本攻击速度
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
		if(model==null){
			log.error("Item model " + this.getItemModelId() + " not found!");
			return 0;
		}
		int value = model.getQ_attackspeed();
		Q_equip_appendBean append = ManagerPool.dataManager.q_equip_appendContainer.getMap().get(this.getItemModelId());
		if(append!=null){
			//计算附加属性
			for (int i = 0; i < attributes.size(); i++) {
				Attribute attribute = attributes.get(i);
				if(attribute.getType() == Attributes.ATTACKSPEED.getValue()){
					value += (int)((append.getQ_attackspeed_min() + (double)(append.getQ_attackspeed_max() - append.getQ_attackspeed_min()) * attribute.getValue() / 100) * getQualityAdd() / Global.MAX_PROBABILITY);
				}
			}
		}
		if(gradeNum>0){
			//天生属性
			Q_item_strengthBean born = DataManager.getInstance().q_item_strengthContainer.getMap().get(getItemModelId()+"_"+gradeNum);
			if(born!=null){
				value+=born.getQ_attackspeed();
			}
		}
		return value;
	}
	
	/**
	 * 获取装备增加速度
	 * @return
	 */
	public int getSpeed() {
		//获取基本速度
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
		if(model==null){
			log.error("Item model " + this.getItemModelId() + " not found!");
			return 0;
		}
		int value = model.getQ_speed();
		Q_equip_appendBean append = ManagerPool.dataManager.q_equip_appendContainer.getMap().get(this.getItemModelId());
		if(append!=null){
			//计算附加属性
			for (int i = 0; i < attributes.size(); i++) {
				Attribute attribute = attributes.get(i);
				if(attribute.getType() == Attributes.SPEED.getValue()){
					value += (int)((append.getQ_speed_min() + (double)(append.getQ_speed_max() - append.getQ_speed_min()) * attribute.getValue() / 100) * getQualityAdd() / Global.MAX_PROBABILITY);
				}
			}
		}
		if(gradeNum>0){
			//天生属性
			Q_item_strengthBean born = DataManager.getInstance().q_item_strengthContainer.getMap().get(getItemModelId()+"_"+gradeNum);
			if(born!=null){
				value+=born.getQ_speed();
			}
		}
		return value;
	}
	
	/**
	 * 获取装备增加幸运
	 * @return
	 */
	public int getLuck() {
		//获取基本幸运
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
		if(model==null){
			log.error("Item model " + this.getItemModelId() + " not found!");
			return 0;
		}
		int value = model.getQ_luck();
		Q_equip_appendBean append = ManagerPool.dataManager.q_equip_appendContainer.getMap().get(this.getItemModelId());
		if(append!=null){
			//计算附加属性
			for (int i = 0; i < attributes.size(); i++) {
				Attribute attribute = attributes.get(i);
				if(attribute.getType() == Attributes.LUCK.getValue()){
					value += (int)((append.getQ_luck_min() + (double)(append.getQ_luck_max() - append.getQ_luck_min()) * attribute.getValue() / 100) * getQualityAdd() / Global.MAX_PROBABILITY);
				}
			}
			if(gradeNum>0){
				//天生属性
				Q_item_strengthBean born = DataManager.getInstance().q_item_strengthContainer.getMap().get(getItemModelId()+"_"+gradeNum);
				if(born!=null){
					value+=born.getQ_luck();
				}
			}
		}
		return value;
	}
	
	/**
	 * 是否顶级强化
	 * @return
	 */
	public boolean isFullStrength(){
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
		if(model==null){
			log.error("Item model " + this.getItemModelId() + " not found!");
			return false;
		}
		if(this.getGradeNum() == model.getQ_max_strengthen()){
			return true;
		}
		return false;
	}
	
	/**
	 * 是否顶级附加
	 * @return
	 */
	public boolean isFullAppend(){
		if(attributes!=null && attributes.size() == 6){
			for (int i = 0; i < 6; i++) {
				Attribute attribute = attributes.get(i);
				if(attribute.getValue() < 100) return false;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 获得品质 0-白色 1-黄色 2-绿色 3-蓝色 4-紫色
	 * @return
	 */
	public int getQuality(){
		if(attributes!=null && attributes.size() > 0) {
			int num = attributes.size();
			if(num < 2){
				//白色装备
				return 0;
			}else if(num < 4){
				//黄色装备
				return 1;
			}else if(num == 4){
				//绿色装备
				return 2;
			}else if(num == 5){
				//蓝色装备
				return 3;
			}else if(num == 6){
				//紫色装备
				return 4;
			}
		}
		return 0;
	}
	
	/**
	 * 获得品质加成数值
	 * @return
	 */
	public int getQualityAdd(){
		if(attributes!=null && attributes.size() > 0) {
			int num = attributes.size();
			if(num < 2){
				//白色装备
				return Global.MAX_PROBABILITY;
			}else if(num < 4){
				//黄色装备
				return Global.YELLOW_EQUIP_ADD;
			}else if(num == 4){
				//绿色装备
				return Global.GREEN_EQUIP_ADD;
			}else if(num == 5){
				//蓝色装备
				return Global.BLUE_EQUIP_ADD;
			}else if(num == 6){
				//紫色装备
				return Global.PURPLE_EQUIP_ADD;
			}
		}
		return Global.MAX_PROBABILITY;
	}
	
	/**
	 * 是否顶级镶嵌
	 * @return
	 */
	public boolean isFullEnchase(){
		return false;
	}
	
	
	/**最高星数强化失败次数
	 * 
	 * @return
	 */
	public short getGradefailnum() {
		return gradefailnum;
	}
	/**最高星数强化失败次数
	 * 
	 * @return
	 */
	public void setGradefailnum(short gradefailnum) {
		this.gradefailnum = gradefailnum;
	}
	/**曾进行强化的最高星数
	 * 
	 * @return
	 */
	public byte getHighestgrade() {
		return highestgrade;
	}
	/**曾进行强化的最高星数
	 * 
	 * @param highestgrade
	 */
	public void setHighestgrade(byte highestgrade) {
		this.highestgrade = highestgrade;
	}
	/**进阶失败次数
	 * 
	 * @return
	 */
	public short getAdvfailnum() {
		return advfailnum;
	}
	/**进阶失败次数
	 * 
	 * @return
	 */
	public void setAdvfailnum(short advfailnum) {
		this.advfailnum = advfailnum;
	}
}
