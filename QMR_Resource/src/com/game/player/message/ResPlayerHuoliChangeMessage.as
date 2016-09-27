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
	 * 活力变化
	 */
	public class ResPlayerHuoliChangeMessage extends Message {
	
		//角色Id
		private var _personId: long;
		
		//当前活力
		private var _currentHuoli: int;
		
		//活力变化
		private var _changeHuoli: int;
		
		//活力变化原因
		private var _reason: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色Id
			writeLong(_personId);
			//当前活力
			writeInt(_currentHuoli);
			//活力变化
			writeInt(_changeHuoli);
			//活力变化原因
			writeInt(_reason);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色Id
			_personId = readLong();
			//当前活力
			_currentHuoli = readInt();
			//活力变化
			_changeHuoli = readInt();
			//活力变化原因
			_reason = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103139;
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
		 * get 当前活力
		 * @return 
		 */
		public function get currentHuoli(): int{
			return _currentHuoli;
		}
		
		/**
		 * set 当前活力
		 */
		public function set currentHuoli(value: int): void{
			this._currentHuoli = value;
		}
		
		/**
		 * get 活力变化
		 * @return 
		 */
		public function get changeHuoli(): int{
			return _changeHuoli;
		}
		
		/**
		 * set 活力变化
		 */
		public function set changeHuoli(value: int): void{
			this._changeHuoli = value;
		}
		
		/**
		 * get 活力变化原因
		 * @return 
		 */
		public function get reason(): int{
			return _reason;
		}
		
		/**
		 * set 活力变化原因
		 */
		public function set reason(value: int): void{
			this._reason = value;
		}
		
	}
}