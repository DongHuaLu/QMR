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
	 * 侍宠目标
	 */
	public class ResPetTargetMessage extends Message {
	
		//目标id
		private var _targetId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//目标id
			writeLong(_targetId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//目标id
			_targetId = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 110114;
		}
		
		/**
		 * get 目标id
		 * @return 
		 */
		public function get targetId(): long{
			return _targetId;
		}
		
		/**
		 * set 目标id
		 */
		public function set targetId(value: long): void{
			this._targetId = value;
		}
		
	}
}