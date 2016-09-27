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
	 * 侍宠物说话
	 */
	public class ResPetChatMessage extends Message {
	
		//侍宠id
		private var _petId: long;
		
		//说话内容
		private var _saycontent: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//侍宠id
			writeLong(_petId);
			//说话内容
			writeString(_saycontent);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//侍宠id
			_petId = readLong();
			//说话内容
			_saycontent = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 110113;
		}
		
		/**
		 * get 侍宠id
		 * @return 
		 */
		public function get petId(): long{
			return _petId;
		}
		
		/**
		 * set 侍宠id
		 */
		public function set petId(value: long): void{
			this._petId = value;
		}
		
		/**
		 * get 说话内容
		 * @return 
		 */
		public function get saycontent(): String{
			return _saycontent;
		}
		
		/**
		 * set 说话内容
		 */
		public function set saycontent(value: String): void{
			this._saycontent = value;
		}
		
	}
}