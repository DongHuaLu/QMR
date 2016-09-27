package com.game.player.message{
	import com.game.utils.long;
	import com.game.equip.bean.EquipInfo;
	import com.game.gem.bean.PosGemInfo;
	import com.game.backpack.bean.ItemInfo;
	import com.game.player.bean.PlayerAttributeItem;
	import com.game.arrow.bean.ArrowInfo;
	import com.game.structs.Position;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 本人玩家信息
	 */
	public class ResMyPlayerInfoMessage extends Message {
	
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
		
		//角色所在地图
		private var _mapId: int;
		
		//角色所在X
		private var _x: int;
		
		//角色所在Y
		private var _y: int;
		
		//角色武功境界
		private var _skill: int;
		
		//角色武功境界层数
		private var _skills: int;
		
		//角色状态
		private var _state: int;
		
		//角色PK状态
		private var _pkState: int;
		
		//角色经验
		private var _exp: long;
		
		//角色真气
		private var _zhenqi: int;
		
		//角色战场声望
		private var _prestige: int;
		
		//人物面对方向
		private var _dir: int;
		
		//头象ID
		private var _avatar: int;
		
		//属性列表
		private var _attributes: Vector.<PlayerAttributeItem> = new Vector.<PlayerAttributeItem>();
		//跑步坐标集合
		private var _positions: Vector.<com.game.structs.Position> = new Vector.<com.game.structs.Position>();
		//装备列表信息
		private var _equips: Vector.<com.game.equip.bean.EquipInfo> = new Vector.<com.game.equip.bean.EquipInfo>();
		//背包格子数
		private var _cellnum: int;
		
		//仓库格子数
		private var _storecellnum: int;
		
		//物品信息列表
		private var _items: Vector.<com.game.backpack.bean.ItemInfo> = new Vector.<com.game.backpack.bean.ItemInfo>();
		//金币
		private var _money: int;
		
		//元宝
		private var _gold: int;
		
		//绑定元宝
		private var _bindgold: int;
		
		//玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
		private var _nonage: int;
		
		//角色当前坐骑
		private var _horseid: int;
		
		//角色当前骑战兵器
		private var _horseweaponid: int;
		
		//角色当前暗器
		private var _hiddenweaponid: int;
		
		//装备部位全部宝石信息
		private var _posallgeminfo: Vector.<com.game.gem.bean.PosGemInfo> = new Vector.<com.game.gem.bean.PosGemInfo>();
		//主线任务ID
		private var _maintaskId: int;
		
		//王城BUFFid
		private var _kingcitybuffid: int;
		
		//VIPid
		private var _vipid: int;
		
		//龙元心法阶段（星图）
		private var _longyuanlv: int;
		
		//龙元心法星位
		private var _longyuannum: int;
		
		//军衔等级
		private var _ranklevel: int;
		
		//弓箭信息
		private var _arrowInfo: com.game.arrow.bean.ArrowInfo;
		
		//平台VIP
		private var _webvip: int;
		
		//平台VIP2
		private var _webvip2: int;
		
		//坐骑锻骨草使用数量
		private var _horseduangu: int;
		
		//平台标识
		private var _webname: String;
		
		//披风model
		private var _cloakModel: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
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
			//角色所在地图
			writeInt(_mapId);
			//角色所在X
			writeShort(_x);
			//角色所在Y
			writeShort(_y);
			//角色武功境界
			writeByte(_skill);
			//角色武功境界层数
			writeInt(_skills);
			//角色状态
			writeInt(_state);
			//角色PK状态
			writeInt(_pkState);
			//角色经验
			writeLong(_exp);
			//角色真气
			writeInt(_zhenqi);
			//角色战场声望
			writeInt(_prestige);
			//人物面对方向
			writeByte(_dir);
			//头象ID
			writeInt(_avatar);
			//属性列表
			writeShort(_attributes.length);
			for (i = 0; i < _attributes.length; i++) {
				writeBean(_attributes[i]);
			}
			//跑步坐标集合
			writeShort(_positions.length);
			for (i = 0; i < _positions.length; i++) {
				writeBean(_positions[i]);
			}
			//装备列表信息
			writeShort(_equips.length);
			for (i = 0; i < _equips.length; i++) {
				writeBean(_equips[i]);
			}
			//背包格子数
			writeShort(_cellnum);
			//仓库格子数
			writeShort(_storecellnum);
			//物品信息列表
			writeShort(_items.length);
			for (i = 0; i < _items.length; i++) {
				writeBean(_items[i]);
			}
			//金币
			writeInt(_money);
			//元宝
			writeInt(_gold);
			//绑定元宝
			writeInt(_bindgold);
			//玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
			writeByte(_nonage);
			//角色当前坐骑
			writeShort(_horseid);
			//角色当前骑战兵器
			writeShort(_horseweaponid);
			//角色当前暗器
			writeShort(_hiddenweaponid);
			//装备部位全部宝石信息
			writeShort(_posallgeminfo.length);
			for (i = 0; i < _posallgeminfo.length; i++) {
				writeBean(_posallgeminfo[i]);
			}
			//主线任务ID
			writeInt(_maintaskId);
			//王城BUFFid
			writeInt(_kingcitybuffid);
			//VIPid
			writeInt(_vipid);
			//龙元心法阶段（星图）
			writeByte(_longyuanlv);
			//龙元心法星位
			writeByte(_longyuannum);
			//军衔等级
			writeByte(_ranklevel);
			//弓箭信息
			writeBean(_arrowInfo);
			//平台VIP
			writeInt(_webvip);
			//平台VIP2
			writeInt(_webvip2);
			//坐骑锻骨草使用数量
			writeShort(_horseduangu);
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
			var i: int = 0;
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
			//角色所在地图
			_mapId = readInt();
			//角色所在X
			_x = readShort();
			//角色所在Y
			_y = readShort();
			//角色武功境界
			_skill = readByte();
			//角色武功境界层数
			_skills = readInt();
			//角色状态
			_state = readInt();
			//角色PK状态
			_pkState = readInt();
			//角色经验
			_exp = readLong();
			//角色真气
			_zhenqi = readInt();
			//角色战场声望
			_prestige = readInt();
			//人物面对方向
			_dir = readByte();
			//头象ID
			_avatar = readInt();
			//属性列表
			var attributes_length : int = readShort();
			for (i = 0; i < attributes_length; i++) {
				_attributes[i] = readBean(PlayerAttributeItem) as PlayerAttributeItem;
			}
			//跑步坐标集合
			var positions_length : int = readShort();
			for (i = 0; i < positions_length; i++) {
				_positions[i] = readBean(com.game.structs.Position) as com.game.structs.Position;
			}
			//装备列表信息
			var equips_length : int = readShort();
			for (i = 0; i < equips_length; i++) {
				_equips[i] = readBean(com.game.equip.bean.EquipInfo) as com.game.equip.bean.EquipInfo;
			}
			//背包格子数
			_cellnum = readShort();
			//仓库格子数
			_storecellnum = readShort();
			//物品信息列表
			var items_length : int = readShort();
			for (i = 0; i < items_length; i++) {
				_items[i] = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			}
			//金币
			_money = readInt();
			//元宝
			_gold = readInt();
			//绑定元宝
			_bindgold = readInt();
			//玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
			_nonage = readByte();
			//角色当前坐骑
			_horseid = readShort();
			//角色当前骑战兵器
			_horseweaponid = readShort();
			//角色当前暗器
			_hiddenweaponid = readShort();
			//装备部位全部宝石信息
			var posallgeminfo_length : int = readShort();
			for (i = 0; i < posallgeminfo_length; i++) {
				_posallgeminfo[i] = readBean(com.game.gem.bean.PosGemInfo) as com.game.gem.bean.PosGemInfo;
			}
			//主线任务ID
			_maintaskId = readInt();
			//王城BUFFid
			_kingcitybuffid = readInt();
			//VIPid
			_vipid = readInt();
			//龙元心法阶段（星图）
			_longyuanlv = readByte();
			//龙元心法星位
			_longyuannum = readByte();
			//军衔等级
			_ranklevel = readByte();
			//弓箭信息
			_arrowInfo = readBean(com.game.arrow.bean.ArrowInfo) as com.game.arrow.bean.ArrowInfo;
			//平台VIP
			_webvip = readInt();
			//平台VIP2
			_webvip2 = readInt();
			//坐骑锻骨草使用数量
			_horseduangu = readShort();
			//平台标识
			_webname = readString();
			//披风model
			_cloakModel = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103107;
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
		 * get 角色PK状态
		 * @return 
		 */
		public function get pkState(): int{
			return _pkState;
		}
		
		/**
		 * set 角色PK状态
		 */
		public function set pkState(value: int): void{
			this._pkState = value;
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
		 * get 跑步坐标集合
		 * @return 
		 */
		public function get positions(): Vector.<com.game.structs.Position>{
			return _positions;
		}
		
		/**
		 * set 跑步坐标集合
		 */
		public function set positions(value: Vector.<com.game.structs.Position>): void{
			this._positions = value;
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
		 * get 背包格子数
		 * @return 
		 */
		public function get cellnum(): int{
			return _cellnum;
		}
		
		/**
		 * set 背包格子数
		 */
		public function set cellnum(value: int): void{
			this._cellnum = value;
		}
		
		/**
		 * get 仓库格子数
		 * @return 
		 */
		public function get storecellnum(): int{
			return _storecellnum;
		}
		
		/**
		 * set 仓库格子数
		 */
		public function set storecellnum(value: int): void{
			this._storecellnum = value;
		}
		
		/**
		 * get 物品信息列表
		 * @return 
		 */
		public function get items(): Vector.<com.game.backpack.bean.ItemInfo>{
			return _items;
		}
		
		/**
		 * set 物品信息列表
		 */
		public function set items(value: Vector.<com.game.backpack.bean.ItemInfo>): void{
			this._items = value;
		}
		
		/**
		 * get 金币
		 * @return 
		 */
		public function get money(): int{
			return _money;
		}
		
		/**
		 * set 金币
		 */
		public function set money(value: int): void{
			this._money = value;
		}
		
		/**
		 * get 元宝
		 * @return 
		 */
		public function get gold(): int{
			return _gold;
		}
		
		/**
		 * set 元宝
		 */
		public function set gold(value: int): void{
			this._gold = value;
		}
		
		/**
		 * get 绑定元宝
		 * @return 
		 */
		public function get bindgold(): int{
			return _bindgold;
		}
		
		/**
		 * set 绑定元宝
		 */
		public function set bindgold(value: int): void{
			this._bindgold = value;
		}
		
		/**
		 * get 玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
		 * @return 
		 */
		public function get nonage(): int{
			return _nonage;
		}
		
		/**
		 * set 玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
		 */
		public function set nonage(value: int): void{
			this._nonage = value;
		}
		
		/**
		 * get 角色当前坐骑
		 * @return 
		 */
		public function get horseid(): int{
			return _horseid;
		}
		
		/**
		 * set 角色当前坐骑
		 */
		public function set horseid(value: int): void{
			this._horseid = value;
		}
		
		/**
		 * get 角色当前骑战兵器
		 * @return 
		 */
		public function get horseweaponid(): int{
			return _horseweaponid;
		}
		
		/**
		 * set 角色当前骑战兵器
		 */
		public function set horseweaponid(value: int): void{
			this._horseweaponid = value;
		}
		
		/**
		 * get 角色当前暗器
		 * @return 
		 */
		public function get hiddenweaponid(): int{
			return _hiddenweaponid;
		}
		
		/**
		 * set 角色当前暗器
		 */
		public function set hiddenweaponid(value: int): void{
			this._hiddenweaponid = value;
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
		 * get 主线任务ID
		 * @return 
		 */
		public function get maintaskId(): int{
			return _maintaskId;
		}
		
		/**
		 * set 主线任务ID
		 */
		public function set maintaskId(value: int): void{
			this._maintaskId = value;
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
		 * get 龙元心法阶段（星图）
		 * @return 
		 */
		public function get longyuanlv(): int{
			return _longyuanlv;
		}
		
		/**
		 * set 龙元心法阶段（星图）
		 */
		public function set longyuanlv(value: int): void{
			this._longyuanlv = value;
		}
		
		/**
		 * get 龙元心法星位
		 * @return 
		 */
		public function get longyuannum(): int{
			return _longyuannum;
		}
		
		/**
		 * set 龙元心法星位
		 */
		public function set longyuannum(value: int): void{
			this._longyuannum = value;
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
		 * get 平台VIP2
		 * @return 
		 */
		public function get webvip2(): int{
			return _webvip2;
		}
		
		/**
		 * set 平台VIP2
		 */
		public function set webvip2(value: int): void{
			this._webvip2 = value;
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