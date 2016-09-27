package com.game.dataserver.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 开始比赛
	 */
	public class ResStartMatchMessage extends Message {
	
		//服务器Id
		private var _serverId: int;
		
		//服务器平台
		private var _web: String;
		
		//账号id
		private var _userId: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//服务器Id
			writeInt(_serverId);
			//服务器平台
			writeString(_web);
			//账号id
			writeString(_userId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//服务器Id
			_serverId = readInt();
			//服务器平台
			_web = readString();
			//账号id
			_userId = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 203102;
		}
		
		/**
		 * get 服务器Id
		 * @return 
		 */
		public function get serverId(): int{
			return _serverId;
		}
		
		/**
		 * set 服务器Id
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
		
	}
}