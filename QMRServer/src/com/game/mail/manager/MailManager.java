package com.game.mail.manager;

import java.util.ArrayList;
import java.util.List;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.structs.Reasons;
import com.game.utils.Global;

/**
 * 批量发放物品   发放失败则发送邮件
 * @author 赵聪慧
 * @2012-10-9 下午1:54:19
 */
public class MailManager {
	
	public void addItems(Player player,List<Item> items,String title,String notice,Reasons reasons,long action){
		List<Item> tmp=new ArrayList<Item>();
		List<Item> special=new ArrayList<Item>();
		tmp.addAll(items);
		while (tmp.iterator().hasNext()) {
			Item item = (Item) tmp.iterator().next();
			if(item.getItemModelId()<0){
				tmp.iterator().remove();
				special.add(item);
			}
		}
		boolean tag=true;
		for (Item item : special) {
			// -1铜币，-2元宝，-3经验，-4真气  -5绑定元宝
			switch (item.getItemModelId()) {
			case -1:
				if (player.getMoney() + item.getNum() > Global.BAG_MAX_COPPER)tag = false;
				break;
//			case -2:
//				if(player.getGold()!=null&&player.getGold().getGold()+item.getNum() > Global.BAG_MAX_GOLD)tag=false;
//				break;
			case -3:
				if(PlayerManager.getInstance().isTopLevel(player))tag=false;
				break;
			case -4:
				if(PlayerManager.getInstance().isFullZq(player))tag=false;
				break;
			case -5:
				if(player.getBindGold()+item.getNum()>Global.BAG_MAX_GOLD)tag=false;
				break;
			}
		}
		if(!BackpackManager.getInstance().hasAddSpace(player, tmp)){
			tag=false;
		}
		if(tag){
			for (Item item : special) {
				// -1铜币，-2元宝，-3经验，-4真气  -5绑定元宝
				switch (item.getItemModelId()) {
				case -1:
					BackpackManager.getInstance().changeMoney(player, item.getNum(), reasons, action);
					break;
//				case -2:
//					BackpackManager.getInstance().addGold(player, item.getNum(), reasons, action);
//					break;
				case -3:
					PlayerManager.getInstance().addExp(player, item.getNum(), AttributeChangeReason.MAIL);
					break;
				case -4:
					PlayerManager.getInstance().addZhenqi(player, item.getNum(), AttributeChangeReason.MAIL);
					break;
				case -5:
					BackpackManager.getInstance().changeBindGold(player, item.getNum(), reasons, action);
					break;
				}
			}
		}else{
			MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), title, notice,(byte)0,0, items);	
		}
	}
}
