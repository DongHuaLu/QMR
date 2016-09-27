package com.game.guild.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 成员信息
	 */
	public class MemberInfo extends Bean {
	
		//玩家id
		private var _userId: long;
		
		//玩家名
		private var _userName: String;
		
		//帮会id
		private var _guildId: long;
		
		//帮会名
		private var _guildName: String;
		
		//所在地图Id
		private var _mapId: int;
		
		//玩家等级
		private var _level: int;
		
		//帮会职位等级 1 帮主 2 副帮主 3 长老 4 堂主 5 舵主
		private var _guildPowerLevel: int;
		
		//昵称
		private var _nickName: String;
		
		//分组名
		private var _groupName: String;
		
		//贡献点
		private var _contributionPoint: int;
		
		//历史贡献点
		private var _contributionPointHistory: int;
		
		//入会时间
		private var _addTime: int;
		
		//上次在线时间
		private var _lastOnlineTime: int;
		
		//坐骑阶数
		private var _mountLevel: int;
		
		//弓箭阶数
		private var _arrowLevel: int;
		
		//天元阶数
		private var _tianyuanLevel: int;
		
		//声望点
		private var _prestigePoint: int;
		
		//成就点
		private var _achievementPoint: int;
		
		//战斗力
		private var _fightPower: int;
		
		//历史青龙令牌数
		private var _dragonHistory: int;
		
		//历史白虎令牌数
		private var _whiteTigerHistory: int;
		
		//历史朱雀令牌数
		private var _suzakuHistory: int;
		
		//历史玄武令牌数
		private var _basalticHistory: int;
		
		//历史库存元宝
		private var _stockGoldHistory: long;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家id
			writeLong(_userId);
			//玩家名
			writeString(_userName);
			//帮会id
			writeLong(_guildId);
			//帮会名
			writeString(_guildName);
			//所在地图Id
			writeInt(_mapId);
			//玩家等级
			writeShort(_level);
			//帮会职位等级 1 帮主 2 副帮主 3 长老 4 堂主 5 舵主
			writeByte(_guildPowerLevel);
			//昵称
			writeString(_nickName);
			//分组名
			writeString(_groupName);
			//贡献点
			writeInt(_contributionPoint);
			//历史贡献点
			writeInt(_contributionPointHistory);
			//入会时间
			writeInt(_addTime);
			//上次在线时间
			writeInt(_lastOnlineTime);
			//坐骑阶数
			writeByte(_mountLevel);
			//弓箭阶数
			writeByte(_arrowLevel);
			//天元阶数
			writeByte(_tianyuanLevel);
			//声望点
			writeInt(_prestigePoint);
			//成就点
			writeInt(_achievementPoint);
			//战斗力
			writeInt(_fightPower);
			//历史青龙令牌数
			writeInt(_dragonHistory);
			//历史白虎令牌数
			writeInt(_whiteTigerHistory);
			//历史朱雀令牌数
			writeInt(_suzakuHistory);
			//历史玄武令牌数
			writeInt(_basalticHistory);
			//历史库存元宝
			writeLong(_stockGoldHistory);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家id
			_userId = readLong();
			//玩家名
			_userName = readString();
			//帮会id
			_guildId = readLong();
			//帮会名
			_guildName = readString();
			//所在地图Id
			_mapId = readInt();
			//玩家等级
			_level = readShort();
			//帮会职位等级 1 帮主 2 副帮主 3 长老 4 堂主 5 舵主
			_guildPowerLevel = readByte();
			//昵称
			_nickName = readString();
			//分组名
			_groupName = readString();
			//贡献点
			_contributionPoint = readInt();
			//历史贡献点
			_contributionPointHistory = readInt();
			//入会时间
			_addTime = readInt();
			//上次在线时间
			_lastOnlineTime = readInt();
			//坐骑阶数
			_mountLevel = readByte();
			//弓箭阶数
			_arrowLevel = readByte();
			//天元阶数
			_tianyuanLevel = readByte();
			//声望点
			_prestigePoint = readInt();
			//成就点
			_achievementPoint = readInt();
			//战斗力
			_fightPower = readInt();
			//历史青龙令牌数
			_dragonHistory = readInt();
			//历史白虎令牌数
			_whiteTigerHistory = readInt();
			//历史朱雀令牌数
			_suzakuHistory = readInt();
			//历史玄武令牌数
			_basalticHistory = readInt();
			//历史库存元宝
			_stockGoldHistory = readLong();
			return true;
		}
		
		/**
		 * get 玩家id
		 * @return 
		 */
		public function get userId(): long{
			return _userId;
		}
		
		/**
		 * set 玩家id
		 */
		public function set userId(value: long): void{
			this._userId = value;
		}
		
		/**
		 * get 玩家名
		 * @return 
		 */
		public function get userName(): String{
			return _userName;
		}
		
		/**
		 * set 玩家名
		 */
		public function set userName(value: String): void{
			this._userName = value;
		}
		
		/**
		 * get 帮会id
		 * @return 
		 */
		public function get guildId(): long{
			return _guildId;
		}
		
		/**
		 * set 帮会id
		 */
		public function set guildId(value: long): void{
			this._guildId = value;
		}
		
		/**
		 * get 帮会名
		 * @return 
		 */
		public function get guildName(): String{
			return _guildName;
		}
		
		/**
		 * set 帮会名
		 */
		public function set guildName(value: String): void{
			this._guildName = value;
		}
		
		/**
		 * get 所在地图Id
		 * @return 
		 */
		public function get mapId(): int{
			return _mapId;
		}
		
		/**
		 * set 所在地图Id
		 */
		public function set mapId(value: int): void{
			this._mapId = value;
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
		 * get 帮会职位等级 1 帮主 2 副帮主 3 长老 4 堂主 5 舵主
		 * @return 
		 */
		public function get guildPowerLevel(): int{
			return _guildPowerLevel;
		}
		
		/**
		 * set 帮会职位等级 1 帮主 2 副帮主 3 长老 4 堂主 5 舵主
		 */
		public function set guildPowerLevel(value: int): void{
			this._guildPowerLevel = value;
		}
		
		/**
		 * get 昵称
		 * @return 
		 */
		public function get nickName(): String{
			return _nickName;
		}
		
		/**
		 * set 昵称
		 */
		public function set nickName(value: String): void{
			this._nickName = value;
		}
		
		/**
		 * get 分组名
		 * @return 
		 */
		public function get groupName(): String{
			return _groupName;
		}
		
		/**
		 * set 分组名
		 */
		public function set groupName(value: String): void{
			this._groupName = value;
		}
		
		/**
		 * get 贡献点
		 * @return 
		 */
		public function get contributionPoint(): int{
			return _contributionPoint;
		}
		
		/**
		 * set 贡献点
		 */
		public function set contributionPoint(value: int): void{
			this._contributionPoint = value;
		}
		
		/**
		 * get 历史贡献点
		 * @return 
		 */
		public function get contributionPointHistory(): int{
			return _contributionPointHistory;
		}
		
		/**
		 * set 历史贡献点
		 */
		public function set contributionPointHistory(value: int): void{
			this._contributionPointHistory = value;
		}
		
		/**
		 * get 入会时间
		 * @return 
		 */
		public function get addTime(): int{
			return _addTime;
		}
		
		/**
		 * set 入会时间
		 */
		public function set addTime(value: int): void{
			this._addTime = value;
		}
		
		/**
		 * get 上次在线时间
		 * @return 
		 */
		public function get lastOnlineTime(): int{
			return _lastOnlineTime;
		}
		
		/**
		 * set 上次在线时间
		 */
		public function set lastOnlineTime(value: int): void{
			this._lastOnlineTime = value;
		}
		
		/**
		 * get 坐骑阶数
		 * @return 
		 */
		public function get mountLevel(): int{
			return _mountLevel;
		}
		
		/**
		 * set 坐骑阶数
		 */
		public function set mountLevel(value: int): void{
			this._mountLevel = value;
		}
		
		/**
		 * get 弓箭阶数
		 * @return 
		 */
		public function get arrowLevel(): int{
			return _arrowLevel;
		}
		
		/**
		 * set 弓箭阶数
		 */
		public function set arrowLevel(value: int): void{
			this._arrowLevel = value;
		}
		
		/**
		 * get 天元阶数
		 * @return 
		 */
		public function get tianyuanLevel(): int{
			return _tianyuanLevel;
		}
		
		/**
		 * set 天元阶数
		 */
		public function set tianyuanLevel(value: int): void{
			this._tianyuanLevel = value;
		}
		
		/**
		 * get 声望点
		 * @return 
		 */
		public function get prestigePoint(): int{
			return _prestigePoint;
		}
		
		/**
		 * set 声望点
		 */
		public function set prestigePoint(value: int): void{
			this._prestigePoint = value;
		}
		
		/**
		 * get 成就点
		 * @return 
		 */
		public function get achievementPoint(): int{
			return _achievementPoint;
		}
		
		/**
		 * set 成就点
		 */
		public function set achievementPoint(value: int): void{
			this._achievementPoint = value;
		}
		
		/**
		 * get 战斗力
		 * @return 
		 */
		public function get fightPower(): int{
			return _fightPower;
		}
		
		/**
		 * set 战斗力
		 */
		public function set fightPower(value: int): void{
			this._fightPower = value;
		}
		
		/**
		 * get 历史青龙令牌数
		 * @return 
		 */
		public function get dragonHistory(): int{
			return _dragonHistory;
		}
		
		/**
		 * set 历史青龙令牌数
		 */
		public function set dragonHistory(value: int): void{
			this._dragonHistory = value;
		}
		
		/**
		 * get 历史白虎令牌数
		 * @return 
		 */
		public function get whiteTigerHistory(): int{
			return _whiteTigerHistory;
		}
		
		/**
		 * set 历史白虎令牌数
		 */
		public function set whiteTigerHistory(value: int): void{
			this._whiteTigerHistory = value;
		}
		
		/**
		 * get 历史朱雀令牌数
		 * @return 
		 */
		public function get suzakuHistory(): int{
			return _suzakuHistory;
		}
		
		/**
		 * set 历史朱雀令牌数
		 */
		public function set suzakuHistory(value: int): void{
			this._suzakuHistory = value;
		}
		
		/**
		 * get 历史玄武令牌数
		 * @return 
		 */
		public function get basalticHistory(): int{
			return _basalticHistory;
		}
		
		/**
		 * set 历史玄武令牌数
		 */
		public function set basalticHistory(value: int): void{
			this._basalticHistory = value;
		}
		
		/**
		 * get 历史库存元宝
		 * @return 
		 */
		public function get stockGoldHistory(): long{
			return _stockGoldHistory;
		}
		
		/**
		 * set 历史库存元宝
		 */
		public function set stockGoldHistory(value: long): void{
			this._stockGoldHistory = value;
		}
		
	}
}