package com.game.protect.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 验证码冷却时间发送
	 */
	public class ResVerificationCoolingToGameMessage extends Message {
	
		//验证码冷却时间
		private var _time: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//验证码冷却时间
			writeInt(_time);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//验证码冷却时间
			_time = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 164103;
		}
		
		/**
		 * get 验证码冷却时间
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 验证码冷却时间
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
	}
}