package com.game.protect.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求服务器，获得验证码
	 */
	public class ReqVerificationToGameMessage extends Message {
	
		//邮箱
		private var _mail: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//邮箱
			writeString(_mail);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//邮箱
			_mail = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 164203;
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
		
	}
}