package com.game.cloak.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 晶石合成
	 */
	public class ReqStoneCombineMessage extends Message {
	
		//道具ID
		private var _itemModel: int;
		
		//0:合成后自动装备 1:合成后放到背包里面
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//道具ID
			writeInt(_itemModel);
			//0:合成后自动装备 1:合成后放到背包里面
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//道具ID
			_itemModel = readInt();
			//0:合成后自动装备 1:合成后放到背包里面
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 170204;
		}
		
		/**
		 * get 道具ID
		 * @return 
		 */
		public function get itemModel(): int{
			return _itemModel;
		}
		
		/**
		 * set 道具ID
		 */
		public function set itemModel(value: int): void{
			this._itemModel = value;
		}
		
		/**
		 * get 0:合成后自动装备 1:合成后放到背包里面
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0:合成后自动装备 1:合成后放到背包里面
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}