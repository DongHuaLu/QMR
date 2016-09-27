package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_task_mainBean;
import com.game.data.dao.Q_task_mainDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_task_main数据容器
 */
public class Q_task_mainContainer {

	private List<Q_task_mainBean> list;
	
	private HashMap<Integer, Q_task_mainBean> map = new HashMap<Integer, Q_task_mainBean>();

	private Q_task_mainDao dao = new Q_task_mainDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_task_mainBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_task_mainBean bean = (Q_task_mainBean) iter
					.next();
			map.put(bean.getQ_taskid(), bean);
		}
	}

	public List<Q_task_mainBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_task_mainBean> getMap(){
		return map;
	}
}