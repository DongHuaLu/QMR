package com.game.chat.timer;

import java.util.List;

import com.game.data.bean.Q_bulletinBean;
import com.game.manager.ManagerPool;
import com.game.prompt.structs.Notifys;
import com.game.timer.TimerEvent;
import com.game.utils.MessageUtil;
import com.game.utils.Symbol;
import com.game.utils.TimeUtil;

public class BulletinTimer extends TimerEvent {
	public BulletinTimer() {
		super(-1,1000*60);
	}

	@Override
	public void action() {
		List<Q_bulletinBean> datalist = ManagerPool.dataManager.q_bulletinContainer.getList();
		for (Q_bulletinBean q_bulletinBean : datalist) {
			String[] timeString = q_bulletinBean.getQ_time().split(Symbol.FENHAO);
			boolean is = false;
			for (String string : timeString) {
				if(TimeUtil.isNowSatisfiedBy(string)){
					is = true;
					break;
				}
			}
			
			
			if (is) {
				String[] typeString =q_bulletinBean.getQ_type().split(Symbol.FENHAO);
				int country = q_bulletinBean.getQ_country();
				for (String string : typeString) {
					if (string.equals("1") || string.equals("0")) {
						MessageUtil.notify_All_player(Notifys.SROLL, q_bulletinBean.getQ_content() );
					}
					
					if (string.equals("2")) {
						MessageUtil.notify_All_player(Notifys.CHAT_SYSTEM, q_bulletinBean.getQ_content() );
					}
				}
				

			}
		}
	}

	
	
	
	
	
	
}
