package com.game.hiddenweapon.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 暗器技能ICO
	 */
	public class HiddenWeaponSkillIco extends Bean {
	
		//技能
		private var _skill: int;
		
		//当前投掷数
		private var _times: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//技能
			writeInt(_skill);
			//当前投掷数
			writeInt(_times);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//技能
			_skill = readInt();
			//当前投掷数
			_times = readInt();
			return true;
		}
		
		/**
		 * get 技能
		 * @return 
		 */
		public function get skill(): int{
			return _skill;
		}
		
		/**
		 * set 技能
		 */
		public function set skill(value: int): void{
			this._skill = value;
		}
		
		/**
		 * get 当前投掷数
		 * @return 
		 */
		public function get times(): int{
			return _times;
		}
		
		/**
		 * set 当前投掷数
		 */
		public function set times(value: int): void{
			this._times = value;
		}
		
	}
}