package com.game.cache;

import java.util.List;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-2-18
 * 
 * 内存缓存接口
 */
public interface Cache<K, V> {
	
	//获得缓存结果
	public V get(K key);
	
	//放入缓存
	public void put(K key, V value);
	
	//移除缓存
	public void remove(K key);
	
	//取得最近要更新的记录
	public List<V> getWaitingSave(int size);
}
