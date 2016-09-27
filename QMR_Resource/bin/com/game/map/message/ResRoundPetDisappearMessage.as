package com.game.map.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 周围消失宠物
	 */
	public class ResRoundPetDisappearMessage extends Message {
	
		//消失宠物列表
		private var _petIds: Vector.<long> = new Vector.<long>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//消失宠物列表
			writeShort(_petIds.length);
			for (i = 0; i < _petIds.length; i++) {
				writeLong(_petIds[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//消失宠物列表
			var petIds_length : int = readShort();
			for (i = 0; i < petIds_length; i++) {
				_petIds[i] = readLong();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101108;
		}
		
		/**
		 * get 消失宠物列表
		 * @return 
		 */
		public function get petIds(): Vector.<long>{
			return _petIds;
		}
		
		/**
		 * set 消失宠物列表
		 */
		public function set petIds(value: Vector.<long>): void{
			this._petIds = value;
		}
		
	}
}