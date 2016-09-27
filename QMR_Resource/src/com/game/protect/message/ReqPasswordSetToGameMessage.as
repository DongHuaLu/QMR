package com.game.protect.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 新设置密码
	 */
	public class ReqPasswordSetToGameMessage extends Message {
	
		//密码
		private var _password: String;
		
		//邮箱
		private var _mail: String;
		
		//验证码
		private var _verification: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//密码
			writeString(_password);
			//邮箱
			writeString(_mail);
			//验证码
			writeString(_verification);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//密码
			_password = readString();
			//邮箱
			_mail = readString();
			//验证码
			_verification = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 164202;
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
		
		/**
		 * get 邮箱
		 * @return 
		 */
		public function get mail(): String{
			return _mail;
		}
		
		/**
		 * set 邮箱
		 */
		public function set mail(value: String): void{
			this._mail = value;
		}
		
		/**
		 * get 验证码
		 * @return 
		 */
		public function get verification(): String{
			return _verification;
		}
		
		/**
		 * set 验证码
		 */
		public function set verification(value: String): void{
			this._verification = value;
		}
		
	}
}