package com.game.bag.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 跨服包裹物品删除
	 */
	public class ResBagItemRemoveMessage extends Message {
	
		//物品Id
		private var _itemId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//物品Id
			writeLong(_itemId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//物品Id
			_itemId = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 160103;
		}
		
		/**
		 * get 物品Id
		 * @return 
		 */
		public function get itemId(): long{
			return _itemId;
		}
		
		/**
		 * set 物品Id
		 */
		public function set itemId(value: long): void{
			this._itemId = value;
		}
		
	}
}