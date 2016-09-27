package com.game.recall.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 打开急速升级丹面板
	 */
	public class ResRecallExpPanelMessage extends Message {
	
		//物品唯一id
		private var _item: long;
		
		//总经验
		private var _expAll: long;
		
		//本次经验
		private var _expTime: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//物品唯一id
			writeLong(_item);
			//总经验
			writeLong(_expAll);
			//本次经验
			writeLong(_expTime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//物品唯一id
			_item = readLong();
			//总经验
			_expAll = readLong();
			//本次经验
			_expTime = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 168107;
		}
		
		/**
		 * get 物品唯一id
		 * @return 
		 */
		public function get item(): long{
			return _item;
		}
		
		/**
		 * set 物品唯一id
		 */
		public function set item(value: long): void{
			this._item = value;
		}
		
		/**
		 * get 总经验
		 * @return 
		 */
		public function get expAll(): long{
			return _expAll;
		}
		
		/**
		 * set 总经验
		 */
		public function set expAll(value: long): void{
			this._expAll = value;
		}
		
		/**
		 * get 本次经验
		 * @return 
		 */
		public function get expTime(): long{
			return _expTime;
		}
		
		/**
		 * set 本次经验
		 */
		public function set expTime(value: long): void{
			this._expTime = value;
		}
		
	}
}