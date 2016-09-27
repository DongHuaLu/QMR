package com.game.backpack.message{
	import com.game.backpack.bean.ItemInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 物品信息增加
	 */
	public class ResItemAddMessage extends Message {
	
		//物品信息
		private var _item: ItemInfo;
		
		//获取方式
		private var _reason: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//物品信息
			writeBean(_item);
			//获取方式
			writeByte(_reason);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//物品信息
			_item = readBean(ItemInfo) as ItemInfo;
			//获取方式
			_reason = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 104102;
		}
		
		/**
		 * get 物品信息
		 * @return 
		 */
		public function get item(): ItemInfo{
			return _item;
		}
		
		/**
		 * set 物品信息
		 */
		public function set item(value: ItemInfo): void{
			this._item = value;
		}
		
		/**
		 * get 获取方式
		 * @return 
		 */
		public function get reason(): int{
			return _reason;
		}
		
		/**
		 * set 获取方式
		 */
		public function set reason(value: int): void{
			this._reason = value;
		}
		
	}
}