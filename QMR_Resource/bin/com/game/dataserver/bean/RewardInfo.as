package com.game.dataserver.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 获得奖励信息
	 */
	public class RewardInfo extends Bean {
	
		//奖励id
		private var _rewardId: long;
		
		//荣誉点
		private var _honour: int;
		
		//真气
		private var _zhenqi: int;
		
		//经验
		private var _exp: int;
		
		//获得时间
		private var _time: long;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//奖励id
			writeLong(_rewardId);
			//荣誉点
			writeInt(_honour);
			//真气
			writeInt(_zhenqi);
			//经验
			writeInt(_exp);
			//获得时间
			writeLong(_time);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//奖励id
			_rewardId = readLong();
			//荣誉点
			_honour = readInt();
			//真气
			_zhenqi = readInt();
			//经验
			_exp = readInt();
			//获得时间
			_time = readLong();
			return true;
		}
		
		/**
		 * get 奖励id
		 * @return 
		 */
		public function get rewardId(): long{
			return _rewardId;
		}
		
		/**
		 * set 奖励id
		 */
		public function set rewardId(value: long): void{
			this._rewardId = value;
		}
		
		/**
		 * get 荣誉点
		 * @return 
		 */
		public function get honour(): int{
			return _honour;
		}
		
		/**
		 * set 荣誉点
		 */
		public function set honour(value: int): void{
			this._honour = value;
		}
		
		/**
		 * get 真气
		 * @return 
		 */
		public function get zhenqi(): int{
			return _zhenqi;
		}
		
		/**
		 * set 真气
		 */
		public function set zhenqi(value: int): void{
			this._zhenqi = value;
		}
		
		/**
		 * get 经验
		 * @return 
		 */
		public function get exp(): int{
			return _exp;
		}
		
		/**
		 * set 经验
		 */
		public function set exp(value: int): void{
			this._exp = value;
		}
		
		/**
		 * get 获得时间
		 * @return 
		 */
		public function get time(): long{
			return _time;
		}
		
		/**
		 * set 获得时间
		 */
		public function set time(value: long): void{
			this._time = value;
		}
		
	}
}