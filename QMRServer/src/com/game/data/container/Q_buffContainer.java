package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_buffBean;
import com.game.data.dao.Q_buffDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_buff数据容器
 */
public class Q_buffContainer {

	private List<Q_buffBean> list;
	
	private HashMap<Integer, Q_buffBean> map = new HashMap<Integer, Q_buffBean>();

	private Q_buffDao dao = new Q_buffDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_buffBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_buffBean bean = (Q_buffBean) iter
					.next();
			map.put(bean.getQ_buff_id(), bean);
		}
	}

	public List<Q_buffBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_buffBean> getMap(){
		return map;
	}
}