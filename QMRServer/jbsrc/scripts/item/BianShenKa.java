package scripts.item;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;

/**
 * 变身卡
 */
public class BianShenKa implements IItemScript {
	
	private int[] monsters = new int[]{10, 30, 40, 60, 70, 80, 91, 100, 110, 120, 130, 140, 151, 160, 170, 180, 190, 200, 211, 220, 
			230, 240, 241, 250, 261, 270, 280, 290, 310, 320, 330, 341, 350, 360, 370, 391, 410, 420, 430, 441, 
			450, 460, 470, 480, 490, 492, 500, 510, 511, 520, 530, 550, 560, 570, 590, 600, 610, 620, 640, 650, 
			660, 670};


	@Override
	public int getId() {
		return 1009146;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		if(ManagerPool.marriageManager.getWeddingstatus() == 1){
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, "婚宴时间不能使用变身卡");
			return false;
		}
		
		int monster = monsters[RandomUtils.random(monsters.length)];
		Q_itemBean bean = ManagerPool.dataManager.q_itemContainer.getMap().get(item.getItemModelId());
		if(bean==null){
			return false;
		}
		String[] buffs = bean.getQ_buff().split(Symbol.FENHAO_REG);
		int buffId = Integer.parseInt(buffs[0]);
		if(ManagerPool.buffManager.addBuff(player, player, buffId, 0, monster, 0)==1){
			BackpackManager.getInstance().removeItem(player, item, 1, Reasons.GOODUSE, Config.getId());
			return true;
		}
		return false;
	}

}
