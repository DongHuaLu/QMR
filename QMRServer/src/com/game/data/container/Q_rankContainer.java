package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_rankBean;
import com.game.data.dao.Q_rankDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_rank数据容器
 */
public class Q_rankContainer {

	private List<Q_rankBean> list;
	
	private HashMap<Integer, Q_rankBean> map = new HashMap<Integer, Q_rankBean>();

	private Q_rankDao dao = new Q_rankDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_rankBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_rankBean bean = (Q_rankBean) iter
					.next();
			map.put(bean.getQ_ranklv(), bean);
		}
	}

	public List<Q_rankBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_rankBean> getMap(){
		return map;
	}
}