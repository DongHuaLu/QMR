package com.game.melting.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 对指定道具进行熔炼操作
	 */
	public class ReqMeltingItemToServerMessage extends Message {
	
		//要熔炼的道具唯一ID
		private var _itemid: long;
		
		//熔炼石id
		private var _metingid: long;
		
		//类型：0手动，1自动
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//要熔炼的道具唯一ID
			writeLong(_itemid);
			//熔炼石id
			writeLong(_metingid);
			//类型：0手动，1自动
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//要熔炼的道具唯一ID
			_itemid = readLong();
			//熔炼石id
			_metingid = readLong();
			//类型：0手动，1自动
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 154201;
		}
		
		/**
		 * get 要熔炼的道具唯一ID
		 * @return 
		 */
		public function get itemid(): long{
			return _itemid;
		}
		
		/**
		 * set 要熔炼的道具唯一ID
		 */
		public function set itemid(value: long): void{
			this._itemid = value;
		}
		
		/**
		 * get 熔炼石id
		 * @return 
		 */
		public function get metingid(): long{
			return _metingid;
		}
		
		/**
		 * set 熔炼石id
		 */
		public function set metingid(value: long): void{
			this._metingid = value;
		}
		
		/**
		 * get 类型：0手动，1自动
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型：0手动，1自动
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}