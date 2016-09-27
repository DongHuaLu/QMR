package com.game.languageres.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 字符串资源管理器
 *
 * @author 杨鸿岚
 */
public class ResManager {

	private Logger log = Logger.getLogger(ResManager.class);
	private static Object obj = new Object();
	//字符串资源管理器实例
	private static ResManager manager;

	private ResManager() {
		String language = "cn";
		gameres = new HashMap<String, String>();
		FileInputStream configis = null;
		try{
			String configFile = "world-config/languageres/language-config.properties";
			File configfile = new File(configFile);
			Properties configprop = new Properties();
			configis = new FileInputStream(configfile);
			configprop.load(configis);
			language = configprop.getProperty("language");
		}catch (Exception e) {
			log.error(e, e);
		}finally{
			try{
				if(configis!=null) configis.close();
			}catch (Exception e) {
				log.error(e, e);
			}
		}

		InputStreamReader isr = null;
		FileInputStream is = null;
		try{
			String propesFile = "world-config/languageres/worldres_" + language + ".properties";
			File file = new File(propesFile);
			is = new FileInputStream(file);
			isr = new InputStreamReader(is, "UTF-8");
			Properties prop = new Properties();
			prop.load(isr);
			
			Iterator<Entry<Object, Object>> iterator = prop.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<Object, Object> entry = iterator.next();
				if (entry != null) {
					String Key = (String) entry.getKey();
					String Value = (String) entry.getValue();
					gameres.put(Key, Value);
				}
			}
		} catch (Exception e) {
			log.error("资源字符串出错");
			log.error(e, e);
			gameres = null;
		}finally{
			try{
				if(is!=null) is.close();
				if(isr!=null) isr.close();
			}catch (Exception e) {
				log.error(e, e);
			}
		}
		if (gameres == null){
			gameres = new HashMap<String, String>();
		}
		if (gameres.isEmpty()) {
			throw new NullPointerException("字符串资源加载失败");
		}
		log.info("字符串资源加载成功");
	}

	public static ResManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new ResManager();
			}
		}
		return manager;
	}
	//字符串资源
	private HashMap<String, String> gameres;

	public String getString(String Key) {
		if (gameres != null && !gameres.isEmpty()) {
			if (gameres.containsKey(Key)) {
				return gameres.get(Key);
			} else {
				return Key;
			}
		} else {
			return Key;
		}
	}
}
