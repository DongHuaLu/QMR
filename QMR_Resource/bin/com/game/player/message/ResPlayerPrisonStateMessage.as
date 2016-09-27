package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玩家被关监狱情况
	 */
	public class ResPlayerPrisonStateMessage extends Message {
	
		//关监狱次数
		private var _prisontimes: int;
		
		//监狱剩余时间(单位 秒)
		private var _prisonremaintime: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//关监狱次数
			writeInt(_prisontimes);
			//监狱剩余时间(单位 秒)
			writeInt(_prisonremaintime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//关监狱次数
			_prisontimes = readInt();
			//监狱剩余时间(单位 秒)
			_prisonremaintime = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103134;
		}
		
		/**
		 * get 关监狱次数
		 * @return 
		 */
		public function get prisontimes(): int{
			return _prisontimes;
		}
		
		/**
		 * set 关监狱次数
		 */
		public function set prisontimes(value: int): void{
			this._prisontimes = value;
		}
		
		/**
		 * get 监狱剩余时间(单位 秒)
		 * @return 
		 */
		public function get prisonremaintime(): int{
			return _prisonremaintime;
		}
		
		/**
		 * set 监狱剩余时间(单位 秒)
		 */
		public function set prisonremaintime(value: int): void{
			this._prisonremaintime = value;
		}
		
	}
}