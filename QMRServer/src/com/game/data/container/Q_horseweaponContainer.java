package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_horseweaponBean;
import com.game.data.dao.Q_horseweaponDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_horseweapon数据容器
 */
public class Q_horseweaponContainer {

	private List<Q_horseweaponBean> list;
	
	private HashMap<Integer, Q_horseweaponBean> map = new HashMap<Integer, Q_horseweaponBean>();

	private Q_horseweaponDao dao = new Q_horseweaponDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_horseweaponBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_horseweaponBean bean = (Q_horseweaponBean) iter
					.next();
			map.put(bean.getQ_rank(), bean);
		}
	}

	public List<Q_horseweaponBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_horseweaponBean> getMap(){
		return map;
	}
}