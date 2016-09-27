package com.game.scripts.bean{
	import com.game.backpack.bean.ItemInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 面板物品框信息
	 */
	public class PanelItemInfo extends Bean {
	
		//控件ID名称
		private var _name: String;
		
		//道具信息
		private var _iteminfo: com.game.backpack.bean.ItemInfo;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//控件ID名称
			writeString(_name);
			//道具信息
			writeBean(_iteminfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//控件ID名称
			_name = readString();
			//道具信息
			_iteminfo = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			return true;
		}
		
		/**
		 * get 控件ID名称
		 * @return 
		 */
		public function get name(): String{
			return _name;
		}
		
		/**
		 * set 控件ID名称
		 */
		public function set name(value: String): void{
			this._name = value;
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