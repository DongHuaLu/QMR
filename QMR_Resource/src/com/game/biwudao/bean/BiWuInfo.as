package com.game.biwudao.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 比武岛信息
	 */
	public class BiWuInfo extends Bean {
	
		//区域经验倍率
		private var _areadouble: int;
		
		//每次可获得经验值
		private var _availableexp: int;
		
		//每次可获得真气值
		private var _availablezhenqi: int;
		
		//旗帜占领者帮会名字
		private var _guildname: String;
		
		//旗帜占领者帮会id
		private var _guildid: long;
		
		//累计获得经验
		private var _totalexp: int;
		
		//累计获得真气
		private var _totalzhenqi: int;
		
		//累计获得军功
		private var _totalrank: int;
		
		//累计开启宝箱
		private var _totalBox: int;
		
		//活动剩余时间(秒)
		private var _surplustime: int;
		
		//夺旗剩余冷却时间（秒）
		private var _flagcooldown: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//区域经验倍率
			writeInt(_areadouble);
			//每次可获得经验值
			writeInt(_availableexp);
			//每次可获得真气值
			writeInt(_availablezhenqi);
			//旗帜占领者帮会名字
			writeString(_guildname);
			//旗帜占领者帮会id
			writeLong(_guildid);
			//累计获得经验
			writeInt(_totalexp);
			//累计获得真气
			writeInt(_totalzhenqi);
			//累计获得军功
			writeInt(_totalrank);
			//累计开启宝箱
			writeInt(_totalBox);
			//活动剩余时间(秒)
			writeInt(_surplustime);
			//夺旗剩余冷却时间（秒）
			writeInt(_flagcooldown);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//区域经验倍率
			_areadouble = readInt();
			//每次可获得经验值
			_availableexp = readInt();
			//每次可获得真气值
			_availablezhenqi = readInt();
			//旗帜占领者帮会名字
			_guildname = readString();
			//旗帜占领者帮会id
			_guildid = readLong();
			//累计获得经验
			_totalexp = readInt();
			//累计获得真气
			_totalzhenqi = readInt();
			//累计获得军功
			_totalrank = readInt();
			//累计开启宝箱
			_totalBox = readInt();
			//活动剩余时间(秒)
			_surplustime = readInt();
			//夺旗剩余冷却时间（秒）
			_flagcooldown = readInt();
			return true;
		}
		
		/**
		 * get 区域经验倍率
		 * @return 
		 */
		public function get areadouble(): int{
			return _areadouble;
		}
		
		/**
		 * set 区域经验倍率
		 */
		public function set areadouble(value: int): void{
			this._areadouble = value;
		}
		
		/**
		 * get 每次可获得经验值
		 * @return 
		 */
		public function get availableexp(): int{
			return _availableexp;
		}
		
		/**
		 * set 每次可获得经验值
		 */
		public function set availableexp(value: int): void{
			this._availableexp = value;
		}
		
		/**
		 * get 每次可获得真气值
		 * @return 
		 */
		public function get availablezhenqi(): int{
			return _availablezhenqi;
		}
		
		/**
		 * set 每次可获得真气值
		 */
		public function set availablezhenqi(value: int): void{
			this._availablezhenqi = value;
		}
		
		/**
		 * get 旗帜占领者帮会名字
		 * @return 
		 */
		public function get guildname(): String{
			return _guildname;
		}
		
		/**
		 * set 旗帜占领者帮会名字
		 */
		public function set guildname(value: String): void{
			this._guildname = value;
		}
		
		/**
		 * get 旗帜占领者帮会id
		 * @return 
		 */
		public function get guildid(): long{
			return _guildid;
		}
		
		/**
		 * set 旗帜占领者帮会id
		 */
		public function set guildid(value: long): void{
			this._guildid = value;
		}
		
		/**
		 * get 累计获得经验
		 * @return 
		 */
		public function get totalexp(): int{
			return _totalexp;
		}
		
		/**
		 * set 累计获得经验
		 */
		public function set totalexp(value: int): void{
			this._totalexp = value;
		}
		
		/**
		 * get 累计获得真气
		 * @return 
		 */
		public function get totalzhenqi(): int{
			return _totalzhenqi;
		}
		
		/**
		 * set 累计获得真气
		 */
		public function set totalzhenqi(value: int): void{
			this._totalzhenqi = value;
		}
		
		/**
		 * get 累计获得军功
		 * @return 
		 */
		public function get totalrank(): int{
			return _totalrank;
		}
		
		/**
		 * set 累计获得军功
		 */
		public function set totalrank(value: int): void{
			this._totalrank = value;
		}
		
		/**
		 * get 累计开启宝箱
		 * @return 
		 */
		public function get totalBox(): int{
			return _totalBox;
		}
		
		/**
		 * set 累计开启宝箱
		 */
		public function set totalBox(value: int): void{
			this._totalBox = value;
		}
		
		/**
		 * get 活动剩余时间(秒)
		 * @return 
		 */
		public function get surplustime(): int{
			return _surplustime;
		}
		
		/**
		 * set 活动剩余时间(秒)
		 */
		public function set surplustime(value: int): void{
			this._surplustime = value;
		}
		
		/**
		 * get 夺旗剩余冷却时间（秒）
		 * @return 
		 */
		public function get flagcooldown(): int{
			return _flagcooldown;
		}
		
		/**
		 * set 夺旗剩余冷却时间（秒）
		 */
		public function set flagcooldown(value: int): void{
			this._flagcooldown = value;
		}
		
	}
}