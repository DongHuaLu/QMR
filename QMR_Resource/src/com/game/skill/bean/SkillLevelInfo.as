package com.game.skill.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 技能等级加成信息
	 */
	public class SkillLevelInfo extends Bean {
	
		//技能等级加成模块
		private var _key: int;
		
		//技能等级
		private var _level: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//技能等级加成模块
			writeInt(_key);
			//技能等级
			writeInt(_level);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//技能等级加成模块
			_key = readInt();
			//技能等级
			_level = readInt();
			return true;
		}
		
		/**
		 * get 技能等级加成模块
		 * @return 
		 */
		public function get key(): int{
			return _key;
		}
		
		/**
		 * set 技能等级加成模块
		 */
		public function set key(value: int): void{
			this._key = value;
		}
		
		/**
		 * get 技能等级
		 * @return 
		 */
		public function get level(): int{
			return _level;
		}
		
		/**
		 * set 技能等级
		 */
		public function set level(value: int): void{
			this._level = value;
		}
		
	}
}