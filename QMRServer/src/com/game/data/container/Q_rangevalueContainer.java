package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_rangevalueBean;
import com.game.data.dao.Q_rangevalueDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_rangevalue数据容器
 */
public class Q_rangevalueContainer {

	private List<Q_rangevalueBean> list;
	
	private HashMap<Integer, Q_rangevalueBean> map = new HashMap<Integer, Q_rangevalueBean>();

	private Q_rangevalueDao dao = new Q_rangevalueDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_rangevalueBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_rangevalueBean bean = (Q_rangevalueBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_rangevalueBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_rangevalueBean> getMap(){
		return map;
	}
}