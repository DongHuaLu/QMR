package com.game.scripts.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 面板文字信息
	 */
	public class PanelTxtInfo extends Bean {
	
		//控件ID名称
		private var _name: String;
		
		//0整段文字替换，1只替换对应变量
		private var _type: int;
		
		//文字内容替换
		private var _content: String;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//控件ID名称
			writeString(_name);
			//0整段文字替换，1只替换对应变量
			writeByte(_type);
			//文字内容替换
			writeString(_content);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//控件ID名称
			_name = readString();
			//0整段文字替换，1只替换对应变量
			_type = readByte();
			//文字内容替换
			_content = readString();
			return true;
		}
		
		/**
		 * get 控件ID名称
		 * @return 
		 */
		public function get name(): String{
			return _name;
		}
		
		/**
		 * set 控件ID名称
		 */
		public function set name(value: String): void{
			this._name = value;
		}
		
		/**
		 * get 0整段文字替换，1只替换对应变量
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0整段文字替换，1只替换对应变量
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 文字内容替换
		 * @return 
		 */
		public function get content(): String{
			return _content;
		}
		
		/**
		 * set 文字内容替换
		 */
		public function set content(value: String): void{
			this._content = value;
		}
		
	}
}