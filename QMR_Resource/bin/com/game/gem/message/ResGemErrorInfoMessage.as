package com.game.gem.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 错误消息
	 */
	public class ResGemErrorInfoMessage extends Message {
	
		//错误类型
		private var _type: int;
		
		//数值
		private var _intnum: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//错误类型
			writeByte(_type);
			//数值
			writeInt(_intnum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//错误类型
			_type = readByte();
			//数值
			_intnum = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 132105;
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
		
		/**
		 * get 数值
		 * @return 
		 */
		public function get intnum(): int{
			return _intnum;
		}
		
		/**
		 * set 数值
		 */
		public function set intnum(value: int): void{
			this._intnum = value;
		}
		
	}
}