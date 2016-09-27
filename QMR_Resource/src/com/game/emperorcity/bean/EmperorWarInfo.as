package com.game.emperorcity.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 皇城争霸战即时信息
	 */
	public class EmperorWarInfo extends Bean {
	
		//皇城占领者国家ID
		private var _countryid: int;
		
		//皇城帮会ID
		private var _emperorguildid: long;
		
		//皇城帮会名字
		private var _guildname: String;
		
		//皇帝ID
		private var _emperorid: long;
		
		//皇帝名字
		private var _emperorname: String;
		
		//占领旗帜时间（秒）
		private var _occupytime: int;
		
		//皇城争霸战结束剩余时间（秒）
		private var _endtime: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//皇城占领者国家ID
			writeInt(_countryid);
			//皇城帮会ID
			writeLong(_emperorguildid);
			//皇城帮会名字
			writeString(_guildname);
			//皇帝ID
			writeLong(_emperorid);
			//皇帝名字
			writeString(_emperorname);
			//占领旗帜时间（秒）
			writeInt(_occupytime);
			//皇城争霸战结束剩余时间（秒）
			writeInt(_endtime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//皇城占领者国家ID
			_countryid = readInt();
			//皇城帮会ID
			_emperorguildid = readLong();
			//皇城帮会名字
			_guildname = readString();
			//皇帝ID
			_emperorid = readLong();
			//皇帝名字
			_emperorname = readString();
			//占领旗帜时间（秒）
			_occupytime = readInt();
			//皇城争霸战结束剩余时间（秒）
			_endtime = readInt();
			return true;
		}
		
		/**
		 * get 皇城占领者国家ID
		 * @return 
		 */
		public function get countryid(): int{
			return _countryid;
		}
		
		/**
		 * set 皇城占领者国家ID
		 */
		public function set countryid(value: int): void{
			this._countryid = value;
		}
		
		/**
		 * get 皇城帮会ID
		 * @return 
		 */
		public function get emperorguildid(): long{
			return _emperorguildid;
		}
		
		/**
		 * set 皇城帮会ID
		 */
		public function set emperorguildid(value: long): void{
			this._emperorguildid = value;
		}
		
		/**
		 * get 皇城帮会名字
		 * @return 
		 */
		public function get guildname(): String{
			return _guildname;
		}
		
		/**
		 * set 皇城帮会名字
		 */
		public function set guildname(value: String): void{
			this._guildname = value;
		}
		
		/**
		 * get 皇帝ID
		 * @return 
		 */
		public function get emperorid(): long{
			return _emperorid;
		}
		
		/**
		 * set 皇帝ID
		 */
		public function set emperorid(value: long): void{
			this._emperorid = value;
		}
		
		/**
		 * get 皇帝名字
		 * @return 
		 */
		public function get emperorname(): String{
			return _emperorname;
		}
		
		/**
		 * set 皇帝名字
		 */
		public function set emperorname(value: String): void{
			this._emperorname = value;
		}
		
		/**
		 * get 占领旗帜时间（秒）
		 * @return 
		 */
		public function get occupytime(): int{
			return _occupytime;
		}
		
		/**
		 * set 占领旗帜时间（秒）
		 */
		public function set occupytime(value: int): void{
			this._occupytime = value;
		}
		
		/**
		 * get 皇城争霸战结束剩余时间（秒）
		 * @return 
		 */
		public function get endtime(): int{
			return _endtime;
		}
		
		/**
		 * set 皇城争霸战结束剩余时间（秒）
		 */
		public function set endtime(value: int): void{
			this._endtime = value;
		}
		
	}
}