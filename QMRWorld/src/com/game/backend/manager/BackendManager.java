package com.game.backend.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.game.activities.script.IBackendWorldScript;
import com.game.backend.message.ReqChangePlayerCurrencyMessage;
import com.game.backend.message.ReqJinYanMessage;
import com.game.backend.struct.ServerRequest;
import com.game.backpack.structs.Item;
import com.game.data.bean.Q_itemBean;
import com.game.data.manager.DataManager;
import com.game.db.bean.GameMaster;
import com.game.db.bean.GameUser;
import com.game.db.bean.Gold;
import com.game.db.dao.GoldDao;
import com.game.db.dao.GoldRechargeLogDAO;
import com.game.db.dao.UserDao;
import com.game.gm.manager.BGManagerGmCommand;
import com.game.gm.manager.GMCommandManager;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.mail.manager.MailWorldManager;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerWorldInfo;
import com.game.prompt.structs.Notifys;
import com.game.recharge.RechargeEntry;
import com.game.recharge.RechargeManager;
import com.game.script.manager.ScriptManager;
import com.game.script.structs.ScriptEnum;
import com.game.server.WorldServer;
import com.game.utils.MessageUtil;
import com.game.utils.ScriptsUtils;

public class BackendManager {
	private Logger log = Logger.getLogger(BackendManager.class);
	private static Object obj = new Object();
	//后台管理类实例
	private static BackendManager manager;
	private GoldDao dao=new GoldDao();
	private UserDao userDao=new UserDao();
	private GoldRechargeLogDAO rechargelog=new GoldRechargeLogDAO();
	
