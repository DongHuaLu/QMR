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
	 * 更改pk状态
	 */
	public class ResChangePKStateMessage extends Message {
	
		//角色Id
		private var _personId: long;
		
		//pk状态
		private var _pkState: int;
		
		//是否自动切换
		private var _auto: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色Id
			writeLong(_personId);
			//pk状态
			writeInt(_pkState);
			//是否自动切换
			writeInt(_auto);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色Id
			_personId = readLong();
			//pk状态
			_pkState = readInt();
			//是否自动切换
			_auto = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103118;
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
		 * get pk状态
		 * @return 
		 */
		public function get pkState(): int{
			return _pkState;
		}
		
		/**
		 * set pk状态
		 */
		public function set pkState(value: int): void{
			this._pkState = value;
		}
		
		/**
		 * get 是否自动切换
		 * @return 
		 */
		public function get auto(): int{
			return _auto;
		}
		
		/**
		 * set 是否自动切换
		 */
		public function set auto(value: int): void{
			this._auto = value;
		}
		
	}
}