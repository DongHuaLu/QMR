package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_task_explorepalaceBean;
import com.game.data.dao.Q_task_explorepalaceDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_task_explorepalace数据容器
 */
public class Q_task_explorepalaceContainer {

	private List<Q_task_explorepalaceBean> list;
	
	private HashMap<Integer, Q_task_explorepalaceBean> map = new HashMap<Integer, Q_task_explorepalaceBean>();

	private Q_task_explorepalaceDao dao = new Q_task_explorepalaceDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_task_explorepalaceBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_task_explorepalaceBean bean = (Q_task_explorepalaceBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_task_explorepalaceBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_task_explorepalaceBean> getMap(){
		return map;
	}
}