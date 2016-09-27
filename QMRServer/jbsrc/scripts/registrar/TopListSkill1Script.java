package scripts.registrar;

import java.util.*;
import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.json.JSONserializable;
import com.game.mail.manager.MailServerManager;
import com.game.player.structs.Player;
import com.game.registrar.script.IRegistrar;
import com.game.structs.Reasons;

public class TopListSkill1Script implements IRegistrar {

	private static Logger faillog = Logger.getLogger("GIVEREWARDFAILED");
	@Override
	public int getId() {
		return 1208;
	}

	@Override
	public String getFirstReward(Player player) {
		return "";
	}

	@Override
	public String getCommonReward(Player player) { 
		return "";
	}

	@Override
	public void giveFirstReward(Player player) {
		
	}

	@Override
	public void giveCommonReward(Player player) { 
		if(!BackpackManager.getInstance().changeBindGold(player, 10000, Reasons.ACTIVITY_GIFT, Config.getId())){
			String title="武功排行榜第一名活动奖励";
			String content="武功排行榜第一名活动奖励";
			List<Item> items = Item.createItems(-5, 10000, true, 0);
			if(!MailServerManager.getInstance().sendSystemMail(Long.valueOf(player.getId()), player.getName(), title, content, (byte)0, 0, items)){
				faillog.info("[Player"+player.getName()+"|"+player.getId()+"]"+"等级排行榜第一名发奖励发邮件失败"+JSONserializable.toString(items));
			}
		}
	}

	@Override
	public void callWorld(Player player) {
		
	}

	@Override
	public void worldCallback(List<Object> paras) {
		
	}

}
