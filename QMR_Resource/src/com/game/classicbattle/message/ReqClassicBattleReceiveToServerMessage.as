package com.game.classicbattle.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 领取首通奖励
	 */
	public class ReqClassicBattleReceiveToServerMessage extends Message {
	
		//当前层数
		private var _currlayers: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前层数
			writeInt(_currlayers);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前层数
			_currlayers = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 165202;
		}
		
		/**
		 * get 当前层数
		 * @return 
		 */
		public function get currlayers(): int{
			return _currlayers;
		}
		
		/**
		 * set 当前层数
		 */
		public function set currlayers(value: int): void{
			this._currlayers = value;
		}
		
	}
}