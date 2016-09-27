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
	 * 皇城战排行榜信息
	 */
	public class EmperorWarTopInfo extends Bean {
	
		//玩家名字
		private var _playername: String;
		
		//帮会名字
		private var _guildname: String;
		
		//玩家ID
		private var _playerid: long;
		
		//玩家等级
		private var _playerlevel: int;
		
		//玩家国家ID
		private var _playercountry: int;
		
		//杀敌数量
		private var _kill: int;
		
		//死亡次数
		private var _death: int;
		
		//总伤害输出
		private var _hurt: int;
		
		//被伤害总数
		private var _beenhurt: int;
		
		//排名
		private var _ranking: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家名字
			writeString(_playername);
			//帮会名字
			writeString(_guildname);
			//玩家ID
			writeLong(_playerid);
			//玩家等级
			writeInt(_playerlevel);
			//玩家国家ID
			writeInt(_playercountry);
			//杀敌数量
			writeInt(_kill);
			//死亡次数
			writeInt(_death);
			//总伤害输出
			writeInt(_hurt);
			//被伤害总数
			writeInt(_beenhurt);
			//排名
			writeInt(_ranking);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家名字
			_playername = readString();
			//帮会名字
			_guildname = readString();
			//玩家ID
			_playerid = readLong();
			//玩家等级
			_playerlevel = readInt();
			//玩家国家ID
			_playercountry = readInt();
			//杀敌数量
			_kill = readInt();
			//死亡次数
			_death = readInt();
			//总伤害输出
			_hurt = readInt();
			//被伤害总数
			_beenhurt = readInt();
			//排名
			_ranking = readInt();
			return true;
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
		 * get 玩家等级
		 * @return 
		 */
		public function get playerlevel(): int{
			return _playerlevel;
		}
		
		/**
		 * set 玩家等级
		 */
		public function set playerlevel(value: int): void{
			this._playerlevel = value;
		}
		
		/**
		 * get 玩家国家ID
		 * @return 
		 */
		public function get playercountry(): int{
			return _playercountry;
		}
		
		/**
		 * set 玩家国家ID
		 */
		public function set playercountry(value: int): void{
			this._playercountry = value;
		}
		
		/**
		 * get 杀敌数量
		 * @return 
		 */
		public function get kill(): int{
			return _kill;
		}
		
		/**
		 * set 杀敌数量
		 */
		public function set kill(value: int): void{
			this._kill = value;
		}
		
		/**
		 * get 死亡次数
		 * @return 
		 */
		public function get death(): int{
			return _death;
		}
		
		/**
		 * set 死亡次数
		 */
		public function set death(value: int): void{
			this._death = value;
		}
		
		/**
		 * get 总伤害输出
		 * @return 
		 */
		public function get hurt(): int{
			return _hurt;
		}
		
		/**
		 * set 总伤害输出
		 */
		public function set hurt(value: int): void{
			this._hurt = value;
		}
		
		/**
		 * get 被伤害总数
		 * @return 
		 */
		public function get beenhurt(): int{
			return _beenhurt;
		}
		
		/**
		 * set 被伤害总数
		 */
		public function set beenhurt(value: int): void{
			this._beenhurt = value;
		}
		
		/**
		 * get 排名
		 * @return 
		 */
		public function get ranking(): int{
			return _ranking;
		}
		
		/**
		 * set 排名
		 */
		public function set ranking(value: int): void{
			this._ranking = value;
		}
		
	}
}