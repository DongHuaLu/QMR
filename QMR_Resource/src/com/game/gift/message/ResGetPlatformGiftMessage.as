package com.game.gift.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送领取结果
	 */
	public class ResGetPlatformGiftMessage extends Message {
	
		//礼包id
		private var _giftid: int;
		
		//礼包领取结果 0-成功  1-失败
		private var _result: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//礼包id
			writeInt(_giftid);
			//礼包领取结果 0-成功  1-失败
			writeInt(_result);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//礼包id
			_giftid = readInt();
			//礼包领取结果 0-成功  1-失败
			_result = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 129104;
		}
		
		/**
		 * get 礼包id
		 * @return 
		 */
		public function get giftid(): int{
			return _giftid;
		}
		
		/**
		 * set 礼包id
		 */
		public function set giftid(value: int): void{
			this._giftid = value;
		}
		
		/**
		 * get 礼包领取结果 0-成功  1-失败
		 * @return 
		 */
		public function get result(): int{
			return _result;
		}
		
		/**
		 * set 礼包领取结果 0-成功  1-失败
		 */
		public function set result(value: int): void{
			this._result = value;
		}
		
	}
}