package com.game.team.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.game.config.Config;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.structs.Guild;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.setupmenu.manager.SetupMenuManager;
import com.game.team.bean.MapPlayerInfo;
import com.game.team.bean.MapTeamInfo;
import com.game.team.bean.SearchPlayerInfo;
import com.game.team.bean.TeamInfo;
import com.game.team.bean.TeamMemberInfo;
import com.game.team.message.ReqGenericSearchToWorldMessage;
import com.game.team.message.ReqIntoTeamToWorldMessage;
import com.game.team.message.ReqMapSearchMemberNameWorldMessage;
import com.game.team.message.ReqMapSearchPlayerInfoWorldMessage;
import com.game.team.message.ReqMapSearchTeamInfoWorldMessage;
import com.game.team.message.ReqTeamMessageWorldMessage;
import com.game.team.message.ResApplyClientMessage;
import com.game.team.message.ResAppointClientMessage;
import com.game.team.message.ResGenericSearchToClientMessage;
import com.game.team.message.ResInviteClientMessage;
import com.game.team.message.ResMapSearchMemberNameClientMessage;
import com.game.team.message.ResMapSearchPlayerInfoClientMessage;
import com.game.team.message.ResMapSearchTeamInfoClientMessage;
import com.game.team.message.ResTeamClientRefreshMessage;
import com.game.team.message.ResTeamGameMessage;
import com.game.team.message.ResTeamchangeToGameMessage;
import com.game.team.message.ResToleaveWorldMessage;
import com.game.utils.MessageUtil;

public class TeamManager {
	private static Object obj = new Object();
	Logger log = Logger.getLogger(TeamManager.class);
	/**
	 * 队伍列表 (队伍ID做索引)
	 */
	private static  HashMap<Long, TeamInfo> teamlist = new HashMap<Long, TeamInfo>();
	/**
	 * 存放临时组队列表， (队伍ID做索引)
	 */
	private static  HashMap<Long, HashMap<Long, Long>> tmpteamlist = new HashMap<Long, HashMap<Long, Long>>();
	/**
	 * 没有队伍的时候邀请别人入队，在这里验证，换队长也在这里验证， (玩家ID做索引)
	 */
	
	private static  HashMap<Long, HashMap<Long, Long>> tmpcaptainlist = new HashMap<Long, HashMap<Long, Long>>();
	//队伍最大人数
	public final int largestmember = 4;
	// 管理类实例
	private static TeamManager manager;
	//请求超时
	private int TIMEOUT = 40;

