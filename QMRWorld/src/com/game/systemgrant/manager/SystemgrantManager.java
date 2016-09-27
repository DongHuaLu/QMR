package com.game.systemgrant.manager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.db.bean.System_grantBean;
import com.game.db.dao.System_grantDao;
import com.game.json.JSONserializable;
import com.game.systemgrant.message.ResSystemgrantRefreshToGameMessage;
import com.game.utils.MessageUtil;

/**
 * 系统发放奖励或补偿全服邮件
 * 
 * @author zhangrong
 *
 */
public class SystemgrantManager {
	Logger log = Logger.getLogger(SystemgrantManager.class);
	private static Object obj = new Object();
	//玩家管理类实例
	private static SystemgrantManager manager;
	private SystemgrantManager() {
	}

	public static SystemgrantManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new SystemgrantManager();
			}
		}
		return manager;
	}
	
	private List<System_grantBean> list;
	
	private HashMap<Integer, System_grantBean> grantmap = new HashMap<Integer, System_grantBean>();
	
	
	/**
	 * 全服邮件数据接口
	 */
	private System_grantDao system_grantDao = new System_grantDao();

	public System_grantDao getSystem_grantDao() {
		return system_grantDao;
	}
	

	
	/**读取全服邮件数据
	 * 
	 */
	public void system_GrantBean_load(){
		list = system_grantDao.select();
		Iterator<System_grantBean> iter = list.iterator();
		HashMap<Integer, System_grantBean> newgrantmap = new HashMap<Integer, System_grantBean>();
		while (iter.hasNext()) {
			System_grantBean bean = (System_grantBean) iter.next();
			newgrantmap.put(bean.getQ_id(), bean);
		}
		setGrantmap(newgrantmap);
	}

	
	
	public List<System_grantBean> getList(){
		return list;
	}
	
	public HashMap<Integer, System_grantBean> getGrantmap() {
		return grantmap;
	}

	public void setGrantmap(HashMap<Integer, System_grantBean> grantmap) {
		this.grantmap = grantmap;
	}
	
	
	/**插入数据
	 *  成功返回1
	 * @param system_grantBean
	 * @return 
	 */
	public int insertSystemGrantBean(System_grantBean system_grantBean){
		try {
			if (system_grantDao.insert(system_grantBean) == 1) {
				ResSystemgrantRefreshToGameMessage gmsg = new ResSystemgrantRefreshToGameMessage();
				String str = JSONserializable.toString(system_grantBean);
				gmsg.setType(1);
				gmsg.setJosnstr(str);
				MessageUtil.send_to_game(gmsg);
				return 1;
			}else {
				log.error("插入全服邮件数据失败:"+system_grantBean.getQ_caption());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	/**删除数据
	 * 成功返回1
	 * @return 
	 * 
	 */
	public int deleteSystemGrantBean(int id) {
		try {
			if(system_grantDao.delete(id) == 1){
				system_GrantBean_load();
				ResSystemgrantRefreshToGameMessage gmsg = new ResSystemgrantRefreshToGameMessage();
				String str = JSONserializable.toString(grantmap.get(id));
				gmsg.setType(1);
				gmsg.setJosnstr(str);
				MessageUtil.send_to_game(gmsg);
				return 1;
			}else {
				log.error("移除全服邮件数据失败,ID: "+id);
			}
		} catch (Exception e) {
			
		}
		return 0;
	}
	
	

	
	
}
