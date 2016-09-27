package com.game.vip.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玩家VIP信息
	 */
	public class VipInfo extends Bean {
	
		//VIPid 0表示不是VIP
		private var _vipId: int;
		
		//VIP剩余持续时间 单位:秒 
		private var _remain: int;
		
		//是否可领取，0-不可领取， 1-可领取
		private var _status: int;
		
		//是否展示 至尊VIP的广告 0-不展示 1-展示
		private var _showad: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//VIPid 0表示不是VIP
			writeInt(_vipId);
			//VIP剩余持续时间 单位:秒 
			writeInt(_remain);
			//是否可领取，0-不可领取， 1-可领取
			writeInt(_status);
			//是否展示 至尊VIP的广告 0-不展示 1-展示
			writeByte(_showad);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//VIPid 0表示不是VIP
			_vipId = readInt();
			//VIP剩余持续时间 单位:秒 
			_remain = readInt();
			//是否可领取，0-不可领取， 1-可领取
			_status = readInt();
			//是否展示 至尊VIP的广告 0-不展示 1-展示
			_showad = readByte();
			return true;
		}
		
		/**
		 * get VIPid 0表示不是VIP
		 * @return 
		 */
		public function get vipId(): int{
			return _vipId;
		}
		
		/**
		 * set VIPid 0表示不是VIP
		 */
		public function set vipId(value: int): void{
			this._vipId = value;
		}
		
		/**
		 * get VIP剩余持续时间 单位:秒 
		 * @return 
		 */
		public function get remain(): int{
			return _remain;
		}
		
		/**
		 * set VIP剩余持续时间 单位:秒 
		 */
		public function set remain(value: int): void{
			this._remain = value;
		}
		
		/**
		 * get 是否可领取，0-不可领取， 1-可领取
		 * @return 
		 */
		public function get status(): int{
			return _status;
		}
		
		/**
		 * set 是否可领取，0-不可领取， 1-可领取
		 */
		public function set status(value: int): void{
			this._status = value;
		}
		
		/**
		 * get 是否展示 至尊VIP的广告 0-不展示 1-展示
		 * @return 
		 */
		public function get showad(): int{
			return _showad;
		}
		
		/**
		 * set 是否展示 至尊VIP的广告 0-不展示 1-展示
		 */
		public function set showad(value: int): void{
			this._showad = value;
		}
		
	}
}