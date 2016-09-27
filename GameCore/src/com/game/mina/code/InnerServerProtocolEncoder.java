package com.game.mina.code;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.game.message.Message;
import com.game.message.TransfersMessage;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-1
 * 
 * Mina编码器
 */
public class InnerServerProtocolEncoder implements ProtocolEncoder {

	@Override
	public void dispose(IoSession session) throws Exception {
	}

//	private static int package_num = 0;
//	
//	private static long package_length = 0;
//	
//	private static long start = 0l;
	
//	private static ConcurrentHashMap<Integer, Long> packages = new ConcurrentHashMap<Integer, Long>();
	
	@Override
	public void encode(IoSession session, Object obj, ProtocolEncoderOutput out)
			throws Exception {
//		long start = System.currentTimeMillis();
		if(obj instanceof Message){
			Message message = (Message)obj;
			IoBuffer buf = IoBuffer.allocate(100);
			buf.setAutoExpand(true);
			buf.setAutoShrink(true);
			buf.putInt(0);
			buf.putInt(message.getId());
			buf.putLong(message.getSendId());
			buf.putInt(message.getRoleId().size());
			for (int i = 0; i < message.getRoleId().size(); i++) {
				buf.putLong(message.getRoleId().get(i));
			}
			message.write(buf);
			buf.flip();
			buf.putInt(buf.limit() - Integer.SIZE/Byte.SIZE);
			buf.rewind();
			
//			long end = System.currentTimeMillis();
//			if(buf.remaining() > 0){
//				System.out.println("打包消息：" + message.getId());
//				System.out.println("打包发送人数：" + message.getRoleId().size());
//				System.out.println("打包消息内容：" + message.toString());
//				System.out.println("打包长度：" + buf.remaining());
//				System.out.println("发包时间：" + System.currentTimeMillis());
//				System.out.println("打包时间：" + ((end - start)));
//				if(packages.containsKey(message.getId())){
//					packages.put(message.getId(), packages.get(message.getId()) + buf.remaining());
//				}else{
//					packages.put(message.getId(), (long)buf.remaining());
//				}
//				System.out.println("总发送长度：" + packages.get(message.getId()));
//			}
			
			out.write(buf);
			out.flush();
		}else if(obj instanceof TransfersMessage){
			TransfersMessage message = (TransfersMessage)obj;
			IoBuffer buf = IoBuffer.allocate(1024);
			buf.setAutoExpand(true);
			buf.setAutoShrink(true);
			buf.putInt(message.getLengthWithRole());
			buf.putInt(message.getId());
			buf.putLong(message.getSendId());
			buf.putInt(message.getRoleIds().size());
			for (int i = 0; i < message.getRoleIds().size(); i++) {
				buf.putLong(message.getRoleIds().get(i));
			}
			buf.put(message.getBytes());
			buf.flip();
			out.write(buf);
			out.flush();
		}
	}

}
