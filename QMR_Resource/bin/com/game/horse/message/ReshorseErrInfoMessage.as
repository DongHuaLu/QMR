package com.game.horse.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 错误信息
	 */
	public class ReshorseErrInfoMessage extends Message {
	
		//错误类型
		private var _type: int;
		
		//错误int内容
		private var _errint: int;
		
		//错误字符内容
		private var _errstr: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//错误类型
			writeByte(_type);
			//错误int内容
			writeInt(_errint);
			//错误字符内容
			writeString(_errstr);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//错误类型
			_type = readByte();
			//错误int内容
			_errint = readInt();
			//错误字符内容
			_errstr = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 126110;
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
		 * get 错误int内容
		 * @return 
		 */
		public function get errint(): int{
			return _errint;
		}
		
		/**
		 * set 错误int内容
		 */
		public function set errint(value: int): void{
			this._errint = value;
		}
		
		/**
		 * get 错误字符内容
		 * @return 
		 */
		public function get errstr(): String{
			return _errstr;
		}
		
		/**
		 * set 错误字符内容
		 */
		public function set errstr(value: String): void{
			this._errstr = value;
		}
		
	}
}