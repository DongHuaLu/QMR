package com.game.arrow.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回战魂搜索次数
	 */
	public class ResFightSpiritGetNumMessage extends Message {
	
		//已经搜索次数
		private var _num: int;
		
		//副本id
		private var _zoneid: int;
		
		//是否第一次搜索
		private var _first: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//已经搜索次数
			writeInt(_num);
			//副本id
			writeInt(_zoneid);
			//是否第一次搜索
			writeInt(_first);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//已经搜索次数
			_num = readInt();
			//副本id
			_zoneid = readInt();
			//是否第一次搜索
			_first = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 151105;
		}
		
		/**
		 * get 已经搜索次数
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 已经搜索次数
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
		/**
		 * get 副本id
		 * @return 
		 */
		public function get zoneid(): int{
			return _zoneid;
		}
		
		/**
		 * set 副本id
		 */
		public function set zoneid(value: int): void{
			this._zoneid = value;
		}
		
		/**
		 * get 是否第一次搜索
		 * @return 
		 */
		public function get first(): int{
			return _first;
		}
		
		/**
		 * set 是否第一次搜索
		 */
		public function set first(value: int): void{
			this._first = value;
		}
		
	}
}