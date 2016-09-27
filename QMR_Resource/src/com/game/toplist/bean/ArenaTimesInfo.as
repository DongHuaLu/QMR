package com.game.toplist.bean{
	import com.game.utils.long;
	import com.game.player.bean.PlayerAppearanceInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 竞技场连胜排行信息
	 */
	public class ArenaTimesInfo extends Bean {
	
		//角色id
		private var _playerid: long;
		
		//名称
		private var _name: String;
		
		//等级
		private var _level: int;
		
		//战斗力
		private var _fightpower: int;
		
		//连胜场次
		private var _wintimes: int;
		
		//勋章
		private var _medal: int;
		
		//外观
		private var _appearance: com.game.player.bean.PlayerAppearanceInfo;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色id
			writeLong(_playerid);
			//名称
			writeString(_name);
			//等级
			writeInt(_level);
			//战斗力
			writeInt(_fightpower);
			//连胜场次
			writeInt(_wintimes);
			//勋章
			writeInt(_medal);
			//外观
			writeBean(_appearance);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色id
			_playerid = readLong();
			//名称
			_name = readString();
			//等级
			_level = readInt();
			//战斗力
			_fightpower = readInt();
			//连胜场次
			_wintimes = readInt();
			//勋章
			_medal = readInt();
			//外观
			_appearance = readBean(com.game.player.bean.PlayerAppearanceInfo) as com.game.player.bean.PlayerAppearanceInfo;
			return true;
		}
		
		/**
		 * get 角色id
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 角色id
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 名称
		 * @return 
		 */
		public function get name(): String{
			return _name;
		}
		
		/**
		 * set 名称
		 */
		public function set name(value: String): void{
			this._name = value;
		}
		
		/**
		 * get 等级
		 * @return 
		 */
		public function get level(): int{
			return _level;
		}
		
		/**
		 * set 等级
		 */
		public function set level(value: int): void{
			this._level = value;
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
		
		/**
		 * get 连胜场次
		 * @return 
		 */
		public function get wintimes(): int{
			return _wintimes;
		}
		
		/**
		 * set 连胜场次
		 */
		public function set wintimes(value: int): void{
			this._wintimes = value;
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
		 * get 外观
		 * @return 
		 */
		public function get appearance(): com.game.player.bean.PlayerAppearanceInfo{
			return _appearance;
		}
		
		/**
		 * set 外观
		 */
		public function set appearance(value: com.game.player.bean.PlayerAppearanceInfo): void{
			this._appearance = value;
		}
		
	}
}