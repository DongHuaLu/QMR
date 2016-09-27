package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_titleBean;
import com.game.data.dao.Q_titleDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_title数据容器
 */
public class Q_titleContainer {

	private List<Q_titleBean> list;
	
	private HashMap<String, Q_titleBean> map = new HashMap<String, Q_titleBean>();

	private Q_titleDao dao = new Q_titleDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_titleBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_titleBean bean = (Q_titleBean) iter
					.next();
			map.put(bean.getQ_toptype() + "_" + bean.getQ_topidx(), bean);
		}
	}

	public List<Q_titleBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_titleBean> getMap(){
		return map;
	}
}