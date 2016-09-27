package com.game.activities.handler;

import org.apache.log4j.Logger;

import com.game.activities.script.IReceiveLiJinGift;
import com.game.command.Handler;
import com.game.player.structs.Player;
import com.game.script.manager.ScriptManager;
import com.game.script.structs.ScriptEnum;

public class ReqReceiveLiJinGiftHandler extends Handler{

	Logger log = Logger.getLogger(ReqReceiveLiJinGiftHandler.class);

	public void action() {
		try {
//			ReqReceiveLiJinGiftMessage msg = (ReqReceiveLiJinGiftMessage) this.getMessage();
			IReceiveLiJinGift script = (IReceiveLiJinGift) ScriptManager.getInstance().getScript(ScriptEnum.RECEIVELIJINGIFT);
			if (script == null) {
				log.error("公测领取礼金脚本找不到");
				return;
			}
			script.receive((Player) getParameter());
		} catch (Exception e) {
			log.error(e, e);
		}
	}
}