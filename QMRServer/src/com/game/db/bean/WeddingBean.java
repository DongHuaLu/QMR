package com.game.db.bean;
/**婚宴BEAN
 * 
 * @author zhangrong
 *
 */
public class WeddingBean {
	//id
	private long id;
	
	//数据
	private String data;
	
	//是否删除 1 删除
	private int deleted;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
}
