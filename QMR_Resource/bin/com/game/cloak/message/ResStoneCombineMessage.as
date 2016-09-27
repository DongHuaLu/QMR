package com.game.cloak.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 晶石合成
	 */
	public class ResStoneCombineMessage extends Message {
	
		//0:成功 1:失败
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//0:成功 1:失败
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//0:成功 1:失败
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 170105;
		}
		
		/**
		 * get 0:成功 1:失败
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0:成功 1:失败
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}