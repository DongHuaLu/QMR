package com.game.marriage.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 配偶在线状态
	 */
	public class ResOnlineStatusToClientMessage extends Message {
	
		//在线状态，1上线，2下线
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//在线状态，1上线，2下线
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//在线状态，1上线，2下线
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163120;
		}
		
		/**
		 * get 在线状态，1上线，2下线
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 在线状态，1上线，2下线
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}