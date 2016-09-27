package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 跳跃最大速度
	 */
	public class ResJumpMaxSpeedMessage extends Message {
	
		//跳跃最大速度
		private var _jumpmaxspeed: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//跳跃最大速度
			writeInt(_jumpmaxspeed);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//跳跃最大速度
			_jumpmaxspeed = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103135;
		}
		
		/**
		 * get 跳跃最大速度
		 * @return 
		 */
		public function get jumpmaxspeed(): int{
			return _jumpmaxspeed;
		}
		
		/**
		 * set 跳跃最大速度
		 */
		public function set jumpmaxspeed(value: int): void{
			this._jumpmaxspeed = value;
		}
		
	}
}