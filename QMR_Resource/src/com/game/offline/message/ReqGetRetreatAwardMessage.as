package com.game.offline.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 前端请求闭关奖励
	 */
	public class ReqGetRetreatAwardMessage extends Message {
	
		//领取类型，0单倍 1双倍 2自动双倍
		private var _getType: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//领取类型，0单倍 1双倍 2自动双倍
			writeInt(_getType);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//领取类型，0单倍 1双倍 2自动双倍
			_getType = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 150202;
		}
		
		/**
		 * get 领取类型，0单倍 1双倍 2自动双倍
		 * @return 
		 */
		public function get getType(): int{
			return _getType;
		}
		
		/**
		 * set 领取类型，0单倍 1双倍 2自动双倍
		 */
		public function set getType(value: int): void{
			this._getType = value;
		}
		
	}
}