package com.game.server.thread;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.game.db.bean.Q_mailBean;
import com.game.db.dao.Q_mailDao;

public class SaveMailThread extends Thread {

	//数据库
	private Q_mailDao dao = new Q_mailDao();
	//日志
	private Logger log = LogManager.getLogger(SaveMailThread.class);
	//日志
	private Logger failedlog = LogManager.getLogger("SAVEMAILFAILED");
	//命令执行队列
	private LinkedBlockingQueue<MailInfo> mail_queue = new LinkedBlockingQueue<MailInfo>();
	//运行标志
	private boolean stop;
	//线程名称
	protected String threadName;
	
	boolean insertDB = true;
	
	private static int MAX_SIZE = 10000;
	
	public static int MAIL_UPDATE = 0;
	public static int MAIL_INSERT = 1;
	public static int MAIL_DELETE = 2;
	public static int MAIL_DELETEALLBYID = 3;

	public SaveMailThread(String threadName) {
		super(threadName);
		this.threadName = threadName;
	}

	public void run() {
		stop = false;
		while (!stop || mail_queue.size() > 0) {
			MailInfo mail = mail_queue.poll();
			if (mail == null) {
				try {
					synchronized (this) {
						wait();
					}
				} catch (InterruptedException e) {
					log.error("Save Mail Thread " + threadName + " Wait Exception:" + e.getMessage());
				}
			} else {
				try {
					if(mail_queue.size() > MAX_SIZE){
						insertDB = false;
					}
					if(insertDB){
						if (mail.getDeal() == MAIL_UPDATE) {
							if (dao.update(mail.getMail()) == 0) {
								log.error(String.format("邮件数据更新错误，邮件id[%s]", Long.toString(mail.getMail().getMail_id())));
							}
						} else if (mail.getDeal() == MAIL_INSERT) {
							log.error(String.format("邮件数据保存开始,邮件ID[%S]", Long.toString(mail.getMail().getMail_id())));
							if (dao.insert(mail.getMail()) == 0) {
								log.error(String.format("邮件数据保存错误，邮件id[%s]", Long.toString(mail.getMail().getMail_id())));
							}
						} else if (mail.getDeal() == MAIL_DELETE) {
							dao.delete(mail.getMail().getMail_id());
						} else if (mail.getDeal() == MAIL_DELETEALLBYID) {
							dao.deletebyuserid(mail.getMail().getReceiver_id());
						}
					}else{
						failedlog.error(mail.toString());
					}
				} catch (Exception e) {
					log.error("Mail Exception:", e);
					mail_queue.add(mail);
				}
			}
		}
	}

	public void stop(boolean flag) {
		stop = flag;
		try {
			synchronized (this) {
				notify();
			}
		} catch (Exception e) {
			log.error("Mail Thread " + threadName + " Notify Exception:" + e.getMessage());
		}
	}

	/**
	 * 处理邮件
	 *
	 * @param mail 邮件
	 * @param deal 0-save 1-delete 2-deleteallbyid
	 */
	public void dealMail(Q_mailBean mail, int deal) {
		try {
			this.mail_queue.add(new MailInfo(mail, deal));
			synchronized (this) {
				notify();
			}
		} catch (Exception e) {
			log.error("Role Thread " + threadName + " Notify Exception:" + e.getMessage());


		}
	}

	private class MailInfo {

		private Q_mailBean mail;
		private int deal;

		public MailInfo(Q_mailBean mail, int deal) {
			this.mail = mail;
			this.deal = deal;
		}

		public Q_mailBean getMail() {
			return mail;
		}

		public int getDeal() {
			return deal;
		}
		
		public String toString(){
			return mailtoString(this.mail);
		}
	}
	
	
	
	public String mailtoString(Q_mailBean mail){
		StringBuffer buf = new StringBuffer("[");
		//邮件id
		buf.append("mail_id:" + mail.getMail_id());
		//发送者名字
		buf.append("send_name:" + mail.getSend_name());
		//接受者id
		buf.append("receiver_id:" + mail.getReceiver_id());
		//接受者名字
		buf.append("receiver_name:" + mail.getReceiver_name());
		//发送时间
		buf.append("send_time:" + mail.getSend_time());
		//是否有附件（1是0否）
		buf.append("btAccessory:" + mail.getBtAccessory());
		//是否系统邮件（1是0否）
		buf.append("btSystem:" + mail.getBtSystem());
		//是否已读取（1是0否）
		buf.append("btRead:" + mail.getBtRead());
		//是否是回信（1是0否）
		buf.append("btReturn:" + mail.getBtReturn());
		//邮件数据
		buf.append("mail_data:" + mail.getMail_data());

		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
	
	
	
}
