package com.game.toplist.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送给前端竞技场排行状态
	 */
	public class ResToplistArenaGiftStateMessage extends Message {
	
		//竞技场排行榜领奖状态  0:尚未领取 1:已经领取
		private var _giftState: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//竞技场排行榜领奖状态  0:尚未领取 1:已经领取
			writeByte(_giftState);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//竞技场排行榜领奖状态  0:尚未领取 1:已经领取
			_giftState = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 142152;
		}
		
		/**
		 * get 竞技场排行榜领奖状态  0:尚未领取 1:已经领取
		 * @return 
		 */
		public function get giftState(): int{
			return _giftState;
		}
		
		/**
		 * set 竞技场排行榜领奖状态  0:尚未领取 1:已经领取
		 */
		public function set giftState(value: int): void{
			this._giftState = value;
		}
		
	}
}