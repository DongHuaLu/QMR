package com.game.marriage.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 申请婚宴
	 */
	public class ReqApplyWeddingToGameMessage extends Message {
	
		//婚宴类型，1普通，2大型，3豪华
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//婚宴类型，1普通，2大型，3豪华
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//婚宴类型，1普通，2大型，3豪华
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163207;
		}
		
		/**
		 * get 婚宴类型，1普通，2大型，3豪华
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 婚宴类型，1普通，2大型，3豪华
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}