package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_jiaochangBean;
import com.game.data.dao.Q_jiaochangDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_jiaochang数据容器
 */
public class Q_jiaochangContainer {

	private List<Q_jiaochangBean> list;
	
	private HashMap<Integer, Q_jiaochangBean> map = new HashMap<Integer, Q_jiaochangBean>();

	private Q_jiaochangDao dao = new Q_jiaochangDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_jiaochangBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_jiaochangBean bean = (Q_jiaochangBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_jiaochangBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_jiaochangBean> getMap(){
		return map;
	}
}