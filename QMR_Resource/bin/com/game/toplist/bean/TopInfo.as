package com.game.toplist.bean{
	import com.game.skill.bean.SkillInfo;
	import com.game.hiddenweapon.bean.HiddenWeaponSkillInfo;
	import com.game.utils.long;
	import com.game.equip.bean.EquipInfo;
	import com.game.horseweapon.bean.HorseWeaponSkillInfo;
	import com.game.gem.bean.PosGemInfo;
	import com.game.player.bean.PlayerAttributeItem;
	import com.game.arrow.bean.ArrowInfo;
	import com.game.cloak.bean.CloakStoneInfo;
	import com.game.amulet.bean.AmuletSkillInfo;
	import com.game.guild.bean.GuildInfo;
	import com.game.horse.bean.HorseSkillInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 排行信息
	 */
	public class TopInfo extends Bean {
	
		//玩家id
		private var _playerid: long;
		
		//平台VIP
		private var _webvip: int;
		
		//玩家名
		private var _playername: String;
		
		//地图模板id
		private var _mapModelId: int;
		
		//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
		private var _toptype: int;
		
		//自己是否膜拜
		private var _worship: int;
		
		//自己今天膜拜次数
		private var _worshipnum: int;
		
		//总膜拜次数
		private var _allworshipnum: int;
		
		//名次
		private var _topidx: int;
		
		//等级
		private var _level: int;
		
		//性别
		private var _sex: int;
		
		//国家
		private var _country: int;
		
		//角色经验
		private var _exp: long;
		
		//角色真气
		private var _zhenqi: int;
		
		//头象ID
		private var _avatar: int;
		
		//当前章节
		private var _chapter: int;
		
		//王城BUFFid
		private var _kingcitybuffid: int;
		
		//VIPid
		private var _vipid: int;
		
		//战场声望
		private var _prestige: int;
		
		//游戏币
		private var _money: int;
		
		//技能总层数
		private var _totalSkillLevel: int;
		
		//墨子主动技能总层数
		private var _moziSkillLevel: int;
		
		//墨子被动技能总层数
		private var _mozibackSkillLevel: int;
		
		//龙元主动技能总层数
		private var _longyuanSkillLevel: int;
		
		//龙元被动技能总层数
		private var _longyuanbackSkillLevel: int;
		
		//龙元阶数
		private var _lysection: int;
		
		//龙元级数
		private var _lylevel: int;
		
		//战斗力
		private var _fightPower: int;
		
		//属性战斗力
		private var _attrfightPower: int;
		
		//装备战斗力
		private var _equipfightPower: int;
		
		//技能战斗力
		private var _skillfightPower: int;
		
		//侍宠模板id
		private var _petmodelId: int;
		
		//侍宠等级
		private var _petlevel: int;
		
		//合体次数
		private var _pethtcount: int;
		
		//合体增加生命
		private var _pethtaddhp: int;
		
		//合体增加内力
		private var _pethtaddmp: int;
		
		//合体增加攻击
		private var _pethtaddattack: int;
		
		//合体增加防御
		private var _pethtadddefence: int;
		
		//合体增加暴击
		private var _pethtaddcrit: int;
		
		//合体增加闪避
		private var _pethtadddodge: int;
		
		//侍宠技能 天赋技能排在第一个 不能动
		private var _petskillinfolist: Vector.<com.game.skill.bean.SkillInfo> = new Vector.<com.game.skill.bean.SkillInfo>();
		//历史最大连击数
		private var _maxEventcut: int;
		
		//历史最大连击数时间
		private var _maxEventcutTime: int;
		
		//连斩发生地图ID
		private var _evencutmapid: int;
		
		//最后连斩的怪物ID
		private var _evencutmonid: int;
		
		//连斩发生坐标X
		private var _evencutmapx: int;
		
		//连斩发生坐标Y
		private var _evencutmapy: int;
		
		//弓箭信息
		private var _arrowInfo: com.game.arrow.bean.ArrowInfo;
		
		//坐骑最高阶层
		private var _horselayer: int;
		
		//坐骑最高阶的当前等级
		private var _horselevel: int;
		
		//坐骑技能列表
		private var _skillinfolist: Vector.<com.game.horse.bean.HorseSkillInfo> = new Vector.<com.game.horse.bean.HorseSkillInfo>();
		//坐骑装备列表
		private var _horseequipinfo: Vector.<com.game.equip.bean.EquipInfo> = new Vector.<com.game.equip.bean.EquipInfo>();
		//属性列表
		private var _attributes: Vector.<com.game.player.bean.PlayerAttributeItem> = new Vector.<com.game.player.bean.PlayerAttributeItem>();
		//帮会信息
		private var _guildinfo: com.game.guild.bean.GuildInfo;
		
		//身上装备
		private var _itemlist: Vector.<com.game.equip.bean.EquipInfo> = new Vector.<com.game.equip.bean.EquipInfo>();
		//装备部位全部宝石信息
		private var _posallgeminfo: Vector.<com.game.gem.bean.PosGemInfo> = new Vector.<com.game.gem.bean.PosGemInfo>();
		//骑战兵器技能信息
		private var _horseWeaponSkillInfo: Vector.<com.game.horseweapon.bean.HorseWeaponSkillInfo> = new Vector.<com.game.horseweapon.bean.HorseWeaponSkillInfo>();
		//骑战兵器最高阶层
		private var _horseweaponlayer: int;
		
		//骑战兵器最高阶的当前等级
		private var _horseweaponlevel: int;
		
		//暗器技能信息
		private var _hiddenWeaponSkillInfo: Vector.<com.game.hiddenweapon.bean.HiddenWeaponSkillInfo> = new Vector.<com.game.hiddenweapon.bean.HiddenWeaponSkillInfo>();
		//暗器最高阶层
		private var _hiddenweaponlayer: int;
		
		//暗器最高阶的当前等级
		private var _hiddenweaponlevel: int;
		
		//境界等级
		private var _realmlevel: int;
		
		//境界强化等级
		private var _realmintensifylevel: int;
		
		//坐骑锻骨草使用数量
		private var _horseduangu: int;
		
		//配偶名字
		private var _spousename: String;
		
		//戒指模版ID
		private var _ringmodelid: int;
		
		//结婚时间
		private var _marriedtime: int;
		
		//护身符技能信息
		private var _amuletSkillInfo: Vector.<com.game.amulet.bean.AmuletSkillInfo> = new Vector.<com.game.amulet.bean.AmuletSkillInfo>();
		//护身符model
		private var _amuletModel: int;
		
		//披风石头信息
		private var _cloakStoneInfo: Vector.<com.game.cloak.bean.CloakStoneInfo> = new Vector.<com.game.cloak.bean.CloakStoneInfo>();
		//披风model
		private var _cloakModel: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家id
			writeLong(_playerid);
			//平台VIP
			writeInt(_webvip);
			//玩家名
			writeString(_playername);
			//地图模板id
			writeInt(_mapModelId);
			//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
			writeByte(_toptype);
			//自己是否膜拜
			writeByte(_worship);
			//自己今天膜拜次数
			writeByte(_worshipnum);
			//总膜拜次数
			writeInt(_allworshipnum);
			//名次
			writeInt(_topidx);
			//等级
			writeInt(_level);
			//性别
			writeByte(_sex);
			//国家
			writeInt(_country);
			//角色经验
			writeLong(_exp);
			//角色真气
			writeInt(_zhenqi);
			//头象ID
			writeInt(_avatar);
			//当前章节
			writeInt(_chapter);
			//王城BUFFid
			writeInt(_kingcitybuffid);
			//VIPid
			writeInt(_vipid);
			//战场声望
			writeInt(_prestige);
			//游戏币
			writeInt(_money);
			//技能总层数
			writeInt(_totalSkillLevel);
			//墨子主动技能总层数
			writeInt(_moziSkillLevel);
			//墨子被动技能总层数
			writeInt(_mozibackSkillLevel);
			//龙元主动技能总层数
			writeInt(_longyuanSkillLevel);
			//龙元被动技能总层数
			writeInt(_longyuanbackSkillLevel);
			//龙元阶数
			writeInt(_lysection);
			//龙元级数
			writeInt(_lylevel);
			//战斗力
			writeInt(_fightPower);
			//属性战斗力
			writeInt(_attrfightPower);
			//装备战斗力
			writeInt(_equipfightPower);
			//技能战斗力
			writeInt(_skillfightPower);
			//侍宠模板id
			writeInt(_petmodelId);
			//侍宠等级
			writeInt(_petlevel);
			//合体次数
			writeInt(_pethtcount);
			//合体增加生命
			writeInt(_pethtaddhp);
			//合体增加内力
			writeInt(_pethtaddmp);
			//合体增加攻击
			writeInt(_pethtaddattack);
			//合体增加防御
			writeInt(_pethtadddefence);
			//合体增加暴击
			writeInt(_pethtaddcrit);
			//合体增加闪避
			writeInt(_pethtadddodge);
			//侍宠技能 天赋技能排在第一个 不能动
			writeShort(_petskillinfolist.length);
			for (var i: int = 0; i < _petskillinfolist.length; i++) {
				writeBean(_petskillinfolist[i]);
			}
			//历史最大连击数
			writeInt(_maxEventcut);
			//历史最大连击数时间
			writeInt(_maxEventcutTime);
			//连斩发生地图ID
			writeInt(_evencutmapid);
			//最后连斩的怪物ID
			writeInt(_evencutmonid);
			//连斩发生坐标X
			writeInt(_evencutmapx);
			//连斩发生坐标Y
			writeInt(_evencutmapy);
			//弓箭信息
			writeBean(_arrowInfo);
			//坐骑最高阶层
			writeInt(_horselayer);
			//坐骑最高阶的当前等级
			writeInt(_horselevel);
			//坐骑技能列表
			writeShort(_skillinfolist.length);
			for (var i: int = 0; i < _skillinfolist.length; i++) {
				writeBean(_skillinfolist[i]);
			}
			//坐骑装备列表
			writeShort(_horseequipinfo.length);
			for (var i: int = 0; i < _horseequipinfo.length; i++) {
				writeBean(_horseequipinfo[i]);
			}
			//属性列表
			writeShort(_attributes.length);
			for (var i: int = 0; i < _attributes.length; i++) {
				writeBean(_attributes[i]);
			}
			//帮会信息
			writeBean(_guildinfo);
			//身上装备
			writeShort(_itemlist.length);
			for (var i: int = 0; i < _itemlist.length; i++) {
				writeBean(_itemlist[i]);
			}
			//装备部位全部宝石信息
			writeShort(_posallgeminfo.length);
			for (var i: int = 0; i < _posallgeminfo.length; i++) {
				writeBean(_posallgeminfo[i]);
			}
			//骑战兵器技能信息
			writeShort(_horseWeaponSkillInfo.length);
			for (var i: int = 0; i < _horseWeaponSkillInfo.length; i++) {
				writeBean(_horseWeaponSkillInfo[i]);
			}
			//骑战兵器最高阶层
			writeInt(_horseweaponlayer);
			//骑战兵器最高阶的当前等级
			writeInt(_horseweaponlevel);
			//暗器技能信息
			writeShort(_hiddenWeaponSkillInfo.length);
			for (var i: int = 0; i < _hiddenWeaponSkillInfo.length; i++) {
				writeBean(_hiddenWeaponSkillInfo[i]);
			}
			//暗器最高阶层
			writeInt(_hiddenweaponlayer);
			//暗器最高阶的当前等级
			writeInt(_hiddenweaponlevel);
			//境界等级
			writeInt(_realmlevel);
			//境界强化等级
			writeInt(_realmintensifylevel);
			//坐骑锻骨草使用数量
			writeShort(_horseduangu);
			//配偶名字
			writeString(_spousename);
			//戒指模版ID
			writeInt(_ringmodelid);
			//结婚时间
			writeInt(_marriedtime);
			//护身符技能信息
			writeShort(_amuletSkillInfo.length);
			for (var i: int = 0; i < _amuletSkillInfo.length; i++) {
				writeBean(_amuletSkillInfo[i]);
			}
			//护身符model
			writeInt(_amuletModel);
			//披风石头信息
			writeShort(_cloakStoneInfo.length);
			for (var i: int = 0; i < _cloakStoneInfo.length; i++) {
				writeBean(_cloakStoneInfo[i]);
			}
			//披风model
			writeInt(_cloakModel);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家id
			_playerid = readLong();
			//平台VIP
			_webvip = readInt();
			//玩家名
			_playername = readString();
			//地图模板id
			_mapModelId = readInt();
			//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
			_toptype = readByte();
			//自己是否膜拜
			_worship = readByte();
			//自己今天膜拜次数
			_worshipnum = readByte();
			//总膜拜次数
			_allworshipnum = readInt();
			//名次
			_topidx = readInt();
			//等级
			_level = readInt();
			//性别
			_sex = readByte();
			//国家
			_country = readInt();
			//角色经验
			_exp = readLong();
			//角色真气
			_zhenqi = readInt();
			//头象ID
			_avatar = readInt();
			//当前章节
			_chapter = readInt();
			//王城BUFFid
			_kingcitybuffid = readInt();
			//VIPid
			_vipid = readInt();
			//战场声望
			_prestige = readInt();
			//游戏币
			_money = readInt();
			//技能总层数
			_totalSkillLevel = readInt();
			//墨子主动技能总层数
			_moziSkillLevel = readInt();
			//墨子被动技能总层数
			_mozibackSkillLevel = readInt();
			//龙元主动技能总层数
			_longyuanSkillLevel = readInt();
			//龙元被动技能总层数
			_longyuanbackSkillLevel = readInt();
			//龙元阶数
			_lysection = readInt();
			//龙元级数
			_lylevel = readInt();
			//战斗力
			_fightPower = readInt();
			//属性战斗力
			_attrfightPower = readInt();
			//装备战斗力
			_equipfightPower = readInt();
			//技能战斗力
			_skillfightPower = readInt();
			//侍宠模板id
			_petmodelId = readInt();
			//侍宠等级
			_petlevel = readInt();
			//合体次数
			_pethtcount = readInt();
			//合体增加生命
			_pethtaddhp = readInt();
			//合体增加内力
			_pethtaddmp = readInt();
			//合体增加攻击
			_pethtaddattack = readInt();
			//合体增加防御
			_pethtadddefence = readInt();
			//合体增加暴击
			_pethtaddcrit = readInt();
			//合体增加闪避
			_pethtadddodge = readInt();
			//侍宠技能 天赋技能排在第一个 不能动
			var petskillinfolist_length : int = readShort();
			for (var i: int = 0; i < petskillinfolist_length; i++) {
				_petskillinfolist[i] = readBean(com.game.skill.bean.SkillInfo) as com.game.skill.bean.SkillInfo;
			}
			//历史最大连击数
			_maxEventcut = readInt();
			//历史最大连击数时间
			_maxEventcutTime = readInt();
			//连斩发生地图ID
			_evencutmapid = readInt();
			//最后连斩的怪物ID
			_evencutmonid = readInt();
			//连斩发生坐标X
			_evencutmapx = readInt();
			//连斩发生坐标Y
			_evencutmapy = readInt();
			//弓箭信息
			_arrowInfo = readBean(com.game.arrow.bean.ArrowInfo) as com.game.arrow.bean.ArrowInfo;
			//坐骑最高阶层
			_horselayer = readInt();
			//坐骑最高阶的当前等级
			_horselevel = readInt();
			//坐骑技能列表
			var skillinfolist_length : int = readShort();
			for (var i: int = 0; i < skillinfolist_length; i++) {
				_skillinfolist[i] = readBean(com.game.horse.bean.HorseSkillInfo) as com.game.horse.bean.HorseSkillInfo;
			}
			//坐骑装备列表
			var horseequipinfo_length : int = readShort();
			for (var i: int = 0; i < horseequipinfo_length; i++) {
				_horseequipinfo[i] = readBean(com.game.equip.bean.EquipInfo) as com.game.equip.bean.EquipInfo;
			}
			//属性列表
			var attributes_length : int = readShort();
			for (var i: int = 0; i < attributes_length; i++) {
				_attributes[i] = readBean(com.game.player.bean.PlayerAttributeItem) as com.game.player.bean.PlayerAttributeItem;
			}
			//帮会信息
			_guildinfo = readBean(com.game.guild.bean.GuildInfo) as com.game.guild.bean.GuildInfo;
			//身上装备
			var itemlist_length : int = readShort();
			for (var i: int = 0; i < itemlist_length; i++) {
				_itemlist[i] = readBean(com.game.equip.bean.EquipInfo) as com.game.equip.bean.EquipInfo;
			}
			//装备部位全部宝石信息
			var posallgeminfo_length : int = readShort();
			for (var i: int = 0; i < posallgeminfo_length; i++) {
				_posallgeminfo[i] = readBean(com.game.gem.bean.PosGemInfo) as com.game.gem.bean.PosGemInfo;
			}
			//骑战兵器技能信息
			var horseWeaponSkillInfo_length : int = readShort();
			for (var i: int = 0; i < horseWeaponSkillInfo_length; i++) {
				_horseWeaponSkillInfo[i] = readBean(com.game.horseweapon.bean.HorseWeaponSkillInfo) as com.game.horseweapon.bean.HorseWeaponSkillInfo;
			}
			//骑战兵器最高阶层
			_horseweaponlayer = readInt();
			//骑战兵器最高阶的当前等级
			_horseweaponlevel = readInt();
			//暗器技能信息
			var hiddenWeaponSkillInfo_length : int = readShort();
			for (var i: int = 0; i < hiddenWeaponSkillInfo_length; i++) {
				_hiddenWeaponSkillInfo[i] = readBean(com.game.hiddenweapon.bean.HiddenWeaponSkillInfo) as com.game.hiddenweapon.bean.HiddenWeaponSkillInfo;
			}
			//暗器最高阶层
			_hiddenweaponlayer = readInt();
			//暗器最高阶的当前等级
			_hiddenweaponlevel = readInt();
			//境界等级
			_realmlevel = readInt();
			//境界强化等级
			_realmintensifylevel = readInt();
			//坐骑锻骨草使用数量
			_horseduangu = readShort();
			//配偶名字
			_spousename = readString();
			//戒指模版ID
			_ringmodelid = readInt();
			//结婚时间
			_marriedtime = readInt();
			//护身符技能信息
			var amuletSkillInfo_length : int = readShort();
			for (var i: int = 0; i < amuletSkillInfo_length; i++) {
				_amuletSkillInfo[i] = readBean(com.game.amulet.bean.AmuletSkillInfo) as com.game.amulet.bean.AmuletSkillInfo;
			}
			//护身符model
			_amuletModel = readInt();
			//披风石头信息
			var cloakStoneInfo_length : int = readShort();
			for (var i: int = 0; i < cloakStoneInfo_length; i++) {
				_cloakStoneInfo[i] = readBean(com.game.cloak.bean.CloakStoneInfo) as com.game.cloak.bean.CloakStoneInfo;
			}
			//披风model
			_cloakModel = readInt();
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
		 * get 平台VIP
		 * @return 
		 */
		public function get webvip(): int{
			return _webvip;
		}
		
		/**
		 * set 平台VIP
		 */
		public function set webvip(value: int): void{
			this._webvip = value;
		}
		
		/**
		 * get 玩家名
		 * @return 
		 */
		public function get playername(): String{
			return _playername;
		}
		
		/**
		 * set 玩家名
		 */
		public function set playername(value: String): void{
			this._playername = value;
		}
		
		/**
		 * get 地图模板id
		 * @return 
		 */
		public function get mapModelId(): int{
			return _mapModelId;
		}
		
		/**
		 * set 地图模板id
		 */
		public function set mapModelId(value: int): void{
			this._mapModelId = value;
		}
		
		/**
		 * get 排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
		 * @return 
		 */
		public function get toptype(): int{
			return _toptype;
		}
		
		/**
		 * set 排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
		 */
		public function set toptype(value: int): void{
			this._toptype = value;
		}
		
		/**
		 * get 自己是否膜拜
		 * @return 
		 */
		public function get worship(): int{
			return _worship;
		}
		
		/**
		 * set 自己是否膜拜
		 */
		public function set worship(value: int): void{
			this._worship = value;
		}
		
		/**
		 * get 自己今天膜拜次数
		 * @return 
		 */
		public function get worshipnum(): int{
			return _worshipnum;
		}
		
		/**
		 * set 自己今天膜拜次数
		 */
		public function set worshipnum(value: int): void{
			this._worshipnum = value;
		}
		
		/**
		 * get 总膜拜次数
		 * @return 
		 */
		public function get allworshipnum(): int{
			return _allworshipnum;
		}
		
		/**
		 * set 总膜拜次数
		 */
		public function set allworshipnum(value: int): void{
			this._allworshipnum = value;
		}
		
		/**
		 * get 名次
		 * @return 
		 */
		public function get topidx(): int{
			return _topidx;
		}
		
		/**
		 * set 名次
		 */
		public function set topidx(value: int): void{
			this._topidx = value;
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
		 * get 性别
		 * @return 
		 */
		public function get sex(): int{
			return _sex;
		}
		
		/**
		 * set 性别
		 */
		public function set sex(value: int): void{
			this._sex = value;
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
		 * get 角色经验
		 * @return 
		 */
		public function get exp(): long{
			return _exp;
		}
		
		/**
		 * set 角色经验
		 */
		public function set exp(value: long): void{
			this._exp = value;
		}
		
		/**
		 * get 角色真气
		 * @return 
		 */
		public function get zhenqi(): int{
			return _zhenqi;
		}
		
		/**
		 * set 角色真气
		 */
		public function set zhenqi(value: int): void{
			this._zhenqi = value;
		}
		
		/**
		 * get 头象ID
		 * @return 
		 */
		public function get avatar(): int{
			return _avatar;
		}
		
		/**
		 * set 头象ID
		 */
		public function set avatar(value: int): void{
			this._avatar = value;
		}
		
		/**
		 * get 当前章节
		 * @return 
		 */
		public function get chapter(): int{
			return _chapter;
		}
		
		/**
		 * set 当前章节
		 */
		public function set chapter(value: int): void{
			this._chapter = value;
		}
		
		/**
		 * get 王城BUFFid
		 * @return 
		 */
		public function get kingcitybuffid(): int{
			return _kingcitybuffid;
		}
		
		/**
		 * set 王城BUFFid
		 */
		public function set kingcitybuffid(value: int): void{
			this._kingcitybuffid = value;
		}
		
		/**
		 * get VIPid
		 * @return 
		 */
		public function get vipid(): int{
			return _vipid;
		}
		
		/**
		 * set VIPid
		 */
		public function set vipid(value: int): void{
			this._vipid = value;
		}
		
		/**
		 * get 战场声望
		 * @return 
		 */
		public function get prestige(): int{
			return _prestige;
		}
		
		/**
		 * set 战场声望
		 */
		public function set prestige(value: int): void{
			this._prestige = value;
		}
		
		/**
		 * get 游戏币
		 * @return 
		 */
		public function get money(): int{
			return _money;
		}
		
		/**
		 * set 游戏币
		 */
		public function set money(value: int): void{
			this._money = value;
		}
		
		/**
		 * get 技能总层数
		 * @return 
		 */
		public function get totalSkillLevel(): int{
			return _totalSkillLevel;
		}
		
		/**
		 * set 技能总层数
		 */
		public function set totalSkillLevel(value: int): void{
			this._totalSkillLevel = value;
		}
		
		/**
		 * get 墨子主动技能总层数
		 * @return 
		 */
		public function get moziSkillLevel(): int{
			return _moziSkillLevel;
		}
		
		/**
		 * set 墨子主动技能总层数
		 */
		public function set moziSkillLevel(value: int): void{
			this._moziSkillLevel = value;
		}
		
		/**
		 * get 墨子被动技能总层数
		 * @return 
		 */
		public function get mozibackSkillLevel(): int{
			return _mozibackSkillLevel;
		}
		
		/**
		 * set 墨子被动技能总层数
		 */
		public function set mozibackSkillLevel(value: int): void{
			this._mozibackSkillLevel = value;
		}
		
		/**
		 * get 龙元主动技能总层数
		 * @return 
		 */
		public function get longyuanSkillLevel(): int{
			return _longyuanSkillLevel;
		}
		
		/**
		 * set 龙元主动技能总层数
		 */
		public function set longyuanSkillLevel(value: int): void{
			this._longyuanSkillLevel = value;
		}
		
		/**
		 * get 龙元被动技能总层数
		 * @return 
		 */
		public function get longyuanbackSkillLevel(): int{
			return _longyuanbackSkillLevel;
		}
		
		/**
		 * set 龙元被动技能总层数
		 */
		public function set longyuanbackSkillLevel(value: int): void{
			this._longyuanbackSkillLevel = value;
		}
		
		/**
		 * get 龙元阶数
		 * @return 
		 */
		public function get lysection(): int{
			return _lysection;
		}
		
		/**
		 * set 龙元阶数
		 */
		public function set lysection(value: int): void{
			this._lysection = value;
		}
		
		/**
		 * get 龙元级数
		 * @return 
		 */
		public function get lylevel(): int{
			return _lylevel;
		}
		
		/**
		 * set 龙元级数
		 */
		public function set lylevel(value: int): void{
			this._lylevel = value;
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
		 * get 属性战斗力
		 * @return 
		 */
		public function get attrfightPower(): int{
			return _attrfightPower;
		}
		
		/**
		 * set 属性战斗力
		 */
		public function set attrfightPower(value: int): void{
			this._attrfightPower = value;
		}
		
		/**
		 * get 装备战斗力
		 * @return 
		 */
		public function get equipfightPower(): int{
			return _equipfightPower;
		}
		
		/**
		 * set 装备战斗力
		 */
		public function set equipfightPower(value: int): void{
			this._equipfightPower = value;
		}
		
		/**
		 * get 技能战斗力
		 * @return 
		 */
		public function get skillfightPower(): int{
			return _skillfightPower;
		}
		
		/**
		 * set 技能战斗力
		 */
		public function set skillfightPower(value: int): void{
			this._skillfightPower = value;
		}
		
		/**
		 * get 侍宠模板id
		 * @return 
		 */
		public function get petmodelId(): int{
			return _petmodelId;
		}
		
		/**
		 * set 侍宠模板id
		 */
		public function set petmodelId(value: int): void{
			this._petmodelId = value;
		}
		
		/**
		 * get 侍宠等级
		 * @return 
		 */
		public function get petlevel(): int{
			return _petlevel;
		}
		
		/**
		 * set 侍宠等级
		 */
		public function set petlevel(value: int): void{
			this._petlevel = value;
		}
		
		/**
		 * get 合体次数
		 * @return 
		 */
		public function get pethtcount(): int{
			return _pethtcount;
		}
		
		/**
		 * set 合体次数
		 */
		public function set pethtcount(value: int): void{
			this._pethtcount = value;
		}
		
		/**
		 * get 合体增加生命
		 * @return 
		 */
		public function get pethtaddhp(): int{
			return _pethtaddhp;
		}
		
		/**
		 * set 合体增加生命
		 */
		public function set pethtaddhp(value: int): void{
			this._pethtaddhp = value;
		}
		
		/**
		 * get 合体增加内力
		 * @return 
		 */
		public function get pethtaddmp(): int{
			return _pethtaddmp;
		}
		
		/**
		 * set 合体增加内力
		 */
		public function set pethtaddmp(value: int): void{
			this._pethtaddmp = value;
		}
		
		/**
		 * get 合体增加攻击
		 * @return 
		 */
		public function get pethtaddattack(): int{
			return _pethtaddattack;
		}
		
		/**
		 * set 合体增加攻击
		 */
		public function set pethtaddattack(value: int): void{
			this._pethtaddattack = value;
		}
		
		/**
		 * get 合体增加防御
		 * @return 
		 */
		public function get pethtadddefence(): int{
			return _pethtadddefence;
		}
		
		/**
		 * set 合体增加防御
		 */
		public function set pethtadddefence(value: int): void{
			this._pethtadddefence = value;
		}
		
		/**
		 * get 合体增加暴击
		 * @return 
		 */
		public function get pethtaddcrit(): int{
			return _pethtaddcrit;
		}
		
		/**
		 * set 合体增加暴击
		 */
		public function set pethtaddcrit(value: int): void{
			this._pethtaddcrit = value;
		}
		
		/**
		 * get 合体增加闪避
		 * @return 
		 */
		public function get pethtadddodge(): int{
			return _pethtadddodge;
		}
		
		/**
		 * set 合体增加闪避
		 */
		public function set pethtadddodge(value: int): void{
			this._pethtadddodge = value;
		}
		
		/**
		 * get 侍宠技能 天赋技能排在第一个 不能动
		 * @return 
		 */
		public function get petskillinfolist(): Vector.<com.game.skill.bean.SkillInfo>{
			return _petskillinfolist;
		}
		
		/**
		 * set 侍宠技能 天赋技能排在第一个 不能动
		 */
		public function set petskillinfolist(value: Vector.<com.game.skill.bean.SkillInfo>): void{
			this._petskillinfolist = value;
		}
		
		/**
		 * get 历史最大连击数
		 * @return 
		 */
		public function get maxEventcut(): int{
			return _maxEventcut;
		}
		
		/**
		 * set 历史最大连击数
		 */
		public function set maxEventcut(value: int): void{
			this._maxEventcut = value;
		}
		
		/**
		 * get 历史最大连击数时间
		 * @return 
		 */
		public function get maxEventcutTime(): int{
			return _maxEventcutTime;
		}
		
		/**
		 * set 历史最大连击数时间
		 */
		public function set maxEventcutTime(value: int): void{
			this._maxEventcutTime = value;
		}
		
		/**
		 * get 连斩发生地图ID
		 * @return 
		 */
		public function get evencutmapid(): int{
			return _evencutmapid;
		}
		
		/**
		 * set 连斩发生地图ID
		 */
		public function set evencutmapid(value: int): void{
			this._evencutmapid = value;
		}
		
		/**
		 * get 最后连斩的怪物ID
		 * @return 
		 */
		public function get evencutmonid(): int{
			return _evencutmonid;
		}
		
		/**
		 * set 最后连斩的怪物ID
		 */
		public function set evencutmonid(value: int): void{
			this._evencutmonid = value;
		}
		
		/**
		 * get 连斩发生坐标X
		 * @return 
		 */
		public function get evencutmapx(): int{
			return _evencutmapx;
		}
		
		/**
		 * set 连斩发生坐标X
		 */
		public function set evencutmapx(value: int): void{
			this._evencutmapx = value;
		}
		
		/**
		 * get 连斩发生坐标Y
		 * @return 
		 */
		public function get evencutmapy(): int{
			return _evencutmapy;
		}
		
		/**
		 * set 连斩发生坐标Y
		 */
		public function set evencutmapy(value: int): void{
			this._evencutmapy = value;
		}
		
		/**
		 * get 弓箭信息
		 * @return 
		 */
		public function get arrowInfo(): com.game.arrow.bean.ArrowInfo{
			return _arrowInfo;
		}
		
		/**
		 * set 弓箭信息
		 */
		public function set arrowInfo(value: com.game.arrow.bean.ArrowInfo): void{
			this._arrowInfo = value;
		}
		
		/**
		 * get 坐骑最高阶层
		 * @return 
		 */
		public function get horselayer(): int{
			return _horselayer;
		}
		
		/**
		 * set 坐骑最高阶层
		 */
		public function set horselayer(value: int): void{
			this._horselayer = value;
		}
		
		/**
		 * get 坐骑最高阶的当前等级
		 * @return 
		 */
		public function get horselevel(): int{
			return _horselevel;
		}
		
		/**
		 * set 坐骑最高阶的当前等级
		 */
		public function set horselevel(value: int): void{
			this._horselevel = value;
		}
		
		/**
		 * get 坐骑技能列表
		 * @return 
		 */
		public function get skillinfolist(): Vector.<com.game.horse.bean.HorseSkillInfo>{
			return _skillinfolist;
		}
		
		/**
		 * set 坐骑技能列表
		 */
		public function set skillinfolist(value: Vector.<com.game.horse.bean.HorseSkillInfo>): void{
			this._skillinfolist = value;
		}
		
		/**
		 * get 坐骑装备列表
		 * @return 
		 */
		public function get horseequipinfo(): Vector.<com.game.equip.bean.EquipInfo>{
			return _horseequipinfo;
		}
		
		/**
		 * set 坐骑装备列表
		 */
		public function set horseequipinfo(value: Vector.<com.game.equip.bean.EquipInfo>): void{
			this._horseequipinfo = value;
		}
		
		/**
		 * get 属性列表
		 * @return 
		 */
		public function get attributes(): Vector.<com.game.player.bean.PlayerAttributeItem>{
			return _attributes;
		}
		
		/**
		 * set 属性列表
		 */
		public function set attributes(value: Vector.<com.game.player.bean.PlayerAttributeItem>): void{
			this._attributes = value;
		}
		
		/**
		 * get 帮会信息
		 * @return 
		 */
		public function get guildinfo(): com.game.guild.bean.GuildInfo{
			return _guildinfo;
		}
		
		/**
		 * set 帮会信息
		 */
		public function set guildinfo(value: com.game.guild.bean.GuildInfo): void{
			this._guildinfo = value;
		}
		
		/**
		 * get 身上装备
		 * @return 
		 */
		public function get itemlist(): Vector.<com.game.equip.bean.EquipInfo>{
			return _itemlist;
		}
		
		/**
		 * set 身上装备
		 */
		public function set itemlist(value: Vector.<com.game.equip.bean.EquipInfo>): void{
			this._itemlist = value;
		}
		
		/**
		 * get 装备部位全部宝石信息
		 * @return 
		 */
		public function get posallgeminfo(): Vector.<com.game.gem.bean.PosGemInfo>{
			return _posallgeminfo;
		}
		
		/**
		 * set 装备部位全部宝石信息
		 */
		public function set posallgeminfo(value: Vector.<com.game.gem.bean.PosGemInfo>): void{
			this._posallgeminfo = value;
		}
		
		/**
		 * get 骑战兵器技能信息
		 * @return 
		 */
		public function get horseWeaponSkillInfo(): Vector.<com.game.horseweapon.bean.HorseWeaponSkillInfo>{
			return _horseWeaponSkillInfo;
		}
		
		/**
		 * set 骑战兵器技能信息
		 */
		public function set horseWeaponSkillInfo(value: Vector.<com.game.horseweapon.bean.HorseWeaponSkillInfo>): void{
			this._horseWeaponSkillInfo = value;
		}
		
		/**
		 * get 骑战兵器最高阶层
		 * @return 
		 */
		public function get horseweaponlayer(): int{
			return _horseweaponlayer;
		}
		
		/**
		 * set 骑战兵器最高阶层
		 */
		public function set horseweaponlayer(value: int): void{
			this._horseweaponlayer = value;
		}
		
		/**
		 * get 骑战兵器最高阶的当前等级
		 * @return 
		 */
		public function get horseweaponlevel(): int{
			return _horseweaponlevel;
		}
		
		/**
		 * set 骑战兵器最高阶的当前等级
		 */
		public function set horseweaponlevel(value: int): void{
			this._horseweaponlevel = value;
		}
		
		/**
		 * get 暗器技能信息
		 * @return 
		 */
		public function get hiddenWeaponSkillInfo(): Vector.<com.game.hiddenweapon.bean.HiddenWeaponSkillInfo>{
			return _hiddenWeaponSkillInfo;
		}
		
		/**
		 * set 暗器技能信息
		 */
		public function set hiddenWeaponSkillInfo(value: Vector.<com.game.hiddenweapon.bean.HiddenWeaponSkillInfo>): void{
			this._hiddenWeaponSkillInfo = value;
		}
		
		/**
		 * get 暗器最高阶层
		 * @return 
		 */
		public function get hiddenweaponlayer(): int{
			return _hiddenweaponlayer;
		}
		
		/**
		 * set 暗器最高阶层
		 */
		public function set hiddenweaponlayer(value: int): void{
			this._hiddenweaponlayer = value;
		}
		
		/**
		 * get 暗器最高阶的当前等级
		 * @return 
		 */
		public function get hiddenweaponlevel(): int{
			return _hiddenweaponlevel;
		}
		
		/**
		 * set 暗器最高阶的当前等级
		 */
		public function set hiddenweaponlevel(value: int): void{
			this._hiddenweaponlevel = value;
		}
		
		/**
		 * get 境界等级
		 * @return 
		 */
		public function get realmlevel(): int{
			return _realmlevel;
		}
		
		/**
		 * set 境界等级
		 */
		public function set realmlevel(value: int): void{
			this._realmlevel = value;
		}
		
		/**
		 * get 境界强化等级
		 * @return 
		 */
		public function get realmintensifylevel(): int{
			return _realmintensifylevel;
		}
		
		/**
		 * set 境界强化等级
		 */
		public function set realmintensifylevel(value: int): void{
			this._realmintensifylevel = value;
		}
		
		/**
		 * get 坐骑锻骨草使用数量
		 * @return 
		 */
		public function get horseduangu(): int{
			return _horseduangu;
		}
		
		/**
		 * set 坐骑锻骨草使用数量
		 */
		public function set horseduangu(value: int): void{
			this._horseduangu = value;
		}
		
		/**
		 * get 配偶名字
		 * @return 
		 */
		public function get spousename(): String{
			return _spousename;
		}
		
		/**
		 * set 配偶名字
		 */
		public function set spousename(value: String): void{
			this._spousename = value;
		}
		
		/**
		 * get 戒指模版ID
		 * @return 
		 */
		public function get ringmodelid(): int{
			return _ringmodelid;
		}
		
		/**
		 * set 戒指模版ID
		 */
		public function set ringmodelid(value: int): void{
			this._ringmodelid = value;
		}
		
		/**
		 * get 结婚时间
		 * @return 
		 */
		public function get marriedtime(): int{
			return _marriedtime;
		}
		
		/**
		 * set 结婚时间
		 */
		public function set marriedtime(value: int): void{
			this._marriedtime = value;
		}
		
		/**
		 * get 护身符技能信息
		 * @return 
		 */
		public function get amuletSkillInfo(): Vector.<com.game.amulet.bean.AmuletSkillInfo>{
			return _amuletSkillInfo;
		}
		
		/**
		 * set 护身符技能信息
		 */
		public function set amuletSkillInfo(value: Vector.<com.game.amulet.bean.AmuletSkillInfo>): void{
			this._amuletSkillInfo = value;
		}
		
		/**
		 * get 护身符model
		 * @return 
		 */
		public function get amuletModel(): int{
			return _amuletModel;
		}
		
		/**
		 * set 护身符model
		 */
		public function set amuletModel(value: int): void{
			this._amuletModel = value;
		}
		
		/**
		 * get 披风石头信息
		 * @return 
		 */
		public function get cloakStoneInfo(): Vector.<com.game.cloak.bean.CloakStoneInfo>{
			return _cloakStoneInfo;
		}
		
		/**
		 * set 披风石头信息
		 */
		public function set cloakStoneInfo(value: Vector.<com.game.cloak.bean.CloakStoneInfo>): void{
			this._cloakStoneInfo = value;
		}
		
		/**
		 * get 披风model
		 * @return 
		 */
		public function get cloakModel(): int{
			return _cloakModel;
		}
		
		/**
		 * set 披风model
		 */
		public function set cloakModel(value: int): void{
			this._cloakModel = value;
		}
		
	}
}