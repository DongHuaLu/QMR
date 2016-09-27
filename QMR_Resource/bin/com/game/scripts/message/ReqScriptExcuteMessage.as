package com.game.scripts.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求脚本函数
	 */
	public class ReqScriptExcuteMessage extends Message {
	
		//脚本Id
		private var _scriptId: int;
		
		//脚本函数
		private var _method: String;
		
		//脚本参数
		private var _paras: Vector.<String> = new Vector.<String>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//脚本Id
			writeInt(_scriptId);
			//脚本函数
			writeString(_method);
			//脚本参数
			writeShort(_paras.length);
			for (i = 0; i < _paras.length; i++) {
				writeString(_paras[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//脚本Id
			_scriptId = readInt();
			//脚本函数
			_method = readString();
			//脚本参数
			var paras_length : int = readShort();
			for (i = 0; i < paras_length; i++) {
				_paras[i] = readString();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 148201;
		}
		
		/**
		 * get 脚本Id
		 * @return 
		 */
		public function get scriptId(): int{
			return _scriptId;
		}
		
		/**
		 * set 脚本Id
		 */
		public function set scriptId(value: int): void{
			this._scriptId = value;
		}
		
		/**
		 * get 脚本函数
		 * @return 
		 */
		public function get method(): String{
			return _method;
		}
		
		/**
		 * set 脚本函数
		 */
		public function set method(value: String): void{
			this._method = value;
		}
		
		/**
		 * get 脚本参数
		 * @return 
		 */
		public function get paras(): Vector.<String>{
			return _paras;
		}
		
		/**
		 * set 脚本参数
		 */
		public function set paras(value: Vector.<String>): void{
			this._paras = value;
		}
		
	}
}