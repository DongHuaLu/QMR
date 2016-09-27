package com.game.gem.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.chat.bean.GoodsInfoRes;
import com.game.config.Config;
import com.game.data.bean.Q_gem_activationBean;
import com.game.data.bean.Q_gem_upBean;
import com.game.data.bean.Q_itemBean;
import com.game.dblog.LogService;
import com.game.fightpower.manager.FightPowerManager;
import com.game.gem.Log.GemLog;
import com.game.gem.bean.GemInfo;
import com.game.gem.bean.PosGemInfo;
import com.game.gem.message.ReqGemActivationORUpMessage;
import com.game.gem.message.ReqGemIntoMessage;
import com.game.gem.message.ReqOpenGemPanelMessage;
import com.game.gem.message.ResGemActivationORUpMessage;
import com.game.gem.message.ResGemErrorInfoMessage;
import com.game.gem.message.ResGemExtraExpMessage;
import com.game.gem.message.ResGemIntoMessage;
import com.game.gem.message.ResOpenGemPanelMessage;
import com.game.gem.struts.Gem;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.utils.RandomUtils;
import com.game.vip.manager.VipManager;
import com.game.vip.struts.GuideType;

public class GemManager {
	private Logger logmsg = Logger.getLogger(GemManager.class);
	private static Object obj = new Object();
	// 管理类实例
	private static GemManager manager;

	private GemManager() {}

