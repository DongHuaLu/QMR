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
	 * 玩家开始采集
	 */
	public class ResStartGatherMessage extends Message {
	
		//角色Id
		private var _personId: long;
		
		//行为目标
		private var _tatget: long;
		
		//采集时间
		private var _costtime: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色Id
			writeLong(_personId);
			//行为目标
			writeLong(_tatget);
			//采集时间
			writeInt(_costtime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色Id
			_personId = readLong();
			//行为目标
			_tatget = readLong();
			//采集时间
			_costtime = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 140102;
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
		 * get 采集时间
		 * @return 
		 */
		public function get costtime(): int{
			return _costtime;
		}
		
		/**
		 * set 采集时间
		 */
		public function set costtime(value: int): void{
			this._costtime = value;
		}
		
	}
}