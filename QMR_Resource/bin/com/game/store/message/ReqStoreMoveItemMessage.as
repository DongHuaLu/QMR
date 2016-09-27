package com.game.store.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 移动物品
	 */
	public class ReqStoreMoveItemMessage extends Message {
	
		//物品Id
		private var _itemId: long;
		
		//移动到格子Id
		private var _toGridId: int;
		
		//移动数量
		private var _num: int;
		
		//关联NPC
		private var _npcid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//物品Id
			writeLong(_itemId);
			//移动到格子Id
			writeInt(_toGridId);
			//移动数量
			writeInt(_num);
			//关联NPC
			writeInt(_npcid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//物品Id
			_itemId = readLong();
			//移动到格子Id
			_toGridId = readInt();
			//移动数量
			_num = readInt();
			//关联NPC
			_npcid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 112201;
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
		 * get 移动到格子Id
		 * @return 
		 */
		public function get toGridId(): int{
			return _toGridId;
		}
		
		/**
		 * set 移动到格子Id
		 */
		public function set toGridId(value: int): void{
			this._toGridId = value;
		}
		
		/**
		 * get 移动数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 移动数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
		/**
		 * get 关联NPC
		 * @return 
		 */
		public function get npcid(): int{
			return _npcid;
		}
		
		/**
		 * set 关联NPC
		 */
		public function set npcid(value: int): void{
			this._npcid = value;
		}
		
	}
}