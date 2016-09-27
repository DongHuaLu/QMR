package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_toplistchestBean;
import com.game.data.dao.Q_toplistchestDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_toplistchest数据容器
 */
public class Q_toplistchestContainer {

	private List<Q_toplistchestBean> list;
	
	private HashMap<Integer, Q_toplistchestBean> map = new HashMap<Integer, Q_toplistchestBean>();

	private Q_toplistchestDao dao = new Q_toplistchestDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_toplistchestBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_toplistchestBean bean = (Q_toplistchestBean) iter
					.next();
			map.put(bean.getQ_chest_id(), bean);
		}
	}

	public List<Q_toplistchestBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_toplistchestBean> getMap(){
		return map;
	}
}