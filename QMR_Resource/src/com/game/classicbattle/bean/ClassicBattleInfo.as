package com.game.classicbattle.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 战役信息
	 */
	public class ClassicBattleInfo extends Bean {
	
		//怪物列表
		private var _monsterlist: String;
		
		//首次通关奖励列表
		private var _firstrewards: String;
		
		//是否首次通关，0否，1是
		private var _isfirst: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//怪物列表
			writeString(_monsterlist);
			//首次通关奖励列表
			writeString(_firstrewards);
			//是否首次通关，0否，1是
			writeByte(_isfirst);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//怪物列表
			_monsterlist = readString();
			//首次通关奖励列表
			_firstrewards = readString();
			//是否首次通关，0否，1是
			_isfirst = readByte();
			return true;
		}
		
		/**
		 * get 怪物列表
		 * @return 
		 */
		public function get monsterlist(): String{
			return _monsterlist;
		}
		
		/**
		 * set 怪物列表
		 */
		public function set monsterlist(value: String): void{
			this._monsterlist = value;
		}
		
		/**
		 * get 首次通关奖励列表
		 * @return 
		 */
		public function get firstrewards(): String{
			return _firstrewards;
		}
		
		/**
		 * set 首次通关奖励列表
		 */
		public function set firstrewards(value: String): void{
			this._firstrewards = value;
		}
		
		/**
		 * get 是否首次通关，0否，1是
		 * @return 
		 */
		public function get isfirst(): int{
			return _isfirst;
		}
		
		/**
		 * set 是否首次通关，0否，1是
		 */
		public function set isfirst(value: int): void{
			this._isfirst = value;
		}
		
	}
}