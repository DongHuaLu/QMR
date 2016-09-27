package com.game.guild.bean{
	import com.game.guild.bean.DiplomaticInfo;
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 帮会信息
	 */
	public class GuildInfo extends Bean {
	
		//帮会id
		private var _guildId: long;
		
		//帮会名
		private var _guildName: String;
		
		//帮会旗帜
		private var _guildBanner: String;
		
		//帮会国家
		private var _guildCountry: int;
		
		//帮主id
		private var _bangZhuid: long;
		
		//帮主名字
		private var _bangZhuName: String;
		
		//帮主等级
		private var _bangZhuLevel: int;
		
		//帮主是否在线
		private var _bangZhuOnline: int;
		
		//副帮主id
		private var _viceBangZhuid: long;
		
		//副帮主名字
		private var _viceBangZhuName: String;
		
		//副帮主等级
		private var _viceBangZhuLevel: int;
		
		//副帮主是否在线
		private var _viceBangZhuOnline: int;
		
		//旗帜造型
		private var _bannerIcon: int;
		
		//旗帜等级
		private var _bannerLevel: int;
		
		//帮会公告
		private var _guildBulletin: String;
		
		//青龙令牌数
		private var _dragon: int;
		
		//白虎令牌数
		private var _whiteTiger: int;
		
		//朱雀令牌数
		private var _suzaku: int;
		
		//玄武令牌数
		private var _basaltic: int;
		
		//库存元宝
		private var _stockGold: long;
		
		//友好帮会列表
		private var _friendGuildList: Vector.<DiplomaticInfo> = new Vector.<DiplomaticInfo>();
		//敌对帮会列表
		private var _enemyGuildList: Vector.<DiplomaticInfo> = new Vector.<DiplomaticInfo>();
		//今日活跃值
		private var _activeValue: int;
		
		//解散警告值
		private var _warningValue: int;
		
		//自动同意加入帮会的申请
		private var _autoGuildAgreeAdd: int;
		
		//成员数量
		private var _memberNum: int;
		
		//成员战斗力之和
		private var _memberFightPower: int;
		
		//占领地图列表
		private var _ownMapList: Vector.<int> = new Vector.<int>();
		//拥有王城
		private var _ownKingCity: int;
		
		//拥有皇城
		private var _ownEmperorCity: int;
		
		//帮会创建时间
		private var _createTime: int;
		
		//帮会创建IP
		private var _createIp: String;
		
		//帮会公告最后更新时间
		private var _lastGuildBulletinTime: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//帮会id
			writeLong(_guildId);
			//帮会名
			writeString(_guildName);
			//帮会旗帜
			writeString(_guildBanner);
			//帮会国家
			writeInt(_guildCountry);
			//帮主id
			writeLong(_bangZhuid);
			//帮主名字
			writeString(_bangZhuName);
			//帮主等级
			writeShort(_bangZhuLevel);
			//帮主是否在线
			writeByte(_bangZhuOnline);
			//副帮主id
			writeLong(_viceBangZhuid);
			//副帮主名字
			writeString(_viceBangZhuName);
			//副帮主等级
			writeShort(_viceBangZhuLevel);
			//副帮主是否在线
			writeByte(_viceBangZhuOnline);
			//旗帜造型
			writeInt(_bannerIcon);
			//旗帜等级
			writeByte(_bannerLevel);
			//帮会公告
			writeString(_guildBulletin);
			//青龙令牌数
			writeInt(_dragon);
			//白虎令牌数
			writeInt(_whiteTiger);
			//朱雀令牌数
			writeInt(_suzaku);
			//玄武令牌数
			writeInt(_basaltic);
			//库存元宝
			writeLong(_stockGold);
			//友好帮会列表
			writeShort(_friendGuildList.length);
			for (var i: int = 0; i < _friendGuildList.length; i++) {
				writeBean(_friendGuildList[i]);
			}
			//敌对帮会列表
			writeShort(_enemyGuildList.length);
			for (var i: int = 0; i < _enemyGuildList.length; i++) {
				writeBean(_enemyGuildList[i]);
			}
			//今日活跃值
			writeByte(_activeValue);
			//解散警告值
			writeByte(_warningValue);
			//自动同意加入帮会的申请
			writeByte(_autoGuildAgreeAdd);
			//成员数量
			writeByte(_memberNum);
			//成员战斗力之和
			writeInt(_memberFightPower);
			//占领地图列表
			writeShort(_ownMapList.length);
			for (var i: int = 0; i < _ownMapList.length; i++) {
				writeInt(_ownMapList[i]);
			}
			//拥有王城
			writeByte(_ownKingCity);
			//拥有皇城
			writeByte(_ownEmperorCity);
			//帮会创建时间
			writeInt(_createTime);
			//帮会创建IP
			writeString(_createIp);
			//帮会公告最后更新时间
			writeInt(_lastGuildBulletinTime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//帮会id
			_guildId = readLong();
			//帮会名
			_guildName = readString();
			//帮会旗帜
			_guildBanner = readString();
			//帮会国家
			_guildCountry = readInt();
			//帮主id
			_bangZhuid = readLong();
			//帮主名字
			_bangZhuName = readString();
			//帮主等级
			_bangZhuLevel = readShort();
			//帮主是否在线
			_bangZhuOnline = readByte();
			//副帮主id
			_viceBangZhuid = readLong();
			//副帮主名字
			_viceBangZhuName = readString();
			//副帮主等级
			_viceBangZhuLevel = readShort();
			//副帮主是否在线
			_viceBangZhuOnline = readByte();
			//旗帜造型
			_bannerIcon = readInt();
			//旗帜等级
			_bannerLevel = readByte();
			//帮会公告
			_guildBulletin = readString();
			//青龙令牌数
			_dragon = readInt();
			//白虎令牌数
			_whiteTiger = readInt();
			//朱雀令牌数
			_suzaku = readInt();
			//玄武令牌数
			_basaltic = readInt();
			//库存元宝
			_stockGold = readLong();
			//友好帮会列表
			var friendGuildList_length : int = readShort();
			for (var i: int = 0; i < friendGuildList_length; i++) {
				_friendGuildList[i] = readBean(DiplomaticInfo) as DiplomaticInfo;
			}
			//敌对帮会列表
			var enemyGuildList_length : int = readShort();
			for (var i: int = 0; i < enemyGuildList_length; i++) {
				_enemyGuildList[i] = readBean(DiplomaticInfo) as DiplomaticInfo;
			}
			//今日活跃值
			_activeValue = readByte();
			//解散警告值
			_warningValue = readByte();
			//自动同意加入帮会的申请
			_autoGuildAgreeAdd = readByte();
			//成员数量
			_memberNum = readByte();
			//成员战斗力之和
			_memberFightPower = readInt();
			//占领地图列表
			var ownMapList_length : int = readShort();
			for (var i: int = 0; i < ownMapList_length; i++) {
				_ownMapList[i] = readInt();
			}
			//拥有王城
			_ownKingCity = readByte();
			//拥有皇城
			_ownEmperorCity = readByte();
			//帮会创建时间
			_createTime = readInt();
			//帮会创建IP
			_createIp = readString();
			//帮会公告最后更新时间
			_lastGuildBulletinTime = readInt();
			return true;
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
		 * get 帮会旗帜
		 * @return 
		 */
		public function get guildBanner(): String{
			return _guildBanner;
		}
		
		/**
		 * set 帮会旗帜
		 */
		public function set guildBanner(value: String): void{
			this._guildBanner = value;
		}
		
		/**
		 * get 帮会国家
		 * @return 
		 */
		public function get guildCountry(): int{
			return _guildCountry;
		}
		
		/**
		 * set 帮会国家
		 */
		public function set guildCountry(value: int): void{
			this._guildCountry = value;
		}
		
		/**
		 * get 帮主id
		 * @return 
		 */
		public function get bangZhuid(): long{
			return _bangZhuid;
		}
		
		/**
		 * set 帮主id
		 */
		public function set bangZhuid(value: long): void{
			this._bangZhuid = value;
		}
		
		/**
		 * get 帮主名字
		 * @return 
		 */
		public function get bangZhuName(): String{
			return _bangZhuName;
		}
		
		/**
		 * set 帮主名字
		 */
		public function set bangZhuName(value: String): void{
			this._bangZhuName = value;
		}
		
		/**
		 * get 帮主等级
		 * @return 
		 */
		public function get bangZhuLevel(): int{
			return _bangZhuLevel;
		}
		
		/**
		 * set 帮主等级
		 */
		public function set bangZhuLevel(value: int): void{
			this._bangZhuLevel = value;
		}
		
		/**
		 * get 帮主是否在线
		 * @return 
		 */
		public function get bangZhuOnline(): int{
			return _bangZhuOnline;
		}
		
		/**
		 * set 帮主是否在线
		 */
		public function set bangZhuOnline(value: int): void{
			this._bangZhuOnline = value;
		}
		
		/**
		 * get 副帮主id
		 * @return 
		 */
		public function get viceBangZhuid(): long{
			return _viceBangZhuid;
		}
		
		/**
		 * set 副帮主id
		 */
		public function set viceBangZhuid(value: long): void{
			this._viceBangZhuid = value;
		}
		
		/**
		 * get 副帮主名字
		 * @return 
		 */
		public function get viceBangZhuName(): String{
			return _viceBangZhuName;
		}
		
		/**
		 * set 副帮主名字
		 */
		public function set viceBangZhuName(value: String): void{
			this._viceBangZhuName = value;
		}
		
		/**
		 * get 副帮主等级
		 * @return 
		 */
		public function get viceBangZhuLevel(): int{
			return _viceBangZhuLevel;
		}
		
		/**
		 * set 副帮主等级
		 */
		public function set viceBangZhuLevel(value: int): void{
			this._viceBangZhuLevel = value;
		}
		
		/**
		 * get 副帮主是否在线
		 * @return 
		 */
		public function get viceBangZhuOnline(): int{
			return _viceBangZhuOnline;
		}
		
		/**
		 * set 副帮主是否在线
		 */
		public function set viceBangZhuOnline(value: int): void{
			this._viceBangZhuOnline = value;
		}
		
		/**
		 * get 旗帜造型
		 * @return 
		 */
		public function get bannerIcon(): int{
			return _bannerIcon;
		}
		
		/**
		 * set 旗帜造型
		 */
		public function set bannerIcon(value: int): void{
			this._bannerIcon = value;
		}
		
		/**
		 * get 旗帜等级
		 * @return 
		 */
		public function get bannerLevel(): int{
			return _bannerLevel;
		}
		
		/**
		 * set 旗帜等级
		 */
		public function set bannerLevel(value: int): void{
			this._bannerLevel = value;
		}
		
		/**
		 * get 帮会公告
		 * @return 
		 */
		public function get guildBulletin(): String{
			return _guildBulletin;
		}
		
		/**
		 * set 帮会公告
		 */
		public function set guildBulletin(value: String): void{
			this._guildBulletin = value;
		}
		
		/**
		 * get 青龙令牌数
		 * @return 
		 */
		public function get dragon(): int{
			return _dragon;
		}
		
		/**
		 * set 青龙令牌数
		 */
		public function set dragon(value: int): void{
			this._dragon = value;
		}
		
		/**
		 * get 白虎令牌数
		 * @return 
		 */
		public function get whiteTiger(): int{
			return _whiteTiger;
		}
		
		/**
		 * set 白虎令牌数
		 */
		public function set whiteTiger(value: int): void{
			this._whiteTiger = value;
		}
		
		/**
		 * get 朱雀令牌数
		 * @return 
		 */
		public function get suzaku(): int{
			return _suzaku;
		}
		
		/**
		 * set 朱雀令牌数
		 */
		public function set suzaku(value: int): void{
			this._suzaku = value;
		}
		
		/**
		 * get 玄武令牌数
		 * @return 
		 */
		public function get basaltic(): int{
			return _basaltic;
		}
		
		/**
		 * set 玄武令牌数
		 */
		public function set basaltic(value: int): void{
			this._basaltic = value;
		}
		
		/**
		 * get 库存元宝
		 * @return 
		 */
		public function get stockGold(): long{
			return _stockGold;
		}
		
		/**
		 * set 库存元宝
		 */
		public function set stockGold(value: long): void{
			this._stockGold = value;
		}
		
		/**
		 * get 友好帮会列表
		 * @return 
		 */
		public function get friendGuildList(): Vector.<DiplomaticInfo>{
			return _friendGuildList;
		}
		
		/**
		 * set 友好帮会列表
		 */
		public function set friendGuildList(value: Vector.<DiplomaticInfo>): void{
			this._friendGuildList = value;
		}
		
		/**
		 * get 敌对帮会列表
		 * @return 
		 */
		public function get enemyGuildList(): Vector.<DiplomaticInfo>{
			return _enemyGuildList;
		}
		
		/**
		 * set 敌对帮会列表
		 */
		public function set enemyGuildList(value: Vector.<DiplomaticInfo>): void{
			this._enemyGuildList = value;
		}
		
		/**
		 * get 今日活跃值
		 * @return 
		 */
		public function get activeValue(): int{
			return _activeValue;
		}
		
		/**
		 * set 今日活跃值
		 */
		public function set activeValue(value: int): void{
			this._activeValue = value;
		}
		
		/**
		 * get 解散警告值
		 * @return 
		 */
		public function get warningValue(): int{
			return _warningValue;
		}
		
		/**
		 * set 解散警告值
		 */
		public function set warningValue(value: int): void{
			this._warningValue = value;
		}
		
		/**
		 * get 自动同意加入帮会的申请
		 * @return 
		 */
		public function get autoGuildAgreeAdd(): int{
			return _autoGuildAgreeAdd;
		}
		
		/**
		 * set 自动同意加入帮会的申请
		 */
		public function set autoGuildAgreeAdd(value: int): void{
			this._autoGuildAgreeAdd = value;
		}
		
		/**
		 * get 成员数量
		 * @return 
		 */
		public function get memberNum(): int{
			return _memberNum;
		}
		
		/**
		 * set 成员数量
		 */
		public function set memberNum(value: int): void{
			this._memberNum = value;
		}
		
		/**
		 * get 成员战斗力之和
		 * @return 
		 */
		public function get memberFightPower(): int{
			return _memberFightPower;
		}
		
		/**
		 * set 成员战斗力之和
		 */
		public function set memberFightPower(value: int): void{
			this._memberFightPower = value;
		}
		
		/**
		 * get 占领地图列表
		 * @return 
		 */
		public function get ownMapList(): Vector.<int>{
			return _ownMapList;
		}
		
		/**
		 * set 占领地图列表
		 */
		public function set ownMapList(value: Vector.<int>): void{
			this._ownMapList = value;
		}
		
		/**
		 * get 拥有王城
		 * @return 
		 */
		public function get ownKingCity(): int{
			return _ownKingCity;
		}
		
		/**
		 * set 拥有王城
		 */
		public function set ownKingCity(value: int): void{
			this._ownKingCity = value;
		}
		
		/**
		 * get 拥有皇城
		 * @return 
		 */
		public function get ownEmperorCity(): int{
			return _ownEmperorCity;
		}
		
		/**
		 * set 拥有皇城
		 */
		public function set ownEmperorCity(value: int): void{
			this._ownEmperorCity = value;
		}
		
		/**
		 * get 帮会创建时间
		 * @return 
		 */
		public function get createTime(): int{
			return _createTime;
		}
		
		/**
		 * set 帮会创建时间
		 */
		public function set createTime(value: int): void{
			this._createTime = value;
		}
		
		/**
		 * get 帮会创建IP
		 * @return 
		 */
		public function get createIp(): String{
			return _createIp;
		}
		
		/**
		 * set 帮会创建IP
		 */
		public function set createIp(value: String): void{
			this._createIp = value;
		}
		
		/**
		 * get 帮会公告最后更新时间
		 * @return 
		 */
		public function get lastGuildBulletinTime(): int{
			return _lastGuildBulletinTime;
		}
		
		/**
		 * set 帮会公告最后更新时间
		 */
		public function set lastGuildBulletinTime(value: int): void{
			this._lastGuildBulletinTime = value;
		}
		
	}
}