package com.game.pet.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 宠物技能变更
	 */
	public class ReqPetChangeSkillMessage extends Message {
	
		//宠物Id
		private var _petId: long;
		
		//位置
		private var _index: int;
		
		//技能模型
		private var _skillModel: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//宠物Id
			writeLong(_petId);
			//位置
			writeInt(_index);
			//技能模型
			writeInt(_skillModel);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//宠物Id
			_petId = readLong();
			//位置
			_index = readInt();
			//技能模型
			_skillModel = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 110205;
		}
		
		/**
		 * get 宠物Id
		 * @return 
		 */
		public function get petId(): long{
			return _petId;
		}
		
		/**
		 * set 宠物Id
		 */
		public function set petId(value: long): void{
			this._petId = value;
		}
		
		/**
		 * get 位置
		 * @return 
		 */
		public function get index(): int{
			return _index;
		}
		
		/**
		 * set 位置
		 */
		public function set index(value: int): void{
			this._index = value;
		}
		
		/**
		 * get 技能模型
		 * @return 
		 */
		public function get skillModel(): int{
			return _skillModel;
		}
		
		/**
		 * set 技能模型
		 */
		public function set skillModel(value: int): void{
			this._skillModel = value;
		}
		
	}
}