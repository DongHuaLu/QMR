package com.game.equip.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 卸下装备信息
	 */
	public class UnwearEquipItemMessage extends Message {
	
		//物品Id
		private var _itemId: long;
		
		//格子Id
		private var _gridId: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//物品Id
			writeLong(_itemId);
			//格子Id
			writeInt(_gridId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//物品Id
			_itemId = readLong();
			//格子Id
			_gridId = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 106102;
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
		
		/**
		 * get 格子Id
		 * @return 
		 */
		public function get gridId(): int{
			return _gridId;
		}
		
		/**
		 * set 格子Id
		 */
		public function set gridId(value: int): void{
			this._gridId = value;
		}
		
	}
}