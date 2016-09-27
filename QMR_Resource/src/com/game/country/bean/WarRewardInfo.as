package com.game.country.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 攻城战个人在线奖励信息
	 */
	public class WarRewardInfo extends Bean {
	
		//奖励真气
		private var _zhenqi: int;
		
		//奖励经验
		private var _exp: int;
		
		//停留时间（秒）
		private var _remaintime: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//奖励真气
			writeInt(_zhenqi);
			//奖励经验
			writeInt(_exp);
			//停留时间（秒）
			writeInt(_remaintime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//奖励真气
			_zhenqi = readInt();
			//奖励经验
			_exp = readInt();
			//停留时间（秒）
			_remaintime = readInt();
			return true;
		}
		
		/**
		 * get 奖励真气
		 * @return 
		 */
		public function get zhenqi(): int{
			return _zhenqi;
		}
		
		/**
		 * set 奖励真气
		 */
		public function set zhenqi(value: int): void{
			this._zhenqi = value;
		}
		
		/**
		 * get 奖励经验
		 * @return 
		 */
		public function get exp(): int{
			return _exp;
		}
		
		/**
		 * set 奖励经验
		 */
		public function set exp(value: int): void{
			this._exp = value;
		}
		
		/**
		 * get 停留时间（秒）
		 * @return 
		 */
		public function get remaintime(): int{
			return _remaintime;
		}
		
		/**
		 * set 停留时间（秒）
		 */
		public function set remaintime(value: int): void{
			this._remaintime = value;
		}
		
	}
}