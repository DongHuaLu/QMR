package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_mapBean;
import com.game.data.dao.Q_mapDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_map数据容器
 */
public class Q_mapContainer {

	private List<Q_mapBean> list;
	
	private HashMap<Integer, Q_mapBean> map = new HashMap<Integer, Q_mapBean>();

	private Q_mapDao dao = new Q_mapDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_mapBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_mapBean bean = (Q_mapBean) iter
					.next();
			map.put(bean.getQ_map_id(), bean);
		}
	}

	public List<Q_mapBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_mapBean> getMap(){
		return map;
	}
}