package com.game.login.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 创建账号后返回给前端
	 */
	public class ResCreateUserMessage extends Message {
	
		//用户ID
		private var _userId: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//用户ID
			writeString(_userId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//用户ID
			_userId = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 100110;
		}
		
		/**
		 * get 用户ID
		 * @return 
		 */
		public function get userId(): String{
			return _userId;
		}
		
		/**
		 * set 用户ID
		 */
		public function set userId(value: String): void{
			this._userId = value;
		}
		
	}
}