package scripts.gm;

import com.alibaba.fastjson.JSON;
import com.game.data.bean.Q_activitiesBean;
import com.game.data.manager.DataManager;
import com.game.db.bean.GuildBean;
import com.game.gm.script.IGmCommandScript;
import com.game.guild.bean.MemberInfo;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.structs.Guild;
import com.game.manager.ManagerPool;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.recharge.QueryRecharge;
import com.game.script.structs.ScriptEnum;
import com.game.toplist.manager.TopListManager;
import com.game.toplist.structs.GestTop;
import com.game.toplist.structs.TopPlayer;
import com.game.utils.MessageUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.log4j.Logger;


public class GmCommandScript implements IGmCommandScript {

	protected Logger log = Logger.getLogger(GmCommandScript.class);
	
	@Override
	public int getId() {
		return ScriptEnum.GM_COMMAND;
	}

	//格式为&runonworld 命令 参数1 ...
	@Override
	public void doCommand(Player player, String command) {
		//分割指令
		String[] strs = command.split(" ");
		for (int i = 0; i < strs.length; i++) {
			log.error(i + ":" + strs[i]);
		}
		if("reloadmap".equalsIgnoreCase(strs[1])){
			
		}
		else if("saveallguild".equalsIgnoreCase(strs[1])){
			log.error("开始执行saveallguild");
			GuildWorldManager.getInstance().saveAllGuild();
			log.error("执行saveallguild成功");
		}else if ("DoubleTimenull".equalsIgnoreCase(strs[1])) {
			ManagerPool.monsterManager.setDaguaiDoubleTime(null);
			log.error("执行DoubleTimenull成功");
		}else if ("deleteguild".equalsIgnoreCase(strs[1])) {
			long guildId = Long.parseLong(strs[2]);
			Guild guild = ManagerPool.guildWorldManager.getGuild(guildId);
			log.error("获得公会：" + guild);
			ManagerPool.guildWorldManager.deleteGuildFromAll(guild, player, true);
			log.error("执行deleteguild成功");
		}else if ("deleteskilllevel".equalsIgnoreCase(strs[1])) {
			long playerId = Long.parseLong(strs[2]);
			TopPlayer topPlayer = TopListManager.getTopplayerMap().get(playerId);
			if (topPlayer == null) {
				return ;
			}
			GestTop gestTop = new GestTop(playerId, 0, 0);
			GestTop oldTopData = (GestTop)topPlayer.getTopData(gestTop);
			gestTop.setGestlv(25);
			gestTop.setGesttime(oldTopData.getGesttime());
			TopListManager.getInstance().updateTopData(gestTop);
		}else if("deleteguildmemeber".equalsIgnoreCase(strs[1])){
			//台湾22 5635499827789462605L  6281768666015384L = 賀和雅
			Long guildid = new Long(5635499827789462605L);
			Long roleid = new Long(6281768666015384L);
			//key roleid
			Guild guild = GuildWorldManager.getInstance().getGuild(guildid);
			if(guild!=null){
				log.error(guild.getGuildInfo().getGuildName()+" found");
				HashMap<Long, MemberInfo> members = guild.getMemberinfoHashMap();
				if(members.containsKey(roleid)){
					log.error("("+members.get(roleid)+")"+roleid+" found");
					members.remove(roleid);
				}
			}
		}
	}
	
	public void cleanActivites(List<Object> objects){
		long playerId = Long.parseLong((String)objects.get(0));
		
		Map<String, String> playerparams = QueryRecharge.queryPlayerAwardParam(playerId);
		String cleankey = "CLEANACTIVES20121104";
		if(playerparams.containsKey(cleankey)){
			return;
		}else{
			playerparams.put(cleankey, String.valueOf(System.currentTimeMillis()));
		}
		
		ListIterator<Q_activitiesBean> listIterator = DataManager.getInstance().q_activitiesContainer.getList().listIterator();
		while (listIterator.hasNext()) {
			Q_activitiesBean q_activitiesBean = listIterator.next();
			if(q_activitiesBean.getQ_id() < 100000 || q_activitiesBean.getQ_id() > 110000){
				continue;
			}
			String key = "";
			if (q_activitiesBean.getQ_titleimage().equalsIgnoreCase("0")) {
				key = q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id();
			} else {
				key = q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id() + "_" + playerId;
			}
			if(playerparams.containsKey(key)){
				playerparams.remove(key);
				
			}
		}
		
		QueryRecharge.updatePlayerParams(playerId, playerparams);
	}

	public void loadoneguild(List<Object> param){
		try {
			long playerid = Long.parseLong((String)param.get(0));
			long guildid = Long.parseLong((String)param.get(1));
			Player player = PlayerManager.getInstance().getPlayer(playerid);
			GuildWorldManager.getInstance().loadallGuild();
			Guild guild = GuildWorldManager.getInstance().getGuild(guildid);
			if (guild != null) {
				guild.setCountry(3);
				guild.getGuildInfo().setAutoGuildAgreeAdd((byte) 1);
				MemberInfo memberInfo = guild.getMemberinfoHashMap().get(8814300544099358L);
				if (memberInfo != null) {
					memberInfo.setGuildPowerLevel((byte) 1);
				}
			}
			MessageUtil.notify_player(player, Notifys.SUCCESS, "重新加载GUILD成功");
		} catch (Exception e) {
			log.error(e,e);
		}
	}
}
