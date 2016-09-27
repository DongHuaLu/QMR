package com.game.mina.context;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-1
 * 
 * Mina服务器上下文
 */
public class ServerContext {

	//数据缓存
	private IoBuffer buff;
	
	public ServerContext(){
		buff = IoBuffer.allocate(256);
		buff.setAutoExpand(true);
		buff.setAutoShrink(true);
	}
	
	public void append(IoBuffer buff){
		this.buff.put(buff);
	}
	
	public IoBuffer getBuff(){
		return buff;
	}
	
}
