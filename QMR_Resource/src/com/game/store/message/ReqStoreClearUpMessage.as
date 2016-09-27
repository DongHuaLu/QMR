package com.game.store.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 整理物品
	 */
	public class ReqStoreClearUpMessage extends Message {
	
		//关联NPC
		private var _npcid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//关联NPC
			writeInt(_npcid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//关联NPC
			_npcid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 112202;
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