package com.game.gift.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 开始翻牌抽奖
	 */
	public class ReqStartShuffleRaffleMessage extends Message {
	
		//道具唯一ID
		private var _itemid: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//道具唯一ID
			writeString(_itemid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//道具唯一ID
			_itemid = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 129205;
		}
		
		/**
		 * get 道具唯一ID
		 * @return 
		 */
		public function get itemid(): String{
			return _itemid;
		}
		
		/**
		 * set 道具唯一ID
		 */
		public function set itemid(value: String): void{
			this._itemid = value;
		}
		
	}
}