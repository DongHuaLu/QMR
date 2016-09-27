package com.game.mina.impl;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.game.mina.IServer;
import com.game.mina.code.InnerServerProtocolCodecFactory;
import com.game.mina.handler.ServerProtocolHandler;
import com.game.server.Server;
import com.game.server.config.ClientServerConfig;
import com.game.server.config.ServerInfo;
import com.game.server.loader.ClientServerConfigXmlLoader;

public abstract class ClientServer extends Server implements IServer {
	
	protected Logger log = Logger.getLogger(ClientServer.class);
	//默认与每个网关服务器连接数量
	public static int gateSessionNumber=1;
	
	//默认与世界服务器连接数量
	public static int worldSessionNumber=1;
	
	//默认与公共服务器连接数量
	public static int publicSessionNumber=1;
	
	protected HashMap<Integer, List<IoSession>> gateSessions = new HashMap<Integer, List<IoSession>>();
	
	protected List<IoSession> worldSessions = new ArrayList<IoSession>();
	
	protected NioSocketConnector socket = null;
	
	protected ClientServer(String serverConfig) {
		this(serverConfig, gateSessionNumber, worldSessionNumber);
	}
	
	protected ClientServer(String serverConfig, int gSessionNumber, int wSessionNumber) {
		super(new ClientServerConfigXmlLoader().load(serverConfig));
		gateSessionNumber = gSessionNumber;
		worldSessionNumber = wSessionNumber;
	}
	
	@Override
	protected void init(){
		super.init();
	}
	
	@Override
	public void run(){
		super.run();
		
		socket = new NioSocketConnector();
		socket.getFilterChain().addLast("codec", new ProtocolCodecFilter(new InnerServerProtocolCodecFactory()));
		
		OrderedThreadPoolExecutor threadpool=new OrderedThreadPoolExecutor(500);
		socket.getFilterChain().addLast("threadPool", new ExecutorFilter(threadpool));
        
		int recsize = 512 * 1024;
		int sendsize = 1024 * 1024;

		SocketSessionConfig sc = socket.getSessionConfig();
		sc.setReceiveBufferSize(recsize);// 设置输入缓冲区的大小
		sc.setSendBufferSize(sendsize);// 设置输出缓冲区的大小
		//sc.setTcpNoDelay(true);// flush函数的调用 设置为非延迟发送，为true则不组装成大包发送，收到东西马上发出   
		sc.setSoLinger(0);
		
		//初始化端口
		socket.setHandler(new ServerProtocolHandler(this));
		
		ClientServerConfig config = (ClientServerConfig)this.serverConfig;
		
		if(config!=null){
			if(config.getWorldServer()!=null){
				int connected = 0;
		        while (connected < worldSessionNumber) {
			        //连接世界服务器
			        ConnectFuture connect = socket.connect(new InetSocketAddress(config.getWorldServer().getIp(), config.getWorldServer().getPort()));
					connect.awaitUninterruptibly(60 * 1000);
					
					if(!connect.isConnected()){
						try{
							Thread.sleep(5000);
						}catch (Exception e) {
							log.error(e, e);
						}
						continue;
					}
					
					IoSession session = connect.getSession();
					session.setAttribute("connect-server-id", config.getWorldServer().getId());
					session.setAttribute("connect-server-ip", config.getWorldServer().getIp());
					session.setAttribute("connect-server-port", config.getWorldServer().getPort());
					add(session, config.getWorldServer().getId(), IServer.WORLD_SERVER);
					//注册
					register(session, IServer.WORLD_SERVER);
					
					connected++;
		        }
			}
			
			for (int i = 0; i < config.getGateServers().size(); i++) {
				ServerInfo info = config.getGateServers().get(i);
				int connected = 0;
				
		        while (connected < gateSessionNumber) {
		        	//连接网关服务器
			        ConnectFuture connect = socket.connect(new InetSocketAddress(info.getIp(), info.getPort()));
					connect.awaitUninterruptibly();
					
					if(!connect.isConnected()){
						try{
							Thread.sleep(5000);
						}catch (Exception e) {
							log.error(e, e);
						}
						continue;
					}
					
					IoSession session = connect.getSession();
					session.setAttribute("connect-server-id", info.getId());
					session.setAttribute("connect-server-ip", info.getIp());
					session.setAttribute("connect-server-port", info.getPort());
					add(session, info.getId(), IServer.GATE_SERVER);
					//注册
					register(session, IServer.GATE_SERVER);
					
					connected++;
				}
			}
			
			if(config.getPublicServer()!=null){
				int connected = 0;
		        while (connected < publicSessionNumber) {
			        //连接世界服务器
			        ConnectFuture connect = socket.connect(new InetSocketAddress(config.getPublicServer().getIp(), config.getPublicServer().getPort()));
					connect.awaitUninterruptibly(60 * 1000);
					
					if(!connect.isConnected()){
						try{
							Thread.sleep(5000);
						}catch (Exception e) {
							log.error(e, e);
						}
						continue;
					}
					
					IoSession session = connect.getSession();
					session.setAttribute("connect-server-id", config.getPublicServer().getId());
					session.setAttribute("connect-server-ip", config.getPublicServer().getIp());
					session.setAttribute("connect-server-port", config.getPublicServer().getPort());
					add(session, config.getPublicServer().getId(), IServer.PUBLIC_SERVER);
					//注册
					register(session, IServer.PUBLIC_SERVER);
					
					connected++;
		        }
			}
		}
		connectComplete();
	}
	
	protected void reconnectPublic(int id, String ip, int port){
		int connected = 0;
        while (connected < publicSessionNumber) {
			//连接世界服务器
	        ConnectFuture connect = socket.connect(new InetSocketAddress(ip, port));
			connect.awaitUninterruptibly(60 * 1000);
			
			if(!connect.isConnected()){
				try{
					Thread.sleep(5000);
				}catch (Exception e) {
					log.error(e, e);
				}
				continue;
			}
			
			IoSession session = connect.getSession();
			session.setAttribute("connect-server-id", id);
			session.setAttribute("connect-server-ip", ip);
			session.setAttribute("connect-server-port", port);
			//注册
			register(session, IServer.PUBLIC_SERVER);
			
			connected++;
		}
	}
	
	/**
	 * 服务器连接完成事件
	 */
	protected abstract void connectComplete();
	
	/**
	 * 移除session
	 * @param session
	 * @param id
	 * @param type
	 */
	protected void removeSession(IoSession session, int id, int type){
		if(type==IServer.GATE_SERVER){
			synchronized (gateSessions) {
				List<IoSession> sessions = gateSessions.get(id);
				if(sessions!=null){
					sessions.remove(session);
				}
			}
		}else if(type==IServer.WORLD_SERVER){
			synchronized (worldSessions) {
				worldSessions.remove(session);
			}
		}
	}
	
	public List<IoSession> getWorldSessions(){
		return worldSessions;
	}
	
	public HashMap<Integer, List<IoSession>> getGateSessions(){
		return gateSessions;
	}
	
	public void add(IoSession session, int id, int type){
		if(type==IServer.GATE_SERVER){
			synchronized (gateSessions) {
				List<IoSession> sessions = gateSessions.get(id);
				if(sessions==null){
					sessions = new ArrayList<IoSession>();
					gateSessions.put(id, sessions);
				}
				sessions.add(session);
			}
		}else if(type==IServer.WORLD_SERVER){
			synchronized (worldSessions) {
				worldSessions.add(session);
			}
		}
	}
	/**
	 * 注册服务器
	 * @param session
	 */
	public abstract void register(IoSession session, int type);
}
