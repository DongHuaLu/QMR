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
	 * 当前地图玩家信息类
	 */
	public class MapPlayerInfo extends Bean {
	
		//玩家Id
		private var _playerid: long;
		
		//玩家等级
		private var _playerlv: int;
		
		//国家
		private var _country: int;
		
		//组队状态，0未组队，1已组队
		private var _teamstate: int;
		
		//玩家名字
		private var _playername: String;
		
		//帮会名字
		private var _guildname: String;
		
		//所在线路
		private var _line: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家Id
			writeLong(_playerid);
			//玩家等级
			writeShort(_playerlv);
			//国家
			writeInt(_country);
			//组队状态，0未组队，1已组队
			writeByte(_teamstate);
			//玩家名字
			writeString(_playername);
			//帮会名字
			writeString(_guildname);
			//所在线路
			writeByte(_line);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家Id
			_playerid = readLong();
			//玩家等级
			_playerlv = readShort();
			//国家
			_country = readInt();
			//组队状态，0未组队，1已组队
			_teamstate = readByte();
			//玩家名字
			_playername = readString();
			//帮会名字
			_guildname = readString();
			//所在线路
			_line = readByte();
			return true;
		}
		
		/**
		 * get 玩家Id
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 玩家Id
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 玩家等级
		 * @return 
		 */
		public function get playerlv(): int{
			return _playerlv;
		}
		
		/**
		 * set 玩家等级
		 */
		public function set playerlv(value: int): void{
			this._playerlv = value;
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
		 * get 组队状态，0未组队，1已组队
		 * @return 
		 */
		public function get teamstate(): int{
			return _teamstate;
		}
		
		/**
		 * set 组队状态，0未组队，1已组队
		 */
		public function set teamstate(value: int): void{
			this._teamstate = value;
		}
		
		/**
		 * get 玩家名字
		 * @return 
		 */
		public function get playername(): String{
			return _playername;
		}
		
		/**
		 * set 玩家名字
		 */
		public function set playername(value: String): void{
			this._playername = value;
		}
		
		/**
		 * get 帮会名字
		 * @return 
		 */
		public function get guildname(): String{
			return _guildname;
		}
		
		/**
		 * set 帮会名字
		 */
		public function set guildname(value: String): void{
			this._guildname = value;
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