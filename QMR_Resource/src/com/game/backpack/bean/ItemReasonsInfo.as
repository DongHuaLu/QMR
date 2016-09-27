package com.game.backpack.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 获得物品原因信息
	 */
	public class ItemReasonsInfo extends Bean {
	
		//物品唯一id
		private var _itemId: long;
		
		//物品模板Id
		private var _itemModelId: int;
		
		//物品数量
		private var _itemNum: int;
		
		//获得物品原因
		private var _itemReasons: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//物品唯一id
			writeLong(_itemId);
			//物品模板Id
			writeInt(_itemModelId);
			//物品数量
			writeInt(_itemNum);
			//获得物品原因
			writeInt(_itemReasons);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//物品唯一id
			_itemId = readLong();
			//物品模板Id
			_itemModelId = readInt();
			//物品数量
			_itemNum = readInt();
			//获得物品原因
			_itemReasons = readInt();
			return true;
		}
		
		/**
		 * get 物品唯一id
		 * @return 
		 */
		public function get itemId(): long{
			return _itemId;
		}
		
		/**
		 * set 物品唯一id
		 */
		public function set itemId(value: long): void{
			this._itemId = value;
		}
		
		/**
		 * get 物品模板Id
		 * @return 
		 */
		public function get itemModelId(): int{
			return _itemModelId;
		}
		
		/**
		 * set 物品模板Id
		 */
		public function set itemModelId(value: int): void{
			this._itemModelId = value;
		}
		
		/**
		 * get 物品数量
		 * @return 
		 */
		public function get itemNum(): int{
			return _itemNum;
		}
		
		/**
		 * set 物品数量
		 */
		public function set itemNum(value: int): void{
			this._itemNum = value;
		}
		
		/**
		 * get 获得物品原因
		 * @return 
		 */
		public function get itemReasons(): int{
			return _itemReasons;
		}
		
		/**
		 * set 获得物品原因
		 */
		public function set itemReasons(value: int): void{
			this._itemReasons = value;
		}
		
	}
}