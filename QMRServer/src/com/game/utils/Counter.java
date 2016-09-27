package com.game.utils;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 计数器	线程安全
 * @author 赵聪慧
 *
 */
public class Counter {
	private double sum;
	private int count;
	private ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
	
	public int getCount(){
		return count;
	}

	public void add(int num) {
		lockWrite();
		sum += num;
		count++;
		unLockWrite();
	}
	/**
	 * 求平均值
	 * @return
	 */
	public int getAVG(){
		lockRead();
		int result=(int) (sum/count);
		unLockRead();
		return result;
	}
	
	public void lockRead(){
		lock.readLock().lock();
	}
	public void lockWrite(){
		lock.writeLock().lock();
	}
	public void unLockRead(){
		lock.readLock().unlock();
	}
	public void unLockWrite(){
		lock.writeLock().unlock();
	}

	
}
