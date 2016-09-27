package com.game.message.pool;
	
import java.util.HashMap;

import com.game.command.Handler;
import com.game.gm.handler.GmCommandToGateHandler;
import com.game.gm.message.GmCommandToGateMessage;
import com.game.login.handler.ReqCreateCharacterHandler;
import com.game.login.handler.ReqDeleteHandler;
import com.game.login.handler.ReqHeartHandler;
import com.game.login.handler.ReqJustCreateCharacterHandler;
import com.game.login.handler.ReqKickPlayerHandler;
import com.game.login.handler.ReqLoginForNoUserHandler;
import com.game.login.handler.ReqLoginForPlatformHandler;
import com.game.login.handler.ReqLoginHandler;
import com.game.login.handler.ReqQuitHandler;
import com.game.login.handler.ReqSelectCharacterHandler;
import com.game.login.handler.ResCreateCharacterFailedHandler;
import com.game.login.handler.ResLoginCharacterFailedHandler;
import com.game.login.handler.ResLoginSuccessToGateHandler;
import com.game.login.handler.ResReloginCharacterHandler;
import com.game.login.handler.ResRemoveCharacterToGateHandler;
import com.game.login.handler.ResRolesCreateReportToGateHandler;
import com.game.login.message.ReqCreateCharacterMessage;
import com.game.login.message.ReqDeleteMessage;
import com.game.login.message.ReqHeartMessage;
import com.game.login.message.ReqJustCreateCharacterMessage;
import com.game.login.message.ReqKickPlayerMessage;
import com.game.login.message.ReqLoginForNoUserMessage;
import com.game.login.message.ReqLoginForPlatformMessage;
import com.game.login.message.ReqLoginMessage;
import com.game.login.message.ReqQuitMessage;
import com.game.login.message.ReqSelectCharacterMessage;
import com.game.login.message.ResCreateCharacterFailedMessage;
import com.game.login.message.ResLoginCharacterFailedMessage;
import com.game.login.message.ResLoginSuccessToGateMessage;
import com.game.login.message.ResReloginCharacterMessage;
import com.game.login.message.ResRemoveCharacterToGateMessage;
import com.game.login.message.ResRolesCreateReportToGateMessage;
import com.game.map.handler.ReqLoadFinishForChangeMapHandler;
import com.game.map.message.ReqLoadFinishForChangeMapMessage;
import com.game.message.Message;
import com.game.player.handler.ResDelPlayerStatusHandler;
import com.game.player.message.ResDelPlayerStatusMessage;
import com.game.publogin.handler.ReqKickPlayerForPublicHandler;
import com.game.publogin.handler.ReqLoginForPublicHandler;
import com.game.publogin.handler.ReqQuitForPublicHandler;
import com.game.publogin.handler.ResLoginCharacterFailedForPublicHandler;
import com.game.publogin.handler.ResLoginSuccessToPublicGateHandler;
import com.game.publogin.handler.ResRemoveCharacterToPublicGateHandler;
import com.game.publogin.message.ReqKickPlayerForPublicMessage;
import com.game.publogin.message.ReqLoginForPublicMessage;
import com.game.publogin.message.ReqQuitForPublicMessage;
import com.game.publogin.message.ResLoginCharacterFailedForPublicMessage;
import com.game.publogin.message.ResLoginSuccessToPublicGateMessage;
import com.game.publogin.message.ResRemoveCharacterToPublicGateMessage;
import com.game.server.handler.ReqCloseForGateHandler;
import com.game.server.handler.ReqRegisterGateHandler;
import com.game.server.handler.ResRegisterWorldForGateHandler;
import com.game.server.message.ReqCloseForGateMessage;
import com.game.server.message.ReqRegisterGateMessage;
import com.game.server.message.ResRegisterWorldForGateMessage;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 引用类列表
 */
public class MessagePool {
	//消息类字典
	HashMap<Integer, Class<?>> messages = new HashMap<Integer, Class<?>>();
	//处理类字典
	HashMap<Integer, Class<?>> handlers = new HashMap<Integer, Class<?>>();
	
	public MessagePool(){
		register(100201, ReqLoginMessage.class, ReqLoginHandler.class);
		register(100202, ReqSelectCharacterMessage.class, ReqSelectCharacterHandler.class);
		register(100203, ReqCreateCharacterMessage.class, ReqCreateCharacterHandler.class);
		register(100302, ResLoginSuccessToGateMessage.class, ResLoginSuccessToGateHandler.class);
		register(300301, ReqRegisterGateMessage.class, ReqRegisterGateHandler.class);
		register(300306, ResRegisterWorldForGateMessage.class, ResRegisterWorldForGateHandler.class);
		register(100206, ReqHeartMessage.class, ReqHeartHandler.class);
		register(101207, ReqLoadFinishForChangeMapMessage.class, ReqLoadFinishForChangeMapHandler.class);
		register(100306, ResRemoveCharacterToGateMessage.class, ResRemoveCharacterToGateHandler.class);
		register(100205, ReqQuitMessage.class, ReqQuitHandler.class);
		register(100309, ResCreateCharacterFailedMessage.class, ResCreateCharacterFailedHandler.class);
		register(100207, ReqDeleteMessage.class, ReqDeleteHandler.class);
		register(100208, ReqLoginForPlatformMessage.class, ReqLoginForPlatformHandler.class);
		register(100311, ResLoginCharacterFailedMessage.class, ResLoginCharacterFailedHandler.class);
		register(100312, ResReloginCharacterMessage.class, ResReloginCharacterHandler.class);
		register(103315, ResDelPlayerStatusMessage.class, ResDelPlayerStatusHandler.class);
		register(100209, ReqLoginForNoUserMessage.class, ReqLoginForNoUserHandler.class);
		register(300308, ReqCloseForGateMessage.class, ReqCloseForGateHandler.class);
		register(100313, ReqKickPlayerMessage.class, ReqKickPlayerHandler.class);
		register(200302, GmCommandToGateMessage.class, GmCommandToGateHandler.class);
		register(100314, ResRolesCreateReportToGateMessage.class, ResRolesCreateReportToGateHandler.class);
		register(100211, ReqJustCreateCharacterMessage.class, ReqJustCreateCharacterHandler.class);
		register(204201, ReqQuitForPublicMessage.class, ReqQuitForPublicHandler.class);
		register(204202, ReqLoginForPublicMessage.class, ReqLoginForPublicHandler.class);
		register(204302, ResLoginSuccessToPublicGateMessage.class, ResLoginSuccessToPublicGateHandler.class);
		register(204305, ResRemoveCharacterToPublicGateMessage.class, ResRemoveCharacterToPublicGateHandler.class);
		register(204307, ResLoginCharacterFailedForPublicMessage.class, ResLoginCharacterFailedForPublicHandler.class);
		register(204308, ReqKickPlayerForPublicMessage.class, ReqKickPlayerForPublicHandler.class);
	}
	
	private void register(int id, Class<?> messageClass, Class<?> handlerClass){
		messages.put(id, messageClass);
		if(handlerClass!=null) handlers.put(id, handlerClass);
	}
	
	/**
	 * 获取消息体
	 * @param id
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Message getMessage(int id) throws InstantiationException, IllegalAccessException{
		if(!messages.containsKey(id)){
			return null;
		}else{
			return (Message)messages.get(id).newInstance();
		}
	}
	
	/**
	 * 获取处理函数
	 * @param id
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Handler getHandler(int id) throws InstantiationException, IllegalAccessException{
		if(!handlers.containsKey(id)){
			return null;
		}else{
			return (Handler)handlers.get(id).newInstance();
		}
	}
}