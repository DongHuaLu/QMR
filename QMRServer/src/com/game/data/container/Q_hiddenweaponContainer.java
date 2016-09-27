package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_hiddenweaponBean;
import com.game.data.dao.Q_hiddenweaponDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_hiddenweapon数据容器
 */
public class Q_hiddenweaponContainer {

	private List<Q_hiddenweaponBean> list;
	
	private HashMap<Integer, Q_hiddenweaponBean> map = new HashMap<Integer, Q_hiddenweaponBean>();

	private Q_hiddenweaponDao dao = new Q_hiddenweaponDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_hiddenweaponBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_hiddenweaponBean bean = (Q_hiddenweaponBean) iter
					.next();
			map.put(bean.getQ_rank(), bean);
		}
	}

	public List<Q_hiddenweaponBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_hiddenweaponBean> getMap(){
		return map;
	}
}