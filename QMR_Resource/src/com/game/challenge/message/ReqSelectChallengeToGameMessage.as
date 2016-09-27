package com.game.challenge.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 选择挑战面板
	 */
	public class ReqSelectChallengeToGameMessage extends Message {
	
		//面板类型，1-9
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//面板类型，1-9
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//面板类型，1-9
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 144202;
		}
		
		/**
		 * get 面板类型，1-9
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 面板类型，1-9
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}