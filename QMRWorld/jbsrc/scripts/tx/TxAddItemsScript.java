package scripts.tx;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.game.backend.manager.BackendManager;
import com.game.backpack.structs.Item;
import com.game.db.bean.GameUser;
import com.game.db.bean.Gold;
import com.game.db.bean.GoldRechargeLog;
import com.game.db.bean.Role;
import com.game.db.bean.TxConsumeLog;
import com.game.db.dao.GoldDao;
import com.game.db.dao.GoldRechargeLogDAO;
import com.game.db.dao.RoleDao;
import com.game.db.dao.TxConsumeLogDAO;
import com.game.db.dao.UserDao;
import com.game.dblog.LogService;
import com.game.json.JSONserializable;
import com.game.mail.manager.MailWorldManager;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.recharge.RechargeHistory;
import com.game.recharge.RechargelogLog;
import com.game.recharge.message.RechargeMessage;
import com.game.script.structs.ScriptEnum;
import com.game.txconsume.log.TxConsumesLog;
import com.game.txconsume.manager.TxConsumeManager;
import com.game.txconsume.script.ITxAddItemsScript;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.utils.ScriptsUtils;

public class TxAddItemsScript implements ITxAddItemsScript {
	
	private static Logger log = Logger.getLogger(TxAddItemsScript.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public int getId() {
		return ScriptEnum.TXADDITEMS;
	}
	
//	String items = "[{\"itemmodelid\":-1,\"num\":100,\"bind\":0,\"losttime\":0},{\"itemmodelid\":1001,\"num\":3,\"bind\":1,\"losttime\":1}]";
	
	private GoldRechargeLogDAO rechargelog = new GoldRechargeLogDAO();
	private TxConsumeLogDAO txconsumedao = new TxConsumeLogDAO();
	private UserDao userDao = new UserDao();
	private RoleDao roleDao = new RoleDao();
	private GoldDao dao = new GoldDao();
	
	@SuppressWarnings("unchecked")
	@Override
	public String reqAddItems(String requeststr) {
		HashMap<String, String> reqmap = (HashMap<String, String>) JSONserializable.toObject(requeststr, HashMap.class);
		//取出参数
		boolean isworldmail = false;
		String username = ParseUtil.getMapString(reqmap, "username");
		String roleid = ParseUtil.getMapString(reqmap, "roleid");
		String serverid = ParseUtil.getMapString(reqmap, "serverid");
		String oid = ParseUtil.getMapString(reqmap, "oid");
		String items = ParseUtil.getMapString(reqmap, "items");
		String content = ParseUtil.getMapString(reqmap, "content");
		String totalconsume = ParseUtil.getMapString(reqmap, "totalconsume");
		String desc = requeststr; //原始请求字符串
		String callbackurl = ParseUtil.getMapString(reqmap, "callbackurl");
		String userid = "";
		//返回值
		HashMap<String, String> resmap = new HashMap<String, String>();
		//参数验证
		if(StringUtils.isBlank(oid)){
			resmap.put("ret", "-1"); resmap.put("msg", "订单号为空");
		} else if(isOrderDuplicate(oid)){
			resmap.put("ret", "-5"); resmap.put("msg", "订单号重复");
		} else if(StringUtils.isBlank(totalconsume) || !StringUtils.isNumeric(totalconsume)){
			resmap.put("ret", "-2"); resmap.put("msg", "总消耗为空或者为非正数["+totalconsume+"]");
		} else if(StringUtils.isBlank(serverid) || !StringUtils.isNumeric(serverid)){
			resmap.put("ret", "-3"); resmap.put("msg", "服务器ID为空或者为非正数["+serverid+"]");
		} else if(StringUtils.isBlank(username)){
			resmap.put("ret", "-4"); resmap.put("msg", "用户名为空");
		} else if(StringUtils.isBlank(roleid)){
			resmap.put("ret", "-12"); resmap.put("msg", "角色ID为空");
		}  else{
			try{
				userid=getUsernameByServerid(username, serverid);
				if(StringUtils.isBlank(userid) ){
					resmap.put("ret", "-6"); resmap.put("msg", "指定服务器["+serverid+"]不存在用户["+username+"]");
				}else if(!isItemsValid(items)){
					resmap.put("ret", "-9"); resmap.put("msg", "道具字符串解析错误["+items+"]");
				}else {
					Role role = roleDao.selectById(Long.valueOf(roleid));
					if(role==null){
						resmap.put("ret", "-13"); resmap.put("msg", "指定Roleid["+roleid+"]不存在");
					}else{
						String roleuserid = role.getUserid();
						if(!userid.equals(roleuserid)){
							resmap.put("ret", "-14"); resmap.put("msg", "指定Roleid["+roleid+"]与用户名["+username+"]不匹配");
						}else{
							boolean isaddgold = isAddGold(items);
							if(isaddgold){ //是否发放元宝
								int addresult = txrechargegold(username, serverid, items, oid, Integer.parseInt(totalconsume), userid);
								if(addresult==0){ //发放成功
									resmap.put("ret", "0"); resmap.put("msg", "成功");
								}else{
									resmap.put("ret", "-10"); resmap.put("msg", "元宝发放失败["+addresult+"]");
								}
							}else{ //道具 通过脚本发放
								Player player = PlayerManager.getInstance().getPlayer(Long.valueOf(roleid)); 
								if(player!=null){ //角色在线
									ScriptsUtils.callGame(player, ScriptEnum.BACKENDSERVER, "addItemTx", oid, roleid, items); //调用游戏服发放道具
									resmap.put("ret", "0"); resmap.put("msg", "成功");
								}else{ //角色不在线
									isworldmail = true;
									int sendmail = sendmail(role.getName(), items);
									if(sendmail==1){
										resmap.put("ret", "0"); resmap.put("msg", "成功");
									}else{
										resmap.put("ret", "-15"); resmap.put("msg", "世界服邮件发送失败");
									}
								}
							}
						}
					}
				}
			}catch (Exception e) {
				log.error(e, e);
				resmap.put("ret", "-1024"); resmap.put("msg", "未知错误["+e+"]");
			}
			//记录
			if(ParseUtil.getMapInt(resmap, "ret")==0){  //是否正确才做记录
				final TxConsumeLog txlog = new TxConsumeLog();
				txlog.setUsername(username);
				txlog.setServerid(serverid);
				txlog.setUserid(userid);
				txlog.setRoleid(roleid);
				txlog.setRet(resmap.get("ret"));
				txlog.setMsg(resmap.get("msg"));
				txlog.setItems(items);
				txlog.setOid(oid);
				txlog.setState(1);
				txlog.setConsume(Integer.parseInt(totalconsume));
				txlog.setTimes(System.currentTimeMillis());
				txlog.setDate(sdf.format(new Date(txlog.getTimes())));
				txlog.setContent(content);
				txlog.setDesc(desc);
				txlog.setIsconfirm(0);
				txlog.setConfirmmsg("");
				txlog.setCallbackurl(callbackurl);
				txconsumedao.insert(txlog);
				//如果是发放元宝或者世界服发送邮件的 立刻请求PHP确认
				if(isAddGold(items)||isworldmail){
					new Timer().schedule(new TimerTask() {
						@Override
						public void run() {
							TxConsumeManager.getInstance().putConfirmQueue(txlog);
						}
					}, 2000); //延时2秒执行,保证先完成再确认
				}
			}
		}
		try{ //所有情况都记录日志
			//记录日志
			TxConsumesLog log = new TxConsumesLog();
			log.setState(1); //立即返回
			log.setDesc(desc); 
			log.setUsername(username); log.setUserid(userid); log.setRoleid(roleid);
			log.setRet(resmap.get("ret")); log.setMsg(resmap.get("msg"));
			log.setTotalconsume(Integer.parseInt(totalconsume));
			LogService.getInstance().execute(log);
		}catch (Exception e) {
			log.error(e, e);
		}
		//立即返回参数
		return JSONserializable.toString(resmap);
	}

	public void doResAddItem(List<String> params){ //处理腾讯道具发放结果
		if(params.size()>0){
			String oid= params.get(0);
			String ret = params.get(1);
			String msg = params.get(2);
			if(ret.equals("0")){
				TxConsumeLog txlog = null;
				try {
					txlog = txconsumedao.selectById(oid);
				} catch (SQLException e) {
					log.error(e, e);
				}
				if(txlog==null){ log.error("订单号["+oid+"]在数据库中没找到"); }
				else{
					txlog.setState(2);
					TxConsumeManager.getInstance().putConfirmQueue(txlog);
				}
			}else{
				log.error("异常 腾讯发放道具 游戏服发放失败["+ret+"|"+msg+"]");
			}
		}else{
			log.error("异常 腾讯发放道具 返回无订单号");
		}
	}
	
	
	/**
	 * 检查订单号是否重复
	 * @return
	 */
	public boolean isOrderDuplicate(String oid){
		boolean result = true;
		TxConsumeLog consume = null;
		try {
			consume = txconsumedao.selectById(oid);
			if(consume==null) result = false;
		} catch (SQLException e) {
			log.error("数据库读取订单号["+oid+"]异常"+e);
		}
		return result;
	}
	
	/**
	 * 检查指定服务器是否有所传账号
	 * @param username
	 * @param serverid
	 * @return
	 */
	public boolean isUsernameExist(String username, String serverid){
		boolean result = false;
		GameUser user = userDao.selectGameUser(username, Integer.parseInt(serverid));
		if(user!=null && user.getUsername().equals(username) && user.getServer()==Integer.parseInt(serverid))
			result = true;
		return result;
	}
	
	/**
	 * 根据指定服务器获得所传账号的userid
	 * @param username
	 * @param serverid
	 * @return
	 */
	public String getUsernameByServerid(String username, String serverid){
		String userid = "";
		GameUser user = userDao.selectGameUser(username, Integer.parseInt(serverid));
		if(user!=null) userid = String.valueOf(user.getUserid());
		return userid;
	}
	
	/**
	 * 道具字符串是否解析正确
	 * @param items
	 * @return
	 */
	public boolean isItemsValid(String items){
		boolean isValid = true;
		List<HashMap<String, String>> listmap = (List<HashMap<String, String>>) JSONserializable.toList(items, HashMap.class);
		for(HashMap<String, String> map: listmap){
			int itemid = ParseUtil.getMapInt(map, "itemmodelid", -1);   //道具模型id
			int num = ParseUtil.getMapInt(map, "num", -1);				//道具数量 //TODO 是否设上限
			int bind = ParseUtil.getMapInt(map, "bind", -1);			//是否绑定
			int losttime = ParseUtil.getMapInt(map, "losttime", -1);	//过期时间
			if(itemid<0 && itemid!=-2){ isValid=false; break; } 		//道具ID非正且不是元宝
			if(num<=0){ isValid=false; break; }					    	//道具数量非正
			if(itemid!=-2){ 											//对非元宝道具检查绑定和过期属性
				if(bind<0){ isValid=false; break; }				    	//绑定属性异常(1-绑定 0-不绑定)
				if(losttime<0){ isValid=false; break; }					//过期属性异常(0-不过期 1-过期秒数) //TODO 是否设置上限
			}
		}
		return isValid;
	}
	
	/**
	 * 本次发放是否元宝发放
	 * @param items
	 * @return
	 */
	public boolean isAddGold(String items){
		boolean result = false;
		if(items.contains("{\"itemmodelid\":\"-2\"")){ result = true; } 
		return result;
	}
	
	/**
	 * 从字符串中得到要充值的元宝数
	 * @param items
	 * @return
	 */
	public int getRechargeGoldNum(String items){
		int num = 0;
		List<HashMap<String, String>> listmap = (List<HashMap<String, String>>) JSONserializable.toList(items, HashMap.class);
		for(HashMap<String, String> map: listmap){
			num = ParseUtil.getMapInt(map, "num");
		}
		return num;
	}
	
	/**
	 * 腾讯币换元宝
	 * @param username
	 * @param serverId
	 * @param items
	 * @param oid
	 * @param totalconsume
	 * @return
	 */
	public int txrechargegold(String username, String serverId, String items, String oid, int totalconsume, String userid){
		int addresult = 0;
		Gold gold = dao.select(userid, Integer.parseInt(serverId));
		//
		long now = System.currentTimeMillis();
		int rechargeNum = getRechargeGoldNum(items);
		try{
			if (gold == null) {
				gold = new Gold();
				gold.setUserId(userid);
				gold.setServerId(Integer.parseInt(serverId));
				gold.setGold(rechargeNum);
				gold.setTotalGold(rechargeNum > 0 ? rechargeNum : 0);
				gold.setCostGold(0);
				gold.setBuyitemresume(0);
				gold.setFaildrollBackadd(0);
				gold.setGettempybadd(0);
				gold.setHuokuanAdd(0);
				gold.setJiaoyiresume(0);
				gold.setJiaoyiybadd(0);
				gold.setShangjiaresume(0);
				gold.setTwgmadd(0);
				gold.setYbxiajiaadd(0);
	//			gold.setIsinner(0);
				dao.insert(gold);
			} else {
				Gold savegold = new Gold();
				savegold.setGold(rechargeNum);
				savegold.setTotalGold(rechargeNum > 0 ? rechargeNum : 0);
				savegold.setUserId(userid);
				savegold.setServerId(Integer.parseInt(serverId));
	//			gold.setGold(rechargeNum);
	//			gold.setTotalGold(rechargeNum>0?rechargeNum:0);
				dao.update(savegold);
			}
			//记录
			GoldRechargeLog rechargeLog = new GoldRechargeLog();
			rechargeLog.setGold(rechargeNum);
			rechargeLog.setOid(oid);
			rechargeLog.setServerid(serverId);
			rechargeLog.setTime(now);
			rechargeLog.setType(0); //腾讯充值 类型 ? 如果要记录的话
			rechargeLog.setUid(username);
			rechargeLog.setUserid(Long.valueOf(userid));
			rechargeLog.setRmb(String.valueOf(totalconsume));
			rechargeLog.setRechargeContent(items);
			rechargelog.insert(rechargeLog);
			//添加gamelog库日志
			RechargelogLog logLog = new RechargelogLog(rechargeLog);
			LogService.getInstance().execute(logLog);
		}catch (Exception e) {
			log.error(e, e);
			addresult = -1;
		}
		try{
			if(addresult==0){
				//通知
				List<Player> result = PlayerManager.getInstance().getPlayersByUser(userid);
				if (result!=null && result.size() > 1) {
					log.error("userId=" + userid + ",serverId" + serverId + "出现了同一个服同一个账号登录两个角色的情况,忽略充值请求");
					for (Player player : result) {
						BackendManager.getInstance().kickonlineplayer(String.valueOf(player.getId()));
					}
				}else if (result!=null && result.size() > 0) {   // 通知到Server
					for (Player player : result) {
						RechargeMessage msg = new RechargeMessage();
						msg.setPlayerId(player.getId());
						msg.setRechargeParam(rechargeNum);
						msg.setOid(oid);
						MessageUtil.send_to_game(player, msg);
						if (player.getRechargeHistorys() != null) {
							RechargeHistory rechargeHistory = new RechargeHistory();
							rechargeHistory.setGold(rechargeNum);
							rechargeHistory.setTime(now);
							player.getRechargeHistorys().add(rechargeHistory);
						}
					}
				}
			}
		}catch (Exception e) {
			log.error(e, e);
		}
		return addresult;
	}
	
	//发送邮件 1-成功 0-失败
	public int sendmail(String rolename, String items){
		List<HashMap<String, String>> listmap = (List<HashMap<String, String>>) JSONserializable.toList(items, HashMap.class);
		List<Item> mailitems = new ArrayList<Item>();
		for(HashMap<String, String> map: listmap){
			int itemid = ParseUtil.getMapInt(map, "itemmodelid");
			int num = ParseUtil.getMapInt(map, "num");
			int bind = ParseUtil.getMapInt(map, "bind");
			int losttime = ParseUtil.getMapInt(map, "losttime"); //losttime = 0无过期时间  >0过期秒数
			List<Item> createitems = Item.createItems(itemid, num, bind==0?false:true, losttime==0?0:System.currentTimeMillis()+losttime*1000);
			mailitems.addAll(createitems);
		}
		long recieveid = MailWorldManager.getInstance().sendSystemMail(rolename, "", "", (byte)1, 0, mailitems); //TODO 标题 内容
		return (recieveid>0||recieveid==-2) ? 1:0;
	}
}
