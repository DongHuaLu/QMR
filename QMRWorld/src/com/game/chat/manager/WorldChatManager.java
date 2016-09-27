package com.game.chat.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.game.chat.bean.GoodsInfoRes;
import com.game.chat.message.ChatResponseWCMessage;
import com.game.guild.bean.MemberInfo;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.structs.Guild;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.server.gmchat.manager.GmChatManager;
import com.game.team.bean.TeamInfo;
import com.game.team.bean.TeamMemberInfo;
import com.game.team.manager.TeamManager;
import com.game.utils.CodedUtil;
import com.game.utils.HttpUtil;
import com.game.utils.MessageUtil;

/**
 * 世界服聊天管理器
 *
 * @author 赵聪慧
 *
 */
public class WorldChatManager {

	private Logger log = Logger.getLogger(WorldChatManager.class);
	private Logger chatLog = Logger.getLogger("CHATLOG");
	public static final byte CHATTYPE_ROLE = 2;
	public static final byte CHATTYPE_TEAM = 3;
	public static final byte CHATTYPE_GROUP = 4;
//	private static final byte CHATTYPE_SYSTEM=6;
	public static final byte CHATTYPE_GM=7;
	private static WorldChatManager instance = new WorldChatManager();

	public static WorldChatManager getInstance() {
		return instance;
	}

	/**
	 *
	 *
	 * @param sender	发送者ID
	 * @param name	发送者名字
	 * @param kinglv	王城等级
	 * @param country	国家id
	 * @param chattype	聊天类型
	 * @param condition	聊天内容
	 * @param list	物品列表
	 * @param isProhibit 是否禁言
	 */
	public void chat(long sender, String name, int kinglv, int country, String receiver, int chattype, String condition, List<GoodsInfoRes> list, boolean isProhibit, boolean isgm, short sendervip, int senderwebvip, short receiverVIPtype, int receiverwebvip) {
		Player player = PlayerManager.getInstance().getPlayer(sender);
		if (player == null) {
			log.error("发送者不在线");
			return;
		}
		chatLog.info(isProhibit + "\t" + player.getName() + "\t" + receiver + "\t" + chattype + "\t" + condition + "\t" + JSONserializable.toString(list));
		switch (chattype) {
			case CHATTYPE_ROLE://私聊
				chatToRole(player, kinglv, country, receiver, chattype, list, condition, isProhibit, isgm, sendervip, senderwebvip, receiverVIPtype, receiverwebvip);
				break;
			case CHATTYPE_TEAM://队伍
				chatToTeam(player, kinglv, country, chattype, list, condition, isProhibit, isgm, sendervip, senderwebvip);
				break;
			case CHATTYPE_GROUP://帮会
				chatToGroup(player, kinglv, country, chattype, list, condition, isProhibit, isgm, sendervip, senderwebvip);
				break;
			case CHATTYPE_GM:
				chatToGm(player, kinglv, country, chattype, list, condition, isProhibit, isgm);
				break;
			default:
				break;
		}
	}

	/*
	 * 私聊
	 */
	private void chatToRole(Player source, int kinglv, int country, String receiver, int chattype, List<GoodsInfoRes> other, String condition, boolean isProhibit,boolean isgm,short sendervip, int senderwebvip, short receiverVIPtype, int receiverwebvip) {
		Player player = PlayerManager.getInstance().getOnlinePlayerByName(receiver);
		if (player == null) {
			MessageUtil.notify_player(source, Notifys.CHAT_ROLE, receiver + ResManager.getInstance().getString("不在线"));
			return;
		}
		try{
			if(StringUtils.isBlank(player.getName()) || player.getLevel()<=0){
				log.error("未同步角色("+player.getId()+")信息 不予发送聊天消息");
				return;
			}
		}catch (Exception e) {
			log.error(e, e);
		}
		ChatResponseWCMessage msg = new ChatResponseWCMessage();
		msg.setChater(source.getId());
		msg.setChaterlevel(source.getLevel());
		msg.setChatername(source.getName());
		msg.setChaterkinglv(kinglv);
		msg.setCountry(country);
		msg.setChattype(chattype);
		msg.setCondition(condition);
		msg.setReceiver(player.getId());
		msg.setReceivername(player.getName());
		msg.setReceiverlevel(player.getLevel());
		msg.setReceiverviptype(receiverVIPtype);
		msg.setReceiverwebvip(receiverwebvip);
		msg.setOther(other);
		msg.setIsgm((byte) (isgm?1:0));
		msg.setViptype(sendervip);
		msg.setWebvip(senderwebvip);
		if (isProhibit) {
			msg.getRoleId().clear();
			msg.setChaterkinglv(player.gKingLevel());
			msg.setCountry(player.getCountry());
			MessageUtil.tell_player_message(source, msg);
			ManagerPool.friendManager.addRelation(source.getId(), (byte) 3, player.getId(), true);
		} else {
			MessageUtil.tell_player_message(player, msg);
			msg.getRoleId().clear();
			msg.setChaterkinglv(player.gKingLevel());
			msg.setCountry(player.getCountry());
			MessageUtil.tell_player_message(source, msg);
//			msg.getRoleId().add(player.getId());
//			msg.getRoleId().add(source.getId());
//			MessageUtil.send_to_gate(msg);
			ManagerPool.friendManager.addRelation(source.getId(), (byte) 3, player.getId(), true);
			ManagerPool.friendManager.addRelation(player.getId(), (byte) 3, source.getId(), true);
		}
	}

