package com.game.skill.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 技能领悟结果
	 */
	public class SkillLingWuResultMessage extends Message {
	
		//技能模板Id
		private var _skillModelId: int;
		
		//技能等级
		private var _grade: int;
		
		//1成功 0失败
		private var _issuccess: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//技能模板Id
			writeInt(_skillModelId);
			//技能等级
			writeInt(_grade);
			//1成功 0失败
			writeByte(_issuccess);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//技能模板Id
			_skillModelId = readInt();
			//技能等级
			_grade = readInt();
			//1成功 0失败
			_issuccess = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 107105;
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
		
		/**
		 * get 技能等级
		 * @return 
		 */
		public function get grade(): int{
			return _grade;
		}
		
		/**
		 * set 技能等级
		 */
		public function set grade(value: int): void{
			this._grade = value;
		}
		
		/**
		 * get 1成功 0失败
		 * @return 
		 */
		public function get issuccess(): int{
			return _issuccess;
		}
		
		/**
		 * set 1成功 0失败
		 */
		public function set issuccess(value: int): void{
			this._issuccess = value;
		}
		
	}
}