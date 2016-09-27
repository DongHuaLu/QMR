package com.game.wine.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 变动信息
	 */
	public class WineUpdate extends Bean {
	
		//掷骰子次数
		private var _times: int;
		
		//铜币转运次数
		private var _moneyTimes: int;
		
		//本次投掷的点数
		private var _nowWine: int;
		
		//投掷点数合计
		private var _wine: int;
		
		//本次投掷的结果(111111:表示投掷的6个都是酒)
		private var _detail: int;
		
		//当前可获得奖励真气
		private var _zhenqi: int;
		
		//当前可获得奖励经验
		private var _exp: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//掷骰子次数
			writeByte(_times);
			//铜币转运次数
			writeByte(_moneyTimes);
			//本次投掷的点数
			writeByte(_nowWine);
			//投掷点数合计
			writeByte(_wine);
			//本次投掷的结果(111111:表示投掷的6个都是酒)
			writeInt(_detail);
			//当前可获得奖励真气
			writeInt(_zhenqi);
			//当前可获得奖励经验
			writeInt(_exp);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//掷骰子次数
			_times = readByte();
			//铜币转运次数
			_moneyTimes = readByte();
			//本次投掷的点数
			_nowWine = readByte();
			//投掷点数合计
			_wine = readByte();
			//本次投掷的结果(111111:表示投掷的6个都是酒)
			_detail = readInt();
			//当前可获得奖励真气
			_zhenqi = readInt();
			//当前可获得奖励经验
			_exp = readInt();
			return true;
		}
		
		/**
		 * get 掷骰子次数
		 * @return 
		 */
		public function get times(): int{
			return _times;
		}
		
		/**
		 * set 掷骰子次数
		 */
		public function set times(value: int): void{
			this._times = value;
		}
		
		/**
		 * get 铜币转运次数
		 * @return 
		 */
		public function get moneyTimes(): int{
			return _moneyTimes;
		}
		
		/**
		 * set 铜币转运次数
		 */
		public function set moneyTimes(value: int): void{
			this._moneyTimes = value;
		}
		
		/**
		 * get 本次投掷的点数
		 * @return 
		 */
		public function get nowWine(): int{
			return _nowWine;
		}
		
		/**
		 * set 本次投掷的点数
		 */
		public function set nowWine(value: int): void{
			this._nowWine = value;
		}
		
		/**
		 * get 投掷点数合计
		 * @return 
		 */
		public function get wine(): int{
			return _wine;
		}
		
		/**
		 * set 投掷点数合计
		 */
		public function set wine(value: int): void{
			this._wine = value;
		}
		
		/**
		 * get 本次投掷的结果(111111:表示投掷的6个都是酒)
		 * @return 
		 */
		public function get detail(): int{
			return _detail;
		}
		
		/**
		 * set 本次投掷的结果(111111:表示投掷的6个都是酒)
		 */
		public function set detail(value: int): void{
			this._detail = value;
		}
		
		/**
		 * get 当前可获得奖励真气
		 * @return 
		 */
		public function get zhenqi(): int{
			return _zhenqi;
		}
		
		/**
		 * set 当前可获得奖励真气
		 */
		public function set zhenqi(value: int): void{
			this._zhenqi = value;
		}
		
		/**
		 * get 当前可获得奖励经验
		 * @return 
		 */
		public function get exp(): int{
			return _exp;
		}
		
		/**
		 * set 当前可获得奖励经验
		 */
		public function set exp(value: int): void{
			this._exp = value;
		}
		
	}
}