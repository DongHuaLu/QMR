package com.game.transactions.message{
	import com.game.utils.long;
	import com.game.backpack.bean.ItemInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 放入道具
	 */
	public class ResTransactionsIntoItemMessage extends Message {
	
		//放入道具的玩家ID
		private var _playerid: long;
		
		//放入交易框的位置
		private var _itemposition: int;
		
		//道具信息
		private var _iteminfo: com.game.backpack.bean.ItemInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//放入道具的玩家ID
			writeLong(_playerid);
			//放入交易框的位置
			writeShort(_itemposition);
			//道具信息
			writeBean(_iteminfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//放入道具的玩家ID
			_playerid = readLong();
			//放入交易框的位置
			_itemposition = readShort();
			//道具信息
			_iteminfo = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 122104;
		}
		
		/**
		 * get 放入道具的玩家ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 放入道具的玩家ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 放入交易框的位置
		 * @return 
		 */
		public function get itemposition(): int{
			return _itemposition;
		}
		
		/**
		 * set 放入交易框的位置
		 */
		public function set itemposition(value: int): void{
			this._itemposition = value;
		}
		
		/**
		 * get 道具信息
		 * @return 
		 */
		public function get iteminfo(): com.game.backpack.bean.ItemInfo{
			return _iteminfo;
		}
		
		/**
		 * set 道具信息
		 */
		public function set iteminfo(value: com.game.backpack.bean.ItemInfo): void{
			this._iteminfo = value;
		}
		
	}
}