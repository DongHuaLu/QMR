package com.game.player.handler;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.game.command.Handler;
import com.game.db.bean.Role;
import com.game.db.dao.RoleDao;
import com.game.login.bean.CharacterInfo;
import com.game.login.message.ResCharacterInfosMessage;
import com.game.login.message.ResDelPlayerStatusToClientMessage;
import com.game.player.message.ResDelPlayerStatusMessage;
import com.game.server.GateServer;

public class ResDelPlayerStatusHandler extends Handler{

	Logger log = Logger.getLogger(ResDelPlayerStatusHandler.class);

	public void action(){
		try{
			ResDelPlayerStatusMessage msg = (ResDelPlayerStatusMessage)this.getMessage();
			ResDelPlayerStatusToClientMessage cmsg = new ResDelPlayerStatusToClientMessage();
			cmsg.setPlayerId(msg.getPlayerId());
			cmsg.setType(msg.getType());
			cmsg.setUserId(msg.getUserId());
			IoSession oldSession = GateServer.getInstance().getSessionByUser(msg.getCreateServer(),msg.getUserId());
			if (oldSession!= null) {
				oldSession.write(cmsg);		//返回删号错误消息
				
				if (msg.getType() == 1) {	//如果删除成功，刷新人物列表
					//查询当前用户可登录角色列表
					RoleDao roledao = new RoleDao();
					List<Role> characters = null;
					characters = roledao.selectByUser(msg.getUserId(), msg.getCreateServer());
					if (characters != null) {
						ResCharacterInfosMessage return_msg = new ResCharacterInfosMessage();
						for (int i = 0; i < characters.size(); i++) {
							Role role = characters.get(i);
							CharacterInfo info = new CharacterInfo();
							info.setName(role.getName());
							info.setPlayerId(role.getRoleid());
							info.setLevel(role.getLevel());
							info.setSex((byte)(int)role.getSex());
							if (role.getLogintime() == null) {
								info.setLongintime(0);
							}else {
								info.setLongintime((int)(role.getLogintime()/1000));
							}
							return_msg.getCharacters().add(info);
						}
						oldSession.write(return_msg);
					}
					
				}
			}
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}