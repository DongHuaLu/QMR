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
	 * 怪物死亡
	 */
	public class ResMonsterDieMessage extends Message {
	
		//角色Id
		private var _monsterId: long;
		
		//死亡状态 2-假死 3-死亡
		private var _die: int;
		
		//杀死人Id
		private var _killer: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色Id
			writeLong(_monsterId);
			//死亡状态 2-假死 3-死亡
			writeByte(_die);
			//杀死人Id
			writeLong(_killer);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色Id
			_monsterId = readLong();
			//死亡状态 2-假死 3-死亡
			_die = readByte();
			//杀死人Id
			_killer = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 114108;
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
		 * get 死亡状态 2-假死 3-死亡
		 * @return 
		 */
		public function get die(): int{
			return _die;
		}
		
		/**
		 * set 死亡状态 2-假死 3-死亡
		 */
		public function set die(value: int): void{
			this._die = value;
		}
		
		/**
		 * get 杀死人Id
		 * @return 
		 */
		public function get killer(): long{
			return _killer;
		}
		
		/**
		 * set 杀死人Id
		 */
		public function set killer(value: long): void{
			this._killer = value;
		}
		
	}
}