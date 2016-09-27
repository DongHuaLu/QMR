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
	 * 体力变化
	 */
	public class ResMonsterSpChangeMessage extends Message {
	
		//角色Id
		private var _monsterId: long;
		
		//当前SP
		private var _currentSp: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色Id
			writeLong(_monsterId);
			//当前SP
			writeInt(_currentSp);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色Id
			_monsterId = readLong();
			//当前SP
			_currentSp = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 114103;
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
		 * get 当前SP
		 * @return 
		 */
		public function get currentSp(): int{
			return _currentSp;
		}
		
		/**
		 * set 当前SP
		 */
		public function set currentSp(value: int): void{
			this._currentSp = value;
		}
		
	}
}