package com.game.util;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

public class SessionUtil {

	private static Logger closelog = Logger.getLogger("GATESESSIONCLOSE");
	
	public static void closeSession(IoSession session, String reason){
		closelog.error(session + "-->close [because] " + reason);
		session.close(true);
	}
	
	public static void closeSession(IoSession session, String reason, boolean force){
		closelog.error(session + "-->close [because] " + reason);
		session.close(force);
	}
}
