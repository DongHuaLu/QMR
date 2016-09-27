package com.game.emperorcity.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 大型群体活动时间记录
	 */
	public class ResGroupActivityinfoToGameClientMessage extends Message {
	
		//活动时间内容列表
		private var _strjson: Vector.<String> = new Vector.<String>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//活动时间内容列表
			writeShort(_strjson.length);
			for (i = 0; i < _strjson.length; i++) {
				writeString(_strjson[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//活动时间内容列表
			var strjson_length : int = readShort();
			for (i = 0; i < strjson_length; i++) {
				_strjson[i] = readString();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 169119;
		}
		
		/**
		 * get 活动时间内容列表
		 * @return 
		 */
		public function get strjson(): Vector.<String>{
			return _strjson;
		}
		
		/**
		 * set 活动时间内容列表
		 */
		public function set strjson(value: Vector.<String>): void{
			this._strjson = value;
		}
		
	}
}