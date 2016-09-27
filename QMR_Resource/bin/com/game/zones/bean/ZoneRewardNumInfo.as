package com.game.zones.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 副本奖励信息
	 */
	public class ZoneRewardNumInfo extends Bean {
	
		//副本id
		private var _zoneid: int;
		
		//奖励可领数量
		private var _num: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//副本id
			writeInt(_zoneid);
			//奖励可领数量
			writeInt(_num);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//副本id
			_zoneid = readInt();
			//奖励可领数量
			_num = readInt();
			return true;
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
		 * get 奖励可领数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 奖励可领数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
	}
}