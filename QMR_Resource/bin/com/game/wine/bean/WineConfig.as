package com.game.wine.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 配置信息
	 */
	public class WineConfig extends Bean {
	
		//掷骰子的次数上限
		private var _maxTimes: int;
		
		//铜币改运的次数上限
		private var _maxMoneyTimes: int;
		
		//转运需要的铜币数量
		private var _money: int;
		
		//转运需要的元宝数量
		private var _gold: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//掷骰子的次数上限
			writeByte(_maxTimes);
			//铜币改运的次数上限
			writeByte(_maxMoneyTimes);
			//转运需要的铜币数量
			writeInt(_money);
			//转运需要的元宝数量
			writeInt(_gold);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//掷骰子的次数上限
			_maxTimes = readByte();
			//铜币改运的次数上限
			_maxMoneyTimes = readByte();
			//转运需要的铜币数量
			_money = readInt();
			//转运需要的元宝数量
			_gold = readInt();
			return true;
		}
		
		/**
		 * get 掷骰子的次数上限
		 * @return 
		 */
		public function get maxTimes(): int{
			return _maxTimes;
		}
		
		/**
		 * set 掷骰子的次数上限
		 */
		public function set maxTimes(value: int): void{
			this._maxTimes = value;
		}
		
		/**
		 * get 铜币改运的次数上限
		 * @return 
		 */
		public function get maxMoneyTimes(): int{
			return _maxMoneyTimes;
		}
		
		/**
		 * set 铜币改运的次数上限
		 */
		public function set maxMoneyTimes(value: int): void{
			this._maxMoneyTimes = value;
		}
		
		/**
		 * get 转运需要的铜币数量
		 * @return 
		 */
		public function get money(): int{
			return _money;
		}
		
		/**
		 * set 转运需要的铜币数量
		 */
		public function set money(value: int): void{
			this._money = value;
		}
		
		/**
		 * get 转运需要的元宝数量
		 * @return 
		 */
		public function get gold(): int{
			return _gold;
		}
		
		/**
		 * set 转运需要的元宝数量
		 */
		public function set gold(value: int): void{
			this._gold = value;
		}
		
	}
}