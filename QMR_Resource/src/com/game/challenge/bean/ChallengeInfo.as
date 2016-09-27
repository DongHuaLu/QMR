package com.game.challenge.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 挑战信息
	 */
	public class ChallengeInfo extends Bean {
	
		//秦王战役副本剩余次数
		private var _zonesnum: int;
		
		//BOSS刷新时间
		private var _bosstime: String;
		
		//攻城战开始时间
		private var _gongchengtime: String;
		
		//领地战开始时间
		private var _lingditime: String;
		
		//地宫寻宝剩余次数
		private var _epalacenum: String;
		
		//挑战校场剩余次数
		private var _jiaochangnum: int;
		
		//双倍收益时间
		private var _doubletime: String;
		
		//迷宫开始时间
		private var _mazetime: String;
		
		//比武岛时间
		private var _biwudaotime: String;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//秦王战役副本剩余次数
			writeInt(_zonesnum);
			//BOSS刷新时间
			writeString(_bosstime);
			//攻城战开始时间
			writeString(_gongchengtime);
			//领地战开始时间
			writeString(_lingditime);
			//地宫寻宝剩余次数
			writeString(_epalacenum);
			//挑战校场剩余次数
			writeInt(_jiaochangnum);
			//双倍收益时间
			writeString(_doubletime);
			//迷宫开始时间
			writeString(_mazetime);
			//比武岛时间
			writeString(_biwudaotime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//秦王战役副本剩余次数
			_zonesnum = readInt();
			//BOSS刷新时间
			_bosstime = readString();
			//攻城战开始时间
			_gongchengtime = readString();
			//领地战开始时间
			_lingditime = readString();
			//地宫寻宝剩余次数
			_epalacenum = readString();
			//挑战校场剩余次数
			_jiaochangnum = readInt();
			//双倍收益时间
			_doubletime = readString();
			//迷宫开始时间
			_mazetime = readString();
			//比武岛时间
			_biwudaotime = readString();
			return true;
		}
		
		/**
		 * get 秦王战役副本剩余次数
		 * @return 
		 */
		public function get zonesnum(): int{
			return _zonesnum;
		}
		
		/**
		 * set 秦王战役副本剩余次数
		 */
		public function set zonesnum(value: int): void{
			this._zonesnum = value;
		}
		
		/**
		 * get BOSS刷新时间
		 * @return 
		 */
		public function get bosstime(): String{
			return _bosstime;
		}
		
		/**
		 * set BOSS刷新时间
		 */
		public function set bosstime(value: String): void{
			this._bosstime = value;
		}
		
		/**
		 * get 攻城战开始时间
		 * @return 
		 */
		public function get gongchengtime(): String{
			return _gongchengtime;
		}
		
		/**
		 * set 攻城战开始时间
		 */
		public function set gongchengtime(value: String): void{
			this._gongchengtime = value;
		}
		
		/**
		 * get 领地战开始时间
		 * @return 
		 */
		public function get lingditime(): String{
			return _lingditime;
		}
		
		/**
		 * set 领地战开始时间
		 */
		public function set lingditime(value: String): void{
			this._lingditime = value;
		}
		
		/**
		 * get 地宫寻宝剩余次数
		 * @return 
		 */
		public function get epalacenum(): String{
			return _epalacenum;
		}
		
		/**
		 * set 地宫寻宝剩余次数
		 */
		public function set epalacenum(value: String): void{
			this._epalacenum = value;
		}
		
		/**
		 * get 挑战校场剩余次数
		 * @return 
		 */
		public function get jiaochangnum(): int{
			return _jiaochangnum;
		}
		
		/**
		 * set 挑战校场剩余次数
		 */
		public function set jiaochangnum(value: int): void{
			this._jiaochangnum = value;
		}
		
		/**
		 * get 双倍收益时间
		 * @return 
		 */
		public function get doubletime(): String{
			return _doubletime;
		}
		
		/**
		 * set 双倍收益时间
		 */
		public function set doubletime(value: String): void{
			this._doubletime = value;
		}
		
		/**
		 * get 迷宫开始时间
		 * @return 
		 */
		public function get mazetime(): String{
			return _mazetime;
		}
		
		/**
		 * set 迷宫开始时间
		 */
		public function set mazetime(value: String): void{
			this._mazetime = value;
		}
		
		/**
		 * get 比武岛时间
		 * @return 
		 */
		public function get biwudaotime(): String{
			return _biwudaotime;
		}
		
		/**
		 * set 比武岛时间
		 */
		public function set biwudaotime(value: String): void{
			this._biwudaotime = value;
		}
		
	}
}