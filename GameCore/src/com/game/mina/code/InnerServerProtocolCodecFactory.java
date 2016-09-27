package com.game.mina.code;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-1
 * 
 * Mina编解码器工厂
 */
public class InnerServerProtocolCodecFactory implements ProtocolCodecFactory {

	//获得解码器
	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return new InnerServerProtocolDecoder();
	}
	
	//获得编码器
	@Override
	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return new InnerServerProtocolEncoder();
	}

}
