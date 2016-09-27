package com.game.emperorcity.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 皇城争霸战个人在线奖励信息
	 */
	public class EmperorWarRewardInfo extends Bean {
	
		//累计奖励真气
		private var _zhenqi: int;
		
		//累计奖励经验
		private var _exp: int;
		
		//累计奖励军功
		private var _rank: int;
		
		//停留时间（秒）
		private var _remaintime: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//累计奖励真气
			writeInt(_zhenqi);
			//累计奖励经验
			writeInt(_exp);
			//累计奖励军功
			writeInt(_rank);
			//停留时间（秒）
			writeInt(_remaintime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//累计奖励真气
			_zhenqi = readInt();
			//累计奖励经验
			_exp = readInt();
			//累计奖励军功
			_rank = readInt();
			//停留时间（秒）
			_remaintime = readInt();
			return true;
		}
		
		/**
		 * get 累计奖励真气
		 * @return 
		 */
		public function get zhenqi(): int{
			return _zhenqi;
		}
		
		/**
		 * set 累计奖励真气
		 */
		public function set zhenqi(value: int): void{
			this._zhenqi = value;
		}
		
		/**
		 * get 累计奖励经验
		 * @return 
		 */
		public function get exp(): int{
			return _exp;
		}
		
		/**
		 * set 累计奖励经验
		 */
		public function set exp(value: int): void{
			this._exp = value;
		}
		
		/**
		 * get 累计奖励军功
		 * @return 
		 */
		public function get rank(): int{
			return _rank;
		}
		
		/**
		 * set 累计奖励军功
		 */
		public function set rank(value: int): void{
			this._rank = value;
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