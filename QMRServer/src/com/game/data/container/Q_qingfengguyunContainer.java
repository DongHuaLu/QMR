package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_qingfengguyunBean;
import com.game.data.dao.Q_qingfengguyunDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_qingfengguyun数据容器
 */
public class Q_qingfengguyunContainer {

	private List<Q_qingfengguyunBean> list;
	
	private HashMap<Integer, Q_qingfengguyunBean> map = new HashMap<Integer, Q_qingfengguyunBean>();

	private Q_qingfengguyunDao dao = new Q_qingfengguyunDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_qingfengguyunBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_qingfengguyunBean bean = (Q_qingfengguyunBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_qingfengguyunBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_qingfengguyunBean> getMap(){
		return map;
	}
}