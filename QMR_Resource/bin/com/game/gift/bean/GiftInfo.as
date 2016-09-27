package com.game.gift.bean{
	import com.game.backpack.bean.ItemInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 礼包信息
	 */
	public class GiftInfo extends Bean {
	
		//礼包id
		private var _giftid: int;
		
		//礼包类型(1-蓝钻每日 2-年费蓝钻每日 3-蓝钻新手 4-蓝钻成长 5-3366每日)
		private var _gifttype: int;
		
		//礼包相关数据
		private var _value: int;
		
		//是否可领取 0-可领取 1-未达成不可领 2-已领取不可领
		private var _canreceive: int;
		
		//奖励道具信息
		private var _iteminfos: Vector.<com.game.backpack.bean.ItemInfo> = new Vector.<com.game.backpack.bean.ItemInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//礼包id
			writeInt(_giftid);
			//礼包类型(1-蓝钻每日 2-年费蓝钻每日 3-蓝钻新手 4-蓝钻成长 5-3366每日)
			writeByte(_gifttype);
			//礼包相关数据
			writeInt(_value);
			//是否可领取 0-可领取 1-未达成不可领 2-已领取不可领
			writeByte(_canreceive);
			//奖励道具信息
			writeShort(_iteminfos.length);
			for (var i: int = 0; i < _iteminfos.length; i++) {
				writeBean(_iteminfos[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//礼包id
			_giftid = readInt();
			//礼包类型(1-蓝钻每日 2-年费蓝钻每日 3-蓝钻新手 4-蓝钻成长 5-3366每日)
			_gifttype = readByte();
			//礼包相关数据
			_value = readInt();
			//是否可领取 0-可领取 1-未达成不可领 2-已领取不可领
			_canreceive = readByte();
			//奖励道具信息
			var iteminfos_length : int = readShort();
			for (var i: int = 0; i < iteminfos_length; i++) {
				_iteminfos[i] = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			}
			return true;
		}
		
		/**
		 * get 礼包id
		 * @return 
		 */
		public function get giftid(): int{
			return _giftid;
		}
		
		/**
		 * set 礼包id
		 */
		public function set giftid(value: int): void{
			this._giftid = value;
		}
		
		/**
		 * get 礼包类型(1-蓝钻每日 2-年费蓝钻每日 3-蓝钻新手 4-蓝钻成长 5-3366每日)
		 * @return 
		 */
		public function get gifttype(): int{
			return _gifttype;
		}
		
		/**
		 * set 礼包类型(1-蓝钻每日 2-年费蓝钻每日 3-蓝钻新手 4-蓝钻成长 5-3366每日)
		 */
		public function set gifttype(value: int): void{
			this._gifttype = value;
		}
		
		/**
		 * get 礼包相关数据
		 * @return 
		 */
		public function get value(): int{
			return _value;
		}
		
		/**
		 * set 礼包相关数据
		 */
		public function set value(value: int): void{
			this._value = value;
		}
		
		/**
		 * get 是否可领取 0-可领取 1-未达成不可领 2-已领取不可领
		 * @return 
		 */
		public function get canreceive(): int{
			return _canreceive;
		}
		
		/**
		 * set 是否可领取 0-可领取 1-未达成不可领 2-已领取不可领
		 */
		public function set canreceive(value: int): void{
			this._canreceive = value;
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
		
	}
}