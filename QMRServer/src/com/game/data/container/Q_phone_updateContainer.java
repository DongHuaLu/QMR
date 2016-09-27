package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_phone_updateBean;
import com.game.data.dao.Q_phone_updateDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_phone_update数据容器
 */
public class Q_phone_updateContainer {

	private List<Q_phone_updateBean> list;
	
	private HashMap<Integer, Q_phone_updateBean> map = new HashMap<Integer, Q_phone_updateBean>();

	private Q_phone_updateDao dao = new Q_phone_updateDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_phone_updateBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_phone_updateBean bean = (Q_phone_updateBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_phone_updateBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_phone_updateBean> getMap(){
		return map;
	}
}