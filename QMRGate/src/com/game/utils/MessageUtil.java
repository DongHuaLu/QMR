package com.game.utils;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.game.manager.ManagerPool;
import com.game.message.Message;
import com.game.message.TransfersMessage;
import com.game.player.structs.Player;
import com.game.server.GateServer;
import com.game.util.ZLibUtils;

public class MessageUtil {

	private static Logger log = Logger.getLogger(MessageUtil.class);
	
	public static ConcurrentHashMap<Integer, Long> packages = new ConcurrentHashMap<Integer, Long>();
	
	public static ConcurrentHashMap<Integer, Integer> packagenums = new ConcurrentHashMap<Integer, Integer>();
	
	public static ConcurrentHashMap<Integer, Integer> packagemax = new ConcurrentHashMap<Integer, Integer>();
	
	public static ConcurrentHashMap<Integer, Integer> packagemin = new ConcurrentHashMap<Integer, Integer>();
	/**
	 * 通知玩家消息	
	 * @param me 玩家
	 * @param message 消息
	 */
	public static void tell_player_message(Player player, Message message){
		tell_player_message(player.getId(), message);
	}
	
	public static void tell_player_message(Player player, TransfersMessage buf){
		tell_player_message(player.getId(), buf);
	}
	
	/**
	 * 全服通知
	 * @param message 信息
	 */
	public static void tell_world_message(Message message){
		//全体发送
		Player[] players = ManagerPool.playerManager.getPlayers().values().toArray(new Player[0]);
		for (Player player : players) {
			MessageUtil.tell_player_message(player, message);
		}
	}
	
	/**
	 * 全服通知
	 * @param message 信息
	 */
	public static void tell_world_message(TransfersMessage msg){
		//全体发送
		Player[] players = ManagerPool.playerManager.getPlayers().values().toArray(new Player[0]);
		for (Player player : players) {
			MessageUtil.tell_player_message(player, msg);
		}
	}
	
	
	public static void tell_player_message(long roleId, Message message){
		IoSession session = GateServer.getInstance().getSessionByRole(roleId);
		if(session!=null){
			if(log.isDebugEnabled()){
				log.debug("->["+roleId+"-"+message.getClass().getSimpleName()+"]" + message.toString());	
			}
			//session.write(message);
			writeToPlayer(session, message);
		}
	}
	
	public static void tell_player_message(long roleId, TransfersMessage buf){
		IoSession session = GateServer.getInstance().getSessionByRole(roleId);
		if(session!=null){
			if(log.isDebugEnabled()){
				log.debug("->["+roleId+"]" + buf.toString());	
			}
			//session.write(buf);
			writeToPlayer(session, buf);
		}
	}
	
	/**
	 * 发送消息到世界服务器
	 * @param message
	 */
//	public static void send_to_world(Message message){
//		GateServer.getInstance().getWorldSession().write(message);
//	}
	
	/**
	 * 发送消息到游戏服务器
	 * @param server
	 * @param message
	 */
	public static boolean send_to_game(int server, int id, Message message){
		List<IoSession> sessions = GateServer.getInstance().getGameSession(server);
		if(sessions!=null){
			message.setSendId(id);
			//IoSession session = sessions.get((int)(id % sessions.size()));
			IoSession session = sessions.get(0);
			writeToGame(session, message);
			return true;
		}else{
			log.error("与游戏服务器" + server + "通讯session不存在！");
			return false;
		}
	}
	
	/**
	 * 发送消息到游戏服务器
	 * @param server
	 * @param message
	 */
	public static void send_to_game(int server, int id, TransfersMessage sendbuf){
		List<IoSession> sessions = GateServer.getInstance().getGameSession(server);
		if(sessions!=null){
			sendbuf.setSendId(id);
			//IoSession session = sessions.get((int)(id % sessions.size()));
			IoSession session = sessions.get(0);
			writeToGame(session, sendbuf);
		}else{
			log.error("与游戏服务器" + server + "通讯session不存在！");
		}
	}
	
	/**
	 * 写入发送到游戏服务器缓存
	 * @param session
	 * @param message
	 */
	private static void writeToPlayer(IoSession session, Message message){
		IoBuffer buf = IoBuffer.allocate(100);
		buf.setAutoExpand(true);
		buf.setAutoShrink(true);
		buf.putInt(0);
		buf.putInt(message.getId());
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
	
	/**
	 * 写入发送到游戏服务器缓存
	 * @param session
	 * @param message
	 */
	private static void writeToPlayer(IoSession session, TransfersMessage message){
		IoBuffer buf = IoBuffer.allocate(1024);
		buf.setAutoExpand(true);
		buf.setAutoShrink(true);
		//是否压缩
		boolean zlib = false;
		buf.putInt(0);
		buf.putInt(message.getId());
		if(message.getBytes().length > 512){
			byte[] bytes = ZLibUtils.compress(message.getBytes());
			buf.put(bytes);
			zlib = true;
		}else{
			buf.put(message.getBytes());
		}
		buf.flip();
		int length = buf.limit() - Integer.SIZE/Byte.SIZE;
		if(zlib){
			length = (((int)1) << 24) | length;
		}
		buf.putInt(length);
		buf.rewind();
		
		if(buf.remaining() > 0){
			if(packages.containsKey(message.getId())){
				packages.put(message.getId(), packages.get(message.getId()) + buf.remaining() + 20);
				packagenums.put(message.getId(), packagenums.get(message.getId()) + 1);
				int max = packagemax.get(message.getId());
				if(max < buf.remaining()){
					packagemax.put(message.getId(), buf.remaining());
				}
				int min = packagemin.get(message.getId());
				if(min > buf.remaining()){
					packagemin.put(message.getId(), buf.remaining());
				}
			}else{
				packages.put(message.getId(), (long)buf.remaining() + 20);
				packagenums.put(message.getId(), 1);
				packagemax.put(message.getId(), buf.remaining());
				packagemin.put(message.getId(), buf.remaining());
			}
		}
		
		synchronized (session) {
			IoBuffer sendbuf = null;
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
	
	/**
	 * 写入发送到游戏服务器缓存
	 * @param session
	 * @param message
	 */
	private static void writeToGame(IoSession session, Message message){
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
	
	/**
	 * 写入发送到游戏服务器缓存
	 * @param session
	 * @param message
	 */
	private static void writeToGame(IoSession session, TransfersMessage message){
		IoBuffer buf = IoBuffer.allocate(1024);
		buf.setAutoExpand(true);
		buf.setAutoShrink(true);
		buf.putInt(message.getLengthWithRole());
		buf.putInt(message.getId());
		buf.putLong(message.getSendId());
		buf.putInt(message.getRoleIds().size());
		for (int i = 0; i < message.getRoleIds().size(); i++) {
			buf.putLong(message.getRoleIds().get(i));
		}
		buf.put(message.getBytes());
		buf.flip();
		
		synchronized (session) {
			IoBuffer sendbuf = null;
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
	
	@SuppressWarnings({ "rawtypes" })
	public static String castListToString(List list){
		StringBuffer buf = new StringBuffer();
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Object object = (Object) iter.next();
			buf.append(",").append(object.toString());
		}
		if(buf.length()>0) buf.deleteCharAt(0);
		return "{"+ buf.toString() +"}";
	}
}
