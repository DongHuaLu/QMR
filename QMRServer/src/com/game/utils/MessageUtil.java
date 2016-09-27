package com.game.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.game.chat.bean.GoodsInfoRes;
import com.game.guild.message.ReqGuildStrMessageToWorldMessage;
import com.game.manager.ManagerPool;
import com.game.map.manager.MapManager;
import com.game.map.message.ResRoundMonsterMessage;
import com.game.map.structs.Area;
import com.game.map.structs.IMapObject;
import com.game.map.structs.Map;
import com.game.message.Message;
import com.game.player.structs.Player;
import com.game.prompt.message.PersonalNoticeActivityMessage;
import com.game.prompt.message.PersonalNoticeMessage;
import com.game.prompt.structs.Notifys;
import com.game.server.config.MapConfig;
import com.game.server.impl.WServer;
import com.game.zones.structs.ZoneContext;

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
//	private static HashSet<Integer> NOT_ALLOW = new HashSet<Integer>(){
//
//		private static final long serialVersionUID = 1L;
//
//		{
////			this.add(101111);
////			this.add(102102);
////			this.add(102101);
////			this.add(101115);
////			this.add(101701);
////			this.add(101110);
////			this.add(101102);
////			this.add(101101);
////			this.add(101116);
////			this.add(103305);
//		}
//	};
	public static ConcurrentHashMap<Long, Integer> RECORD_PLAYER = new ConcurrentHashMap<Long, Integer>();

	/**
	 * 通知玩家消息
	 *
	 * @param me 玩家
	 * @param message 消息
	 */
	public static void tell_player_message(Player player, Message message) {
//		if (RECORD_PLAYER.containsKey(player.getId())) {
//			if(message instanceof ResRoundMonsterDisappearMessage || message instanceof ResRoundMonsterMessage || message instanceof ResRoundObjectsMessage) log.debug("Message Send Player(" + player.getId() + "):" + message);
//		}
		if (player.getGateId() != 0) {
			Map map = ManagerPool.mapManager.getMap(player);
			message.getRoleId().add(player.getId());
			if (map == null) {
				send_to_gate(player.getGateId(), player.getId(), message);
			} else {
				send_to_gate(player.getGateId(), map.getSendId(), message);
			}
		} else {
//			log.error("player" + player.getId() + " 网关为0");
//			try{
//				throw new Exception();
//			}catch (Exception e) {
//				log.error(e, e);
//			}
		}
	}
	
	/**
	 * 通知指定玩家  消息
	 *
	 * @param me 玩家
	 * @param message 消息
	 */
	public static void tell_playerlist_message(List<Player> list, Message message) {
		for (Player player : list) {
			message.getRoleId().add(player.getId());
		}
		send_to_gate(message);
	}
	
	
	

	/**
	 * 广播玩家信息给地图玩家
	 *
	 * @param me 被广播人
	 * @param message 信息
	 */
	public static void tell_round_message(IMapObject object, Message message) {
		Map map = ManagerPool.mapManager.getMap(object.getServerId(), object.getLine(), object.getMap());
		if (map == null) {
//			try {
//
//				Exception exception = new Exception();
//				boolean tag = true;
//				StackTraceElement[] stackTrace = exception.getStackTrace();
//				for (StackTraceElement stackTraceElement : stackTrace) {
//					if (stackTraceElement.getMethodName().contains("buildDefaultValue")) {
//						tag = false;
//					}
//				}
//				if (tag) {
//					throw new Exception();
//				}
//
//			} catch (Exception e) {
//				if (me instanceof Player) {
//					log.error("player " + me.getId() + " server " + me.getServerId() + " line " + me.getLine() + " map " + me.getMap() + " not found!");
//				}
//				log.error(e, e);
//			}
			if (object instanceof Player) {
				tell_player_message((Player) object, message);
			}
			return;
		}

		List<Area> rounds = ManagerPool.mapManager.getRound(map, object.getPosition());
		for (int i = 0; i < rounds.size(); i++) {
			Area area = rounds.get(i);
			Iterator<Player> iter = area.getPlayers().iterator();
			while (iter.hasNext()) {
				Player other = (Player) iter.next();
				if (object.canSee(other)) {
					message.getRoleId().add(other.getId());
//					if (RECORD_PLAYER.containsKey(other.getId())) {
//						log.error("send to " + castListToString(message.getRoleId()) + " message:" + message);
//					}
				}
			}
		}

//		if(message instanceof ResRoundMonsterMessage && ((ResRoundMonsterMessage)message).getMonster().getMonsterModelId()) log.error("send to " + castListToString(message.getRoleId()) + " message:" + message);
		
		if (object instanceof Player) {
			if(!message.getRoleId().contains(object.getId())){
				message.getRoleId().add(object.getId());
			}
		}
		
		if (message.getRoleId().size() > 0) {
			send_to_gate(map.getSendId(), message);
		}
	}

	/**
	 * 广播玩家信息给周围玩家
	 *
	 * @param me 被广播人
	 * @param message 信息
	 */
	public static void tell_map_message(IMapObject object, Message message) {
		Map map = ManagerPool.mapManager.getMap(object.getServerId(), object.getLine(), object.getMap());
		if (map == null) {
//				try {
			//
//					Exception exception = new Exception();
//					boolean tag = true;
//					StackTraceElement[] stackTrace = exception.getStackTrace();
//					for (StackTraceElement stackTraceElement : stackTrace) {
//						if (stackTraceElement.getMethodName().contains("buildDefaultValue")) {
//							tag = false;
//						}
//					}
//					if (tag) {
//						throw new Exception();
//					}
			//
//				} catch (Exception e) {
//					if (me instanceof Player) {
//						log.error("player " + me.getId() + " server " + me.getServerId() + " line " + me.getLine() + " map " + me.getMap() + " not found!");
//					}
//					log.error(e, e);
//				}
			if (object instanceof Player) {
				tell_player_message((Player) object, message);
			}
			return;
		}


		for (Entry<Long, Player> entry : map.getPlayers().entrySet()) {
			Player player = entry.getValue();
			if (player != null && object.canSee(player)) {
				message.getRoleId().add(player.getId());
				//				if (RECORD_PLAYER.containsKey(player.getId())) {
				//					if(message instanceof ResRoundMonsterDisappearMessage || message instanceof ResRoundMonsterMessage || message instanceof ResRoundObjectsMessage) log.error("send to " + castListToString(message.getRoleId()) + " message:" + message);
				//				}
			}
		}
		//if(object instanceof Player) log.error("send to " + castListToString(message.getRoleId()) + " message:" + message);
		if (message.getRoleId().size() > 0) {
			send_to_gate(map.getSendId(), message);
		}
	}

