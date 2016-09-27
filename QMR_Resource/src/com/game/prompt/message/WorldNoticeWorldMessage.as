package com.game.prompt.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 世界公告
	 */
	public class WorldNoticeWorldMessage extends Message {
	
		//信息内容
		private var _content: String;
		
		//数值
		private var _values: Vector.<String> = new Vector.<String>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//信息内容
			writeString(_content);
			//数值
			writeShort(_values.length);
			for (i = 0; i < _values.length; i++) {
				writeString(_values[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//信息内容
			_content = readString();
			//数值
			var values_length : int = readShort();
			for (i = 0; i < values_length; i++) {
				_values[i] = readString();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 109108;
		}
		
		/**
		 * get 信息内容
		 * @return 
		 */
		public function get content(): String{
			return _content;
		}
		
		/**
		 * set 信息内容
		 */
		public function set content(value: String): void{
			this._content = value;
		}
		
		/**
		 * get 数值
		 * @return 
		 */
		public function get values(): Vector.<String>{
			return _values;
		}
		
		/**
		 * set 数值
		 */
		public function set values(value: Vector.<String>): void{
			this._values = value;
		}
		
	}
}