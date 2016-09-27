package com.game.country.message{
	import com.game.backpack.bean.ItemInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 王城宝箱奖励显示
	 */
	public class ResKingCityChestPanelShowToClientMessage extends Message {
	
		//道具奖励列表
		private var _itemlist: Vector.<com.game.backpack.bean.ItemInfo> = new Vector.<com.game.backpack.bean.ItemInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//道具奖励列表
			writeShort(_itemlist.length);
			for (i = 0; i < _itemlist.length; i++) {
				writeBean(_itemlist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//道具奖励列表
			var itemlist_length : int = readShort();
			for (i = 0; i < itemlist_length; i++) {
				_itemlist[i] = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 146108;
		}
		
		/**
		 * get 道具奖励列表
		 * @return 
		 */
		public function get itemlist(): Vector.<com.game.backpack.bean.ItemInfo>{
			return _itemlist;
		}
		
		/**
		 * set 道具奖励列表
		 */
		public function set itemlist(value: Vector.<com.game.backpack.bean.ItemInfo>): void{
			this._itemlist = value;
		}
		
	}
}