	private BackendManager() { }
	public static BackendManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new BackendManager();
			}
		}
		return manager;
	}
	
	public String processHttpRequest(ServerRequest sr, String requeststr){
		String action = sr.getActionid();
		if(StringUtils.isBlank(action)) return "-1001";  //-1001 =  没有这个动作
		
		if("1".equals(action)){  //1 = 查看在线角色  根据roleid
			List<Map<String, String>> playerlist = new ArrayList<Map<String, String>>(); 
			playerlist = this.inspectPlayer(sr.getRoleids());
			return JSONserializable.toString(playerlist);
		} else if("3".equals(action)){   //3 查看在线人数
			String serverid = sr.getServerid();
			int count = serveronlinecount(serverid);
			return ""+count;
		} else if("4".equals(action)){  // 踢用户下线
			int result = 0;
			String roleid = sr.getRoleid();
			result = this.kickonlineplayer(roleid);
			return ""+result;
		} else if("5".equals(action)){  //禁言
			int result = 0;
			String roleid = sr.getRoleid(); String time = sr.getTime();
			result = this.forbidtalk(roleid, time);
			return ""+result;
		} else if("6".equals(action)){  //发即时公告
			int result=1;
			MessageUtil.notify_All_player(Notifys.SROLL, sr.getContent()); //TODO 向所有服务器发公告。 因为暂时没有发某个服务器发公告的方法。
			return ""+result;
		} else if("7".equals(action)){  //根据角色名查找角色
			Map<String, String> playerinfo = this.getPlayerByRolename(sr);
			return JSONserializable.toString(playerinfo);
		} else if("8".equals(action)){  //内部元宝充值
			String roleid = sr.getRoleid(), num = sr.getNum(), sid=sr.getServerid(), oid=sr.getOid();
			int result = -1;
			try {
				result = this.innergoldrecharge(roleid, num, sid, oid);
			} catch (NumberFormatException e) {
				log.error(e, e);
			} catch (SQLException e) {
				log.error(e, e);
			}
			return ""+result;
			
		} else if("9".equals(action)){ //内部修改铜币或者绑定元宝
			String roleid = sr.getRoleid(), num = sr.getNum(), type = sr.getType();
			return ""+this.innermoneybind(roleid, num, type);
		} else if("10".equals(action)){  //等级分布
			HashMap<String, Integer> levelmap = new HashMap<String, Integer>();
			levelmap = this.getLevelMap(sr);
			return JSONserializable.toString(levelmap);
		} else if("11".equals(action)){  //关服倒计时 单位 秒 
			int secend = Integer.parseInt(sr.getNum());
			int result = this.setCloseServerTime(secend);
			return ""+result;
		} else if("12".equals(action)){  //全服邮件
			int result = 1;
			
			return ""+result;
		} else if("13".equals(action)){ //单人邮件
			return ""+sendmailrole(sr.getServerid(), sr.getRolename(), sr.getTitle(), sr.getContent(), sr.getSitem(), sr.getItems());
		} else if("14".equals(action)){ //查询剩余元宝			
			return ""+getPlayerGoldNum(sr.getRoleid(), sr.getServerid());
		} else if("15".equals(action)){ //查询GM列表
			List<GameMaster> gmlist = GMCommandManager.getInstance().getGameMasterList();
			return JSONserializable.toString(gmlist);
		} else if("16".equals(action)){ //修改GM
			GameMaster gm = new GameMaster();
			gm.setUserid(sr.getUserid()); gm.setGmlevel(sr.getGmlevel()); gm.setIsDeleted(sr.getIsDeleted());
			return ""+GMCommandManager.getInstance().updateGameMaster(gm);
		} else if("17".equals(action)){ //添加GM
			int result = 0;
			String rolename = sr.getRolename();
			String serverid = sr.getServerid();
			Player p = PlayerManager.getInstance().getOnlinePlayerByName(rolename);
			if(p==null){
				result = 3; //3for 没有玩家
			}else{  //1成功 0失败
				result = GMCommandManager.getInstance().addGameMaster(Long.valueOf(p.getUserId()), "", rolename, Integer.parseInt(serverid), sr.getGmlevel());
			}
			return ""+result;
		} else if("18".equals(action)){
			return BGManagerGmCommand.getInstance().command(sr);
		}else{
			IBackendWorldScript script = (IBackendWorldScript) ScriptManager.getInstance().getScript(ScriptEnum.BACKEND);
			return script.doServerRequest(sr, requeststr);
		}
	}
	 
	//10服务器等级分布
	public HashMap<String, Integer> getLevelMap(ServerRequest sr){
		HashMap<String, Integer> levelmap = new HashMap<String, Integer>();
		int serverid = Integer.parseInt(sr.getServerid());
		ConcurrentHashMap<Long, Player> players = PlayerManager.getPlayers();
		for(Player p: players.values()){
			if(p.getServer()==serverid){  
				int level = p.getLevel();
				if(levelmap.containsKey(level)){ levelmap.put(""+level, levelmap.get(level)); }
				else{ levelmap.put(""+level, 1); } 
			}
		}
		return levelmap;
	}
	
	//3查看服务器在线人数
	public int serveronlinecount(String serverid){
		int count = 0;
		int queryserverid = Integer.parseInt(serverid);
		ConcurrentHashMap<Long, Player> players = PlayerManager.getPlayers();
		for(Player p: players.values()){
			if(p.getServer()==queryserverid){
				count++;
			}
		}
		return count;
	}
	
	//7根据角色名查找角色
	public Map<String, String> getPlayerByRolename(ServerRequest sr){
		Map<String, String> playerinfo = new HashMap<String, String>();
		String rolename = sr.getRolename();
		String zone = "["+sr.getServerid()+ResManager.getInstance().getString("区")+"]";
		String name = zone+rolename;
		Player p = PlayerManager.getInstance().getOnlinePlayerByName(name);
		if(p!=null){
			playerinfo.put("id", ""+p.getId());
			playerinfo.put("userid",""+p.getUserId());
			playerinfo.put("name", p.getName());
			playerinfo.put("level", ""+p.getLevel());
			String adult = p.getIsAdult()==1? "已成年":"未成年";
			playerinfo.put("isAdult", adult);
			playerinfo.put("server", ""+p.getServer());
			playerinfo.put("location", ""+p.getMap()+"["+p.getLine()+"线]");
			playerinfo.put("ipString", p.getIpString());
			String sex = p.getSex()==(byte)1?"男":"女";
			playerinfo.put("sex", sex);
		}
		return playerinfo;
	}
	
	//1查看在线角色
	public List<Map<String, String>> inspectPlayer(List<String> roleids){
		List<Map<String, String>> playerlist = new ArrayList<Map<String, String>>(); 
		Map<String, String> playerinfo = new HashMap<String, String>();
		for(String s: roleids){
			Player p = PlayerManager.getInstance().getPlayer(Long.parseLong(s));
			if(p!=null){ 
				playerinfo = new HashMap<String, String>();
				playerinfo.put("id", ""+p.getId());
				playerinfo.put("userid",""+p.getUserId());
				playerinfo.put("name", p.getName());
				playerinfo.put("level", ""+p.getLevel());
				String adult = p.getIsAdult()==1? "已成年":"未成年";
				playerinfo.put("isAdult", adult);
				playerinfo.put("server", ""+p.getServer());
				playerinfo.put("location", ""+p.getMap()+"["+p.getLine()+"线]");
				playerinfo.put("ipString", p.getIpString());
				String sex = p.getSex()==(byte)1?"男":"女";
				playerinfo.put("sex", sex);
				playerlist.add(playerinfo);
			}
		}
		return playerlist;
	}
	
	//4 踢用户下线
	public int kickonlineplayer(String roleid){
		Player p = PlayerManager.getInstance().getPlayer(Long.valueOf(roleid));
		if(p!=null){
			PlayerManager.getInstance().kickPlayer(p.getName());
			return 1;
		}else {
			return 0;
		}
	}
	
	//禁言
	public int forbidtalk(String roleid, String time){
		Player p = PlayerManager.getInstance().getPlayer(Long.valueOf(roleid));
		if(p!=null){
			long times = Long.parseLong(time)*60*1000L;
			ReqJinYanMessage msg = new ReqJinYanMessage();
			msg.setCreateServerId(p.getServer());
			msg.setPlayerId(p.getId());
			msg.setStartTime(System.currentTimeMillis());
			msg.setTimes(times);
			msg.setUserId(p.getUserId());
			MessageUtil.send_to_game(p, msg);
			return 1;
		}else {
			return 0;
		}
	}
	
	//8内部元宝充值
	//{svrtmpplayername=laodao, idh=0, msgid=1, login_account=laodaotest, zoneid=1, errormsg=6, svrtmpaccount=laodaoeasda, password=123456, agent=37wan, idl=0}
	//0：成功   1：用户不存在   2：注册错误    3：用户名不符合规定     4：密码不符合规定    6：加密验证失效,   100：帐号信息不存在  -2内部错误
	public int innergoldrecharge(String roleid, String num, String sid, String oid) throws NumberFormatException, SQLException{
		Player p = PlayerManager.getInstance().getPlayer(Long.valueOf(roleid));
		if(p!=null){
			GameUser u = userDao.selectGameUser(Long.valueOf(p.getUserId()), Integer.parseInt(sid));
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("UID", u.getUsername());  //UID
			map.put("OID", oid);   //OID
			map.put("SID", sid);   //SID
			map.put("GOLD", num);  //GOLD
			map.put("IP", "-1");   //IP
			map.put("TYPE", "9");  //TYPE
			map.put("REMARK", ""); //REMARK
			map.put("TIME", "");   //TIME 
			map.put("SIGN", "");   //SIGN 
			String msg=JSONserializable.toString(map);
			RechargeEntry entry = new RechargeEntry(map, msg);
			int result = RechargeManager.getInstance().reacharge(entry);
			return result;
		}
		return 1;
	}

	//9内部修改铜币或绑定元宝
	public int innermoneybind(String roleid, String num, String type){
		Player p = PlayerManager.getInstance().getPlayer(Long.valueOf(roleid));
		if(p!=null){
			ReqChangePlayerCurrencyMessage msg = new ReqChangePlayerCurrencyMessage();
			msg.setType(Byte.parseByte(type));
			msg.setPersonId(Long.valueOf(roleid));
			msg.setNum(Integer.parseInt(num));
			MessageUtil.send_to_game(p, msg);
			return 1;
		}else{
			return 0;
		}
	}
	
	//11设计关服倒计时
	//secend = 0 取消倒计时关服
	public int setCloseServerTime(int secend){
		//设置关服时间
		WorldServer.getInstance().setCloseTime( secend==0? 0:System.currentTimeMillis() + secend * 1000);
		//关服设置成功 同时停止充值接口
		if (secend > 0){ 
			ScriptsUtils.call(ScriptEnum.BACKEND, "changerefusecharge", 1);
		} else if(secend==0){ //取消倒计时关服 打开充值接口
			ScriptsUtils.call(ScriptEnum.BACKEND, "changerefusecharge", 0);
		}
		return 1;
	}
	
	private static Pattern pattern = Pattern.compile("(-?\\d+)\\_(\\d+)");
	private static Pattern pattern1 = Pattern.compile("(-?\\d+)\\_(\\d+)\\_([01])\\_(\\d+)"); //是否绑定 过期时间(小时)
	private static Pattern pattern2 = Pattern.compile("(-?\\d+)\\_(\\d+)\\_([01])\\_(\\d+)\\_(\\d{1,2})\\_(.+)"); // id+num+bind+losttime+grade+append(取出来再检测)
	//return 1成功 2角色不存在   -2 不支持 else 物品不存在  -40字符串解析出错
	public int sendmailrole(String serverid, String rolename, String title, String content, String sitem, String items){
		String zone = "["+serverid+ResManager.getInstance().getString("区")+"]";
		String name = zone+rolename;
		PlayerWorldInfo playerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(name);
 		if(playerWorldInfo==null) return 2; //角色不存在
		List<Item> itemlist = new ArrayList<Item>();
		int itemid=0, itemnum=0, grade=0, groupcount=0;
		boolean isbind = false; long losttime = 0L;
		String append="";
		if(!StringUtils.isBlank(sitem) && "checked".equals(sitem) && !StringUtils.isBlank(items)){
			String[] itemss = items.split("&");
			for(String s: itemss){
				if(StringUtils.isBlank(s)) continue;
				Matcher m2 = pattern2.matcher(s);
				Matcher m1 = pattern1.matcher(s);
				Matcher m = pattern.matcher(s);
				if(m.matches()){
					groupcount = m.groupCount();
					itemid = Integer.parseInt(m.group(1));
					itemnum = Integer.parseInt(m.group(2));
				}else if(m1.matches()){
					groupcount = m1.groupCount();
					itemid = Integer.parseInt(m1.group(1));
					itemnum = Integer.parseInt(m1.group(2));
					isbind = Integer.parseInt(m1.group(3))>0? true:false;
					int losthour = Integer.parseInt(m1.group(4));
					long now = System.currentTimeMillis();
					losttime = losthour==0?0: now+losthour*3600000L;
//					losttime = System.currentTimeMillis();
				}else if(m2.matches()){
					groupcount = m2.groupCount();
					itemid = Integer.parseInt(m2.group(1));
					itemnum = Integer.parseInt(m2.group(2));
					isbind = Integer.parseInt(m2.group(3))>0? true:false;
					int losthour = Integer.parseInt(m2.group(4));
					losttime = losthour==0?0: System.currentTimeMillis()+losthour*3600000L;
					grade = Integer.parseInt(m2.group(5));
					append = m2.group(6);
				}else{
					return -40;
				}
				Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(itemid);
				if(q_itemBean==null){ return itemid; } //物品不存在
				if(itemid>0){
					if(groupcount==6){
						itemlist.addAll(Item.createItems( itemid, itemnum, isbind, losttime, grade, append));
					}else if(groupcount==4){
						itemlist.addAll(Item.createItems( itemid, itemnum, isbind, losttime));
					}else if(groupcount==2){
						itemlist.addAll(Item.createItems( itemid, itemnum, false , 0));
					}
				}else if(itemid==-1){
					itemlist.add(Item.createMoney(itemnum));
				}else if(itemid==-2){
					return -2;  //暂不支持元宝
				}else if(itemid==-3){
					itemlist.add(Item.createZhenQi(itemnum));
				}else if(itemid==-4){
					itemlist.add(Item.createExp(itemnum));
				}else if(itemid==-5){
					itemlist.add(Item.createBindGold(itemnum));
				}else if(itemid==-6){ //战魂
					itemlist.add(Item.createFightSpirit(itemnum));
				}else if(itemid==-7){ //军功
					itemlist.add(Item.createRank(itemnum));
				}
			}
		}
		long sendresult = MailWorldManager.getInstance().sendSystemMail(name, title, content, (byte)1, 0, itemlist);
		log.error("后台发送邮件记录: sendresult "+sendresult + "\tserverid "+serverid+"\trolename "+rolename+"\ttitle "+title+"\tcontent "+content+"\tsitem "+sitem+"\titems "+items);
		return sendresult>0?-50: sendresult==-2?-50: (int)sendresult;
	}
	
	//14当前剩余元宝
	public int getPlayerGoldNum(String roleid, String serverid){
		Player p = PlayerManager.getInstance().getPlayer(Long.valueOf(roleid));
		if(p!=null){
			try {
				Gold gold = dao.select(p.getUserId(), Integer.parseInt(serverid));
				if(gold!=null) return gold.getGold();
			} catch (NumberFormatException e) {
				log.error(e, e);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		return -1;
	}
	
	public static void main(String[] args){
//		String s = "&1001_100&";
//		String[] b = s.split("&");
//		Matcher m = pattern.matcher(s);
//		if(m.matches()){
//			System.out.println(m.groupCount());
//			System.out.println(m.group(1));
//			System.out.println(m.group(2));
//		}
//		System.out.println(System.currentTimeMillis());
//		System.out.println(Integer.parseInt("35000")*60*1000);
//		System.out.println(Long.parseLong("5000000000")*60*1000);
		MessageUtil.notify_All_player(Notifys.SROLL, "'{\"t\":\"前往{@}\", \"ps\":[{\"name\":\"官网\",\"url\":\"http://q.37wan.com\",\"type\":4}]}';"); 
		//'{"t":"前往{@}", "ps":[{"name":"官网","url":"http://q.37wan.com","type":4}]}';
		//{"t":"前往{@}","ps":[{"name":"官网","url":"http://q.37wan.com","type":4}]}
//		long times = Long.parseLong(time)*60*1000L;
		//
	}
}

