package com.game.dataserver.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 切换服务器
	 */
	public class ResChangeServerMessage extends Message {
	
		//公共游戏服务器Id
		private var _serverId: int;
		
		//服务器平台
		private var _web: String;
		
		//账号id
		private var _userId: String;
		
		//角色id
		private var _dataServerPlayerId: long;
		
		//公共游戏服务器Ip
		private var _serverIp: String;
		
		//公共游戏服务器port
		private var _serverPort: int;
		
		//公共游戏服务器sslport
		private var _serverSSLPort: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//公共游戏服务器Id
			writeInt(_serverId);
			//服务器平台
			writeString(_web);
			//账号id
			writeString(_userId);
			//角色id
			writeLong(_dataServerPlayerId);
			//公共游戏服务器Ip
			writeString(_serverIp);
			//公共游戏服务器port
			writeInt(_serverPort);
			//公共游戏服务器sslport
			writeInt(_serverSSLPort);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//公共游戏服务器Id
			_serverId = readInt();
			//服务器平台
			_web = readString();
			//账号id
			_userId = readString();
			//角色id
			_dataServerPlayerId = readLong();
			//公共游戏服务器Ip
			_serverIp = readString();
			//公共游戏服务器port
			_serverPort = readInt();
			//公共游戏服务器sslport
			_serverSSLPort = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 203101;
		}
		
		/**
		 * get 公共游戏服务器Id
		 * @return 
		 */
		public function get serverId(): int{
			return _serverId;
		}
		
		/**
		 * set 公共游戏服务器Id
		 */
		public function set serverId(value: int): void{
			this._serverId = value;
		}
		
		/**
		 * get 服务器平台
		 * @return 
		 */
		public function get web(): String{
			return _web;
		}
		
		/**
		 * set 服务器平台
		 */
		public function set web(value: String): void{
			this._web = value;
		}
		
		/**
		 * get 账号id
		 * @return 
		 */
		public function get userId(): String{
			return _userId;
		}
		
		/**
		 * set 账号id
		 */
		public function set userId(value: String): void{
			this._userId = value;
		}
		
		/**
		 * get 角色id
		 * @return 
		 */
		public function get dataServerPlayerId(): long{
			return _dataServerPlayerId;
		}
		
		/**
		 * set 角色id
		 */
		public function set dataServerPlayerId(value: long): void{
			this._dataServerPlayerId = value;
		}
		
		/**
		 * get 公共游戏服务器Ip
		 * @return 
		 */
		public function get serverIp(): String{
			return _serverIp;
		}
		
		/**
		 * set 公共游戏服务器Ip
		 */
		public function set serverIp(value: String): void{
			this._serverIp = value;
		}
		
		/**
		 * get 公共游戏服务器port
		 * @return 
		 */
		public function get serverPort(): int{
			return _serverPort;
		}
		
		/**
		 * set 公共游戏服务器port
		 */
		public function set serverPort(value: int): void{
			this._serverPort = value;
		}
		
		/**
		 * get 公共游戏服务器sslport
		 * @return 
		 */
		public function get serverSSLPort(): int{
			return _serverSSLPort;
		}
		
		/**
		 * set 公共游戏服务器sslport
		 */
		public function set serverSSLPort(value: int): void{
			this._serverSSLPort = value;
		}
		
	}
}