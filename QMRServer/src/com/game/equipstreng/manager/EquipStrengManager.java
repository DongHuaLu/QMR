package com.game.equipstreng.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.backpack.structs.ItemTypeConst;
import com.game.buff.structs.Buff;
import com.game.chat.bean.GoodsInfoRes;
import com.game.config.Config;
import com.game.data.bean.Q_globalBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_item_strengthBean;
import com.game.dblog.LogService;
import com.game.equipstreng.Log.EquipStageLog;
import com.game.equipstreng.Log.EquipStrengLog;
import com.game.equipstreng.message.ReqStageUpItemToServerMessage;
import com.game.equipstreng.message.ReqStrengClearCoolingMessage;
import com.game.equipstreng.message.ReqStrengItemToServerMessage;
import com.game.equipstreng.message.ReqStrengthenStateMessage;
import com.game.equipstreng.message.ResErrorInfoToClientMessage;
import com.game.equipstreng.message.ResStageUpItemToClientMessage;
import com.game.equipstreng.message.ResStrengItemToClientMessage;
import com.game.equipstreng.message.ResStrengthenStateMessage;
import com.game.equipstreng.structs.EquipStreng;
import com.game.fightpower.manager.FightPowerManager;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.message.ResWeaponChangeMessage;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;
import com.game.vip.manager.VipManager;
import com.game.vip.struts.GuideType;

public class EquipStrengManager {

	private static Object obj = new Object();

	//玩家管理类实例
	private static EquipStrengManager manager;
	public static EquipStrengManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new EquipStrengManager();
			}
		}
		return manager;
	}

	private EquipStrengManager(){}
	
