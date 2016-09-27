package com.game.mina.code;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.game.mina.context.ServerContext;
import com.game.util.SessionUtil;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-1
 * 
 * Mina解码器
 */
public class ServerProtocolDecoder implements ProtocolDecoder {
	//属性名称
	private static final String CONTEXT = "context";

    private static String START_TIME = "start_time";
    
    private static String RECEIVE_COUNT = "receive_count";
    
	protected Logger log = Logger.getLogger(ServerProtocolDecoder.class);
	
//	private static Logger closelog = Logger.getLogger("GATESESSIONCLOSE");
	
	private static int MAX_SIZE = 10 * 1024;
	
	private static int MAX_COUNT = 30;
	
	@Override
	public void decode(IoSession session, IoBuffer buff, ProtocolDecoderOutput out)
			throws Exception {
		
		long startTime = 0;
    	if(session.containsAttribute(START_TIME)){
    		startTime = (Long)session.getAttribute(START_TIME);
    	}
    	int count = 0;
    	if(session.containsAttribute(RECEIVE_COUNT)){
    		count = (Integer)session.getAttribute(RECEIVE_COUNT);
    	}
    	
    	if(System.currentTimeMillis() - startTime > 1000){
    		if(count > 10) log.error(session + " --> sendmsg:" + count);
    		startTime = System.currentTimeMillis();
    		count = 0;
    	}
    	
    	count++;
    	
    	if(count > MAX_COUNT){
    		log.error(session + " --> sendmsg:" + count + "-->close");
//    		closelog.error(session + " --> sendmsg:" + count + "-->close");
//    		session.close(true);
    		SessionUtil.closeSession(session, "发送消息数量过多(" + count + ")");
    		return;
    	}else{
    		session.setAttribute(START_TIME, startTime);
    		session.setAttribute(RECEIVE_COUNT, count);
    	}
    	
		//初始化服务器上下文
		ServerContext context = null;
    	if(session.getAttribute(CONTEXT) != null) context = (ServerContext)session.getAttribute(CONTEXT);
    	if(context == null){
    		context = new ServerContext();
    		session.setAttribute(CONTEXT, context);
    	}
    	//读取信息
    	IoBuffer io = context.getBuff();
    	
    	//添加接收到的信息到服务器上下文
    	io.put(buff);
    	
    	do{
    		io.flip();
    		if(io.remaining() < Integer.SIZE/Byte.SIZE){
    			//剩余字节长度不足，等待下次信息
    			io.compact();
    			break;
    		}
    		//获得信息长度
    		int length = io.getInt();

    		if(length > MAX_SIZE || length <= 0){
//    			log.error(session + "-->msglength:" + length + "-->close");
//    			session.close(true);
    			int pre = 0;
    			if(session.containsAttribute("PREMESSAGE")){
    				pre = (Integer)session.getAttribute("PREMESSAGE");
    			}
    			SessionUtil.closeSession(session, "发送消息出错过长(" + length + "), 前一消息为(" + pre + ")");
    			break;
    		}
    		if(io.remaining() < length){
    			io.rewind();
    			//剩余字节长度不足，等待下次信息
    			io.compact();
    			break;
    		}else{
    			//读取指定长度的字节数
    			byte[] bytes = new byte[length];
    			io.get(bytes);
    			//返回字节数组
    			out.write(bytes);
    			
    			if(io.remaining() == 0){
    				io.clear();
    				break;
    			}else{
    				io.compact();
    			}
    		}
    	}while(true);
    	
	}

	@Override
	public void dispose(IoSession session) throws Exception {
		if(session.getAttribute(CONTEXT) != null) session.removeAttribute(CONTEXT);
	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {

	}
}
