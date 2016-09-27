package com.game.country.bean{
	import com.game.utils.long;
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
	public class PlayerDamageInfo extends Bean {
	
		//角色Id
		private var _personId: long;
		
		//角色名字
		private var _name: String;
		
		//角色性别 1-男 2-女
		private var _sex: int;
		
		//角色所在X
		private var _x: int;
		
		//角色所在Y
		private var _y: int;
		
		//角色HP
		private var _hp: int;
		
		//角色最大HP
		private var _maxHp: int;
		
		//角色状态
		private var _state: int;
		
		//武器模板id
		private var _weapon: int;
		
		//衣服模板id
		private var _armor: int;
		
		//头像模板id
		private var _avatar: int;
		
		//人物面对方向
		private var _dir: int;
		
		//帮派ID
		private var _guild: long;
		
		//当前坐骑阶数
		private var _horseid: int;
		
		//伤害数值
		private var _damage: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色Id
			writeLong(_personId);
			//角色名字
			writeString(_name);
			//角色性别 1-男 2-女
			writeByte(_sex);
			//角色所在X
			writeShort(_x);
			//角色所在Y
			writeShort(_y);
			//角色HP
			writeInt(_hp);
			//角色最大HP
			writeInt(_maxHp);
			//角色状态
			writeInt(_state);
			//武器模板id
			writeInt(_weapon);
			//衣服模板id
			writeInt(_armor);
			//头像模板id
			writeInt(_avatar);
			//人物面对方向
			writeByte(_dir);
			//帮派ID
			writeLong(_guild);
			//当前坐骑阶数
			writeShort(_horseid);
			//伤害数值
			writeInt(_damage);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色Id
			_personId = readLong();
			//角色名字
			_name = readString();
			//角色性别 1-男 2-女
			_sex = readByte();
			//角色所在X
			_x = readShort();
			//角色所在Y
			_y = readShort();
			//角色HP
			_hp = readInt();
			//角色最大HP
			_maxHp = readInt();
			//角色状态
			_state = readInt();
			//武器模板id
			_weapon = readInt();
			//衣服模板id
			_armor = readInt();
			//头像模板id
			_avatar = readInt();
			//人物面对方向
			_dir = readByte();
			//帮派ID
			_guild = readLong();
			//当前坐骑阶数
			_horseid = readShort();
			//伤害数值
			_damage = readInt();
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
		 * get 伤害数值
		 * @return 
		 */
		public function get damage(): int{
			return _damage;
		}
		
		/**
		 * set 伤害数值
		 */
		public function set damage(value: int): void{
			this._damage = value;
		}
		
	}
}