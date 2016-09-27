package scripts.fight;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.country.manager.CountryManager;
import com.game.fight.script.IHitDamageScript;
import com.game.fight.structs.FightResult;
import com.game.fight.structs.Fighter;
import com.game.fight.structs.FighterState;
import com.game.languageres.manager.ResManager;
import com.game.mail.manager.MailServerManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.monster.structs.Monster;
import com.game.pet.struts.Pet;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.scripts.bean.ButtonInfo;
import com.game.scripts.bean.PanelInfo;
import com.game.scripts.bean.PanelTxtInfo;
import com.game.utils.CommonConfig;
import com.game.utils.Global;
import com.game.utils.MessageUtil;
import com.game.utils.NpcParamUtil;

public class HitDamageScript implements IHitDamageScript {

	//弓箭boss分身模板
	private static int FENSHEN = 14008;
	
	//弓箭boss分身狂暴
	private static String FENSHENKUANGBAO = "fenshenkuangbao";
	
	//暴击设定值
	private static int CRITMIN = 4500;
	
	//弓箭第2关boss
	private static int BOSS2 = 14002;
	
	//弓箭第6关boss
	private static int BOSS6 = 14006;
	
	//伤害设定值
	private static int DAMAGEMIN = 4500;
	
	//buff
	private int BUFF1 = 24002;
		
	//buff
	private int BUFF2 = 24008;
	
	//buff
	private int FENSHENBUFF1 = 24007;
	
	//buff
	private int FENSHENBUFF2 = 24010;
	
	//buff
	private int FENSHENBUFF3 = 24011;
	
	@Override
	public int getId() {
		return ScriptEnum.HIT_DAMAGE;
	}

