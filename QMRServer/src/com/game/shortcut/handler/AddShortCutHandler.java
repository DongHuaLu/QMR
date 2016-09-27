package com.game.shortcut.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.shortcut.message.AddShortCutMessage;

public class AddShortCutHandler extends Handler{

	Logger log = Logger.getLogger(AddShortCutHandler.class);

	public void action(){
		try{
			AddShortCutMessage msg = (AddShortCutMessage)this.getMessage();
			ManagerPool.shortCutManager.addShortCut((Player)this.getParameter(), msg.getShortcutType(), msg.getShortcutSource(), msg.getShortcutSourceModel(), msg.getShortcutGrid());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}