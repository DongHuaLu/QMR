package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_hiddenweapon_skillBean;
import com.game.data.dao.Q_hiddenweapon_skillDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_hiddenweapon_skill数据容器
 */
public class Q_hiddenweapon_skillContainer {

	private List<Q_hiddenweapon_skillBean> list;
	
	private HashMap<String, Q_hiddenweapon_skillBean> map = new HashMap<String, Q_hiddenweapon_skillBean>();

	private Q_hiddenweapon_skillDao dao = new Q_hiddenweapon_skillDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_hiddenweapon_skillBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_hiddenweapon_skillBean bean = (Q_hiddenweapon_skillBean) iter
					.next();
			map.put(bean.getQ_skill(), bean);
		}
	}

	public List<Q_hiddenweapon_skillBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_hiddenweapon_skillBean> getMap(){
		return map;
	}
}