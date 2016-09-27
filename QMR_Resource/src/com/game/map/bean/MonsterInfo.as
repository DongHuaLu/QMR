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
	 * 怪物信息类
	 */
	public class MonsterInfo extends Bean {
	
		//怪物Id
		private var _monsterId: long;
		
		//怪物模板Id
		private var _monsterModelId: int;
		
		//怪物名字
		private var _monsterName: String;
		
		//怪物资源造型
		private var _monsterRes: String;
		
		//怪物头像造型
		private var _monsterIcon: String;
		
		//怪物敌对类型 0-全体玩家敌对， 1-全体玩家友好， 2-帮派敌对
		private var _friend: int;
		
		//怪物敌对参数
		private var _friendPara: String;
		
		//怪物等级
		private var _level: int;
		
		//怪物所在地图
		private var _mapId: int;
		
		//怪物所在X
		private var _x: int;
		
		//怪物所在Y
		private var _y: int;
		
		//怪物HP
		private var _hp: int;
		
		//怪物最大HP
		private var _maxHp: int;
		
		//怪物MP
		private var _mp: int;
		
		//怪物最大MP
		private var _maxMp: int;
		
		//怪物SP
		private var _sp: int;
		
		//怪物最大SP
		private var _maxSp: int;
		
		//怪物速度
		private var _speed: int;
		
		//变身类型
		private var _morph: int;
		
		//怪物面对方向
		private var _dir: int;
		
		//跑步坐标集合
		private var _positions: Vector.<int> = new Vector.<int>();
		//buff集合
		private var _buffs: Vector.<com.game.buff.bean.BuffInfo> = new Vector.<com.game.buff.bean.BuffInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//怪物Id
			writeLong(_monsterId);
			//怪物模板Id
			writeInt(_monsterModelId);
			//怪物名字
			writeString(_monsterName);
			//怪物资源造型
			writeString(_monsterRes);
			//怪物头像造型
			writeString(_monsterIcon);
			//怪物敌对类型 0-全体玩家敌对， 1-全体玩家友好， 2-帮派敌对
			writeByte(_friend);
			//怪物敌对参数
			writeString(_friendPara);
			//怪物等级
			writeInt(_level);
			//怪物所在地图
			writeInt(_mapId);
			//怪物所在X
			writeShort(_x);
			//怪物所在Y
			writeShort(_y);
			//怪物HP
			writeInt(_hp);
			//怪物最大HP
			writeInt(_maxHp);
			//怪物MP
			writeInt(_mp);
			//怪物最大MP
			writeInt(_maxMp);
			//怪物SP
			writeInt(_sp);
			//怪物最大SP
			writeInt(_maxSp);
			//怪物速度
			writeInt(_speed);
			//变身类型
			writeInt(_morph);
			//怪物面对方向
			writeByte(_dir);
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
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//怪物Id
			_monsterId = readLong();
			//怪物模板Id
			_monsterModelId = readInt();
			//怪物名字
			_monsterName = readString();
			//怪物资源造型
			_monsterRes = readString();
			//怪物头像造型
			_monsterIcon = readString();
			//怪物敌对类型 0-全体玩家敌对， 1-全体玩家友好， 2-帮派敌对
			_friend = readByte();
			//怪物敌对参数
			_friendPara = readString();
			//怪物等级
			_level = readInt();
			//怪物所在地图
			_mapId = readInt();
			//怪物所在X
			_x = readShort();
			//怪物所在Y
			_y = readShort();
			//怪物HP
			_hp = readInt();
			//怪物最大HP
			_maxHp = readInt();
			//怪物MP
			_mp = readInt();
			//怪物最大MP
			_maxMp = readInt();
			//怪物SP
			_sp = readInt();
			//怪物最大SP
			_maxSp = readInt();
			//怪物速度
			_speed = readInt();
			//变身类型
			_morph = readInt();
			//怪物面对方向
			_dir = readByte();
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
			return true;
		}
		
		/**
		 * get 怪物Id
		 * @return 
		 */
		public function get monsterId(): long{
			return _monsterId;
		}
		
		/**
		 * set 怪物Id
		 */
		public function set monsterId(value: long): void{
			this._monsterId = value;
		}
		
		/**
		 * get 怪物模板Id
		 * @return 
		 */
		public function get monsterModelId(): int{
			return _monsterModelId;
		}
		
		/**
		 * set 怪物模板Id
		 */
		public function set monsterModelId(value: int): void{
			this._monsterModelId = value;
		}
		
		/**
		 * get 怪物名字
		 * @return 
		 */
		public function get monsterName(): String{
			return _monsterName;
		}
		
		/**
		 * set 怪物名字
		 */
		public function set monsterName(value: String): void{
			this._monsterName = value;
		}
		
		/**
		 * get 怪物资源造型
		 * @return 
		 */
		public function get monsterRes(): String{
			return _monsterRes;
		}
		
		/**
		 * set 怪物资源造型
		 */
		public function set monsterRes(value: String): void{
			this._monsterRes = value;
		}
		
		/**
		 * get 怪物头像造型
		 * @return 
		 */
		public function get monsterIcon(): String{
			return _monsterIcon;
		}
		
		/**
		 * set 怪物头像造型
		 */
		public function set monsterIcon(value: String): void{
			this._monsterIcon = value;
		}
		
		/**
		 * get 怪物敌对类型 0-全体玩家敌对， 1-全体玩家友好， 2-帮派敌对
		 * @return 
		 */
		public function get friend(): int{
			return _friend;
		}
		
		/**
		 * set 怪物敌对类型 0-全体玩家敌对， 1-全体玩家友好， 2-帮派敌对
		 */
		public function set friend(value: int): void{
			this._friend = value;
		}
		
		/**
		 * get 怪物敌对参数
		 * @return 
		 */
		public function get friendPara(): String{
			return _friendPara;
		}
		
		/**
		 * set 怪物敌对参数
		 */
		public function set friendPara(value: String): void{
			this._friendPara = value;
		}
		
		/**
		 * get 怪物等级
		 * @return 
		 */
		public function get level(): int{
			return _level;
		}
		
		/**
		 * set 怪物等级
		 */
		public function set level(value: int): void{
			this._level = value;
		}
		
		/**
		 * get 怪物所在地图
		 * @return 
		 */
		public function get mapId(): int{
			return _mapId;
		}
		
		/**
		 * set 怪物所在地图
		 */
		public function set mapId(value: int): void{
			this._mapId = value;
		}
		
		/**
		 * get 怪物所在X
		 * @return 
		 */
		public function get x(): int{
			return _x;
		}
		
		/**
		 * set 怪物所在X
		 */
		public function set x(value: int): void{
			this._x = value;
		}
		
		/**
		 * get 怪物所在Y
		 * @return 
		 */
		public function get y(): int{
			return _y;
		}
		
		/**
		 * set 怪物所在Y
		 */
		public function set y(value: int): void{
			this._y = value;
		}
		
		/**
		 * get 怪物HP
		 * @return 
		 */
		public function get hp(): int{
			return _hp;
		}
		
		/**
		 * set 怪物HP
		 */
		public function set hp(value: int): void{
			this._hp = value;
		}
		
		/**
		 * get 怪物最大HP
		 * @return 
		 */
		public function get maxHp(): int{
			return _maxHp;
		}
		
		/**
		 * set 怪物最大HP
		 */
		public function set maxHp(value: int): void{
			this._maxHp = value;
		}
		
		/**
		 * get 怪物MP
		 * @return 
		 */
		public function get mp(): int{
			return _mp;
		}
		
		/**
		 * set 怪物MP
		 */
		public function set mp(value: int): void{
			this._mp = value;
		}
		
		/**
		 * get 怪物最大MP
		 * @return 
		 */
		public function get maxMp(): int{
			return _maxMp;
		}
		
		/**
		 * set 怪物最大MP
		 */
		public function set maxMp(value: int): void{
			this._maxMp = value;
		}
		
		/**
		 * get 怪物SP
		 * @return 
		 */
		public function get sp(): int{
			return _sp;
		}
		
		/**
		 * set 怪物SP
		 */
		public function set sp(value: int): void{
			this._sp = value;
		}
		
		/**
		 * get 怪物最大SP
		 * @return 
		 */
		public function get maxSp(): int{
			return _maxSp;
		}
		
		/**
		 * set 怪物最大SP
		 */
		public function set maxSp(value: int): void{
			this._maxSp = value;
		}
		
		/**
		 * get 怪物速度
		 * @return 
		 */
		public function get speed(): int{
			return _speed;
		}
		
		/**
		 * set 怪物速度
		 */
		public function set speed(value: int): void{
			this._speed = value;
		}
		
		/**
		 * get 变身类型
		 * @return 
		 */
		public function get morph(): int{
			return _morph;
		}
		
		/**
		 * set 变身类型
		 */
		public function set morph(value: int): void{
			this._morph = value;
		}
		
		/**
		 * get 怪物面对方向
		 * @return 
		 */
		public function get dir(): int{
			return _dir;
		}
		
		/**
		 * set 怪物面对方向
		 */
		public function set dir(value: int): void{
			this._dir = value;
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
		
	}
}