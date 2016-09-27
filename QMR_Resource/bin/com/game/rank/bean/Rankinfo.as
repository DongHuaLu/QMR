package com.game.rank.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 军衔保存信息
	 */
	public class Rankinfo extends Bean {
	
		//军衔等级
		private var _ranklevel: int;
		
		//军功数量
		private var _rankexp: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//军衔等级
			writeInt(_ranklevel);
			//军功数量
			writeInt(_rankexp);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//军衔等级
			_ranklevel = readInt();
			//军功数量
			_rankexp = readInt();
			return true;
		}
		
		/**
		 * get 军衔等级
		 * @return 
		 */
		public function get ranklevel(): int{
			return _ranklevel;
		}
		
		/**
		 * set 军衔等级
		 */
		public function set ranklevel(value: int): void{
			this._ranklevel = value;
		}
		
		/**
		 * get 军功数量
		 * @return 
		 */
		public function get rankexp(): int{
			return _rankexp;
		}
		
		/**
		 * set 军功数量
		 */
		public function set rankexp(value: int): void{
			this._rankexp = value;
		}
		
	}
}