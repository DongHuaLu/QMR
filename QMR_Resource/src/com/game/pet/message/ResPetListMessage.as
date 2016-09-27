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
	 * 宠物列表
	 */
	public class ResPetListMessage extends Message {
	
		//宠物列表
		private var _pets: Vector.<PetDetailInfo> = new Vector.<PetDetailInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//宠物列表
			writeShort(_pets.length);
			for (i = 0; i < _pets.length; i++) {
				writeBean(_pets[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//宠物列表
			var pets_length : int = readShort();
			for (i = 0; i < pets_length; i++) {
				_pets[i] = readBean(PetDetailInfo) as PetDetailInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 110101;
		}
		
		/**
		 * get 宠物列表
		 * @return 
		 */
		public function get pets(): Vector.<PetDetailInfo>{
			return _pets;
		}
		
		/**
		 * set 宠物列表
		 */
		public function set pets(value: Vector.<PetDetailInfo>): void{
			this._pets = value;
		}
		
	}
}