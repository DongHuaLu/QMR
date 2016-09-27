package com.game.txconsume.thread;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.game.db.bean.TxConsumeLog;
import com.game.db.dao.TxConsumeLogDAO;
import com.game.dblog.LogService;
import com.game.txconsume.log.TxConsumesLog;
import com.game.txconsume.manager.TxConsumeManager;
import com.game.utils.HttpUtil;

public class TxConsumeConfirmThread implements Runnable {

	private static Logger log = Logger.getLogger(TxConsumeConfirmThread.class);
	private static Logger txlog = Logger.getLogger("txlog");
	
	private TxConsumeLog txconsumelog;
	private TxConsumeLogDAO dao;
	
	public TxConsumeConfirmThread(TxConsumeLog txconsumelog, TxConsumeLogDAO dao) {
		super();
		this.txconsumelog = txconsumelog;
		this.dao = dao;
	}

	@Override
	public void run() {
		//最长等待时间是5分钟
		TxConsumesLog tlog = new TxConsumesLog();
		tlog.setUsername(txconsumelog.getUsername());
		tlog.setUserid(txconsumelog.getUserid());
		tlog.setRoleid(txconsumelog.getRoleid());
		tlog.setState(2); // 2次确认
		
		long now = System.currentTimeMillis();
		long start = txconsumelog.getTimes();
		if(now - start > 5*60*1000){  //此条记录已经超过5分钟确认时间
			txconsumelog.setIsconfirm(2); //标识为5分钟超时
			txconsumelog.setConfirmmsg("超时没有得到确认信息");
		}else{
			String httpresult = "";
			try {
				Map<String, String> paramsmap = new HashMap<String, String>();
				paramsmap.put("desc", txconsumelog.getDesc());
				httpresult = HttpUtil.get(txconsumelog.getCallbackurl(),paramsmap, 2, 8); //2秒连接超时 8秒数据读取超时 一次10秒
			} catch (Exception e) {
				log.error(e, e);
			}
			if(StringUtils.isBlank(httpresult)){ //无返回结果
				if(System.currentTimeMillis()-start>5*60*1000){ //这条记录已经超过5分钟的确认时间了 
					txconsumelog.setIsconfirm(2); //标识为5分钟超时
					txconsumelog.setConfirmmsg("超时没有得到确认信息");
					tlog.setIsconfirm(2);
					tlog.setConfirmmsg("超时");
				}else{ //这条记录还没过5分钟确认时间 重新放入线程池 以便 再次执行
					TxConsumeManager.getInstance().putConfirmQueue(txconsumelog);
					tlog.setIsconfirm(-1);
					tlog.setConfirmmsg("再次放入线程池");
				}
			}else{ //有返回结果 记录
				txconsumelog.setIsconfirm(1); //已经确认
				txconsumelog.setConfirmmsg(httpresult); //php返回的确认内容
				tlog.setIsconfirm(1);
				tlog.setConfirmmsg(httpresult);
			}
		}
		int result = dao.update(txconsumelog);
		log.error("here");
		txlog.error("here");
		if(result<0){
			log.error("txconsumelog update fail["+txconsumelog.toString()+"]"); 
			txlog.error("txconsumelog update fail["+txconsumelog.toString()+"]");
		}
		//数据库日志
		LogService.getInstance().execute(tlog);
	}

}
