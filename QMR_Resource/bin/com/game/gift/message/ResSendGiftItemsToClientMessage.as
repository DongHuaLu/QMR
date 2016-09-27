package com.game.gift.message{
	import com.game.backpack.bean.ItemInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送礼包物品
	 */
	public class ResSendGiftItemsToClientMessage extends Message {
	
		//礼包物品列表
		private var _items: Vector.<com.game.backpack.bean.ItemInfo> = new Vector.<com.game.backpack.bean.ItemInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//礼包物品列表
			writeShort(_items.length);
			for (i = 0; i < _items.length; i++) {
				writeBean(_items[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//礼包物品列表
			var items_length : int = readShort();
			for (i = 0; i < items_length; i++) {
				_items[i] = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 129101;
		}
		
		/**
		 * get 礼包物品列表
		 * @return 
		 */
		public function get items(): Vector.<com.game.backpack.bean.ItemInfo>{
			return _items;
		}
		
		/**
		 * set 礼包物品列表
		 */
		public function set items(value: Vector.<com.game.backpack.bean.ItemInfo>): void{
			this._items = value;
		}
		
	}
}