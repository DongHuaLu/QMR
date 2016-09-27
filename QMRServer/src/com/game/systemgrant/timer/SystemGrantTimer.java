package com.game.systemgrant.timer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.backpack.structs.Item;
import com.game.db.bean.System_grantBean;
import com.game.languageres.manager.ResManager;
import com.game.mail.manager.MailServerManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.server.impl.WServer;
import com.game.systemgrant.manager.SystemgrantManager;
import com.game.timer.TimerEvent;
import com.game.utils.Symbol;
import com.game.utils.TimeUtil;
/**在线自动发邮件，每分钟检测一次
 * 
 * @author zhangrong
 *
 */
public class SystemGrantTimer extends TimerEvent{

	private static final Logger logger = Logger.getLogger(SystemGrantTimer.class);

	private int serverid;
	private int lineid;
	private int mapid;
	
	public SystemGrantTimer(int serverid,int lineid,int mapid) {
		super(-1,5000*60);	//5分钟触发一次
		this.serverid=serverid;
		this.lineid=lineid;
		this.mapid=mapid;
	}

	@Override
	public void action() {
		//获取地图
		long ms =System.currentTimeMillis();
		Map map = ManagerPool.mapManager.getMap(serverid, lineid, mapid);
		if (map.getPlayerNumber() == 0) {
			return;
		}
		try {
			HashMap<Integer, System_grantBean> grantmap = SystemgrantManager.getInstance().getGrantmap();
			if (grantmap.isEmpty()) {
				return;
			}
			String sysplatform = WServer.getInstance().getServerWeb();
			Iterator<System_grantBean> grantit = grantmap.values().iterator();
			while (grantit.hasNext()) {
				System_grantBean system_grantBean = (System_grantBean) grantit.next();
				String key = system_grantBean.getQ_key();
				if (system_grantBean.getQ_platform() == null ||system_grantBean.getQ_platform().equals("")) continue;
				if (system_grantBean.getQ_time_interval() == null || system_grantBean.getQ_time_interval().equals("")) continue;
				if (system_grantBean.getQ_key() == null || system_grantBean.getQ_key().equals("")) continue;
				if (system_grantBean.getQ_level() == null && system_grantBean.getQ_level().equals("")) continue;
				
	
				HashMap<String, List<Integer>> platformmap = SystemgrantManager.getInstance().resolvestr(system_grantBean.getQ_platform());
				HashMap<String, List<Integer>> exclude_platformmap =  SystemgrantManager.getInstance().resolvestr(system_grantBean.getQ_exclude_platform());
				
				if (!platformmap.containsKey(sysplatform)) continue;//当前平台和区是否在发放列表范围内
				List<Integer> list = platformmap.get(sysplatform);
//				if (list.size() < 2) continue;//区范围格式不对，跳出
//				if (list.size() == 2) {//如果是2个元素，表示范围
//					if (serverid < list.get(0) || serverid > list.get(1)) continue;
//				}else {
//					if(!list.contains(serverid)) continue;	//不包含本区
//				}
				
	
				if (exclude_platformmap.containsKey(sysplatform)) {//特定区是否在排除列表
					List<Integer> list2 = exclude_platformmap.get(sysplatform);
					if (list2.contains(serverid)) {
						continue;
					}
				}
				
				String[] times=system_grantBean.getQ_time_interval().split(Symbol.SHUXIAN_REG);
				if (times.length < 2)continue;//时间范围设置不正确
				Date startdate = TimeUtil.getDateByString(times[0]);
				Date enddate = TimeUtil.getDateByString(times[1]);
				if (startdate.getTime() > ms || enddate.getTime() < ms) continue;//检测时间范围
				String[] lvs = system_grantBean.getQ_level().split(Symbol.SHUXIAN_REG);
				int lvmin  = Integer.valueOf(lvs[0]);
				int lvmax  = Integer.valueOf(lvs[1]);
				long ctime = ms;
				if(!system_grantBean.getQ_rolecreated().equals("")){
					Date createddate = TimeUtil.getDateByString(system_grantBean.getQ_rolecreated());
					ctime = createddate.getTime();
				}
				
				//遍历玩家列表
				Iterator<Player> iter = map.getPlayers().values().iterator();
				while (iter.hasNext()) {
					Player player = (Player) iter.next();
					//不是本线玩家
					if(player.getServerId()!=this.serverid || player.getLine()!=this.lineid || player.getMap()!=this.mapid) continue;

					
					if (list.size() < 1) continue;//区范围格式不对，跳出
					if (list.size() == 2) {//如果是2个元素，表示范围
						if (player.getCreateServerId() < list.get(0) || player.getCreateServerId() > list.get(1)) continue;
					}else {
						if(!list.contains(player.getCreateServerId())) continue;	//不包含本区
					}
					
					if (player.getLevel() > lvmin && player.getLevel() < lvmax ) {//检测等级范围
						if(!player.getSysmailmap().containsKey(key)){//检测是否已经领过
							if (player.getCreateTime() <= ctime) {//创建时间小于指定时间的玩家
								List<Item> itemlist = new ArrayList<Item>();
								if (system_grantBean.getQ_items() != null && !system_grantBean.getQ_items().equals("")){
									ManagerPool.backpackManager.createItems(player, system_grantBean.getQ_items(), itemlist);
								}
								player.getSysmailmap().put(key, (ms/1000)+"");//写入已发送标记
								MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("系统邮件"), ResManager.getInstance().getString(system_grantBean.getQ_caption()), (byte) 1, 0, itemlist);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("群发邮件出错："+e,e);
		}
	}

}
