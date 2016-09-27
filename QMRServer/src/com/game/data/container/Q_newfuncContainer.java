package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_newfuncBean;
import com.game.data.dao.Q_newfuncDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_newfunc数据容器
 */
public class Q_newfuncContainer {

	private List<Q_newfuncBean> list;
	
	private HashMap<Integer, Q_newfuncBean> map = new HashMap<Integer, Q_newfuncBean>();

	private Q_newfuncDao dao = new Q_newfuncDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_newfuncBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_newfuncBean bean = (Q_newfuncBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_newfuncBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_newfuncBean> getMap(){
		return map;
	}
}