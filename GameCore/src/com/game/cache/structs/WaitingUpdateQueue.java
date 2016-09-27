package com.game.cache.structs;

import java.io.Serializable;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.mina.util.ConcurrentHashSet;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-2-18
 * 
 * 更新等待队列（实现队列中含有值情况下不会重复添加）
 */
public class WaitingUpdateQueue<V> implements Serializable {

	private static final long serialVersionUID = -6020192336030291965L;
	//Value队列
	private ConcurrentLinkedQueue<V> queue = new ConcurrentLinkedQueue<V>();
	//Value Map
	private ConcurrentHashSet<V> set = new ConcurrentHashSet<V>();
	
	/**
	 * 添加到队列中
	 * @param v
	 */
	public void add(V value){
		//未添加到过队列
		if(!set.contains(value)){
			//Value添加到Set中
			set.add(value);
			//Value添加到队列中
			queue.add(value);
		}
	}
	
	/**
	 * 获得队列头部元素并移除
	 * @return
	 */
	public V poll(){
		//取得队列头部
		V value = queue.poll();
		//从队列中移除Value
		if(value!=null) set.remove(value);
		return value;
	}
	
	/**
	 * 是否包含元素
	 * @param value
	 * @return
	 */
	public boolean contains(V value){
		return set.contains(value);
	}
	
	/**
	 * 移除元素
	 * @param value
	 */
	public void remove(V value){
		set.remove(value);
		queue.remove(value);
	}
}
