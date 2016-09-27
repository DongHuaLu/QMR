package com.game.horse.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 坐骑领取计时器
	 */
	public class ReshorseReceiveTimerMessage extends Message {
	
		//倒计时（秒）
		private var _second: int;
		
		//总时间（秒）
		private var _secondsum: int;
		
		//0不能领取，1可领取，2已经领取（关闭）
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//倒计时（秒）
			writeInt(_second);
			//总时间（秒）
			writeInt(_secondsum);
			//0不能领取，1可领取，2已经领取（关闭）
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//倒计时（秒）
			_second = readInt();
			//总时间（秒）
			_secondsum = readInt();
			//0不能领取，1可领取，2已经领取（关闭）
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 126109;
		}
		
		/**
		 * get 倒计时（秒）
		 * @return 
		 */
		public function get second(): int{
			return _second;
		}
		
		/**
		 * set 倒计时（秒）
		 */
		public function set second(value: int): void{
			this._second = value;
		}
		
		/**
		 * get 总时间（秒）
		 * @return 
		 */
		public function get secondsum(): int{
			return _secondsum;
		}
		
		/**
		 * set 总时间（秒）
		 */
		public function set secondsum(value: int): void{
			this._secondsum = value;
		}
		
		/**
		 * get 0不能领取，1可领取，2已经领取（关闭）
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0不能领取，1可领取，2已经领取（关闭）
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}