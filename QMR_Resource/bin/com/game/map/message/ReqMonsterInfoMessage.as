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
	 * 变换方面
	 */
	public class ReqMonsterInfoMessage extends Message {
	
		//怪物唯一ID
		private var _monsterId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//怪物唯一ID
			writeLong(_monsterId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//怪物唯一ID
			_monsterId = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101214;
		}
		
		/**
		 * get 怪物唯一ID
		 * @return 
		 */
		public function get monsterId(): long{
			return _monsterId;
		}
		
		/**
		 * set 怪物唯一ID
		 */
		public function set monsterId(value: long): void{
			this._monsterId = value;
		}
		
	}
}