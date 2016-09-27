package com.game.pet.bean{
	import com.game.skill.bean.SkillInfo;
	import com.game.utils.long;
	import com.game.player.bean.PlayerAttributeItem;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 宠物祥细信息类
	 */
	public class PetDetailInfo extends Bean {
	
		//宠物Id
		private var _petId: long;
		
		//宠物模板Id
		private var _petModelId: int;
		
		//宠物等级
		private var _level: int;
		
		//宠物HP
		private var _hp: int;
		
		//宠物最大HP
		private var _maxHp: int;
		
		//宠物MP
		private var _mp: int;
		
		//宠物最大MP
		private var _maxMp: int;
		
		//宠物SP
		private var _sp: int;
		
		//宠物最大SP
		private var _maxSp: int;
		
		//宠物速度
		private var _speed: int;
		
		//出战状态,1出战 0不出战
		private var _showState: int;
		
		//死亡时间 如果出战状态且未死亡则返回0 否则返回秒级时间
		private var _dieTime: int;
		
		//合体次数
		private var _htCount: int;
		
		//今日合体次数
		private var _dayCount: int;
		
		//合体冷确时间
		private var _htCoolDownTime: int;
		
		//亲热次数
		private var _makeLoveCount: int;
		
		//今日亲热次数
		private var _dayMakeLoveCount: int;
		
		//亲热冷确时间
		private var _makeLoveCoolDownTime: int;
		
		//技能列表
		private var _skillInfos: Vector.<com.game.skill.bean.SkillInfo> = new Vector.<com.game.skill.bean.SkillInfo>();
		//合体加成
		private var _htAddition: Vector.<com.game.player.bean.PlayerAttributeItem> = new Vector.<com.game.player.bean.PlayerAttributeItem>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//宠物Id
			writeLong(_petId);
			//宠物模板Id
			writeInt(_petModelId);
			//宠物等级
			writeInt(_level);
			//宠物HP
			writeInt(_hp);
			//宠物最大HP
			writeInt(_maxHp);
			//宠物MP
			writeInt(_mp);
			//宠物最大MP
			writeInt(_maxMp);
			//宠物SP
			writeInt(_sp);
			//宠物最大SP
			writeInt(_maxSp);
			//宠物速度
			writeInt(_speed);
			//出战状态,1出战 0不出战
			writeByte(_showState);
			//死亡时间 如果出战状态且未死亡则返回0 否则返回秒级时间
			writeInt(_dieTime);
			//合体次数
			writeInt(_htCount);
			//今日合体次数
			writeInt(_dayCount);
			//合体冷确时间
			writeInt(_htCoolDownTime);
			//亲热次数
			writeInt(_makeLoveCount);
			//今日亲热次数
			writeInt(_dayMakeLoveCount);
			//亲热冷确时间
			writeInt(_makeLoveCoolDownTime);
			//技能列表
			writeShort(_skillInfos.length);
			for (var i: int = 0; i < _skillInfos.length; i++) {
				writeBean(_skillInfos[i]);
			}
			//合体加成
			writeShort(_htAddition.length);
			for (var i: int = 0; i < _htAddition.length; i++) {
				writeBean(_htAddition[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//宠物Id
			_petId = readLong();
			//宠物模板Id
			_petModelId = readInt();
			//宠物等级
			_level = readInt();
			//宠物HP
			_hp = readInt();
			//宠物最大HP
			_maxHp = readInt();
			//宠物MP
			_mp = readInt();
			//宠物最大MP
			_maxMp = readInt();
			//宠物SP
			_sp = readInt();
			//宠物最大SP
			_maxSp = readInt();
			//宠物速度
			_speed = readInt();
			//出战状态,1出战 0不出战
			_showState = readByte();
			//死亡时间 如果出战状态且未死亡则返回0 否则返回秒级时间
			_dieTime = readInt();
			//合体次数
			_htCount = readInt();
			//今日合体次数
			_dayCount = readInt();
			//合体冷确时间
			_htCoolDownTime = readInt();
			//亲热次数
			_makeLoveCount = readInt();
			//今日亲热次数
			_dayMakeLoveCount = readInt();
			//亲热冷确时间
			_makeLoveCoolDownTime = readInt();
			//技能列表
			var skillInfos_length : int = readShort();
			for (var i: int = 0; i < skillInfos_length; i++) {
				_skillInfos[i] = readBean(com.game.skill.bean.SkillInfo) as com.game.skill.bean.SkillInfo;
			}
			//合体加成
			var htAddition_length : int = readShort();
			for (var i: int = 0; i < htAddition_length; i++) {
				_htAddition[i] = readBean(com.game.player.bean.PlayerAttributeItem) as com.game.player.bean.PlayerAttributeItem;
			}
			return true;
		}
		
		/**
		 * get 宠物Id
		 * @return 
		 */
		public function get petId(): long{
			return _petId;
		}
		
		/**
		 * set 宠物Id
		 */
		public function set petId(value: long): void{
			this._petId = value;
		}
		
		/**
		 * get 宠物模板Id
		 * @return 
		 */
		public function get petModelId(): int{
			return _petModelId;
		}
		
		/**
		 * set 宠物模板Id
		 */
		public function set petModelId(value: int): void{
			this._petModelId = value;
		}
		
		/**
		 * get 宠物等级
		 * @return 
		 */
		public function get level(): int{
			return _level;
		}
		
		/**
		 * set 宠物等级
		 */
		public function set level(value: int): void{
			this._level = value;
		}
		
		/**
		 * get 宠物HP
		 * @return 
		 */
		public function get hp(): int{
			return _hp;
		}
		
		/**
		 * set 宠物HP
		 */
		public function set hp(value: int): void{
			this._hp = value;
		}
		
		/**
		 * get 宠物最大HP
		 * @return 
		 */
		public function get maxHp(): int{
			return _maxHp;
		}
		
		/**
		 * set 宠物最大HP
		 */
		public function set maxHp(value: int): void{
			this._maxHp = value;
		}
		
		/**
		 * get 宠物MP
		 * @return 
		 */
		public function get mp(): int{
			return _mp;
		}
		
		/**
		 * set 宠物MP
		 */
		public function set mp(value: int): void{
			this._mp = value;
		}
		
		/**
		 * get 宠物最大MP
		 * @return 
		 */
		public function get maxMp(): int{
			return _maxMp;
		}
		
		/**
		 * set 宠物最大MP
		 */
		public function set maxMp(value: int): void{
			this._maxMp = value;
		}
		
		/**
		 * get 宠物SP
		 * @return 
		 */
		public function get sp(): int{
			return _sp;
		}
		
		/**
		 * set 宠物SP
		 */
		public function set sp(value: int): void{
			this._sp = value;
		}
		
		/**
		 * get 宠物最大SP
		 * @return 
		 */
		public function get maxSp(): int{
			return _maxSp;
		}
		
		/**
		 * set 宠物最大SP
		 */
		public function set maxSp(value: int): void{
			this._maxSp = value;
		}
		
		/**
		 * get 宠物速度
		 * @return 
		 */
		public function get speed(): int{
			return _speed;
		}
		
		/**
		 * set 宠物速度
		 */
		public function set speed(value: int): void{
			this._speed = value;
		}
		
		/**
		 * get 出战状态,1出战 0不出战
		 * @return 
		 */
		public function get showState(): int{
			return _showState;
		}
		
		/**
		 * set 出战状态,1出战 0不出战
		 */
		public function set showState(value: int): void{
			this._showState = value;
		}
		
		/**
		 * get 死亡时间 如果出战状态且未死亡则返回0 否则返回秒级时间
		 * @return 
		 */
		public function get dieTime(): int{
			return _dieTime;
		}
		
		/**
		 * set 死亡时间 如果出战状态且未死亡则返回0 否则返回秒级时间
		 */
		public function set dieTime(value: int): void{
			this._dieTime = value;
		}
		
		/**
		 * get 合体次数
		 * @return 
		 */
		public function get htCount(): int{
			return _htCount;
		}
		
		/**
		 * set 合体次数
		 */
		public function set htCount(value: int): void{
			this._htCount = value;
		}
		
		/**
		 * get 今日合体次数
		 * @return 
		 */
		public function get dayCount(): int{
			return _dayCount;
		}
		
		/**
		 * set 今日合体次数
		 */
		public function set dayCount(value: int): void{
			this._dayCount = value;
		}
		
		/**
		 * get 合体冷确时间
		 * @return 
		 */
		public function get htCoolDownTime(): int{
			return _htCoolDownTime;
		}
		
		/**
		 * set 合体冷确时间
		 */
		public function set htCoolDownTime(value: int): void{
			this._htCoolDownTime = value;
		}
		
		/**
		 * get 亲热次数
		 * @return 
		 */
		public function get makeLoveCount(): int{
			return _makeLoveCount;
		}
		
		/**
		 * set 亲热次数
		 */
		public function set makeLoveCount(value: int): void{
			this._makeLoveCount = value;
		}
		
		/**
		 * get 今日亲热次数
		 * @return 
		 */
		public function get dayMakeLoveCount(): int{
			return _dayMakeLoveCount;
		}
		
		/**
		 * set 今日亲热次数
		 */
		public function set dayMakeLoveCount(value: int): void{
			this._dayMakeLoveCount = value;
		}
		
		/**
		 * get 亲热冷确时间
		 * @return 
		 */
		public function get makeLoveCoolDownTime(): int{
			return _makeLoveCoolDownTime;
		}
		
		/**
		 * set 亲热冷确时间
		 */
		public function set makeLoveCoolDownTime(value: int): void{
			this._makeLoveCoolDownTime = value;
		}
		
		/**
		 * get 技能列表
		 * @return 
		 */
		public function get skillInfos(): Vector.<com.game.skill.bean.SkillInfo>{
			return _skillInfos;
		}
		
		/**
		 * set 技能列表
		 */
		public function set skillInfos(value: Vector.<com.game.skill.bean.SkillInfo>): void{
			this._skillInfos = value;
		}
		
		/**
		 * get 合体加成
		 * @return 
		 */
		public function get htAddition(): Vector.<com.game.player.bean.PlayerAttributeItem>{
			return _htAddition;
		}
		
		/**
		 * set 合体加成
		 */
		public function set htAddition(value: Vector.<com.game.player.bean.PlayerAttributeItem>): void{
			this._htAddition = value;
		}
		
	}
}