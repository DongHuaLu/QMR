package com.game.recall.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 领奖
	 */
	public class ReqRecallGetPrizeMessage extends Message {
	
		//0:召回 奖励 1:回归奖励
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//0:召回 奖励 1:回归奖励
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//0:召回 奖励 1:回归奖励
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 168203;
		}
		
		/**
		 * get 0:召回 奖励 1:回归奖励
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0:召回 奖励 1:回归奖励
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}