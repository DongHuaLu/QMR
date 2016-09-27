package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_characterBean;
import com.game.data.dao.Q_characterDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_character数据容器
 */
public class Q_characterContainer {

	private List<Q_characterBean> list;
	
	private HashMap<Integer, Q_characterBean> map = new HashMap<Integer, Q_characterBean>();

	private Q_characterDao dao = new Q_characterDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_characterBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_characterBean bean = (Q_characterBean) iter
					.next();
			map.put(bean.getQ_level(), bean);
		}
	}

	public List<Q_characterBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_characterBean> getMap(){
		return map;
	}
}