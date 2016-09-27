package com.game.mina.handler;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-1
 * 
 * Mina SSL服务器管理
 */
public class SSLServerProtocolHandler extends IoHandlerAdapter  {

	protected static Logger log = Logger.getLogger(SSLServerProtocolHandler.class);
	
	private static String security_ssl = "<policy-file-request/>";
    private static String allow_ssl = "<?xml version=\"1.0\"?><cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"*\"/></cross-domain-policy>\0";
    
	public SSLServerProtocolHandler()
    {
    }

    public void sessionCreated(IoSession iosession)
        throws Exception
    {
    }

    public void sessionOpened(IoSession iosession)
        throws Exception
    {
    	log.debug(iosession + " ssl create!");
    }

    public void sessionClosed(IoSession iosession)
        throws Exception
    {
    }

    public void sessionIdle(IoSession iosession, IdleStatus idlestatus)
        throws Exception
    {
    }

    public void exceptionCaught(IoSession iosession, Throwable cause)
        throws Exception
    {
    }

    public void messageReceived(IoSession iosession, Object obj)
        throws Exception
    {
    	IoBuffer buff = (IoBuffer)obj;
    	buff.flip();
    	//读取传过来的安全子陵
    	byte[] bytes = new byte[security_ssl.length()];
    	byte[] bytes2 = buff.array();
    	System.arraycopy(bytes2, 0, bytes, 0, Math.min(bytes.length, bytes2.length));
        String ssl = new String(bytes);
        log.error(iosession + " ssl send:" + ssl + "!");
        //比较安全指令是否正确
        if(ssl != null && security_ssl.equals(ssl))
        {
        	//返回安全验证通过指令
            bytes = allow_ssl.getBytes("UTF-8");
            IoBuffer out = IoBuffer.allocate(bytes.length);
            out.put(bytes);
            out.flip();
            iosession.write(out);
        }else{
        	iosession.close(true);
        }
    }

    public void messageSent(IoSession iosession, Object obj)
        throws Exception
    {
    	log.error(iosession + " close by sslsend!");
    	iosession.close(true);
    }
}
