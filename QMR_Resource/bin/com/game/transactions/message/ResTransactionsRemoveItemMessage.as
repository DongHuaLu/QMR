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
	 * 移除交易框道具
	 */
	public class ResTransactionsRemoveItemMessage extends Message {
	
		//移除交易的玩家ID
		private var _playerid: long;
		
		//道具唯一ID
		private var _itemid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//移除交易的玩家ID
			writeLong(_playerid);
			//道具唯一ID
			writeLong(_itemid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//移除交易的玩家ID
			_playerid = readLong();
			//道具唯一ID
			_itemid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 122105;
		}
		
		/**
		 * get 移除交易的玩家ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 移除交易的玩家ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 道具唯一ID
		 * @return 
		 */
		public function get itemid(): long{
			return _itemid;
		}
		
		/**
		 * set 道具唯一ID
		 */
		public function set itemid(value: long): void{
			this._itemid = value;
		}
		
	}
}