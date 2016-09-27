package scripts.item;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_gem_activationBean;
import com.game.data.bean.Q_gem_upBean;
import com.game.gem.message.ResGemActivationORUpMessage;
import com.game.gem.struts.Gem;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;

/**激活极品宝石
 * 
 * @author zhangrong
 *
 */

public class GemActivation  implements IItemScript {
	@Override
	public int getId() {
		return 1016012;	
	}
	
	
	@Override
	public boolean use(Item item, Player player, String... parameters) {
		Gem[][] gems = player.getGems();
		String name = "";
		int type= gettype(item.getItemModelId());	//得到对应道具的宝石类型
		if (type == 0) {
			return false;
		}
		String itemname = item.acqItemModel().getQ_name();
		int zhenpos = 0;
		Gem gem = null;
		for (int i = 0; i < gems.length; i++) {
			Gem[] gemspos = gems[i];
			zhenpos = i;
			for (int j = 3; j < gemspos.length; j++) {
				if (gems[i][0].getLevel() >= 6 && gems[i][1].getLevel() >= 6 && gems[i][2].getLevel() >= 6) {
					int pos = i+1;
					int idx = j+1;
					String id = pos+"_"+idx;
					Q_gem_activationBean gemactdata= ManagerPool.gemManager.getGemActData(id);
					String posname = ManagerPool.equipstrengManager.getPosname(pos);
					if (gems[i][3].getIsact() == 0 && gemactdata.getQ_gem_type() == type) {
						gem = gems[i][3];
						Q_gem_upBean newdata =  ManagerPool.gemManager.getGemUpData(gemactdata.getQ_gem_type()+"_"+gems[i][3].getLevel());
						name = posname+ResManager.getInstance().getString("部位的")+newdata.getQ_gem_name();
						break;
					}else {
						if (gems[i][4].getIsact() == 0 &&  gemactdata.getQ_gem_type() == type) {
							gem = gems[i][4];
							Q_gem_upBean newdata =  ManagerPool.gemManager.getGemUpData(gemactdata.getQ_gem_type()+"_"+gems[i][4].getLevel());
							name = posname+ResManager.getInstance().getString("部位的")+newdata.getQ_gem_name();
							break;
						}
					}
				}
			}
			if (gem != null) {
				if( !ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.def1,Config.getId()) ){
					return false;	//扣道具失败
				}
				
				gem.setIsact((byte) 1);
				gem.setFailactnum((short) 0);
				MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜您，")+name+ResManager.getInstance().getString("激活成功。"));
				ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.GEM);
				//ManagerPool.gemManager.refreshGem(player);
				ResGemActivationORUpMessage smsg = new ResGemActivationORUpMessage();
				smsg.setPos((byte) zhenpos);
				smsg.setResult((byte) 1);
				smsg.setGeminfo(ManagerPool.gemManager.getGeminfo(gem,zhenpos,gem.getGrid()));
				MessageUtil.tell_player_message(player, smsg);
				MessageUtil.notify_All_player( Notifys.CHAT_BULL, ResManager.getInstance().getString("恭喜【{1}】使用{2}激活了一颗{3}【参加极品宝石激活符活动】"),player.getName(),itemname,name);
				return true;
			}
		}
		
		
		MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("没有需要激活的极品宝石，可能前面3颗普通宝石未到6级，或者极品宝石已经全部激活."));
		return false;
	}
	
	
	public int gettype(int itemmodelid){
		switch (itemmodelid) {
		case 16012:
			return 6;
		case 16013:
			return 8;
		case 16014:
			return 7;
		case 16015:
			return 9;
		case 16016:
			return 10;
		}
		return 0;
	}

	
	public Gem getgembyid(Player player ,long id ){
		Gem[][] gems = player.getGems();
		for (int i = 0; i < gems.length; i++) {
			Gem[] gemspos = gems[i];
			for (int j = 0; j < gemspos.length; j++) {
				
				if(gemspos[j].getId() == id){
					return gemspos[j];
				}
			}
		}
		return null;
	}
	
	
	
}