package com.game.gm.handler;

import org.apache.log4j.Logger;

import com.game.chat.manager.WorldChatManager;
import com.game.command.Handler;
import com.game.gm.message.GmCommandToWorldMessage;
import com.game.gm.script.IGmCommandScript;
import com.game.login.message.ResPlayerNonageToGameMessage;
import com.game.manager.ManagerPool;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.User;
import com.game.script.structs.ScriptEnum;
import com.game.server.WorldServer;
import com.game.systemgrant.manager.SystemgrantManager;
import com.game.utils.MessageUtil;

public class GmCommandToWorldHandler extends Handler{

	Logger log = Logger.getLogger(GmCommandToWorldHandler.class);

	public void action(){
		try{
			GmCommandToWorldMessage msg = (GmCommandToWorldMessage)this.getMessage();
			//检查Gm指令
			if (msg.getCommand() == null || ("").equals(msg.getCommand())) {
				return;
			}
			//分割指令
			String[] strs = msg.getCommand().split(" ");
			
			if("&fangchenmi".equalsIgnoreCase(strs[0])){
				Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerId());
				User user = PlayerManager.getUserTimes().get(player.getUserId());
				if(user==null || user.getState()==2) return;
				user.setOnline((long)Integer.parseInt(strs[1]) * 1000);
				
				//同步到游戏服务器
				ResPlayerNonageToGameMessage nonagemsg = new ResPlayerNonageToGameMessage();
				nonagemsg.setPlayerId(player.getId());
				nonagemsg.setNonage(ManagerPool.playerManager.getUserNonage(user));
				MessageUtil.send_to_game(player, nonagemsg);
			}
			else if("&conserve".equalsIgnoreCase(strs[0])){
				WorldServer.getInstance().setCloseTime(System.currentTimeMillis() + Long.parseLong(strs[1]) * 1000);
			}
			else if("&kick".equalsIgnoreCase(strs[0])){
				ManagerPool.playerManager.kickPlayer(strs[1]);
			}
			else if("&worldscript".equalsIgnoreCase(strs[0])){
				ManagerPool.scriptManager.reload(Integer.parseInt(strs[1]), msg.getPlayerId());
			}
			else if ("&worldloadscript".equalsIgnoreCase(strs[0])) {
				ManagerPool.scriptManager.load(strs[1], msg.getPlayerId());
			}
			else if("&SHOWMESSAGE".equalsIgnoreCase(strs[0])){
				MessageUtil.RECORD_PLAYER.put(msg.getPlayerId(), 1);
			}
			else if("&gmchat".equalsIgnoreCase(strs[0])){
				Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerId());
				WorldChatManager.getInstance().chat(player.getId(),player.getName(), 1,1, "gm", 7, strs[1], null, false, false,(short)0,0,(short)0,0);
			}else if ("&reload".equalsIgnoreCase(strs[0])) {
//				Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerId());
//				ReLoadManager.getInstance().reLoad(strs[1],player.getId());
			}
			else if ("&clearupSpirit".equalsIgnoreCase(strs[0])) {
				Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerId());
				ManagerPool.spiritTreeManager.clearupSpiritdata();
			}
			
//			else if("&recharge".equalsIgnoreCase(strs[0])){
//				RechargeEntry entry=new RechargeEntry(strs[1]);
//				RechargeManager.getInstance().reacharge(entry);
//			}
			
			IGmCommandScript script = (IGmCommandScript) ManagerPool.scriptManager.getScript(ScriptEnum.GM_COMMAND);
			if (script != null) {
				try {
					script.doCommand(null, msg.getCommand());
				} catch (Exception e) {
					log.error(e, e);
				}
			} else {
				//log.error("GM命令脚本不存在！");
			}
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}