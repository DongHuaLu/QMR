package com.game.biwudao.manager;

import org.apache.log4j.Logger;

import com.game.biwudao.message.ReqBiWuDaoSelectToGameMessage;
import com.game.biwudao.message.ResBiWuDaoTotalGainToClientMessage;
import com.game.languageres.manager.ResManager;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.utils.MessageUtil;
import com.game.utils.ScriptsUtils;
import com.game.utils.TimeUtil;

public class BiWuDaoManager {
	protected Logger log = Logger.getLogger(BiWuDaoManager.class);
	//玩家管理类实例
	private static BiWuDaoManager manager;
	private static Object obj = new Object();
	private BiWuDaoManager(){}
	
	public static BiWuDaoManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new BiWuDaoManager();
			}
		}
		return manager;
	}
	
	//设定夺旗后最大冷却时间(秒)
	public static int BIWUDAO_FLAGCOOLDOWNMAX = 180 ;
	//比武岛地图ID
	public static int BIWUDAO_MAPID = 42123 ;
	//击杀玩家获得军功上限
	public static int BIWUDAO_RAMKMAX = 300 ;
	//活动总时间（秒）
	public static int BIWUDAO_TIME_MAX = 1800 ;
	//开区指定天数后开放
	public static int OPEN_DAY = 0 ;
	//夺旗后的BUFF
	public static int BIWUDAO_BUFF = 9146 ;
	//比武岛旗帜ID
	public static int BIWUDAO_FLAG = 40000 ;
	
	
	//比武岛状态： 0没有活动，1活动进行中，2活动结束
	private int biwudaostate;
	
	//比武岛开始时间记录 用来做倒计时(秒)
	private int biwudaocountdown;
	
	//比武岛占领者帮会ID
	private long biwudaoguildid;
	//比武岛占领者帮会名字
	private String biwudaoguildname;
	//比武岛夺旗冷却时间（秒）
	private long flagcooldown;
	
	
	public int getBiwudaostate() {
		return biwudaostate;
	}

	public void setBiwudaostate(int biwudaostate) {
		this.biwudaostate = biwudaostate;
	}

	public int getBiwudaocountdown() {
		return biwudaocountdown;
	}

	public void setBiwudaocountdown(int biwudaocountdown) {
		this.biwudaocountdown = biwudaocountdown;
	}

	public long getBiwudaoguildid() {
		return biwudaoguildid;
	}

	public void setBiwudaoguildid(long biwudaoguildid) {
		this.biwudaoguildid = biwudaoguildid;
	}

	public long getFlagcooldown() {
		return flagcooldown;
	}

	public void setFlagcooldown(long flagcooldown) {
		this.flagcooldown = flagcooldown;
	}

	public String getBiwudaoguildname() {
		return biwudaoguildname;
	}

	public void setBiwudaoguildname(String biwudaoguildname) {
		this.biwudaoguildname = biwudaoguildname;
	}
	
	
	
	
	/**进入或者离开比武岛
	 * 
	 * @param parameter
	 * @param msg
	 */
	public void stReqBiWuDaoSelectToGameMessage(Player player,ReqBiWuDaoSelectToGameMessage msg) {
		if (msg.getType() == 0) {
			ScriptsUtils.call(ScriptEnum.BIWUDAO, "biwudaoentr",player);	//进入
		}else if (msg.getType() == 1) {
			ScriptsUtils.call(ScriptEnum.BIWUDAO, "biwudaoleave",player);	//离开
		}
		
	}




	
	
	
	/**
	 * 获取比武岛活动时间信息
	 * @param 
	 * @return
	 */
	public String getbiwudaotimeinfo(){
		long week = TimeUtil.getDayOfWeek(System.currentTimeMillis());
		long time =0;
		int day = TimeUtil.getOpenAreaDay();
		if (day >= OPEN_DAY) {
			if (week > 3 && week <= 7) {
				if (getBiwudaostate() == 0) {
					time = TimeUtil.getSoonWeek(System.currentTimeMillis(),0);
				}else if (getBiwudaostate() == 2){
					time = TimeUtil.getSoonWeek(System.currentTimeMillis(),3);
				}else if (getBiwudaostate() == 1){
					return ResManager.getInstance().getString("今日比武岛活动正在进行中");
				}
			}else {
				if (getBiwudaostate() == 0) {
					time = TimeUtil.getSoonWeek(System.currentTimeMillis(),3);
				}else if (getBiwudaostate() == 2){
					time = TimeUtil.getSoonWeek(System.currentTimeMillis(),0);
				}else if (getBiwudaostate() == 1){
					return ResManager.getInstance().getString("今日比武岛活动正在进行中");
				}
			}
			long curday = TimeUtil.getDayOfMonth(System.currentTimeMillis());
			int mday = (int)TimeUtil.getDayOfMonth(time);
			if (curday == mday) {
				if (getBiwudaostate() == 0) {
					return String.format(ResManager.getInstance().getString("比武岛活动时间：今晚21时0分"));
				}
			}
			
		}else {
			int sday = OPEN_DAY - day;
			long time_a = TimeUtil.getSoonWeek(System.currentTimeMillis()+(sday*24*60*60*1000),3);
			long time_b = TimeUtil.getSoonWeek(System.currentTimeMillis()+(sday*24*60*60*1000),0);
			if (time_b < time_a) {
				time = time_b;
			}else{
				time = time_a;
			}
		}
		
		long mday = TimeUtil.getDayOfMonth(time);
		long month = TimeUtil.getMonth(time)+1;
		return String.format(ResManager.getInstance().getString("下次开放时间：%d月%d日21时0分"), month,mday);
	}
	
	
	
	/**发送累计获得的数值
	 * 
	 * @param player
	 */
	public void totalGainToClien(Player player ){
		ResBiWuDaoTotalGainToClientMessage cmsg = new ResBiWuDaoTotalGainToClientMessage();
		cmsg.setTotalBox(player.getBiwudaototalBox());
		cmsg.setTotalexp(player.getBiwudaototalexp());
		cmsg.setTotalrank(player.getBiwudaototalrank());
		cmsg.setTotalzhenqi(player.getBiwudaototalzhenqi());
		MessageUtil.tell_player_message(player, cmsg);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
