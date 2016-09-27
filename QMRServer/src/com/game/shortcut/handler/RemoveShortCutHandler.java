package com.game.shortcut.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.shortcut.message.RemoveShortCutMessage;

public class RemoveShortCutHandler extends Handler{

	Logger log = Logger.getLogger(RemoveShortCutHandler.class);

	public void action(){
		try{
			RemoveShortCutMessage msg = (RemoveShortCutMessage)this.getMessage();
			ManagerPool.shortCutManager.removeShortCut((Player)this.getParameter(), msg.getShortcutId());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}