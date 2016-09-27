package scripts.registrar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.game.json.JSONserializable;
import com.game.player.manager.PlayerManager;
import com.game.player.message.ResScriptCommonPlayerToClientMessage;
import com.game.player.structs.Player;
import com.game.registrar.script.IRegistrar;
import com.game.utils.MessageUtil;
import com.game.utils.ScriptsUtils;


public class FirstRechargeScript implements IRegistrar {

	@Override
	public int getId() {
		return 1201;
	}
	//改为
	//美人“白夷女” 五倍收益丹*1 		40级+10紫色武器*1 									生命池*2 	内力池*2
	//             1024_1_1_0      男 钝钧 101311_1_1_0_10_11|1;12|1;8|1;13|1;14|1;9|1   	30301_2_1_0	30302_2_1_0
	//			   1024_1_1_0      女 寒影 101321_1_1_0_10_11|1;12|1;8|1;13|1;14|1;9|1   	30301_2_1_0	30302_2_1_0	
	//发送女性奖励
	@Override
	public String getFirstReward(Player player) {
		return "101321_1_1_0_10_11|1;12|1;8|1;13|1;14|1;9|1" +
				",30301_2_1_0,30302_2_1_0,1024_1_1_0";
	}
	//发送男性奖励
	@Override
	public String getCommonReward(Player player) {
		return "101311_1_1_0_10_11|1;12|1;8|1;13|1;14|1;9|1" +
				",30301_2_1_0,30302_2_1_0,1024_1_1_0";
	}
	
	@Override
	public void giveFirstReward(Player player) {
		
	}

	@Override
	public void giveCommonReward(Player player) {
		
	}
	@Override
	public void callWorld(Player player) {
		if(player.getReceivedFirstRecharge()==0){ //未领取过 调用世界服脚本判断是否可领取
			ScriptsUtils.callWorld(35, "queryRecharge", ""+1201, "worldCallback", ""+player.getId(), ""+0);
		}else{ //已经领取直接返回
			Map<String, String> resmap = new HashMap<String, String>();
			ResScriptCommonPlayerToClientMessage resclientmsg = new ResScriptCommonPlayerToClientMessage();
			resclientmsg.setScriptid(1201);
			resclientmsg.setType(6);
			resmap.put("hasreceive", player.getReceivedFirstRecharge()==1? "0":"1");
			resmap.put("canreceive", "0");
			resclientmsg.setMessageData(JSONserializable.toString(resmap));
			MessageUtil.tell_player_message(player, resclientmsg);
		}
	}
	@Override
	public void worldCallback(List<Object> paras) {
		if(paras.size()>1){
			String playerid = paras.get(0).toString();
			String result = paras.get(1).toString();
			Player player = PlayerManager.getInstance().getPlayer(Long.valueOf(playerid));
			if(player!=null){
				Map<String, String> resmap = new HashMap<String, String>();
				ResScriptCommonPlayerToClientMessage resclientmsg = new ResScriptCommonPlayerToClientMessage();
				resclientmsg.setScriptid(1201);
				resclientmsg.setType(6);
				resmap.put("hasreceive", player.getReceivedFirstRecharge()==1? "0":"1"); //这个变量太绕了以实际效果为准
				resmap.put("canreceive", "0".equals(result)? "1":"0"); //是否可领取 result=0表示有充值可领取
				resclientmsg.setMessageData(JSONserializable.toString(resmap));
				MessageUtil.tell_player_message(player, resclientmsg);
			}
		}
	}
}
