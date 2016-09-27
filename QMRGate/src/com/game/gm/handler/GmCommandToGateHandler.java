package com.game.gm.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.gm.message.GmCommandToGateMessage;
import com.game.login.config.HeartConfig;
import com.game.login.handler.ReqHeartHandler;
import com.game.login.loader.GateHeartConfigXmlLoader;
import com.game.login.loader.HeartConfigXmlLoader;
import com.game.player.manager.PlayerManager;
import com.game.server.GateServer;
import com.game.utils.Global;

public class GmCommandToGateHandler extends Handler{

	Logger log = Logger.getLogger(GmCommandToGateHandler.class);

	public void action(){
		try {
			GmCommandToGateMessage msg = (GmCommandToGateMessage) this.getMessage();
			//检查Gm指令
			if (msg.getCommand() == null || ("").equals(msg.getCommand())) {
				return;
			}
			//分割指令
			String[] strs = msg.getCommand().split(" ");
			if("&maxlogin".equalsIgnoreCase(strs[0])){
				PlayerManager.MAX_PLAYER = Integer.parseInt(strs[1]);
			}else if("&reset".equalsIgnoreCase(strs[0])){
				PlayerManager.getInstance().removeUserState(Integer.parseInt(strs[1]), strs[2]);
			}else if("&setToken".equalsIgnoreCase(strs[0])){
				PlayerManager.getInstance().setToken(strs[1]);
			}else if("&setheart".equalsIgnoreCase(strs[0])){
				try{
					HeartConfig config = GateServer.heartconfig; 
					config = new HeartConfigXmlLoader().load("gate-config/heart-config.xml");
					ReqHeartHandler.HEART_TIME = config.getHearttime();
					ReqHeartHandler.ALLOW_TIME = config.getAllowtime();
					ReqHeartHandler.SUCCESS    = config.getSuccess();
					ReqHeartHandler.ERROR      = config.getError();
					ReqHeartHandler.CLOSE_TIME = config.getClosetime();
					//成功
					log.error("修改心跳参数成功 修改后 "+ReqHeartHandler.HEART_TIME+"\t"+ReqHeartHandler.ALLOW_TIME+"\t"+ReqHeartHandler.SUCCESS+"\t"+ReqHeartHandler.ERROR+"\t"+ReqHeartHandler.CLOSE_TIME);
					return ;
				}catch (Exception e) {
					log.error(e, e);
				}
				//失败
				log.error("修改心跳参数 失败");
			}else if("&setgateheart".equalsIgnoreCase(strs[0])){
				GateServer.gateheartconfig = new GateHeartConfigXmlLoader().load("gate-config/gate-heart-config.xml");
				Global.HEART_PARA = GateServer.gateheartconfig.getHeart_para();
				Global.HEART_WEB  = GateServer.gateheartconfig.getHeart_web();
				log.info("网关心跳参数配置成功 ["+Global.HEART_PARA+"||"+Global.HEART_WEB+"]");
			}
		} catch (ClassCastException e) {
			log.error(e, e);
		} catch (Exception e) {
			log.error(e, e);
		}
	}
}