package com.game.monster.manager;

import org.apache.log4j.Logger;

import com.game.monster.structs.Hatred;
import com.game.pool.MemoryPool;

public class HatredManager {
	
	private MemoryPool<Hatred> pool = new MemoryPool<Hatred>(10000);
	
	private Logger log = Logger.getLogger(HatredManager.class);
	
	private static Object obj = new Object();
	//管理类实例
	private static HatredManager manager;
	
	private HatredManager(){}
	
	public static HatredManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new HatredManager();
			}
		}
		return manager;
	}
	
	public Hatred getHatred(){
		try{
			return pool.get(Hatred.class);
		}catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}
	
	public void removeHatred(Hatred hatred){
		pool.put(hatred);
	}
}
