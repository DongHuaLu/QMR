package com.game.registrar.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.game.activities.manager.ActivitiesManager;
import com.game.activities.script.IActivityScript;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.chat.bean.GoodsInfoRes;
import com.game.config.Config;
import com.game.db.dao.GoldRechargeLogDAO;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.mail.manager.MailServerManager;
import com.game.manager.ManagerPool;
import com.game.pet.manager.PetOptManager;
import com.game.player.manager.PlayerManager;
import com.game.player.message.ReqScriptCommonPlayerToServerMessage;
import com.game.player.message.ReqScriptCommonServerToWorldMessage;
import com.game.player.message.ResScriptCommonPlayerToClientMessage;
import com.game.player.message.ResScriptCommonServerToServerMessage;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.registrar.script.IRegistrar;
import com.game.script.manager.ScriptManager;
import com.game.script.structs.ScriptEnum;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.vip.manager.VipManager;
import com.game.vip.struts.GuideType;

public class RegistrarManager {
	
	private static Logger log = Logger.getLogger(RegistrarManager.class);
	private static Logger faillog = Logger.getLogger("GIVEREWARDFAILED");
	
	private static Object obj = new Object();
	// 管理类实例
	private static RegistrarManager manager;

	private RegistrarManager(){}

	public static RegistrarManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new RegistrarManager();
			}
		}
		return manager;
	}
	
	//
	private GoldRechargeLogDAO goldrechargelogdao = new GoldRechargeLogDAO();
	
	public void CSDispatch(Player player, ReqScriptCommonPlayerToServerMessage msg){
		if(msg.getType()==1){  //领取奖励
			ReceiveRegistrarReward(player, msg);
		}else if(msg.getType()==2){ //改变达成目标状态
			changeRegistrared(player, msg);
		}
		
	}
	public void CSDispatchRecharge(Player player, ReqScriptCommonPlayerToServerMessage msg){
		if(msg.getType()==1){
			receiveFirstRechargeReward(player);
		}
	}
	public void WSDispatch(Player player, ResScriptCommonServerToServerMessage msg){
		if(msg.getType()==1){ //给奖励
			RegistrarManager.getInstance().GiveRegistrarReward(player, msg);
		}else if(msg.getType()==2){
			RegistrarManager.getInstance().sendRegistrarInfoToClient(player, msg);
		}
	}
	public void WSDispatchRecharge(Player player, ResScriptCommonServerToServerMessage msg){
		if(msg.getType()==3){ //
			RegistrarManager.getInstance().giveFirstReceiveReward(player);
		}
	}
	public void WSDispatchActivities(Player player, ResScriptCommonServerToServerMessage msg){
		if(msg.getType()==1){
			giveBETAActivitiesReward(player, msg);
		}else if(msg.getType()==2){
			refreshActivities(player);
		}
	}
	
	//更新活动信息
	public void refreshActivities(Player player){
		ActivitiesManager.getInstance().sendActivitiesInfo(player, true);
	}
	
	//完成任务 task值为1 registrar=1
	public void changeRegistrared(Player player, ReqScriptCommonPlayerToServerMessage msg){
		ReqScriptCommonServerToWorldMessage reqmsg = new  ReqScriptCommonServerToWorldMessage();
		reqmsg.setScriptid(1200);
		reqmsg.setType(1);
		MessageUtil.send_to_world(reqmsg);
	}
	
	//客户端发起请求 领取登录器奖励
	public void ReceiveRegistrarReward(Player player, ReqScriptCommonPlayerToServerMessage msg){
		ReqScriptCommonServerToWorldMessage reqmsg = new ReqScriptCommonServerToWorldMessage();
		reqmsg.setScriptid(1200);
		reqmsg.setType(1);
		Map<String, String> map = new HashMap<String, String>();
		map.put("playerid", ""+player.getId());
		reqmsg.setMessageData(JSONserializable.toString(map));
		MessageUtil.send_to_world(reqmsg);
	}
	
	//task  rewardtype  canreceive  1可领 0不可领
	public void sendRegistrarInfoToClient(Player player, ResScriptCommonServerToServerMessage msg){
		HashMap<String, String> parammap = (HashMap<String, String>) JSONserializable.toObject(msg.getMessageData(), HashMap.class);
		HashMap<String, String> toclientmap = new HashMap<String, String>();
		String task = parammap.get("task"); //账号是否达到过登录器奖励条件
		String rewardtype = parammap.get("rewardtype");  //奖励类型
		String canreceive = parammap.get("canreceive");  //是否可领取
		//脚本中得到奖励内容
		String rewardcontent = getRewardContent(rewardtype, player);
		//发送给前端的数据
		toclientmap.put("task", task);
		toclientmap.put("canreceive", canreceive);
		toclientmap.put("rewardtype", rewardtype);
		toclientmap.put("rewardcontent", rewardcontent);
		//发送给前端
		ResScriptCommonPlayerToClientMessage resmsg = new ResScriptCommonPlayerToClientMessage();
		resmsg.setScriptid(1200);
		resmsg.setType(1);
		resmsg.setMessageData(JSONserializable.toString(toclientmap));
		MessageUtil.tell_player_message(player, resmsg);
	}
	
	//世界服务器端发来消息 给用户发送登录器奖励  rewardtype 1 大礼包 2 小礼包
	public void GiveRegistrarReward(Player player, ResScriptCommonServerToServerMessage msg){
		HashMap<String, String> parammap = (HashMap<String, String>) JSONserializable.toObject(msg.getMessageData(), HashMap.class);
		HashMap<String, String> toclientmap = new HashMap<String, String>();
		String task = parammap.get("task");
		String rewardtype = parammap.get("rewardtype");
		String canreceive = parammap.get("canreceive");
		String rewardcontent = getRewardContent(rewardtype, player);
		if("1".equals(task) && "1".equals(canreceive)){ //完成任务且可领奖
			if(!StringUtils.isBlank(rewardcontent)){ //奖励内容不为空 给奖励
				boolean tobackpack = true; //是否给到包裹里
				List<Item> mailitems = new ArrayList<Item>();
				long actionid = Config.getId();
				//解析字符串
				Matcher matcher = pattern.matcher(rewardcontent);
				while(matcher.find()){
					int itemid = Integer.parseInt(matcher.group(2));
					int itemnum = Integer.parseInt(matcher.group(4));
					//生成物品
					List<Item> rewarditems = new ArrayList<Item>();
					if(itemid>0){		  //非数值
						rewarditems = Item.createItems(itemid, itemnum, true, 0);
						if(tobackpack){
							//给玩家
							if(ManagerPool.backpackManager.getAbleAddNum(player, itemid, true, 0)>itemnum){  //登陆奖励 绑定与过期
								BackpackManager.getInstance().addItems(player, rewarditems, Reasons.REGISTRARREWARD, actionid);
							}else{ //包裹不足了 转向邮件
								tobackpack = false;
								mailitems.addAll(rewarditems);
							}
						}else{ //加入邮件物品中
							mailitems.addAll(rewarditems);
						}
					}else if(itemid==-1){ //铜币
						BackpackManager.getInstance().changeMoney(player, itemnum, Reasons.REGISTRARREWARD, actionid);
					}else if(itemid==-2){ //元宝
						BackpackManager.getInstance().addGold(player, itemnum, Reasons.REGISTRARREWARD, actionid);
					}else if(itemid==-3){ //真气
						PlayerManager.getInstance().addZhenqi(player, itemnum, AttributeChangeReason.LOGINEXE);
					}else if(itemid==-4){ //经验
						PlayerManager.getInstance().addExp(player, itemnum, AttributeChangeReason.LOGINEXE);
					}else if(itemid==-5){ //礼金
						BackpackManager.getInstance().changeBindGold(player, itemnum, Reasons.REGISTRARREWARD, actionid);
					}
				}
				//
				if(mailitems.size()>0){ //邮件有物品 发邮件
					String title = ResManager.getInstance().getString("任务奖励");
					String content = ResManager.getInstance().getString("每日使用登录器进入游戏奖励");
					MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), title, content, (byte)1, 0, mailitems);
				}
				canreceive = "2"; //发送给前端消失掉
				toclientmap.put("task", task);
				toclientmap.put("canreceive", canreceive);
				toclientmap.put("rewardtype", rewardtype);
				toclientmap.put("rewardcontent", rewardcontent);
				//发送给前端
				ResScriptCommonPlayerToClientMessage resmsg = new ResScriptCommonPlayerToClientMessage();
				resmsg.setScriptid(1200);
				resmsg.setType(1);
				resmsg.setMessageData(JSONserializable.toString(toclientmap));
				MessageUtil.tell_player_message(player, resmsg);
				
				if(rewardtype.equals("1")){  //首次登陆大奖  礼金500 双倍收益丹2 装备强化石10
					MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功领取每日登录器任务奖励 礼金*500 双倍收益丹*2 装备强化石*10"));
				}else if(rewardtype.equals("2")){
					MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功领取每日登录器任务奖励 礼金*50"));
				}
			}
		}
	}
	//脚本中得到奖励内容 1 大礼包 2 小礼包
	public String getRewardContent(String type, Player player){
		String content = "";
		if(!StringUtils.isBlank(type) && StringUtils.isNumeric(type)){
			int rtype = Integer.parseInt(type);
			IRegistrar script = (IRegistrar) ScriptManager.getInstance().getScript(1200);
			if(rtype==1){
				content = script.getFirstReward(player);
			}else if(rtype==2){
				content = script.getCommonReward(player);
			}
		}
		return content;
	}
	
	//客户端请求领取首冲奖励  1客户端发起请求 1201 type 1  2游戏服到世界服 1201 2  3世界服到游戏服1201 3  游戏服到世界服  游戏服到客户端1201 type 4   
	public void receiveFirstRechargeReward(Player player){
		if(player.getReceivedFirstRecharge()<=0){  //没领取过 发到世界服判断
			ReqScriptCommonServerToWorldMessage reqmsg = new ReqScriptCommonServerToWorldMessage();
			reqmsg.setScriptid(1201); reqmsg.setType(2);
			Map<String, String> parammap = new HashMap<String, String>();
			parammap.put("playerid", ""+player.getId());
			reqmsg.setMessageData(JSONserializable.toString(parammap));
			MessageUtil.send_to_world(reqmsg);
		}else{
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该角色已经领取过"));
		}
	}
	
	//发放首冲奖励
	public void giveFirstReceiveReward(Player player){
		if (player.getReceivedFirstRecharge() > 0) {
			return ;
		}
		//发放侍宠
		PetOptManager.getInstance().addPet(player, 2,"rechargegift",Config.getId());
		//发放物品奖励
		IRegistrar script = (IRegistrar) ScriptManager.getInstance().getScript(1201);
		String rewardcontent = "";
		if(player.getSex()==2){  //2-first-女  1-common-男
			rewardcontent = script.getFirstReward(player);
		}else{
			rewardcontent = script.getCommonReward(player);
		}
		String[] rewards = rewardcontent.split(",");
		boolean tobackpack = true;
		List<Item> mailitems = new ArrayList<Item>();
		long actionid = Config.getId();
		for(String reward: rewards){
			String[] params = reward.split("_");
			int itemid = 0;
			int num = 0;
			boolean bind = false;
			int losttime = 0;
			int gradenum = 0;
			String append = "";
			itemid = Integer.parseInt(params[0]);
			num    = Integer.parseInt(params[1]);
			if(itemid>0){		  //非数值
				if(params.length>=4){
					bind = Integer.parseInt(params[2])==1? true:false;
					losttime = Integer.parseInt(params[3]);  //秒数
					
				}
				if(params.length>=6){
					gradenum = Integer.parseInt(params[4]);
					append = params[5];
				}
				List<Item> createItems = Item.createItems(itemid, num, bind, losttime, gradenum, append);
				if(tobackpack){
					if(ManagerPool.backpackManager.getAbleAddNum(player, itemid, true, 0)>num){  //登陆奖励 绑定与过期
						BackpackManager.getInstance().addItems(player, createItems, Reasons.RECHARGEFIRST, actionid);
					}else{ //包裹不足了 转向邮件
						tobackpack = false;
						mailitems.addAll(createItems);
						faillog.info("[Player"+player.getName()+"|"+player.getId()+"]"+"toMail_"+JSONserializable.toString(createItems));
					}
				}else{ //加入邮件物品中
					mailitems.addAll(createItems);
					faillog.info("[Player"+player.getName()+"|"+player.getId()+"]"+"toMail_"+JSONserializable.toString(createItems));
				}
			}else if(itemid==-1){ //铜币
				if(!BackpackManager.getInstance().changeMoney(player, num, Reasons.RECHARGEFIRST, actionid)){
					faillog.info("[Player"+player.getName()+"|"+player.getId()+"]"+"登录器奖励添加铜币失败-1_"+num);
				}
			}else if(itemid==-2){ //元宝
				if(!BackpackManager.getInstance().addGold(player, num, Reasons.RECHARGEFIRST, actionid)){
					faillog.info("[Player"+player.getName()+"|"+player.getId()+"]"+"登录器奖励添加元宝失败-2_"+num);
				}
			}else if(itemid==-3){ //真气
				if(PlayerManager.getInstance().addZhenqi(player, num, AttributeChangeReason.LOGINEXE)<=0){
					faillog.info("[Player"+player.getName()+"|"+player.getId()+"]"+"登录器奖励添加真气失败-3_"+num);
				}
			}else if(itemid==-4){ //经验
				PlayerManager.getInstance().addExp(player, num, AttributeChangeReason.LOGINEXE);
			}else if(itemid==-5){ //礼金
				if(!BackpackManager.getInstance().changeBindGold(player, num, Reasons.RECHARGEFIRST, actionid)){
					faillog.info("[Player"+player.getName()+"|"+player.getId()+"]"+"登录器奖励添加礼金失败-5_"+num);
				}
			}
		}
		if(mailitems.size()>0){
			String title = ResManager.getInstance().getString("首次充值礼包");  
			String content = ResManager.getInstance().getString("首次充值礼包");
			if(!MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), title, content, (byte)1, 0, mailitems)){
				faillog.info("[Player"+player.getId()+"]"+"SendMailFail_"+JSONserializable.toString(mailitems));
			}
		}
		player.setReceivedFirstRecharge(1); //设置首冲领奖标识
		MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("首次充值礼包领取成功"));
		//通知客户端
		ResScriptCommonPlayerToClientMessage resclientmsg = new ResScriptCommonPlayerToClientMessage(); 
		resclientmsg.setScriptid(1201);
		resclientmsg.setType(4);
		MessageUtil.tell_player_message(player, resclientmsg);
		
		ParseUtil parseUtil = new ParseUtil();
		parseUtil.setValue(String.format(ResManager.getInstance().getString("恭喜玩家【%s】激活美人白夷女成功，并获得稀有极品装备！{@}"), player.getName()), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.JIHUOMEINV.getValue()));
		MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.JIHUOMEINV.getValue());
		
	}
	
	//发放公测活动奖励  scriptid = 1202 type 1  message acid
	public void giveBETAActivitiesReward(Player player, ResScriptCommonServerToServerMessage msg){
		HashMap<String, String> msgdata = (HashMap<String, String>) JSONserializable.toObject(msg.getMessageData(), HashMap.class);
		int acid = msgdata.containsKey("acid")? Integer.parseInt(msgdata.get("acid")):0;
		int rank = msgdata.containsKey("rank")? Integer.parseInt(msgdata.get("rank")):0;
		int num  = msgdata.containsKey("num") ? Integer.parseInt(msgdata.get("num")) :0;
		if(acid == 21){  //重复500  美人百宝袋
			IActivityScript script = (IActivityScript) ScriptManager.getInstance().getScript(ScriptEnum.BETA_SUMRECHARGE500);
			script.getReward(player);
		}else if(acid == 23){ //重复2000 特修 大真 银票
			IActivityScript script = (IActivityScript) ScriptManager.getInstance().getScript(ScriptEnum.BETA_SUMRECHARGE2000);
			script.getReward(player);
		}else if(acid == 22){ //单次1000 
			IActivityScript script = (IActivityScript) ScriptManager.getInstance().getScript(ScriptEnum.BETA_SUMRECHARGE1000);
			script.getReward(player);
		}else if(acid == 24){ //单次5000
			IActivityScript script = (IActivityScript) ScriptManager.getInstance().getScript(ScriptEnum.BETA_SUMRECHARGE5000);
			script.getReward(player);
		}else if(acid == 25){ //单次10000
			IActivityScript script = (IActivityScript) ScriptManager.getInstance().getScript(ScriptEnum.BETA_SUMRECHARGE10000);
			script.getReward(player);
		}else if(acid == 26){ //等级排行榜
			if(rank == 1){
				IRegistrar script = (IRegistrar) ScriptManager.getInstance().getScript(1202);
				script.giveCommonReward(player);
			}else if(rank == 2){
				IRegistrar script = (IRegistrar) ScriptManager.getInstance().getScript(1203);
				script.giveCommonReward(player);
			}else if(rank == 3){
				IRegistrar script = (IRegistrar) ScriptManager.getInstance().getScript(1204);
				script.giveCommonReward(player);
			}
		}else if(acid == 27){ //坐骑排行榜
			if(rank == 1){
				IRegistrar script = (IRegistrar) ScriptManager.getInstance().getScript(1205);
				script.giveCommonReward(player);
			}else if(rank == 2){
				IRegistrar script = (IRegistrar) ScriptManager.getInstance().getScript(1206);
				script.giveCommonReward(player);
			}else if(rank == 3){
				IRegistrar script = (IRegistrar) ScriptManager.getInstance().getScript(1207);
				script.giveCommonReward(player);
			}
		}else if(acid == 28){ //武功排行榜
			if(rank == 1){
				IRegistrar script = (IRegistrar) ScriptManager.getInstance().getScript(1208);
				script.giveCommonReward(player);
			}else if(rank == 2){
				IRegistrar script = (IRegistrar) ScriptManager.getInstance().getScript(1209);
				script.giveCommonReward(player);
			}else if(rank == 3){
				IRegistrar script = (IRegistrar) ScriptManager.getInstance().getScript(1210);
				script.giveCommonReward(player);
			}
		}else if(acid==36){
			if(BackpackManager.getInstance().changeBindGold(player, num, Reasons.ACTIVITY_GIFT, Config.getId())){
				MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功领取封测礼金"));
			}else{
				faillog.info("[Player"+player.getName()+"|"+player.getId()+"]"+"给予封测礼金失败,数量"+num+".");
			}
		} else if(acid==45){ //国庆单日累计充值500
			IActivityScript script = (IActivityScript) ScriptManager.getInstance().getScript(ScriptEnum.GQRECHARGE500);
			script.getReward(player);
		} else if(acid==46){ //国庆单日累计充值2000
			IActivityScript script = (IActivityScript) ScriptManager.getInstance().getScript(ScriptEnum.GQRECHARGE2000);
			script.getReward(player);
		} else if(acid==47){ //国庆单日累计充值5000
			IActivityScript script = (IActivityScript) ScriptManager.getInstance().getScript(ScriptEnum.GQRECHARGE5000);
			script.getReward(player);
		} else if(acid==48){ //国庆单日累计充值10000
			IActivityScript script = (IActivityScript) ScriptManager.getInstance().getScript(ScriptEnum.GQRECHARGE10000);
			script.getReward(player);
		} else if(acid==49){ //国庆单日累计充值100000
			IActivityScript script = (IActivityScript) ScriptManager.getInstance().getScript(ScriptEnum.GQRECHARGE100000);
			script.getReward(player);
		}
		
	}
	
	//给客户端返回是否领取过首冲奖励 当前是否可领奖
	public void sendFirstRecharged(Player player){
		//调用脚本
		IRegistrar script = (IRegistrar) ScriptManager.getInstance().getScript(1201);
		script.callWorld(player);
	}
	
	//private static String rewarddemo= "[{\"id\":\"-1\",\"num\":\"1000\"},{\"id\":\"1001\",\"num\":\"10\"}]";
	
	private static Pattern pattern = Pattern.compile("(?:\\{\"(\\w+)\":\"(-?\\d+)\",\"(\\w+)\":\"(-?\\d+)\"\\})");

	
}
