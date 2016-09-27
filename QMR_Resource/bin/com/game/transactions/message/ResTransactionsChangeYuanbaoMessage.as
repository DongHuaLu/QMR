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
	 * 发送元宝改变
	 */
	public class ResTransactionsChangeYuanbaoMessage extends Message {
	
		//玩家ID
		private var _playerid: long;
		
		//元宝数量
		private var _yuanbao: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家ID
			writeLong(_playerid);
			//元宝数量
			writeInt(_yuanbao);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家ID
			_playerid = readLong();
			//元宝数量
			_yuanbao = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 122107;
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
		 * get 元宝数量
		 * @return 
		 */
		public function get yuanbao(): int{
			return _yuanbao;
		}
		
		/**
		 * set 元宝数量
		 */
		public function set yuanbao(value: int): void{
			this._yuanbao = value;
		}
		
	}
}