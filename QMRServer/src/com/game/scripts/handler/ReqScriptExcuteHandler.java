package com.game.scripts.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.scripts.message.ReqScriptExcuteMessage;
import com.game.utils.NpcParamUtil;

public class ReqScriptExcuteHandler extends Handler{

	Logger log = Logger.getLogger(ReqScriptExcuteHandler.class);

	public void action(){
		try{
			ReqScriptExcuteMessage msg = (ReqScriptExcuteMessage)this.getMessage();
			//脚本处理
			if (NpcParamUtil.buttonVerification((Player)this.getParameter(), msg)) {
				ManagerPool.scriptManager.excute(msg.getScriptId(), msg.getMethod(), (Player)this.getParameter(), msg.getParas());
			}else {
				log.error("按钮验证错误:"+msg);
			}
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}