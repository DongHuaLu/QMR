package com.game.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.game.manager.ManagerPool;
import com.game.message.Message;
import com.game.player.structs.Player;
import com.game.player.structs.User;
import com.game.prompt.message.NonagePromptMessage;
import com.game.prompt.message.PersonalNoticeWorldMessage;
import com.game.prompt.structs.Notifys;
import com.game.server.WorldServer;

/**
 * @author heyang E-mail: szy_heyang@163.com
 *
 * @version 1.0.0
 *
 * @since 2011-5-29
 *
 * 消息工具类
 */
public class MessageUtil {

	private static Logger log = Logger.getLogger(MessageUtil.class);

	public static ConcurrentHashMap<Long, Integer> RECORD_PLAYER = new ConcurrentHashMap<Long, Integer>();
	/**
	 * 通知玩家消息
	 *
	 * @param players 玩家
	 * @param message 消息
	 */
	public static void tell_player_message(Player player, Message message) {
		if(RECORD_PLAYER.containsKey(player.getId())){
			log.error("Message Send Player(" + player.getId() + "):" + message);
		}
		if (player.getGateId() != 0) {
			message.getRoleId().add(player.getId());
			send_to_gate(player.getGateId(), player.getId(), message);
		}else{
			log.error("player " + player.getId() + " gate " + player.getGateId() + "!");
		}
	}

	/**
	 * 通知玩家消息
	 *
	 * @param players 玩家
	 * @param message 消息
	 */
	public static void tell_player_message(List<Player> players, Message message) {
		for (Player player : players) {
			message.getRoleId().add(player.getId());
		}
		send_to_gate(message);
	}
	
	/**
	 * 通知玩家消息
	 *
	 * @param user 账号
	 * @param message 消息
	 */
	public static void tell_user_message(User user, Message message) {
		List<Player> players = ManagerPool.playerManager.getPlayersByUser(user.getUserId());
		if(players==null){
			log.error("User " + user.getUserId() + " not contain players");
			return;
		}
		for (int i = 0; i < players.size(); i++) {
			tell_player_message(players.get(i), message);
		}
	}

	/**
	 * 全服通知
	 *
	 * @param message 信息
	 */
	public static void tell_world_message(Message message) {
		send_to_gate(message);
	}

