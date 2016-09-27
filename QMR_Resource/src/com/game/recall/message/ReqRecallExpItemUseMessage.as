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
	 * 使用急速升级丹
	 */
	public class ReqRecallExpItemUseMessage extends Message {
	
		//物品唯一id
		private var _item: long;
		
		//0:普通 1:双倍
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//物品唯一id
			writeLong(_item);
			//0:普通 1:双倍
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//物品唯一id
			_item = readLong();
			//0:普通 1:双倍
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 168208;
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
		 * get 0:普通 1:双倍
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0:普通 1:双倍
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}