package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_versionBean;
import com.game.data.dao.Q_versionDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_version数据容器
 */
public class Q_versionContainer {

	private List<Q_versionBean> list;
	
	private HashMap<String, Q_versionBean> map = new HashMap<String, Q_versionBean>();

	private Q_versionDao dao = new Q_versionDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_versionBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_versionBean bean = (Q_versionBean) iter
					.next();
			map.put(bean.getQ_tablename(), bean);
		}
	}

	public List<Q_versionBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_versionBean> getMap(){
		return map;
	}
}