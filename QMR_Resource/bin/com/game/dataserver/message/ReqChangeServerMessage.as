package com.game.dataserver.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 跨服务器
	 */
	public class ReqChangeServerMessage extends Message {
	
		//服务器Id
		private var _serverId: int;
		
		//服务器平台
		private var _web: String;
		
		//副本Id
		private var _zoneId: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//服务器Id
			writeInt(_serverId);
			//服务器平台
			writeString(_web);
			//副本Id
			writeInt(_zoneId);
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
			//副本Id
			_zoneId = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 203201;
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
		 * get 副本Id
		 * @return 
		 */
		public function get zoneId(): int{
			return _zoneId;
		}
		
		/**
		 * set 副本Id
		 */
		public function set zoneId(value: int): void{
			this._zoneId = value;
		}
		
	}
}