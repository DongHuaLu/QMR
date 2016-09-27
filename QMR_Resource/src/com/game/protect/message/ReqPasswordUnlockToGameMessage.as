package com.game.protect.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 输入密码解锁
	 */
	public class ReqPasswordUnlockToGameMessage extends Message {
	
		//密码
		private var _password: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//密码
			writeString(_password);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//密码
			_password = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 164204;
		}
		
		/**
		 * get 密码
		 * @return 
		 */
		public function get password(): String{
			return _password;
		}
		
		/**
		 * set 密码
		 */
		public function set password(value: String): void{
			this._password = value;
		}
		
	}
}