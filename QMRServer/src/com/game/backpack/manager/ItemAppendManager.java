package com.game.backpack.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.backpack.structs.Attribute;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.data.bean.Q_equip_appendBean;
import com.game.data.manager.DataManager;
import com.game.structs.Attributes;
import com.game.utils.RandomUtils;
import com.game.utils.StringUtil;
import com.game.utils.Symbol;
/**
 * 
 * @author 赵聪慧
 *
 */
public class ItemAppendManager {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ItemAppendManager.class);

	// 玩家管理类实例
	private static ItemAppendManager manager;

	private ItemAppendManager() {
	}
	private static Object obj = new Object();
	public static ItemAppendManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new ItemAppendManager();
			}
		}
		return manager;
	}
	
	/**
	 * 装备
	 * @param equip
	 * @param count	条数
	 * @return
	 */
	public Item buildAppend(Equip equip,int count){
		while (count>0) {
			Attribute generateAttribute = generateAttribute(equip.getItemModelId());
			if(generateAttribute!=null){
				equip.getAttributes().add(generateAttribute);	
			}
			count--;
		}		
		return equip;
	}
	private Attribute generateAttribute(int itemmodel){
		Q_equip_appendBean appendModel = DataManager.getInstance().q_equip_appendContainer.getMap().get(itemmodel);
		if(appendModel==null)return null;
		List<Integer> keys=new ArrayList<Integer>();
		List<Integer> probs=new ArrayList<Integer>();
		if(appendModel.getQ_attack()!=0){
			probs.add(appendModel.getQ_attack());
			keys.add(0);
		}
		if(appendModel.getQ_attackspeed()!=0){
			probs.add(appendModel.getQ_attackspeed());
			keys.add(1);
		}
		if(appendModel.getQ_defence()!=0){
			probs.add(appendModel.getQ_defence());
			keys.add(2);
		}
		if(appendModel.getQ_crit()!=0){
			probs.add(appendModel.getQ_crit());
			keys.add(3);
		}
		if(appendModel.getQ_dodge()!=0){
			probs.add(appendModel.getQ_dodge());
			keys.add(4);
		}
		if(appendModel.getQ_dodge()!=0){
			probs.add(appendModel.getQ_speed());
			keys.add(5);
		}
		if(appendModel.getQ_hp()!=0){
			probs.add(appendModel.getQ_hp());
			keys.add(6);
		}
		if(appendModel.getQ_mp()!=0){
			probs.add(appendModel.getQ_mp());
			keys.add(7);
		}
		if(appendModel.getQ_sp()!=0){
			probs.add(appendModel.getQ_sp());
			keys.add(8);
		}
		if(appendModel.getQ_luck()!=0){
			probs.add(appendModel.getQ_sp());
			keys.add(9);
		}
		int index = RandomUtils.randomIndexByProb(probs);
		if(index==-1){
			logger.error("机率计算出错"+probs);
			return null;
		}
		Integer integer = keys.get(index);
		Attribute attribute=new Attribute();
		switch (integer) {
		case 0:
			attribute.setType(Attributes.ATTACK.getValue());
			attribute.setValue(RandomUtils.random(appendModel.getQ_attack_min(),appendModel.getQ_attack_max()));
			break;
		case 1:
			attribute.setType(Attributes.ATTACKSPEED.getValue());
			attribute.setValue(RandomUtils.random(appendModel.getQ_attackspeed_min(),appendModel.getQ_attackspeed_max()));
			break;
		case 2:
			attribute.setType(Attributes.DEFENSE.getValue());
			attribute.setValue(RandomUtils.random(appendModel.getQ_defence_min(),appendModel.getQ_defence_max()));
			break;
		case 3:
			attribute.setType(Attributes.CRIT.getValue());
			attribute.setValue(RandomUtils.random(appendModel.getQ_crit_min(),appendModel.getQ_crit_max()));
			break;
		case 4:
			attribute.setType(Attributes.DODGE.getValue());
			attribute.setValue(RandomUtils.random(appendModel.getQ_dodge_min(),appendModel.getQ_dodge_max()));
			break;
		case 5:
			attribute.setType(Attributes.SPEED.getValue());
			attribute.setValue(RandomUtils.random(appendModel.getQ_speed_min(),appendModel.getQ_speed_max()));
			break;
		case 6:
			attribute.setType(Attributes.MAXHP.getValue());
			attribute.setValue(RandomUtils.random(appendModel.getQ_hp_min(),appendModel.getQ_hp_max()));
			break;
		case 7:
			attribute.setType(Attributes.MAXMP.getValue());
			attribute.setValue(RandomUtils.random(appendModel.getQ_mp_min(),appendModel.getQ_mp_max()));
			break;
		case 8:
			attribute.setType(Attributes.MAXSP.getValue());
			attribute.setValue(RandomUtils.random(appendModel.getQ_sp_min(),appendModel.getQ_sp_max()));
			break;
		case 9:
			attribute.setType(Attributes.LUCK.getValue());
			attribute.setValue(RandomUtils.random(appendModel.getQ_luck_min(),appendModel.getQ_luck_max()));
			break;
		default:
			return null;
		}	
		if(attribute.getValue()<=0){
			return null;
		}
		return attribute;
	}
	
	/**
	 * 
	 * @param equip
	 * @param append 属性类型|属性值;属性类型|属性值;属性类型|属性值;
	 * @return
	 */
	public Item buildAppend(Equip equip,String append){
		if (append != null && !("").equals(append)) {
			equip.getAttributes().clear();
			String[] attributes = append.split(Symbol.FENHAO_REG);
			for (int i = 0; i < attributes.length; i++) {
				if(!StringUtil.isBlank(attributes[i])){
					String[] strs = attributes[i].split("\\|");
					Attribute attribute = new Attribute();
					attribute.setType(Integer.parseInt(strs[0]));
					attribute.setValue(Integer.parseInt(strs[1]));
					equip.getAttributes().add(attribute);	
				}
			}
		}
		return equip;
	}

	public int generateAttribute(int type, Q_equip_appendBean appendModel) {
		if (type == Attributes.ATTACK.getValue())
			return RandomUtils.random(appendModel.getQ_attack_min(),appendModel.getQ_attack_max());
		if (type == Attributes.ATTACKSPEED.getValue())
			return RandomUtils.random(appendModel.getQ_attackspeed_min(),appendModel.getQ_attackspeed_max());
		if (type == Attributes.DEFENSE.getValue())
			return RandomUtils.random(appendModel.getQ_defence_min(),appendModel.getQ_defence_max());
		if (type == Attributes.CRIT.getValue())
			return RandomUtils.random(appendModel.getQ_crit_min(),appendModel.getQ_crit_max());
		if (type == Attributes.DODGE.getValue())
			return RandomUtils.random(appendModel.getQ_dodge_min(),appendModel.getQ_dodge_max());
		if (type == Attributes.SPEED.getValue())
			return RandomUtils.random(appendModel.getQ_speed_min(),appendModel.getQ_speed_max());
		if (type == Attributes.MAXHP.getValue())
			return RandomUtils.random(appendModel.getQ_hp_min(),appendModel.getQ_hp_max());
		if (type == Attributes.MAXMP.getValue())
			return RandomUtils.random(appendModel.getQ_mp_min(),appendModel.getQ_mp_max());
		if (type == Attributes.MAXSP.getValue())
			return RandomUtils.random(appendModel.getQ_sp_min(),appendModel.getQ_sp_max());
		if (type == Attributes.LUCK.getValue())
			return RandomUtils.random(appendModel.getQ_luck_min(),appendModel.getQ_luck_max());
		return 0;
	}
}
