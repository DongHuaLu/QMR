package com.game.util;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.game.message.Message;
import com.game.server.impl.PublicServer;

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

	protected static Logger log = Logger.getLogger(MessageUtil.class);

	/**
	 * 通知游戏服务器 消息
	 */
	public static void send_to_server_message(String web, int serverId, Message message) {
		IoSession session = PublicServer.getInstance().getGame(serverId, web);
		if(session!=null){
			session.write(message);
		}
	}
	
	
}
