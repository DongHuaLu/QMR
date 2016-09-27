package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_activitiesBean;
import com.game.data.dao.Q_activitiesDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_activities数据容器
 */
public class Q_activitiesContainer {

	private List<Q_activitiesBean> list;
	
	private HashMap<Integer, Q_activitiesBean> map = new HashMap<Integer, Q_activitiesBean>();

	private Q_activitiesDao dao = new Q_activitiesDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_activitiesBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_activitiesBean bean = (Q_activitiesBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_activitiesBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_activitiesBean> getMap(){
		return map;
	}
}