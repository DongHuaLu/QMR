package com.game.server.gmchat.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.game.chat.manager.WorldChatManager;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.server.WorldServer;
import com.game.utils.HttpUtil;

/**
 * 
 * @author 赵聪慧
 * @2012-10-11 下午10:30:25
 */
public class GmChatManager {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GmChatManager.class);

//	private Logger chatLog=Logger.getLogger("CHATLOG");
	private static String url="/gmchat";
	private static GmChatManager instance=new GmChatManager();
	private static ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(5);
	private static ArrayList<String> faildMsg=new ArrayList<String>();
	public static GmChatManager getInstance(){
		return instance;
	}
	private GmChatManager(){
		final ArrayList<String> aaa=new ArrayList<String>();
		synchronized (faildMsg) {
			aaa.addAll(faildMsg);
			aaa.clear();
		}
		
		newScheduledThreadPool.schedule(new Runnable() {
			@Override
			public void run() {
				for (String string : aaa) {
					sendMsg(string);
				}
			}
		}, 1, TimeUnit.SECONDS);
	}
	
	public int receivedMsg(IoSession iosession,HashMap<String, String> map){
		String roleid = map.get("roleid");//接收者ID
		String content=map.get("content");//内容
		String gmname=map.get("gmname");//客服编号或者名称
		String time=map.get("ts");//时间戳
		if(StringUtils.isBlank(roleid)||StringUtils.isBlank(content)||StringUtils.isBlank(gmname)){
			//参数不正确
			return -1;
		}
		Player player = PlayerManager.getPlayers().get(Integer.parseInt(roleid));
		if(player==null){
			//玩家不在线
			return -2;
		}
		WorldChatManager.getInstance().gmToRole(player, gmname, content,time);
		return 1;
	}
	
	public void sendMsg(final String content){	
		if(StringUtils.isBlank(content)){
			return;
		}
		newScheduledThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				String ip = WorldServer.getGmchatConfig().getIp();
				int port = WorldServer.getGmchatConfig().getPort();
				try {
					if(!HttpUtil.post("http://"+ip+":"+port+url, content)){
						synchronized (faildMsg) {
							faildMsg.add(content);
						}
					}
				} catch (Exception e) {
					logger.error(e,e);
				}
			}
		});
	}
}