	private TeamManager(){}
	public static TeamManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new TeamManager();
			}
		}
		return manager;
	}

	
	public static HashMap<Long, TeamInfo> getTeamlist() {
		return teamlist;
	}

	public static void setTeamlist(HashMap<Long, TeamInfo> teamlist) {
		TeamManager.teamlist = teamlist;
	}

	public static HashMap<Long, HashMap<Long, Long>> getTmpteamlist() {
		return tmpteamlist;
	}

	public static void setTmpteamlist(HashMap<Long, HashMap<Long, Long>> tmpteamlist) {
		TeamManager.tmpteamlist = tmpteamlist;
	}
	
	
	
	
	/**刷新队伍(保存)
	 * 
	 * @param xteaminfo
	 * @param tid
	 */
	public void refreshTeam(TeamInfo xteaminfo,long tid) {
		if(tid > 0) {
			teamlist.put(tid, xteaminfo);
			updateteaminfo(tid);	//更新队员信息
			teamchangetomember(tid);	//队伍改变，通知队伍成员
		}
	}
	
	
	/**删除指定队伍
	 * 
	 * @param tid
	 */
	public void delTeam(long tid) {
		if(teamlist.get(tid) != null) {
			teamlist.remove(tid);
		}
		if(tmpteamlist.get(tid) != null) {
			tmpteamlist.remove(tid);
		}
	}
	
	
	/**传入队伍ID，得到队伍详细列表
	 * 
	 * @param tid
	 * @return
	 */
	public TeamInfo getTeam(long tid) {
		if(tid > 0) {
			TeamInfo teaminfo = teamlist.get(tid);
			if(teaminfo != null) {
				return teaminfo;
			}
		}
		return null;
	}

	
	/**
	 * 更新队友信息,  把玩家信息写入队员结构内
	 * @param player
	 * @param memberinfo
	 * @return
	 */
	public TeamMemberInfo fillinmemberinfo(Player player,TeamMemberInfo memberinfo){
		memberinfo.setMemberid(player.getId());
		memberinfo.setMemberlevel(player.getLevel());
		memberinfo.setMemberline(player.getLine());
		memberinfo.setMembermapid(player.getMap());
		memberinfo.setMembername(player.getName());
		memberinfo.setMx(player.getPosition().getX());
		memberinfo.setMy(player.getPosition().getY());
		memberinfo.setMembermaponlyid(player.getMaponlyid());
		memberinfo.setAppearanceInfo(ManagerPool.playerManager.getPlayerAppearanceInfo(player));
		return memberinfo;
	}
	
	
	
	/**更新队员详细信息
	 * 
	 * @param tid
	 */
	public void updateteaminfo(long tid) {
		TeamInfo teaminfo = getTeam(tid);
		if(teaminfo != null) {
			List<TeamMemberInfo> tab = teaminfo.getMemberinfo();
			for (TeamMemberInfo memberinfo :tab) {
				Player player = ManagerPool.playerManager.getPlayer(memberinfo.getMemberid());
				if(player != null) {
					fillinmemberinfo(player,memberinfo);
				}
			}
		}
	}
		
	
		
	
	
	
	/**创建队伍
	 */
	public void stCreateateamWorld(long pid ) {
		Player player = ManagerPool.playerManager.getPlayer(pid);
		if(player.getTeamid() == 0) {
			long tid = Config.getId();		//生成一个队伍ID
			TeamInfo teaminfo = new TeamInfo();
			teaminfo.setTeamid(tid);
			List<TeamMemberInfo> tab = teaminfo.getMemberinfo();
			tab.add(fillinmemberinfo(player, new TeamMemberInfo()));
			teaminfo.setAutoIntoteamapply(player.getAutoIntoteamapply());//组队设置，默认值
			player.setTeamid(tid);
			refreshTeam(teaminfo,tid);//刷新存入队伍
		}
	}
	
	/**添加队伍 成员
	 * 
	 * @param tid
	 * @param pid
	 */
	public boolean addmembers(long tid,long pid) {
		Player player = ManagerPool.playerManager.getPlayer(pid);
		TeamInfo teaminfo = getTeam(tid);
		if(teaminfo != null) {
			List<TeamMemberInfo> tab = teaminfo.getMemberinfo();
			if(tab != null) {
				if(tab.size() < largestmember) {
					Iterator<TeamMemberInfo> it = tab.iterator();
					while (it.hasNext()) {
						if(it.next().getMemberid() == pid) {
							return false;
						}
					} 

					tab.add(fillinmemberinfo(player, new TeamMemberInfo()));		//把玩家加入成员列表
					player.setTeamid(tid);
					refreshTeam(teaminfo,tid);	//刷新存入队伍
					removetmpteamlist( tid , pid);
					notify_team_all_player(tid,String.format(ResManager.getInstance().getString("欢迎『%s』加入我们的队伍！"), player.getName()));
					return true;
				}
			}
		}
		return false;
	}
	
	/**发给所有队员的文字消息
	 * 
	 * @param tid
	 * @param type
	 * @param message
	 * @param values
	 */
	public void notify_team_all_player(long tid, String message) {
		TeamInfo teaminfo = getTeam(tid);
		if(teaminfo != null ) {
			ArrayList<Long> list=new ArrayList<Long>();
			List<TeamMemberInfo> tab = teaminfo.getMemberinfo();
			if (tab.size() > 0) {
				Iterator<TeamMemberInfo> it = tab.iterator();
				while (it.hasNext()) {
					long pid = it.next().getMemberid();
					list.add(pid);
					Player player = ManagerPool.playerManager.getPlayer(pid);
					if (player != null) {
						if (!message.contains("{@}")) {//正常消息在聊天栏和屏幕下方同时提示
							MessageUtil.notify_player(player, Notifys.NORMAL, message);
						}
					}
				} 
				MessageUtil.notify_player(list, Notifys.CHAT_TEAM, message);
			}
		}
	}
	
	/**加入临时组队列表，验证用
	 * 
	 * @param tid
	 * @param pid
	 */
	public void addtmpteamlist(long tid ,long pid) {
		HashMap<Long, Long>  tmptab = tmpteamlist.get(tid);
		long ms = System.currentTimeMillis()/1000;
		if(tmptab == null) {
			HashMap<Long, Long> newtmptab = new HashMap<Long, Long>();
			newtmptab.put(pid, ms);
			tmpteamlist.put(tid, newtmptab);
		}
		else {
			List<Long> list = new ArrayList<Long>();
			Set<Entry<Long, Long>> set = tmptab.entrySet();
			Iterator<Entry<Long, Long>> iterator = set.iterator();
			
			//-----------------删除超时ID-----------------
			
			while (iterator.hasNext()) {
				Entry<Long, Long> entry = (Entry<Long, Long>) iterator.next();
				if (ms - entry.getValue() > TIMEOUT) {	//检测超时
					list.add(entry.getKey());	//记录超时ID
				}
			}

			for(long id : list) {
				tmptab.remove(id);
			}

			tmptab.put(pid, ms);
			tmpteamlist.put(tid, tmptab);
		}
	}
	
	
	/**删除临时组队ID（组队完成后调用）
	 * 
	 * @param tid
	 * @param pid
	 */
	public void removetmpteamlist(long tid ,long pid) {
		if(tmpteamlist.containsKey(tid)) {
			HashMap<Long, Long> tmptab = tmpteamlist.get(tid);
			if(tmptab != null) {
				if(tmptab.containsKey(pid)) {
					tmptab.remove(pid);
				}
			}
		}
	}
	
	/**检测是否在临时组队列表里，验证用
	 * 
	 * @param tid
	 * @param pid
	 * @return
	 */
	
	public boolean  cktmpteamlist(long tid ,long pid) {
		if(tmpteamlist.containsKey(tid)) {
			HashMap<Long, Long> tmptab = tmpteamlist.get(tid);
			if(tmptab != null) {
				if(tmptab.containsKey(pid)) {
					long ms = System.currentTimeMillis()/1000 - tmptab.get(pid);
					if (ms < TIMEOUT) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	//----------------临时队长表---------------------
	
	/**得到组队临时队长表
	 * 
	 * @param pid   操作人
	 * @param cid
	 * @return
	 */
	public long  gettmpcaptain(long pid ,long cid){
		if(tmpcaptainlist.containsKey(pid)) {
			HashMap<Long, Long> tmpcidlist = tmpcaptainlist.get(pid);
			if (tmpcidlist.containsKey(cid)) {
				long ms = System.currentTimeMillis()/1000 - tmpcidlist.get(cid);
				if (ms < TIMEOUT) {
					return cid;
				}
			}
		}
		return 0;
	}
	
	
	/**加入临时队长表
	 * 
	 * @param pid   操作人
	 * @param cid  邀请人
	 */
	public void addtmpcaptain(long pid,long cid ){
		long ms = System.currentTimeMillis()/1000;
		HashMap<Long, Long> tmptab = tmpcaptainlist.get(pid);
		if (tmptab == null) {
			HashMap<Long, Long>  newtmptab = new HashMap<Long, Long>();
			newtmptab.put(cid, ms);
			tmpcaptainlist.put(pid, newtmptab);
		}else {
			List<Long> list = new ArrayList<Long>();
			Set<Entry<Long, Long>> set = tmptab.entrySet();
			Iterator<Entry<Long, Long>> iterator = set.iterator();
			
			while (iterator.hasNext()) {
				Entry<Long, Long> entry = (Entry<Long, Long>) iterator.next();
				if (ms - entry.getValue() > TIMEOUT) {
					list.add(entry.getKey());//记录超时ID
				}
			}

			for(long id : list) {
				tmptab.remove(id);
			}
			
			tmptab.put(cid, ms);
			tmpcaptainlist.put(pid, tmptab);
		}
	}
	/**检测是否在临时队长表里，验证用
	 * 
	 * @param pid
	 * @param cid
	 * @return
	 */
	
	public boolean  ckttmpcaptain(long pid ,long cid) {
		if(tmpcaptainlist.containsKey(pid)) {
			HashMap<Long, Long> tmptab = tmpcaptainlist.get(pid);
			if(tmptab != null) {
				if(tmptab.containsKey(cid)) {
					long ms =System.currentTimeMillis()/1000 - tmptab.get(cid) ;
					if (ms  < TIMEOUT) {
						return true;
					}
				}
			}
		}
		return false;
	}
	/**删除临时队长表 
	 * 
	 * @param pid
	 * @param cid
	 */
	public void removetmpcaptain(long pid ){
		if(tmpcaptainlist.containsKey(pid)) {
			tmpcaptainlist.remove(pid);
		}
	}
	
	
	//------------------------------------------------------------
	
	/** 玩家申请入队(需要检测 国籍)
	 * 
	 * @param pid
	 * @param tid
	 */
	public void stReqApply(long pid ,long tid) {
		Player player = ManagerPool.playerManager.getPlayer(pid);
		if(player == null)return ;
		
		long bteamid = player.getTeamid();
		if(bteamid > 0) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您已经加入其他队伍！"));
			return;
		}
		
		TeamInfo teaminfo = getTeam(tid);
		if(teaminfo == null) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，该队伍已解散。"));
			return;
		}
		if(teaminfo.getMemberinfo().size() < largestmember) {
			if(cktmpteamlist(tid,pid)) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方正忙，请稍后再试。"));
				return;
			}
			ResApplyClientMessage msg = new ResApplyClientMessage();
			TeamMemberInfo memberinfo = fillinmemberinfo(player, new TeamMemberInfo());
			msg.setNewmemberinfo(memberinfo);
			msg.setTeamid(tid);
			addtmpteamlist( tid , pid); //加入临时组队验证表
			long captainid = teaminfo.getMemberinfo().get(0).getMemberid();	//队长ID
			Player cplayer = ManagerPool.playerManager.getPlayer(captainid);
			if(cplayer != null) {
				if(cplayer.getCountry() != player.getCountry() )return;
				if(cplayer.getAutoIntoteamapply() == 1) {		//队长开了自动邀请
					applydealwith( tid , pid, (byte) 0);
				}else
				{	
					MessageUtil.tell_player_message(cplayer,msg);//转发给队长选择（前端弹出）
					MessageUtil.notify_player(player,Notifys.NORMAL,ResManager.getInstance().getString("已发送入队申请，请等待队长『{1}』回应。"),cplayer.getName());
				}
			}
		}else {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方队伍已经满员了。"));
		}
	}
	
	
	/**申请入队 最后处理结果
	 * 
	 * @param tid
	 * @param pid
	 * @param select 0同意，1拒绝
	 */

	public void applydealwith(long tid ,long pid,byte select) {
		TeamInfo teaminfo = getTeam(tid);
		Player player = ManagerPool.playerManager.getPlayer(pid);
		if(teaminfo == null) {
			if (select == 0) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，该队伍已经不存在。"));
			}
			removetmpteamlist( tid , pid);
			return;//没有得到队伍
		}
		long captainid = teaminfo.getMemberinfo().get(0).getMemberid();	//队长ID
		Player cplayer = ManagerPool.playerManager.getPlayer(captainid);

		if(select == 0) {//同意申请
			if(player==null){
				MessageUtil.notify_player(cplayer,Notifys.ERROR,ResManager.getInstance().getString("申请入队的玩家已经离线。"));
				removetmpteamlist( tid , pid);
				return;
				}
			if (player.getName() == null) {
				MessageUtil.notify_player(cplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方不在线，或者登录中，请稍后再试。"));
				return;
			}
			long bteamid = player.getTeamid();
			if(bteamid > 0) {
				MessageUtil.notify_player(cplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方已经加入其他队伍。"));
				removetmpteamlist( tid , pid);
				return;
				}
			if(cplayer.getCountry() != player.getCountry() ) {
				MessageUtil.notify_player(cplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，只有相同国家的玩家才能组队。"));
				return;
			}
				
			if(teaminfo.getMemberinfo().size() < largestmember) {
				if(cktmpteamlist(tid,pid)) {
					addmembers(tid ,pid);	//加入
					ManagerPool.friendManager.addRelation(cplayer.getId(),(byte)3,player.getId(),true);
					ManagerPool.friendManager.addRelation(player.getId(),(byte)3,cplayer.getId(),true);
				}else {
					MessageUtil.notify_player(cplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的操作已经过期了。"));
				}
			}else{
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您要加入的队伍人数已经满员。"));
				MessageUtil.notify_player(cplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的队伍人数已经满员,{1}无法加入。"),player.getName());
			}	
		}else {//拒绝
			if (select == 2) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("对不起，队长『{1}』没有响应您的入队申请。"),cplayer.getName());
			}else {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("对不起，队长『{1}』拒绝了您的入队申请。"),cplayer.getName());
			}
			removetmpteamlist( tid , pid);
		}
	}
	
	
	
	
	//--------------------------邀请加入----------------------------------
	
	/**邀请加入
	 * 
	 * @param teamid
	 * @param playerid   被邀请人
	 * @param captainid   邀请人
	 */
	public void stReqInviteWorld(long teamid, long playerid,long captainid) {
		Player player = ManagerPool.playerManager.getPlayer(playerid);
		Player cplayer = ManagerPool.playerManager.getPlayer(captainid);
		if(player == null || cplayer ==null) return;
		if (player.getName() == null) {
			MessageUtil.notify_player(cplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方不在线，或者登录中，请稍后再试。"));
			return;
		}
		
		if(cplayer.getCountry() != player.getCountry() ) {
			MessageUtil.notify_player(cplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，只有相同国家的玩家才能组队。"));
			return;
		}
		
		if (SetupMenuManager.getInstance().isBanTeam(player)) {
			MessageUtil.notify_player(cplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方设置了自动拒绝他人邀请入队。"));
			return;
		}
		
		if(player.getTeamid() > 0) {
			if (player.getTeamid() == cplayer.getTeamid()) {
				MessageUtil.notify_player(cplayer,Notifys.ERROR,ResManager.getInstance().getString("{1}和您已经在同个队伍里。"),player.getName());
			}else {
				removetmpteamlist( teamid , playerid);
				MessageUtil.notify_player(cplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，该玩家已有队伍。"));
			}
			
			return;
		}
		
		if (cplayer.getTeamid() > 0) {
			teamid = cplayer.getTeamid();
		}
		
		if(teamid > 0 ) {
			TeamInfo teaminfo = getTeam(teamid);
			if(teaminfo == null) {
				return;//没有得到队伍消息，出错
			}
			if(teaminfo.getMemberinfo().get(0).getMemberid() != captainid) {
				MessageUtil.notify_player(cplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，只有队长才能发出组队邀请。"));
				return;
			}
			
			if(cktmpteamlist(teamid,playerid)) {//30秒内禁止重复邀请
				MessageUtil.notify_player(cplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方正忙，请稍后再试。"));
				return;
			}
			if(teaminfo.getMemberinfo().size() < largestmember) {
				TeamMemberInfo memberinfo = fillinmemberinfo(cplayer, new TeamMemberInfo());
				addtmpteamlist( teamid , playerid);//临时组队列表，验证用
				if (player.getAutoteaminvited() == 1) {	//检测玩家是否设置自动邀请
					Invitedealwith( teamid,  playerid,captainid,(byte) 0);
				}else {
					ResInviteClientMessage msg = new ResInviteClientMessage();
					msg.setCaptaininfo(memberinfo);
					msg.setTeamid(teamid);
					MessageUtil.tell_player_message(player,msg);//发送消息给玩家做选择
					MessageUtil.notify_player(cplayer,Notifys.NORMAL,ResManager.getInstance().getString("已发送组队邀请，请等待回应。"));
				}
			}else {
				MessageUtil.notify_player(cplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的队伍人数已经满员了。"));
				}
		
		}else {//没有队伍， 还是发给玩家组队通知
			if (gettmpcaptain(playerid,captainid) > 0) {
				MessageUtil.notify_player(cplayer,Notifys.NORMAL,ResManager.getInstance().getString("很抱歉，对方正忙，请稍后再试。"));
				return ;
			}
			addtmpcaptain(playerid,captainid);//加入临时队长表，玩家同意后，根据这里的信息创建队伍
			if (player.getAutoteaminvited() == 1) {	//检测玩家是否设置自动邀请
				Invitedealwith( teamid,  playerid,captainid,(byte) 0);
			}else {
				ResInviteClientMessage msg = new ResInviteClientMessage();
				TeamMemberInfo memberinfo = fillinmemberinfo(cplayer, new TeamMemberInfo());
				msg.setCaptaininfo(memberinfo);
				msg.setTeamid(0);
				MessageUtil.tell_player_message(player,msg);//发送消息给玩家做选择
				MessageUtil.notify_player(cplayer,Notifys.NORMAL,ResManager.getInstance().getString("已发送组队邀请，请等待回应。"));
			}
		}
	}
	
	
	
	
	/**
	 * 邀请组队，玩家选择后，世界服务器处理
	 * @param tid		没有组队发 0
	 * @param pid		被邀请人
	 * @param cid       邀请人
	 * @param select
	 */

	public void Invitedealwith(long tid, long pid,long cid ,byte select) {
		Player player = ManagerPool.playerManager.getPlayer(pid);
		if(player == null) return;

		if(select == 0) {
			if(player.getTeamid() > 0) {
				if (select == 0) {
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您已经加入一个队伍。"));
				}
				removetmpteamlist( tid , pid);
				return; //玩家已经有队伍
			}

			if(tid > 0) {
				TeamInfo teaminfo = getTeam(tid);
				if(teaminfo == null) {
					return;//没有得到队伍
				}
				if(teaminfo.getMemberinfo().size() < largestmember) {
					if(cktmpteamlist(tid,pid)) {
						if (teaminfo.getMemberinfo().get(0).getMemberid() != cid) return;
							Player cplayer = ManagerPool.playerManager.getPlayer(cid);
							if (cplayer !=  null ) {
								if(cplayer.getCountry() != player.getCountry() )return;
								addmembers(tid ,pid);//加入
								ManagerPool.friendManager.addRelation(cplayer.getId(),(byte)3,player.getId(),true);
								ManagerPool.friendManager.addRelation(player.getId(),(byte)3,cplayer.getId(),true);
							}
					}else {
						removetmpteamlist( tid , pid);
						MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的操作已经过期了。"));
					}
				}else{
					removetmpteamlist( tid , pid);
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方队伍人数已经满员了。"));
				}	
				
			}else {		//没队伍的情况下
				long tmpcid = gettmpcaptain(pid,cid);
				if(tmpcid != 0) {
					Player cplayer = ManagerPool.playerManager.getPlayer(tmpcid);
					if(cplayer !=null) {
						if(cplayer.getCountry() != player.getCountry() )return;
						if(cplayer.getTeamid() > 0) {	//队长已经组队（直接加进队伍）
							TeamInfo cteaminfo = getTeam(cplayer.getTeamid());
							if (cteaminfo.getMemberinfo().get(0).getMemberid() == tmpcid) {
								addmembers(cplayer.getTeamid(),pid);
							}
							
						}else {				//队长没有组队（创建新队伍）
							stCreateateamWorld(tmpcid);		//创建新队伍
							addmembers(cplayer.getTeamid(),pid);
						}
						ManagerPool.friendManager.addRelation(cplayer.getId(),(byte)3,player.getId(),true);
						ManagerPool.friendManager.addRelation(player.getId(),(byte)3,cplayer.getId(),true);
					}
					removetmpcaptain(pid);
				}else {
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的操作已经过期了。。"));
				}
			}
		}else {
			if (select == 2) {
				Player cplayer = ManagerPool.playerManager.getPlayer(cid);
				if (cplayer != null) {
					MessageUtil.notify_player(cplayer,Notifys.ERROR,ResManager.getInstance().getString("『{1}』没有响应您的组队邀请。"),player.getName());
				}
			}else {
				long tmpcid = gettmpcaptain(pid,cid);
				if(tmpcid != 0 || cktmpteamlist(tid,pid)) {
					Player cplayer = ManagerPool.playerManager.getPlayer(cid);
					if(cplayer !=null) {
						MessageUtil.notify_player(cplayer,Notifys.ERROR,ResManager.getInstance().getString("很遗憾，『{1}』拒绝了您的组队邀请。"),player.getName());
					}
				}
			}
			removetmpcaptain(pid);
			removetmpteamlist( tid , pid);
		}
	}
	
	
	

	//--------------玩家离队-------------------
	
	
	/**玩家离线退出队伍
	 * 
	 */
	public void stOfflineLeavetheTeam(Player player, byte type){
		long tid=player.getTeamid();
		long pid = player.getId();
		TeamInfo teaminfo = getTeam(tid);
		if(teaminfo != null) {
			long cid = teaminfo.getMemberinfo().get(0).getMemberid();
			Player cplayer = ManagerPool.playerManager.getPlayer(cid);
			
			if (type == 0 ) {
				if (cid == pid) {
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("您不能开除自己，若需退出，请点退出队伍"));
					return;
				}
			}
			
			int pos = -1;
			for (int i = 0; i <teaminfo.getMemberinfo().size(); i++) {
				if(teaminfo.getMemberinfo().get(i).getMemberid() == pid) {
					pos = i;
					break;
				}
			}

			if (pos >= 0) {	//位置从0开始
				ResToleaveWorldMessage msg = new ResToleaveWorldMessage();
				msg.setPlayerid(pid);
				msg.setTeamid(tid);
				if (player != null) {
					player.setTeamid(0);
					MessageUtil.send_to_game(player,msg);// 发离队消息到game
					if (type == 0) {
						MessageUtil.notify_player(player,Notifys.NORMAL,ResManager.getInstance().getString("您被队长『{1}』移出队伍。"),cplayer.getName());
					}else {
						 if(teaminfo.getMemberinfo().size() > 1){
							 MessageUtil.notify_player(player,Notifys.NORMAL,ResManager.getInstance().getString("您退出了队伍。"));
						 }
					}
				}
				teaminfo.getMemberinfo().remove(pos);
				if (type == 0) {
					notify_team_all_player(tid,String.format(ResManager.getInstance().getString("『%s』被队长移出队伍"), player.getName()));
				}else{
					notify_team_all_player(tid,String.format(ResManager.getInstance().getString("『%s』离开了队伍！"), player.getName()));
				}
				if (pos == 0 && teaminfo.getMemberinfo().size() > 2) {
					notify_team_all_player(tid,String.format(ResManager.getInstance().getString("『%s』成为新的队长。"),teaminfo.getMemberinfo().get(0).getMembername()));
				}
				
				//剩下一个人，自动解散,而且是自己退出，则解散
				if (teaminfo.getMemberinfo().size() == 1 && type != 0) {	
					stLeavetheteam(teaminfo.getMemberinfo().get(0).getMemberid(),(byte) 2);
				}
				
			}

			if(teaminfo.getMemberinfo().size() == 0) {	
				delTeam(tid);	//删除队伍（解散）
				MessageUtil.notify_player(player,Notifys.NORMAL,ResManager.getInstance().getString("队伍被解散了。"));
			}
			teamchangetomember(tid);
			
		}
		removetmpcaptain(pid);
	}

	
	/**玩家离队处理
	 * 
	 * @param tid
	 * @param pid
	 * @param type  0 开除，1 自己离开，2下线
	 */
	public void stLeavetheteam(long pid ,byte type) {
		Player player = ManagerPool.playerManager.getPlayer(pid);
		if (player != null ) {
			stOfflineLeavetheTeam(player, type);
		}
		
	}
	
	//-------------------任命新队长--------------------------
	
	/**任命新队长，通知玩家选择
	 * 
	 * @param tid
	 * @param pid
	 */
	public void stReqAppoint(long tid , long pid) {
		Player player = ManagerPool.playerManager.getPlayer(pid);
		if(player == null) {
			return;
		}
		ResAppointClientMessage msg = new ResAppointClientMessage();
		TeamInfo teaminfo = getTeam(tid);
		if(teaminfo !=null) {
			List<TeamMemberInfo> info = teaminfo.getMemberinfo();

			if(info != null && info.size() > 1) {
				if (info.get(0).getMemberid() == pid) {
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("您已经是队长，无需再任命。"));
					return;
				}
				msg.setCaptaininfo(info.get(0));
				msg.setTeamid(tid);
				addtmpcaptain(info.get(0).getMemberid() ,pid);
				MessageUtil.tell_player_message(player,msg);//发送消息给玩家做选择
				long cid = teaminfo.getMemberinfo().get(0).getMemberid();
				Player cplayer = ManagerPool.playerManager.getPlayer(cid);
				MessageUtil.notify_player(cplayer,Notifys.NORMAL,ResManager.getInstance().getString("已发送任命请求，等待回应。"));
			}
		}
	}
		
	/**跨地图更新队伍消息
	 * 
	 */
	public void changeMemberMoveMap(long tid){
		updateteaminfo(tid);	//更新队员信息
		teamchangetomember(tid);	//队伍改变，通知队伍成员
	}
	
	
	
	/**任命新队长，玩家选择后，处理结果
	 * 
	 * @param tid
	 * @param pid
	 * @param type
	 */
	public void Appointdealwith(long tid,long pid ,byte type) {
		Player player = ManagerPool.playerManager.getPlayer(pid);
		if(player == null) {
			return;
		}
		
		TeamInfo teaminfo = getTeam(tid);
		if(teaminfo ==null)return ;
		if(type == 0) {		//同意
			List<TeamMemberInfo> info = teaminfo.getMemberinfo();
			if(info != null && info.size() > 1) {
				long cid = gettmpcaptain(info.get(0).getMemberid(),pid);
				if(cid == pid) {
					removetmpcaptain(info.get(0).getMemberid());
					TeamMemberInfo cmemberInfo =  info.get(0);
					TeamMemberInfo tmemberInfo = null ;
					int idx = 0;
					int size  = info.size();
					for (idx = 0;idx < size;idx++) {
						if(info.get(idx).getMemberid() == pid) {
							tmemberInfo = info.get(idx);
							break;
						}
					}
					
					if(tmemberInfo != null) {	//互换位置
						info.remove(idx);
						info.remove(0);
						info.add(0,tmemberInfo);
						info.add(idx,cmemberInfo);
						refreshTeam(teaminfo,tid);	//刷新存入队伍
						notify_team_all_player(tid,String.format(ResManager.getInstance().getString("恭喜『%s』成为新的队长。"),tmemberInfo.getMembername()));
					}	
				}else {
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的操作已经过期了"));
				}
			}
		}else {		//拒绝
			long cid = teaminfo.getMemberinfo().get(0).getMemberid();
			Player cplayer = ManagerPool.playerManager.getPlayer(cid);
			if(cplayer != null) {
				MessageUtil.notify_player(cplayer,Notifys.ERROR,ResManager.getInstance().getString("对不起，『{1}』拒绝了您的队长任命请求。"),player.getName());
			}
		}
	}
	
	
	
	//-------------------------更新队伍信息---------------------------------
	
		
	/**队伍改变时，发送组队信息
	 * 
	 * @param tid
	 */
	public void teamchangetomember(long tid  ) {
		TeamInfo teminfoa = getTeam(tid);
		ResTeamGameMessage msg = new ResTeamGameMessage();
		msg.setTeamid(tid);
		msg.setType((byte) 0);//在这里设置0，队伍群发到GAME
		if(teminfoa != null) {
			List<TeamMemberInfo> memberinfos = teminfoa.getMemberinfo();
			msg.setMemberinfo(memberinfos);
			MessageUtil.send_to_game(msg);	//群发 所有GAME服务器更新组队缓存
			ResTeamClientRefreshMessage msgclient = new ResTeamClientRefreshMessage();
			msgclient.setMemberinfo(memberinfos);
			msgclient.setTeamid(tid);
			for (TeamMemberInfo teamMemberInfo : memberinfos) {
				Player player =  ManagerPool.playerManager.getPlayer(teamMemberInfo.getMemberid());
				if(player !=null) {
					MessageUtil.tell_player_message(player,msgclient);//发队伍消息到client（更新队伍信息）
					ResTeamchangeToGameMessage smsg = new ResTeamchangeToGameMessage();
					smsg.setPlayerid(player.getId());
					smsg.setTeamid(tid);
					smsg.setMemberinfo(memberinfos);
					MessageUtil.send_to_game(player,smsg);//发送给个人，队伍改变（血量加减）
				}
			}
		}else {
			MessageUtil.send_to_game(msg);	//群发 所有GAME服务器更新组队缓存
		}
	}
	
	
	

	/**发送队伍列表到指定玩家
	 * 
	 * @param tid
	 * @param pid
	 * @param type  ，1 更新到GAME ，2更新到前端
	 */
	public void teamchangetoplayer(long tid,long pid,byte type) {
		TeamInfo teminfoa = getTeam(tid);
		if(teminfoa != null) {
			updateteaminfo(tid);	//更新队员信息
			Player player =  ManagerPool.playerManager.getPlayer(pid);
			if(player !=null) {
				List<TeamMemberInfo> memberinfos = teminfoa.getMemberinfo();
				if(type == 1) {	//更新到GAME 单个人 (暂时没用到)
					ResTeamGameMessage msg = new ResTeamGameMessage();
					msg.setMemberinfo(memberinfos);
					msg.setTeamid(tid);
					msg.setType((byte) 1);
					MessageUtil.send_to_game(player,msg);//发队伍信息到game上的某个玩家(暂时没用到)
				}
				else if (type == 2) {	//更新队伍信息到前端
					ResTeamClientRefreshMessage msgclient = new ResTeamClientRefreshMessage();
					msgclient.setMemberinfo(memberinfos);
					msgclient.setTeamid(tid);
					MessageUtil.tell_player_message(player,msgclient);//发队伍消息到client
				}
			}
		}
	}
	
	
	/**自动接受入队申请
	 * 
	 */
	public void stAutoIntoTeamApply(long pid ,byte select) {
		Player player =  ManagerPool.playerManager.getPlayer(pid);
		if(player != null) {
			player.setAutoIntoteamapply(select);
			TeamInfo teaminfo = getTeam(player.getTeamid());
			if(teaminfo != null) {
				if(teaminfo.getMemberinfo().get(0).getMemberid() ==pid) {
					teaminfo.setAutoIntoteamapply(select);
				}
			}		
		}
	}
	
	/**自动组队邀请消息
	 * 
	 */
	public void stAutoTeaminvited(long pid ,byte select) {
		Player player =  ManagerPool.playerManager.getPlayer(pid);
		if(player != null) {
			player.setAutoteaminvited(select);
		}
	}
	
	
	
	/**搜索本地图 玩家信息 （前端做排行）
	 * 
	 * @param msg
	 */
	public void stMapSearchPlayerInfo(ReqMapSearchPlayerInfoWorldMessage msg) {
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		if (player != null) {
			int mapid = player.getMap();
			@SuppressWarnings("static-access")
			ConcurrentHashMap<Long, Player>	tmpplayers = ManagerPool.playerManager.getPlayers();
			ResMapSearchPlayerInfoClientMessage pmsg = new ResMapSearchPlayerInfoClientMessage();
			List<MapPlayerInfo>	msginfo = new ArrayList<MapPlayerInfo>();
			Set<Entry<Long, Player>> entryplayers = tmpplayers.entrySet();
			
			String cont = msg.getSearchcontent();
			Iterator<Entry<Long, Player>> iterator = entryplayers.iterator();
			while (iterator.hasNext()) {
				if (msginfo.size() >= 200) {		//限制最多200条数据
					break;
				}
				Entry<Long, Player> entry = (Entry<Long, Player>) iterator.next();
				Player bplayer = entry.getValue();
				if (bplayer.getMap() == mapid && bplayer.getId() != player.getId() &&  bplayer.getCountry() == player.getCountry()) {
					if (cont == null) {
						
					}else if (cont != null && !cont.equals("")) {
						if (bplayer.getName().contains(cont) == false) {
							continue;
						}
					}
					
					MapPlayerInfo pInfo = new MapPlayerInfo();
					if (bplayer.getGuildid() > 0) {
						Guild	guild = GuildWorldManager.getInstance().getGuild(bplayer.getGuildid());
						if(guild != null) {
							pInfo.setGuildname(guild.getGuildInfo().getGuildName());
						}
					}
					pInfo.setLine((byte) bplayer.getLine());
					pInfo.setPlayerid(bplayer.getId());
					pInfo.setPlayerlv((short) bplayer.getLevel());
					pInfo.setPlayername(bplayer.getName());
					if (bplayer.getTeamid() > 0) {
						pInfo.setTeamstate((byte) 1);
					}else {
						pInfo.setTeamstate((byte) 0);
					}
					msginfo.add(pInfo);
				}
			}
			if(msg.getSearchcontent() != null && msginfo.size() == 0){
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，没有查询到指定玩家"));
			}
			pmsg.setMapplayerinfo(msginfo);
			MessageUtil.tell_player_message(player,pmsg);//发消息到client
			pmsg = null;
		}
	}
	
	
	
	/**搜索本地图  队伍信息 （前端做排行）
	 * 
	 * @param msg
	 */
	public void stMapSearchTeamInfo(ReqMapSearchTeamInfoWorldMessage msg) {
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		if (player != null) {
			ResMapSearchTeamInfoClientMessage teammsg = new ResMapSearchTeamInfoClientMessage();
			List<MapTeamInfo> mapteamlist  = new ArrayList<MapTeamInfo>();
			Set<Entry<Long, TeamInfo>> entryteam = teamlist.entrySet();
			
			String cont = msg.getSearchcontent();
			Iterator<Entry<Long, TeamInfo>> iterator = entryteam.iterator();
			while (iterator.hasNext()) {
				if (mapteamlist.size() >= 100) {		//限制100条数据
					break;
				}
				
				Entry<Long, TeamInfo> entry = (Entry<Long, TeamInfo>) iterator.next();
				TeamInfo teaminfo = entry.getValue();
				List<TeamMemberInfo> team = teaminfo.getMemberinfo();
				Player 	cplayer = ManagerPool.playerManager.getPlayer(team.get(0).getMemberid());
				if (cplayer != null && player.getMap() == cplayer.getMap() && cplayer.getCountry() == player.getCountry()) {
					if (cont == null) {
						
					}else if (cont != null && !cont.equals("")) {
						if (cplayer.getName().contains(cont) == false) {
							continue;
						}
					}
					
					
					MapTeamInfo mapteamInfo = new  MapTeamInfo();
					mapteamInfo.setCaptainid(cplayer.getId());
					mapteamInfo.setCaptainname(cplayer.getName());
					mapteamInfo.setLine((byte) cplayer.getLine());
					mapteamInfo.setTeamid(teaminfo.getTeamid());
					mapteamInfo.setTeamnum((byte) teaminfo.getMemberinfo().size());
					mapteamInfo.setCaptainlv((short) cplayer.getLevel());
					short[] tab = CalculateTeamLevel(team);
					mapteamInfo.setAveragelv(tab[0]);
					mapteamInfo.setHighestlv(tab[1]);
					mapteamlist.add(mapteamInfo);
				}
			}
			if(msg.getSearchcontent() != null && mapteamlist.size() == 0){
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，没有查询到指定的队伍"));
			}
			teammsg.setMapteaminfo(mapteamlist);
			MessageUtil.tell_player_message(player,teammsg);//发消息到client	
			teammsg = null;
		}
	}
	
	
	/**
	 * 计算队伍等级（平均等级，最高等级）
	 * 
	 * @param team
	 * @return
	 */
	public short[] CalculateTeamLevel(List<TeamMemberInfo> team) {
		short[] tab = {0,0}; //平均等级，最高等级
		int num = 0;
		List<Short> list = new ArrayList<Short>(); 
		for (TeamMemberInfo teamMemberInfo : team) {
			num = num + teamMemberInfo.getMemberlevel();
			list.add((short) teamMemberInfo.getMemberlevel());
		}
		tab[0] = (short) (num/team.size());
		Collections.sort(list);
		tab[1] = list.get(list.size()-1);
		list = null;
		return tab;
	}
	
	
	/**获取队伍全部成员名字
	 * 
	 * @param msg
	 */
	public void stReqMapSearchMemberNameWorldMessage(ReqMapSearchMemberNameWorldMessage msg){
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		if (player != null) {
			TeamInfo teaminfo = getTeam(msg.getTeamid());
			if (teaminfo != null ) {
				List<String> nameList = new ArrayList<String>();
				List<Short> lvList = new ArrayList<Short>();
				List<Byte> lineList = new ArrayList<Byte>();
				ResMapSearchMemberNameClientMessage cmsg = new ResMapSearchMemberNameClientMessage();
				List<TeamMemberInfo> memberinfo = teaminfo.getMemberinfo();
				for (int i = 0; i < memberinfo.size(); i++) {
					nameList.add(memberinfo.get(i).getMembername());
					lvList.add((short) memberinfo.get(i).getMemberlevel());
					lineList.add((byte) memberinfo.get(i).getMemberline());
				}
				cmsg.setMembernamelist(nameList);
				cmsg.setMembernamelv(lvList);
				cmsg.setMembernameline(lineList);
				MessageUtil.tell_player_message(player,cmsg);//发消息到client
			}
		}
	}
	
	
	
	
	/**来自GAME服务器的队伍提示消息(聊天框 队伍频道)
	 * 
	 * @param msg
	 */
	public void stReqTeamMessageWorldMessage(ReqTeamMessageWorldMessage msg) {
		notify_team_all_player(msg.getTeamid(),msg.getContent());
	}
	
	
	
	/**搜索玩家（通用）
	 * 
	 * @param msg  //搜索类型，0搜索世界，1，搜索本国所有人，2，搜索所有线的当前地图，3搜索本线地图,4搜索所有线本地图未组队的人,5本国未组队的人 6本国没入帮的人
	 */
	public void stReqGenericSearchToWorldMessage(ReqGenericSearchToWorldMessage msg) {
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		String cont = msg.getSearchcontent();
		byte type = msg.getType();
		if (player != null) {
			int country = player.getCountry();
			int line = player.getLine();
			int mapid = player.getMap();
			ResGenericSearchToClientMessage pmsg = new ResGenericSearchToClientMessage();
			List<SearchPlayerInfo>	msginfo = new ArrayList<SearchPlayerInfo>();
			List<Player> playerlist = new ArrayList<Player>();
			
			@SuppressWarnings("static-access")
			Set<Entry<Long, Player>> entryplayers = ManagerPool.playerManager.getPlayers().entrySet();
			Iterator<Entry<Long, Player>> iterator = entryplayers.iterator();
				
			while (iterator.hasNext()) {
				Entry<Long, Player> entry = (Entry<Long, Player>) iterator.next();
				Player bplayer = entry.getValue();
				if (bplayer.getName() != null && !bplayer.equals("")) {
					playerlist.add(bplayer);
				}
			}
			
			if (msg.getSort() == 1) {
				Collections.sort(playerlist);//进行排序
			}
			
			//输入了内容，自动转成搜索全国
			if (msg.getType() > 1 && (cont != null && !cont.equals(""))) {
				type = 1;
			}
			
			for (Player  xplayer : playerlist) {
				boolean is = false;
				if (msginfo.size() >= 200) {		//限制最多200条数据
					break;
				}
				
				if (cont == null) {
					
				}else if (cont != null && !cont.equals("")) {
					if (xplayer.getName().contains(cont) == false) {
						continue;
					}
				}
				
				switch (type) {
				case 0:
					if (xplayer.getId() != player.getId()) {
						is = true;
					}
					break;
				case 1:
					if ( xplayer.getId() != player.getId() && xplayer.getCountry() ==country) {
						is = true;
					}
					break;
				case 2:
					if (xplayer.getMap() == mapid && xplayer.getId() != player.getId() && xplayer.getCountry() ==country) {
						is = true;
					}
					break;
				case 3:
					if (xplayer.getMap() == mapid && xplayer.getId() != player.getId() && xplayer.getCountry() ==country && xplayer.getLine()== line ) {
						is = true;
					}
					break;
				case 4:
					if (xplayer.getMap() == mapid && xplayer.getId() != player.getId() && xplayer.getCountry() ==country) {
						if (xplayer.getTeamid() > 0) {
							continue;
						}
						is = true;
					}
					break;
				case 5:
					if ( xplayer.getId() != player.getId() && xplayer.getCountry() ==country) {
						if (xplayer.getTeamid() > 0) {
							continue;
						}
						is = true;
					}
					break;
					
				case 6:
					if ( xplayer.getId() != player.getId() && xplayer.getCountry() ==country && xplayer.getGuildid() == 0) {
						is = true;
					}
					break;
					
				default:
					break;
				}
				if (is) {
					SearchPlayerInfo pInfo = new SearchPlayerInfo();
					pInfo.setPlayerid(xplayer.getId());
					pInfo.setPlayerlv((short) xplayer.getLevel());
					pInfo.setPlayername(xplayer.getName());
					pInfo.setLine((byte) xplayer.getLine());
					msginfo.add(pInfo);
				}
			
				
			}
				
			if(msg.getSearchcontent() != null && msginfo.size() == 0){
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，没有查询到指定玩家"));
			}
				
			pmsg.setPaneltype(msg.getPaneltype());
			pmsg.setPlayerinfolist(msginfo);
			pmsg.setSort(msg.getSort());
			MessageUtil.tell_player_message(player,pmsg);//发消息到client
			pmsg = null;
		}
	}
	
	
	/**自动判断是申请还是邀请组队
	 * 
	 * @param msg
	 */
	public void stReqIntoTeamToWorldMessage(ReqIntoTeamToWorldMessage msg) {
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());	//发起人
		Player others = ManagerPool.playerManager.getPlayer(msg.getOthersid());
		if (others != null && player != null) {
			if ((player.getTeamid()> 0 && others.getTeamid() == 0) ||  (player.getTeamid()== 0 && others.getTeamid() == 0)) {
				stReqInviteWorld(player.getTeamid(), msg.getOthersid(), msg.getPlayerid());//邀请他人
			}else if(player.getTeamid()== 0 && others.getTeamid() > 0){
				stReqApply(msg.getPlayerid(),others.getTeamid());		//申请加入
			}else if(player.getTeamid() > 0 && others.getTeamid() > 0){
				if (player.getTeamid() == others.getTeamid()) {
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("{1}和您已经在同个队伍里。"),others.getName());
				}else {
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，该玩家已有队伍。"));
				}
			}else {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("组队未知错误。"));
			}
		}else {
			if (player != null && others == null) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("对方已不在线。"));
			}
		}
	}
}
	
	
	
		
		