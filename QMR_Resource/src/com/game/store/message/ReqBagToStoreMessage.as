package com.game.store.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 物品存入仓库
	 */
	public class ReqBagToStoreMessage extends Message {
	
		//包裹格子号
		private var _bagCellId: int;
		
		//关联NPC
		private var _npcid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//包裹格子号
			writeInt(_bagCellId);
			//关联NPC
			writeInt(_npcid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//包裹格子号
			_bagCellId = readInt();
			//关联NPC
			_npcid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 112206;
		}
		
		/**
		 * get 包裹格子号
		 * @return 
		 */
		public function get bagCellId(): int{
			return _bagCellId;
		}
		
		/**
		 * set 包裹格子号
		 */
		public function set bagCellId(value: int): void{
			this._bagCellId = value;
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