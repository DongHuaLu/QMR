package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_meihuaxuanwuBean;
import com.game.data.dao.Q_meihuaxuanwuDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_meihuaxuanwu数据容器
 */
public class Q_meihuaxuanwuContainer {

	private List<Q_meihuaxuanwuBean> list;
	
	private HashMap<Integer, Q_meihuaxuanwuBean> map = new HashMap<Integer, Q_meihuaxuanwuBean>();

	private Q_meihuaxuanwuDao dao = new Q_meihuaxuanwuDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_meihuaxuanwuBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_meihuaxuanwuBean bean = (Q_meihuaxuanwuBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_meihuaxuanwuBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_meihuaxuanwuBean> getMap(){
		return map;
	}
}