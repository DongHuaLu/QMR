package com.game.server;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.game.server.config.ServerConfig;

/**
 * 服务器初始化
 * @author heyang
 *
 */
public abstract class Server implements Runnable {
	
	//服务器名字
	private String server_name;
	//服务器Id
	public static int server_id;
	//服务器平台
	private String server_web;
	//Mina服务器配置信息
	protected ServerConfig serverConfig;
	//默认主线程
	public static final String DEFAULT_MAIN_THREAD = "Main";
	
	/**
	 * @param configFile 服务器配置信息
	 */
	protected Server(ServerConfig serverConfig){
		this.serverConfig = serverConfig;
		
		if(this.serverConfig!=null){
			// 初始化运行时
			init();
		}
	}
	
	//服务器初始化
	protected void init(){
		//获得服务器名称
		server_name = this.serverConfig.getName();
		
		server_id = this.serverConfig.getId();
		
		server_web = this.serverConfig.getWeb();
	}
	
	//服务器运行
	public void run(){
		
		//监听关闭命令线程
//	    new Thread(new CloseByCommand(new BufferedReader(new InputStreamReader(System.in)))).start();
	    
		//采集器启动
//		new Thread(new Recorder()).start();
		
	    //注册服务器关闭线程
	    Runtime.getRuntime().addShutdownHook(new Thread(new CloseByExit(server_name)));
	}
	
	/**
	 * 获得服务器名称
	 * @return 服务器名称
	 */
	public String getServerName(){
		return this.server_name;
	}
	
	/**
	 * 获得服务器ID
	 * @return 服务器ID
	 */
	public int getServerId(){
		return server_id;
	}
	
	/**
	 * 获得服务器平台
	 * @return 服务器平台
	 */
	public String getServerWeb(){
		return server_web;
	}
	
	/**
	 * 服务器关闭事件
	 */
	protected abstract void stop();
	
//	//服务器监听关闭线程
//	private class CloseByCommand implements Runnable{
//
//		//日志
//		private Logger log = LogManager.getLogger(CloseByCommand.class);
//		
//		private BufferedReader br;
//		
//		public CloseByCommand(BufferedReader br){
//			this.br = br;
//		}
//		
//		@Override
//		public void run() {
//			while(true){
//				try{
//					//读取输入命令
//					String s = br.readLine();
//					if(Config.CLOSE_COMMAND.equals(s)){
//						//服务器关闭
//						System.exit(0);
//					}else Thread.sleep(2000);
//				}catch (IOException e) {
//					log.error("Close By Command Read Exception:" + e.getMessage());
//				}catch (InterruptedException e) {
//					log.error("Close By Command Sleep Exception:" + e.getMessage());
//				}
//			}
//		}
//		
//	}

	//服务器关闭线程
	private class CloseByExit implements Runnable{
		
		//日志
		private Logger log = LogManager.getLogger(CloseByExit.class);
		
		//服务器名字
		private String server_name;
		
		public CloseByExit(String server_name){
			this.server_name = server_name;
		}
		
		@Override
		public void run() {
			//执行关闭事件
			stop();
			log.info(this.server_name + " Stop!");
		}
		
	}
	
	
}

