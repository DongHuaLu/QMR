package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_explorepalace_mapBean;
import com.game.data.dao.Q_explorepalace_mapDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_explorepalace_map数据容器
 */
public class Q_explorepalace_mapContainer {

	private List<Q_explorepalace_mapBean> list;
	
	private HashMap<Integer, Q_explorepalace_mapBean> map = new HashMap<Integer, Q_explorepalace_mapBean>();

	private Q_explorepalace_mapDao dao = new Q_explorepalace_mapDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_explorepalace_mapBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_explorepalace_mapBean bean = (Q_explorepalace_mapBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_explorepalace_mapBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_explorepalace_mapBean> getMap(){
		return map;
	}
}