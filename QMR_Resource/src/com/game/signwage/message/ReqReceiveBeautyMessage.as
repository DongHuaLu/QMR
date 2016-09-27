package com.game.signwage.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求领取美人
	 */
	public class ReqReceiveBeautyMessage extends Message {
	
		//领取的美人类型，1到4
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//领取的美人类型，1到4
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//领取的美人类型，1到4
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 152207;
		}
		
		/**
		 * get 领取的美人类型，1到4
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 领取的美人类型，1到4
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}