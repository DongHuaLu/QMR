package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_hiddenweapon_attrBean;
import com.game.data.dao.Q_hiddenweapon_attrDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_hiddenweapon_attr数据容器
 */
public class Q_hiddenweapon_attrContainer {

	private List<Q_hiddenweapon_attrBean> list;
	
	private HashMap<Integer, Q_hiddenweapon_attrBean> map = new HashMap<Integer, Q_hiddenweapon_attrBean>();

	private Q_hiddenweapon_attrDao dao = new Q_hiddenweapon_attrDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_hiddenweapon_attrBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_hiddenweapon_attrBean bean = (Q_hiddenweapon_attrBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_hiddenweapon_attrBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_hiddenweapon_attrBean> getMap(){
		return map;
	}
}