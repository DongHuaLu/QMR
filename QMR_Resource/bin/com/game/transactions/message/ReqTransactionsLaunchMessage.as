package com.game.transactions.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * A玩家发起交易
	 */
	public class ReqTransactionsLaunchMessage extends Message {
	
		//B交易玩家ID
		private var _playerid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//B交易玩家ID
			writeLong(_playerid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//B交易玩家ID
			_playerid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 122201;
		}
		
		/**
		 * get B交易玩家ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set B交易玩家ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
	}
}