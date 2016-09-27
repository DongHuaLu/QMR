package com.game.amulet.message{
	import com.game.amulet.bean.AmuletSkillInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 护身符技能列表
	 */
	public class ResAmuletSetSkillMessage extends Message {
	
		//新学的技能所在的格子编号,从1开始
		private var _index: int;
		
		//技能
		private var _skills: Vector.<AmuletSkillInfo> = new Vector.<AmuletSkillInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//新学的技能所在的格子编号,从1开始
			writeByte(_index);
			//技能
			writeShort(_skills.length);
			for (i = 0; i < _skills.length; i++) {
				writeBean(_skills[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//新学的技能所在的格子编号,从1开始
			_index = readByte();
			//技能
			var skills_length : int = readShort();
			for (i = 0; i < skills_length; i++) {
				_skills[i] = readBean(AmuletSkillInfo) as AmuletSkillInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 166103;
		}
		
		/**
		 * get 新学的技能所在的格子编号,从1开始
		 * @return 
		 */
		public function get index(): int{
			return _index;
		}
		
		/**
		 * set 新学的技能所在的格子编号,从1开始
		 */
		public function set index(value: int): void{
			this._index = value;
		}
		
		/**
		 * get 技能
		 * @return 
		 */
		public function get skills(): Vector.<AmuletSkillInfo>{
			return _skills;
		}
		
		/**
		 * set 技能
		 */
		public function set skills(value: Vector.<AmuletSkillInfo>): void{
			this._skills = value;
		}
		
	}
}