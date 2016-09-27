package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_evencutBean;
import com.game.data.dao.Q_evencutDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_evencut数据容器
 */
public class Q_evencutContainer {

	private List<Q_evencutBean> list;
	
	private HashMap<Integer, Q_evencutBean> map = new HashMap<Integer, Q_evencutBean>();

	private Q_evencutDao dao = new Q_evencutDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_evencutBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_evencutBean bean = (Q_evencutBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_evencutBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_evencutBean> getMap(){
		return map;
	}
}