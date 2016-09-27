package com.game.realm.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回前端错误内容
	 */
	public class ResRealmErrToClientMessage extends Message {
	
		//1道具不足，2铜币不足，3真气不足，4强化已满，5元宝不足
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//1道具不足，2铜币不足，3真气不足，4强化已满，5元宝不足
			writeInt(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//1道具不足，2铜币不足，3真气不足，4强化已满，5元宝不足
			_type = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 158104;
		}
		
		/**
		 * get 1道具不足，2铜币不足，3真气不足，4强化已满，5元宝不足
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 1道具不足，2铜币不足，3真气不足，4强化已满，5元宝不足
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}