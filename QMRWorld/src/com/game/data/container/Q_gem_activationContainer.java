package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_gem_activationBean;
import com.game.data.dao.Q_gem_activationDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_gem_activation数据容器
 */
public class Q_gem_activationContainer {

	private List<Q_gem_activationBean> list;
	
	private HashMap<String, Q_gem_activationBean> map = new HashMap<String, Q_gem_activationBean>();

	private Q_gem_activationDao dao = new Q_gem_activationDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_gem_activationBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_gem_activationBean bean = (Q_gem_activationBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_gem_activationBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_gem_activationBean> getMap(){
		return map;
	}
}