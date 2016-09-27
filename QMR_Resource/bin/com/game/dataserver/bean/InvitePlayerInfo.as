package com.game.dataserver.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 跨服邀请单个玩家信息
	 */
	public class InvitePlayerInfo extends Bean {
	
		//玩家ID
		private var _playerId: long;
		
		//玩家等级
		private var _level: int;
		
		//玩家名字
		private var _name: String;
		
		//勋章
		private var _medal: int;
		
		//荣誉值
		private var _honor: int;
		
		//战斗力 
		private var _fightpower: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家ID
			writeLong(_playerId);
			//玩家等级
			writeInt(_level);
			//玩家名字
			writeString(_name);
			//勋章
			writeInt(_medal);
			//荣誉值
			writeInt(_honor);
			//战斗力 
			writeInt(_fightpower);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家ID
			_playerId = readLong();
			//玩家等级
			_level = readInt();
			//玩家名字
			_name = readString();
			//勋章
			_medal = readInt();
			//荣誉值
			_honor = readInt();
			//战斗力 
			_fightpower = readInt();
			return true;
		}
		
		/**
		 * get 玩家ID
		 * @return 
		 */
		public function get playerId(): long{
			return _playerId;
		}
		
		/**
		 * set 玩家ID
		 */
		public function set playerId(value: long): void{
			this._playerId = value;
		}
		
		/**
		 * get 玩家等级
		 * @return 
		 */
		public function get level(): int{
			return _level;
		}
		
		/**
		 * set 玩家等级
		 */
		public function set level(value: int): void{
			this._level = value;
		}
		
		/**
		 * get 玩家名字
		 * @return 
		 */
		public function get name(): String{
			return _name;
		}
		
		/**
		 * set 玩家名字
		 */
		public function set name(value: String): void{
			this._name = value;
		}
		
		/**
		 * get 勋章
		 * @return 
		 */
		public function get medal(): int{
			return _medal;
		}
		
		/**
		 * set 勋章
		 */
		public function set medal(value: int): void{
			this._medal = value;
		}
		
		/**
		 * get 荣誉值
		 * @return 
		 */
		public function get honor(): int{
			return _honor;
		}
		
		/**
		 * set 荣誉值
		 */
		public function set honor(value: int): void{
			this._honor = value;
		}
		
		/**
		 * get 战斗力 
		 * @return 
		 */
		public function get fightpower(): int{
			return _fightpower;
		}
		
		/**
		 * set 战斗力 
		 */
		public function set fightpower(value: int): void{
			this._fightpower = value;
		}
		
	}
}