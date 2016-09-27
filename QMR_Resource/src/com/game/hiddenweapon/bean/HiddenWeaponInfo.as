package com.game.hiddenweapon.bean{
	import com.game.hiddenweapon.bean.HiddenWeaponSkillInfo;
	import com.game.hiddenweapon.bean.HiddenWeaponSkillIco;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 暗器信息
	 */
	public class HiddenWeaponInfo extends Bean {
	
		//当前最高暗器阶层
		private var _layer: int;
		
		//当前使用的暗器阶层
		private var _curlayer: int;
		
		//是否装备，1装备，0卸下
		private var _status: int;
		
		//剩余过期时间
		private var _time: int;
		
		//当前祝福值
		private var _bless: int;
		
		//暗器技能ico
		private var _ico: HiddenWeaponSkillIco;
		
		//技能
		private var _skills: Vector.<HiddenWeaponSkillInfo> = new Vector.<HiddenWeaponSkillInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前最高暗器阶层
			writeShort(_layer);
			//当前使用的暗器阶层
			writeShort(_curlayer);
			//是否装备，1装备，0卸下
			writeByte(_status);
			//剩余过期时间
			writeInt(_time);
			//当前祝福值
			writeInt(_bless);
			//暗器技能ico
			writeBean(_ico);
			//技能
			writeShort(_skills.length);
			for (var i: int = 0; i < _skills.length; i++) {
				writeBean(_skills[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前最高暗器阶层
			_layer = readShort();
			//当前使用的暗器阶层
			_curlayer = readShort();
			//是否装备，1装备，0卸下
			_status = readByte();
			//剩余过期时间
			_time = readInt();
			//当前祝福值
			_bless = readInt();
			//暗器技能ico
			_ico = readBean(HiddenWeaponSkillIco) as HiddenWeaponSkillIco;
			//技能
			var skills_length : int = readShort();
			for (var i: int = 0; i < skills_length; i++) {
				_skills[i] = readBean(HiddenWeaponSkillInfo) as HiddenWeaponSkillInfo;
			}
			return true;
		}
		
		/**
		 * get 当前最高暗器阶层
		 * @return 
		 */
		public function get layer(): int{
			return _layer;
		}
		
		/**
		 * set 当前最高暗器阶层
		 */
		public function set layer(value: int): void{
			this._layer = value;
		}
		
		/**
		 * get 当前使用的暗器阶层
		 * @return 
		 */
		public function get curlayer(): int{
			return _curlayer;
		}
		
		/**
		 * set 当前使用的暗器阶层
		 */
		public function set curlayer(value: int): void{
			this._curlayer = value;
		}
		
		/**
		 * get 是否装备，1装备，0卸下
		 * @return 
		 */
		public function get status(): int{
			return _status;
		}
		
		/**
		 * set 是否装备，1装备，0卸下
		 */
		public function set status(value: int): void{
			this._status = value;
		}
		
		/**
		 * get 剩余过期时间
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 剩余过期时间
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
		/**
		 * get 当前祝福值
		 * @return 
		 */
		public function get bless(): int{
			return _bless;
		}
		
		/**
		 * set 当前祝福值
		 */
		public function set bless(value: int): void{
			this._bless = value;
		}
		
		/**
		 * get 暗器技能ico
		 * @return 
		 */
		public function get ico(): HiddenWeaponSkillIco{
			return _ico;
		}
		
		/**
		 * set 暗器技能ico
		 */
		public function set ico(value: HiddenWeaponSkillIco): void{
			this._ico = value;
		}
		
		/**
		 * get 技能
		 * @return 
		 */
		public function get skills(): Vector.<HiddenWeaponSkillInfo>{
			return _skills;
		}
		
		/**
		 * set 技能
		 */
		public function set skills(value: Vector.<HiddenWeaponSkillInfo>): void{
			this._skills = value;
		}
		
	}
}