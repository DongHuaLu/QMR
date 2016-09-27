package com.game.login.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 登陆
	 */
	public class ReqLoginMessage extends Message {
	
		//服务器Id
		private var _serverId: int;
		
		//玩家用户名
		private var _name: String;
		
		//玩家用户名
		private var _password: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//服务器Id
			writeInt(_serverId);
			//玩家用户名
			writeString(_name);
			//玩家用户名
			writeString(_password);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//服务器Id
			_serverId = readInt();
			//玩家用户名
			_name = readString();
			//玩家用户名
			_password = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 100201;
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
		 * get 玩家用户名
		 * @return 
		 */
		public function get name(): String{
			return _name;
		}
		
		/**
		 * set 玩家用户名
		 */
		public function set name(value: String): void{
			this._name = value;
		}
		
		/**
		 * get 玩家用户名
		 * @return 
		 */
		public function get password(): String{
			return _password;
		}
		
		/**
		 * set 玩家用户名
		 */
		public function set password(value: String): void{
			this._password = value;
		}
		
	}
}