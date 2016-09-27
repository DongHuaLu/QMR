package com.game.skill.message{
	import com.game.skill.bean.SkillInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 技能变更消息
	 */
	public class SkillChangeMessage extends Message {
	
		//技能信息列表
		private var _skills: SkillInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//技能信息列表
			writeBean(_skills);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//技能信息列表
			_skills = readBean(SkillInfo) as SkillInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 107107;
		}
		
		/**
		 * get 技能信息列表
		 * @return 
		 */
		public function get skills(): SkillInfo{
			return _skills;
		}
		
		/**
		 * set 技能信息列表
		 */
		public function set skills(value: SkillInfo): void{
			this._skills = value;
		}
		
	}
}