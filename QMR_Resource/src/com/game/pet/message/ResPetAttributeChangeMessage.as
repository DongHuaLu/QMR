package com.game.pet.message{
	import com.game.utils.long;
	import com.game.pet.bean.PetAttribute;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 宠物属性变更
	 */
	public class ResPetAttributeChangeMessage extends Message {
	
		//宠物Id
		private var _petId: long;
		
		//变更的属性
		private var _attributeChange: PetAttribute;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//宠物Id
			writeLong(_petId);
			//变更的属性
			writeBean(_attributeChange);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//宠物Id
			_petId = readLong();
			//变更的属性
			_attributeChange = readBean(PetAttribute) as PetAttribute;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 110105;
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
		 * get 变更的属性
		 * @return 
		 */
		public function get attributeChange(): PetAttribute{
			return _attributeChange;
		}
		
		/**
		 * set 变更的属性
		 */
		public function set attributeChange(value: PetAttribute): void{
			this._attributeChange = value;
		}
		
	}
}