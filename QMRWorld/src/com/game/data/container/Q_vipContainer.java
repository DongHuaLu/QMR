package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_vipBean;
import com.game.data.dao.Q_vipDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_vip数据容器
 */
public class Q_vipContainer {

	private List<Q_vipBean> list;
	
	private HashMap<Integer, Q_vipBean> map = new HashMap<Integer, Q_vipBean>();

	private Q_vipDao dao = new Q_vipDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_vipBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_vipBean bean = (Q_vipBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_vipBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_vipBean> getMap(){
		return map;
	}
}