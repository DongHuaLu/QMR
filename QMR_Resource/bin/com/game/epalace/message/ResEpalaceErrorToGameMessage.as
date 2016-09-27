package com.game.epalace.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 错误提示消息
	 */
	public class ResEpalaceErrorToGameMessage extends Message {
	
		//错误消息文字提示
		private var _str: String;
		
		//错误消息数字提示
		private var _num: int;
		
		//错误消息类型
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//错误消息文字提示
			writeString(_str);
			//错误消息数字提示
			writeInt(_num);
			//错误消息类型
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//错误消息文字提示
			_str = readString();
			//错误消息数字提示
			_num = readInt();
			//错误消息类型
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 143104;
		}
		
		/**
		 * get 错误消息文字提示
		 * @return 
		 */
		public function get str(): String{
			return _str;
		}
		
		/**
		 * set 错误消息文字提示
		 */
		public function set str(value: String): void{
			this._str = value;
		}
		
		/**
		 * get 错误消息数字提示
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 错误消息数字提示
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
		/**
		 * get 错误消息类型
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 错误消息类型
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}