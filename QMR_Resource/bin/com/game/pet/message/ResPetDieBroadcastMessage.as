package com.game.pet.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 宠物死亡广播
	 */
	public class ResPetDieBroadcastMessage extends Message {
	
		//死亡宠物ID
		private var _petId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//死亡宠物ID
			writeLong(_petId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//死亡宠物ID
			_petId = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 110111;
		}
		
		/**
		 * get 死亡宠物ID
		 * @return 
		 */
		public function get petId(): long{
			return _petId;
		}
		
		/**
		 * set 死亡宠物ID
		 */
		public function set petId(value: long): void{
			this._petId = value;
		}
		
	}
}