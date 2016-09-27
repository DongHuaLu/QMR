package com.game.recall.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回回归奖励信息
	 */
	public class ResBackPrizeMessage extends Message {
	
		//可领取奖励次数
		private var _times: int;
		
		//发起召回的玩家名称
		private var _name: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//可领取奖励次数
			writeByte(_times);
			//发起召回的玩家名称
			writeString(_name);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//可领取奖励次数
			_times = readByte();
			//发起召回的玩家名称
			_name = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 168106;
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
		
		/**
		 * get 发起召回的玩家名称
		 * @return 
		 */
		public function get name(): String{
			return _name;
		}
		
		/**
		 * set 发起召回的玩家名称
		 */
		public function set name(value: String): void{
			this._name = value;
		}
		
	}
}