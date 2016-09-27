package com.game.systemgrant.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.db.bean.System_grantBean;
import com.game.db.dao.System_grantDao;
import com.game.json.JSONserializable;
import com.game.systemgrant.message.ResSystemgrantRefreshToGameMessage;
import com.game.utils.Symbol;

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
		log.error("重读全服邮件成功");
	}

	
	
	public List<System_grantBean> getList(){
		return list;
	}
	
	public synchronized HashMap<Integer, System_grantBean> getGrantmap() {
		return grantmap;
	}

	public void setGrantmap(HashMap<Integer, System_grantBean> grantmap) {
		this.grantmap = grantmap;
	}
	
	
	/**插入数据
	 * 
	 * @param system_grantBean
	 */
	public void insertSystemGrantBean(System_grantBean system_grantBean){
		try {
			if (system_grantDao.insert(system_grantBean) == 1) {
				
			}else {
				log.error("插入全服邮件数据失败:"+system_grantBean.getQ_caption());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**删除数据
	 * 
	 */
	public void deleteSystemGrantBean(int id) {
		if(system_grantDao.delete(id) == 1){
			
		}else {
			log.error("移除全服邮件数据失败,ID: "+id);
		}
	}
	
	
	/**解析字符串
	 * @return 
	 * 
	 */
	public HashMap<String, List<Integer>> resolvestr(String str){
		HashMap<String, List<Integer>> resmap = new HashMap<String, List<Integer>>();
		String[] content = str.split(Symbol.SHUXIAN_REG);
		if (content.length > 0) {
			for (String string : content) {
				String[] content2 = string.split(Symbol.DOUHAO);
				List<Integer> list = new ArrayList<Integer>();
				for (int i = 0; i < content2.length; i++) {
					if (i >= 1) {//第1个是平台ID，后面是区号
						list.add(Integer.valueOf(content2[i]));
					}
				}
				resmap.put(content2[0], list);//KEY=区ID
			}
		}
		return resmap;
	}

	
	
	
	/**更新邮件列表
	 * 
	 * @param msg
	 */
	public synchronized void operatingGrantmap(ResSystemgrantRefreshToGameMessage msg) {
		if (msg.getJosnstr() == null || msg.getJosnstr().equals("")) {
			return;
		}
		System_grantBean system_grantBean = (System_grantBean) JSONserializable.toObject(msg.getJosnstr(),System_grantBean.class);
		if (msg.getType() == 2) {//删除
			grantmap.remove(system_grantBean.getQ_id());
		}else if (msg.getType() ==1) {//更新
			grantmap.put(system_grantBean.getQ_id(), system_grantBean);
		}
	}


	
	
	
	
	

	
	
}
