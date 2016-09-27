package com.game.spirittree.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 灵树错误信息
	 */
	public class ResFruitErrorToClientMessage extends Message {
	
		//错误类型
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//错误类型
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//错误类型
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 133108;
		}
		
		/**
		 * get 错误类型
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 错误类型
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}