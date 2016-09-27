package com.game.horse.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送骑乘倒计时开始
	 */
	public class ResRidingCountdownMessage extends Message {
	
		//时间（秒）
		private var _time: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//时间（秒）
			writeByte(_time);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//时间（秒）
			_time = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 126102;
		}
		
		/**
		 * get 时间（秒）
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 时间（秒）
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
	}
}