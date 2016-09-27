package com.game.monster.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 生命变化
	 */
	public class ResMonsterHpChangeMessage extends Message {
	
		//角色Id
		private var _monsterId: long;
		
		//当前HP
		private var _currentHp: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色Id
			writeLong(_monsterId);
			//当前HP
			writeInt(_currentHp);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色Id
			_monsterId = readLong();
			//当前HP
			_currentHp = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 114101;
		}
		
		/**
		 * get 角色Id
		 * @return 
		 */
		public function get monsterId(): long{
			return _monsterId;
		}
		
		/**
		 * set 角色Id
		 */
		public function set monsterId(value: long): void{
			this._monsterId = value;
		}
		
		/**
		 * get 当前HP
		 * @return 
		 */
		public function get currentHp(): int{
			return _currentHp;
		}
		
		/**
		 * set 当前HP
		 */
		public function set currentHp(value: int): void{
			this._currentHp = value;
		}
		
	}
}