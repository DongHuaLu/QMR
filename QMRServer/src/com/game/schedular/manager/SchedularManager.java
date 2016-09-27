package com.game.schedular.manager;

import java.util.List;

import com.game.data.bean.Q_schedulerBean;
import com.game.manager.ManagerPool;
import com.game.server.impl.WServer;

public class SchedularManager {

	//private Logger log = Logger.getLogger(SchedularManager.class);
	
	private static Object obj = new Object();
	//玩家管理类实例
	private static SchedularManager manager;

	private SchedularManager(){}
	
	public static SchedularManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new SchedularManager();
			}
		}
		return manager;
	}
	
	public void init(){
		List<Q_schedulerBean> beans = ManagerPool.dataManager.q_schedulerContainer.getList();
		for (int i = 0; i < beans.size(); i++) {
			Q_schedulerBean bean = beans.get(i);
			WServer.getInstance().addSchedularTask(bean.getQ_scheduler_time(), bean.getQ_scheduler_class());
		}
	}
	
}
