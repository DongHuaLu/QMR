package com.game.spirittree.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.backpack.structs.Attribute;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.data.bean.Q_equip_appendBean;
import com.game.data.bean.Q_spirittree_pack_conBean;
import com.game.data.manager.DataManager;
import com.game.manager.ManagerPool;
import com.game.spirittree.structs.FruitReward;
import com.game.spirittree.structs.FruitRewardAttr;
import com.game.structs.Attributes;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;

public class FruitAppendManager {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FruitAppendManager.class);

	// 玩家管理类实例
	private static FruitAppendManager manager;

	private FruitAppendManager() {
	}
	private static Object obj = new Object();
	public static FruitAppendManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new FruitAppendManager();
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
	public FruitReward buildAppend(FruitReward fruitReward,int count){
		while (count>0) {
			FruitRewardAttr fruitRewardAttr = generateAttribute(fruitReward);
			if(fruitRewardAttr!=null){
				fruitReward.getFruitRewardAttrslist().add(fruitRewardAttr);	
			}
			count--;
		}		
		return fruitReward;
	}
	private FruitRewardAttr generateAttribute(FruitReward fruitReward){
		Q_equip_appendBean appendModel = DataManager.getInstance().q_equip_appendContainer.getMap().get(fruitReward.getItemModelid());
		Q_spirittree_pack_conBean pack = ManagerPool.spiritTreeManager.getpackcondata().get(fruitReward.getIdx());
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
		FruitRewardAttr attribute=new FruitRewardAttr();
		switch (index) {
		case 0:
			attribute.setType(Attributes.ATTACK.getValue());
			
			break;
		case 1:
			attribute.setType(Attributes.ATTACKSPEED.getValue());
			
			break;
		case 2:
			attribute.setType(Attributes.DEFENSE.getValue());
			
			break;
		case 3:
			attribute.setType(Attributes.CRIT.getValue());
			
			break;
		case 4:
			attribute.setType(Attributes.DODGE.getValue());
			
			break;
		case 5:
			attribute.setType(Attributes.SPEED.getValue());
			
			break;
		case 6:
			attribute.setType(Attributes.MAXHP.getValue());
			
			break;
		case 7:
			attribute.setType(Attributes.MAXMP.getValue());
			
			break;
		case 8:
			attribute.setType(Attributes.MAXSP.getValue());
			
			break;
		case 9:
			attribute.setType(Attributes.LUCK.getValue());
			
			break;
		default:
			return null;
		}
		attribute.setValue(RandomUtils.random(pack.getQ_addProperty_min(),pack.getQ_addProperty_max()));
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
