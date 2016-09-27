package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_longyuanBean;
import com.game.data.dao.Q_longyuanDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_longyuan数据容器
 */
public class Q_longyuanContainer {

	private List<Q_longyuanBean> list;
	
	private HashMap<String, Q_longyuanBean> map = new HashMap<String, Q_longyuanBean>();

	private Q_longyuanDao dao = new Q_longyuanDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_longyuanBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_longyuanBean bean = (Q_longyuanBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_longyuanBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_longyuanBean> getMap(){
		return map;
	}
}