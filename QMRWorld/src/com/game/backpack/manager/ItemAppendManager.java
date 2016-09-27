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
		List<Integer> probs=new ArrayList<Integer>();
		probs.add(appendModel.getQ_attack());
		probs.add(appendModel.getQ_attackspeed());
		probs.add(appendModel.getQ_defence());
		probs.add(appendModel.getQ_crit());
		probs.add(appendModel.getQ_dodge());
		probs.add(appendModel.getQ_speed());
		probs.add(appendModel.getQ_hp());
		probs.add(appendModel.getQ_mp());
		probs.add(appendModel.getQ_sp());
		probs.add(appendModel.getQ_luck());
		int index = RandomUtils.randomIndexByProb(probs);
		if(index==-1){
			logger.error("机率计算出错"+probs);
			return null;
		}
		Attribute attribute=new Attribute();
		switch (index) {
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
				String[] strs = attributes[i].split("\\|");
				Attribute attribute = new Attribute();
				attribute.setType(Integer.parseInt(strs[0]));
				attribute.setValue(Integer.parseInt(strs[1]));
				equip.getAttributes().add(attribute);
			}
		}
		return equip;
	}
}
