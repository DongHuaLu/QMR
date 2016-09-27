package com.game.cloak.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 镶嵌信息
	 */
	public class CloakStoneInfo extends Bean {
	
		//格子编号,从1开始
		private var _index: int;
		
		//物品ID
		private var _itemModel: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//格子编号,从1开始
			writeByte(_index);
			//物品ID
			writeInt(_itemModel);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//格子编号,从1开始
			_index = readByte();
			//物品ID
			_itemModel = readInt();
			return true;
		}
		
		/**
		 * get 格子编号,从1开始
		 * @return 
		 */
		public function get index(): int{
			return _index;
		}
		
		/**
		 * set 格子编号,从1开始
		 */
		public function set index(value: int): void{
			this._index = value;
		}
		
		/**
		 * get 物品ID
		 * @return 
		 */
		public function get itemModel(): int{
			return _itemModel;
		}
		
		/**
		 * set 物品ID
		 */
		public function set itemModel(value: int): void{
			this._itemModel = value;
		}
		
	}
}