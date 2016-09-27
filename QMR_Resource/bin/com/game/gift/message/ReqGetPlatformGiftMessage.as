package com.game.gift.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求领取平台礼包
	 */
	public class ReqGetPlatformGiftMessage extends Message {
	
		//平台名称
		private var _platform: String;
		
		//礼包id
		private var _giftid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//平台名称
			writeString(_platform);
			//礼包id
			writeInt(_giftid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//平台名称
			_platform = readString();
			//礼包id
			_giftid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 129203;
		}
		
		/**
		 * get 平台名称
		 * @return 
		 */
		public function get platform(): String{
			return _platform;
		}
		
		/**
		 * set 平台名称
		 */
		public function set platform(value: String): void{
			this._platform = value;
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
		
	}
}