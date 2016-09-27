package com.game.mina.impl;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.game.mina.IServer;
import com.game.mina.code.InnerServerProtocolCodecFactory;
import com.game.mina.handler.ServerProtocolHandler;
import com.game.server.Server;
import com.game.server.config.InnerServerConfig;
import com.game.server.loader.InnerServerConfigXmlLoader;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-1
 * 
 * Mina内部服务器
 */
public abstract class InnerServer extends Server implements IServer {

	//服务器数据监听端口
	private int port;
	
	protected NioSocketAcceptor acceptor;
	
	/**
	 * Mina服务器初始化
	 */
	protected InnerServer(String serverConfig) {
		super(new InnerServerConfigXmlLoader().load(serverConfig));
	}
	
	@Override
	protected void init(){
		super.init();
		
		this.port = ((InnerServerConfig)this.serverConfig).getPort();
	}
	
	@Override
	public void run(){
		super.run();
		//Mina连接线程启动
		new Thread(new ConnectServer(this)).start();
	}
	
	/**
	 * Session关闭事件
	 */
	public abstract void sessionClosed(IoSession iosession);
	
	/**
	 * Session出错事件
	 */
	public abstract void exceptionCaught(IoSession session, Throwable cause);
	
	//执行Mina服务器接受到的命令
	public abstract void doCommand(IoSession iosession, IoBuffer buf);
	
	//Mina数据连接线程
	private class ConnectServer implements Runnable{
		
		//日志
		private Logger log = LogManager.getLogger(ConnectServer.class);
		
		//Mina服务器
		private InnerServer server;
		
		public ConnectServer(InnerServer server){
			this.server = server;
		}
		
		@Override
		public void run() {
			//注册Mina Nio端口接收
			acceptor = new NioSocketAcceptor();
			
	        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
	        //注册数据编解码器
	        chain.addLast("codec", new ProtocolCodecFilter(new InnerServerProtocolCodecFactory()));
	        //注册日志管理
	        //chain.addLast("logger", new LoggingFilter());
	        
	        OrderedThreadPoolExecutor threadpool=new OrderedThreadPoolExecutor(500);
	        chain.addLast("threadPool", new ExecutorFilter(threadpool));
	        
	        int recsize = 512 * 1024;
			int sendsize = 1024 * 1024;
			//int timeout =10;

			acceptor.setReuseAddress(true);
			
			SocketSessionConfig sc = acceptor.getSessionConfig();
			sc.setReuseAddress(true);// 设置每一个非主监听连接的端口可以重用
			sc.setReceiveBufferSize(recsize);// 设置输入缓冲区的大小
			sc.setSendBufferSize(sendsize);// 设置输出缓冲区的大小
			sc.setTcpNoDelay(true);// flush函数的调用 设置为非延迟发送，为true则不组装成大包发送，收到东西马上发出   
			sc.setSoLinger(0);
			//sc.setIdleTime(IdleStatus.READER_IDLE, timeout);


	        //绑定Mina服务器管理模块
	        acceptor.setHandler(new ServerProtocolHandler(server));
	        try{
	        	//绑定服务器数据监听端口，启动服务器
	        	acceptor.bind(new InetSocketAddress(port));
	        	log.info("Inner Server " + server.getServerName() + " Start At Port " + port);
	        }catch (IOException e) {
	        	log.error("Inner Server " + server.getServerName() + " Port " + port + "Already Use:" + e.getMessage());
	        	System.exit(1);
			}
		}
		
	}
}
