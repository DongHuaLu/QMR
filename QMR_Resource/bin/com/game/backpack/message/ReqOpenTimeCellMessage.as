package com.game.backpack.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 时间到打开格子申请
	 */
	public class ReqOpenTimeCellMessage extends Message {
	
		//0 包裹  2仓库
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//0 包裹  2仓库
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//0 包裹  2仓库
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 104213;
		}
		
		/**
		 * get 0 包裹  2仓库
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0 包裹  2仓库
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}