	/**
	 * 发送到队伍
	 *
	 * @param source
	 * @param kinglv	王城等级
	 * @param country	国家id
	 * @param chattype
	 * @param other
	 * @param condition
	 */
	private void chatToTeam(Player source, int kinglv, int country, int chattype, List<GoodsInfoRes> other, String condition, boolean isProhibit, boolean isgm, short vip, int webvip) {
		long teamid = source.getTeamid();
		TeamInfo teamInfo = TeamManager.getTeamlist().get(teamid);
		if (teamInfo == null) {
			// 还没有加入队伍
			return;
		}
		ChatResponseWCMessage msg = new ChatResponseWCMessage();
		msg.setChater(source.getId());
		msg.setChatername(source.getName());
		msg.setChaterkinglv(kinglv);
		msg.setCountry(country);
		msg.setChaterlevel(source.getLevel());
		msg.setChattype(chattype);
		msg.setCondition(condition);
		msg.setOther(other);
		msg.setIsgm((byte) (isgm?1:0));
		msg.setViptype(vip);
		msg.setWebvip(webvip);
		if (isProhibit) {
			MessageUtil.tell_player_message(source, msg);
		} else {
			List<TeamMemberInfo> memberinfo = teamInfo.getMemberinfo();
			for (TeamMemberInfo teamMemberInfo : memberinfo) {
				msg.getRoleId().add(teamMemberInfo.getMemberid());
			}
			if (msg.getRoleId().size() <= 0) {
				return;
			}
			MessageUtil.send_to_gate(msg);
		}
	}
	/**
	 * 发送到帮会
	 * @param source
	 * @param kinglv
	 * @param country
	 * @param chattype
	 * @param other
	 * @param condition
	 * @param isProhibit
	 * @param isgm
	 */
	private void chatToGroup(Player source, int kinglv, int country, int chattype, List<GoodsInfoRes> other, String condition, boolean isProhibit, boolean isgm, short vip, int webvip) {

		long guildid = source.getGuildid();
		Guild guild = GuildWorldManager.getInstance().getGuild(guildid);
		if (guild == null) {
			return;
		}
		ChatResponseWCMessage msg = new ChatResponseWCMessage();
		msg.setChater(source.getId());
		msg.setChatername(source.getName());
		msg.setChaterkinglv(kinglv);
		msg.setCountry(country);
		msg.setChaterlevel(source.getLevel());
		msg.setChattype(chattype);
		msg.setCondition(condition);
		msg.setOther(other);
		msg.setIsgm((byte) (isgm?1:0));
		msg.setViptype(vip);
		msg.setWebvip(webvip);
		if (isProhibit) {
			MessageUtil.tell_player_message(source, msg);
		} else {
			HashMap<Long, MemberInfo> memberinfoHashMap = guild.getMemberinfoHashMap();
			Set<Long> keySet = memberinfoHashMap.keySet();
			for (Long roleId : keySet) {
				Player player = PlayerManager.getInstance().getPlayer(roleId);
				if (player != null) {
					msg.getRoleId().add(roleId);
				}
			}
			if (msg.getRoleId().size() <= 0) {
				return;
			}
			MessageUtil.send_to_gate(msg);
		}
	}
	
	private void chatToGm(Player source,int kinglv, int country, int chattype, List<GoodsInfoRes> other, String content, boolean isProhibit,boolean isgm){
		if(content==null||content.equals("")){
			//内容为空
			return;
		}
//		PlayerWorldInfo worldInfo = PlayerManager.getInstance().getPlayerWorldInfo(source.getId());
		
		StringBuffer buff=new StringBuffer();
		buff.append(source.getId()).append("\f");	
		buff.append(source.getName()).append("\f");
		buff.append(source.getServer()).append("\f");
		buff.append(source.getUserId()).append("\f");
		buff.append(source.getVipid()).append("\f");
		buff.append(source.getMap()).append("\f");
		buff.append(source.getLine()).append("\f");
		buff.append(source.getLevel()).append("\f");
		buff.append(source.getIpString()).append("\f");
		buff.append(System.currentTimeMillis()).append("\f");
		buff.append(isProhibit).append("\f");
		buff.append(content).append("\f");
		String createUrlParam = HttpUtil.createUrlParam("context",CodedUtil.encodeBase64(buff.toString()));
		GmChatManager.getInstance().sendMsg(createUrlParam);		
	}
	
	public void gmToRole(Player receiver,String source,String content, String time){
		ChatResponseWCMessage msg = new ChatResponseWCMessage();
		msg.setChater(-2);
		msg.setChatername(source);
		msg.setChaterkinglv(-1);
		msg.setCountry(-1);
		msg.setChaterlevel(-1);
		msg.setChattype(7);
		msg.setCondition(content);
//		msg.setOther(other);
		msg.setIsgm((byte)1);
		msg.getRoleId().add(receiver.getId());
		MessageUtil.send_to_gate(msg);
	}
	
}
