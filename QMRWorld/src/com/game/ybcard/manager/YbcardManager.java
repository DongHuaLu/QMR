package com.game.ybcard.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.game.db.bean.ActivitySetBean;
import com.game.db.bean.GameUser;
import com.game.db.bean.YbCardBean;
import com.game.db.dao.ActivitySetDao;
import com.game.db.dao.UserDao;
import com.game.db.dao.YbCardDao;
import com.game.dblog.LogService;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.TimeUtil;
import com.game.ybcard.log.YbcaedLog;
import com.game.ybcard.message.ReqYBCardToWorldMessage;
import com.game.ybcard.message.ResYBCardAddYBPlayerToGameMessage;
import com.game.ybcard.message.ResYBCardNoticeToGameMessage;
import com.game.ybcard.message.ResYBCardReceiveToGameMessage;
import com.game.ybcard.message.ResYBCardToClientMessage;


public class YbcardManager {
	Logger log = Logger.getLogger(YbcardManager.class);
	private static Object obj = new Object();
	//玩家管理类实例
	private static YbcardManager manager;


	public static YbcardManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new YbcardManager();
			}
		}
		return manager;
	}

	//数据库连接
	private YbCardDao ybcardDao = new YbCardDao();	//玩家公测元宝储存
	private UserDao userDao=new UserDao();			//玩家帐号
	private ActivitySetDao actDao = new ActivitySetDao();	//公测元宝卡获得常量
	
	
	
	private ConcurrentHashMap<Integer, Integer> ybcardmap = new ConcurrentHashMap<Integer, Integer>();
	private ArrayList<Integer> rndtab = new ArrayList<Integer>();
	private ActivitySetBean activitySetBean = null;
	
	
	
	private YbcardManager() {
		int minute = (int) TimeUtil.GetCurTimeInMin(2);
		for (int i = 0; i < tab.length; i++) {
			ybcardmap.put(tab[i][0] , minute+i+1);
			rndtab.add(tab[i][1]);
		}
		try {
			activitySetBean = actDao.selectsingle(1);	//如果不存在，尝试创建
			if (activitySetBean == null) {
				activitySetBean = new ActivitySetBean();
				activitySetBean.setIdx(1);
				activitySetBean.setDay_value(TimeUtil.GetCurTimeInMin(4));
				actDao.insert(activitySetBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	public  ConcurrentHashMap<Integer, Integer>  getybcardmap(){
		return ybcardmap;
	}
	
	//元宝，几率,间隔时间（分）	
	private Integer[][] tab = {
			{5000,5,2880},
			{2,4995,0},
			{1,5000,0},
	};	
	
 	
	private byte YBCRAD_TYPE = 1;	//1封测期间（抽奖），2公测期间(领取元宝)
	private int YB_DAY_MAX = 500000000;	//每日最大元宝数量
	private int YB_DAY_RND = 18000;	//每日随机元宝停止数量
	
	
	
	
	/**得到元宝卡数据
	 * 
	 * @param player
	 * @return
	 */
	public int getybcardnum(Player player){
		try {
			GameUser user = userDao.selectGameUser(Long.parseLong(player.getUserId()), player.getServer());
			if (user == null) {
				log.error(player.getId()+":USERNAME空");
				return 0;
			}	
			
			String username = user.getUsername();
			YbCardBean ybCardBean = ybcardDao.selectsingle(username);
			if (ybCardBean == null) {
				return 0;
			}
		
			return ybCardBean.getYuanbao();
		} catch (Exception e) {
			log.error(e);
		}
		return 0;
	}
	
	
	
	
	/**元宝卡操作
	 * 
	 * @param msg
	 * @throws SQLException 
	 */

	public void stReqYBCardToWorldMessage(ReqYBCardToWorldMessage msg) throws SQLException {
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		if (player != null) {
			long day = TimeUtil.GetCurTimeInMin(4);
			ResYBCardToClientMessage cmsg = new ResYBCardToClientMessage();
			cmsg.setType(msg.getType());
			GameUser user = userDao.selectGameUser(Long.parseLong(player.getUserId()), player.getServer());
			if (user == null) {
				log.error(player.getId()+":USERNAME空");
				return ;
			}
			
			byte isday= 0;
			String username = user.getUsername();
			YbCardBean ybCardBean = ybcardDao.selectsingle(username);
			boolean isinsert = false;
			if (ybCardBean == null) {
				isinsert = true;
			}

			
			if (day != activitySetBean.getDay_value() ) {	//天数不同，进行元宝清零
				activitySetBean.setDay_value(day);
				activitySetBean.setInt_value(0);
				actDao.update(activitySetBean);
				isday = 1;
			}
			
			int dataybnum = activitySetBean.getInt_value();
			if (dataybnum >= YB_DAY_MAX ) {
				ResYBCardNoticeToGameMessage nsmsg = new ResYBCardNoticeToGameMessage();
				nsmsg.setYuanbaonum(dataybnum);
				MessageUtil.send_to_game(nsmsg);
			}
			
			//----------------------------------------------打开面板(返给前端元宝数量)
			if (msg.getType() == 0 || msg.getType() == 3) { 
				if (isinsert) {
					cmsg.setYuanbaonum(0);
				}else {
					cmsg.setYuanbaonum(ybCardBean.getYuanbao());
				}
				if (msg.getType() == 0) {
					cmsg.setType(YBCRAD_TYPE);
				}else {
					cmsg.setType(msg.getType());
				}
				
				MessageUtil.tell_player_message(player, cmsg);
				
				//---------------------------------------------使用元宝卡（加元宝）	
			}else if (msg.getType() == 1 ) {
				int minute = (int) TimeUtil.GetCurTimeInMin(2);
				int ybsum=0;	//默认最低1元宝
				
				YbcaedLog yblog = new YbcaedLog();
				yblog.setUsername(username);
				yblog.setPlayerid(player.getId());
				yblog.setSid(player.getCreateServer());
				
				List<Integer> ybms = new ArrayList<Integer>();
				for (int i = 0; i < msg.getNum(); i++) {
					if (dataybnum < YB_DAY_RND) {
						int idx = RandomUtils.randomIndexByProb(rndtab);
						if(ybcardmap.containsKey(tab[idx][0]) &&  minute >= ybcardmap.get(tab[idx][0])){
							ybcardmap.put(tab[idx][0], minute + tab[idx][2]);//设置新时间
							ybsum += tab[idx][0];	//元宝数量
							dataybnum += tab[idx][0];	//元宝数量
							if(tab[idx][0] > 5){
								ybms.add(tab[idx][0]);
							}
						}else{
							ybsum += 1;
							dataybnum += 1;
						}
					}else{
						if (dataybnum > 50000  ) {
							ybsum = ybsum + 1;
							dataybnum = dataybnum + 1;
						}else {
							int newnum = RandomUtils.random(1, 4);
							ybsum = ybsum + newnum;
							dataybnum = dataybnum + newnum;
						}

					}
				}
				
				yblog.setYuanbao(ybsum);	//日志
				int status = 0;
				cmsg.setNum(ybsum);
				if (isinsert) {
					ybCardBean = new YbCardBean();
					ybCardBean.setUsername(username);
					ybCardBean.setYuanbao(ybsum);		//加元宝
					
					if(ybcardDao.insert(ybCardBean)== 0){
						log.error("元宝insert失败="+ybsum);
						stYbcaedLog(yblog,0);
						status = 0;
					}else {
						cmsg.setYuanbaonum(ybsum);
						MessageUtil.tell_player_message(player, cmsg);
						MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("使用{1}公测元宝卡，获得{2}公测礼金"),msg.getNum()+"",ybsum+"");
						status = 1;
					}
					
				}else {
					ybCardBean.setYuanbao(ybCardBean.getYuanbao()+ ybsum);	//加元宝
					if(ybcardDao.update(ybCardBean)== 0){
						log.error(player.getId()+":元宝update失败="+ybsum);
						status = 0;
					}else {
						cmsg.setYuanbaonum(ybCardBean.getYuanbao());
						MessageUtil.tell_player_message(player, cmsg);
						MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("使用{1}公测元宝卡，获得{2}公测礼金"),msg.getNum()+"",ybsum+"");
						status = 1;
					}
				}
				if (status == 1 && ybms.size() > 0) {
					for (int i = 0; i < ybms.size(); i++) {
						MessageUtil.notify_All_player(Notifys.CUTOUT,ResManager.getInstance().getString("恭喜『{1}』使用公测元宝卡获得了 {2}公测礼金"),player.getName(),ybms.get(i)+"");
					}
				}
				
				stYbcaedLog(yblog,status);
				//通知GAME给玩家加元宝
				ResYBCardAddYBPlayerToGameMessage xmsg = new ResYBCardAddYBPlayerToGameMessage();
				xmsg.setNum(ybsum);
				xmsg.setPlayerid(player.getId());
				MessageUtil.send_to_game(player, xmsg);
//				int sum = dataybnum + ybsum;
				activitySetBean.setInt_value(dataybnum);
					if((dataybnum%100)==0 || dataybnum > YB_DAY_MAX - 10){
						actDao.update(activitySetBean);
					}
				//群发给所有GAME
				if (isday == 1 || dataybnum >= YB_DAY_MAX - 10) {
					ResYBCardNoticeToGameMessage nsmsg = new ResYBCardNoticeToGameMessage();
					nsmsg.setYuanbaonum(dataybnum);
					MessageUtil.send_to_game(nsmsg);
				}
				
			//-------------------------------------------------领取元宝
			}else if (msg.getType() == 2) {
				if (isinsert) {
					MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("没有找到您的公测元宝卡记录"));
					return;
				}else {
					if (ybCardBean.getYuanbao() == 0) {
						MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("您已经领取全部公测卡元宝。"));
						return ;
					}
					int yb = ybCardBean.getYuanbao();
					ybCardBean.setYuanbao(0);
					if(ybcardDao.update(ybCardBean) == 1){
						ResYBCardReceiveToGameMessage gmsg = new ResYBCardReceiveToGameMessage();
						gmsg.setPlayerid(player.getId());
						gmsg.setYuanbaonum(yb);
						MessageUtil.send_to_game(player, gmsg);
					}else {
						log.error(player.getName()+"=领取时，元宝清零失败");
					}
				}	
			}
		}
	}
	
	
	
	/**
	 * 类型1成功，0失败
	 * @param yblog
	 * @param yuanbao
	 * @param type
	 */
	public void stYbcaedLog(YbcaedLog yblog , int type) {
		yblog.setType(type);
		LogService.getInstance().execute(yblog);
	}
	
	
	
	
	
}
