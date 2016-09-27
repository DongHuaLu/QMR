package com.game.player.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 最大生命变化
	 */
	public class ResPlayerMaxHpChangeMessage extends Message {
	
		//角色Id
		private var _personId: long;
		
		//当前HP
		private var _currentHp: int;
		
		//最大HP
		private var _maxHp: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色Id
			writeLong(_personId);
			//当前HP
			writeInt(_currentHp);
			//最大HP
			writeInt(_maxHp);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色Id
			_personId = readLong();
			//当前HP
			_currentHp = readInt();
			//最大HP
			_maxHp = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103109;
		}
		
		/**
		 * get 角色Id
		 * @return 
		 */
		public function get personId(): long{
			return _personId;
		}
		
		/**
		 * set 角色Id
		 */
		public function set personId(value: long): void{
			this._personId = value;
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
		
		/**
		 * get 最大HP
		 * @return 
		 */
		public function get maxHp(): int{
			return _maxHp;
		}
		
		/**
		 * set 最大HP
		 */
		public function set maxHp(value: int): void{
			this._maxHp = value;
		}
		
	}
}