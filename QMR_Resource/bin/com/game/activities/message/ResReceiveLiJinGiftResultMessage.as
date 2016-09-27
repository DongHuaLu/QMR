package com.game.activities.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 封测礼金领取结果
	 */
	public class ResReceiveLiJinGiftResultMessage extends Message {
	
		//下次领取所需时间
		private var _nextReceiveNeedTime: int;
		
		//领取结果 1成功 0失败
		private var _result: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//下次领取所需时间
			writeInt(_nextReceiveNeedTime);
			//领取结果 1成功 0失败
			writeByte(_result);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//下次领取所需时间
			_nextReceiveNeedTime = readInt();
			//领取结果 1成功 0失败
			_result = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 138102;
		}
		
		/**
		 * get 下次领取所需时间
		 * @return 
		 */
		public function get nextReceiveNeedTime(): int{
			return _nextReceiveNeedTime;
		}
		
		/**
		 * set 下次领取所需时间
		 */
		public function set nextReceiveNeedTime(value: int): void{
			this._nextReceiveNeedTime = value;
		}
		
		/**
		 * get 领取结果 1成功 0失败
		 * @return 
		 */
		public function get result(): int{
			return _result;
		}
		
		/**
		 * set 领取结果 1成功 0失败
		 */
		public function set result(value: int): void{
			this._result = value;
		}
		
	}
}