package com.game.equipstreng.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 对指定道具进行升阶操作
	 */
	public class ReqStageUpItemToServerMessage extends Message {
	
		//要升阶的道具唯一ID
		private var _itemid: long;
		
		//类型：0手动，1自动
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//要升阶的道具唯一ID
			writeLong(_itemid);
			//类型：0手动，1自动
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//要升阶的道具唯一ID
			_itemid = readLong();
			//类型：0手动，1自动
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 130202;
		}
		
		/**
		 * get 要升阶的道具唯一ID
		 * @return 
		 */
		public function get itemid(): long{
			return _itemid;
		}
		
		/**
		 * set 要升阶的道具唯一ID
		 */
		public function set itemid(value: long): void{
			this._itemid = value;
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