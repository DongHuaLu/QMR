package com.game.country.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 王城成员信息
	 */
	public class CountryMemberInfo extends Bean {
	
		//职位：1城主，2列侯，3丞相，4太尉，5御史大夫，6夫人
		private var _post: int;
		
		//玩家ID
		private var _playerid: long;
		
		//玩家名字
		private var _playername: String;
		
		//玩家性别
		private var _sex: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//职位：1城主，2列侯，3丞相，4太尉，5御史大夫，6夫人
			writeByte(_post);
			//玩家ID
			writeLong(_playerid);
			//玩家名字
			writeString(_playername);
			//玩家性别
			writeInt(_sex);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//职位：1城主，2列侯，3丞相，4太尉，5御史大夫，6夫人
			_post = readByte();
			//玩家ID
			_playerid = readLong();
			//玩家名字
			_playername = readString();
			//玩家性别
			_sex = readInt();
			return true;
		}
		
		/**
		 * get 职位：1城主，2列侯，3丞相，4太尉，5御史大夫，6夫人
		 * @return 
		 */
		public function get post(): int{
			return _post;
		}
		
		/**
		 * set 职位：1城主，2列侯，3丞相，4太尉，5御史大夫，6夫人
		 */
		public function set post(value: int): void{
			this._post = value;
		}
		
		/**
		 * get 玩家ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 玩家ID
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
		 * get 玩家性别
		 * @return 
		 */
		public function get sex(): int{
			return _sex;
		}
		
		/**
		 * set 玩家性别
		 */
		public function set sex(value: int): void{
			this._sex = value;
		}
		
	}
}