	/**
	 * 个人通知
	 *
	 * @param me 通知玩家
	 * @param type 通知类型
	 * @param message 通知消息
	 * @param values 参数值
	 */
	public static void notify_player(Player me, Notifys type, String message, String... values) {
		PersonalNoticeWorldMessage msg = new PersonalNoticeWorldMessage();
		msg.setType(type.getValue());
		msg.setContent(message);
		if (values != null) {
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < values.length; i++) {
				list.add(values[i]);
			}
			msg.setValues(list);
		}
		tell_player_message(me, msg);
	}
	/**
	 * 小范围广播通知
	 * @param players
	 * @param type
	 * @param message
	 * @param values
	 */
	public static void notify_player(List<Long> players,Notifys type,String message,String... values){
		PersonalNoticeWorldMessage msg = new PersonalNoticeWorldMessage();
		msg.setType(type.getValue());
		msg.setContent(message);
		if (values != null) {
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < values.length; i++) {
				list.add(values[i]);
			}
			msg.setValues(list);
		}
		msg.setRoleId(players);
		send_to_gate(msg);
	}
	
	
	/**
	 * 提示给整个国家
	 * @param players
	 * @param type
	 * @param message
	 * @param values
	 */
	public static void notify_All_player(int serverid,Notifys type,String message,String... values){
		//TODO  提示给指定服务器的人
	}
	
	
	
	/**
	 * 提示给整个世界
	 * @param players
	 * @param type
	 * @param message
	 * @param values
	 */
	public static void notify_All_player(Notifys type,String message,String... values){
		PersonalNoticeWorldMessage msg = new PersonalNoticeWorldMessage();
		msg.setType(type.getValue());
		msg.setContent(message);
		if (values != null) {
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < values.length; i++) {
				list.add(values[i]);
			}
			msg.setValues(list);
		}
		tell_world_message(msg);
	}
	
	
	/**
	 * 账号通知
	 *
	 * @param user 通知账号
	 * @param type 通知类型
	 * @param message 通知消息
	 * @param values 参数值
	 */
	public static void notify_user(User user, Notifys type, String message, String... values) {
		PersonalNoticeWorldMessage msg = new PersonalNoticeWorldMessage();
		msg.setType(type.getValue());
		msg.setContent(message);
		if (values != null) {
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < values.length; i++) {
				list.add(values[i]);
			}
			msg.setValues(list);
		}
		tell_user_message(user, msg);
	}
	
	/**
	 * 个人反沉迷通知
	 *
	 * @param user 通知账号
	 * @param type 通知类型
	 * @param message 通知消息
	 * @param values 参数值
	 */
	public static void nonage_user(User user, String message, String... values) {
		NonagePromptMessage msg = new NonagePromptMessage();
		msg.setContent(message);
		if (values != null) {
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < values.length; i++) {
				list.add(values[i]);
			}
			msg.setValues(list);
		}
		tell_user_message(user, msg);
	}

	/**
	 * 发送消息到游戏服务器
	 *
	 * @param message
	 */
	public static void send_to_game(Player player, Message message) {
		Vector<IoSession> sessions = WorldServer.getGameSessions().get(player.getServer());
		if (sessions != null && sessions.size()>0) {
			//IoSession session = sessions.get((int)(player.getId()%sessions.size()));
			IoSession session = sessions.get(0);
			if (log.isDebugEnabled()) {
				log.debug("->[" + castListToString(message.getRoleId()) + "-" + player.getServer() + "-" + message.getClass().getSimpleName() + "]" + message.toString());
			}
			message.setSendId(-1);
			message.getRoleId().add(player.getId());
			//session.write(message);
			write(session, message);
		} else {
			log.error("与游戏服务器" + player.getServer() + "通讯session不存在！");
		}
	}
	
	
	public static void send_to_game(int server,Message message){
		Vector<IoSession> sessions = WorldServer.getGameSessions().get(server);
		if (sessions != null && sessions.size()>0) {
			IoSession session = sessions.get(0);
			message.setSendId(-1);
			//session.write(message);
			write(session, message);
		}
	}
	
	
	/**
	 * 发送消息到游戏服务器
	 *
	 * @param message
	 */
	public static void send_to_game(User user, Message message) {
		List<Player> players = ManagerPool.playerManager.getPlayersByUser(user.getUserId());
		if(players==null){
			log.error("User " + user.getUserId() + " not contain players");
			return;
		}
		message.setSendId(-1);
		for (int i = 0; i < players.size(); i++) {
			send_to_game(players.get(i), message);
		}
	}

	/**
	 * 发送消息到游戏服务器
	 *
	 * @param message
	 */
	public static void send_to_game(Message message) {
		Iterator<Vector<IoSession>> sessions = WorldServer.getGameSessions().values().iterator();
		if (log.isDebugEnabled()) {
			log.debug("->[" + castListToString(message.getRoleId()) + "-AllGame-" + message.getClass().getSimpleName() + "]" + message.toString());
		}
		message.setSendId(-1);
		while (sessions.hasNext()) {
			Vector<IoSession> ioSessionList = (Vector<IoSession>) sessions.next();
			if(ioSessionList!=null && ioSessionList.size()>0){
				//IoSession ioSession = ioSessionList.get(RandomUtils.random(ioSessionList.size()));
				IoSession ioSession = ioSessionList.get(0);
				//ioSession.write(message);
				write(ioSession, message);
			}
		}
	}

	/**
	 * 发送消息到网关服务器
	 *
	 * @param server
	 * @param message
	 */
	public static void send_to_gate(int gateId, long playerId, Message message) {
		message.setSendId(playerId);
		Vector<IoSession> sessions = WorldServer.getGateSessions().get(gateId);
		if (sessions != null && sessions.size()>0) {
			int i = 0;//(int)(playerId % sessions.size());
			IoSession session = sessions.get(i);
			if (log.isDebugEnabled()) {
				log.debug(session + "(" + i + "/" + sessions.size() + ")" + "->[" + castListToString(message.getRoleId()) + "-" + gateId + "-" + message.getClass().getSimpleName() + "]" + message.toString());
			}
			//session.write(message);
			write(session, message);
		} else {
			log.error("与网关服务器" + gateId + "通讯session不存在！");
		}
	}
	
	/**
	 * 发送消息到网关服务器
	 *
	 * @param server
	 * @param message
	 */
	public static void send_to_gate(int gateId, Message message) {
		Vector<IoSession> sessions = WorldServer.getGateSessions().get(gateId);
		if (sessions != null && sessions.size()>0) {
			IoSession session = sessions.get(0);
			if (log.isDebugEnabled()) {
				log.debug(session + "(" + 1 + "/" + sessions.size() + ")" + "->[" + castListToString(message.getRoleId()) + "-" + gateId + "-" + message.getClass().getSimpleName() + "]" + message.toString());
			}
//			session.write(message);
			write(session, message);
		} else {
			log.error("与网关服务器" + gateId + "通讯session不存在！");
		}
	}

	/**
	 * 发送消息到所有网关服务器
	 *
	 * @param server
	 * @param message
	 */
	public static void send_to_gate(Message message) {
		Iterator<Vector<IoSession>> sessions = WorldServer.getGateSessions().values().iterator();
		while (sessions.hasNext()) {
			Vector<IoSession> ioSessionList = (Vector<IoSession>) sessions.next();
			if(ioSessionList!=null && ioSessionList.size()>0){
				int i = 0;//RandomUtils.random(ioSessionList.size());
				IoSession ioSession = ioSessionList.get(i);
				if (log.isDebugEnabled()) {
					log.debug(ioSession + "(" + i + "/" + ioSessionList.size() + ")" + "->[" + castListToString(message.getRoleId()) + "-AllGate-" + message.getClass().getSimpleName() + "]" + message.toString());
				}
				//ioSession.write(message);
				write(ioSession, message);
			}
		}
	}

