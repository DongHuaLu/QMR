package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_activities_dropBean;
import com.game.data.dao.Q_activities_dropDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_activities_drop数据容器
 */
public class Q_activities_dropContainer {

	private List<Q_activities_dropBean> list;
	
	private HashMap<Integer, Q_activities_dropBean> map = new HashMap<Integer, Q_activities_dropBean>();

	private Q_activities_dropDao dao = new Q_activities_dropDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_activities_dropBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_activities_dropBean bean = (Q_activities_dropBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_activities_dropBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_activities_dropBean> getMap(){
		return map;
	}
}