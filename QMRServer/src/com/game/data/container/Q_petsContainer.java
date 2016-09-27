package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_petsBean;
import com.game.data.dao.Q_petsDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_pets数据容器
 */
public class Q_petsContainer {

	private List<Q_petsBean> list;
	
	private HashMap<String, Q_petsBean> map = new HashMap<String, Q_petsBean>();

	private Q_petsDao dao = new Q_petsDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_petsBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_petsBean bean = (Q_petsBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_petsBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_petsBean> getMap(){
		return map;
	}
}