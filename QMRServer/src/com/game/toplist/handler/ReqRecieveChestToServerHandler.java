package com.game.toplist.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.player.structs.Player;
import com.game.toplist.bean.ChestInfo;
import com.game.toplist.manager.TopListManager;
import com.game.toplist.message.ReqRecieveChestToServerMessage;
import com.game.toplist.message.ResTopListChestInfoToClientMessage;
import com.game.utils.MessageUtil;
import com.game.command.Handler;

//领取宝箱
public class ReqRecieveChestToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqRecieveChestToServerHandler.class);

	public void action(){
		try{
			Player player = (Player)this.getParameter();
			ReqRecieveChestToServerMessage msg = (ReqRecieveChestToServerMessage)this.getMessage();
			int chestid = msg.getChestid();
			TopListManager.getInstance().recieveChest(player, chestid);
			//发送宝箱列表
			ResTopListChestInfoToClientMessage remsg = new ResTopListChestInfoToClientMessage();
			List<ChestInfo> chestlist = new ArrayList<ChestInfo>();
			chestlist = TopListManager.getInstance().getChestInfoList(player);
			remsg.setChestinfolist(chestlist);
			MessageUtil.tell_player_message(player, remsg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}