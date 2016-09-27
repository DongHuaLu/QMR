package com.game.gift.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求获得普通礼包物品
	 */
	public class ReqGetGiftItemsToServerMessage extends Message {
	
		//要打开的礼包id
		private var _giftid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//要打开的礼包id
			writeLong(_giftid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//要打开的礼包id
			_giftid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 129201;
		}
		
		/**
		 * get 要打开的礼包id
		 * @return 
		 */
		public function get giftid(): long{
			return _giftid;
		}
		
		/**
		 * set 要打开的礼包id
		 */
		public function set giftid(value: long): void{
			this._giftid = value;
		}
		
	}
}