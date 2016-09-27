package scripts.player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.game.chat.log.ChatAutoJinYanLog;
import com.game.chat.manager.ChatManager;
import com.game.chat.struts.ChatCountBean;
import com.game.dblog.LogService;
import com.game.languageres.manager.ResManager;
import com.game.player.manager.PlayerManager;
import com.game.player.script.IPlayerChatScript;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.utils.StringCompar;

public class PlayerChatScript implements IPlayerChatScript {

	private static Logger log = Logger.getLogger(PlayerChatScript.class);
	
	/**
	 * 触发自动禁言最高等级
	 */
	private int AUTOPROHIBIT_LEVEL=40;
	
	/**
	 * 触发自动禁言聊天内容长度
	 */
	private int AUTOPROHIBIT_LENGTH=15;
	
	/**
	 * 自动禁言记录时长
	 */
	private int AUTOPROHIBIT_TIME=5*60*1000;
	
	/**
	 * 禁言时长
	 */
	private int AUTOPROHIBIT_PROHIBITTIME=20*60*1000;
	/**
	 * 自动禁言相似重复次数
	 */
	private int AUTOPROHIBIT_COUNT=4;
	
	/**
	 * 自动禁言相似度
	 */
	private int AUTOPROHIBIT_SEMBLANCE=80;
	
	/**
	 * 自动禁言关键词列表
	 */
	private String[][] keywords = new String[][]{
			{ResManager.getInstance().getString("元"), ResManager.getInstance().getString("宝"), ResManager.getInstance().getString("出"), ResManager.getInstance().getString("售")}, 
			{ResManager.getInstance().getString("神"), ResManager.getInstance().getString("器"), ResManager.getInstance().getString("群"), ResManager.getInstance().getString("Y"), ResManager.getInstance().getString("Q")}, 
			{ResManager.getInstance().getString("首"), ResManager.getInstance().getString("充"), ResManager.getInstance().getString("群"), ResManager.getInstance().getString("Y"), ResManager.getInstance().getString("Q")}
	};
	
	@Override
	public int getId() {
		return ScriptEnum.PLAYER_CHAT;
	}

