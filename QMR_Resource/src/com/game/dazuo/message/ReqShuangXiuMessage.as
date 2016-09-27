package com.game.dazuo.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求双修
	 */
	public class ReqShuangXiuMessage extends Message {
	
		//玩家ID
		private var _role: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家ID
			writeLong(_role);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家ID
			_role = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 136202;
		}
		
		/**
		 * get 玩家ID
		 * @return 
		 */
		public function get role(): long{
			return _role;
		}
		
		/**
		 * set 玩家ID
		 */
		public function set role(value: long): void{
			this._role = value;
		}
		
	}
}