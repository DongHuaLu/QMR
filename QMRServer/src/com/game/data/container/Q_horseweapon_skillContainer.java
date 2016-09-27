package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_horseweapon_skillBean;
import com.game.data.dao.Q_horseweapon_skillDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_horseweapon_skill数据容器
 */
public class Q_horseweapon_skillContainer {

	private List<Q_horseweapon_skillBean> list;
	
	private HashMap<Integer, Q_horseweapon_skillBean> map = new HashMap<Integer, Q_horseweapon_skillBean>();

	private Q_horseweapon_skillDao dao = new Q_horseweapon_skillDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_horseweapon_skillBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_horseweapon_skillBean bean = (Q_horseweapon_skillBean) iter
					.next();
			map.put(bean.getQ_grid(), bean);
		}
	}

	public List<Q_horseweapon_skillBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_horseweapon_skillBean> getMap(){
		return map;
	}
}