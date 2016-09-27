package com.game.message;

import java.io.UnsupportedEncodingException;

import org.apache.mina.core.buffer.IoBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class Bean{

	protected Logger LOGGER = LoggerFactory.getLogger(Bean.class);
	
//	protected IoBuffer _buf;
//	
//	public boolean write(IoBuffer _buf){
//		this._buf = _buf;
//		return write();
//	}
//	
//	public boolean read(IoBuffer _buf){
//		this._buf = _buf;
//		return read();
//	}
	
	public abstract boolean write(IoBuffer buf);
	
	public abstract boolean read(IoBuffer buf);
	
	protected void writeInt(IoBuffer buf, int value){
		buf.putInt(value);
	}
	
	protected void writeString(IoBuffer buf, String value){
		//为空处理
		if(value==null){
			buf.putInt(0);
			return;
		}
		
		try {
			byte[] bytes = value.getBytes("UTF-8");
			buf.putInt(bytes.length);
			buf.put(bytes);
		} catch (UnsupportedEncodingException e) {
			//抛出错误
			LOGGER.error("Encode String Error:" + e.getMessage());
		}
	}
	
//	protected void writeFloat(float value){
//		
//	}
	
	protected void writeLong(IoBuffer buf, long value){
		buf.putLong(value);
	}
	
	protected void writeBean(IoBuffer buf, Bean value){
		value.write(buf);
	}
	
	protected void writeShort(IoBuffer buf, int value){
		buf.putShort((short)value);
	}
	
	protected void writeShort(IoBuffer buf, short value){
		buf.putShort(value);
	}
	
	protected void writeByte(IoBuffer buf, byte value){
		buf.put(value);
	}
	
	protected int readInt(IoBuffer buf){
		return buf.getInt();
	}
	
	protected String readString(IoBuffer buf){
		int length = buf.getInt();
		if(length<=0) return null;
		if(buf.remaining() < length) return null;
		byte[] bytes = new byte[length];
		buf.get(bytes);
		try {
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			//抛出错误
			LOGGER.error("Decode String Error:" + e.getMessage());
		}
		return null;
	}
	
	protected long readLong(IoBuffer buf){
		return buf.getLong();
	}
	
//	protected float readFloat(){
//		return 0f;
//	}
	
	protected Bean readBean(IoBuffer buf, Class<? extends Bean> clazz){
		try{
			Bean bean = clazz.newInstance();
			bean.read(buf);
			return bean;
		}catch (IllegalAccessException e) {
			//抛出错误
			LOGGER.error("Decode Bean Error:" + e.getMessage());
		}catch (InstantiationException e) {
			//抛出错误
			LOGGER.error("Decode Bean Error:" + e.getMessage());
		}
		return null;
	}
	
	protected short readShort(IoBuffer buf){
		return buf.getShort();
	}
	
	protected byte readByte(IoBuffer buf){
		return buf.get();
	}
}