	@Override
	public void onChat(Player player, int type, String content) {
		try {
			if(player.getLevel() >= AUTOPROHIBIT_LEVEL){
				//未达等级
				return;
			}
			if(PlayerManager.getInstance().getPlayerGmlevel(player)>0){ 
				//GM排除
				return;
			}
			HashMap<String, ChatCountBean> hashMap = player.getChatCount();
			Set<String> keySet = hashMap.keySet();
			// 找出匹配度最高的话
			StringCompar comp = new StringCompar();
			int semblance = 0;
			//相识度判断
			String samekey = "";
			for (String key : keySet) {
				float similarityRatio = comp.getSimilarityRatio(content, key) * 100;
				if (similarityRatio >= AUTOPROHIBIT_SEMBLANCE && similarityRatio > semblance) {
					semblance = (int) similarityRatio;
					samekey = key;
				}
			}
			
			boolean isProhibitChat = ChatManager.getInstance().isProhibitChat(player);
			if(!isProhibitChat){
				//移除过时语言
				Iterator<ChatCountBean> iterator = hashMap.values().iterator();
				while (iterator.hasNext()) {
					ChatCountBean chatCountBean = (ChatCountBean) iterator.next();
					if (chatCountBean.getFirstTime()!=-1 && System.currentTimeMillis() - chatCountBean.getFirstTime() > AUTOPROHIBIT_TIME){
						iterator.remove();
					}
				}
			}
			
			//是否包含禁言内容
			boolean isHaveForbid = false;
			for (int i = 0; i < keywords.length; i++) {
				boolean pass = true;
				for (int j = 0; j < keywords[i].length; j++) {
					if(!content.contains(keywords[i][j])){
						pass = false;
						break;
					}
				}
				if(pass){
					isHaveForbid = true;
					break;
				}
			}
			
			
			ChatCountBean bean = hashMap.get(samekey);
			if (bean == null) {
				//禁言中
				if (isProhibitChat) {
					//未包含关键字
					if(!isHaveForbid){
						return;
					}else{
						//禁言中时间重置 当前次数为0则置为1 避免禁言时间被重置
						if(player.getAutojinyancount()==0) { player.setAutojinyancount(1); }
						//禁言中时间重置
						prohibitPlayer(player, content);
						return;
					}
				}
				//未禁言中
				else{
					//未包含关键字
					if(!isHaveForbid){
						bean = new ChatCountBean(System.currentTimeMillis(), 1);
					}else{
						bean = new ChatCountBean(-1, 1);
					}
					hashMap.put(content, bean);
				}
			}else{
				if (isProhibitChat) {  
					//禁言中时间重置 当前次数为0则置为1 避免禁言时间被重置
					if(player.getAutojinyancount()==0) { player.setAutojinyancount(1); }
					prohibitPlayer(player, content);
					return;
				}
			}
			switch (type) {
			case 0:// 世界
				if (content.length() >= AUTOPROHIBIT_LENGTH || isHaveForbid) {
					if ((bean.getFirstTime()==-1 || System.currentTimeMillis() - bean.getFirstTime() <= AUTOPROHIBIT_TIME) && bean.getCount() >= AUTOPROHIBIT_COUNT) {
						player.setAutojinyancount(player.getAutojinyancount() + 1);
						prohibitPlayer(player, content);
					} else {
						bean.setCount(bean.getCount() + 1);
					}
				}
				break;
			case 1:// 场景
				if (content.length() >= AUTOPROHIBIT_LENGTH || isHaveForbid) {
					if ((bean.getFirstTime()==-1 || System.currentTimeMillis() - bean.getFirstTime() <= AUTOPROHIBIT_TIME) && bean.getCount() >= AUTOPROHIBIT_COUNT) {
						player.setAutojinyancount(player.getAutojinyancount() + 1);
						prohibitPlayer(player, content);
					} else {
						bean.setCount(bean.getCount() + 1);
					}
				}
				break;
			case 2:// 私聊
				if (content.length() >= AUTOPROHIBIT_LENGTH || isHaveForbid) {
					if ((bean.getFirstTime()==-1 || System.currentTimeMillis() - bean.getFirstTime() <= AUTOPROHIBIT_TIME) && bean.getCount() >= AUTOPROHIBIT_COUNT) {
						player.setAutojinyancount(player.getAutojinyancount() + 1);
						prohibitPlayer(player, content);
					} else {
						bean.setCount(bean.getCount() + 1);
					}
				}
				break;
			case 3:// 组队
				if (content.length() >= AUTOPROHIBIT_LENGTH || isHaveForbid) {
					if ((bean.getFirstTime()==-1 || System.currentTimeMillis() - bean.getFirstTime() <= AUTOPROHIBIT_TIME) && bean.getCount() >= AUTOPROHIBIT_COUNT) {
						player.setAutojinyancount(player.getAutojinyancount() + 1);
						prohibitPlayer(player, content);
					} else {
						bean.setCount(bean.getCount() + 1);
					}
				}
				break;
			case 4:// 帮会
				if (content.length() > AUTOPROHIBIT_LENGTH || isHaveForbid) {
					if ((bean.getFirstTime()==-1 || System.currentTimeMillis() - bean.getFirstTime() <= AUTOPROHIBIT_TIME) && bean.getCount() >= AUTOPROHIBIT_COUNT) {
						player.setAutojinyancount(player.getAutojinyancount() + 1);
						prohibitPlayer(player, content);
					} else {
						bean.setCount(bean.getCount() + 1);
					}
				}
				break;
			case 5:// 喇叭
				// minlength = 10;
				// needtime = 60 * 1000; //20秒
				// jytime=60*60*1000;
				// needcount = 5; //5次相同
				// if(msg.getCondition().length()<minlength){
				// return;
				// }
				// if (System.currentTimeMillis() - firsttime > needtime) {
				// // 超过时间 清除
				// hashMap.remove(msg.getCondition());
				// return;
				// }
				// if (count >=needcount) {
				// player.setProhibitChatTime(System.currentTimeMillis()+jytime);
				// } else {
				// bean.setCount(bean.getCount()+1);
				// }
				break;
			}
		} catch (Exception e) {
			log.error(e, e);
		}
	}

	private void prohibitPlayer(Player player, String content){
		long nowend = player.getStartProhibitChatTime()+player.getProhibitChatTime(); //角色当前禁言结束时间
		long now = System.currentTimeMillis();
		long targetend = now + (player.getAutojinyancount() * AUTOPROHIBIT_PROHIBITTIME); //角色本次禁言结束时间
		long jinyanlong = player.getAutojinyancount() * AUTOPROHIBIT_PROHIBITTIME; //禁言时长
		if(targetend>nowend){  //结束时间会延长的情况下 才改变禁言时间
			player.setProhibitChatTime(jinyanlong);
			player.setStartProhibitChatTime(now);
		}
		try {
			ChatAutoJinYanLog log = new ChatAutoJinYanLog();
			log.setContent(content);
			log.setJiyanlong(player.getProhibitChatTime());
			log.setReason(1);
			log.setRoleid(player.getId());
			log.setSid(player.getServerId());
			log.setStartTime(player.getStartProhibitChatTime());
			LogService.getInstance().execute(log);
		} catch (Exception e) {
			log.error(e, e);
		}
	}
	
}
