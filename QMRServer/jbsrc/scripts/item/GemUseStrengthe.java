//package com.game.zones.script;


package scripts.item;

import java.util.ArrayList;
import java.util.List;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.buff.structs.Buff;
import com.game.chat.bean.GoodsInfoRes;
import com.game.config.Config;
import com.game.data.bean.Q_gem_activationBean;
import com.game.data.bean.Q_gem_upBean;
import com.game.fightpower.manager.FightPowerManager;
import com.game.gem.message.ResGemStrengthenPanelMessage;
import com.game.gem.struts.Gem;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.scripts.bean.PanelInfo;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.NpcParamUtil;
import com.game.utils.ParseUtil;
import com.game.vip.manager.VipManager;
import com.game.vip.struts.GuideType;


public class GemUseStrengthe implements IItemScript {
	@Override
	public int getId() {
		return 1001027;	
	}

	private int zumuluqianghuaid =	1028; //祖母绿强化符
	private int lanyusuiqianghuaid = 1027;// 蓝玉髓强化符
	private int wucaijianyingshi = 	9130; //五彩坚硬石（使用后强化6级低级宝石）
	private int qicaijingshi = 	9131; //七彩晶石 （宝石全部6级别后使用，加BUFF）
	
	private String key = "drop_1027_use";
	

	@Override
	public boolean use(Item item, Player player, String... parameters) {
//		ResScriptPaneleMessage cmsg = new ResScriptPaneleMessage();
//		cmsg.setScriptId(getId());
		if (item.getItemModelId() == zumuluqianghuaid || item.getItemModelId() == lanyusuiqianghuaid) {
			ResGemStrengthenPanelMessage cmsg = new ResGemStrengthenPanelMessage();
			cmsg.setItemmodelid(item.getItemModelId());
			cmsg.setItemid(item.getId());
			MessageUtil.tell_player_message(player, cmsg);
		}else if (item.getItemModelId() == wucaijianyingshi  ) {//五彩坚硬石（使用后强化6级低级宝石）
			PanelInfo panel = NpcParamUtil.getPanelInfo(player, 3);
			List<String> list = new ArrayList<String>();
			list.add("btnStarting#1001027#wucaijianyingshi_act#"+item.getId());
			panel.setButtoninfolist(NpcParamUtil.getbuttonInfo(player , list));
			NpcParamUtil.showPanel(player , panel);

			
		}else if ( item.getItemModelId() == qicaijingshi) { //七彩晶石 （宝石全部6级别后使用，加BUFF）
			PanelInfo panel = NpcParamUtil.getPanelInfo(player, 2);
			List<String> list = new ArrayList<String>();
			list.add("btnStarting#1001027#qicaijingshi_act#"+item.getId());
			panel.setButtoninfolist(NpcParamUtil.getbuttonInfo(player , list));
			NpcParamUtil.showPanel(player , panel);
		}
		return true;
	}
	
	
	

	public void button(List<Object> para){
		Player player = (Player) para.get(0);
		if (player == null ) {
			return ;
		}
		long itemid = Long.valueOf((String)para.get(1), 16);
		int itemmodelid = Integer.valueOf((String)para.get(2));
		int type = 0;
		String oldname="";
		String newname="";
		Item item = ManagerPool.backpackManager.getItemById(player, itemid);
		if (itemmodelid == zumuluqianghuaid) {
			type = 2;//初级祖母绿
		}else if (itemmodelid == lanyusuiqianghuaid ) {
			type = 3 ;//初级蓝玉髓
		}
		String itemname=item.acqItemModel().getQ_name();
		
		//检测可提升宝石
		boolean is = false;
		Gem[][] gems = player.getGems();
		for (int i = 0; i < gems.length; i++) {
			Gem[] gempos = ManagerPool.gemManager.getPosGems(player,i);	//得到位置上可显示的宝石
			for (int j = 0; j < gempos.length; j++) {
				int pos = i+1;
				int idx = j+1;
				String id = pos+"_"+idx;
				Q_gem_activationBean gemactdata= ManagerPool.gemManager.getGemActData(id);
				if (gemactdata != null) {
					if (gempos[j].getLevel() == 6 && gemactdata.getQ_gem_type() == type) {
						is = true;
					}
				}
			}
		}
		
		if (is ==false) {
			MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您没有符合提升条件的宝石。"));
			return;	
		}
		
		if( !ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GEMQIANGHUA,Config.getId()) ){
			return ;	//扣道具失败
		}
		
