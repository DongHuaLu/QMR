package com.game.rank.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 获得军功改变
	 */
	public class ResRankExpToClientMessage extends Message {
	
		//当前总军功值
		private var _ranksum: int;
		
		//本次得到的军功值
		private var _rankexp: int;
		
		//今日得到的军功值
		private var _dayrankexp: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前总军功值
			writeInt(_ranksum);
			//本次得到的军功值
			writeInt(_rankexp);
			//今日得到的军功值
			writeInt(_dayrankexp);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前总军功值
			_ranksum = readInt();
			//本次得到的军功值
			_rankexp = readInt();
			//今日得到的军功值
			_dayrankexp = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 117102;
		}
		
		/**
		 * get 当前总军功值
		 * @return 
		 */
		public function get ranksum(): int{
			return _ranksum;
		}
		
		/**
		 * set 当前总军功值
		 */
		public function set ranksum(value: int): void{
			this._ranksum = value;
		}
		
		/**
		 * get 本次得到的军功值
		 * @return 
		 */
		public function get rankexp(): int{
			return _rankexp;
		}
		
		/**
		 * set 本次得到的军功值
		 */
		public function set rankexp(value: int): void{
			this._rankexp = value;
		}
		
		/**
		 * get 今日得到的军功值
		 * @return 
		 */
		public function get dayrankexp(): int{
			return _dayrankexp;
		}
		
		/**
		 * set 今日得到的军功值
		 */
		public function set dayrankexp(value: int): void{
			this._dayrankexp = value;
		}
		
	}
}