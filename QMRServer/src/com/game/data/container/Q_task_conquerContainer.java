package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_task_conquerBean;
import com.game.data.dao.Q_task_conquerDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_task_conquer数据容器
 */
public class Q_task_conquerContainer {

	private List<Q_task_conquerBean> list;
	
	private HashMap<Integer, Q_task_conquerBean> map = new HashMap<Integer, Q_task_conquerBean>();

	private Q_task_conquerDao dao = new Q_task_conquerDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_task_conquerBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_task_conquerBean bean = (Q_task_conquerBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_task_conquerBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_task_conquerBean> getMap(){
		return map;
	}
}