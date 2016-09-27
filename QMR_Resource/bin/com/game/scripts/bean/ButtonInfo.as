package com.game.scripts.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 面板按钮信息
	 */
	public class ButtonInfo extends Bean {
	
		//按钮控件ID名称
		private var _name: String;
		
		//脚本Id
		private var _scriptId: int;
		
		//调用方法名
		private var _method: String;
		
		//0不关闭，1点击后关闭
		private var _isclose: int;
		
		//脚本参数
		private var _paras: Vector.<String> = new Vector.<String>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//按钮控件ID名称
			writeString(_name);
			//脚本Id
			writeInt(_scriptId);
			//调用方法名
			writeString(_method);
			//0不关闭，1点击后关闭
			writeByte(_isclose);
			//脚本参数
			writeShort(_paras.length);
			for (var i: int = 0; i < _paras.length; i++) {
				writeString(_paras[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//按钮控件ID名称
			_name = readString();
			//脚本Id
			_scriptId = readInt();
			//调用方法名
			_method = readString();
			//0不关闭，1点击后关闭
			_isclose = readByte();
			//脚本参数
			var paras_length : int = readShort();
			for (var i: int = 0; i < paras_length; i++) {
				_paras[i] = readString();
			}
			return true;
		}
		
		/**
		 * get 按钮控件ID名称
		 * @return 
		 */
		public function get name(): String{
			return _name;
		}
		
		/**
		 * set 按钮控件ID名称
		 */
		public function set name(value: String): void{
			this._name = value;
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
		 * get 调用方法名
		 * @return 
		 */
		public function get method(): String{
			return _method;
		}
		
		/**
		 * set 调用方法名
		 */
		public function set method(value: String): void{
			this._method = value;
		}
		
		/**
		 * get 0不关闭，1点击后关闭
		 * @return 
		 */
		public function get isclose(): int{
			return _isclose;
		}
		
		/**
		 * set 0不关闭，1点击后关闭
		 */
		public function set isclose(value: int): void{
			this._isclose = value;
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