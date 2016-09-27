package com.game.txconsume.manager;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

import com.game.db.bean.TxConsumeLog;
import com.game.db.dao.TxConsumeLogDAO;
import com.game.txconsume.thread.TxConsumeConfirmThread;

public class TxConsumeManager {

	private static Logger log = Logger.getLogger(TxConsumeManager.class);
	
	private static TxConsumeManager instance = new TxConsumeManager();
	private TxConsumeManager(){
		
	}
	public static TxConsumeManager getInstance(){
		return instance;
	}
	private TxConsumeLogDAO dao = new TxConsumeLogDAO();
	private ThreadPoolExecutor txConfirmExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5); //保持5条线程的线程池
	public ThreadPoolExecutor getTxConfirmExecutor() {
		return txConfirmExecutor;
	}
	
	public void initTxConfirmExecutor(){
		//从数据库读出所有需要确认的 isconfirm=0 并且是 当前5分钟以内的
		long now = System.currentTimeMillis();
		long last5min = now-5*60*1000;
		try {
			List<TxConsumeLog> consumelist = dao.selectByIsconfirmTime(0, last5min);
			for(TxConsumeLog consumelog: consumelist){
				putConfirmQueue(consumelog);
			}
		} catch (SQLException e) {
			log.error(e, e);
		}
	}
	public void putConfirmQueue(TxConsumeLog consumelog){
		getTxConfirmExecutor().execute(new TxConsumeConfirmThread(consumelog, dao));
	}
	
	public int getActiveCount(){
		return txConfirmExecutor.getActiveCount();
	}
}
