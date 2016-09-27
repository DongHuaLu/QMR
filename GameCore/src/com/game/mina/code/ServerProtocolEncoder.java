package com.game.mina.code;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.game.message.Message;
import com.game.message.TransfersMessage;
import com.game.util.SessionUtil;
import com.game.util.ZLibUtils;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-1
 * 
 * Mina编码器
 */
public class ServerProtocolEncoder implements ProtocolEncoder {

	protected Logger log = Logger.getLogger("GATESESSIONCLOSE");
	@Override
	public void dispose(IoSession session) throws Exception {
	}

	public static ConcurrentHashMap<Integer, Long> packages = new ConcurrentHashMap<Integer, Long>();
	
	public static ConcurrentHashMap<Integer, Integer> packagenums = new ConcurrentHashMap<Integer, Integer>();
	
	public static ConcurrentHashMap<Integer, Integer> packagemax = new ConcurrentHashMap<Integer, Integer>();
	
	public static ConcurrentHashMap<Integer, Integer> packagemin = new ConcurrentHashMap<Integer, Integer>();
	
	public static int unzliblength = 0;
	
	public static int zliblength = 0;
	
	private static int MAX_SIZE = 1 * 1024 * 1024;
	
	@Override
	public void encode(IoSession session, Object obj, ProtocolEncoderOutput out)
			throws Exception {
		
		if(session.getScheduledWriteBytes() > MAX_SIZE){
//			log.error(session + "-->" + session.getScheduledWriteBytes() + "-->close");
//			session.close(true);
			SessionUtil.closeSession(session, "等待发送字节过多(" + session.getScheduledWriteBytes() + ")");
		}
		
		//long start = System.currentTimeMillis();
		if(obj instanceof Message){
			Message message = (Message)obj;
			IoBuffer buf = IoBuffer.allocate(100);
			buf.setAutoExpand(true);
			buf.setAutoShrink(true);
			buf.putInt(0);
			buf.putInt(message.getId());
			message.write(buf);
			buf.flip();
			buf.putInt(buf.limit() - Integer.SIZE/Byte.SIZE);
			buf.rewind();
			if(session.isConnected()){
				out.write(buf);
				out.flush();
			}
			
		}else if(obj instanceof TransfersMessage){
			TransfersMessage message = (TransfersMessage)obj;
			IoBuffer buf = IoBuffer.allocate(1024);
			buf.setAutoExpand(true);
			buf.setAutoShrink(true);
			//是否压缩
			boolean zlib = false;
			buf.putInt(0);
//			buf.putInt(message.getLength());
			buf.putInt(message.getId());
//			log.error(message.getId() + ":" + message.getBytes().length);
			if(message.getBytes().length > 512){
				unzliblength += message.getBytes().length;
				byte[] bytes = ZLibUtils.compress(message.getBytes());
				zliblength += bytes.length;
				buf.put(bytes);
				zlib = true;
//				log.error("zlib " + message.getId() + ":" + old + "-->" + zlength);
			}else{
				buf.put(message.getBytes());
			}
			buf.flip();
			int length = buf.limit() - Integer.SIZE/Byte.SIZE;
			if(zlib){
				length = (((int)1) << 24) | length;
			}
			buf.putInt(length);
			buf.rewind();
			
			if(buf.remaining() > 0){
				if(packages.containsKey(message.getId())){
					packages.put(message.getId(), packages.get(message.getId()) + buf.remaining() + 20);
					packagenums.put(message.getId(), packagenums.get(message.getId()) + 1);
					int max = packagemax.get(message.getId());
					if(max < buf.remaining()){
						packagemax.put(message.getId(), buf.remaining());
					}
					int min = packagemin.get(message.getId());
					if(min > buf.remaining()){
						packagemin.put(message.getId(), buf.remaining());
					}
				}else{
					packages.put(message.getId(), (long)buf.remaining() + 20);
					packagenums.put(message.getId(), 1);
					packagemax.put(message.getId(), buf.remaining());
					packagemin.put(message.getId(), buf.remaining());
				}
			}
			
			if(session.isConnected()){
				out.write(buf);
				out.flush();
			}
		}
		
	}

}
