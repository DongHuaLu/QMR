package com.game.longyuan.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 计时器开始
	 */
	public class ResLongYuanTimerMessage extends Message {
	
		//是否播放倒计时特效,1开始播放，0不处理
		private var _status: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//是否播放倒计时特效,1开始播放，0不处理
			writeByte(_status);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//是否播放倒计时特效,1开始播放，0不处理
			_status = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 115105;
		}
		
		/**
		 * get 是否播放倒计时特效,1开始播放，0不处理
		 * @return 
		 */
		public function get status(): int{
			return _status;
		}
		
		/**
		 * set 是否播放倒计时特效,1开始播放，0不处理
		 */
		public function set status(value: int): void{
			this._status = value;
		}
		
	}
}