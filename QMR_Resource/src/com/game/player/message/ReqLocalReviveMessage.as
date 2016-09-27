package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 原地复活
	 */
	public class ReqLocalReviveMessage extends Message {
	
		//使用复活道具modelid
		private var _itemmodelid: int;
		
		//0自动使用玫瑰，1手动使用玫瑰复活
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//使用复活道具modelid
			writeInt(_itemmodelid);
			//0自动使用玫瑰，1手动使用玫瑰复活
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//使用复活道具modelid
			_itemmodelid = readInt();
			//0自动使用玫瑰，1手动使用玫瑰复活
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103202;
		}
		
		/**
		 * get 使用复活道具modelid
		 * @return 
		 */
		public function get itemmodelid(): int{
			return _itemmodelid;
		}
		
		/**
		 * set 使用复活道具modelid
		 */
		public function set itemmodelid(value: int): void{
			this._itemmodelid = value;
		}
		
		/**
		 * get 0自动使用玫瑰，1手动使用玫瑰复活
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0自动使用玫瑰，1手动使用玫瑰复活
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}