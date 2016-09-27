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
	 * 技能增加
	 */
	public class SkillAddMessage extends Message {
	
		//物品信息
		private var _skill: SkillInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//物品信息
			writeBean(_skill);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//物品信息
			_skill = readBean(SkillInfo) as SkillInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 107102;
		}
		
		/**
		 * get 物品信息
		 * @return 
		 */
		public function get skill(): SkillInfo{
			return _skill;
		}
		
		/**
		 * set 物品信息
		 */
		public function set skill(value: SkillInfo): void{
			this._skill = value;
		}
		
	}
}