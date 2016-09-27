package com.game.dataserver.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回排位错误
	 */
	public class ResEnterErrorToClientMessage extends Message {
	
		//0个人，1组队
		private var _type: int;
		
		//错误代码
		private var _error: int;
		
		//结果参数
		private var _paras: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//0个人，1组队
			writeInt(_type);
			//错误代码
			writeInt(_error);
			//结果参数
			writeString(_paras);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//0个人，1组队
			_type = readInt();
			//错误代码
			_error = readInt();
			//结果参数
			_paras = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 203109;
		}
		
		/**
		 * get 0个人，1组队
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0个人，1组队
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 错误代码
		 * @return 
		 */
		public function get error(): int{
			return _error;
		}
		
		/**
		 * set 错误代码
		 */
		public function set error(value: int): void{
			this._error = value;
		}
		
		/**
		 * get 结果参数
		 * @return 
		 */
		public function get paras(): String{
			return _paras;
		}
		
		/**
		 * set 结果参数
		 */
		public function set paras(value: String): void{
			this._paras = value;
		}
		
	}
}