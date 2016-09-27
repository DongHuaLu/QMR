package com.game.mina.handler;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.game.mina.IServer;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-1
 * 
 * Mina服务器管理
 */
public class ServerProtocolHandler extends IoHandlerAdapter {

	protected static Logger log = Logger.getLogger(ServerProtocolHandler.class);
	//Mina服务器
	private IServer server;
	
	public ServerProtocolHandler(IServer server)
    {
		this.server = server;
    }

    public void sessionCreated(IoSession iosession)
        throws Exception
    {
    	server.sessionCreate(iosession);
    }

    public void sessionOpened(IoSession iosession)
        throws Exception
    {
    	server.sessionOpened(iosession);
    }

    public void sessionClosed(IoSession iosession)
        throws Exception
    {
    	server.sessionClosed(iosession);
    }

    public void sessionIdle(IoSession iosession, IdleStatus idlestatus)
        throws Exception
    {
    	server.sessionIdle(iosession, idlestatus);
    }

    public void exceptionCaught(IoSession iosession, Throwable cause)
        throws Exception
    {
        server.exceptionCaught(iosession, cause);
    }
    
    public void messageReceived(IoSession iosession, Object obj)
        throws Exception
    {
    	byte[] bytes = (byte[])obj;
    	
    	IoBuffer buf = IoBuffer.allocate(bytes.length);
    	
    	buf.put(bytes);
    	
    	buf.flip();
    	//处理命令
    	server.doCommand(iosession, buf);
    }

    public void messageSent(IoSession iosession, Object obj)
        throws Exception
    {
    }
}
