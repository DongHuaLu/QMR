package com.game.cache.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.game.cache.Cache;
import com.game.cache.structs.LRULinkedHashMap;
import com.game.cache.structs.WaitingUpdateQueue;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-2-18
 * 
 * 内存缓存类实现
 */
public class MemoryCache<K, V> implements Cache<K, V>, Serializable {
	
	private static final long serialVersionUID = -3656956459941919920L;
	
	//private static Object obj = new Object();
	//缓存最大容量
	private static int MAX_SIZE = 5000;
	//每次保存数据量
	private static int PER_SAVE = 5;
	//每次保存数据量
	protected int saveSize;
	//内存缓存
	private LRULinkedHashMap<K, V> cache;
	//更新队列
	private WaitingUpdateQueue<V> queue = new WaitingUpdateQueue<V>();
	//丢弃缓存
//	private ConcurrentHashMap<K, V> drop = new ConcurrentHashMap<K, V>();
	
	public MemoryCache(){
		this(MAX_SIZE, PER_SAVE);
	}
	
	public MemoryCache(int maxSize, int saveSize){
		cache = new LRULinkedHashMap<K, V>(maxSize);
		this.saveSize = saveSize;
	}
	/**
	 * 添加到缓存中
	 */
	@Override
	public synchronized void put(K key, V value){
		//缓存中已存在
		if(cache.containsKey(key)){
			//添加到等待更新队列
			queue.add(value);
			return;
		}
		//缓存达到最大
//		if(cache.size() == MAX_SIZE){
//			//移除最不常用
//			Iterator<Map.Entry<K, V>> it = cache.entrySet().iterator();
//			int i = 0;
//			while(it.hasNext() && i < PER_DROP){
//				Map.Entry<K, V> entry = it.next();
//				it.remove();
//				//放入丢弃缓存
//				drop.put(entry.getKey(), entry.getValue());
//				i++;
//			}
//		}
		//放入缓存
		cache.put(key, value);
		
//		System.out.println("cache put " + key + " size -------->" + cache.size());
//		System.out.println("cache contains " + key + " -------->" + cache.containsKey(key));
	}
	
	/**
	 * 从缓存中获得数据
	 */
	@Override
	public V get(K key) {
		//缓存中取数据
		V value = cache.get(key);
//		//数据不存在
//		if(value==null){
//			//丢弃缓存中取数据
//			value = drop.get(key);
//			//数据存在，重新放入缓存
//			if(value!=null){
//				drop.remove(key);
//				put(key, value);
//			}
//		}
		return value;
	}
	
	/**
	 * 从缓存中移除数据
	 */
	@Override
	public void remove(K key){
		//缓存中取数据
		V value = cache.get(key);
//		//数据不存在
//		if(value==null){
//			//丢弃缓存中取数据
//			value = drop.get(key);
//		}
//		
		if(value!=null){
			//缓存移除数据
			cache.remove(key);
			//更新队列移除数据
			queue.remove(value);
//			//丢弃缓存移除数据
//			drop.remove(key);
		}
	}
	
	/**
	 * 获得更新数据
	 */
	@Override
	public List<V> getWaitingSave(int size) {
		//清空丢弃缓存中的内容
//		Iterator<Map.Entry<K, V>> it = drop.entrySet().iterator();
//		while(it.hasNext()){
//			Map.Entry<K, V> entry = it.next();
//			//如果等待更新队列中含有该数据，暂缓清除，否则清除
//			if(!queue.contains(entry.getValue())){
//				it.remove();
//			}
//		}
		ArrayList<V> waiting = new ArrayList<V>();
		//保存数量
		int i = 0;
		//队列弹出数据
		V value = queue.poll();
		while (value != null) {
			waiting.add(value);
			i++;
			if(i == size) break;
			//队列弹出数据
			value = queue.poll();
		}
		return waiting;
	}
	
	/**
	 * 获得全部更新数据
	 */
	public List<V> getAllWaitingSave() {
		ArrayList<V> waiting = new ArrayList<V>();
		//队列弹出数据
		V value = queue.poll();
		while (value != null) {
			waiting.add(value);
			//队列弹出数据
			value = queue.poll();
		}
		return waiting;
	}
	
	public LRULinkedHashMap<K, V> getCache(){
		return cache;
	}
}
