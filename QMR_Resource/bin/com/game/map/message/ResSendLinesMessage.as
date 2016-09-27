package com.game.map.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送当前线数量
	 */
	public class ResSendLinesMessage extends Message {
	
		//线列表
		private var _lines: Vector.<int> = new Vector.<int>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//线列表
			writeShort(_lines.length);
			for (i = 0; i < _lines.length; i++) {
				writeInt(_lines[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//线列表
			var lines_length : int = readShort();
			for (i = 0; i < lines_length; i++) {
				_lines[i] = readInt();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101124;
		}
		
		/**
		 * get 线列表
		 * @return 
		 */
		public function get lines(): Vector.<int>{
			return _lines;
		}
		
		/**
		 * set 线列表
		 */
		public function set lines(value: Vector.<int>): void{
			this._lines = value;
		}
		
	}
}