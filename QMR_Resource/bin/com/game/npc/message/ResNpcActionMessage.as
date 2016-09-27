package com.game.npc.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * npc行为
	 */
	public class ResNpcActionMessage extends Message {
	
		//npcId
		private var _npcId: long;
		
		//行为目标
		private var _tatget: long;
		
		//行为类型
		private var _actionType: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//npcId
			writeLong(_npcId);
			//行为目标
			writeLong(_tatget);
			//行为类型
			writeInt(_actionType);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//npcId
			_npcId = readLong();
			//行为目标
			_tatget = readLong();
			//行为类型
			_actionType = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 140104;
		}
		
		/**
		 * get npcId
		 * @return 
		 */
		public function get npcId(): long{
			return _npcId;
		}
		
		/**
		 * set npcId
		 */
		public function set npcId(value: long): void{
			this._npcId = value;
		}
		
		/**
		 * get 行为目标
		 * @return 
		 */
		public function get tatget(): long{
			return _tatget;
		}
		
		/**
		 * set 行为目标
		 */
		public function set tatget(value: long): void{
			this._tatget = value;
		}
		
		/**
		 * get 行为类型
		 * @return 
		 */
		public function get actionType(): int{
			return _actionType;
		}
		
		/**
		 * set 行为类型
		 */
		public function set actionType(value: int): void{
			this._actionType = value;
		}
		
	}
}