//	/**
//	 * 通知一个坐标点为中心周围的玩家
//	 *
//	 * @param position
//	 * @param message
//	 */
//	public static void tell_round_message(Map map, Position position, Message message) {
//		if (map == null) {
//			log.error("map not found!", new Exception());
//			return;
//		}
//		int areaId = ManagerPool.mapManager.getAreaId(position);
//		Area area = map.getAreas().get(areaId);
//		int[] rounds = ManagerPool.mapManager.getRoundAreas(areaId);
//		for (int i = 0; i < rounds.length; i++) {
//			area = map.getAreas().get(rounds[i]);
//			if (area == null) {
//				continue;
//			}
//			Iterator<Player> iter = area.getPlayers().iterator();
//			while (iter.hasNext()) {
//				Player other = (Player) iter.next();
//				message.getRoleId().add(other.getId());
//			}
//		}
//
//		if (message.getRoleId().size() > 0) {
//			send_to_gate(message);
//		}
//	}
//	/**
//	 * 通知一个坐标点为中心周围的玩家
//	 *
//	 * @param position
//	 * @param message
//	 */
//	public static void tell_round_message(Map map, Area area, Message message) {
//		if (map == null) {
//			log.error("map not found!", new Exception());
//			return;
//		}
//		int[] rounds = ManagerPool.mapManager.getRoundAreas((int) area.getId());
//		for (int i = 0; i < rounds.length; i++) {
//			area = map.getAreas().get(rounds[i]);
//			if (area == null) {
//				continue;
//			}
//			Iterator<Player> iter = area.getPlayers().iterator();
//			while (iter.hasNext()) {
//				Player other = (Player) iter.next();
//				message.getRoleId().add(other.getId());
//			}
//		}
//
//		if (message.getRoleId().size() > 0) {
//			send_to_gate(message);
//		}
//	}
//	public static void tell_map_message(Player me, String cmd, Object parameter){
//	}
//	public static void tell_team_in_same_map_message(Player player, Message message){
//		Map map = ManagerPool.mapManager.getMap(player.getServerId(), player.getLine(), player.getMap());
//		if(map==null){
//			log.error("player " + player.getId() + " server " + player.getServerId() + " line " + player.getLine() + " map " + player.getMap() + " not found!");
//			return;
//		}
//		Iterator<Player> iter = map.getPlayers().values().iterator();
//		while (iter.hasNext()) {
//			Player other = (Player) iter.next();
//			if(other.getTeamid()==player.getTeamid() && other.getId()!=player.getId()){
//				message.getRoleId().add(other.getId());
//			}
//		}
//		if(message.getRoleId().size()>0) send_to_gate(player.getId(), message);
//	}
	/**
	 * 地图广播
	 *
	 * @param serverid
	 * @param lineid
	 * @param mapid
	 * @param message
	 */
	public static void tell_map_message(Map map, Message message) {
		if (map == null) {
			//log.error("Server " + serverid + " line " + lineid + " map " + mapid + " not found!");
			return;
		}
		Iterator<Player> iterator = map.getPlayers().values().iterator();
		while (iterator.hasNext()) {
			Player other = (Player) iterator.next();
			message.getRoleId().add(other.getId());
		}
		if (!message.getRoleId().isEmpty()) {
			send_to_gate(map.getSendId(), message);
		}
	}

	/**
	 * 副本地图广播
	 *
	 * @param zoneContext 副本上下文
	 * @param message
	 */
	public static void tell_zone_message(ZoneContext zoneContext, Message message) {
		if (zoneContext == null) {
			//log.error("Server " + serverid + " line " + lineid + " map " + mapid + " not found!");
			return;
		}
		if (zoneContext.getConfigs().isEmpty()) {
			return;
		}
		Iterator<MapConfig> iterator = zoneContext.getConfigs().iterator();
		while (iterator.hasNext()) {
			MapConfig mapConfig = iterator.next();
			if (mapConfig != null) {
				Map map = MapManager.getInstance().getMap(mapConfig.getServerId(), mapConfig.getLineId(), mapConfig.getMapId());
				if (map != null) {
					Iterator<Player> mapIterator = map.getPlayers().values().iterator();
					while (mapIterator.hasNext()) {
						Player other = (Player) mapIterator.next();
						message.getRoleId().add(other.getId());
					}
				}
			}
		}
		if (!message.getRoleId().isEmpty()) {
			send_to_gate(message);
		}
	}

