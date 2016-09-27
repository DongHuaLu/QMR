package com.game.backpack.message{
	import com.game.backpack.bean.ItemInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 物品信息列表
	 */
	public class ResItemInfosMessage extends Message {
	
		//已开格子数量
		private var _cellnum: int;
		
		//物品信息列表
		private var _items: Vector.<ItemInfo> = new Vector.<ItemInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//已开格子数量
			writeInt(_cellnum);
			//物品信息列表
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
			//已开格子数量
			_cellnum = readInt();
			//物品信息列表
			var items_length : int = readShort();
			for (i = 0; i < items_length; i++) {
				_items[i] = readBean(ItemInfo) as ItemInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 104101;
		}
		
		/**
		 * get 已开格子数量
		 * @return 
		 */
		public function get cellnum(): int{
			return _cellnum;
		}
		
		/**
		 * set 已开格子数量
		 */
		public function set cellnum(value: int): void{
			this._cellnum = value;
		}
		
		/**
		 * get 物品信息列表
		 * @return 
		 */
		public function get items(): Vector.<ItemInfo>{
			return _items;
		}
		
		/**
		 * set 物品信息列表
		 */
		public function set items(value: Vector.<ItemInfo>): void{
			this._items = value;
		}
		
	}
}