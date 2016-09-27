package com.game.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public interface IServer {
	/**
	 * 网关服务器
	 */
	public static int GATE_SERVER = 1;
	/**
	 * 游戏服务器
	 */
	public static int GAME_SERVER = 2;
	/**
	 * 世界服务器
	 */
	public static int WORLD_SERVER = 3;
	/**
	 * 公共服务器
	 */
	public static int PUBLIC_SERVER = 4;
	
	/**
	 * 消息处理
	 * @param iosession
	 * @param buf
	 */
	public void doCommand(IoSession iosession, IoBuffer buf);
	/**
	 * Session创建事件
	 */
	public void sessionCreate(IoSession iosession);
	/**
	 * Session打开事件
	 */
	public void sessionOpened(IoSession iosession);
	/**
	 * Session关闭事件
	 */
	public void sessionClosed(IoSession iosession);
	/**
	 * Session出错事件
	 */
	public void exceptionCaught(IoSession session, Throwable cause);
	/**
	 * Session Idle出错事件
	 */
	 public void sessionIdle(IoSession iosession, IdleStatus idlestatus);
}
