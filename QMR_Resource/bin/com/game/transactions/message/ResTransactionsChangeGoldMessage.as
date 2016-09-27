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
	 * 发送金币改变
	 */
	public class ResTransactionsChangeGoldMessage extends Message {
	
		//玩家ID
		private var _playerid: long;
		
		//金币数量
		private var _gold: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家ID
			writeLong(_playerid);
			//金币数量
			writeInt(_gold);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家ID
			_playerid = readLong();
			//金币数量
			_gold = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 122106;
		}
		
		/**
		 * get 玩家ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 玩家ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 金币数量
		 * @return 
		 */
		public function get gold(): int{
			return _gold;
		}
		
		/**
		 * set 金币数量
		 */
		public function set gold(value: int): void{
			this._gold = value;
		}
		
	}
}