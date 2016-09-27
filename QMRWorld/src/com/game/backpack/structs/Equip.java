package com.game.backpack.structs;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_equip_appendBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_item_strengthBean;
import com.game.data.manager.DataManager;
import com.game.manager.ManagerPool;
import com.game.structs.Attributes;

/**
 * 装备类
 * @author heyang szy_heyang@163.com
 *
 */
public class Equip extends Item {

	private Logger log = Logger.getLogger(Equip.class);
	
	private static final long serialVersionUID = -6709344489920034517L;
	//强化次数
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
	
	
	//最高星数强化失败次数
	private short gradefailnum ;
	//曾进行强化的最高星数
	private byte highestgrade;
	//进阶失败次数
	private short advfailnum ;
	
	
	
	public int getGradeNum() {
		return gradeNum;
	}

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
					value += (int)((append.getQ_attack_min() + (append.getQ_attack_max() - append.getQ_attack_min()) * attribute.getValue() / 100 )*  getQualityAdd() / 10000);
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
					value += (int)((append.getQ_defence_min() + (append.getQ_defence_max() - append.getQ_defence_min()) * attribute.getValue() / 100) *  getQualityAdd() / 10000);
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
					value += (int)((append.getQ_crit_min() + (append.getQ_crit_max() - append.getQ_crit_min()) * attribute.getValue() / 100 )*  getQualityAdd() / 10000);
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
					value += (int)((append.getQ_dodge_min() + (append.getQ_dodge_max() - append.getQ_dodge_min()) * attribute.getValue() / 100 ) *  getQualityAdd() / 10000);
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
					value += (int)((append.getQ_hp_min() + (append.getQ_hp_max() - append.getQ_hp_min()) * attribute.getValue() / 100) *  getQualityAdd() / 10000);
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
					value += (int)((append.getQ_mp_min() + (append.getQ_mp_max() - append.getQ_mp_min()) * attribute.getValue() / 100 ) *  getQualityAdd() / 10000);
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
					value += (int)((append.getQ_sp_min() + (append.getQ_sp_max() - append.getQ_sp_min()) * attribute.getValue() / 100) *  getQualityAdd() / 10000);
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
					value += (int)((append.getQ_attackspeed_min() + (append.getQ_attackspeed_max() - append.getQ_attackspeed_min()) * attribute.getValue() / 100) *  getQualityAdd() / 10000);
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
					value += (int)((append.getQ_speed_min() + (append.getQ_speed_max() - append.getQ_speed_min()) * attribute.getValue() / 100) *  getQualityAdd() / 10000);
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
					value += (int)((append.getQ_luck_min() + (append.getQ_luck_max() - append.getQ_luck_min()) * attribute.getValue() / 100 )*  getQualityAdd() / 10000);
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
	 * 黄色装备加成
	 * 黄色品质装备附加属性修正系数：10500
	 */
	public static int YELLOW_EQUIP_ADD = 10500;
	
	/**
	 * 绿色装备加成
	 * 绿色品质装备附加属性修正系数：11500
	 */
	public static int GREEN_EQUIP_ADD = 11500;
	
	/**
	 * 蓝色装备加成
	 * 蓝色品质装备附加属性修正系数：15000
	 */
	public static int BLUE_EQUIP_ADD = 15000;
	
	/**
	 * 紫色装备加成
	 * 紫色品质装备附加属性修正系数：20000
	 */
	public static int PURPLE_EQUIP_ADD = 20000;
	
	
	
	/**
	 * 获得品质加成数值
	 * @return
	 */
	public int getQualityAdd(){
		if(attributes!=null && attributes.size() > 0) {
			int num = attributes.size();
			if(num < 2){
				//白色装备
				return 10000;
			}else if(num < 4){
				//黄色装备
				return YELLOW_EQUIP_ADD;
			}else if(num == 4){
				//绿色装备
				return GREEN_EQUIP_ADD;
			}else if(num == 5){
				//蓝色装备
				return BLUE_EQUIP_ADD;
			}else if(num == 6){
				//紫色装备
				return PURPLE_EQUIP_ADD;
			}
		}
		return 10000;
	}
	
	
	/**
	 * 是否顶级镶嵌
	 * @return
	 */
	public boolean isFullEnchase(){
		return false;
	}

	public short getGradefailnum() {
		return gradefailnum;
	}

	public void setGradefailnum(short gradefailnum) {
		this.gradefailnum = gradefailnum;
	}

	public byte getHighestgrade() {
		return highestgrade;
	}

	public void setHighestgrade(byte highestgrade) {
		this.highestgrade = highestgrade;
	}

	public short getAdvfailnum() {
		return advfailnum;
	}

	public void setAdvfailnum(short advfailnum) {
		this.advfailnum = advfailnum;
	}
}
