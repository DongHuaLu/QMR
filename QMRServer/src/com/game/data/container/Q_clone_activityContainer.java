package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_clone_activityBean;
import com.game.data.dao.Q_clone_activityDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_clone_activity数据容器
 */
public class Q_clone_activityContainer {

	private List<Q_clone_activityBean> list;
	
	private HashMap<Integer, Q_clone_activityBean> map = new HashMap<Integer, Q_clone_activityBean>();

	private Q_clone_activityDao dao = new Q_clone_activityDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_clone_activityBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_clone_activityBean bean = (Q_clone_activityBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_clone_activityBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_clone_activityBean> getMap(){
		return map;
	}
}