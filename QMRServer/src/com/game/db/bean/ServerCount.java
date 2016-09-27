package com.game.db.bean;
/**
 * 
 * @author 赵聪慧
 * @2012-9-24 下午3:27:54
 */
public class ServerCount implements Cloneable {
	private int countkey;
	/**
	 * 计数类型 0永久 1时 2日 3周 4月 5年
	 */
	private int type;
	//计数
	private long count;
	@Override
	public ServerCount clone() throws CloneNotSupportedException {
		ServerCount clone = (ServerCount) super.clone();
		clone.setType(type);
		clone.setCount(count);
		clone.setCountkey(countkey);
		return clone;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public int getCountkey() {
		return countkey;
	}
	public void setCountkey(int countkey) {
		this.countkey = countkey;
	}
	

}
