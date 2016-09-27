package com.game.registrar.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.game.db.bean.GoldRechargeLog;
import com.game.db.bean.RegistrarBean;
import com.game.db.dao.GoldRechargeLogDAO;
import com.game.db.dao.RegistrarDao;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.player.manager.PlayerManager;
import com.game.player.message.ReqScriptCommonServerToWorldMessage;
import com.game.player.message.ResScriptCommonPlayerWorldToClientMessage;
import com.game.player.message.ResScriptCommonServerToServerMessage;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.utils.MessageUtil;
import com.game.utils.TimeUtil;

public class RegistrarManager {
	private static Logger log = Logger.getLogger(RegistrarManager.class);
	private static Object obj = new Object();
	//玩家管理类实例
	private static RegistrarManager manager;
	
	private RegistrarManager(){}
	
	public static RegistrarManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new RegistrarManager();
			}
		}
		return manager;
	}
	//
	private RegistrarDao registrardao = new RegistrarDao();
	private GoldRechargeLogDAO goldrechargelogdao = new GoldRechargeLogDAO(); 
	//登陆发送登录器任务情况
	public void sendRegistrarReward(Player player){
		ResScriptCommonServerToServerMessage resmsg = new ResScriptCommonServerToServerMessage();
		Map<String, String> paramMap = new HashMap<String, String>();
		try {
			if(player.getLoginType()==5){
				paramMap.put("task", "1"); //账号用登录器登陆过
				RegistrarBean registrabean = registrardao.selectbyuserid(Long.valueOf(player.getUserId()));
				if(registrabean==null || registrabean.getLastregistrartime()<=0){ 
					paramMap.put("rewardtype", "1"); //礼包类型首次登陆
					paramMap.put("canreceive", "1"); //能领取
					if(registrabean==null) registrardao.insert(new RegistrarBean(Long.valueOf(player.getUserId()), 0L, 0, 0));
				}else{
					paramMap.put("rewardtype", "2"); //礼包类型普通登陆
					long now = System.currentTimeMillis();
					long last = registrabean.getLastregistrartime();
					if(TimeUtil.isSameDay(last, now)){  //同一天领取过  消掉
						paramMap.put("canreceive", "2");
					}else{  							//非同一天领取过  可以领取
						paramMap.put("canreceive", "1");
					}
				}
			}else{
				paramMap.put("task", "0"); //账号不是登录器登
				RegistrarBean registrabean = registrardao.selectbyuserid(Long.valueOf(player.getUserId()));
				if(registrabean==null || registrabean.getLastregistrartime()<=0){
					paramMap.put("rewardtype", "1"); //礼包类型首次登陆
					paramMap.put("canreceive", "0"); //不可领取
					if(registrabean==null) registrardao.insert(new RegistrarBean(Long.valueOf(player.getUserId()), 0L, 0, 0));
				}else{
					paramMap.put("rewardtype", "2"); //礼包类型普通礼包
					long now = System.currentTimeMillis();
					long last = registrabean.getLastregistrartime();
					if(TimeUtil.isSameDay(last, now)){  //同一天领取过  消掉
						paramMap.put("canreceive", "2");
					}else{  							//非同一天领取过  可以领取
						paramMap.put("canreceive", "0");
					}
				}
			}
		} catch (NumberFormatException e) {
			log.error(e, e);
		} catch (SQLException e) {
			log.error(e, e);
		}
		String jsondata = JSONserializable.toString(paramMap);
		resmsg.setScriptid(1200);
		resmsg.setType(2);
		resmsg.setMessageData(jsondata);
		MessageUtil.send_to_game(player, resmsg);
	}
	//领取奖励
	public void receiveRegistrarReward(ReqScriptCommonServerToWorldMessage msg){
		Map<String, String> paramMap = (Map<String, String>) JSONserializable.toObject(msg.getMessageData(), HashMap.class);
		Player player = PlayerManager.getInstance().getPlayer(Long.valueOf(paramMap.get("playerid")));
		ResScriptCommonServerToServerMessage resmsg = new ResScriptCommonServerToServerMessage();
		try {
			if(player.getLoginType()==5){
				paramMap.put("task", "1"); //账号用登录器登录的
				RegistrarBean registrabean = registrardao.selectbyuserid(Long.valueOf(player.getUserId()));
				if(registrabean==null || registrabean.getLastregistrartime()<=0){ //账号没领过 
					paramMap.put("rewardtype", "1"); //礼包类型首次登陆
					paramMap.put("canreceive", "1"); //能领取
					if(registrabean==null) registrardao.insert(new RegistrarBean(Long.valueOf(player.getUserId()), 0L, 0, 0));
				}else{  //账号领取过
					paramMap.put("rewardtype", "2"); //礼包类型普通登陆
					long now = System.currentTimeMillis();
					long last = registrabean.getLastregistrartime();
					if(TimeUtil.isSameDay(last, now)){  //同一天领取过  消掉
						paramMap.put("canreceive", "2");
					}else{  							//非同一天领取过  可以领取
						paramMap.put("canreceive", "1");
					}
				}
			}else{
				paramMap.put("task", "0"); //账号不是登录器登
				RegistrarBean registrabean = registrardao.selectbyuserid(Long.valueOf(player.getUserId()));
				if(registrabean==null || registrabean.getLastregistrartime()<=0){
					paramMap.put("rewardtype", "1"); //礼包类型首次登陆
					paramMap.put("canreceive", "0"); //不可领取
				}else{
					paramMap.put("rewardtype", "2"); //礼包类型普通礼包
					long now = System.currentTimeMillis();
					long last = registrabean.getLastregistrartime();
					if(TimeUtil.isSameDay(last, now)){  //同一天领取过  消掉
						paramMap.put("canreceive", "2");
					}else{  							//非同一天领取过  不可领取
						paramMap.put("canreceive", "0");
					}
				}
			}
		} catch (NumberFormatException e) {
			log.error(e, e);
		} catch (SQLException e) {
			log.error(e, e);
		}
		String jsondata = JSONserializable.toString(paramMap);
		//修改player状态
		if(paramMap.get("canreceive").equals("1")){  //可以领取
			try {
				registrardao.updateRegistrarByUserid(new RegistrarBean(Long.valueOf(player.getUserId()), System.currentTimeMillis()));
				resmsg.setScriptid(1200);
				resmsg.setType(1); //领取奖励
				resmsg.setMessageData(jsondata);
				MessageUtil.send_to_game(player, resmsg);
			} catch (NumberFormatException e) {
				log.error(e, e);
			} catch (SQLException e) {
				log.error(e, e);
			}
		}
	}
	
	
	//没用了 绑定在角色身上  在游戏服即可处理 游戏服不能读数据但是现在可以了 #还是要到世界服来处理就不用了
	//首冲奖励 1客户端发起请求 1021 1  2游戏服到世界服 1021 2  3世界服到游戏服1021 3 ，到客户端1021 4
	public void recieveFirstRechargeReward(ReqScriptCommonServerToWorldMessage msg){
		Map<String, String> paramMap = (Map<String, String>) JSONserializable.toObject(msg.getMessageData(), HashMap.class);
		Player player = PlayerManager.getInstance().getPlayer(Long.valueOf(paramMap.get("playerid")));
		//判断能不能领
		int recievestate = 0; // 1领取成功   2已经领取  3不能领取  4领取失败
		try {
			//判断是否充值过
			List<GoldRechargeLog> loglist = goldrechargelogdao.selectByUserid(Long.valueOf(player.getUserId())); 
			if(loglist==null || loglist.size()<=0){  //没有记录 没充值过 不能领奖
				recievestate = 3;
			}else{  //有过充值  可以领奖
				recievestate = 1;
			}
		} catch (NumberFormatException e) {
			log.error(e, e);
		} catch (SQLException e) {
			log.error(e, e);
		}
		if(recievestate==1){ //领奖
			try {
				int result = registrardao.updateRechargeByUserid(new RegistrarBean(Long.valueOf(player.getUserId())));
				if(result>0){ //修改状态成功  给予领奖
					ResScriptCommonServerToServerMessage resmsg = new ResScriptCommonServerToServerMessage();
					//到游戏服
					resmsg.setScriptid(1201);
					resmsg.setType(3);
					MessageUtil.send_to_game(player, resmsg);
				}else{ //修改状态失败 说明记录不存在或者数据库异常
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("首冲奖励领取失败"));
					recievestate = 4; //领取失败 可以领但是没领到
				}
			} catch (NumberFormatException e) {
				log.error(e, e);
			} catch (SQLException e) {
				log.error(e, e);
			}
		}else if(recievestate==3){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("领取失败,请先充值"));
		}
	}
	
	public int getRechargeInDay(Player player, Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		long startTime = cal.getTimeInMillis();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		long endTime = cal.getTimeInMillis();
		
		try{
			//判断是否充值过
			List<GoldRechargeLog> loglist = goldrechargelogdao.selectByUseridType(Long.valueOf(player.getUserId()), startTime, endTime);
			if(loglist!=null && loglist.size()>0){  //没有记录 没充值过 不能领奖
				int gold = 0;
				for (int i = 0; i < loglist.size(); i++) {
					gold += loglist.get(i).getGold();
				}
				return gold;
			}
		}catch (Exception e) {
			log.error(e, e);
		}
		
		return 0;
	}
	

	public void SWDispatch(ReqScriptCommonServerToWorldMessage msg){
		if(msg.getType()==1){
			receiveRegistrarReward(msg);
		}
	}
	
	public void SWDispatchRecharge(ReqScriptCommonServerToWorldMessage msg){
		if(msg.getType()==2){
			recieveFirstRechargeReward(msg);
		}
	}
	
	
	public Map<String, String> getPlayerRegistrarParams(Player player){
		if(player!=null){
			return getPlayerRegistrarParams(Long.valueOf(player.getUserId()));
		}else{
			return new HashMap<String, String>();
		}
	}
	
	public Map<String, String> getPlayerRegistrarParams(long playerid){
		Map<String, String> playerparams = new HashMap<String, String>();
		String params = "";
		try {
			RegistrarBean b = registrardao.selectparambyuserid(playerid);
			if(b==null){
				registrardao.insert(new RegistrarBean(playerid, 0L, 0, 0));
				params = "";
			}else{
				params = b.getParams();
			}
			
		} catch (NumberFormatException e) {
			log.error(e, e);
		} catch (SQLException e) {
			log.error(e, e);
		}
		playerparams =  StringUtils.isBlank(params)? new HashMap<String, String>():(Map<String, String>) JSONserializable.toObject(params, HashMap.class);
		return playerparams;
	}
	
	public int savePlayerParams(long userid, Map<String, String> paramsmap){
		String params = JSONserializable.toString(paramsmap);
		try {
			int r = registrardao.updateRegistrarParamByUserid(new RegistrarBean(userid, params));
			return r;
		} catch (NumberFormatException e) {
			log.error(e, e);
		} catch (SQLException e) {
			log.error(e, e);
		}
		return 0;
	}
	
	public int savePlayerParams(Player player, Map<String, String> paramsmap){
		return savePlayerParams(Long.valueOf(player.getUserId()), paramsmap);
	}
	
	public List<GoldRechargeLog> getPlayerRechargeLogList(Player player){
		List<GoldRechargeLog> loglist = new ArrayList<GoldRechargeLog>();
		try {
			loglist = goldrechargelogdao.selectByUseridType(Long.valueOf(player.getUserId()), 0);
		} catch (NumberFormatException e) {
			log.error(e, e);
		} catch (SQLException e) {
			log.error(e, e);
		} //得到玩家所有正常充值的记录
		return loglist;
	}
	
	public int getPlayerSumRecharge(Player player){
		int sum=0;
		List<GoldRechargeLog> loglist = new ArrayList<GoldRechargeLog>();
		loglist = getPlayerRechargeLogList(player);
		for(GoldRechargeLog g: loglist){
			sum += g.getGold();
		}
		return sum;
	}
	
	public void sendHasRegistrared(Player player){
		int receive = 1;
		try {
			RegistrarBean bean = registrardao.selectbyuserid(Long.valueOf(player.getUserId()));
			if(bean==null || bean.getFirstrechargeed()>0){
				receive = 0;
				if(bean==null) registrardao.insert(new RegistrarBean(Long.valueOf(player.getUserId()), 0L, 0, 0));
			}
		} catch (NumberFormatException e) {
			log.error(e, e);
		} catch (SQLException e) {
			log.error(e, e);
		}
		ResScriptCommonPlayerWorldToClientMessage resmsg = new ResScriptCommonPlayerWorldToClientMessage();
		resmsg.setScriptid(1201);
		resmsg.setType(6);
		Map<String, String> resmap = new HashMap<String, String>();
		resmap.put("hasreceive", ""+receive);
		resmsg.setMessageData(JSONserializable.toString(resmap));
		MessageUtil.tell_player_message(player, resmsg);
	}
}