//	武器	1
//	衣服	2
//	头盔	3
//	项链	4
//	护腕	5
//	护腿	6
//	戒指	7
//	绶佩	8
//	腰带	9
//	鞋子	10
	private static String[] posname = {" ","武器","衣服","头盔","项链","护腕","护腿","戒指","绶佩","腰带","鞋子"};
	

	
	public String getPosname(int idx) {
		if (posname.length >= idx) {
			return ResManager.getInstance().getString(posname[idx]);
		}
		return " ";
	}


	
	
	
	/**得到强化数据库模版
	 * 
	 * @param idx
	 * @return
	 */
	public Q_item_strengthBean getStrengItemData(String id){
		return ManagerPool.dataManager.q_item_strengthContainer.getMap().get(id);
	}
	
	
	/**
	 * 立即完成功能消耗元宝数量
	 * @return
	 */
	public int getGoldPrompt(){
		Q_globalBean global = ManagerPool.dataManager.q_globalContainer.getMap().get(80);
		return global.getQ_int_value();
	}
	
	
	/**得到道具数据库模版
	 * 
	 * @param idx
	 * @return
	 */
	public Q_itemBean getItemData(int id){
		return ManagerPool.dataManager.q_itemContainer.getMap().get(id);
	}
	
	
	/**对指定道具进行强化操作
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqStrengItemToServerMessage(Player player,ReqStrengItemToServerMessage msg) {
		EquipStreng esdata = player.getEquipStreng();
		if (esdata.getItemid() > 0) {
			Equip xequip = ManagerPool.equipManager.getEquipById(player,esdata.getItemid());
			if (xequip != null) {
				Q_itemBean ydata = getItemData(xequip.getItemModelId());
				String pname = getPosname(ydata.getQ_kind());
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("{1}『{2}』尚未强化完成，请稍后"),pname,BackpackManager.getInstance().getName(ydata.getQ_id()));
				return;
			}
		}
		
		Equip equip = ManagerPool.equipManager.getEquipById(player,msg.getItemid());
		if (equip != null) {
			int currentnum = equip.getGradeNum();//得到强化次数
			int snum = currentnum + 1;		//当前要加的强化等级
			Q_item_strengthBean strengthBean = getStrengItemData(equip.getItemModelId()+"_"+snum);
			if (strengthBean != null ) {
				if (player.getMoney() < strengthBean.getQ_streng_money()) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，所需铜币不足。"));
					stResErrorInfoToClient(player,(byte)1,strengthBean.getQ_streng_money(),null);
					return;
				}
				
				//优先使用超级强化石
				if(ManagerPool.backpackManager.removeItem(player,16026,1,Reasons.STRENG_SUPER,Config.getId())==false){
					if (checkTakeMaterial(player,strengthBean.getQ_streng_item()) == false) {//检测并收道具
						return;
					}	
				}
				
				if(ManagerPool.backpackManager.changeMoney(player, -strengthBean.getQ_streng_money(), Reasons.STRENG_QH_GOLD,Config.getId())){
					byte result = 0;// 强化结果，1成功，0失败
					
					//若当前所操作的星数<曾进行强化的最高星数，则读取“服务器端计算用成功几率”进行计算在1-10000之间随机一个数
					if(RandomUtils.isGenerate2(10000,strengthBean.getQ_streng_pby() )){	//	进入随机
						result = 1;
					}
					//若当前所操作的星数>=曾进行强化的最高星数，则读取该装备的“最高星数强化失败次数”并进行判断
					if (snum >= equip.getHighestgrade()) {
						if (equip.getGradefailnum() < strengthBean.getQ_streng_min()) {
							result= 0;	//必定失败
						}else if (equip.getGradefailnum() > strengthBean.getQ_streng_max()) {
							result= 1;	//必定成功
						}
					}

					if (strengthBean.getQ_streng_time() > 0) {	//强化耗时大于0
						esdata.setItemid(equip.getId());
						esdata.setResult(result);
						esdata.setStarttime(strengthBean.getQ_streng_time());//强化剩余时间
						ResStrengthenStateMessage stmsg = new ResStrengthenStateMessage();
						stmsg.setItemid(equip.getId());
						stmsg.setTime(strengthBean.getQ_streng_time());
						stmsg.setTimesum(strengthBean.getQ_streng_time());
						stmsg.setYuanbao(strengthBean.getQ_fast_streng_yuanbao());
						MessageUtil.tell_player_message(player, stmsg);		//发送给前端展示强化时间
					}else {		//直接得到强化结果
						changeEquip(player,equip,strengthBean,result,snum);	//改变装备
						ResStrengItemToClientMessage smsg =new ResStrengItemToClientMessage();
						smsg.setEquipInfo(ManagerPool.equipManager.getEquipInfo(equip));
						smsg.setIssuccess(result);
						smsg.setItemlevel((byte) equip.getGradeNum());
						MessageUtil.tell_player_message(player, smsg);	
					}
				}
			}else {
				if (currentnum >= 10 ) {
					MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("很抱歉，该装备已经强化到顶级。"));
				}else {
					MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("很抱歉，该装备不能强化。"));
				}
			}
		}
	}
	
	
	
	/**改变装备（强化结果）
	 * 
	 * @param player
	 * @param equip
	 * @param strengthBean
	 * @param result
	 * @param snum
	 */
	public void changeEquip(Player player,Equip equip,Q_item_strengthBean strengthBean,byte result,int snum ){
//		String name = equip.acqItemModel().getQ_name();
		EquipStrengLog log = new EquipStrengLog();	//日志
		log.setPlayerid(player.getId());
		log.setStartlv(equip.getGradeNum());
		log.setEquiponlyid(equip.getId());
		boolean  isreduce = false;	//是否降星
		if (result == 1) {//成功
			int oldfightpower=FightPowerManager.getInstance().calAllFightPower(player);
			equip.setGradeNum(snum);
			//重新计算属性
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.EQUIP, 0);
			int newfightpower=FightPowerManager.getInstance().calAllFightPower(player);
			if (snum >= equip.getHighestgrade()) {
				equip.setHighestgrade((byte) snum);
				equip.setGradefailnum((short) 0);
				
				if (strengthBean.getQ_streng_notice() == 1) {
					Q_itemBean ydata = getItemData(equip.getItemModelId());
					String pname = getPosname(ydata.getQ_kind());
				//	MessageUtil.notify_All_player(Notifys.CHAT_BULL, "恭喜 {1} 将{2} 【{3}】 强化至{4}级",player.getName(),pname,BackpackManager.getInstance().getName(equip.acqItemModel().getQ_id()),String.valueOf(snum));
					ParseUtil parseUtil = new ParseUtil();
					int fightpower = newfightpower - oldfightpower;
					String equipname= BackpackManager.getInstance().getName(equip.acqItemModel().getQ_id());
					parseUtil.setValue(String.format(ResManager.getInstance().getString("恭喜 %s 将%s【%s】强化至【%s】级，战斗力提升%d!{@}"), player.getName(),pname,equipname,String.valueOf(snum),fightpower), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.EQUIP.getValue()));
					MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.EQUIP.getValue());
				}
			}
			MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("恭喜您，装备强化成功,目前为强化{1}级，战斗力提升。"),String.valueOf(snum));

		}else {		//失败
			if (snum >= equip.getHighestgrade()) {
				equip.setHighestgrade((byte) snum);
				equip.setGradefailnum((short) (equip.getGradefailnum()+1));
			}
			if(strengthBean.getQ_streng_fail_reduce()> 0){	//降星
				//9星装备保护符：使用后在12小时以内,7星以前,9星装备强化失败不掉星,VIP为24小时
				List<Buff> buffs = ManagerPool.buffManager.getBuffByModelId(player, 1406);
				//5星装备保护符：使用后在12小时以内,7星以前,9星装备强化失败不掉星,VIP为24小时
				List<Buff> buffs2 = ManagerPool.buffManager.getBuffByModelId(player, 1413);
				//完美强化卡：使用后在12小时以内,装备强化不掉星
				List<Buff> buffs3 = ManagerPool.buffManager.getBuffByModelId(player, 1414);
				
				if ((equip.getGradeNum() == 9 && buffs.size() > 0) || (equip.getGradeNum() == 5 && buffs2.size() > 0) || (buffs3.size() > 0) )  {	
					MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("很遗憾，强化操作失败了(装备保护符保护不掉星)，再接再厉哦。"));
				}else {
					int xnum = equip.getGradeNum()-strengthBean.getQ_streng_fail_reduce();
					if (xnum <0) {
						xnum= 0;
					}
					log.setBackwardslv(xnum);
					equip.setGradeNum(xnum);
					String txt = randomGiveItem(player,strengthBean.getQ_streng_fail_item());	//随机道具奖励
					if (txt != null && txt.length() > 0) {
						log.setFailgiveitem(txt);
						MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("很遗憾，强化失败爆了[{1}]颗星，但您获得了：{2}。"),String.valueOf(strengthBean.getQ_streng_fail_reduce()),txt);
					}else {
						MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("很遗憾，强化失败爆了[{1}]颗星。"),String.valueOf(strengthBean.getQ_streng_fail_reduce()));
					}
					isreduce = true;
				}
			}else {
				MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("很遗憾，强化操作失败了，再接再厉哦。"));
			}
			//重新计算属性
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.EQUIP, 0);
		}

		if (result == 1 && isreduce == false) {
			//检查并改变套装属性BUFF
			ManagerPool.equipManager.stTaoZhuang(player,2);
			Q_itemBean model = getItemData(equip.getItemModelId());
			
			if(model.getQ_kind() == 1 && equip.getGradeNum() == 10 ){ //同步武器外观和强化等级10 到世界服务器
				ManagerPool.playerManager.stSyncExterior(player);
				ResWeaponChangeMessage msg = new ResWeaponChangeMessage();
				msg.setPersonId(player.getId());
				msg.setWeaponId(model.getQ_id());
				msg.setWeaponStreng((byte)equip.getGradeNum());
				MessageUtil.tell_round_message(player,msg);	
			}
			
		}
		log.setModelid(equip.getItemModelId());
		log.setResult(result);
		log.setTargetlv(snum);
		log.setConsumeitem(strengthBean.getQ_streng_item());
		log.setMoney(strengthBean.getQ_streng_money());
		LogService.getInstance().execute(log);
	}
	

	
	
	/**
	 * 检查并收取材料
	 * @return 
	 */
	public boolean checkTakeMaterial(Player player,String string){
		ArrayList<Integer[]> itemlist =getAnalyzeString(string);
		boolean is = true;
		String txt = "";
		int itemid = 0;
		long action=Config.getId();
		if (itemlist.size() > 0) {
			for (Integer[] integers : itemlist) {
				int num = ManagerPool.backpackManager.getItemNum(player,integers[0]);
				if (num < integers[1]) {
					Q_itemBean itemBean = ManagerPool.dataManager.q_itemContainer.getMap().get(integers[0]);
					is=false;
					txt=txt+" "+(integers[1]-num)+ResManager.getInstance().getString("个")+BackpackManager.getInstance().getName(itemBean.getQ_id());
					itemid = integers[0];
				}
			}

			
			if (is) {
				for (Integer[] integers : itemlist) {
					if (ManagerPool.backpackManager.removeItem(player, integers[0], integers[1],Reasons.STRENG_ITEM,action) == false) {
						return false;
					}
				}
				return true;
			}else {
				MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("很抱歉，所需材料不足,缺少{1}。"),txt);
				stResErrorInfoToClient(player,(byte)2,itemid,null);
			}
		}else {
			MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("升阶或强化没有设定所需道具，停止。"));
		}
		return false;
	}
	
	
	 
	
	/**解析字符串（获取道具ID和数量）
	 * 
	 * @param strengthBean
	 * @return
	 */
	public ArrayList<Integer[]> getAnalyzeString(String str) {
		String[] itemstr = str.split(Symbol.FENHAO);
		ArrayList<Integer[]> itemlist = new ArrayList<Integer[]>();
		for (String itemtab : itemstr) {
			String[] items = itemtab.split(Symbol.XIAHUAXIAN_REG);
			if (items.length == 2) {
				Integer[] tab = {Integer.valueOf(items[0]),Integer.valueOf(items[1])};
				itemlist.add(tab);
			}
		}
		return itemlist;
	}
	
	
	
	
	/**随机给奖励
	 * @return 
	 */
	public String randomGiveItem(Player player,String str){
		String[] itemstr = str.split(Symbol.FENHAO);
		String txt = "";
		long action = Config.getId();
		for (String string : itemstr) {
			Pattern pattern = Pattern.compile("(.+?)\\|(.+?)\\_(.+?)");
			Matcher matcher = pattern.matcher(string);
			if(matcher.find()) {
				int rnd = Integer.parseInt(matcher.group(1)) ;
				int itemid = Integer.parseInt(matcher.group(2)) ;
				int num = Integer.parseInt(matcher.group(3)) ;
				Q_itemBean itemBean = getItemData(itemid);
				if (itemBean != null) {
					if(RandomUtils.isGenerate2(10000,rnd)){	//	进入随机
						int gridnum  = ManagerPool.backpackManager.getEmptyGridNum(player);
						List<Item> items = Item.createItems(itemid, num, true, 0);
						txt = txt + " "+BackpackManager.getInstance().getName(itemBean.getQ_id())+"*"+num;
						if (gridnum >= items.size()) {
							ManagerPool.backpackManager.addItems(player,items,Reasons.STRENG_QH_TO_ITEM,action);
						}else {
							ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("装备强化失败获得物品"),(byte)1,0,items);
							MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("您的包裹已满，强化失败获得{1}个『{2}』已通过邮件发送给您。"),String.valueOf(num),BackpackManager.getInstance().getName(itemBean.getQ_id()));
						}
					}
				}
			}
		}
		return txt;
	}
	
	
	

	/**装备强化立即完成消息
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqStrengClearCoolingMessage(Player player,ReqStrengClearCoolingMessage msg) {
		//所需元宝数 = 强化数据库中的“立即完成功能消耗元宝数量 * （剩余的完成所需时间 / 装备强化耗时所需总时间）”
		EquipStreng esdata = player.getEquipStreng();
		Equip equip = ManagerPool.equipManager.getEquipById(player,esdata.getItemid());
		if (esdata.getItemid() > 0 && equip != null) {
			int snum = equip.getGradeNum() + 1;	//当前要加的强化等级
			Q_item_strengthBean strengthBean = getStrengItemData(equip.getItemModelId()+"_"+snum);
			if (strengthBean != null) {
				if (strengthBean.getQ_streng_time() > 0) {
					double t = (double)esdata.getStarttime()/(double)strengthBean.getQ_streng_time();
					double yb = (double)strengthBean.getQ_fast_streng_yuanbao()*t;
					int ybint = (int) Math.ceil(yb);
					if(ManagerPool.backpackManager.checkGold(player, ybint)) {
						ManagerPool.backpackManager.changeGold(player,-ybint,Reasons.STRENG_QH_YUANBAO,Config.getId());
						esdata.setStarttime(0);
						atonceStrengthen(player);
					}else {
						MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("立即强化完成需要{1}元宝"),String.valueOf(ybint));
						stResErrorInfoToClient(player,(byte)3,ybint,null);
					}
				}
			}
		}
	}


	
	
	/**打开强化面板
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqStrengthenStateMessage(Player player,ReqStrengthenStateMessage msg) {
		atonceStrengthen(player);
	}
	
	
	/**处理强化时间到期后返回前端
	 * 
	 * @param player
	 */
	public void atonceStrengthen(Player player ) {
		EquipStreng esdata = player.getEquipStreng();
		Equip equip = ManagerPool.equipManager.getEquipById(player,esdata.getItemid());
		if (esdata.getItemid() > 0 && equip != null) {
			int snum = equip.getGradeNum() + 1;	//当前要加的强化等级
			Q_item_strengthBean strengthBean = getStrengItemData(equip.getItemModelId()+"_"+snum);
			if (strengthBean != null) {
				if (esdata.getStarttime() > 0) {
					ResStrengthenStateMessage stmsg = new ResStrengthenStateMessage();
					stmsg.setItemid(esdata.getItemid());
					stmsg.setTime(esdata.getStarttime());
					stmsg.setTimesum(strengthBean.getQ_streng_time());
					stmsg.setYuanbao(strengthBean.getQ_fast_streng_yuanbao());
					MessageUtil.tell_player_message(player, stmsg);		//发送给前端展示强化时间
					return;
				}else {
					changeEquip(player,equip,strengthBean,esdata.getResult(),snum);	//改变装备
					ResStrengItemToClientMessage smsg =new ResStrengItemToClientMessage();
					smsg.setEquipInfo(ManagerPool.equipManager.getEquipInfo(equip));
					smsg.setIssuccess(esdata.getResult());
					smsg.setItemlevel((byte) equip.getGradeNum());
					MessageUtil.tell_player_message(player, smsg);	
					esdata.setItemid(0);
					esdata.setResult((byte) 0);
					esdata.setStarttime(0);
					return;
				}
			}
		}
		ResStrengthenStateMessage stmsg = new ResStrengthenStateMessage();
		stmsg.setItemid(0);
		stmsg.setTime(0);
		MessageUtil.tell_player_message(player, stmsg);		//发送给前端展示强化时间
	}
		
		
		
		
	
	/**对指定道具进行升阶操作消息
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqStageUpItemToServerMessage(Player player,ReqStageUpItemToServerMessage msg) {
		EquipStreng esdata = player.getEquipStreng();
		if (esdata.getItemid() > 0) {
			Equip xequip = ManagerPool.equipManager.getEquipById(player,esdata.getItemid());
			if (xequip != null) {
				Q_itemBean data = getItemData(xequip.getItemModelId());
				MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("{1}『{2}』尚未强化完成，请稍后"),getPosname(data.getQ_kind()),BackpackManager.getInstance().getName(xequip.getItemModelId()));
				return;
			}
		}
		Equip equip = ManagerPool.equipManager.getEquipById(player,msg.getItemid());
		if (equip != null) {
			int oldmodelid = equip.getItemModelId();
			int lv = equip.getGradeNum() ;	//当前的强化等级
			Q_item_strengthBean strengthBean = getStrengItemData(equip.getItemModelId()+"_"+lv);
			if (strengthBean == null) {
				MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("很抱歉，该装备限制为：无法进阶"));
				return;
			}
			if (strengthBean.getQ_is_up_stage() == 0) {
				MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("很抱歉，该装备限制为：无法进阶"));
				return;
			}
			
			int newitemid = strengthBean.getQ_stage_newitem();
			if(newitemid == 0 || equip.getItemModelId() == newitemid){
				MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("很抱歉，该装备限制为：无法进阶"));
				return;
			}
			
			Q_itemBean newitemBean = getItemData(newitemid);
			if (newitemBean == null) {
				MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("很抱歉，该装备限制为：无法进阶"));
				return;
			}
			if (player.getLevel() < newitemBean.getQ_level()) {
				MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("成功进阶后的装备，需要[{1}级]才能佩戴，请先升到[{1}级]"),String.valueOf(newitemBean.getQ_level()));
				return;
			}
			Q_itemBean ydata = getItemData(equip.getItemModelId());
//			String yname = ydata.getQ_name();
//			String xname = newitemBean.getQ_name();
			if (player.getMoney() < strengthBean.getQ_up_stage_money()) {
				MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("很抱歉，所需铜币不足。"));
				stResErrorInfoToClient(player,(byte)1,strengthBean.getQ_up_stage_money(),null);
				return;
			}
			
			if (checkTakeMaterial(player,strengthBean.getQ_up_stage_item())== false) return;
			if(ManagerPool.backpackManager.changeMoney(player, -strengthBean.getQ_up_stage_money(), Reasons.STRENG_SJ_GOLD,Config.getId())){			
				byte result = 0;// 进阶结果，1成功，0失败
				if (equip.getAdvfailnum() <= strengthBean.getQ_up_stage_min()) {
					result= 0;	//必定失败
				}else if (equip.getAdvfailnum() > strengthBean.getQ_up_stage_max()) {
					result= 1;	//必定成功
				}else {
					if(RandomUtils.isGenerate2(10000,strengthBean.getQ_stage_probability() )){	//	进入随机
						result = 1;
					}
				}
				EquipStageLog log = new EquipStageLog();	//日志结构
				log.setEquip(JSONserializable.toString(equip));
				if (result == 1) {
					equip.setItemModelId(newitemid);
					equip.setAdvfailnum((short) 0);
					equip.setGradeNum(strengthBean.getQ_newitem_level());
					MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("恭喜您，装备进阶成功，战斗力提升"));
					if (strengthBean.getQ_stage_notice() == 1) {
						String pname = getPosname(ydata.getQ_kind());
						MessageUtil.notify_All_player(Notifys.CHAT_BULL, ResManager.getInstance().getString("恭喜 {1} 将{2}【{3}】成功进阶为【{4}】"),player.getName(),pname,BackpackManager.getInstance().getName(oldmodelid),BackpackManager.getInstance().getName(newitemid));
					
//						ParseUtil parseUtil = new ParseUtil();
//						parseUtil.setValue(String.format("恭喜 %s 将%s【%s】成功进阶为【%s】!{@}", player.getName(),pname,BackpackManager.getInstance().getName(oldmodelid),BackpackManager.getInstance().getName(newitemid)), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.EQUIP_UP.getValue()));
//						MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.EQUIP.getValue());
					}
					log.setTargetModel(newitemid);
				}else {
					equip.setAdvfailnum((short) (equip.getAdvfailnum()+1));
					MessageUtil.notify_player(player, Notifys.EQST, ResManager.getInstance().getString("很遗憾，进阶操作失败了，再接再厉哦!"));
				}
				ResStageUpItemToClientMessage smsg = new ResStageUpItemToClientMessage();
				smsg.setIssuccess(result);
				smsg.setEquipInfo(ManagerPool.equipManager.getEquipInfo(equip));
				MessageUtil.tell_player_message(player, smsg);	
				log.setEquiponlyid(equip.getId());
				log.setPlayerid(player.getId());
				log.setConsumeitem(strengthBean.getQ_up_stage_item());
				log.setResult(result);
				log.setMoney(strengthBean.getQ_up_stage_money());
				log.setSid(player.getCreateServerId());
				LogService.getInstance().execute(log);	//写日志
			}	
		}
	}
	
	
	
	/**GM测试强化
	 * 
	 * @param player
	 * @param pos
	 * @param num
	 */
	public void testStrengthen(Player player ,int pos,int num){
		Equip weared = player.getEquips()[pos];
		if (weared != null) {
			weared.setGradeNum(num);
//			String yname = getItemData(weared.getItemModelId()).getQ_name();
			//发送装备信息
			MessageUtil.tell_player_message(player, ManagerPool.equipManager.getWearEquipInfo(weared));
			MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("{1}强化等级变更为：{2}。"),BackpackManager.getInstance().getName(weared.getItemModelId()),num+"");
		}else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该位置装备不存在,请输入0至9"));
		}
	}
	

	/**发送错误消息给前端
	 * 类型 ：1铜币不足，2道具不足，3，元宝不足
	 * @param player
	 */
	public void stResErrorInfoToClient(Player player,byte type , int num ,String str){
		ResErrorInfoToClientMessage smsg = new ResErrorInfoToClientMessage();
		smsg.setType(type);
		smsg.setErrint(num);
		smsg.setErrstr(str);
		MessageUtil.tell_player_message(player, smsg);	
	}
	
	
	/**清除强化记录
	 * 
	 * @param item
	 * @return
	 */
	public Item clearStrengthenLog(Item item) {
		Q_itemBean q_itemBean = getItemData(item.getItemModelId());
		if (q_itemBean != null && q_itemBean.getQ_type() == ItemTypeConst.EQUIP ) {
			Equip equip = (Equip)item;
			equip.setGradefailnum((short) 0);
			equip.setAdvfailnum((short) 0);
			equip.setHighestgrade((byte) 0);
		}
		return item;
	}
	
}
