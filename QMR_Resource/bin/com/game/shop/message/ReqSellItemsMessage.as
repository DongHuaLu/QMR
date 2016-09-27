package com.game.shop.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 出售物品列表
	 */
	public class ReqSellItemsMessage extends Message {
	
		//物品Id
		private var _itemId: Vector.<long> = new Vector.<long>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//物品Id
			writeShort(_itemId.length);
			for (i = 0; i < _itemId.length; i++) {
				writeLong(_itemId[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//物品Id
			var itemId_length : int = readShort();
			for (i = 0; i < itemId_length; i++) {
				_itemId[i] = readLong();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 105207;
		}
		
		/**
		 * get 物品Id
		 * @return 
		 */
		public function get itemId(): Vector.<long>{
			return _itemId;
		}
		
		/**
		 * set 物品Id
		 */
		public function set itemId(value: Vector.<long>): void{
			this._itemId = value;
		}
		
	}
}