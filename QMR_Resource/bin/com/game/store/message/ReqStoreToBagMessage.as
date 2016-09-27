package com.game.store.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 物品从仓库取出
	 */
	public class ReqStoreToBagMessage extends Message {
	
		//包裹格子号
		private var _storeCellId: int;
		
		//关联NPC
		private var _npcid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//包裹格子号
			writeInt(_storeCellId);
			//关联NPC
			writeInt(_npcid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//包裹格子号
			_storeCellId = readInt();
			//关联NPC
			_npcid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 112207;
		}
		
		/**
		 * get 包裹格子号
		 * @return 
		 */
		public function get storeCellId(): int{
			return _storeCellId;
		}
		
		/**
		 * set 包裹格子号
		 */
		public function set storeCellId(value: int): void{
			this._storeCellId = value;
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