		if (type > 0) {//对宝石进行提升
			int num = 0 ;
			int oldfightpower=FightPowerManager.getInstance().calAllFightPower(player);
			for (int i = 0; i < gems.length; i++) {
				Gem[] gempos = ManagerPool.gemManager.getPosGems(player,i);	//得到位置上可显示的宝石
				for (int j = 0; j < gempos.length; j++) {
					int pos = i+1;
					int idx = j+1;
					String id = pos+"_"+idx;
					Q_gem_activationBean gemactdata= ManagerPool.gemManager.getGemActData(id);
					if (gemactdata != null) {
						if (gempos[j].getLevel() == 6 && gemactdata.getQ_gem_type() == type) {
							Q_gem_upBean olddata =  ManagerPool.gemManager.getGemUpData(type+"_"+gempos[j].getLevel());
							gempos[j].setLevel(gempos[j].getLevel()+100);
							Q_gem_upBean newdata =  ManagerPool.gemManager.getGemUpData(type+"_"+gempos[j].getLevel());
							oldname = olddata.getQ_gem_name();
							newname = newdata.getQ_gem_name();
							num = num + 1;
						}
					}
				}
			}
			if (newname.length() > 1) {
				MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜您把【{1}】升级为【{2}】"), oldname,newname);
				ManagerPool.gemManager.refreshGem(player);
				ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.GEM);
				if (itemmodelid == lanyusuiqianghuaid ) {
					player.getActivitiesReward().put(key, "1027");
				}
				int newfightpower=FightPowerManager.getInstance().calAllFightPower(player);
				int fightpower = newfightpower - oldfightpower;
				ParseUtil parseUtil = new ParseUtil();
				parseUtil.setValue(String.format(ResManager.getInstance().getString("玩家【%s】使用【%s】使得%d颗%s属性加倍,战斗力提升%d!{@}"), player.getName(), itemname,num,oldname,fightpower), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.GEM_QH.getValue()));
				MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.GEM_QH.getValue());
				NpcParamUtil.setPanel(player, 2, new ArrayList<String>(), 1);//关闭面板
			}
		}
	}
	
	

	
	
	
	/**
	 * 五彩坚硬石使用
	 * @param para
	 */
	public void wucaijianyingshi_act(List<Object> para){
		Player player = (Player) para.get(0);
		if (player == null ) {
			return ;
		}
		long itemid = Long.valueOf((String)para.get(1));
		Item item = ManagerPool.backpackManager.getItemById(player, itemid);
		if (item == null) {
			return;
		}

		String itemname=item.acqItemModel().getQ_name();
		
		Gem[][] gems = player.getGems();
		for (int i = 0; i < gems.length; i++) {
			Gem[] gempos = gems[i];
			for (int j = 0; j < 3; j++) {	//检测所有低级宝石
				if (gempos[j].getLevel() < 6) {
					MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("部分低级宝石没有达到6品，无法使用，请把所有低级宝石提升到6品后使用。"));
					return;
				}
			}
		}
		
		
		
		//检测可提升宝石
		boolean is = false;
		for (int i = 0; i < gems.length; i++) {
			Gem[] gempos = ManagerPool.gemManager.getPosGems(player,i);	//得到位置上可显示的宝石
			for (int j = 0; j < gempos.length; j++) {
				int pos = i+1;
				int idx = j+1;
				String id = pos+"_"+idx;
				Q_gem_activationBean gemactdata= ManagerPool.gemManager.getGemActData(id);
				if (gemactdata != null) {
					if (gempos[j].getLevel() == 6 && (gemactdata.getQ_gem_type() >= 2 && gemactdata.getQ_gem_type() <= 5)) {
						is = true;
					}
				}
			}
		}
		
		if (is ==false) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("您没有符合提升条件的宝石。"));
			return;	
		}
		

		if( !ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GEMQIANGHUA_WUCAI,Config.getId()) ){
			return ;	//扣道具失败
		}
		

		int num = 0 ;
		for (int i = 0; i < gems.length; i++) {
			Gem[] gempos = ManagerPool.gemManager.getPosGems(player,i);	//得到位置上可显示的宝石
			for (int j = 0; j < gempos.length; j++) {
				int pos = i+1;
				int idx = j+1;
				String id = pos+"_"+idx;
				Q_gem_activationBean gemactdata= ManagerPool.gemManager.getGemActData(id);
				if (gemactdata != null) {
					if (gempos[j].getLevel() == 6 && (gemactdata.getQ_gem_type() >= 2 && gemactdata.getQ_gem_type() <= 5)) {
						gempos[j].setLevel(106);
						num = num + 1;
					}
				}
			}
		}
		if (num > 0) {
			MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("{1}使用成功，强化{2}颗宝石！"),itemname,num+"");
			ManagerPool.gemManager.refreshGem(player);
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.GEM);
			NpcParamUtil.setPanel(player, 3, new ArrayList<String>(), 1);//关闭面板
		}
	}
	
	
	
	
	/**七彩晶石
	 * 
	 */
	public void qicaijingshi_act(List<Object> para){
		Player player = (Player) para.get(0);
		if (player == null ) {
			return ;
		}
		long itemid = Long.valueOf((String)para.get(1));
		Item item = ManagerPool.backpackManager.getItemById(player, itemid);
		if (item == null) {
			return;
		}
		
		List<Buff> buffs = ManagerPool.buffManager.getBuffByModelId(player, 1113);
		if (buffs.size() > 0) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("您已经获得七彩晶石隐藏属性，无需再使用。"));
			return;
		}
		
//		String itemname=item.acqItemModel().getQ_name();
		Gem[][] gems = player.getGems();
		for (int i = 0; i < gems.length; i++) {
			Gem[] gempos = gems[i];
			for (int j = 0; j < gempos.length; j++) {	//检测所有宝石
				if (gempos[j].getLevel() < 6) {
					MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("部分宝石没有达到6品，无法使用，请把所有宝石提升到6品后使用。"));
					return;
				}
			}
		}
	
		if( !ManagerPool.backpackManager.removeItem(player, 1007, 9999, Reasons.GEMQIANGHUA_QICAI,Config.getId()) ){
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("您没有9999个宝石精华，不能获得七彩晶石隐藏属性。"));
			return ;	//扣道具失败
		}
		
		if( !ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GEMQIANGHUA_QICAI,Config.getId()) ){
			return ;	//扣道具失败
		}

		ManagerPool.buffManager.addBuff(player, player, 1113, 0, 0, 0);
		MessageUtil.notify_player(player, Notifys.CHAT_BULL, ResManager.getInstance().getString("恭喜您获得了七彩晶石隐藏属性，请打开宝石面板查看。"));
		NpcParamUtil.setPanel(player, 2, new ArrayList<String>(), 1);//关闭面板
	}

	
	
}
