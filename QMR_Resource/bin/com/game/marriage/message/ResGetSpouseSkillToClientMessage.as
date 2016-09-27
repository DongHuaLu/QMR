package com.game.marriage.message{
	import com.game.skill.bean.SkillInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回配偶武功
	 */
	public class ResGetSpouseSkillToClientMessage extends Message {
	
		//默认技能模板Id
		private var _defaultSkill: int;
		
		//技能信息列表
		private var _skills: Vector.<com.game.skill.bean.SkillInfo> = new Vector.<com.game.skill.bean.SkillInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//默认技能模板Id
			writeInt(_defaultSkill);
			//技能信息列表
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
			//默认技能模板Id
			_defaultSkill = readInt();
			//技能信息列表
			var skills_length : int = readShort();
			for (i = 0; i < skills_length; i++) {
				_skills[i] = readBean(com.game.skill.bean.SkillInfo) as com.game.skill.bean.SkillInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163117;
		}
		
		/**
		 * get 默认技能模板Id
		 * @return 
		 */
		public function get defaultSkill(): int{
			return _defaultSkill;
		}
		
		/**
		 * set 默认技能模板Id
		 */
		public function set defaultSkill(value: int): void{
			this._defaultSkill = value;
		}
		
		/**
		 * get 技能信息列表
		 * @return 
		 */
		public function get skills(): Vector.<com.game.skill.bean.SkillInfo>{
			return _skills;
		}
		
		/**
		 * set 技能信息列表
		 */
		public function set skills(value: Vector.<com.game.skill.bean.SkillInfo>): void{
			this._skills = value;
		}
		
	}
}