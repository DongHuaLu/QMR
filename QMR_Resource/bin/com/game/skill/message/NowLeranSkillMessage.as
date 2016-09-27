package com.game.skill.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 当前正在学习的技能
	 */
	public class NowLeranSkillMessage extends Message {
	
		//技能模板Id 如果没有返回-1
		private var _skillModelId: int;
		
		//剩余时间(秒)
		private var _remainingTime: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//技能模板Id 如果没有返回-1
			writeInt(_skillModelId);
			//剩余时间(秒)
			writeInt(_remainingTime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//技能模板Id 如果没有返回-1
			_skillModelId = readInt();
			//剩余时间(秒)
			_remainingTime = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 107106;
		}
		
		/**
		 * get 技能模板Id 如果没有返回-1
		 * @return 
		 */
		public function get skillModelId(): int{
			return _skillModelId;
		}
		
		/**
		 * set 技能模板Id 如果没有返回-1
		 */
		public function set skillModelId(value: int): void{
			this._skillModelId = value;
		}
		
		/**
		 * get 剩余时间(秒)
		 * @return 
		 */
		public function get remainingTime(): int{
			return _remainingTime;
		}
		
		/**
		 * set 剩余时间(秒)
		 */
		public function set remainingTime(value: int): void{
			this._remainingTime = value;
		}
		
	}
}