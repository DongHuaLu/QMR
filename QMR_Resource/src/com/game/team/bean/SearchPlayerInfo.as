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
	 * 通用搜索玩家信息
	 */
	public class SearchPlayerInfo extends Bean {
	
		//玩家Id
		private var _playerid: long;
		
		//玩家等级
		private var _playerlv: int;
		
		//玩家名字
		private var _playername: String;
		
		//线路
		private var _line: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家Id
			writeLong(_playerid);
			//玩家等级
			writeShort(_playerlv);
			//玩家名字
			writeString(_playername);
			//线路
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
			//玩家名字
			_playername = readString();
			//线路
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
		 * get 线路
		 * @return 
		 */
		public function get line(): int{
			return _line;
		}
		
		/**
		 * set 线路
		 */
		public function set line(value: int): void{
			this._line = value;
		}
		
	}
}