	public static GemManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new GemManager();
			}
		}
		return manager;
	}
	
	
	/**获取宝石激活数据
	 * @return 
	 * 
	 */
	public Q_gem_activationBean getGemActData(String id){
		return ManagerPool.dataManager.q_gem_activationContainer.getMap().get(id);
	}
	
	/**获取宝石升级数据
	 * @return 
	 * 
	 */
	public Q_gem_upBean getGemUpData(String id){
		return ManagerPool.dataManager.q_gem_upContainer.getMap().get(id);
	}
	
	
	
	/**获取高阶宝石开放所需等级
	 * @return 
	 * 
	 */
	public int getHighGenPlayerlv(){
		return ManagerPool.dataManager.q_globalContainer.getMap().get(85).getQ_int_value();
	}
	
	/**获取高阶宝石开放所需宝石等级之和
	 * @return 
	 * 
	 */
	public int getHighGenlv(){
		return ManagerPool.dataManager.q_globalContainer.getMap().get(86).getQ_int_value();
	}
	
	/**开放宝石功能需要等级
	 * @return 
	 * 
	 */
	public int getOpenGenlv(){
		return ManagerPool.dataManager.q_globalContainer.getMap().get(90).getQ_int_value();
	}
	
	
	/**新人进入游戏，创建默认宝石
	 * 
	 * @param player
	 */
	public void createDefaultGem(Player player){
		Gem[][] gems = player.getGems();
		for (int i = 0; i < gems.length; i++) {
			int pos = i+1;
			Gem[] gemspos = gems[i];
			for (int j = 0; j < gemspos.length; j++) {
				if (gemspos[j] == null) {
					int idx = j+1;
					String id = pos+"_"+idx;
					Q_gem_activationBean gemactdata= getGemActData(id);
					if (gemactdata != null) {
						Gem gem=new Gem();
						gem.setLevel(gemactdata.getQ_initial_level());
						gemspos[j] = gem;
						gemspos[j].setGrid((byte) j);
					}else {
						logmsg.error(id+"宝石数据不存在");
					}
				}else {
					gemspos[j].setGrid((byte) j);
				}
			}
		}
	}
	
	
	
	/**获得指定部位宝石(根据条件过滤，得到3或者5颗)
	 * 
	 * @param player
	 * @param pos
	 * @return
	 */
	public Gem[] getPosGems(Player player,int pos){
		Gem[][] gems = player.getGems();
		if (pos<0 && pos >=10) {
			return null;
		}
		if(gems[pos] !=null){
			if (gems[pos][0].getLevel() >= 6 && gems[pos][1].getLevel() >= 6 && gems[pos][2].getLevel() >= 6) {
				return gems[pos];
			}else {
				Gem tmpgems[] = new Gem [3];
				tmpgems[0] = gems[pos][0];
				tmpgems[1] = gems[pos][1];
				tmpgems[2] = gems[pos][2];
				return tmpgems;
			}
		}
		return null;
	}
	
	
	/**单个宝石信息
	 * 
	 * @param gem
	 * @param pos
	 * @param idx
	 * @return
	 */
	public GemInfo getGeminfo(Gem gem,int pos,int idx){
		pos = pos+1;
		idx = idx+1;
		String id = pos+"_"+idx;
		Q_gem_activationBean gemactdata= getGemActData(id);
		if (gemactdata != null) {
			GemInfo gemInfo = new GemInfo();
			gemInfo.setGrid(gem.getGrid());
			gemInfo.setId(gem.getId());
			gemInfo.setExp(gem.getExp());
			gemInfo.setIsact(gem.getIsact());
			gemInfo.setLevel((byte) gem.getLevel());
			gemInfo.setType((byte) gemactdata.getQ_gem_type());
			return gemInfo;
		}else{
			logmsg.error("宝石空:"+id);
		}
			
		return null;
	}
	
	
	/**获得部位宝石信息
	 * 
	 * @param gems
	 * @param pos
	 * @return
	 */
	public PosGemInfo getPosGemInfo(Gem[] gems ,int pos){
		PosGemInfo posGemInfo = new PosGemInfo();
		posGemInfo.setPos((byte) pos);
		for (int i = 0; i < gems.length; i++) {
			GemInfo gemdata = getGeminfo(gems[i],pos,i);
			if (gemdata != null) {
				posGemInfo.getGeminfo().add(gemdata);
			}
		}
		return posGemInfo;
	}
	
	public PosGemInfo getPosGemInfo(Player player,int pos){
		Gem[] gems = player.getGems()[pos];
		PosGemInfo posGemInfo = new PosGemInfo();
		posGemInfo.setPos((byte) pos);
		for (int i = 0; i < gems.length; i++) {
			posGemInfo.getGeminfo().add(getGeminfo(gems[i],pos,i));
		}
		return posGemInfo;
	}	
	
	
	/**检测最小宝石
	 * 
	 * @param gems
	 */
	@SuppressWarnings("unchecked")
	public Gem checkGenLeast(Gem[] gems){
		Gem[] cgems = gems.clone();
		Arrays.sort(cgems, new GemSort());
		return cgems[0];
	}
	
	
	
	/**检查并设置是否可以开放高阶宝石
	 *  true 开放
	 * @param player
	 * @return
	 */
	public boolean checkOpenLimit(Player player){
		if (player.getIsopenhighgem() == 1) {
			return true;
		}
		
		if (player.getLevel() >= getHighGenPlayerlv()  && GetGemLimit(player) ) {
			player.setIsopenhighgem((byte) 1);
			return true;
		}
		return false;
	}
	
	
	/**检查宝石等级之和
	 *  true 开放
	 * @param player
	 * @return
	 */
	public boolean GetGemLimit(Player player){
		Gem[][] gems = player.getGems();
		int sum = 0;
		for (int i = 0; i < gems.length; i++) {
			Gem[] gempos = gems[i];
			for (int j = 0; j < 3; j++) {	//只检测前面3个宝石等级
				sum = sum + (gempos[j].getLevel()%100);
			}
		}
		if (sum >= getHighGenlv()) {
			return true;
		}
		return false;
	}
	
	
	
	/**得到0-9部位宝石信息
	 * 
	 * @param player
	 * @return
	 */
	public ArrayList<PosGemInfo> getAllGem(Player player){
		ArrayList<PosGemInfo> posGemInfos= new ArrayList<PosGemInfo>();
		Gem[][] gems = player.getGems();
		for (int i = 0; i < gems.length; i++) {
			Gem[] gempos = getPosGems(player,i);	//得到位置上可显示的宝石
			posGemInfos.add(getPosGemInfo(gempos,i));
		}
		return posGemInfos;
	}
	
	
	
	
	/**
	 * 打开宝石面板消息
	 * @param player
	 * @param msg
	 */

	public void stReqOpenGemPanelMessage(Player player,ReqOpenGemPanelMessage msg) {
		refreshGem(player);
	}

	
	public void refreshGem(Player player){
		ResOpenGemPanelMessage smsg = new ResOpenGemPanelMessage();
		smsg.setPosallgeminfo(getAllGem(player));
		MessageUtil.tell_player_message(player, smsg);
	}
	
	
	

	/**
	 * 放入宝石装备部位消息(告诉前端要升级或者激活的宝石)
	 * @param player
	 * @param msg
	 */
	public void stReqGemIntoMessage(Player player, ReqGemIntoMessage msg) {
		byte pos = msg.getPos();
		ResGemIntoMessage smsg = new ResGemIntoMessage();
		Gem[] gems = getPosGems(player,msg.getPos());	//得到指定部位宝石
		if (gems != null) {
			Gem gem= checkGenLeast(gems);
			GemInfo geminfo = getGeminfo(gem,pos,gem.getGrid());
			smsg.setGeminfo(geminfo);
			smsg.setType(gem.getIsact());
			smsg.setPos(pos);
			MessageUtil.tell_player_message(player, smsg);
		}else{
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("获得指定部位宝石出错。"));
		}
	}
	
	

	
	/**点击宝石激活或者升级消息
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqGemActivationORUpMessage(Player player,ReqGemActivationORUpMessage msg) {
		if (getOpenGenlv()> player.getLevel() ) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，宝石功能开放需要达到{1}级。"),getOpenGenlv()+"");
			return;
		}
		int pos = msg.getPos();
		Gem[] gems = getPosGems(player,msg.getPos());//得到指定部位宝石
		if (gems != null) {
			Gem gem = checkGenLeast(gems);
			byte grid = gem.getGrid();
			String id = (pos+1)+"_"+(grid+1);
			Q_gem_activationBean gemactdata= getGemActData(id);
			
			if (gemactdata != null) {
				ResGemActivationORUpMessage smsg = new ResGemActivationORUpMessage();
				int type = gemactdata.getQ_gem_type();
				String upid = type+"_"+gem.getLevel();
				Q_gem_upBean gemupdata = getGemUpData(upid);
				byte result = 0;
				String gemname=gemupdata.getQ_gem_name();
				smsg.setType(gem.getIsact());
				GemLog  log = new GemLog();
				log.setExp(gem.getExp());
				log.setEquiptype(pos);
				if (gem.getIsact() == 0) {//未激活，开始激活
					if (player.getZhenqi() < gemactdata.getQ_consumable_zhenqi()) {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，宝石激活所需真气不足，建议打坐修炼。"));
						stResGemErrorToClient(player,(byte)1,0);
						return;
					}

					if (checkTakeMaterial(player,gemactdata.getQ_consumable_item()) == false) {
						stResGemErrorToClient(player,(byte)2,0);
						return;
					}

					ManagerPool.playerManager.addZhenqi(player,-gemactdata.getQ_consumable_zhenqi(),AttributeChangeReason.GEM);//扣真气
					if(RandomUtils.isGenerate2(10000,gemactdata.getQ_act_rnd() )){	//	进入随机
						result = 1;
					}
					if (gem.getFailactnum() < gemactdata.getQ_act_num_min()) {
						result= 0;	//必定失败
					}else if (gem.getFailactnum() >= gemactdata.getQ_act_num_max()) {
						result= 1;	//必定成功
					}
					log.setZhenqi(gemactdata.getQ_consumable_zhenqi());
					log.setItem(gemactdata.getQ_consumable_item());
				}else {	//已经激活的宝石，进入升级

					if (gemupdata != null ) {
						if (gemupdata.getQ_is_up() == 0) {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该部位宝石已经全部达到顶级。"));
							return;
						}

						if (player.getZhenqi() < gemupdata.getQ_consumable_zhenqi()) {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，宝石升级所需真气不足，建议打坐修炼。"));
							stResGemErrorToClient(player,(byte)3,0);
							return;
						}
						if (checkTakeMaterial(player,gemupdata.getQ_consumable_item()) == false) {
							stResGemErrorToClient(player,(byte)4,0);
							return;
						}
						ManagerPool.playerManager.addZhenqi(player,-gemupdata.getQ_consumable_zhenqi(),AttributeChangeReason.GEM);//扣真气
						List<Integer> rndList = new ArrayList<Integer>();
						rndList.add(gemupdata.getQ_normal_rnd());
						rndList.add(gemupdata.getQ_crit_rnd());
						rndList.add(gemupdata.getQ_max_crit_rnd());
						int idx = RandomUtils.randomIndexByProb(rndList);
						int exp=0;
						if (idx == 1) {
							exp = gemupdata.getQ_crit_exp();
						}else if (idx == 2) {
							exp = gemupdata.getQ_max_crit_exp();
						}else {
							exp = gemupdata.getQ_normal_exp();
						}
						gem.setExp(gem.getExp()+ exp);
						int sexp = gemup(player,pos,gem,gemupdata);
						smsg.setExptype((byte) idx);
						smsg.setExp(exp-sexp);//去掉溢出经验
						log.setZhenqi( gemupdata.getQ_consumable_zhenqi());
						log.setItem(gemupdata.getQ_consumable_item());
						log.setAddexp(exp);
						log.setIscrit(idx);
//						ManagerPool.activitiesManager.sendActivitiesInfo(player,false);
					}
				}

				if (gem.getIsact() == 0) {
					if (result == 1) {
						int oldfightpower=FightPowerManager.getInstance().calAllFightPower(player);
						gem.setFailactnum((short) 0);
						gem.setIsact((byte) 1);
						MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜您，宝石激活成功。"));
						ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.GEM);
						int newfightpower=FightPowerManager.getInstance().calAllFightPower(player);
						int fightpower = newfightpower - oldfightpower;
						if(gem.getGrid() >= 3){
							ParseUtil parseUtil = new ParseUtil();
							parseUtil.setValue(String.format(ResManager.getInstance().getString("恭喜玩家【%s】激活【%s】战斗力提升%d!{@}"), player.getName(),gemname,fightpower), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.GEM_B.getValue()));
							MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.GEM_B.getValue());
						}

						
					}else {
						gem.setFailactnum( (short) (gem.getFailactnum()+1));
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("宝石激活未成功，请再接再厉。"));
					}

				}

				smsg.setPos((byte) pos);
				smsg.setResult(result);
				smsg.setGeminfo(getGeminfo(gem,pos,grid));
				MessageUtil.tell_player_message(player, smsg);
				log.setPos(grid);
				log.setLv(gem.getLevel());
				log.setResult(result);
				log.setType(gem.getIsact());
				log.setPlayerId(player.getId());
				log.setSid(player.getCreateServerId());
				LogService.getInstance().execute(log);
				if (gem.getLevel() >= 6) {
					refreshGem(player);
				}
			}
		}else{
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("获得指定部位宝石出错。"));
		}
	}


	/**宝石升级(返回溢出经验)
	 * 
	 * @param player
	 * @param pos
	 * @param gem
	 */
	public int gemup(Player player ,int pos,Gem gem,Q_gem_upBean gemupdata){
		if (gemupdata.getQ_is_up() > 0) {
			if (gem.getExp() >= gemupdata.getQ_need_up_exp()) {
				int sexp = gem.getExp() - gemupdata.getQ_need_up_exp();
				gem.setLevel(gem.getLevel()+1);
				gem.setExp(0);
				String upid = gemupdata.getQ_gem_type()+"_"+gem.getLevel();
				Q_gem_upBean gemupNext = getGemUpData(upid);
				//checkOpenLimit(player);		//检查是否开放更高阶宝石
				ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.GEM);
				if (gem.getLevel() >= 3) {
					addGemBuff(player);
				}
				int posid = pos+1;
				Q_gem_activationBean gemactdata = getGemActData(posid +"_"+(gem.getGrid()+1));
				if (gemactdata !=null) {
					if (gem.getLevel() >= 3) {
						Q_gem_upBean dbname = getGemUpData(gemactdata.getQ_gem_type()+"_"+ (gem.getLevel()-1));
						String posname = ManagerPool.equipstrengManager.getPosname(pos+1);
						ParseUtil parseUtil = new ParseUtil();
						parseUtil.setValue(String.format(ResManager.getInstance().getString("恭喜玩家【%s】将%s上的【%s】成功升至【%d】品!{@}"), player.getName(),posname,dbname.getQ_gem_name(),gem.getLevel()), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.GEM_A.getValue()));
						MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.GEM_A.getValue());
					}
					
					if(gemupNext.getQ_is_up() == 0 && gemactdata.getQ_notice()== 1){
						//MessageUtil.notify_All_player(Notifys.CUTOUT,"恭喜【{1}】将{2}上的【{3}】成功升至顶级！",player.getName(),posname,dbname.getQ_gem_name());
					}
					Gem[] gempos = getPosGems(player,pos);	
					Gem gem2 = checkGenLeast(gempos);	//得到指定部位第2个可升级宝石
					Q_gem_activationBean dbact = getGemActData(posid+"_"+(gem2.getGrid()+1));
					if (dbact != null) {
						Q_gem_upBean dbup = getGemUpData(dbact.getQ_gem_type()+"_"+gem2.getLevel());
						if (dbup != null) {
							if (dbup.getQ_is_up() > 0) {	//选中 的第2个宝石还可以升级
								gem2.setExp(gem2.getExp() + sexp);		//第2颗宝石得到溢出经验
								ResGemExtraExpMessage smsg = new ResGemExtraExpMessage();
								smsg.setExp(sexp);
								smsg.setGeminfo(getGeminfo(gem2,pos,gem2.getGrid()));
								smsg.setPos((byte) pos);
								MessageUtil.tell_player_message(player, smsg);
								return sexp;
							}
						}
					}
				}
			}
		}
		return 0;
	}
	
	
	
	
	/**
	 * 检查并收取材料
	 * @return 
	 */
	public boolean checkTakeMaterial(Player player,String string){
		ArrayList<Integer[]> itemlist =ManagerPool.equipstrengManager.getAnalyzeString(string);
		boolean is = true;
		String txt = "";
//		int itemid = 0;
		long action=Config.getId();
		if (itemlist.size() > 0) {
			for (Integer[] integers : itemlist) {
				int num = ManagerPool.backpackManager.getItemNum(player,integers[0]);
				if (num < integers[1]) {
					Q_itemBean itemBean = ManagerPool.dataManager.q_itemContainer.getMap().get(integers[0]);
					is=false;
					txt=txt+" "+(integers[1]-num)+ResManager.getInstance().getString("个")+itemBean.getQ_name();
//					itemid = integers[0];
				}
			}

			
			if (is) {
				for (Integer[] integers : itemlist) {
					if (integers[1] > 0){
						if (ManagerPool.backpackManager.removeItem(player, integers[0], integers[1],Reasons.GEM_JH_ITEM,action) == false) {
							return false;
						}
					}
				}
				return true;
			}else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，所需材料不足,缺少{1}。"),txt);
			}
		}
		return false;
	}
	

	
	
	
	/**发送错误消息给前端
	 * 类型 ：1激活真气不足，2激活道具不足，3升级真气不足，4升级道具不足
	 * @param player
	 */
	public void stResGemErrorToClient(Player player,byte type , int num ){
		ResGemErrorInfoMessage smsg = new ResGemErrorInfoMessage();
		smsg.setType(type);
		smsg.setIntnum(num);
		MessageUtil.tell_player_message(player, smsg);	
	}

	
	
	/**激活所有宝石（GM）
	 * 
	 * @param player
	 */
	public void testActAllGem(Player player ){
		Gem[][] gems = player.getGems();
		for (int i = 0; i < gems.length; i++) {
			Gem[] gempos = gems[i];
			for (int j = 0; j < gempos.length; j++) {
				gempos[j].setIsact((byte) 1);
			}
		}
		ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.GEM);
		refreshGem(player);
		MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("宝石全部激活"));
		
	}
	
	
	
	/**升级所有宝石（GM）
	 * 
	 * @param player
	 */
	public void testUPAllGem(Player player ){
		Gem[][] gems = player.getGems();
		for (int i = 0; i < gems.length; i++) {
			Gem[] gempos = gems[i];
			for (int j = 0; j < gempos.length; j++) {
				gems[i][j].setIsact((byte) 1);
				gems[i][j].setLevel(6);
			}
		}
		ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.GEM);
		refreshGem(player);
		MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("宝石全部满级"));
		//checkOpenLimit(player);//检查是否开放更高阶宝石
	}
	
	
	
	/**升级指定位置宝石（GM）
	 * 
	 * @param player
	 */
	public void testUPPosGem(Player player ,int pos ,int idx ,int lv){
		Gem[][] gems = player.getGems();
		if (gems[pos-1] != null) {
			if (gems[pos-1][idx-1] != null) {
				if (lv > 6) {
					lv = 6;
				}
				gems[pos-1][idx-1].setIsact((byte) 1);
				gems[pos-1][idx-1].setLevel(lv);
				String posname = ManagerPool.equipstrengManager.getPosname(pos);
				MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("{1}部位，第{2}颗宝石设置为{3}级"),posname,""+idx,lv+"");
				ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.GEM);
				refreshGem(player);
			}else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("每个部位最多5个宝石，请输入（1-5）"));
			}
		}else{
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("装备部位不存在，请输入（1-10）"));
		}
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
			Collections.sort(tab,new Comparator<Integer>() {	
				@Override
				public int compare(Integer o1, Integer o2) {
					if (o1 > o2) {
						return 1;
					}
					return 0;
				}
			});
			return tab.get(0);	//0 不加BUFF，1=3颗3级，2=3颗六级，3=5颗6级
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
		ManagerPool.buffManager.removeByBuffId( player, gembuffa);
		ManagerPool.buffManager.removeByBuffId( player, gembuffb);
		ManagerPool.buffManager.removeByBuffId( player, gembuffc);
		if (type > 0) {
			if (type >= 1) {
				ManagerPool.buffManager.addBuff(player, player, gembuffa, 0, 0, 0);
			}
			
			if (type >= 2) {
				ManagerPool.buffManager.addBuff(player, player, gembuffb, 0, 0, 0);
			}
			
			if (type >= 3) {
				ManagerPool.buffManager.addBuff(player, player, gembuffc, 0, 0, 0);
			}
		}
	}

	/**获取身上宝石等级之和
	 * 
	 * @param player
	 * @return
	 */
	public int getGemLevel(Player player){
		int lv= 0;
		Gem[][] gems = player.getGems();
		for (int i = 0; i < gems.length; i++) {
			Gem[] gempos = ManagerPool.gemManager.getPosGems(player,i);	//得到位置上可显示的宝石
			if (gempos != null) {
				for (int j = 0; j < gempos.length; j++) {
					if (gempos[j].getLevel() >= 6) {
						lv = lv + 6;
					}else {
						lv = lv + gempos[j].getLevel();
					}
				}
			}
		}
		return lv;
	}
	
	
	
	
	

