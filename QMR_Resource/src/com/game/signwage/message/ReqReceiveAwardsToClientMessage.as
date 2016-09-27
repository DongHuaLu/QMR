package com.game.signwage.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 领取签到奖励
	 */
	public class ReqReceiveAwardsToClientMessage extends Message {
	
		//领取奖励的类型，奖励对应的签到次数
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//领取奖励的类型，奖励对应的签到次数
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//领取奖励的类型，奖励对应的签到次数
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 152203;
		}
		
		/**
		 * get 领取奖励的类型，奖励对应的签到次数
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 领取奖励的类型，奖励对应的签到次数
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}