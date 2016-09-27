package com.game.server.filter;

import java.util.List;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.command.ICommand;
import com.game.manager.ManagerPool;
import com.game.message.Message;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.server.config.MapConfig;
import com.game.server.message.ResDiscardMsgMessage;
import com.game.server.script.IServerHandlerScript;
import com.game.utils.MessageUtil;

public class HandlerFilter implements ICommandFilter {

	protected Logger log = Logger.getLogger(HandlerFilter.class);
	
	private static Logger droplog = Logger.getLogger("DROPCOMMAND");
	
	private List<MapConfig> mapConfigs;
	
	public HandlerFilter(List<MapConfig> mapConfigs){
		this.mapConfigs = mapConfigs;
	}
	
	@Override
	public boolean filter(ICommand command) {
		if(command instanceof Handler){
			Handler handler = (Handler)command;
			IServerHandlerScript script = (IServerHandlerScript) ManagerPool.scriptManager.getScript(ScriptEnum.HANDLER_ACTION);
			if (script != null) {
				try {
					boolean result = script.check(handler);
					if(!result){
						return false;
					}
				} catch (Exception e) {
					log.error(e, e);
				}
			}
			if(handler.getMessage()!=null){
				Message msg = handler.getMessage();
				int id = msg.getId() % 1000;
				//客户端发到服务器消息
				if(id < 300 && id >= 200){
					Player player = (Player)handler.getParameter();
					if(player==null){
						log.error("因为玩家不存在丢弃指令" + handler.getMessage());
						return false;
					}
					boolean result = false;
					for (int i = 0; i < mapConfigs.size(); i++) {
						MapConfig config = mapConfigs.get(i);
						if(player.getMap()==config.getMapId() && player.getLine()==config.getLineId() && player.getServerId()==config.getServerId()){
							result = true;
							break;
						}
					}
					if(!result){
						StringBuffer buf = new StringBuffer();
						for (int i = 0; i < mapConfigs.size(); i++) {
							MapConfig config = mapConfigs.get(i);
							buf.append("{map=" + config.getMapId()+", line=" + config.getLineId()+", mapModel=" + config.getMapModelId() + "}");
						}
						droplog.error("丢弃玩家(" + player.getIcon() + ",line=" + player.getLine() + ",map=" + player.getMap() + ",mapModel=" + player.getMapModelId() + ")Map(" + buf.toString() + ")指令：" + msg.toString());
						ResDiscardMsgMessage dropmsg = new ResDiscardMsgMessage();
						dropmsg.setMsgid(msg.getId());
						dropmsg.setMsgcont(msg.toString());
						MessageUtil.tell_player_message(player, dropmsg);
					}
					return result;
				}
			}
		}
		return true;
	}

}