//	/**设置强化宝石 (移到脚本去了)
//	 * 
//	 * @param parameter
//	 * @param msg
//	 */
//	public void stReqGemUseStrengthenMessage(Player player,ReqGemUseStrengthenMessage msg) {
//		int type = 0;
//		String oldname="";
//		String newname="";
//		Item item = ManagerPool.backpackManager.getItemById(player, msg.getSendId());
//		if (msg.getItemmodelid() == 1111111111 && item.getItemModelId() == 1111111111) {
//			if(ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GEMQIANGHUA,Config.getId()) ){
//				type = 3;//初级蓝玉髓
//			}
//		}else if (msg.getItemmodelid() == 22222222 && item.getItemModelId() == 22222222) {
//			if(ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GEMQIANGHUA,Config.getId()) ){
//				type = 2 ;//初级祖母绿
//			}
//		}
//		
//		
//		if (type > 0) {
//			Gem[][] gems = player.getGems();
//			for (int i = 0; i < gems.length; i++) {
//				Gem[] gempos = getPosGems(player,i);	//得到位置上可显示的宝石
//				for (int j = 0; j < gempos.length; j++) {
//					int pos = i+1;
//					int idx = j+1;
//					String id = pos+"_"+idx;
//					Q_gem_activationBean gemactdata= getGemActData(id);
//					if (gemactdata != null) {
//						if (gempos[j].getLevel() == 6 && gemactdata.getQ_gem_type() == type) {
//							Q_gem_upBean olddata = getGemUpData(type+"_"+gempos[j].getLevel());
//							gempos[j].setLevel(gempos[j].getLevel()+100);
//							Q_gem_upBean newdata = getGemUpData(type+"_"+gempos[j].getLevel());
//							oldname = olddata.getQ_gem_name();
//							newname = newdata.getQ_gem_name();
//						}
//					}
//				}
//			}
//			MessageUtil.notify_player(player, Notifys.SUCCESS, "恭喜您把{1}升级为{2}", oldname,newname);
//			refreshGem(player);
//		}
//	}
//	
	

	
}








