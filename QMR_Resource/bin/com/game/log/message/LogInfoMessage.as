package com.game.log.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 日志信息
	 */
	public class LogInfoMessage extends Message {
	
		//日志信息
		private var _info: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//日志信息
			writeString(_info);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//日志信息
			_info = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 201201;
		}
		
		/**
		 * get 日志信息
		 * @return 
		 */
		public function get info(): String{
			return _info;
		}
		
		/**
		 * set 日志信息
		 */
		public function set info(value: String): void{
			this._info = value;
		}
		
	}
}