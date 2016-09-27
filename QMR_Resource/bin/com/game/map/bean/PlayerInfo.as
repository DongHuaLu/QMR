package com.game.map.bean{
	import com.game.utils.long;
	import com.game.buff.bean.BuffInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 角色信息类
	 */
	public class PlayerInfo extends Bean {
	
		//角色Id
		private var _personId: long;
		
		//国家
		private var _country: int;
		
		//平台VIP
		private var _webvip: int;
		
		//角色名字
		private var _name: String;
		
		//角色性别 1-男 2-女
		private var _sex: int;
		
		//角色等级
		private var _level: int;
		
		//角色所在地图
		private var _mapId: int;
		
		//角色所在X
		private var _x: int;
		
		//角色所在Y
		private var _y: int;
		
		//角色HP
		private var _hp: int;
		
		//角色最大HP
		private var _maxHp: int;
		
		//角色MP
		private var _mp: int;
		
		//角色最大MP
		private var _maxMp: int;
		
		//角色SP
		private var _sp: int;
		
		//角色最大SP
		private var _maxSp: int;
		
		//角色速度
		private var _speed: int;
		
		//角色状态
		private var _state: int;
		
		//武器模板id
		private var _weapon: int;
		
		//武器模板强化等级
		private var _weaponStreng: int;
		
		//衣服模板id
		private var _armor: int;
		
		//头像模板id
		private var _avatar: int;
		
		//人物面对方向
		private var _dir: int;
		
		//队伍ID
		private var _team: long;
		
		//帮派ID
		private var _guild: long;
		
		//跑步坐标集合
		private var _positions: Vector.<int> = new Vector.<int>();
		//buff集合
		private var _buffs: Vector.<com.game.buff.bean.BuffInfo> = new Vector.<com.game.buff.bean.BuffInfo>();
		//当前坐骑阶数
		private var _horseid: int;
		
		//当前骑战兵器阶数
		private var _horseweaponid: int;
		
		//打坐状态 0 未打座 1单人打座 2与待宠双修 3与玩家双修
		private var _dazuoState: int;
		
		//参与双修的宠物唯一ID
		private var _sxpets: Vector.<long> = new Vector.<long>();
		//双修的玩家ID
		private var _sxroleid: long;
		
		//双修玩家对方的坐标X
		private var _sxtargetx: int;
		
		//双修玩家对方的坐标Y
		private var _sxtargety: int;
		
		//军衔等级
		private var _ranklevel: int;
		
		//弓箭ID
		private var _arrowid: int;
		
		//境界等级
		private var _realmlevel: int;
		
		//当前暗器阶数
		private var _hiddenweaponid: int;
		
		//坐骑锻骨草使用数量
		private var _horseduangu: int;
		
		//当前护身符model
		private var _amuletModel: int;
		
		//配偶护身符model
		private var _spouseAmuletModel: int;
		
		//平台标识
		private var _webname: String;
		
		//披风model
		private var _cloakModel: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色Id
			writeLong(_personId);
			//国家
			writeInt(_country);
			//平台VIP
			writeInt(_webvip);
			//角色名字
			writeString(_name);
			//角色性别 1-男 2-女
			writeByte(_sex);
			//角色等级
			writeInt(_level);
			//角色所在地图
			writeInt(_mapId);
			//角色所在X
			writeShort(_x);
			//角色所在Y
			writeShort(_y);
			//角色HP
			writeInt(_hp);
			//角色最大HP
			writeInt(_maxHp);
			//角色MP
			writeInt(_mp);
			//角色最大MP
			writeInt(_maxMp);
			//角色SP
			writeInt(_sp);
			//角色最大SP
			writeInt(_maxSp);
			//角色速度
			writeInt(_speed);
			//角色状态
			writeInt(_state);
			//武器模板id
			writeInt(_weapon);
			//武器模板强化等级
			writeByte(_weaponStreng);
			//衣服模板id
			writeInt(_armor);
			//头像模板id
			writeInt(_avatar);
			//人物面对方向
			writeByte(_dir);
			//队伍ID
			writeLong(_team);
			//帮派ID
			writeLong(_guild);
			//跑步坐标集合
			writeShort(_positions.length);
			for (var i: int = 0; i < _positions.length; i++) {
				writeByte(_positions[i]);
			}
			//buff集合
			writeShort(_buffs.length);
			for (var i: int = 0; i < _buffs.length; i++) {
				writeBean(_buffs[i]);
			}
			//当前坐骑阶数
			writeShort(_horseid);
			//当前骑战兵器阶数
			writeShort(_horseweaponid);
			//打坐状态 0 未打座 1单人打座 2与待宠双修 3与玩家双修
			writeByte(_dazuoState);
			//参与双修的宠物唯一ID
			writeShort(_sxpets.length);
			for (var i: int = 0; i < _sxpets.length; i++) {
				writeLong(_sxpets[i]);
			}
			//双修的玩家ID
			writeLong(_sxroleid);
			//双修玩家对方的坐标X
			writeShort(_sxtargetx);
			//双修玩家对方的坐标Y
			writeShort(_sxtargety);
			//军衔等级
			writeByte(_ranklevel);
			//弓箭ID
			writeInt(_arrowid);
			//境界等级
			writeInt(_realmlevel);
			//当前暗器阶数
			writeShort(_hiddenweaponid);
			//坐骑锻骨草使用数量
			writeShort(_horseduangu);
			//当前护身符model
			writeInt(_amuletModel);
			//配偶护身符model
			writeInt(_spouseAmuletModel);
			//平台标识
			writeString(_webname);
			//披风model
			writeInt(_cloakModel);
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
			//平台VIP
			_webvip = readInt();
			//角色名字
			_name = readString();
			//角色性别 1-男 2-女
			_sex = readByte();
			//角色等级
			_level = readInt();
			//角色所在地图
			_mapId = readInt();
			//角色所在X
			_x = readShort();
			//角色所在Y
			_y = readShort();
			//角色HP
			_hp = readInt();
			//角色最大HP
			_maxHp = readInt();
			//角色MP
			_mp = readInt();
			//角色最大MP
			_maxMp = readInt();
			//角色SP
			_sp = readInt();
			//角色最大SP
			_maxSp = readInt();
			//角色速度
			_speed = readInt();
			//角色状态
			_state = readInt();
			//武器模板id
			_weapon = readInt();
			//武器模板强化等级
			_weaponStreng = readByte();
			//衣服模板id
			_armor = readInt();
			//头像模板id
			_avatar = readInt();
			//人物面对方向
			_dir = readByte();
			//队伍ID
			_team = readLong();
			//帮派ID
			_guild = readLong();
			//跑步坐标集合
			var positions_length : int = readShort();
			for (var i: int = 0; i < positions_length; i++) {
				_positions[i] = readByte();
			}
			//buff集合
			var buffs_length : int = readShort();
			for (var i: int = 0; i < buffs_length; i++) {
				_buffs[i] = readBean(com.game.buff.bean.BuffInfo) as com.game.buff.bean.BuffInfo;
			}
			//当前坐骑阶数
			_horseid = readShort();
			//当前骑战兵器阶数
			_horseweaponid = readShort();
			//打坐状态 0 未打座 1单人打座 2与待宠双修 3与玩家双修
			_dazuoState = readByte();
			//参与双修的宠物唯一ID
			var sxpets_length : int = readShort();
			for (var i: int = 0; i < sxpets_length; i++) {
				_sxpets[i] = readLong();
			}
			//双修的玩家ID
			_sxroleid = readLong();
			//双修玩家对方的坐标X
			_sxtargetx = readShort();
			//双修玩家对方的坐标Y
			_sxtargety = readShort();
			//军衔等级
			_ranklevel = readByte();
			//弓箭ID
			_arrowid = readInt();
			//境界等级
			_realmlevel = readInt();
			//当前暗器阶数
			_hiddenweaponid = readShort();
			//坐骑锻骨草使用数量
			_horseduangu = readShort();
			//当前护身符model
			_amuletModel = readInt();
			//配偶护身符model
			_spouseAmuletModel = readInt();
			//平台标识
			_webname = readString();
			//披风model
			_cloakModel = readInt();
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
		 * get 角色所在地图
		 * @return 
		 */
		public function get mapId(): int{
			return _mapId;
		}
		
		/**
		 * set 角色所在地图
		 */
		public function set mapId(value: int): void{
			this._mapId = value;
		}
		
		/**
		 * get 角色所在X
		 * @return 
		 */
		public function get x(): int{
			return _x;
		}
		
		/**
		 * set 角色所在X
		 */
		public function set x(value: int): void{
			this._x = value;
		}
		
		/**
		 * get 角色所在Y
		 * @return 
		 */
		public function get y(): int{
			return _y;
		}
		
		/**
		 * set 角色所在Y
		 */
		public function set y(value: int): void{
			this._y = value;
		}
		
		/**
		 * get 角色HP
		 * @return 
		 */
		public function get hp(): int{
			return _hp;
		}
		
		/**
		 * set 角色HP
		 */
		public function set hp(value: int): void{
			this._hp = value;
		}
		
		/**
		 * get 角色最大HP
		 * @return 
		 */
		public function get maxHp(): int{
			return _maxHp;
		}
		
		/**
		 * set 角色最大HP
		 */
		public function set maxHp(value: int): void{
			this._maxHp = value;
		}
		
		/**
		 * get 角色MP
		 * @return 
		 */
		public function get mp(): int{
			return _mp;
		}
		
		/**
		 * set 角色MP
		 */
		public function set mp(value: int): void{
			this._mp = value;
		}
		
		/**
		 * get 角色最大MP
		 * @return 
		 */
		public function get maxMp(): int{
			return _maxMp;
		}
		
		/**
		 * set 角色最大MP
		 */
		public function set maxMp(value: int): void{
			this._maxMp = value;
		}
		
		/**
		 * get 角色SP
		 * @return 
		 */
		public function get sp(): int{
			return _sp;
		}
		
		/**
		 * set 角色SP
		 */
		public function set sp(value: int): void{
			this._sp = value;
		}
		
		/**
		 * get 角色最大SP
		 * @return 
		 */
		public function get maxSp(): int{
			return _maxSp;
		}
		
		/**
		 * set 角色最大SP
		 */
		public function set maxSp(value: int): void{
			this._maxSp = value;
		}
		
		/**
		 * get 角色速度
		 * @return 
		 */
		public function get speed(): int{
			return _speed;
		}
		
		/**
		 * set 角色速度
		 */
		public function set speed(value: int): void{
			this._speed = value;
		}
		
		/**
		 * get 角色状态
		 * @return 
		 */
		public function get state(): int{
			return _state;
		}
		
		/**
		 * set 角色状态
		 */
		public function set state(value: int): void{
			this._state = value;
		}
		
		/**
		 * get 武器模板id
		 * @return 
		 */
		public function get weapon(): int{
			return _weapon;
		}
		
		/**
		 * set 武器模板id
		 */
		public function set weapon(value: int): void{
			this._weapon = value;
		}
		
		/**
		 * get 武器模板强化等级
		 * @return 
		 */
		public function get weaponStreng(): int{
			return _weaponStreng;
		}
		
		/**
		 * set 武器模板强化等级
		 */
		public function set weaponStreng(value: int): void{
			this._weaponStreng = value;
		}
		
		/**
		 * get 衣服模板id
		 * @return 
		 */
		public function get armor(): int{
			return _armor;
		}
		
		/**
		 * set 衣服模板id
		 */
		public function set armor(value: int): void{
			this._armor = value;
		}
		
		/**
		 * get 头像模板id
		 * @return 
		 */
		public function get avatar(): int{
			return _avatar;
		}
		
		/**
		 * set 头像模板id
		 */
		public function set avatar(value: int): void{
			this._avatar = value;
		}
		
		/**
		 * get 人物面对方向
		 * @return 
		 */
		public function get dir(): int{
			return _dir;
		}
		
		/**
		 * set 人物面对方向
		 */
		public function set dir(value: int): void{
			this._dir = value;
		}
		
		/**
		 * get 队伍ID
		 * @return 
		 */
		public function get team(): long{
			return _team;
		}
		
		/**
		 * set 队伍ID
		 */
		public function set team(value: long): void{
			this._team = value;
		}
		
		/**
		 * get 帮派ID
		 * @return 
		 */
		public function get guild(): long{
			return _guild;
		}
		
		/**
		 * set 帮派ID
		 */
		public function set guild(value: long): void{
			this._guild = value;
		}
		
		/**
		 * get 跑步坐标集合
		 * @return 
		 */
		public function get positions(): Vector.<int>{
			return _positions;
		}
		
		/**
		 * set 跑步坐标集合
		 */
		public function set positions(value: Vector.<int>): void{
			this._positions = value;
		}
		
		/**
		 * get buff集合
		 * @return 
		 */
		public function get buffs(): Vector.<com.game.buff.bean.BuffInfo>{
			return _buffs;
		}
		
		/**
		 * set buff集合
		 */
		public function set buffs(value: Vector.<com.game.buff.bean.BuffInfo>): void{
			this._buffs = value;
		}
		
		/**
		 * get 当前坐骑阶数
		 * @return 
		 */
		public function get horseid(): int{
			return _horseid;
		}
		
		/**
		 * set 当前坐骑阶数
		 */
		public function set horseid(value: int): void{
			this._horseid = value;
		}
		
		/**
		 * get 当前骑战兵器阶数
		 * @return 
		 */
		public function get horseweaponid(): int{
			return _horseweaponid;
		}
		
		/**
		 * set 当前骑战兵器阶数
		 */
		public function set horseweaponid(value: int): void{
			this._horseweaponid = value;
		}
		
		/**
		 * get 打坐状态 0 未打座 1单人打座 2与待宠双修 3与玩家双修
		 * @return 
		 */
		public function get dazuoState(): int{
			return _dazuoState;
		}
		
		/**
		 * set 打坐状态 0 未打座 1单人打座 2与待宠双修 3与玩家双修
		 */
		public function set dazuoState(value: int): void{
			this._dazuoState = value;
		}
		
		/**
		 * get 参与双修的宠物唯一ID
		 * @return 
		 */
		public function get sxpets(): Vector.<long>{
			return _sxpets;
		}
		
		/**
		 * set 参与双修的宠物唯一ID
		 */
		public function set sxpets(value: Vector.<long>): void{
			this._sxpets = value;
		}
		
		/**
		 * get 双修的玩家ID
		 * @return 
		 */
		public function get sxroleid(): long{
			return _sxroleid;
		}
		
		/**
		 * set 双修的玩家ID
		 */
		public function set sxroleid(value: long): void{
			this._sxroleid = value;
		}
		
		/**
		 * get 双修玩家对方的坐标X
		 * @return 
		 */
		public function get sxtargetx(): int{
			return _sxtargetx;
		}
		
		/**
		 * set 双修玩家对方的坐标X
		 */
		public function set sxtargetx(value: int): void{
			this._sxtargetx = value;
		}
		
		/**
		 * get 双修玩家对方的坐标Y
		 * @return 
		 */
		public function get sxtargety(): int{
			return _sxtargety;
		}
		
		/**
		 * set 双修玩家对方的坐标Y
		 */
		public function set sxtargety(value: int): void{
			this._sxtargety = value;
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
		 * get 弓箭ID
		 * @return 
		 */
		public function get arrowid(): int{
			return _arrowid;
		}
		
		/**
		 * set 弓箭ID
		 */
		public function set arrowid(value: int): void{
			this._arrowid = value;
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
		 * get 当前暗器阶数
		 * @return 
		 */
		public function get hiddenweaponid(): int{
			return _hiddenweaponid;
		}
		
		/**
		 * set 当前暗器阶数
		 */
		public function set hiddenweaponid(value: int): void{
			this._hiddenweaponid = value;
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
		 * get 当前护身符model
		 * @return 
		 */
		public function get amuletModel(): int{
			return _amuletModel;
		}
		
		/**
		 * set 当前护身符model
		 */
		public function set amuletModel(value: int): void{
			this._amuletModel = value;
		}
		
		/**
		 * get 配偶护身符model
		 * @return 
		 */
		public function get spouseAmuletModel(): int{
			return _spouseAmuletModel;
		}
		
		/**
		 * set 配偶护身符model
		 */
		public function set spouseAmuletModel(value: int): void{
			this._spouseAmuletModel = value;
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