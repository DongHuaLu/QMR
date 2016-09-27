package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_task_rankBean;
import com.game.data.dao.Q_task_rankDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_task_rank数据容器
 */
public class Q_task_rankContainer {

	private List<Q_task_rankBean> list;
	
	private HashMap<Integer, Q_task_rankBean> map = new HashMap<Integer, Q_task_rankBean>();

	private Q_task_rankDao dao = new Q_task_rankDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_task_rankBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_task_rankBean bean = (Q_task_rankBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_task_rankBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_task_rankBean> getMap(){
		return map;
	}
}