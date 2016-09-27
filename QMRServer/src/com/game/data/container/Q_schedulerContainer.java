package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_schedulerBean;
import com.game.data.dao.Q_schedulerDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_scheduler数据容器
 */
public class Q_schedulerContainer {

	private List<Q_schedulerBean> list;
	
	private HashMap<Integer, Q_schedulerBean> map = new HashMap<Integer, Q_schedulerBean>();

	private Q_schedulerDao dao = new Q_schedulerDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_schedulerBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_schedulerBean bean = (Q_schedulerBean) iter
					.next();
			map.put(bean.getQ_scheduler_id(), bean);
		}
	}

	public List<Q_schedulerBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_schedulerBean> getMap(){
		return map;
	}
}