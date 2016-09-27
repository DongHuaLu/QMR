package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_task_daily_condBean;
import com.game.data.dao.Q_task_daily_condDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_task_daily_cond数据容器
 */
public class Q_task_daily_condContainer {

	private List<Q_task_daily_condBean> list;
	
	private HashMap<Integer, Q_task_daily_condBean> map = new HashMap<Integer, Q_task_daily_condBean>();

	private Q_task_daily_condDao dao = new Q_task_daily_condDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_task_daily_condBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_task_daily_condBean bean = (Q_task_daily_condBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_task_daily_condBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_task_daily_condBean> getMap(){
		return map;
	}
}