//	/**
//	 * 给组队成员广播消息
//	 *
//	 * @param pid
//	 * @param message
//	 */
//	public static void teambroadcast(long pid, Message message) {
//		Player player = ManagerPool.playerManager.getPlayer(pid);
//		if (player != null) {
//			TeamInfo teaminfo = ManagerPool.teamManager.getTeam(player.getTeamid());
//			if (teaminfo != null) {
//				for (int i = 0; i < teaminfo.getMemberinfo().size(); i++) {
//					TeamMemberInfo info = teaminfo.getMemberinfo().get(i);
//					if (info != null) {
//						Player member = ManagerPool.playerManager.getPlayer(info.getMemberid());
//						if (member != null) {
//							MessageUtil.tell_player_message(member, message);
//						}
//					}
//				}
//			}
//		}
//	}

//	/**
//	 * 给公会成员广播消息
//	 *
//	 * @param roleId 发送消息玩家Id
//	 * @param message 消息
//	 * @return
//	 */
//	public static void guildBroadcast(long roleId, Message message) {
//		Player player = PlayerManager.getInstance().getPlayer(roleId);
//		if (player != null) {
//			Guild guild = GuildWorldManager.getInstance().getGuild(player.getGuildid());
//			if (guild != null) {
//				guild.sendAllMemberMessage(message);
//			}
//		}
//	}

	/**
	 * 写入发送到游戏服务器缓存
	 * @param session
	 * @param message
	 */
	public static void write(IoSession session, Message message){
		IoBuffer buf = IoBuffer.allocate(100);
		buf.setAutoExpand(true);
		buf.setAutoShrink(true);
		buf.putInt(0);
		buf.putInt(message.getId());
		buf.putLong(message.getSendId());
		buf.putInt(message.getRoleId().size());
		for (int i = 0; i < message.getRoleId().size(); i++) {
			buf.putLong(message.getRoleId().get(i));
		}
		message.write(buf);
		buf.flip();
		buf.putInt(buf.limit() - Integer.SIZE/Byte.SIZE);
		buf.rewind();
		
		IoBuffer sendbuf = null;
		synchronized (session) {
			if(session.containsAttribute("SEND_BUF")){
				sendbuf = (IoBuffer)session.getAttribute("SEND_BUF");
			}else{
				sendbuf = IoBuffer.allocate(1024);
				sendbuf.setAutoExpand(true);
				sendbuf.setAutoShrink(true);
				session.setAttribute("SEND_BUF", sendbuf);
			}
			sendbuf.put(buf);
		}
	}
	
	@SuppressWarnings({"rawtypes"})
	private static String castListToString(List list) {
		StringBuffer buf = new StringBuffer();
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Object object = (Object) iter.next();
			buf.append(",").append(object.toString());
		}
		if (buf.length() > 0) {
			buf.deleteCharAt(0);
		}
		return "{" + buf.toString() + "}";
	}
}
