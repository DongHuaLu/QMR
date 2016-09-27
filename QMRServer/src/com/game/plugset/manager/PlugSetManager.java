package com.game.plugset.manager;

import com.game.data.bean.Q_mapBean;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.plugset.bean.PlugSetInfo;
import com.game.plugset.message.ReqPlugSetInfoMessage;
import com.game.plugset.message.ResPlugSetInfoMessage;
import com.game.plugset.structs.PlugSet;
import com.game.structs.Grid;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;

public class PlugSetManager {


	private PlugSetManager() {
	}
	
	//玩家管理类实例
	private static PlugSetManager manager;
	private static Object obj = new Object();
	public static PlugSetManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new PlugSetManager();
			}
		}
		return manager;
	}
	
	
	/**保存前端挂机设置消息
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqPlugSetInfoMessage(Player player,ReqPlugSetInfoMessage msg) {
		PlugSet plugset = player.getPlugset();
		if (msg.getTime()> 0 && msg.getTime() < 5) {
			plugset.setTime(5);		//最低5分钟
		}else {
			plugset.setTime(msg.getTime());
		}
		plugset.setHpper(msg.getHpper());
		plugset.setItemcolor(msg.getItemcolor());
		plugset.setItemlv(msg.getItemlv());
		plugset.setMpper(msg.getMpper());
		plugset.setParameter(msg.getParameter());
		plugset.setPickup(msg.getPickup());
		plugset.setRange(msg.getRange());
		plugset.setSkillid(msg.getSkillid());
		plugset.setSpper(msg.getSpper());
	}

	
	
	/**发送挂机设置到前端
	 * 
	 * @param player
	 */
	public void stResPlugSetInfo(Player player){
		PlugSet plugset =player.getPlugset();
		PlugSetInfo psinfo=new PlugSetInfo();
		psinfo.setTime(plugset.getTime());
		psinfo.setHpper(plugset.getHpper());
		psinfo.setItemcolor(plugset.getItemcolor());
		psinfo.setItemlv(plugset.getItemlv());
		psinfo.setMpper(plugset.getMpper());
		psinfo.setParameter(plugset.getParameter());
		psinfo.setPickup(plugset.getPickup());
		psinfo.setRange(plugset.getRange());
		psinfo.setSkillid(plugset.getSkillid());
		psinfo.setSpper(plugset.getSpper());
		ResPlugSetInfoMessage smsg = new ResPlugSetInfoMessage();
		smsg.setPlugsetinfo(psinfo);
		MessageUtil.tell_player_message(player, smsg);
	}
	
	
	/**挂机时间到达后回城
	 * 
	 * @param player
	 */
	public void stPlugBackToCity(Player player){
		Map map = ManagerPool.mapManager.getMap(player);	//所在地图
		Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(map.getMapModelid());
		Q_mapBean reviveMapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(mapBean.getQ_map_die());
		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(reviveMapBean.getQ_map_id());
		Grid grid = MapUtils.getGrid(mapBean.getQ_map_die_x(), mapBean.getQ_map_die_y(), grids);
		ManagerPool.mapManager.changeMap(player, mapBean.getQ_map_die(), mapBean.getQ_map_die(), 0, grid.getCenter(), this.getClass().getName() + ".stPlugBackToCity");
	}
	
	
	
}
