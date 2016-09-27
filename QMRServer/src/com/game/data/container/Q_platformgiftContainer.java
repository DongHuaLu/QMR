package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_platformgiftBean;
import com.game.data.dao.Q_platformgiftDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_platformgift数据容器
 */
public class Q_platformgiftContainer {

	private List<Q_platformgiftBean> list;
	
	private HashMap<Integer, Q_platformgiftBean> map = new HashMap<Integer, Q_platformgiftBean>();

	private Q_platformgiftDao dao = new Q_platformgiftDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_platformgiftBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_platformgiftBean bean = (Q_platformgiftBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_platformgiftBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_platformgiftBean> getMap(){
		return map;
	}
}