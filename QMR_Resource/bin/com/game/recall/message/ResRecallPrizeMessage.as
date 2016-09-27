package com.game.recall.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回召回奖励信息
	 */
	public class ResRecallPrizeMessage extends Message {
	
		//可领取奖励次数
		private var _times: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//可领取奖励次数
			writeByte(_times);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//可领取奖励次数
			_times = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 168104;
		}
		
		/**
		 * get 可领取奖励次数
		 * @return 
		 */
		public function get times(): int{
			return _times;
		}
		
		/**
		 * set 可领取奖励次数
		 */
		public function set times(value: int): void{
			this._times = value;
		}
		
	}
}