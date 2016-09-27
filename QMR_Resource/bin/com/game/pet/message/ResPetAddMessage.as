package com.game.pet.message{
	import com.game.pet.bean.PetDetailInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 宠物增加
	 */
	public class ResPetAddMessage extends Message {
	
		//增加的宠物
		private var _pet: PetDetailInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//增加的宠物
			writeBean(_pet);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//增加的宠物
			_pet = readBean(PetDetailInfo) as PetDetailInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 110102;
		}
		
		/**
		 * get 增加的宠物
		 * @return 
		 */
		public function get pet(): PetDetailInfo{
			return _pet;
		}
		
		/**
		 * set 增加的宠物
		 */
		public function set pet(value: PetDetailInfo): void{
			this._pet = value;
		}
		
	}
}