	@Override
	public void onDamage(Fighter attacker, Fighter defender, FightResult fightResult) {
		if(defender instanceof Monster){
			Monster monster = (Monster) defender;
			if(monster.getModelId()==FENSHEN){
				if(attacker instanceof Player){
					Player player = (Player)attacker;
					if(player.getCrit() >= CRITMIN){
						fightResult.fightResult = fightResult.fightResult | 4;
					}
					if((fightResult.fightResult & 4) > 0){
						//暴击
						fightResult.damage = monster.getMaxHp();
					}else{
						//未暴击
						if(!monster.getParameters().containsKey(FENSHENKUANGBAO)){
							monster.getParameters().put(FENSHENKUANGBAO, 1);
							ManagerPool.buffManager.addBuff(monster, monster, FENSHENBUFF1, 0, 0, 0);
							ManagerPool.buffManager.addBuff(monster, monster, FENSHENBUFF2, 0, 0, 0);
							ManagerPool.buffManager.addBuff(monster, monster, FENSHENBUFF3, 0, 0, 0);
							monster.setHp(monster.getMaxHp());
							ManagerPool.monsterManager.onMaxHpChange(monster, monster.getMaxHp());
							
							//提示
							MessageUtil.notify_player(player, Notifys.CHAT_BULL, ResManager.getInstance().getString("BOSS分身显得愈发狂躁，其战斗力大幅提升！"));
						}
					}
				}
			}
			else if(monster.getModelId()==BOSS6){
				if(FighterState.WEAK.compare(monster.getFightState())){
					if(attacker instanceof Player){
						if((fightResult.fightResult & 4) == 0){
							//未暴击
							fightResult.fightResult = fightResult.fightResult | 4;
							fightResult.damage = fightResult.damage * Global.CRIT_MULTIPLE;
						}
					}
				}
			}
		}
		if(attacker instanceof Monster){
			Monster monster = (Monster) attacker;
			if(monster.getModelId()==BOSS2){
				//计算boss血量
				int hpPercent = (int)(((double)monster.getHp() * 100)/monster.getMaxHp());
				if(hpPercent< 30 && (defender instanceof Player)){
					Player player = (Player)defender;
					if(((fightResult.fightResult&2) == 0) && fightResult.damage>DAMAGEMIN){
						//伤害大于设定值
						ManagerPool.buffManager.addBuff(player, player, BUFF1, 0, 0, 0);
						ManagerPool.buffManager.addBuff(player, player, BUFF2, 0, 0, 0);
						
						//提示
						MessageUtil.notify_player(player, Notifys.CHAT_BULL, ResManager.getInstance().getString("BOSS对你施放了精疲力竭，你感到从所未有的虚弱！"));
					}
				}
			}
		}
		
		//--------------------攻城战记录伤害------------------
		if (fightResult.fightResult != 2) {
			if ((attacker instanceof Player || attacker instanceof Pet)  && ( defender instanceof Player || defender instanceof Pet) ){
				Player attackplayer = null;
				Player defendplayer = null;
				if (attacker instanceof Player) {
					attackplayer = (Player)attacker;
				}else {
					attackplayer =ManagerPool.petInfoManager.getPetHost((Pet)attacker);
				}
				if (defender instanceof Player) {
					defendplayer = (Player)defender;
				}else {
					defendplayer =ManagerPool.petInfoManager.getPetHost((Pet)defender);
				}
				if (defendplayer != null && attackplayer != null) {
					Map map = ManagerPool.mapManager.getMap(attackplayer);
					if(map != null && map.getMapModelid() == ManagerPool.countryManager.SIEGE_MAPID && ManagerPool.countryManager.getSiegestate() == 1){
						ManagerPool.countryManager.changeSiegeSMSTopData(attackplayer, CountryManager.HURT, fightResult.damage);
						ManagerPool.countryManager.changeSiegeSMSTopData(defendplayer, CountryManager.BEENHURT, fightResult.damage);
					}
				}
			}
		}
		
		// 年兽处理
//		sprintMonsterProcess(attacker, defender, fightResult.damage);
	}

	
	// 年兽相关
//	static class ComparatorTreeMap implements Comparator<Integer> {
//		public int compare(Integer v1, Integer v2) {
//			int ret = v1.compareTo(v2);
//			return ret == 0 ? 1 : -ret;
//		}
//	}
//	private HashMap<Player, Integer> player2damage = new HashMap<Player, Integer>();
//	private int monsterModel = 2002; // 年兽的modelid
//	private static Object synObject = new Object();
//	
//	/**
//	 * 这个函数是记录伤害列表,这里只管记录，死亡后清空。只是针对仅有一个年兽的时候保证准确性
//	 * @param attacker
//	 * @param defender
//	 * @param damage
//	 */
//	private void sprintMonsterProcess(Fighter attacker, Fighter defender,
//			int damage) {
//		if (damage < 1) {
//			return ;
//		}
//		if (!(defender instanceof Monster)) {
//			return ;
//		}
//		
//		Monster monster = (Monster) defender;
//		if (monster.getModelId() != monsterModel) {
//			return ;
//		}
//		
//		Player player = null;
//		if (attacker instanceof Player) {
//			player = (Player) attacker;
//		}
//		if (attacker instanceof Pet) {
//			player = PlayerManager.getInstance().getPlayer(((Pet) attacker).getOwnerId());
//		}
//		
//		if (player == null) {
//			return ;
//		}
//		synchronized (synObject) {
//			if (player2damage.containsKey(player)) {
//				player2damage.put(player, player2damage.get(player) + damage);
//			}
//			else {
//				player2damage.put(player, damage);
//			}
//		}
//	}
//	
//	/**
//	 * 这个函数是进行奖励处理,奖励对年兽造成伤害排名前三的玩家,从global表中取奖励物品
//	 * @param list
//	 */
//	public void springMonstPrize(List<Object> list) {
//		synchronized (synObject) {
//			Monster monster = (Monster) list.get(0);
//			if (monster.getModelId() != monsterModel) {
//				return ;
//			}
//			
//			String prize1 = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SPRING_MONSTER_PRIZE_1.getValue()).getQ_string_value();
//			String prize2 = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SPRING_MONSTER_PRIZE_2.getValue()).getQ_string_value();
//			String prize3 = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SPRING_MONSTER_PRIZE_3.getValue()).getQ_string_value();
//			List<Integer[]> prizeList1 = JSON.parseArray(prize1, Integer[].class);
//			List<Integer[]> prizeList2 = JSON.parseArray(prize2, Integer[].class);
//			List<Integer[]> prizeList3 = JSON.parseArray(prize3, Integer[].class);
//			
//			TreeMap<Integer, Player> damage2player = new TreeMap<Integer, Player>(new ComparatorTreeMap());
//			{
//				Iterator<Entry<Player, Integer>> it = player2damage.entrySet().iterator();
//				while (it.hasNext()) {
//					Entry<Player, Integer> entry = it.next();
//					damage2player.put(entry.getValue(), entry.getKey());
//				}
//				
//				player2damage.clear();
//			}
//			
//			{
//				MessageUtil.notify_All_player(Notifys.CHAT_BULL, ResManager.getInstance().getString("本次击杀年兽前三名玩家:"));
//				Iterator<Entry<Integer, Player>> it = damage2player.entrySet().iterator();
//				int index = 0;
//				while (it.hasNext() && (++index) < 4) {
//					Entry<Integer, Player> entry = it.next();
//					List<Integer[]> prizeList = null;
//					if (index == 1) {
//						prizeList = prizeList1;
//					}
//					else if (index == 2) {
//						prizeList = prizeList2;
//					}
//					else if (index == 3) {
//						prizeList = prizeList3;
//					}
//					
//					if (prizeList != null) {
//						String itemStr = new String();
//						Iterator<Integer[]> ii = prizeList.iterator();
//						while (ii.hasNext()) {
//							Integer[] prize = ii.next();
//							itemStr = itemStr + prize[0] + "," + prize[1] + ",0,1;";
//						}
//						itemStr = itemStr.substring(0, itemStr.length() - 1);
//						Player player = entry.getValue();
//						// 发面板
//						{
//							PanelInfo panel = NpcParamUtil.getPanelInfo(player, 21);
//							List<PanelTxtInfo> paneltxtinfolist = new ArrayList<PanelTxtInfo>();
//							{
//								PanelTxtInfo txtInfo = new PanelTxtInfo();
//								txtInfo.setType((byte) 1);
//								txtInfo.setName("labInfo");
//								txtInfo.setContent(ResManager.getInstance().getString(String.valueOf(index)));
//								paneltxtinfolist.add(txtInfo);
//							}
//							
//							{
//								PanelTxtInfo txtInfo = new PanelTxtInfo();
//								txtInfo.setType((byte) 0);
//								txtInfo.setName("gridReward");
//								txtInfo.setContent(itemStr);
//								paneltxtinfolist.add(txtInfo);
//							}
//							panel.setPaneltxtinfolist(paneltxtinfolist);
//							
//							List<ButtonInfo> panelbuttoninfolist = new ArrayList<ButtonInfo>();
//							{
//								ButtonInfo buttonInfo = new ButtonInfo();
//								buttonInfo.setName("btnStarting");
//								buttonInfo.setIsclose((byte) 1);
//								panelbuttoninfolist.add(buttonInfo);
//							}
//							panel.setButtoninfolist(panelbuttoninfolist);
//							
//							NpcParamUtil.showPanel(player, panel);
//						}
//						
//						List<Item> items = new ArrayList<Item>();
//						
//						MessageUtil.notify_All_player(Notifys.CHAT_BULL, ResManager.getInstance().getString("第" + index + "名:" + player.getName()));
//						ManagerPool.backpackManager.createItems(player, itemStr, items);
//						
//						if (BackpackManager.getInstance().hasAddSpace(player, items)) {
//							BackpackManager.getInstance().addItems(player, items, Config.getId());
//						}
//						else {
//							MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("活动系统邮件"), ResManager.getInstance().getString("由于未知原因，您的活动中铜币未领取成功，请点击附件领取。"), (byte) 0, 0, items);
//						}
//					}
//				}
//			}
//		}
//	}

}
