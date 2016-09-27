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
	 * 宠物出战
	 */
	public class ResPetShowMessage extends Message {
	
		//宠物信息
		private var _pet: PetDetailInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//宠物信息
			writeBean(_pet);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//宠物信息
			_pet = readBean(PetDetailInfo) as PetDetailInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 110108;
		}
		
		/**
		 * get 宠物信息
		 * @return 
		 */
		public function get pet(): PetDetailInfo{
			return _pet;
		}
		
		/**
		 * set 宠物信息
		 */
		public function set pet(value: PetDetailInfo): void{
			this._pet = value;
		}
		
	}
}