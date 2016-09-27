package com.game.activities.handler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.game.activities.message.ReqGetTailiToWorldMessage;
import com.game.command.Handler;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.recharge.QueryRecharge;
import com.game.server.WorldServer;
import com.game.utils.CodedUtil;
import com.game.utils.HttpUtil;

public class ReqGetTailiToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetTailiToWorldHandler.class);

	public void action(){
		try{
			String md5key = "R4TaB0$GfwZEx8Ri-2pWjmt#14@&@ALg";
			ReqGetTailiToWorldMessage msg = (ReqGetTailiToWorldMessage)this.getMessage();
			Player player = PlayerManager.getInstance().getPlayer(msg.getPlayerid());
			if (player != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
				int recharge = QueryRecharge.queryRecharge(player, 2, sdf.parse("20130224000000").getTime(), sdf.parse("20130225235959").getTime());
				String data = "秦美人;" + WorldServer.getInstance().getServerWeb() + ";" + player.getServer() + ";"
						+ player.getUserId() + ";" + player.getName() + ";" + recharge + ";" + msg.getName() + ";"
						+ msg.getPhone() + ";" + msg.getAddr();
				String encode = URLEncoder.encode(data, "utf-8");
				String md5sign = CodedUtil.Md5(data + md5key);
				HttpUtil.post("http://huodong.37wan.com/index.php", "c=qmr&d=" + encode + "&sign=" + md5sign);
			}
		}catch(ClassCastException e){
			log.error(e);
		} catch (UnsupportedEncodingException e) {
			log.error(e);
		} catch (Exception e) {
			log.error(e);
		}
	}
}