package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_special_eventBean;
import com.game.data.dao.Q_special_eventDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_special_event数据容器
 */
public class Q_special_eventContainer {

	private List<Q_special_eventBean> list;
	
	private HashMap<Integer, Q_special_eventBean> map = new HashMap<Integer, Q_special_eventBean>();

	private Q_special_eventDao dao = new Q_special_eventDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_special_eventBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_special_eventBean bean = (Q_special_eventBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_special_eventBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_special_eventBean> getMap(){
		return map;
	}
}