package com.game.data.reload;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.game.data.manager.DataManager;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.utils.HttpUtil;
import com.game.utils.MessageUtil;

/**
 * 静态数据重加载管理器
 * @author 赵聪慧
 * 2012-2-25下午5:50:45
 */
public class ReLoadManager {
	private static final Logger logger=Logger.getLogger(ReLoadManager.class);
	
	private static final ReLoadManager manager=new ReLoadManager(); 
	
	ExecutorService service;
	
	private static String[] tables = new String[]{"q_map"};
	
	public static ReLoadManager getInstance(){
		return manager;
	}
	
	private ReLoadManager(){
		service=Executors.newSingleThreadExecutor();				
	}
	
	public void reLoad(final String tableName,final long roleID) {
		logger.info("接到重新load"+tableName+"指令");
		for (int i = 0; i < tables.length; i++) {
			if(tables[i].equals(tableName)){
				Player player = PlayerManager.getInstance().getPlayer(roleID);
				if(player!=null)
					MessageUtil.notify_player(player, Notifys.SUCCESS,"表" + tableName + "不支持重新加载");
				return;
			}
		}
		service.execute(new Runnable() {
			@Override
			public void run() {
				logger.info("reload "+tableName+" is starting");
				
					try {
						Player player = PlayerManager.getInstance().getPlayer(roleID);
						if(tableName!=null&&!tableName.equals("")){
							Field declaredField = DataManager.getInstance().getClass().getDeclaredField(tableName+"Container");
							int hashCode = declaredField.get(DataManager.getInstance()).hashCode();
							Class<?> cls = declaredField.getType();
							Object newInstance =cls.newInstance();
							Method method = cls.getMethod("load");
							method.invoke(newInstance);
							declaredField.set(DataManager.getInstance(),newInstance);
							int hashCode2 = declaredField.get(DataManager.getInstance()).hashCode();
							if(hashCode!=hashCode2){
								logger.info("reload "+tableName+" end");	
							}else{
								logger.info("reload "+tableName+" is faild"+hashCode+" "+hashCode2);
							}
							if(player!=null)
							MessageUtil.notify_player(player, Notifys.SUCCESS,"world重加载表{1}成功",tableName);
							
						}else{
							Field[] declaredFields = DataManager.getInstance().getClass().getDeclaredFields();
							for (Field field : declaredFields) {
								int hashCode = field.get(DataManager.getInstance()).hashCode();
								Class<?> cls = field.getType();
								Object newInstance =cls.newInstance();
								Method method = cls.getMethod("load");
								method.invoke(newInstance);
								field.set(DataManager.getInstance(),newInstance);
								int hashCode2 = field.get(DataManager.getInstance()).hashCode();
								if(hashCode!=hashCode2){
									logger.info("reload "+tableName+" end");	
								}else{
									logger.info("reload "+tableName+" is faild"+hashCode+" "+hashCode2);
								}		
								if(player!=null)
									MessageUtil.notify_player(player, Notifys.SUCCESS,"world重加载所有表成功",tableName);
							}
						}
						
					} catch (SecurityException e) {
					logger.error("run()", e);
						e.printStackTrace();
					} catch (NoSuchFieldException e) {
					logger.error("run()", e);
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
					logger.error("run()", e);
						e.printStackTrace();
					} catch (IllegalAccessException e) {
					logger.error("run()", e);
						e.printStackTrace();
					} catch (InstantiationException e) {
					logger.error("run()", e);
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
					logger.error("run()", e);
						e.printStackTrace();
					} catch (InvocationTargetException e) {
					logger.error("run()", e);
						e.printStackTrace();
					}				
				
				if (logger.isDebugEnabled()) {
					logger.debug("run() - end");
				}
			}
		});

		if (logger.isDebugEnabled()) {
			logger.debug("reLoad(String) - end");
		}
	}
	
	public void reLoadBybg(final String tableName, final String httpresult) {
		service.execute(new Runnable() {
			@Override
			public void run() {
				logger.info("reload " + tableName + " is starting");
				try {
					if (tableName != null && !tableName.equals("")) {
						Field declaredField = DataManager.getInstance().getClass().getDeclaredField(tableName + "Container");
						int hashCode = declaredField.get(DataManager.getInstance()).hashCode();
						Class<?> cls = declaredField.getType();
						Object newInstance = cls.newInstance();
						Method method = cls.getMethod("load");
						method.invoke(newInstance);
						declaredField.set(DataManager.getInstance(), newInstance);
						int hashCode2 = declaredField.get(DataManager.getInstance()).hashCode();
						int state = 0;
						if (hashCode != hashCode2) {
							state = 1;
							logger.info("reload " + tableName + " end");
						} else {
							state = 0;
							logger.info("reload " + tableName + " is faild" + hashCode + " " + hashCode2);
						}
						try {
							HttpUtil.wget(httpresult+"&result="+state+"&location=world");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						Field[] declaredFields = DataManager.getInstance().getClass().getDeclaredFields();
						for (Field field : declaredFields) {
							int hashCode = field.get(DataManager.getInstance()).hashCode();
							Class<?> cls = field.getType();
							Object newInstance = cls.newInstance();
							Method method = cls.getMethod("load");
							method.invoke(newInstance);
							field.set(DataManager.getInstance(), newInstance);
							int hashCode2 = field.get(DataManager.getInstance()).hashCode();
							int state = 0;
							if (hashCode != hashCode2) {
								state = 1;
								logger.info("reload " + tableName + " end");
							} else {
								state = 0;
								logger.info("reload " + tableName + " is faild" + hashCode + " " + hashCode2);
							}
							try {
								HttpUtil.wget(httpresult+"&result="+state+"&location=world");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				} catch (SecurityException e) {
					logger.error("run()", e);
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					logger.error("run()", e);
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					logger.error("run()", e);
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					logger.error("run()", e);
					e.printStackTrace();
				} catch (InstantiationException e) {
					logger.error("run()", e);
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					logger.error("run()", e);
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					logger.error("run()", e);
					e.printStackTrace();
				}
				if (logger.isDebugEnabled()) {
					logger.debug("run() - end");
				}
			}
		});
	}
}
