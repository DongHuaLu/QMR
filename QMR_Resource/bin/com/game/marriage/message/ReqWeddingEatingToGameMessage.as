package com.game.marriage.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 开始吃菜
	 */
	public class ReqWeddingEatingToGameMessage extends Message {
	
		//结婚id
		private var _marriageid: long;
		
		//NPC唯一id
		private var _npcid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//结婚id
			writeLong(_marriageid);
			//NPC唯一id
			writeLong(_npcid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//结婚id
			_marriageid = readLong();
			//NPC唯一id
			_npcid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163213;
		}
		
		/**
		 * get 结婚id
		 * @return 
		 */
		public function get marriageid(): long{
			return _marriageid;
		}
		
		/**
		 * set 结婚id
		 */
		public function set marriageid(value: long): void{
			this._marriageid = value;
		}
		
		/**
		 * get NPC唯一id
		 * @return 
		 */
		public function get npcid(): long{
			return _npcid;
		}
		
		/**
		 * set NPC唯一id
		 */
		public function set npcid(value: long): void{
			this._npcid = value;
		}
		
	}
}