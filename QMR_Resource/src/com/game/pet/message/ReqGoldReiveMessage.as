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
	 * 复活宠物
	 */
	public class ReqGoldReiveMessage extends Message {
	
		//宠物ID
		private var _petId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//宠物ID
			writeLong(_petId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//宠物ID
			_petId = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 110206;
		}
		
		/**
		 * get 宠物ID
		 * @return 
		 */
		public function get petId(): long{
			return _petId;
		}
		
		/**
		 * set 宠物ID
		 */
		public function set petId(value: long): void{
			this._petId = value;
		}
		
	}
}