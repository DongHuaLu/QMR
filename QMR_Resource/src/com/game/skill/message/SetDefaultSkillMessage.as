package com.game.skill.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 设置默认技能
	 */
	public class SetDefaultSkillMessage extends Message {
	
		//技能模板Id
		private var _defaultSkill: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//技能模板Id
			writeInt(_defaultSkill);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//技能模板Id
			_defaultSkill = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 107203;
		}
		
		/**
		 * get 技能模板Id
		 * @return 
		 */
		public function get defaultSkill(): int{
			return _defaultSkill;
		}
		
		/**
		 * set 技能模板Id
		 */
		public function set defaultSkill(value: int): void{
			this._defaultSkill = value;
		}
		
	}
}