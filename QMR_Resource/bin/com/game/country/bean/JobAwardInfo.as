package com.game.country.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 职位领奖信息
	 */
	public class JobAwardInfo extends Bean {
	
		//职位
		private var _level: int;
		
		//是否可领奖，1可领，0不可
		private var _status: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//职位
			writeInt(_level);
			//是否可领奖，1可领，0不可
			writeInt(_status);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//职位
			_level = readInt();
			//是否可领奖，1可领，0不可
			_status = readInt();
			return true;
		}
		
		/**
		 * get 职位
		 * @return 
		 */
		public function get level(): int{
			return _level;
		}
		
		/**
		 * set 职位
		 */
		public function set level(value: int): void{
			this._level = value;
		}
		
		/**
		 * get 是否可领奖，1可领，0不可
		 * @return 
		 */
		public function get status(): int{
			return _status;
		}
		
		/**
		 * set 是否可领奖，1可领，0不可
		 */
		public function set status(value: int): void{
			this._status = value;
		}
		
	}
}