package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.dataserver.message.ResGmCommandToDataServerMessage;
import com.game.gm.script.IGmCommandScript;
import com.game.script.manager.ScriptManager;

public class ResGmCommandToDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ResGmCommandToDataServerHandler.class);

	public void action(){
		try{
			ResGmCommandToDataServerMessage msg = (ResGmCommandToDataServerMessage)this.getMessage();
			//检查Gm指令
			if (msg.getCommand() == null || ("").equals(msg.getCommand())) {
				return;
			}
			//分割指令
			String[] strs = msg.getCommand().split(" ");
			
			if ("script".equalsIgnoreCase(strs[1])) {
				ScriptManager.getInstance().reload(Integer.parseInt(strs[2]));
			}else{
				IGmCommandScript script = (IGmCommandScript) ScriptManager.getInstance().getScript(13);
				if (script != null) {
					try {
						script.doCommand(msg.getCommand());
					} catch (Exception e) {
						log.error(e, e);
					}
				} else {
					log.error("GM命令脚本不存在！");
				}
			}
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}