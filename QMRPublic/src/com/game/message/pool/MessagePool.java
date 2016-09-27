package com.game.message.pool;
	
import java.util.HashMap;

import com.game.command.Handler;
import com.game.dataserver.handler.ReqCancelPlayerEnterToDataServerHandler;
import com.game.dataserver.handler.ReqCancelTeamEnterToDataServerHandler;
import com.game.dataserver.handler.ReqCheckPlayerEnterToGameServerHandler;
import com.game.dataserver.handler.ReqCheckTeamEnterToGameServerHandler;
import com.game.dataserver.handler.ReqFinishMatchToDataServerHandler;
import com.game.dataserver.handler.ReqKickPlayerToCrossServerHandler;
import com.game.dataserver.handler.ReqPlayerEnterToDataServerHandler;
import com.game.dataserver.handler.ReqPlayerQuitToDataServerHandler;
import com.game.dataserver.handler.ReqPlayerRewardFromDataServerHandler;
import com.game.dataserver.handler.ReqPlayerStateFromDataServerHandler;
import com.game.dataserver.handler.ReqReceivePlayerRewardToDataServerHandler;
import com.game.dataserver.handler.ReqStartMatchToGameServerHandler;
import com.game.dataserver.handler.ReqSyncPlayerInfoFromDataServerHandler;
import com.game.dataserver.handler.ReqSyncPlayerToDataServerHandler;
import com.game.dataserver.handler.ReqTeamEnterToDataServerHandler;
import com.game.dataserver.handler.ResCancelPlayerEnterToGameServerHandler;
import com.game.dataserver.handler.ResCancelTeamEnterToGameServerHandler;
import com.game.dataserver.handler.ResCheckPlayerEnterToDataServerHandler;
import com.game.dataserver.handler.ResCheckTeamEnterToDataServerHandler;
import com.game.dataserver.handler.ResGmCommandToDataServerHandler;
import com.game.dataserver.handler.ResPlayerEnterToGameServerHandler;
import com.game.dataserver.handler.ResPlayerQuitToDataServerHandler;
import com.game.dataserver.handler.ResPlayerRewardFromDataServerHandler;
import com.game.dataserver.handler.ResPlayerStateFromDataServerHandler;
import com.game.dataserver.handler.ResReceivePlayerRewardToDataServerHandler;
import com.game.dataserver.handler.ResSyncPlayerInfoFromDataServerHandler;
import com.game.dataserver.handler.ResSyncPlayerToDataServerHandler;
import com.game.dataserver.handler.ResTeamEnterToGameServerHandler;
import com.game.dataserver.message.ReqCancelPlayerEnterToDataServerMessage;
import com.game.dataserver.message.ReqCancelTeamEnterToDataServerMessage;
import com.game.dataserver.message.ReqCheckPlayerEnterToGameServerMessage;
import com.game.dataserver.message.ReqCheckTeamEnterToGameServerMessage;
import com.game.dataserver.message.ReqFinishMatchToDataServerMessage;
import com.game.dataserver.message.ReqKickPlayerToCrossServerMessage;
import com.game.dataserver.message.ReqPlayerEnterToDataServerMessage;
import com.game.dataserver.message.ReqPlayerQuitToDataServerMessage;
import com.game.dataserver.message.ReqPlayerRewardFromDataServerMessage;
import com.game.dataserver.message.ReqPlayerStateFromDataServerMessage;
import com.game.dataserver.message.ReqReceivePlayerRewardToDataServerMessage;
import com.game.dataserver.message.ReqStartMatchToGameServerMessage;
import com.game.dataserver.message.ReqSyncPlayerInfoFromDataServerMessage;
import com.game.dataserver.message.ReqSyncPlayerToDataServerMessage;
import com.game.dataserver.message.ReqTeamEnterToDataServerMessage;
import com.game.dataserver.message.ResCancelPlayerEnterToGameServerMessage;
import com.game.dataserver.message.ResCancelTeamEnterToGameServerMessage;
import com.game.dataserver.message.ResCheckPlayerEnterToDataServerMessage;
import com.game.dataserver.message.ResCheckTeamEnterToDataServerMessage;
import com.game.dataserver.message.ResGmCommandToDataServerMessage;
import com.game.dataserver.message.ResPlayerEnterToGameServerMessage;
import com.game.dataserver.message.ResPlayerQuitToDataServerMessage;
import com.game.dataserver.message.ResPlayerRewardFromDataServerMessage;
import com.game.dataserver.message.ResPlayerStateFromDataServerMessage;
import com.game.dataserver.message.ResReceivePlayerRewardToDataServerMessage;
import com.game.dataserver.message.ResSyncPlayerInfoFromDataServerMessage;
import com.game.dataserver.message.ResSyncPlayerToDataServerMessage;
import com.game.dataserver.message.ResTeamEnterToGameServerMessage;
import com.game.message.Message;
import com.game.server.handler.ReqRegisterGameForPublicHandler;
import com.game.server.handler.ResRegisterGameForPublicHandler;
import com.game.server.message.ReqRegisterGameForPublicMessage;
import com.game.server.message.ResRegisterGameForPublicMessage;

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
		register(300311, ReqRegisterGameForPublicMessage.class, ReqRegisterGameForPublicHandler.class);
		register(300312, ResRegisterGameForPublicMessage.class, ResRegisterGameForPublicHandler.class);
		register(203301, ReqSyncPlayerToDataServerMessage.class, ReqSyncPlayerToDataServerHandler.class);
		register(203302, ResSyncPlayerToDataServerMessage.class, ResSyncPlayerToDataServerHandler.class);
		register(203303, ReqSyncPlayerInfoFromDataServerMessage.class, ReqSyncPlayerInfoFromDataServerHandler.class);
		register(203304, ResSyncPlayerInfoFromDataServerMessage.class, ResSyncPlayerInfoFromDataServerHandler.class);
		register(203306, ReqPlayerStateFromDataServerMessage.class, ReqPlayerStateFromDataServerHandler.class);
		register(203307, ResPlayerStateFromDataServerMessage.class, ResPlayerStateFromDataServerHandler.class);
		register(203308, ReqPlayerQuitToDataServerMessage.class, ReqPlayerQuitToDataServerHandler.class);
		register(203309, ResPlayerQuitToDataServerMessage.class, ResPlayerQuitToDataServerHandler.class);
		register(203310, ReqReceivePlayerRewardToDataServerMessage.class, ReqReceivePlayerRewardToDataServerHandler.class);
		register(203311, ResReceivePlayerRewardToDataServerMessage.class, ResReceivePlayerRewardToDataServerHandler.class);
		register(203312, ReqPlayerRewardFromDataServerMessage.class, ReqPlayerRewardFromDataServerHandler.class);
		register(203313, ResPlayerRewardFromDataServerMessage.class, ResPlayerRewardFromDataServerHandler.class);
		register(203314, ReqFinishMatchToDataServerMessage.class, ReqFinishMatchToDataServerHandler.class);
		register(203315, ReqKickPlayerToCrossServerMessage.class, ReqKickPlayerToCrossServerHandler.class);
		register(203316, ReqTeamEnterToDataServerMessage.class, ReqTeamEnterToDataServerHandler.class);
		register(203317, ReqPlayerEnterToDataServerMessage.class, ReqPlayerEnterToDataServerHandler.class);
		register(203318, ResTeamEnterToGameServerMessage.class, ResTeamEnterToGameServerHandler.class);
		register(203319, ResPlayerEnterToGameServerMessage.class, ResPlayerEnterToGameServerHandler.class);
		register(203320, ReqCheckTeamEnterToGameServerMessage.class, ReqCheckTeamEnterToGameServerHandler.class);
		register(203321, ReqCheckPlayerEnterToGameServerMessage.class, ReqCheckPlayerEnterToGameServerHandler.class);
		register(203322, ResCheckTeamEnterToDataServerMessage.class, ResCheckTeamEnterToDataServerHandler.class);
		register(203323, ResCheckPlayerEnterToDataServerMessage.class, ResCheckPlayerEnterToDataServerHandler.class);
		register(203324, ReqCancelTeamEnterToDataServerMessage.class, ReqCancelTeamEnterToDataServerHandler.class);
		register(203325, ReqCancelPlayerEnterToDataServerMessage.class, ReqCancelPlayerEnterToDataServerHandler.class);
		register(203326, ResCancelTeamEnterToGameServerMessage.class, ResCancelTeamEnterToGameServerHandler.class);
		register(203327, ResCancelPlayerEnterToGameServerMessage.class, ResCancelPlayerEnterToGameServerHandler.class);
		register(203328, ReqStartMatchToGameServerMessage.class, ReqStartMatchToGameServerHandler.class);
		register(203330, ResGmCommandToDataServerMessage.class, ResGmCommandToDataServerHandler.class);
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