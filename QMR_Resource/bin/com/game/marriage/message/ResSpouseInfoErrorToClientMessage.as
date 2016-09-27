package com.game.marriage.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 配偶信息读取错误
	 */
	public class ResSpouseInfoErrorToClientMessage extends Message {
	
		//错误类型
		private var _type: int;
		
		//错误编码
		private var _error: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//错误类型
			writeInt(_type);
			//错误编码
			writeInt(_error);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//错误类型
			_type = readInt();
			//错误编码
			_error = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163125;
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
		 * get 错误编码
		 * @return 
		 */
		public function get error(): int{
			return _error;
		}
		
		/**
		 * set 错误编码
		 */
		public function set error(value: int): void{
			this._error = value;
		}
		
	}
}