package com.game.chestbox.bean{
	import com.game.backpack.bean.ItemInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 宝箱格子信息
	 */
	public class ChestGridInfo extends Bean {
	
		//格子编号
		private var _grididx: int;
		
		//格子类型
		private var _gridtype: int;
		
		//当前格子中的物品信息
		private var _curiteminfo: com.game.backpack.bean.ItemInfo;
		
		//当前格子中的多倍奖励物品信息
		private var _itemlist: Vector.<com.game.backpack.bean.ItemInfo> = new Vector.<com.game.backpack.bean.ItemInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//格子编号
			writeInt(_grididx);
			//格子类型
			writeInt(_gridtype);
			//当前格子中的物品信息
			writeBean(_curiteminfo);
			//当前格子中的多倍奖励物品信息
			writeShort(_itemlist.length);
			for (var i: int = 0; i < _itemlist.length; i++) {
				writeBean(_itemlist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//格子编号
			_grididx = readInt();
			//格子类型
			_gridtype = readInt();
			//当前格子中的物品信息
			_curiteminfo = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			//当前格子中的多倍奖励物品信息
			var itemlist_length : int = readShort();
			for (var i: int = 0; i < itemlist_length; i++) {
				_itemlist[i] = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			}
			return true;
		}
		
		/**
		 * get 格子编号
		 * @return 
		 */
		public function get grididx(): int{
			return _grididx;
		}
		
		/**
		 * set 格子编号
		 */
		public function set grididx(value: int): void{
			this._grididx = value;
		}
		
		/**
		 * get 格子类型
		 * @return 
		 */
		public function get gridtype(): int{
			return _gridtype;
		}
		
		/**
		 * set 格子类型
		 */
		public function set gridtype(value: int): void{
			this._gridtype = value;
		}
		
		/**
		 * get 当前格子中的物品信息
		 * @return 
		 */
		public function get curiteminfo(): com.game.backpack.bean.ItemInfo{
			return _curiteminfo;
		}
		
		/**
		 * set 当前格子中的物品信息
		 */
		public function set curiteminfo(value: com.game.backpack.bean.ItemInfo): void{
			this._curiteminfo = value;
		}
		
		/**
		 * get 当前格子中的多倍奖励物品信息
		 * @return 
		 */
		public function get itemlist(): Vector.<com.game.backpack.bean.ItemInfo>{
			return _itemlist;
		}
		
		/**
		 * set 当前格子中的多倍奖励物品信息
		 */
		public function set itemlist(value: Vector.<com.game.backpack.bean.ItemInfo>): void{
			this._itemlist = value;
		}
		
	}
}