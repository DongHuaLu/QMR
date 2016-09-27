package com.game.backpack.message{
	import com.game.backpack.bean.ItemReasonsInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 获得物品原因
	 */
	public class ResGetItemReasonsMessage extends Message {
	
		//获得物品原因
		private var _itemReasons: int;
		
		//物品列表
		private var _itemReasonsInfoList: Vector.<ItemReasonsInfo> = new Vector.<ItemReasonsInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//获得物品原因
			writeInt(_itemReasons);
			//物品列表
			writeShort(_itemReasonsInfoList.length);
			for (i = 0; i < _itemReasonsInfoList.length; i++) {
				writeBean(_itemReasonsInfoList[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//获得物品原因
			_itemReasons = readInt();
			//物品列表
			var itemReasonsInfoList_length : int = readShort();
			for (i = 0; i < itemReasonsInfoList_length; i++) {
				_itemReasonsInfoList[i] = readBean(ItemReasonsInfo) as ItemReasonsInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 104214;
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
		
		/**
		 * get 物品列表
		 * @return 
		 */
		public function get itemReasonsInfoList(): Vector.<ItemReasonsInfo>{
			return _itemReasonsInfoList;
		}
		
		/**
		 * set 物品列表
		 */
		public function set itemReasonsInfoList(value: Vector.<ItemReasonsInfo>): void{
			this._itemReasonsInfoList = value;
		}
		
	}
}