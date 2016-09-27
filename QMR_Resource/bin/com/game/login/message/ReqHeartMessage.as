package com.game.login.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 心跳消息
	 */
	public class ReqHeartMessage extends Message {
	
		//当前时间
		private var _time: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前时间
			writeInt(_time);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前时间
			_time = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 100206;
		}
		
		/**
		 * get 当前时间
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 当前时间
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
	}
}