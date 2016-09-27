package com.game.skill.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 学习技能
	 */
	public class StudySkillMessage extends Message {
	
		//技能模板Id
		private var _skillModelId: int;
		
		//技能书Id
		private var _bookId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//技能模板Id
			writeInt(_skillModelId);
			//技能书Id
			writeLong(_bookId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//技能模板Id
			_skillModelId = readInt();
			//技能书Id
			_bookId = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 107201;
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
		 * get 技能书Id
		 * @return 
		 */
		public function get bookId(): long{
			return _bookId;
		}
		
		/**
		 * set 技能书Id
		 */
		public function set bookId(value: long): void{
			this._bookId = value;
		}
		
	}
}