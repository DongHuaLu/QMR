package com.game.message;

import java.util.ArrayList;
import java.util.List;

public class TransfersMessage {

	private int id;
	
	private long sendId;
	
	private List<Long> roleIds = new ArrayList<Long>(); 
	
	private byte[] bytes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getSendId() {
		return sendId;
	}

	public void setSendId(long sendId) {
		this.sendId = sendId;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
	public int getLength(){
		return bytes.length + Integer.SIZE / Byte.SIZE;
	}
	
	public int getLengthWithRole(){
		return bytes.length + Integer.SIZE / Byte.SIZE + Long.SIZE / Byte.SIZE + roleIds.size() * Long.SIZE / Byte.SIZE + Integer.SIZE / Byte.SIZE;
	}
}
