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
	 * 打开急速升级丹面板(不用)
	 */
	public class ReqRecallExpPanelMessage extends Message {
	
		//物品唯一id
		private var _item: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//物品唯一id
			writeLong(_item);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//物品唯一id
			_item = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 168207;
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
		
	}
}