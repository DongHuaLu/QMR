package com.game.horseweapon.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 骑乘兵器技能信息
	 */
	public class HorseWeaponSkillInfo extends Bean {
	
		//技能
		private var _skill: int;
		
		//等级
		private var _level: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//技能
			writeInt(_skill);
			//等级
			writeInt(_level);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//技能
			_skill = readInt();
			//等级
			_level = readInt();
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
		
	}
}