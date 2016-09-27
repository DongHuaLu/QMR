package com.game.marriage.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 红包信息
	 */
	public class RedEnvelopeInfo extends Bean {
	
		//玩家id
		private var _playerid: long;
		
		//玩家名字
		private var _playername: String;
		
		//玩家等级
		private var _playerlv: int;
		
		//红包数量
		private var _rednum: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家id
			writeLong(_playerid);
			//玩家名字
			writeString(_playername);
			//玩家等级
			writeInt(_playerlv);
			//红包数量
			writeInt(_rednum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家id
			_playerid = readLong();
			//玩家名字
			_playername = readString();
			//玩家等级
			_playerlv = readInt();
			//红包数量
			_rednum = readInt();
			return true;
		}
		
		/**
		 * get 玩家id
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 玩家id
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
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
		 * get 红包数量
		 * @return 
		 */
		public function get rednum(): int{
			return _rednum;
		}
		
		/**
		 * set 红包数量
		 */
		public function set rednum(value: int): void{
			this._rednum = value;
		}
		
	}
}