package com.game.recall.message{
	import com.game.recall.bean.SearchResult;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回服查询玩家名称列表
	 */
	public class ResRecallSearchNameMessage extends Message {
	
		//玩家名称列表
		private var _names: Vector.<SearchResult> = new Vector.<SearchResult>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//玩家名称列表
			writeShort(_names.length);
			for (i = 0; i < _names.length; i++) {
				writeBean(_names[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//玩家名称列表
			var names_length : int = readShort();
			for (i = 0; i < names_length; i++) {
				_names[i] = readBean(SearchResult) as SearchResult;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 168101;
		}
		
		/**
		 * get 玩家名称列表
		 * @return 
		 */
		public function get names(): Vector.<SearchResult>{
			return _names;
		}
		
		/**
		 * set 玩家名称列表
		 */
		public function set names(value: Vector.<SearchResult>): void{
			this._names = value;
		}
		
	}
}