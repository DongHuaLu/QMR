package com.game.skill.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 技能开始升级
	 */
	public class SkillStartLevelUpMessage extends Message {
	
		//技能模板Id
		private var _skillModelId: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//技能模板Id
			writeInt(_skillModelId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//技能模板Id
			_skillModelId = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 107103;
		}
		
		/**
		 * get 技能模板Id
		 * @return 
		 */
		public function get skillModelId(): int{
			return _skillModelId;
		}
		
		/**
		 * set 技能模板Id
		 */
		public function set skillModelId(value: int): void{
			this._skillModelId = value;
		}
		
	}
}