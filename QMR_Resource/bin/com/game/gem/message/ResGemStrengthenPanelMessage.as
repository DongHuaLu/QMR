package com.game.gem.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 弹出强化石面板
	 */
	public class ResGemStrengthenPanelMessage extends Message {
	
		//宝石强化符模型ID
		private var _itemmodelid: int;
		
		//宝石强化符唯一ID
		private var _itemid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//宝石强化符模型ID
			writeInt(_itemmodelid);
			//宝石强化符唯一ID
			writeLong(_itemid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//宝石强化符模型ID
			_itemmodelid = readInt();
			//宝石强化符唯一ID
			_itemid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 132106;
		}
		
		/**
		 * get 宝石强化符模型ID
		 * @return 
		 */
		public function get itemmodelid(): int{
			return _itemmodelid;
		}
		
		/**
		 * set 宝石强化符模型ID
		 */
		public function set itemmodelid(value: int): void{
			this._itemmodelid = value;
		}
		
		/**
		 * get 宝石强化符唯一ID
		 * @return 
		 */
		public function get itemid(): long{
			return _itemid;
		}
		
		/**
		 * set 宝石强化符唯一ID
		 */
		public function set itemid(value: long): void{
			this._itemid = value;
		}
		
	}
}