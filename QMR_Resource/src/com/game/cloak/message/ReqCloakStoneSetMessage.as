package com.game.cloak.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 披风镶嵌
	 */
	public class ReqCloakStoneSetMessage extends Message {
	
		//道具唯一ID
		private var _item: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//道具唯一ID
			writeLong(_item);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//道具唯一ID
			_item = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 170203;
		}
		
		/**
		 * get 道具唯一ID
		 * @return 
		 */
		public function get item(): long{
			return _item;
		}
		
		/**
		 * set 道具唯一ID
		 */
		public function set item(value: long): void{
			this._item = value;
		}
		
	}
}