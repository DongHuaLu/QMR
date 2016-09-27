package scripts.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.buff.structs.Buff;
import com.game.config.Config;
import com.game.gem.struts.Gem;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;


/** 宝石属性道具
 * 
 * @author zhangxiangxi
 *
 */
public class GemRmbItem implements IItemScript{
	@Override
	public int getId() {
		return 1009199;	
	}
	
	@Override
	public boolean use(Item item, Player player, String... parameters) {
		int existBuff = 0;
		int addBuff = 0;
		String stoneName = null;
		addGemBuff(player);
		if (item.getItemModelId() == 9199) {
			existBuff = ManagerPool.dataManager.q_globalContainer.getMap().get(87).getQ_int_value();
			addBuff = 9169;
			stoneName = new String("五彩斑斓");
		} else if (item.getItemModelId() == 9200) {
			existBuff = ManagerPool.dataManager.q_globalContainer.getMap().get(88).getQ_int_value();
			addBuff = 9170;
			stoneName = new String("耀目琳琅");
		} else if (item.getItemModelId() == 9201) {
			existBuff = ManagerPool.dataManager.q_globalContainer.getMap().get(89).getQ_int_value();
			addBuff = 9171;
			stoneName = new String("神光贵胄");
		} else {
			return false;
		}
		
		List<Buff> buffs = ManagerPool.buffManager.getBuffByModelId(player, existBuff);
		if (buffs == null || buffs.size() < 1) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("无法使用,未激活" + stoneName));
			return false;
		}
		// 判断是否已经有了
		List<Buff> oldBuffs = ManagerPool.buffManager.getBuffByModelId(player, addBuff);
		if (oldBuffs != null && oldBuffs.size() > 0) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("无法使用,已经使用过"));
			return false;
		}
		if (ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GOODUSE, Config.getId())) {
			ManagerPool.buffManager.addBuff(player, player, addBuff, 0, 0, 0);
		}
		return false;
	}

	
	
	

	/**宝石BUFF条件检测
	 * @return 
	 * 
	 */
	public int getGemBuffType(Player player ){
		List<Integer> tab = new ArrayList<Integer>();
		Gem[][] gems = player.getGems();
		for (int i = 0; i < gems.length; i++) {
			Gem[] gempos= gems[i];
			int three = 0;	//三级宝石数量
			int six = 0;	//六级宝石数量
			for (int j = 0; j < gempos.length; j++) {
				if (gempos[j] != null ) {
					if(gempos[j].getIsact() == 1 ){
						if (gempos[j].getLevel() >= 6) {
							six = six + 1;
							three = three + 1;
						}else if(gempos[j].getLevel() >= 3){
							three = three + 1;
						}
					}
				}
			}
			if (six >= 5) {
				tab.add(3);
			}else if (six >= 3) {
				tab.add(2);
			}else if (three >= 3) {
				tab.add(1);
			}else {
				tab.add(0);
			}
		}
		if (gems.length == tab.size()) {
			int s = 3;
			for (Integer i : tab) {
				if (i < s ) {
					s= i;
				}
			}
			return s;	//0 不加BUFF，1=3颗3级，2=3颗六级，3=5颗6级
		}
		return 0;
	}
	
	
	/**加宝石BUFF
	 * 
	 * @param player
	 */
	public void addGemBuff(Player player){
		int type = getGemBuffType(player);//0 不加BUFF，1=3颗3级，2=3颗六级，3=5颗6级
		int gembuffa = ManagerPool.dataManager.q_globalContainer.getMap().get(87).getQ_int_value();//3颗3级
		int gembuffb = ManagerPool.dataManager.q_globalContainer.getMap().get(88).getQ_int_value();//3颗六级
		int gembuffc = ManagerPool.dataManager.q_globalContainer.getMap().get(89).getQ_int_value();//5颗6级
		if (type > 0) {
			if (type >= 1) {
				List<Buff> buffs = ManagerPool.buffManager.getBuffByModelId(player, gembuffa);
				if (buffs == null || buffs.size() < 1) {
					ManagerPool.buffManager.addBuff(player, player, gembuffa, 0, 0, 0);
				}
			}
			
			if (type >= 2) {
				List<Buff> buffs = ManagerPool.buffManager.getBuffByModelId(player, gembuffb);
				if (buffs == null || buffs.size() < 1) {
					ManagerPool.buffManager.addBuff(player, player, gembuffb, 0, 0, 0);
				}
			}
			
			if (type >= 3) {
				List<Buff> buffs = ManagerPool.buffManager.getBuffByModelId(player, gembuffc);
				if (buffs == null || buffs.size() < 1) {
					ManagerPool.buffManager.addBuff(player, player, gembuffc, 0, 0, 0);
				}
			}
		}
	}
	
}
