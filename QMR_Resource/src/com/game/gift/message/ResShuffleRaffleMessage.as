package com.game.gift.message{
	import com.game.backpack.bean.ItemInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 翻牌抽奖，发送道具列表
	 */
	public class ResShuffleRaffleMessage extends Message {
	
		//奖励道具信息
		private var _iteminfos: Vector.<com.game.backpack.bean.ItemInfo> = new Vector.<com.game.backpack.bean.ItemInfo>();
		//0发送翻牌前的列表，1发送翻牌后的道具
		private var _type: int;
		
		//道具唯一ID
		private var _itemid: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//奖励道具信息
			writeShort(_iteminfos.length);
			for (i = 0; i < _iteminfos.length; i++) {
				writeBean(_iteminfos[i]);
			}
			//0发送翻牌前的列表，1发送翻牌后的道具
			writeInt(_type);
			//道具唯一ID
			writeString(_itemid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//奖励道具信息
			var iteminfos_length : int = readShort();
			for (i = 0; i < iteminfos_length; i++) {
				_iteminfos[i] = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			}
			//0发送翻牌前的列表，1发送翻牌后的道具
			_type = readInt();
			//道具唯一ID
			_itemid = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 129105;
		}
		
		/**
		 * get 奖励道具信息
		 * @return 
		 */
		public function get iteminfos(): Vector.<com.game.backpack.bean.ItemInfo>{
			return _iteminfos;
		}
		
		/**
		 * set 奖励道具信息
		 */
		public function set iteminfos(value: Vector.<com.game.backpack.bean.ItemInfo>): void{
			this._iteminfos = value;
		}
		
		/**
		 * get 0发送翻牌前的列表，1发送翻牌后的道具
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0发送翻牌前的列表，1发送翻牌后的道具
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 道具唯一ID
		 * @return 
		 */
		public function get itemid(): String{
			return _itemid;
		}
		
		/**
		 * set 道具唯一ID
		 */
		public function set itemid(value: String): void{
			this._itemid = value;
		}
		
	}
}