package com.game.team.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 当前地图队伍信息类
	 */
	public class MapTeamInfo extends Bean {
	
		//队伍Id
		private var _teamid: long;
		
		//队长Id
		private var _captainid: long;
		
		//国家
		private var _country: int;
		
		//队长名字
		private var _captainname: String;
		
		//队伍人数
		private var _teamnum: int;
		
		//队长等级
		private var _captainlv: int;
		
		//最高等级
		private var _highestlv: int;
		
		//平均等级
		private var _averagelv: int;
		
		//所在线路
		private var _line: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//队伍Id
			writeLong(_teamid);
			//队长Id
			writeLong(_captainid);
			//国家
			writeInt(_country);
			//队长名字
			writeString(_captainname);
			//队伍人数
			writeByte(_teamnum);
			//队长等级
			writeShort(_captainlv);
			//最高等级
			writeShort(_highestlv);
			//平均等级
			writeShort(_averagelv);
			//所在线路
			writeByte(_line);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//队伍Id
			_teamid = readLong();
			//队长Id
			_captainid = readLong();
			//国家
			_country = readInt();
			//队长名字
			_captainname = readString();
			//队伍人数
			_teamnum = readByte();
			//队长等级
			_captainlv = readShort();
			//最高等级
			_highestlv = readShort();
			//平均等级
			_averagelv = readShort();
			//所在线路
			_line = readByte();
			return true;
		}
		
		/**
		 * get 队伍Id
		 * @return 
		 */
		public function get teamid(): long{
			return _teamid;
		}
		
		/**
		 * set 队伍Id
		 */
		public function set teamid(value: long): void{
			this._teamid = value;
		}
		
		/**
		 * get 队长Id
		 * @return 
		 */
		public function get captainid(): long{
			return _captainid;
		}
		
		/**
		 * set 队长Id
		 */
		public function set captainid(value: long): void{
			this._captainid = value;
		}
		
		/**
		 * get 国家
		 * @return 
		 */
		public function get country(): int{
			return _country;
		}
		
		/**
		 * set 国家
		 */
		public function set country(value: int): void{
			this._country = value;
		}
		
		/**
		 * get 队长名字
		 * @return 
		 */
		public function get captainname(): String{
			return _captainname;
		}
		
		/**
		 * set 队长名字
		 */
		public function set captainname(value: String): void{
			this._captainname = value;
		}
		
		/**
		 * get 队伍人数
		 * @return 
		 */
		public function get teamnum(): int{
			return _teamnum;
		}
		
		/**
		 * set 队伍人数
		 */
		public function set teamnum(value: int): void{
			this._teamnum = value;
		}
		
		/**
		 * get 队长等级
		 * @return 
		 */
		public function get captainlv(): int{
			return _captainlv;
		}
		
		/**
		 * set 队长等级
		 */
		public function set captainlv(value: int): void{
			this._captainlv = value;
		}
		
		/**
		 * get 最高等级
		 * @return 
		 */
		public function get highestlv(): int{
			return _highestlv;
		}
		
		/**
		 * set 最高等级
		 */
		public function set highestlv(value: int): void{
			this._highestlv = value;
		}
		
		/**
		 * get 平均等级
		 * @return 
		 */
		public function get averagelv(): int{
			return _averagelv;
		}
		
		/**
		 * set 平均等级
		 */
		public function set averagelv(value: int): void{
			this._averagelv = value;
		}
		
		/**
		 * get 所在线路
		 * @return 
		 */
		public function get line(): int{
			return _line;
		}
		
		/**
		 * set 所在线路
		 */
		public function set line(value: int): void{
			this._line = value;
		}
		
	}
}