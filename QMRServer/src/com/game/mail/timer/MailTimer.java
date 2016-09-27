package com.game.mail.timer;

import java.util.List;

import com.game.db.bean.Q_mailBean;
import com.game.mail.manager.MailServerManager;
import com.game.timer.TimerEvent;

/**
 * 邮件计时器(可能需要服务器级的计时)
 *
 * @author 杨鸿岚
 */
public class MailTimer extends TimerEvent {

	private static int step = 1000 * 60;//一分钟
	private static int deletetime = 30 * 24 * 3600;//30天

	public MailTimer() {
		super(-1, step);
	}

	private int getdeletesendtime() {
		return ((int) System.currentTimeMillis() / 1000) - deletetime;
	}

	@Override
	public void action() {
		List<Q_mailBean> mailBeans = MailServerManager.getInstance().getQ_mailDao().selectbysendtime(getdeletesendtime());
		if (!mailBeans.isEmpty()) {
			for (int i = 0; i < mailBeans.size(); i++) {
				Q_mailBean q_mailBean = mailBeans.get(i);
				if (q_mailBean != null) {
					MailServerManager.getInstance().deleteMail(q_mailBean.getMail_id());
				}
			}
		}
	}
}