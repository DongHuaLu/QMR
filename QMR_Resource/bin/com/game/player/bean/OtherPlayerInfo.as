package com.game.player.bean{
	import com.game.amulet.bean.AmuletInfo;
	import com.game.utils.long;
	import com.game.equip.bean.EquipInfo;
	import com.game.gem.bean.PosGemInfo;
	import com.game.guild.bean.OtherGuildInfo;
	import com.game.player.bean.PlayerAttributeItem;
	import com.game.arrow.bean.ArrowInfo;
	import com.game.cloak.bean.CloakInfo;
	import com.game.horseweapon.bean.OthersHorseWeaponInfo;
	import com.game.hiddenweapon.bean.OthersHiddenWeaponInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 他人信息
	 */
	public class OtherPlayerInfo extends Bean {
	
		//角色Id
		private var _personId: long;
		
		//国家
		private var _country: int;
		
		//角色名字
		private var _name: String;
		
		//角色性别 1-男 2-女
		private var _sex: int;
		
		//角色等级
		private var _level: int;
		
		//角色经验
		private var _exp: long;
		
		//角色真气
		private var _zhenqi: int;
		
		//角色战场声望
		private var _prestige: int;
		
		//头象ID
		private var _avatar: int;
		
		//当前章节
		private var _chapter: int;
		
		//属性列表
		private var _attributes: Vector.<PlayerAttributeItem> = new Vector.<PlayerAttributeItem>();
		//装备列表信息
		private var _equips: Vector.<com.game.equip.bean.EquipInfo> = new Vector.<com.game.equip.bean.EquipInfo>();
		//角色武功境界
		private var _skill: int;
		
		//角色武功境界层数
		private var _skills: int;
		
		//他人公会信息
		private var _otherGuildInfo: com.game.guild.bean.OtherGuildInfo;
		
		//战斗力
		private var _fightpower: int;
		
		//装备部位全部宝石信息
		private var _posallgeminfo: Vector.<com.game.gem.bean.PosGemInfo> = new Vector.<com.game.gem.bean.PosGemInfo>();
		//王城BUFFid
		private var _kingcitybuffid: int;
		
		//VIPid
		private var _vipid: int;
		
		//军衔等级
		private var _ranklevel: int;
		
		//弓箭信息
		private var _arrowInfo: com.game.arrow.bean.ArrowInfo;
		
		//骑战兵器信息
		private var _horseWeaponInfo: com.game.horseweapon.bean.OthersHorseWeaponInfo;
		
		//暗器信息
		private var _hiddenWeaponInfo: com.game.hiddenweapon.bean.OthersHiddenWeaponInfo;
		
		//境界等级
		private var _realmlevel: int;
		
		//境界强化等级
		private var _realmintensifylevel: int;
		
		//配偶名字
		private var _spousename: String;
		
		//戒指模版ID
		private var _ringmodelid: int;
		
		//结婚时间
		private var _marriedtime: int;
		
		//护身符信息
		private var _amuletInfo: com.game.amulet.bean.AmuletInfo;
		
		//平台标识
		private var _webname: String;
		
		//披风信息
		private var _cloakInfo: com.game.cloak.bean.CloakInfo;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色Id
			writeLong(_personId);
			//国家
			writeInt(_country);
			//角色名字
			writeString(_name);
			//角色性别 1-男 2-女
			writeInt(_sex);
			//角色等级
			writeInt(_level);
			//角色经验
			writeLong(_exp);
			//角色真气
			writeInt(_zhenqi);
			//角色战场声望
			writeInt(_prestige);
			//头象ID
			writeInt(_avatar);
			//当前章节
			writeInt(_chapter);
			//属性列表
			writeShort(_attributes.length);
			for (var i: int = 0; i < _attributes.length; i++) {
				writeBean(_attributes[i]);
			}
			//装备列表信息
			writeShort(_equips.length);
			for (var i: int = 0; i < _equips.length; i++) {
				writeBean(_equips[i]);
			}
			//角色武功境界
			writeByte(_skill);
			//角色武功境界层数
			writeInt(_skills);
			//他人公会信息
			writeBean(_otherGuildInfo);
			//战斗力
			writeInt(_fightpower);
			//装备部位全部宝石信息
			writeShort(_posallgeminfo.length);
			for (var i: int = 0; i < _posallgeminfo.length; i++) {
				writeBean(_posallgeminfo[i]);
			}
			//王城BUFFid
			writeInt(_kingcitybuffid);
			//VIPid
			writeInt(_vipid);
			//军衔等级
			writeByte(_ranklevel);
			//弓箭信息
			writeBean(_arrowInfo);
			//骑战兵器信息
			writeBean(_horseWeaponInfo);
			//暗器信息
			writeBean(_hiddenWeaponInfo);
			//境界等级
			writeInt(_realmlevel);
			//境界强化等级
			writeInt(_realmintensifylevel);
			//配偶名字
			writeString(_spousename);
			//戒指模版ID
			writeInt(_ringmodelid);
			//结婚时间
			writeInt(_marriedtime);
			//护身符信息
			writeBean(_amuletInfo);
			//平台标识
			writeString(_webname);
			//披风信息
			writeBean(_cloakInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色Id
			_personId = readLong();
			//国家
			_country = readInt();
			//角色名字
			_name = readString();
			//角色性别 1-男 2-女
			_sex = readInt();
			//角色等级
			_level = readInt();
			//角色经验
			_exp = readLong();
			//角色真气
			_zhenqi = readInt();
			//角色战场声望
			_prestige = readInt();
			//头象ID
			_avatar = readInt();
			//当前章节
			_chapter = readInt();
			//属性列表
			var attributes_length : int = readShort();
			for (var i: int = 0; i < attributes_length; i++) {
				_attributes[i] = readBean(PlayerAttributeItem) as PlayerAttributeItem;
			}
			//装备列表信息
			var equips_length : int = readShort();
			for (var i: int = 0; i < equips_length; i++) {
				_equips[i] = readBean(com.game.equip.bean.EquipInfo) as com.game.equip.bean.EquipInfo;
			}
			//角色武功境界
			_skill = readByte();
			//角色武功境界层数
			_skills = readInt();
			//他人公会信息
			_otherGuildInfo = readBean(com.game.guild.bean.OtherGuildInfo) as com.game.guild.bean.OtherGuildInfo;
			//战斗力
			_fightpower = readInt();
			//装备部位全部宝石信息
			var posallgeminfo_length : int = readShort();
			for (var i: int = 0; i < posallgeminfo_length; i++) {
				_posallgeminfo[i] = readBean(com.game.gem.bean.PosGemInfo) as com.game.gem.bean.PosGemInfo;
			}
			//王城BUFFid
			_kingcitybuffid = readInt();
			//VIPid
			_vipid = readInt();
			//军衔等级
			_ranklevel = readByte();
			//弓箭信息
			_arrowInfo = readBean(com.game.arrow.bean.ArrowInfo) as com.game.arrow.bean.ArrowInfo;
			//骑战兵器信息
			_horseWeaponInfo = readBean(com.game.horseweapon.bean.OthersHorseWeaponInfo) as com.game.horseweapon.bean.OthersHorseWeaponInfo;
			//暗器信息
			_hiddenWeaponInfo = readBean(com.game.hiddenweapon.bean.OthersHiddenWeaponInfo) as com.game.hiddenweapon.bean.OthersHiddenWeaponInfo;
			//境界等级
			_realmlevel = readInt();
			//境界强化等级
			_realmintensifylevel = readInt();
			//配偶名字
			_spousename = readString();
			//戒指模版ID
			_ringmodelid = readInt();
			//结婚时间
			_marriedtime = readInt();
			//护身符信息
			_amuletInfo = readBean(com.game.amulet.bean.AmuletInfo) as com.game.amulet.bean.AmuletInfo;
			//平台标识
			_webname = readString();
			//披风信息
			_cloakInfo = readBean(com.game.cloak.bean.CloakInfo) as com.game.cloak.bean.CloakInfo;
			return true;
		}
		
		/**
		 * get 角色Id
		 * @return 
		 */
		public function get personId(): long{
			return _personId;
		}
		
		/**
		 * set 角色Id
		 */
		public function set personId(value: long): void{
			this._personId = value;
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
		 * get 角色名字
		 * @return 
		 */
		public function get name(): String{
			return _name;
		}
		
		/**
		 * set 角色名字
		 */
		public function set name(value: String): void{
			this._name = value;
		}
		
		/**
		 * get 角色性别 1-男 2-女
		 * @return 
		 */
		public function get sex(): int{
			return _sex;
		}
		
		/**
		 * set 角色性别 1-男 2-女
		 */
		public function set sex(value: int): void{
			this._sex = value;
		}
		
		/**
		 * get 角色等级
		 * @return 
		 */
		public function get level(): int{
			return _level;
		}
		
		/**
		 * set 角色等级
		 */
		public function set level(value: int): void{
			this._level = value;
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
		 * get 角色战场声望
		 * @return 
		 */
		public function get prestige(): int{
			return _prestige;
		}
		
		/**
		 * set 角色战场声望
		 */
		public function set prestige(value: int): void{
			this._prestige = value;
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
		 * get 属性列表
		 * @return 
		 */
		public function get attributes(): Vector.<PlayerAttributeItem>{
			return _attributes;
		}
		
		/**
		 * set 属性列表
		 */
		public function set attributes(value: Vector.<PlayerAttributeItem>): void{
			this._attributes = value;
		}
		
		/**
		 * get 装备列表信息
		 * @return 
		 */
		public function get equips(): Vector.<com.game.equip.bean.EquipInfo>{
			return _equips;
		}
		
		/**
		 * set 装备列表信息
		 */
		public function set equips(value: Vector.<com.game.equip.bean.EquipInfo>): void{
			this._equips = value;
		}
		
		/**
		 * get 角色武功境界
		 * @return 
		 */
		public function get skill(): int{
			return _skill;
		}
		
		/**
		 * set 角色武功境界
		 */
		public function set skill(value: int): void{
			this._skill = value;
		}
		
		/**
		 * get 角色武功境界层数
		 * @return 
		 */
		public function get skills(): int{
			return _skills;
		}
		
		/**
		 * set 角色武功境界层数
		 */
		public function set skills(value: int): void{
			this._skills = value;
		}
		
		/**
		 * get 他人公会信息
		 * @return 
		 */
		public function get otherGuildInfo(): com.game.guild.bean.OtherGuildInfo{
			return _otherGuildInfo;
		}
		
		/**
		 * set 他人公会信息
		 */
		public function set otherGuildInfo(value: com.game.guild.bean.OtherGuildInfo): void{
			this._otherGuildInfo = value;
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
		 * get 军衔等级
		 * @return 
		 */
		public function get ranklevel(): int{
			return _ranklevel;
		}
		
		/**
		 * set 军衔等级
		 */
		public function set ranklevel(value: int): void{
			this._ranklevel = value;
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
		 * get 骑战兵器信息
		 * @return 
		 */
		public function get horseWeaponInfo(): com.game.horseweapon.bean.OthersHorseWeaponInfo{
			return _horseWeaponInfo;
		}
		
		/**
		 * set 骑战兵器信息
		 */
		public function set horseWeaponInfo(value: com.game.horseweapon.bean.OthersHorseWeaponInfo): void{
			this._horseWeaponInfo = value;
		}
		
		/**
		 * get 暗器信息
		 * @return 
		 */
		public function get hiddenWeaponInfo(): com.game.hiddenweapon.bean.OthersHiddenWeaponInfo{
			return _hiddenWeaponInfo;
		}
		
		/**
		 * set 暗器信息
		 */
		public function set hiddenWeaponInfo(value: com.game.hiddenweapon.bean.OthersHiddenWeaponInfo): void{
			this._hiddenWeaponInfo = value;
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
		 * get 护身符信息
		 * @return 
		 */
		public function get amuletInfo(): com.game.amulet.bean.AmuletInfo{
			return _amuletInfo;
		}
		
		/**
		 * set 护身符信息
		 */
		public function set amuletInfo(value: com.game.amulet.bean.AmuletInfo): void{
			this._amuletInfo = value;
		}
		
		/**
		 * get 平台标识
		 * @return 
		 */
		public function get webname(): String{
			return _webname;
		}
		
		/**
		 * set 平台标识
		 */
		public function set webname(value: String): void{
			this._webname = value;
		}
		
		/**
		 * get 披风信息
		 * @return 
		 */
		public function get cloakInfo(): com.game.cloak.bean.CloakInfo{
			return _cloakInfo;
		}
		
		/**
		 * set 披风信息
		 */
		public function set cloakInfo(value: com.game.cloak.bean.CloakInfo): void{
			this._cloakInfo = value;
		}
		
	}
}