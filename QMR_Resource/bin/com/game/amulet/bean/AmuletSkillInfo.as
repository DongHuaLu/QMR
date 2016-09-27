package com.game.amulet.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 技能信息
	 */
	public class AmuletSkillInfo extends Bean {
	
		//格子编号,从1开始
		private var _index: int;
		
		//技能
		private var _skill: int;
		
		//技能加成等级
		private var _level: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//格子编号,从1开始
			writeByte(_index);
			//技能
			writeInt(_skill);
			//技能加成等级
			writeInt(_level);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//格子编号,从1开始
			_index = readByte();
			//技能
			_skill = readInt();
			//技能加成等级
			_level = readInt();
			return true;
		}
		
		/**
		 * get 格子编号,从1开始
		 * @return 
		 */
		public function get index(): int{
			return _index;
		}
		
		/**
		 * set 格子编号,从1开始
		 */
		public function set index(value: int): void{
			this._index = value;
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
		 * get 技能加成等级
		 * @return 
		 */
		public function get level(): int{
			return _level;
		}
		
		/**
		 * set 技能加成等级
		 */
		public function set level(value: int): void{
			this._level = value;
		}
		
	}
}