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
	 * 等级变化
	 */
	public class ResPlayerLevelUpMessage extends Message {
	
		//角色Id
		private var _personId: long;
		
		//当前等级
		private var _currentLevel: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色Id
			writeLong(_personId);
			//当前等级
			writeInt(_currentLevel);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色Id
			_personId = readLong();
			//当前等级
			_currentLevel = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103104;
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
		 * get 当前等级
		 * @return 
		 */
		public function get currentLevel(): int{
			return _currentLevel;
		}
		
		/**
		 * set 当前等级
		 */
		public function set currentLevel(value: int): void{
			this._currentLevel = value;
		}
		
	}
}