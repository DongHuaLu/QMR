package com.game.shortcut.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.shortcut.message.MoveShortCutMessage;
import com.game.command.Handler;

public class MoveShortCutHandler extends Handler{

	Logger log = Logger.getLogger(MoveShortCutHandler.class);

	public void action(){
		try{
			MoveShortCutMessage msg = (MoveShortCutMessage)this.getMessage();

			ManagerPool.shortCutManager.moveShortCut((Player)this.getParameter(), msg.getShortcutId(), msg.getShortcutGrid());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}