package com.game.login.handler;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.game.command.Handler;
import com.game.login.message.ResRolesCreateReportToClientMessage;
import com.game.login.message.ResRolesCreateReportToGateMessage;
import com.game.player.manager.PlayerManager;
import com.game.server.GateServer;

public class ResRolesCreateReportToGateHandler extends Handler{

	Logger log = Logger.getLogger(ResRolesCreateReportToGateHandler.class);

	public void action(){
		try{
			ResRolesCreateReportToGateMessage msg = (ResRolesCreateReportToGateMessage)this.getMessage();
			ResRolesCreateReportToClientMessage cmsg = new ResRolesCreateReportToClientMessage();
			cmsg.setPlayerId(msg.getPlayerId());
			cmsg.setPlayername(msg.getPlayername());
			PlayerManager.getInstance().createSuccess(msg.getCreateServerId(), msg.getUserId());
			IoSession ioSession = GateServer.getInstance().getSessionByUser(msg.getCreateServerId(),msg.getUserId());
			if (ioSession != null) {
				ioSession.write(cmsg);
			}
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}