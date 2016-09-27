package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_horseweapon_attrBean;
import com.game.data.dao.Q_horseweapon_attrDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_horseweapon_attr数据容器
 */
public class Q_horseweapon_attrContainer {

	private List<Q_horseweapon_attrBean> list;
	
	private HashMap<String, Q_horseweapon_attrBean> map = new HashMap<String, Q_horseweapon_attrBean>();

	private Q_horseweapon_attrDao dao = new Q_horseweapon_attrDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_horseweapon_attrBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_horseweapon_attrBean bean = (Q_horseweapon_attrBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_horseweapon_attrBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_horseweapon_attrBean> getMap(){
		return map;
	}
}