package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_task_daily_rewardsBean;
import com.game.data.dao.Q_task_daily_rewardsDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_task_daily_rewards数据容器
 */
public class Q_task_daily_rewardsContainer {

	private List<Q_task_daily_rewardsBean> list;
	
	private HashMap<Integer, Q_task_daily_rewardsBean> map = new HashMap<Integer, Q_task_daily_rewardsBean>();

	private Q_task_daily_rewardsDao dao = new Q_task_daily_rewardsDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_task_daily_rewardsBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_task_daily_rewardsBean bean = (Q_task_daily_rewardsBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_task_daily_rewardsBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_task_daily_rewardsBean> getMap(){
		return map;
	}
}