package com.game.marriage.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 离婚成功后弹出面板
	 */
	public class ResForceDivorceToClientMessage extends Message {
	
		//1协议离婚，2强行离婚
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//1协议离婚，2强行离婚
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//1协议离婚，2强行离婚
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163106;
		}
		
		/**
		 * get 1协议离婚，2强行离婚
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 1协议离婚，2强行离婚
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}