package com.game.map.message{
	import com.game.map.bean.PetInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 周围宠物
	 */
	public class ResRoundPetMessage extends Message {
	
		//周围宠物信息
		private var _pet: PetInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//周围宠物信息
			writeBean(_pet);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//周围宠物信息
			_pet = readBean(PetInfo) as PetInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101104;
		}
		
		/**
		 * get 周围宠物信息
		 * @return 
		 */
		public function get pet(): PetInfo{
			return _pet;
		}
		
		/**
		 * set 周围宠物信息
		 */
		public function set pet(value: PetInfo): void{
			this._pet = value;
		}
		
	}
}