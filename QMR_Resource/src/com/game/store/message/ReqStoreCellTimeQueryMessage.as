package com.game.store.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 格子开启剩余时间查询
	 */
	public class ReqStoreCellTimeQueryMessage extends Message {
	
		//格子编号
		private var _cellId: int;
		
		//关联NPC
		private var _npcid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//格子编号
			writeInt(_cellId);
			//关联NPC
			writeInt(_npcid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//格子编号
			_cellId = readInt();
			//关联NPC
			_npcid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 112204;
		}
		
		/**
		 * get 格子编号
		 * @return 
		 */
		public function get cellId(): int{
			return _cellId;
		}
		
		/**
		 * set 格子编号
		 */
		public function set cellId(value: int): void{
			this._cellId = value;
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