//	public static void tell_guild_message(Player me, String cmd, Object parameter){
//		
//	}
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
		PersonalNoticeMessage msg = new PersonalNoticeMessage();
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
	 * 地图通知
	 *
	 * @param map 通知地图
	 * @param type 通知类型
	 * @param message 通知消息
	 * @param values 参数值
	 */
	public static void notify_map(Map destmap, Notifys type, String message, String... values) {
		PersonalNoticeMessage msg = new PersonalNoticeMessage();
		msg.setType(type.getValue());
		msg.setContent(message);
		if (values != null) {
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < values.length; i++) {
				list.add(values[i]);
			}
			msg.setValues(list);
		}
		for (Entry<Long, Player> entry : destmap.getPlayers().entrySet()) {
			Player player = entry.getValue();
			if (player != null) {
				msg.getRoleId().add(player.getId());
			}
		}
		if (!msg.getRoleId().isEmpty()) {
			send_to_gate(destmap.getSendId(), msg);
		}
	}

//	/**
//	 * 广播到地图
//	 * @param me 通知玩家
//	 * @param type 通知类型
//	 * @param message 通知消息
//	 * @param values 参数值
//	 */
//	public static void notify_Map_Player(int serverid,int lineid,int mapid, Notifys type,String message,String... values){
//		PersonalNoticeMessage msg = new PersonalNoticeMessage();
//		msg.setType(type.getValue());
//		msg.setContent(message);
//		if(values!=null){
//			List<String> list = new ArrayList<String>();
//			for (int i = 0; i < values.length; i++) {
//				list.add(values[i]);
//			}
//			msg.setValues(list);
//		}
//		tell_map_message(serverid, lineid, mapid, msg);
//	}
	/**
	 * 提示给整个世界
	 *
	 * @param players
	 * @param type
	 * @param message
	 * @param values
	 */
	public static void notify_All_player(Notifys type, String message, String... values) {
		//提示给世界所有在线玩家
		notify_All_player(type.getValue(), message, values);
	}

	public static void notify_All_player(byte type, String message, String... values) {
		PersonalNoticeMessage msg = new PersonalNoticeMessage();
		msg.setType(type);
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

	
	/**带有物品tips的提示信息
	 * 
	 * @param type
	 * @param message
	 * @param iteminfolist
	 * @param values
	 */
	public static void notify_All_player(Notifys type, String message, List<GoodsInfoRes> iteminfolist,int subtype,String... values) {
		//提示给世界所有在线玩家
		notify_All_player(type.getValue(), message, iteminfolist,subtype,values);
	}

	public static void notify_All_player(byte type, String message, List<GoodsInfoRes> iteminfolist,int subtype,String... values) {
		PersonalNoticeMessage msg = new PersonalNoticeMessage();
		msg.setType(type);
		msg.setContent(message);
		if (iteminfolist != null) {
			msg.getGoodsinfos().addAll(iteminfolist);
		}
		msg.setSubtype(subtype);
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
	 * 玩家领取活动奖励公告
	 *
	 * @param type
	 * @param activityid
	 * @param message
	 * @param values
	 */
	public static void notify_All_Activity(Notifys type, int activityid, String message, String... values) {
		PersonalNoticeActivityMessage msg = new PersonalNoticeActivityMessage();
		msg.setType(type.getValue());
		msg.setActivityId(activityid);
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

//	/**
//	 * 全服广播通知
//	 * @param message 通知消息
//	 * @param values 参数值
//	 */
//	public static void boardcastWorld(String message, String... values){
//		WorldNoticeMessage msg = new WorldNoticeMessage();
//		msg.setContent(message);
//		if(values!=null){
//			List<String> list = new ArrayList<String>();
//			for (int i = 0; i < values.length; i++) {
//				list.add(values[i]);
//			}
//			msg.setValues(list);
//		}
//		tell_world_message(msg);
//	}
	/**
	 * 发送消息到世界服务器
	 *
	 * @param message
	 */
	public static void send_to_world(Message message) {
		//WServer.getInstance().getWorldSessions().get(0).write(message);
		write(WServer.getInstance().getWorldSessions().get(0), message);
	}

//	/**消息广播给队友
//	 * 
//	 * @param sender  发送者
//	 * @param message
//	 */
//	public static void send_to_Team(Player sender ,Message message) {
//		if(sender != null && message !=null) {
//			if(sender.getTeamid() > 0) {
//				TeambroadcastMessage msg = new TeambroadcastMessage();
//				msg.setTeammessage(message);
//				msg.setPlayerid(sender.getId());
//				send_to_world(msg);
//			}
//		}
//	}
//	
//	/**
//	 * 消息广播给公会所有成员
//	 * @param player 发送者
//	 * @param message 消息
//	 * @return 
//	 */
//	public static void send_to_guild(Player player, Message message){
//		if (player != null && message != null) {
//			if (player.getGuildId() != 0) {
//				ReqInnerGuildBroadcastToWorldMessage sendMessage = new ReqInnerGuildBroadcastToWorldMessage();
//				sendMessage.setPlayerId(player.getId());
//				sendMessage.setGuildMessage(message);
//				send_to_world(sendMessage);
//			}
//		}
//	}
//	
	/**
	 * 发送消息到网关服务器
	 *
	 * @param server
	 * @param message
	 */
	public static void send_to_gate(int server, long id, Message message) {
		//if(NOT_ALLOW.contains(message.getId())) return;
		message.setSendId(id);
		List<IoSession> ioSessions = WServer.getInstance().getGateSessions().get(server);
		if (ioSessions != null) {
			logbossmsg(message); //记录BOSS相关的消息
			
//			if(message instanceof ResRoundObjectsMessage || message instanceof ResRoundMonsterDisappearMessage){
//				// || message instanceof ResRoundPlayerDisappearMessage || message instanceof ResChangePositionMessage) 
//				log.error("->[" + castListToString(message.getRoleId()) + "-" + server + "-" + message.getClass().getSimpleName() + "]" + message.toString());
//			}
			
			//IoSession session = sessions.get((int)(id % sessions.size()));
			//IoSession session = sessions.get(0);
			//session.write(message);
			write(ioSessions.get(0), message);
		} else {
			log.error("与网关服务器" + server + "通讯session不存在！");
		}
	}

	/**
	 * 发送消息到所有网关服务器
	 *
	 * @param server
	 * @param message
	 */
	public static void send_to_gate(long id, Message message) {
		//if(NOT_ALLOW.contains(message.getId())) return;
		message.setSendId(id);
		
//		if(message instanceof ResRoundObjectsMessage || message instanceof ResRoundMonsterDisappearMessage){
//			log.error("send to " + castListToString(message.getRoleId()) + " message:" + message);
//		}
		logbossmsg(message); //记录BOSS相关的消息
		
		Iterator<List<IoSession>> iter = WServer.getInstance().getGateSessions().values().iterator();

//		log.debug("->[" + castListToString(message.getRoleId()) + "-AllGate-" + message.getClass().getSimpleName() + "]" + message.toString());

		while (iter.hasNext()) {
			List<IoSession> ioSessions = (List<IoSession>) iter.next();
			if (ioSessions.size() > 0) {
				//ioSessions.get(((int)(id % ioSessions.size()))).write(message);
				//ioSessions.get(0).write(message);
				write(ioSessions.get(0), message);
			}
		}
	}
	
	/**
	 * 发送消息到所有网关服务器
	 *
	 * @param server
	 * @param message
	 */
	public static void send_to_gate(Message message) {
		//if(NOT_ALLOW.contains(message.getId())) return;
		Iterator<List<IoSession>> iter = WServer.getInstance().getGateSessions().values().iterator();
		
		logbossmsg(message); //记录BOSS相关的消息
//		if(message instanceof ResRoundObjectsMessage || message instanceof ResRoundMonsterDisappearMessage){
//			log.error("->[" + castListToString(message.getRoleId()) + "-AllGate-" + message.getClass().getSimpleName() + "]" + message.toString());
//		}
		while (iter.hasNext()) {
			List<IoSession> ioSessions = (List<IoSession>) iter.next();
			if (ioSessions.size() > 0) {
				//ioSessions.get(RandomUtils.random(ioSessions.size())).write(message);
				//ioSessions.get(0).write(message);
				write(ioSessions.get(0), message);
			}
		}
	}

	/**
	 * 写入发送到游戏服务器缓存
	 *
	 * @param session
	 * @param message
	 */
	public static void write(IoSession session, Message message) {
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
		buf.putInt(buf.limit() - Integer.SIZE / Byte.SIZE);
		buf.rewind();

		IoBuffer sendbuf = null;
		synchronized (session) {
			if (session.containsAttribute("SEND_BUF")) {
				sendbuf = (IoBuffer) session.getAttribute("SEND_BUF");
			} else {
				sendbuf = IoBuffer.allocate(1024);
				sendbuf.setAutoExpand(true);
				sendbuf.setAutoShrink(true);
				session.setAttribute("SEND_BUF", sendbuf);
			}
			sendbuf.put(buf);
		}
	}

	@SuppressWarnings({"rawtypes"})
	public static String castListToString(Collection list) {
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

	/**
	 * 给帮派全部成员的文字通知信息
	 *
	 */
	public static void notify_guild_all(long gid, String cont) {
		ReqGuildStrMessageToWorldMessage wmsg = new ReqGuildStrMessageToWorldMessage();
		wmsg.setGuildId(gid);
		wmsg.setContent(cont);
		MessageUtil.send_to_world(wmsg);
	}
	
	//BOSS集合
	private static List<Integer> bosses = Arrays.asList(91,151,211,261,341,391,441,541,581,631,681,682,683,684,2010,2011,2012,2013,2014,2015,2016,2017,2018,2301,4091,4151,4211,4261,4341,4391,4441,4541,4581,4631,4681);
	public static void logbossmsg(Message message){
		//记录所有BOSS发往前端的日志
		try{
			if( (message instanceof ResRoundMonsterMessage) ){
				int monsterid = ((ResRoundMonsterMessage)message).getMonster().getMonsterModelId();
				if(bosses.contains(monsterid)){ //如果怪物是BOSS
					log.error("ResRoundMonsterMessage - Monster("+monsterid+") "+ castListToString(message.getRoleId()) + " message:" + message);
				}
			}
		}catch (Exception e) {
			log.error(e, e);
		}
	}
}
