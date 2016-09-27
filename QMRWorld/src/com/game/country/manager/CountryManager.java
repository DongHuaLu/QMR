package com.game.country.manager;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.game.country.bean.CountryMemberInfo;
import com.game.country.bean.CountryStructureInfo;
import com.game.country.message.ReqCountryStructureInfoToWoridMessage;
import com.game.country.message.ReqCountrySyncKingCityToWoridMessage;
import com.game.country.message.ResCountryStructureInfoToClientMessage;
import com.game.country.message.ResCountrySyncKingCityToGameMessage;
import com.game.country.structs.KingCity;
import com.game.guild.bean.MemberInfo;
import com.game.guild.structs.Guild;
import com.game.manager.ManagerPool;
import com.game.marriage.structs.Marriage;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerWorldInfo;
import com.game.utils.MessageUtil;
import com.game.utils.ServerParamUtil;

public class CountryManager{
	private Logger log = Logger.getLogger(CountryManager.class);
	
	private static Object obj = new Object();
	//王城管理类实例
	private static CountryManager manager;

	//王城简要信息，储存王城帮派
	public static HashMap<Integer, Long> kingcitymap = new HashMap<Integer, Long>();
	
	
	private CountryManager() {
	}

	public static CountryManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new CountryManager();
			}
		}
		return manager;
	}

	
	/**发送到前端王城信息
	 * 
	 * @param msg
	 */
	public void stReqCountryStructureInfoToWoridMessage(ReqCountryStructureInfoToWoridMessage msg) {
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		if ( player != null) {
			ResCountryStructureInfoToClientMessage cmsg = new ResCountryStructureInfoToClientMessage();
			CountryStructureInfo countryStructureInfo = new CountryStructureInfo();
			Guild guild = ManagerPool.guildWorldManager.getGuild(msg.getGuildid());
			if (guild != null) {
				long kingid= 0;
				int  kingsex = 0;
				for (MemberInfo member : guild.getMemberinfoHashMap().values()) {
					if (member.getGuildPowerLevel() < 6 && member.getGuildPowerLevel() > 0) {
						CountryMemberInfo cinfo = new CountryMemberInfo();
						cinfo.setPlayerid(member.getUserId());
						cinfo.setPlayername(member.getUserName());
						cinfo.setPost(member.getGuildPowerLevel());
						PlayerWorldInfo winfo = ManagerPool.playerManager.getPlayerWorldInfo(member.getUserId());
						cinfo.setSex(winfo.getSex());
						countryStructureInfo.getCountrymemberInfolist().add(cinfo);
						countryStructureInfo.setGuildid(msg.getGuildid());
						countryStructureInfo.setGuildname(guild.getGuildInfo().getGuildName());
						if (member.getGuildPowerLevel() == 1) {
							kingid = member.getUserId();
							kingsex  = winfo.getSex();
						}
						
					}
				}
				
				Marriage marriage = ManagerPool.marriageManager.getPlayerMarriage(kingid);
				if (marriage != null) {
					CountryMemberInfo minfo = new CountryMemberInfo();
					minfo.setPlayerid(marriage.getSpouse(kingid).getPlayerid());
					minfo.setPlayername(marriage.getSpouse(kingid).getName());
					
					if (kingsex == 1) {
						minfo.setSex(2);
						minfo.setPost((byte) 102);	//王后
					}else{
						minfo.setSex(1);
						minfo.setPost((byte) 101);	//亲王
					}
					countryStructureInfo.getCountrymemberInfolist().add(minfo);
				}

			}
			countryStructureInfo.setSiegetime(msg.getSiegetime());
			cmsg.setCountrystructureInfo(countryStructureInfo);
			MessageUtil.tell_player_message(player, cmsg);
		}
	}

	
	/**同步世界服务器王城信息,并且群发
	 * 
	 * @param msg
	 */
	public void stReqCountrySyncKingCityToWoridMessage(ReqCountrySyncKingCityToWoridMessage msg) {
		kingcitymap.put(msg.getCountryid(), msg.getGuildid());
		
		ResCountrySyncKingCityToGameMessage smsg = new ResCountrySyncKingCityToGameMessage();
		smsg.setCountryid(msg.getCountryid());
		smsg.setGuildid(msg.getGuildid());
		MessageUtil.send_to_game(smsg);	//群发到所有游戏服务器
	}
	
	
	
	/**载入所有国家王城简要信息
	 * 
	 */
	public void loadkingcity(){
		for (int i = 0; i < 10; i++) {
			if (ServerParamUtil.getImportantParamMap().containsKey(ServerParamUtil.KINGCITYWAR+i)) {
				String dataString=ServerParamUtil.getImportantParamMap().get(ServerParamUtil.KINGCITYWAR+i);
				KingCity jskingcity = JSON.parseObject(dataString, KingCity.class);
				kingcitymap.put(i, jskingcity.getGuildid());
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
}
