package com.game.login.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 被顶替下线信息
	 */
	public class ResSubstituteMessage extends Message {
	
		//ip地址
		private var _ip: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//ip地址
			writeString(_ip);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//ip地址
			_ip = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 100103;
		}
		
		/**
		 * get ip地址
		 * @return 
		 */
		public function get ip(): String{
			return _ip;
		}
		
		/**
		 * set ip地址
		 */
		public function set ip(value: String): void{
			this._ip = value;
		}
		
	}
}