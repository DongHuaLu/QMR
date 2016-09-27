package com.game.login.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.game.command.Handler;
import com.game.login.message.ReqHeartMessage;
import com.game.login.message.ResHeartMessage;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.server.GateServer;
import com.game.server.message.ReqCheckToGameMessage;
import com.game.utils.MessageUtil;

public class ReqHeartHandler extends Handler{

	Logger log = Logger.getLogger(ReqHeartHandler.class);
	
//	private static Logger closelog = Logger.getLogger("GATESESSIONCLOSE");
	
	private static Logger heartlog = Logger.getLogger("HEART");
	//上一次心跳时间
	protected static final String PRE_HEART = "pre_heart"; 
	
	protected static final String FIRST_HEART = "first_heart"; 
	//上一次间隔过短发生时间
//	private static final String PRE_ERROR = "pre_error"; 
	//间隔过短发生次数
	protected static final String ERROR_TIMES = "error_times"; 
	//允许问题发生次数
	protected static final String ALLOW_TIMES = "allow_times"; 
	//心跳间隔时间
	public static long HEART_TIME = 10 * 1000;
	//心跳允许间隔时间
	public static long ALLOW_TIME = 10 * 1000 - 2 * 1000;
	//心跳关闭间隔时间
	public static long CLOSE_TIME = 2 * 60 * 1000;
	//错误增加次数
	public static int ERROR = 2;
	//正确减少测试
	public static int SUCCESS = 1;
	
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void action(){
		try{
			ReqHeartMessage msg = (ReqHeartMessage)this.getMessage();
			
			heartlog.error(msg.getSession() + "处理心跳，收到心跳时间:" + format.format(new Date(this.getCreateTime())));
			//统计心跳间隔
			long pre = 0;
	    	if(msg.getSession().containsAttribute(PRE_HEART)) pre = (Long)msg.getSession().getAttribute(PRE_HEART);
	    	
	    	long between = this.getCreateTime() - pre;
	    	if(between < ALLOW_TIME){
	    		return;
	    	}
	    	
	    	msg.getSession().setAttribute(PRE_HEART, this.getCreateTime());
	    	
//	    	boolean first = true;
//	    	if(msg.getSession().containsAttribute(FIRST_HEART)){
//	    		first = false;
//	    	}else{
//	    		msg.getSession().setAttribute(FIRST_HEART, "1");
//	    	}
//	    	//第一套方案
//	    	if(!first && pre != 0){
//	    		long between = this.getCreateTime() - pre;
//	    		if(between < ALLOW_TIME){
//	    			int allow = 0;
//	    		    if(msg.getSession().containsAttribute(ALLOW_TIMES)) allow = (Integer)msg.getSession().getAttribute(ALLOW_TIMES);
//	    		    allow -= 1;
//	    		    if(allow>0){
//	    		    	msg.getSession().setAttribute(ALLOW_TIMES, allow);
//	    		    }else{
//		    			int times = 0;
//	    				if(msg.getSession().containsAttribute(ERROR_TIMES)) times = (Integer)msg.getSession().getAttribute(ERROR_TIMES);
//	    				times += ERROR;
//	    				sendCheck(msg.getSession(), times, between);
//	    				msg.getSession().setAttribute(ERROR_TIMES, times);
//	    				msg.getSession().removeAttribute(ALLOW_TIMES);
//	    		    }
//	    		}else{
//	    			if(between > CLOSE_TIME){
//	    				SessionUtil.closeSession(msg.getSession(), "心跳间隔过长");
//		    		}else{
//		    		    int allow = 0;
//		    		    if(msg.getSession().containsAttribute(ALLOW_TIMES)) allow = (Integer)msg.getSession().getAttribute(ALLOW_TIMES);
//		    		    allow += (int)(between / HEART_TIME)-1;
//		    		    if(allow>0) msg.getSession().setAttribute(ALLOW_TIMES, allow);
//		    			int times = 0;
//	    				if(msg.getSession().containsAttribute(ERROR_TIMES)) times = (Integer)msg.getSession().getAttribute(ERROR_TIMES);
//	    				if(times > 0){
//		    				times -= SUCCESS;
//		    				if(times<0) times=0;
//		    				sendCheck(msg.getSession(), times, between);
//		    				msg.getSession().setAttribute(ERROR_TIMES, times);
//	    				}
//	    			}
//	    		}
	    		
//	    		if(between < HEART_TIME){
//	    			long pre_error = 0;
//	    			if(msg.getSession().containsAttribute(PRE_ERROR)) pre_error = (Long)msg.getSession().getAttribute(PRE_ERROR);
//	    			msg.getSession().setAttribute(PRE_ERROR, this.getCreateTime());
//	    			
//	    			long errorBetween = this.getCreateTime() - pre_error;
//	    			if(errorBetween < ERROR_TIME){
//	    				int times = 0;
//	    				if(msg.getSession().containsAttribute(ERROR_TIMES)) times = (Integer)msg.getSession().getAttribute(ERROR_TIMES);
//	    				times += ERROR;
//	    				closelog.error("session " + msg.getSession() + " 作弊" + times + "次！");
//	    				sendCheck(msg.getSession(), times);
//	    				if(times >= MAX_ERROR){
//	    					closelog.error("session " + msg.getSession() + " 作弊次数超过10次！");
//	    					msg.getSession().setAttribute(ERROR_TIMES, times);
////	    					msg.getSession().close(true);
//	    					if(times >= MAX_ERROR * 3){
//	    						msg.getSession().removeAttribute(ERROR_TIMES);
//	    					}
//	    					//SessionUtil.closeSession(msg.getSession(), "作弊次数超过10次");
//	    				}else{
//	    					msg.getSession().setAttribute(ERROR_TIMES, times);
//	    				}
//	    			}else{
//	    				msg.getSession().setAttribute(ERROR_TIMES, ERROR);
//	    				sendCheck(msg.getSession(), ERROR);
//	    			}
//	    		}
//	    		else{
//	    			int times = 0;
//    				if(msg.getSession().containsAttribute(ERROR_TIMES)) times = (Integer)msg.getSession().getAttribute(ERROR_TIMES);
//    				times -= SUCCESS;
//    				if(times<0) times=0;
//    				sendCheck(msg.getSession(), times);
//    				if(times>0) msg.getSession().setAttribute(ERROR_TIMES, times);
//    				else msg.getSession().removeAttribute(ERROR_TIMES);
//	    		}
//	    	}
	    	//第二套方案
	    	int time = (int)(System.currentTimeMillis() - GateServer.HEART_START);
	    	heartlog.error("发送间隔相差时间:" + (msg.getTime() - time) + ",消息时间:" + msg.getTime() + ",系统开服时间:" + time);
	    	if(msg.getTime() > time){
	    		sendCheck(msg.getSession(), 0, msg.getTime() - time);
	    	}

//	    	if(msg.getTime() > time){
//	    		int times = 0;
//				if(msg.getSession().containsAttribute(ERROR_TIMES)) times = (Integer)msg.getSession().getAttribute(ERROR_TIMES);
//				times += ERROR;
//				sendCheck(msg.getSession(), times, msg.getTime() - time);
//				msg.getSession().setAttribute(ERROR_TIMES, times);
//	    	}
//	    	else{
//	    		int times = 0;
//				if(msg.getSession().containsAttribute(ERROR_TIMES)) times = (Integer)msg.getSession().getAttribute(ERROR_TIMES);
//				if(times > 0){
//    				times -= SUCCESS;
//    				if(times<0) times=0;
//    				sendCheck(msg.getSession(), times, msg.getTime() - time);
//    				msg.getSession().setAttribute(ERROR_TIMES, times);
//				}
//	    	}
	    		
	    	ResHeartMessage re = new ResHeartMessage();
			re.setTime((int)(System.currentTimeMillis()/1000));
			re.setTime2(time);
			msg.getSession().write(re);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
	
	/**
	 * 发送作弊消息到游戏服务器
	 * @param session
	 */
	private void sendCheck(IoSession session, int times, long para){
		if(session.containsAttribute(PlayerManager.PLAYER_ID)){
			long playerId = (Long)session.getAttribute(PlayerManager.PLAYER_ID);
			//发送到游戏服务器
			ReqCheckToGameMessage msg = new ReqCheckToGameMessage();
			msg.getRoleId().add(playerId);
			msg.setPlayerid(playerId);
			msg.setCheck(times);
			msg.setPara(para);
			
			Player player = PlayerManager.getInstance().getPlayer(playerId);
			if(player!=null){
				int server = player.getServer();
				int sessionId = (Integer)session.getAttribute(PlayerManager.SESSION_ID);
				MessageUtil.send_to_game(server, sessionId, msg);
			}
		}
	}
}