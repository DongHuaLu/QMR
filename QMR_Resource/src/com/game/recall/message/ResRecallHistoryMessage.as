package com.game.recall.message{
	import com.game.recall.bean.History;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 召回日志
	 */
	public class ResRecallHistoryMessage extends Message {
	
		//日志
		private var _history: Vector.<History> = new Vector.<History>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//日志
			writeShort(_history.length);
			for (i = 0; i < _history.length; i++) {
				writeBean(_history[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//日志
			var history_length : int = readShort();
			for (i = 0; i < history_length; i++) {
				_history[i] = readBean(History) as History;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 168103;
		}
		
		/**
		 * get 日志
		 * @return 
		 */
		public function get history(): Vector.<History>{
			return _history;
		}
		
		/**
		 * set 日志
		 */
		public function set history(value: Vector.<History>): void{
			this._history = value;
		}
		
	}
}