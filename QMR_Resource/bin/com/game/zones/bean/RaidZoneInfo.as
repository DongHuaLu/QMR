package com.game.zones.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 副本扫荡信息
	 */
	public class RaidZoneInfo extends Bean {
	
		//副本编号
		private var _zoneid: int;
		
		//最快通关时间（时间秒）
		private var _throughtime: int;
		
		//当前副本排名
		private var _ranking: int;
		
		//手动扫荡次数
		private var _manualmun: int;
		
		//自动扫荡次数
		private var _automun: int;
		
		//星星数量，评价（0表示没通关）
		private var _starnum: int;
		
		//全服最快通关时间（时间秒）
		private var _fulltime: int;
		
		//全服最快通关者名字
		private var _fullname: String;
		
		//今日是否通关，2是通关，1是未通关，0是未进入
		private var _clearance: int;
		
		//战魂搜索次数
		private var _zhanhunnum: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//副本编号
			writeInt(_zoneid);
			//最快通关时间（时间秒）
			writeInt(_throughtime);
			//当前副本排名
			writeInt(_ranking);
			//手动扫荡次数
			writeByte(_manualmun);
			//自动扫荡次数
			writeByte(_automun);
			//星星数量，评价（0表示没通关）
			writeByte(_starnum);
			//全服最快通关时间（时间秒）
			writeInt(_fulltime);
			//全服最快通关者名字
			writeString(_fullname);
			//今日是否通关，2是通关，1是未通关，0是未进入
			writeByte(_clearance);
			//战魂搜索次数
			writeInt(_zhanhunnum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//副本编号
			_zoneid = readInt();
			//最快通关时间（时间秒）
			_throughtime = readInt();
			//当前副本排名
			_ranking = readInt();
			//手动扫荡次数
			_manualmun = readByte();
			//自动扫荡次数
			_automun = readByte();
			//星星数量，评价（0表示没通关）
			_starnum = readByte();
			//全服最快通关时间（时间秒）
			_fulltime = readInt();
			//全服最快通关者名字
			_fullname = readString();
			//今日是否通关，2是通关，1是未通关，0是未进入
			_clearance = readByte();
			//战魂搜索次数
			_zhanhunnum = readInt();
			return true;
		}
		
		/**
		 * get 副本编号
		 * @return 
		 */
		public function get zoneid(): int{
			return _zoneid;
		}
		
		/**
		 * set 副本编号
		 */
		public function set zoneid(value: int): void{
			this._zoneid = value;
		}
		
		/**
		 * get 最快通关时间（时间秒）
		 * @return 
		 */
		public function get throughtime(): int{
			return _throughtime;
		}
		
		/**
		 * set 最快通关时间（时间秒）
		 */
		public function set throughtime(value: int): void{
			this._throughtime = value;
		}
		
		/**
		 * get 当前副本排名
		 * @return 
		 */
		public function get ranking(): int{
			return _ranking;
		}
		
		/**
		 * set 当前副本排名
		 */
		public function set ranking(value: int): void{
			this._ranking = value;
		}
		
		/**
		 * get 手动扫荡次数
		 * @return 
		 */
		public function get manualmun(): int{
			return _manualmun;
		}
		
		/**
		 * set 手动扫荡次数
		 */
		public function set manualmun(value: int): void{
			this._manualmun = value;
		}
		
		/**
		 * get 自动扫荡次数
		 * @return 
		 */
		public function get automun(): int{
			return _automun;
		}
		
		/**
		 * set 自动扫荡次数
		 */
		public function set automun(value: int): void{
			this._automun = value;
		}
		
		/**
		 * get 星星数量，评价（0表示没通关）
		 * @return 
		 */
		public function get starnum(): int{
			return _starnum;
		}
		
		/**
		 * set 星星数量，评价（0表示没通关）
		 */
		public function set starnum(value: int): void{
			this._starnum = value;
		}
		
		/**
		 * get 全服最快通关时间（时间秒）
		 * @return 
		 */
		public function get fulltime(): int{
			return _fulltime;
		}
		
		/**
		 * set 全服最快通关时间（时间秒）
		 */
		public function set fulltime(value: int): void{
			this._fulltime = value;
		}
		
		/**
		 * get 全服最快通关者名字
		 * @return 
		 */
		public function get fullname(): String{
			return _fullname;
		}
		
		/**
		 * set 全服最快通关者名字
		 */
		public function set fullname(value: String): void{
			this._fullname = value;
		}
		
		/**
		 * get 今日是否通关，2是通关，1是未通关，0是未进入
		 * @return 
		 */
		public function get clearance(): int{
			return _clearance;
		}
		
		/**
		 * set 今日是否通关，2是通关，1是未通关，0是未进入
		 */
		public function set clearance(value: int): void{
			this._clearance = value;
		}
		
		/**
		 * get 战魂搜索次数
		 * @return 
		 */
		public function get zhanhunnum(): int{
			return _zhanhunnum;
		}
		
		/**
		 * set 战魂搜索次数
		 */
		public function set zhanhunnum(value: int): void{
			this._zhanhunnum = value;
		}
		
	}
}